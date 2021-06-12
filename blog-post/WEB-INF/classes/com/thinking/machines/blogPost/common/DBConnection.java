package com.thinking.machines.blogPost.common;
import java.sql.*;
public class DBConnection
{
private DBConnection(){}
public static Connection getConnection() throws DAOException
{
Connection connection=null;
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/blog_post","bpuser","bpuser");
return connection;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
}