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
		this.setTitle("�û�����");

		jp1 = new JPanel();
		jtf = new JTextField(10);
		jb1 = new JButton("��ѯ");

		jb1.addActionListener(this);
		jl1 = new JLabel("������ؼ��֣�");
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);

		jp1.setBounds(200, 120, 400, 35);
		jp1.setOpaque(false);

		jb2 = new JButton("���");
		jb2.addActionListener(this);
		jb3 = new JButton("�޸�");
		jb3.addActionListener(this);
		jb4 = new JButton("ɾ��");
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

		// ��jp1,jp2,jsp���뵽j1��
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
			System.out.println("��ѯ�û���Ϣ...");
			String keyword = this.jtf.getText().toString();
			if (keyword == null || keyword.equals("")) {
				// ��ѯ������Ϣ
				um = new UserModel();
				jt.setModel(um);
			} else {
				String sql = "select * from user_info where username like '%" + keyword + "%' or userid like'%"
						+ keyword + "%' ";
				System.out.println("��ѯ��sql�����:" + sql);
				um = new UserModel(sql);
				jt.setModel(um);
			}

		}

		else if (a.getSource() == jb2) {
			System.out.println("���...");
			UserAdd sa = new UserAdd(this, "����û�", true);

			um = new UserModel();
			jt.setModel(um);
		} else if (a.getSource() == jb4) {
			int rowNum = this.jt.getSelectedRow();// getSelectedRow�᷵�ظ��û����е���
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			String userid = (String) um.getValueAt(rowNum, 1);
			System.out.println("Id�� " + userid);
			try {
				ct = BaseDao.getConn();
				System.out.println("���ӳɹ�");
				ps = ct.prepareStatement("delete from user_info where userid = ?");
				ps.setString(1, userid);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
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
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			System.out.println("12435");
			UserUpdate su = new UserUpdate(this, "�޸��û���Ϣ", true, um, rowNum);
			um = new UserModel();
			jt.setModel(um);
		}
	}
}