package com.thinking.machines.blogPost.servlets;
import com.thinking.machines.blogPost.common.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class AddBlog extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/index.html");
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
// do nothing
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
String username=request.getParameter("username");
String title=request.getParameter("title");
String description=request.getParameter("description");
if(username==null || title==null || description==null)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/index.html");
try
{
requestDispatcher.forward(request,response);
return;
}catch(Exception e)
{
return;
}
}
username=username.trim();
title=title.trim();
description=description.trim();
int usernameLength=username.length();
int titleLength=title.length();
int descriptionLength=description.length();
if(usernameLength<8 || usernameLength>20 || titleLength<8 || titleLength>90 || descriptionLength<200 || descriptionLength>65000)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/index.html");
try
{
requestDispatcher.forward(request,response);
return;
}catch(Exception e)
{
return;
}
}
Connection connection=DBConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("insert into blogs (user, title, description) values(?,?,?)");
preparedStatement.setString(1,username);
preparedStatement.setString(2,title);
preparedStatement.setString(3,description);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
}catch(Exception exception)
{
try
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/error.html");
requestDispatcher.forward(request,response);
return;
}catch(Exception e)
{
// do nothing
}
}
}
}