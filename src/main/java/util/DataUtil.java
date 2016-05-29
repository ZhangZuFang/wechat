package main.java.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;



public class DataUtil {

    String sql;

    public DriverManagerDataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        System.out.println("进入到DbDaoImple");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/recruit?useUnicode=true&amp&characterEncoding=UTF8");
        dataSource.setUsername("root");
        dataSource.setPassword("fds");
        return dataSource;
    }

    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        return jdbcTemplate;
    }

    public Connection getConn() throws SQLException, ClassNotFoundException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/recruit?useUnicode=true&characterEncoding=utf8";
        Connection conn;
        Class.forName(driver);
        conn = DriverManager.getConnection(url, "root", "fds");
        return conn;
    }

    /*
     * 根据sql生成对应的“prepareStament话柄”
     * 可以带参数查询，支持预编译
     */
    public PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException {
        Connection con = this.getConn();
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        return preparedStatement;
    }
    
    
    /*
     * 得到“Stament话柄”
     * 不带参数查询，不支持预编译
     */
    public Statement getStatement() throws ClassNotFoundException, SQLException{
        Connection con = this.getConn();
        Statement Statement = con.createStatement();
        return Statement;
    }

    /*
     * 得到数据库“元信息”
     */
    public DatabaseMetaData getDD() throws ClassNotFoundException, SQLException {
        Connection con = this.getConn();
        DatabaseMetaData dd = con.getMetaData();
        return dd;
    }
    
    
    
}
