package com.thinking.machines.blogPost.common;
public class BlogPostUser implements java.io.Serializable
{
private String username;
public BlogPostUser()
{
this.username="";
}
public BlogPostUser(String username)
{
this.username=username;
}
public void setUsername(String username)
{
this.username=username;
}
public String getUsername()
{
return this.username;
}
}