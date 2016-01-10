package com.zttx.transfer.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 数据库访问辅助类
 * <p>File：DbModelHelper.java</p>
 * <p>Title: DbModelHelper</p>
 * <p>Description:DbModelHelper</p>
 * <p>Copyright: Copyright (c) May 26, 2015</p>
 * <p>Company: 8637.com</p>
 * 
 * @author Playguy
 * @version 1.0
 */
public class DbModelHelper
{
    /**
     * 连接
     */
    private Connection           connection;
    
    /**
     * 单例对象
     */
    private static DbModelHelper instance = new DbModelHelper();
    
    private DbModelHelper()
    {
        init();
    }
    
    public static DbModelHelper getInstance()
    {
        return instance;
    }
    
    /**
     * 初始化
     * @author Playguy
     */
    private void init()
    {
        try
        {
            Class.forName(PropertiesProvider.getProperty("jdbc.driver"));
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    /**
     * 取得一个连接
     * @author Playguy
     * @return
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException
    {
        if (connection == null || connection.isClosed())
        {
            String url = PropertiesProvider.getProperty("jdbc.url");
            String username = PropertiesProvider.getProperty("jdbc.username");
            String password = PropertiesProvider.getProperty("jdbc.password");
            Properties props = new Properties();
            props.put("user", username);
            props.put("password", password);
            props.put("remarksReporting", "true");
            connection = DriverManager.getConnection(url, props);
        }
        return connection;
    }
    
    public void release(ResultSet resultSet, Statement statement, Connection conn)
    {
        if (resultSet != null)
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if (statement != null)
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        if (conn != null)
        {
            try
            {
                conn.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
