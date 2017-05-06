package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DB {
 public static Connection getcon(Connection con)
 {
  String dbname="bincode";   //你创建的数据库名字
  String username="root";    //登陆数据库的账号，默认为root
  String password="zhurui";  //登陆密码
  String url="jdbc:mysql://localhost:3306/"+dbname+ "?useUnicode=true&characterEncoding=UTF-8&useSSL=false"+"&user="+username+"&password="+password+"";
 
     try {
   Class.forName("com.mysql.jdbc.Driver").newInstance();  //反射加载包
  } catch (InstantiationException e) {
 
   e.printStackTrace();
  } catch (IllegalAccessException e) {
  
   e.printStackTrace();
  } catch (ClassNotFoundException e) {
 
   e.printStackTrace();
  }
  try {
   con=DriverManager.getConnection(url);
  } catch (SQLException e) {
  
   e.printStackTrace();
  }
  return con;
  }
  
 public static void closecon(Connection con)
 {
  if(con!=null)
  {
   try {
    con.close();
   } catch (SQLException e) {
    e.printStackTrace();
   }
   con=null;
  }
 }
 public static Statement getsta(Connection con)
 {
  Statement sta=null;
  try {
   sta=con.createStatement();
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return sta;
 }
 public static void closesta(Statement sta)
 {
  if(sta!=null)
  {
   try {
    sta.close();
   } catch (SQLException e) {
    e.printStackTrace();
   }
   sta=null;
  }
 }
 public static PreparedStatement getpsta(Connection con,String sql)
 
 {
  PreparedStatement psta=null;
  try {
   psta=con.prepareStatement(sql);
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return psta;
  
 }
 public static ResultSet getrs(Statement sta,String sql)
 
 {
  ResultSet rs=null;
  try {
   rs=sta.executeQuery(sql);
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return rs;
 }
 public static void closers(ResultSet rs)
 {
  if(rs!=null)
  {
   try {
    rs.close();
   } catch (SQLException e) {
    e.printStackTrace();
   }
   rs=null;
  }
 }
public static void executeUpdate(Connection con,String sql)
{
 Statement sta=null;
 try {
  sta=con.createStatement();
  sta.executeUpdate(sql);
 } catch (SQLException e) {
  e.printStackTrace();
 }
}
}
