package com.dhz.librarydemo;

/*
 * �޸�ѧ��
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowUpdate extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ��������Ҫ��swing���
	JLabel jl1, jl2, jl3, jl4, jl5, jl6, jl7, jl8, jl9;
	JTextField jf1, jf2, jf3, jf4, jf5, jf6, jf7, jf8, jf9;
	JPanel jp1, jp2, jp3;
	JButton jb1, jb2;
	private String jf0;

	// owner���ʸ�����,title�Ǵ��ڵ�����,modalָ����ģʽ����()���߷�ģʽ����
	public BorrowUpdate(Frame owner, String title, boolean modal, BorrowModel sm, int rowNum) {
		// ���ø��෽��
		super(owner, title, modal);

		jl1 = new JLabel("ͼ����");
		jl2 = new JLabel("�û���");
		jl3 = new JLabel("ϵ��");
		jl4 = new JLabel("�༶");
		jl5 = new JLabel("ͼ����");
		jl6 = new JLabel("��������");
		jl7 = new JLabel("Ӧ�黹����");
		jl8 = new JLabel("ʵ�ʹ黹����");
		jl9 = new JLabel("����");
		jf0 = sm.getValueAt(rowNum, 0).toString();
		jf1 = new JTextField(10);
		jf1.setText((sm.getValueAt(rowNum, 1)).toString());
		jf1.setEditable(false);
		jf2 = new JTextField(10);
		jf2.setText((String) sm.getValueAt(rowNum, 2));
		jf2.setEditable(false);
		jf3 = new JTextField(10);
		jf3.setText(sm.getValueAt(rowNum, 3).toString());
		jf3.setEditable(false);
		jf4 = new JTextField(10);
		jf4.setText((sm.getValueAt(rowNum, 4)).toString());
		jf4.setEditable(false);
		jf5 = new JTextField(10);
		jf5.setText((String) sm.getValueAt(rowNum, 5));
		jf5.setEditable(false);
		jf6 = new JTextField(10);
		jf6.setText((String) sm.getValueAt(rowNum, 6));
		jf6.setEditable(false);
		jf7 = new JTextField(10);
		jf7.setText((String) sm.getValueAt(rowNum, 7));
		jf7.setEditable(false);

		jf8 = new JTextField(10);
		jf8.setText((String) sm.getValueAt(rowNum, 8));

		System.out.println(jf7.getText() + " " + jf8.getText().toString());
		jf9 = new JTextField(10);
		jf9.setText((String) sm.getValueAt(rowNum, 9));

		jb1 = new JButton("����");
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
		jp1.setLayout(new GridLayout(9, 1));
		jp2.setLayout(new GridLayout(9, 1));

		jp3.add(jb1);
		jp3.add(jb2);

		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);
		jp1.add(jl4);
		jp1.add(jl5);
		jp1.add(jl6);
		jp1.add(jl7);
		jp1.add(jl8);
		jp1.add(jl9);

		jp2.add(jf1);
		jp2.add(jf2);
		jp2.add(jf3);
		jp2.add(jf4);
		jp2.add(jf5);
		jp2.add(jf6);
		jp2.add(jf7);
		jp2.add(jf8);
		jp2.add(jf9);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);
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

				// ��дʵ�ʻ������ںͷ�����
				String strsql = "update borrow_info set sreturndate=?,fakuan=? where book_id=? and id=?";
				pstmt = ct.prepareStatement(strsql);

				// ������ֵ
				String actual_returndate = jf8.getText().toString();
				String actural_borrowdate = jf6.getText().toString();

				// ���ַ���ת��ΪDate����

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date d1 = sdf.parse(actural_borrowdate);
				Date d2 = sdf.parse(actual_returndate);

				System.out.println("�������ڣ�" + d1);
				System.out.println("�������ڣ�" + d2);

				if (d1.compareTo(d2) > 0) {
					JOptionPane.showMessageDialog(null, "��������Ӧ���ڽ�������");
				} else {
					pstmt.setString(1, jf8.getText());
					pstmt.setString(2, jf9.getText());
					pstmt.setString(3, jf1.getText());
					pstmt.setString(4, jf0);

					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "����ɹ�");

					// ����ɹ���ͼ�����Ŀ���1

					// ����ͼ����еĿ������-1
					// ����ͼ������� ��ʱͼ����-1

					String sql2 = "update books_info set book_num=book_num+1 where book_id=?";
					PreparedStatement pstmt2 = ct.prepareStatement(sql2);
					pstmt2.setString(1, jf1.getText());
					pstmt2.executeUpdate();
					System.out.println("�޸Ŀ��ɹ�");
					this.dispose();
				}

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