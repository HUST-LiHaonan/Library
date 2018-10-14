package com.dhz.librarydemo;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.*;

public class BookUpdate extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ��������Ҫ��swing���
	JLabel jl, jl1, jl2, jl3, jl4, jl5, jl6;
	JTextField jf, jf1, jf2, jf3, jf4, jf5, jf6;
	JPanel jp1, jp2, jp3;
	JButton jb1, jb2;

	// owner���ʸ�����,title�Ǵ��ڵ�����,modalָ����ģʽ����()���߷�ģʽ����
	public BookUpdate(Frame owner, String title, boolean modal, BookModel sm, int rowNum) {
		// ���ø��෽��
		super(owner, title, modal);
		// jl=new JLabel("���");
		jl1 = new JLabel("���");
		jl2 = new JLabel("����");
		jl3 = new JLabel("\u671D\u4EE3");
		jl4 = new JLabel("����");
		jl5 = new JLabel("\u7248\u672C");
		jl6 = new JLabel("ͼ������");

		// jf = new JTextField(10);jf.setText((sm.getValueAt(rowNum, 0)).toString());
		jf1 = new JTextField(10);
		jf1.setText((sm.getValueAt(rowNum, 1)).toString());
		jf2 = new JTextField(10);
		jf2.setText((String) sm.getValueAt(rowNum, 2));
		jf3 = new JTextField(10);
		jf3.setText(sm.getValueAt(rowNum, 3).toString());
		jf4 = new JTextField(10);
		jf4.setText((sm.getValueAt(rowNum, 4)).toString());
		jf5 = new JTextField(10);
		jf5.setText((String) sm.getValueAt(rowNum, 5));
		jf6 = new JTextField(10);
		jf6.setText((String) sm.getValueAt(rowNum, 6));

		jb1 = new JButton("�޸�");
		jb1.addActionListener(this);
		jb2 = new JButton("ȡ��");
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

		// ���ò���
		jp1.setLayout(new GridLayout(7, 1));
		jp2.setLayout(new GridLayout(7, 1));

		jp3.add(jb1);
		jp3.add(jb2);

		// jp1.add(jl);
		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);

		// jp2.add(jf);
		jp2.add(jf1);
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
				// �����������

				String strsql = "update books_info set book_id=?,book_press=?,"
						+ "author=?,book_price=?,book_num=? where book_name=?";
				pstmt = ct.prepareStatement(strsql);

				// ������ֵ
				pstmt.setString(1, jf1.getText());
				pstmt.setString(2, jf3.getText());
				pstmt.setString(3, jf4.getText());
				pstmt.setString(4, jf5.getText());
				pstmt.setString(5, jf6.getText());
				pstmt.setString(6, jf2.getText());

				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
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