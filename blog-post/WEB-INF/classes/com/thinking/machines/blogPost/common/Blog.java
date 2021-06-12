package com.thinking.machines.blogPost.common;
public class Blog implements java.io.Serializable
{
private String title;
private String description;
private String username;
public Blog()
{
this.title="";
this.description="";
this.username="";
}
public Blog(String title,String description,String username)
{
this.title=title;
this.description=description;
this.username=username;
}
public void setTitle(String title)
{
this.title=title;
}
public String getTitle()
{
return this.title;
}
public void setDescription(String description)
{
this.description=description;
}
public String getDescription()
{
return this.description;
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