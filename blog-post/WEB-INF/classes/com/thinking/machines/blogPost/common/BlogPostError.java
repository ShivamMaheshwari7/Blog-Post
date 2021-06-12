package com.thinking.machines.blogPost.common;
public class BlogPostError implements java.io.Serializable
{
private String error;
public BlogPostError()
{
this.error="";
}
public BlogPostError(String error)
{
this.error=error;
}
public void setError(String error)
{
this.error=error;
}
public String getError()
{
return this.error;
}
}