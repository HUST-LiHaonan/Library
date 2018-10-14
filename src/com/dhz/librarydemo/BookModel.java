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

//rowData��������ݣ�columnNames�������
 Vector rowData,columnNames;
   
 //�����������ݿ�ı���
 Statement stat = null;
 Connection ct = null;
 ResultSet rs = null;
 
 //��ʼ��
 public void init(String sql){
  if(sql.equals("")){
   sql = "select * from books_info";
  }
  //�м�
  //��������
  columnNames = new Vector();
  columnNames.add("���");
  columnNames.add("���");
  columnNames.add("����");
  columnNames.add("����");
  columnNames.add("����");
  columnNames.add("�汾");
  columnNames.add("ͼ������");
    
  //rowData��Ŷ���
  rowData = new Vector();
    
  try{
  ct=BaseDao.getConn();
  stat = ct.createStatement();//����stat����
  rs = stat.executeQuery(sql);//��ѯ���
     
  while(rs.next()){
  Vector hang = new Vector();
  hang.add(rs.getInt(1));
  hang.add(rs.getString(2));
  hang.add(rs.getString(3));
  hang.add(rs.getString(4));
  hang.add(rs.getString(5));
  hang.add(rs.getString(6));
  hang.add(rs.getString(7));
  //���뵽rowData��
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
 
 //�ڶ������캯����ͨ�����ݵ�sql������������ģ��
 public BookModel(String sql){
  this.init(sql);
 }
 
 //���캯�������ڳ�ʼ������ģ�ͣ���
 public BookModel(){
  this.init("");
 }
 
 //�õ����ж�����
 public int getRowCount() {
  // TODO Auto-generated method stub
  return this.rowData.size();
 }

 //�õ����ж�����
 public int getColumnCount() {
  // TODO Auto-generated method stub
  return this.columnNames.size();
 }

 //�õ�ĳ��ĳ�е�����
 public Object getValueAt(int row, int column) {
  // TODO Auto-generated method stub
  return ((Vector)(this.rowData.get(row))).get(column);
 }
 
 //�õ���������
 public String getColumnName(int column) {
  // TODO Auto-generated method stub
  return (String)this.columnNames.get(column);
 }
}