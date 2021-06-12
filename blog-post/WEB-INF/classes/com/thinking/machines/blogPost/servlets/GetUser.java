package com.thinking.machines.blogPost.servlets;
import com.thinking.machines.blogPost.common.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class GetUser extends HttpServlet
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
HttpSession session=request.getSession();
String username=(String)session.getAttribute("username");
if(username==null)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/index.html");
try
{
requestDispatcher.forward(request,response);
return;
}catch(Exception exception)
{
return;
}
}
username=username.trim();
PrintWriter pw=response.getWriter();
response.setContentType("application/json");
response.setCharacterEncoding("utf-8");
Gson gson=new Gson();
pw.print(gson.toJson(new BlogPostUser(username)));
pw.flush();
return;
}catch(Exception exception)
{
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/index.html");
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