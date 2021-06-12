package com.thinking.machines.blogPost.servlets;
import com.thinking.machines.blogPost.common.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
import java.sql.*;
public class SignUp extends HttpServlet
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
PrintWriter pw=null;
try
{
String username=request.getParameter("username");
String password=request.getParameter("password");
if(username==null || password==null)
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
password=password.trim();
int usernameLength=username.length();
int passwordLength=password.length();
if(usernameLength<8 || usernameLength>20 || passwordLength<8 || passwordLength>20)
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
pw=response.getWriter();
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
Gson gson=new Gson();
Connection connection=DBConnection.getConnection();
PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement("select * from users where username=?");
preparedStatement.setString(1,username);
ResultSet resultSet=preparedStatement.executeQuery();
if(resultSet.next()==true)
{
resultSet.close();
preparedStatement.close();
connection.close();
pw.print(gson.toJson(new BlogPostError("USERNAME_EXISTS")));
pw.flush();
return;
}
resultSet.close();
preparedStatement.close();
preparedStatement=connection.prepareStatement("insert into users (username,password) values(?,?)");
preparedStatement.setString(1,username);
preparedStatement.setString(2,password);
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();
HttpSession session=request.getSession();
session.setAttribute("username",username);
pw.print(gson.toJson(new BlogPostError("")));
pw.flush();
return;
}catch(Exception exception)
{
try
{
Gson gson=new Gson();
pw.print(gson.toJson(new BlogPostError("INTERNAL_SERVER_ERROR")));
pw.flush();
return;
}catch(Exception e)
{
// do nothing
}
}
}
}