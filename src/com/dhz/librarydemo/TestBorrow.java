package com.dhz.librarydemo;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestBorrow extends JFrame implements ActionListener {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	JPanel jp1, jp2;
	JLabel jl1, jl2;
	JButton jb1, jb2, jb3, jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf1;
	BorrowModel bm;
	Statement stat = null;
	PreparedStatement ps;
	Connection ct = null;
	ResultSet rs = null;
	private JPanel jp3;
	private JTextField jtf2;

	public static void main(String[] args) {
		new TestBorrow();
	}

	public TestBorrow() {
		setTitle("���Ĺ���");
		jp1 = new JPanel();
		jtf1 = new JTextField(10);
		jb1 = new JButton("��ѯ");

		jb1.addActionListener(this);
		jl1 = new JLabel("\u7528\u6237\u67E5\u8BE2");
		jp1.add(jl1);
		jp1.add(jtf1);
		jp1.add(jb1);

		jp1.setBounds(460, 119, 320, 35);
		jp1.setOpaque(false);

		jb2 = new JButton("����");
		jb2.addActionListener(this);
		jb3 = new JButton("����");
		jb3.addActionListener(this);
		jb4 = new JButton("ɾ��������Ϣ");
		jb4.addActionListener(this);
		jp2 = new JPanel();
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		jp2.setBounds(250, 507, 300, 35);
		jp2.setOpaque(false);

		bm = new BorrowModel();
		// ��ʼ��
		jt = new JTable(bm);
		jsp = new JScrollPane(jt);
		jsp.setBounds(26, 166, 754, 337);

		// ��jp1,jp2,jsp���뵽j1��
		JPanel j1 = new Testpanel();
		j1.setLayout(null);
		j1.setSize(808, 580);
		getContentPane().add(j1);
		j1.add(jsp);
		j1.add(jp1);
		j1.add(jp2);

		jp3 = new JPanel();
		jp3.setBounds(26, 119, 320, 35);
		jp3.setOpaque(false);
		j1.add(jp3);

		JLabel label = new JLabel("\u53E4\u7C4D\u67E5\u8BE2");
		jp3.add(label);

		jtf2 = new JTextField();
		jp3.add(jtf2);
		jtf2.setColumns(10);

		JButton jb5 = new JButton("\u67E5\u8BE2");
		jb5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("��ѯ�û�������Ϣ");
				String keyword = jtf2.getText();
				System.out.println("����Ĳ�ѯ�ؼ����ǣ�" + keyword);
				if (keyword == null || keyword.equals("")) {
					// ��ѯ������Ϣ
					bm = new BorrowModel();
					jt.setModel(bm);
				} else {
					String sql = "select b.id,b.book_id,b.username,u.department,u.uclass,b.bookname,b.borrowdate,b.yreturndate,b.sreturndate,b.fakuan"
							+ " from borrow_info b,user_info u " + " where b.user_id=u.userid and b.bookname like '%"
							+ keyword + "%'";
					System.out.println("��ѯ��sql�����:" + sql);
					bm = new BorrowModel(sql);
					jt.setModel(bm);
				}
			}
		});
		jp3.add(jb5);

		this.setBounds(300, 100, 818, 590);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent a) {
		if (a.getSource() == jb1) {
			System.out.println("��ѯ�û�������Ϣ");
			String keyword = this.jtf1.getText().trim();
			System.out.println("����Ĳ�ѯ�ؼ����ǣ�" + keyword);
			if (keyword == null || keyword.equals("")) {
				// ��ѯ������Ϣ
				bm = new BorrowModel();
				jt.setModel(bm);
			} else {
				String sql = "select b.id,b.book_id,b.username,u.department,u.uclass,b.bookname,b.borrowdate,b.yreturndate,b.sreturndate,b.fakuan"
						+ " from borrow_info b,user_info u " + " where b.user_id=u.userid and b.username like '%"
						+ keyword + "%'";
				System.out.println("��ѯ��sql�����:" + sql);
				bm = new BorrowModel(sql);
				jt.setModel(bm);
			}
		}

		else if (a.getSource() == jb2) {
			System.out.println("����...");
			BorrowAdd sa = new BorrowAdd(this, "��ӽ�����Ϣ", true);

			bm = new BorrowModel();
			jt.setModel(bm);
		} else if (a.getSource() == jb4) {
			int rowNum = this.jt.getSelectedRow();// getSelectedRow�᷵�ظ��û����е���
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}

			String id = (String) bm.getValueAt(rowNum, 0);
			System.out.println("ɾ���ı���ǣ� " + id);

			try {
				ct = BaseDao.getConn();
				System.out.println("���ӳɹ�");
				ps = ct.prepareStatement("delete from borrow_info where id = ?");
				ps.setString(1, id);
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
			bm = new BorrowModel();
			// ����jtable
			jt.setModel(bm);
		} else if (a.getSource() == jb3) {
			System.out.println("11111");
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			System.out.println("12435");
			BorrowUpdate su = new BorrowUpdate(this, "������Ϣ", true, bm, rowNum);
			bm = new BorrowModel();
			jt.setModel(bm);
		}
	}
}