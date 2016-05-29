package main.java.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.java.util.DataUtil;

public class onlyCrud {
    String sql;

    DataUtil dataUtil = new DataUtil();

    Connection con;

    /*
     * 创建一张表 删除一张表 增加表中的列 删除表中的列 插入记录 删除记录
     */
    public void executeUpdate(String sql) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = dataUtil.getPreparedStatement(sql);
        ps.executeUpdate();
    }

    /*
     * 不带参的查询，得到一个结果集
     * 使用的是Statement
     */
    public ResultSet getSingleResultSet(String sql) throws ClassNotFoundException, SQLException {
        Statement statement = dataUtil.getStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }
    

    /*
     * 带参的查询(只有一个String参数)得到一个结果集
     * 使用的是PreparedStatement
     */
public  ResultSet getSingleResultSet(String sql ,String paramter) throws ClassNotFoundException, SQLException{
    PreparedStatement ps=dataUtil.getPreparedStatement(sql);
    ps.setString(1, paramter);
    ResultSet rs=ps.executeQuery();
    return rs;
}


}
