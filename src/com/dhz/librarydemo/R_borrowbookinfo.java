package com.dhz.librarydemo;

import javax.swing.*;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class R_borrowbookinfo extends JFrame  {
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//����һЩ�ؼ�
     JPanel jp1,jp2;
     JTable jt;
     JScrollPane jsp;
     R_borrowbookModel bm; 

     //�����������ݿ�ı���
     Connection ct = null;
     ResultSet rs = null;
     PreparedStatement pstmt;
 
     public static void main(String[] args){
//     new R_returnbookinfo(userName);
    }

    public R_borrowbookinfo(final String user_id){
    this.setTitle("�û�������Ϣ��");
    jp1 = new JPanel();
   
//    //����ģ�Ͷ���
    bm = new R_borrowbookModel("",user_id);
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
}
