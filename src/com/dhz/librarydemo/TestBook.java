package com.dhz.librarydemo;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestBook extends JFrame implements ActionListener {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	// ����һЩ�ؼ�
	JPanel jp1, jp2;
	JLabel jl1, jl2;
	JButton jb1, jb2, jb3, jb4, jb5;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	BookModel bm;
	// �����������ݿ�ı���
	Statement stat = null;
	PreparedStatement ps;
	Connection ct = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new TestBook();
	}

	public TestBook() {
		this.setTitle("ͼ�����");
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

		// //����ģ�Ͷ���
		bm = new BookModel();
		// ��ʼ��
		jt = new JTable(bm);
		jsp = new JScrollPane(jt);
		jsp.setBounds(26, 166, 754, 337);

		// ��jp1,jp2,jsp���뵽j1��
		JPanel j1 = new Testpanel();
		// j1.setOpaque(false);
		j1.setLayout(null);
		j1.setSize(808, 580);
		this.add(j1);
		j1.add(jsp);
		j1.add(jp1);
		j1.add(jp2);

		this.setBounds(300, 100, 818, 590);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		setResizable(false);
	}

	public void actionPerformed(ActionEvent a) {
		// �ж����ĸ���ť�����
		if (a.getSource() == jb5) {
			new Admin_Main();
			setVisible(false);
		}
		if (a.getSource() == jb1) {
			// System.out.println("�û�ϣ������ѯ...");
			// �ѶԱ�����ݷ�װ��BookModel��
			String keyword = this.jtf.getText().toString();
			if (keyword == null || keyword.equals("")) {
				// ��ѯ������Ϣ
				bm = new BookModel();
				jt.setModel(bm);
			} else {
				String sql = "select * from books_info where book_name like '%" + keyword + "%' or book_id like'%"
						+ keyword + "%' or author like'%" + keyword + "%' or book_press like'%" + keyword + "%'  ";
				System.out.println("��ѯ��sql�����:" + sql);
				bm = new BookModel(sql);
				jt.setModel(bm);
			}

		} else if (a.getSource() == jb2) {
			// System.out.println("���...");
			new BookAdd(this, "����鼮", true);
			// �����ٻ���µ�����ģ��,
			bm = new BookModel();
			jt.setModel(bm);
		}
		// ɾ����¼
		else if (a.getSource() == jb4) {
			// 1.�õ�ͼ���ID
			int rowNum = this.jt.getSelectedRow();// getSelectedRow�᷵�ظ��û����е���
			// ������û�һ�ж�û��ѡ���ͷ���-1
			if (rowNum == -1) {
				// ��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			// �õ��鼮���
			String book_id = (String) bm.getValueAt(rowNum, 1);
			// System.out.println("Id�� "+book_id);
			try {

				ct = BaseDao.getConn();
				System.out.println("���ӳɹ�");
				ps = ct.prepareStatement("delete from books_info where book_id = ?");
				ps.setString(1, book_id);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
			} catch (Exception e) {
				// e.printStackTrace();
				JOptionPane.showMessageDialog(null, "�����ڽ����в���ɾ����");
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
			bm = new BookModel();
			// ����jtable
			jt.setModel(bm);
		}
		// �޸Ĳ���
		else if (a.getSource() == jb3) {
			// System.out.println("11111");
			// ����ѡ��һ�й���ԱҪ�޸ĵ�
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// ��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return;
			}
			// System.out.println( "12435");
			new BookUpdate(this, "�޸�ͼ����Ϣ", true, bm, rowNum);
			bm = new BookModel();
			jt.setModel(bm);
		}
	}
}