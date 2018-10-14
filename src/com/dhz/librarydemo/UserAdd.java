package com.dhz.librarydemo;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

public class UserAdd extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 定义我需要的swing组件
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JTextField jf1, jf2, jf3, jf4, jf5, jf6;
	JPanel jp1, jp2, jp3;
	JButton jb1, jb2;

	// owner代笔父窗口,title是窗口的名字,modal指定是模式窗口()或者非模式窗口
	public UserAdd(Frame owner, String title, boolean modal) {
		// 调用父类方法
		super(owner, title, modal);

		// jl1 = new JLabel("序号");
		jl2 = new JLabel("用户编号");
		jl3 = new JLabel("\u59D3\u540D");
		jl4 = new JLabel("系部");
		jl5 = new JLabel("班级");
		jl6 = new JLabel("用户密码");

		// jf1 = new JTextField(10);
		jf2 = new JTextField(10);
		jf3 = new JTextField(10);
		jf4 = new JTextField(10);
		jf5 = new JTextField(10);
		jf6 = new JTextField(10);

		jb1 = new JButton("添加");
		jb1.addActionListener(this);
		jb2 = new JButton("取消");
		jb2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();

		// 设置布局
		jp1.setLayout(new GridLayout(6, 1));
		jp2.setLayout(new GridLayout(6, 1));

		jp3.add(jb1);
		jp3.add(jb2);

		// jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		;
		jp1.add(jl5);
		;
		jp1.add(jl6);

		// jp2.add(jf1);
		jp2.add(jf2);
		jp2.add(jf3);
		jp2.add(jf4);
		jp2.add(jf5);
		jp2.add(jf6);

		getContentPane().add(jp1, BorderLayout.WEST);
		getContentPane().add(jp2, BorderLayout.CENTER);
		getContentPane().add(jp3, BorderLayout.SOUTH);

		this.setBounds(550, 300, 420, 250);
		// this.setSize(300,200);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {
			Connection ct = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {
				ct = BaseDao.getConn();

				// 与编译语句对象

				String strsql = "insert into user_info(userid,username,department,uclass,password) values(?,?,?,?,?)";
				pstmt = ct.prepareStatement(strsql);

				// 给对象赋值
				pstmt.setString(1, jf2.getText());
				pstmt.setString(2, jf3.getText());
				pstmt.setString(3, jf4.getText());
				pstmt.setString(4, jf5.getText());
				pstmt.setString(5, jf6.getText());

				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "添加成功");
				this.dispose();

			} catch (Exception arg1) {
				arg1.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;
					}
					if (pstmt != null) {
						pstmt.close();
						pstmt = null;
					}
					if (ct != null) {
						ct.close();
						ct = null;
					}
				} catch (Exception arg2) {
					arg2.printStackTrace();
				}
			}

		}

	}

}