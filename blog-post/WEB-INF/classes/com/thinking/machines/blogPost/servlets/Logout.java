package com.thinking.machines.blogPost.servlets;
import javax.servlet.*;
import javax.servlet.http.*;
public class Logout extends HttpServlet
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
HttpSession session=request.getSession();
session.removeAttribute("username");
RequestDispatcher requestDispatcher;
requestDispatcher=request.getRequestDispatcher("/index.html");
requestDispatcher.forward(request,response);
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