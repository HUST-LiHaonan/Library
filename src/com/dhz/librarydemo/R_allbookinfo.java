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
	//����һЩ�ؼ�
	 private String user_id = null; 
     JPanel jp1,jp2;
     JLabel jl1,jl2;
     JButton jb1,jb2,jb3,jb4;
     JTable jt;
     JScrollPane jsp;
     JTextField jtf;
     BookModel bm; 
     //�����������ݿ�ı���
     Statement stat = null;
     PreparedStatement ps;
     Connection ct = null;
     ResultSet rs = null;
 
//     public static void main(String[] args){
//     new R_allbookinfo();
//    }

    public R_allbookinfo(String user_id){
    this.setTitle("�û��鿴ȫ��ͼ�����");
    jp1 = new JPanel();
    
    jtf = new JTextField(10);
    jb1 = new JButton("��ѯ");
   
    jb1.addActionListener(this); 
   
    jl1 = new JLabel("������ؼ��֣�");    
    jp1.add(jl1);
    jp1.add(jtf);
    jp1.add(jb1);

    jp1.setBounds(200,120,400,35);
    jp1.setOpaque(false);
    
//    //����ģ�Ͷ���
    bm = new BookModel();
    //��ʼ��
    jt = new JTable(bm);
    jsp = new JScrollPane(jt); 
    jsp.setBounds(26,166,754,337);
    
    //��jp1,jp2,jsp���뵽j1��
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
      //�ж����ĸ���ť�����
     if(a.getSource() == jb1){
     System.out.println("��ѯͼ����Ϣ...");
     //��Ϊ�ѶԱ�����ݷ�װ��BookModel�У����ԱȽϼ򵥵���ɲ�ѯ
     String keyword = this.jtf.getText().toString();

      //����һ������ģ���࣬������
     if(keyword==null||keyword.equals("")){
    	 //��ѯ������Ϣ
    	 bm=new  BookModel();
    	 jt.setModel(bm);
     }else{
    	 String sql = "select * from books_info where book_name like '%"+keyword+"%' or book_id like'%"+keyword+"%' or author like'%"+keyword+"%' or book_press like'%"+keyword+"%'  ";
    	 System.out.println("��ѯ��sql�����:"+sql); 
    	 bm = new BookModel(sql);
    	   jt.setModel(bm);
     }
     
     //����jtable
      jt.setModel(bm);
       }
     if(a.getSource() == jb2){
    	 new Reader_Main(user_id);
    	 setVisible(false);
     }
     }
}
