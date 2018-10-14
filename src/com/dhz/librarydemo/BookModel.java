package com.dhz.librarydemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.*;

public class BookModel extends AbstractTableModel{

 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

//rowData存放行数据，columnNames存放列名
 Vector rowData,columnNames;
   
 //定义连接数据库的变量
 Statement stat = null;
 Connection ct = null;
 ResultSet rs = null;
 
 //初始化
 public void init(String sql){
  if(sql.equals("")){
   sql = "select * from books_info";
  }
  //中间
  //设置列名
  columnNames = new Vector();
  columnNames.add("序号");
  columnNames.add("书号");
  columnNames.add("书名");
  columnNames.add("朝代");
  columnNames.add("作者");
  columnNames.add("版本");
  columnNames.add("图书数量");
    
  //rowData存放多行
  rowData = new Vector();
    
  try{
  ct=BaseDao.getConn();
  stat = ct.createStatement();//创建stat对象
  rs = stat.executeQuery(sql);//查询结果
     
  while(rs.next()){
  Vector hang = new Vector();
  hang.add(rs.getInt(1));
  hang.add(rs.getString(2));
  hang.add(rs.getString(3));
  hang.add(rs.getString(4));
  hang.add(rs.getString(5));
  hang.add(rs.getString(6));
  hang.add(rs.getString(7));
  //加入到rowData中
  rowData.add(hang);
      
  }
     
 }catch(Exception e){
  e.printStackTrace();
 }finally{
  try{
  if(rs!=null){
  rs.close();
  rs = null;
   }
  if(stat != null){
  stat.close();
  stat = null;
   }
  if(ct != null){
  ct.close();
  ct = null;
   }
    }catch(Exception e){
   e.printStackTrace();
   }
  }
 }
 
 public void addBook(String sql){
	 
 }
 
 //第二个构造函数，通过传递的sql语句来获得数据模型
 public BookModel(String sql){
  this.init(sql);
 }
 
 //构造函数，用于初始化数据模型（表）
 public BookModel(){
  this.init("");
 }
 
 //得到共有多少行
 public int getRowCount() {
  // TODO Auto-generated method stub
  return this.rowData.size();
 }

 //得到共有多少列
 public int getColumnCount() {
  // TODO Auto-generated method stub
  return this.columnNames.size();
 }

 //得到某行某列的数据
 public Object getValueAt(int row, int column) {
  // TODO Auto-generated method stub
  return ((Vector)(this.rowData.get(row))).get(column);
 }
 
 //得到属性名字
 public String getColumnName(int column) {
  // TODO Auto-generated method stub
  return (String)this.columnNames.get(column);
 }
}