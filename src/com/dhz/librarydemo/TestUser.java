package com.dhz.librarydemo;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestUser extends JFrame implements ActionListener {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	JPanel jp1, jp2;
	JLabel jl1, jl2;
	JButton jb1, jb2, jb3, jb4, jb5;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	UserModel um;
	Statement stat = null;
	PreparedStatement ps;
	Connection ct = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new TestUser();
	}

	public TestUser() {
		this.setTitle("用户管理");

		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("查询");

		jb1.addActionListener(this);
		jl1 = new JLabel("请输入关键字：");
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jp1.setBounds(200, 120, 400, 35);
		jp1.setOpaque(false);

		jb2 = new JButton("添加");
		jb2.addActionListener(this);
		jb3 = new JButton("修改");
		jb3.addActionListener(this);
		jb4 = new JButton("删除");
		jb4.addActionListener(this);
		jp2 = new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.setBounds(250, 507, 300, 35);
		jp2.setOpaque(false);
		um = new UserModel();
		jt = new JTable(um);
		jsp = new JScrollPane(jt);
		jsp.setBounds(26, 166, 754, 337);

		// 将jp1,jp2,jsp放入到j1中
		JPanel j1 = new Testpanel();
		j1.setLayout(null);
		j1.setSize(808, 580);
		this.add(j1);
		j1.add(jsp);
		j1.add(jp1);
		j1.add(jp2);
		// this.add(jsp);
		// this.add(jp1,"North");
		// this.add(jp2,"South");

		// this.setSize(600, 400);
		this.setBounds(300, 100, 818, 590);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == jb5) {
			new Admin_Main();
			setVisible(false);
		}
		if (a.getSource() == jb1) {
			System.out.println("查询用户信息...");
			String keyword = this.jtf.getText().toString();
			if (keyword == null || keyword.equals("")) {
				// 查询所有信息
				um = new UserModel();
				jt.setModel(um);
			} else {
				String sql = "select * from user_info where username like '%" + keyword + "%' or userid like'%"
						+ keyword + "%' ";
				System.out.println("查询的sql语句是:" + sql);
				um = new UserModel(sql);
				jt.setModel(um);
			}

		}

		else if (a.getSource() == jb2) {
			System.out.println("添加...");
			UserAdd sa = new UserAdd(this, "添加用户", true);

			um = new UserModel();
			jt.setModel(um);
		} else if (a.getSource() == jb4) {
			int rowNum = this.jt.getSelectedRow();// getSelectedRow会返回给用户点中的行
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选中一行");
				return;
			}
			String userid = (String) um.getValueAt(rowNum, 1);
			System.out.println("Id： " + userid);
			try {
				ct = BaseDao.getConn();
				System.out.println("连接成功");
				ps = ct.prepareStatement("delete from user_info where userid = ?");
				ps.setString(1, userid);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "删除成功");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null) {
						rs.close();
						rs = null;

					}
					if (ps != null) {
						ps.close();
						ps = null;
					}
					if (ct != null) {
						ct.close();
						ct = null;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			um = new UserModel();
			jt.setModel(um);
		} else if (a.getSource() == jb3) {
			System.out.println("11111");
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			System.out.println("12435");
			UserUpdate su = new UserUpdate(this, "修改用户信息", true, um, rowNum);
			um = new UserModel();
			jt.setModel(um);
		}
	}
}