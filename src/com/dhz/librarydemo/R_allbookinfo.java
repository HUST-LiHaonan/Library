package com.dhz.librarydemo;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class R_allbookinfo extends JFrame implements ActionListener {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//定义一些控件
	 private String user_id = null; 
     JPanel jp1,jp2;
     JLabel jl1,jl2;
     JButton jb1,jb2,jb3,jb4;
     JTable jt;
     JScrollPane jsp;
     JTextField jtf;
     BookModel bm; 
     //定义连接数据库的变量
     Statement stat = null;
     PreparedStatement ps;
     Connection ct = null;
     ResultSet rs = null;
 
//     public static void main(String[] args){
//     new R_allbookinfo();
//    }

    public R_allbookinfo(String user_id){
    this.setTitle("用户查看全部图书界面");
    jp1 = new JPanel();
    
    jtf = new JTextField(10);
    jb1 = new JButton("查询");
   
    jb1.addActionListener(this); 
   
    jl1 = new JLabel("请输入关键字：");    
    jp1.add(jl1);
    jp1.add(jtf);
    jp1.add(jb1);

    jp1.setBounds(200,120,400,35);
    jp1.setOpaque(false);
    
//    //创建模型对象
    bm = new BookModel();
    //初始化
    jt = new JTable(bm);
    jsp = new JScrollPane(jt); 
    jsp.setBounds(26,166,754,337);
    
    //将jp1,jp2,jsp放入到j1中
    JPanel j1=new Testpanel();
    j1.setLayout(null);
    j1.setSize(808,580);
    this.add(j1);
    j1.add(jsp);
    j1.add(jp1);
  
    this.setBounds(300, 100, 808, 580); 
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
    this.setResizable(false);
 }
     public void actionPerformed(ActionEvent a) {
      //判断是哪个按钮被点击
     if(a.getSource() == jb1){
     System.out.println("查询图书信息...");
     //因为把对表的数据封装到BookModel中，可以比较简单的完成查询
     String keyword = this.jtf.getText().toString();

      //构建一个数据模型类，并更新
     if(keyword==null||keyword.equals("")){
    	 //查询所有信息
    	 bm=new  BookModel();
    	 jt.setModel(bm);
     }else{
    	 String sql = "select * from books_info where book_name like '%"+keyword+"%' or book_id like'%"+keyword+"%' or author like'%"+keyword+"%' or book_press like'%"+keyword+"%'  ";
    	 System.out.println("查询的sql语句是:"+sql); 
    	 bm = new BookModel(sql);
    	   jt.setModel(bm);
     }
     
     //更新jtable
      jt.setModel(bm);
       }
     if(a.getSource() == jb2){
    	 new Reader_Main(user_id);
    	 setVisible(false);
     }
     }
}
