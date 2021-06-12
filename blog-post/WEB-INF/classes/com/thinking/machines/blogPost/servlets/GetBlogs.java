package com.thinking.machines.blogPost.servlets;
import com.thinking.machines.blogPost.common.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
import java.sql.*;
import java.util.*;
public class GetBlogs extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
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
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
List<Blog> blogs;
blogs=new LinkedList<>();
Connection connection=DBConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select blog_id,title,description,user from blogs order by blog_id DESC");
ResultSet resultSet=preparedStatement.executeQuery();
String title;
String description;
String username;
while(resultSet.next())
{
resultSet.getInt("blog_id");
title=resultSet.getString("title").trim();
description=resultSet.getString("description").trim();
username=resultSet.getString("user").trim();
blogs.add(new Blog(title,description,username));
}
resultSet.close();
preparedStatement.close();
connection.close();
PrintWriter pw=response.getWriter();
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
Gson gson=new Gson();
pw.print(gson.toJson(blogs));
pw.flush();
return;
}catch(Exception exception)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/error.html");
try
{
requestDispatcher.forward(request,response);
return;
}catch(Exception e)
{
//do nothing
}
}
}
}