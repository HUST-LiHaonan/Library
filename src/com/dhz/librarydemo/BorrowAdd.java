package com.dhz.librarydemo;

import javax.swing.JDialog;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class BorrowAdd extends JDialog implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel jl1, jl2, jl3, jl4, jl5, jl6;
	JTextField jf1, jf2, jf3, jf4, jf5, jf6;
	JPanel jp1, jp2, jp3;
	JButton jb1, jb2;
	JComboBox jcb1, jcb2;
	Connection ct = null;
	PreparedStatement pstmt = null;
	String bookid, userid;
	ResultSet rs = null;

	// owner代笔父窗口,title是窗口的名字,modal指定是模式窗口()或者非模式窗口
	public BorrowAdd(Frame owner, String title, boolean modal) {
		// 调用父类方法
		super(owner, title, modal);

		jl1 = new JLabel("图书编号");
		jl2 = new JLabel("用户编号");
		jl3 = new JLabel("用户名");

		jl5 = new JLabel("图书名");
		jl6 = new JLabel("借阅日期");

		/*
		 * jf1 = new JTextField(10); jf2 = new JTextField(10);
		 */

		ct = BaseDao.getConn();
		String sql1 = "select book_id from books_info";
		String sql2 = "select userid from user_info";
		List<String> rs1 = getIdList(ct, sql1);
		List<String> rs2 = getIdList(ct, sql2);
		Object[] itmes1 = rs1.toArray();
		Object[] itmes2 = rs2.toArray();
		jcb1 = new JComboBox<>(itmes1);
		jcb2 = new JComboBox<>(itmes2);
		jcb1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				bookid = (String) jcb1.getSelectedItem();
				// 根据图书id和用户id查询对应的图书名和用户名
				String booksql = "select book_name from books_info where book_id=?";
				String bookname = getNamebyid(ct, booksql, bookid);
				jf5.setText(bookname);

			}
		});

		jcb2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				userid = (String) jcb2.getSelectedItem();

				String usersql = "select username from  user_info where userid=?";
				String usrename = getNamebyid(ct, usersql, userid);
				jf3.setText(usrename);

			}
		});

		jf3 = new JTextField(10);
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

		jp1.add(jl1);
		jp1.add(jl2);
		jp1.add(jl3);

		jp1.add(jl5);
		jp1.add(jl6);

		jp2.add(jcb1);
		jp2.add(jcb2);
		jp2.add(jf3);
		jp2.add(jf5);
		jp2.add(jf6);

		this.add(jp1, BorderLayout.WEST);
		this.add(jp2, BorderLayout.CENTER);
		this.add(jp3, BorderLayout.SOUTH);

		this.setBounds(550, 300, 420, 250);
		// this.setSize(300,200);
		this.setVisible(true);
	}

	// 该方法实现获得userid和bookid的下拉列表的数据选项
	private java.util.List<String> getIdList(Connection ct, String sql) {
		// TODO Auto-generated method stub
		java.util.List<String> list = new ArrayList<>();
		try {
			pstmt = ct.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				list.add(id);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {

			try {
				ct = BaseDao.getConn();
				// 与编译语句对象
				// 添加读者借阅信息
				String strsql = "insert into borrow_info(book_id,user_id,username,bookname,borrowdate,yreturndate) values(?,?,?,?,?,?)";
				pstmt = ct.prepareStatement(strsql);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(sdf.parse(jf6.getText().toString()));
				calendar.add(Calendar.DAY_OF_MONTH, 30);
				java.util.Date str = calendar.getTime();

				Date date = new Date(str.getTime());
				// System.out.println("日期"+date);

				// 给对象赋值
				pstmt.setString(1, bookid);
				pstmt.setString(2, userid);
				pstmt.setString(3, jf3.getText());
				pstmt.setString(4, jf5.getText());
				pstmt.setString(5, jf6.getText());
				pstmt.setDate(6, date);
				pstmt.executeUpdate();
				JOptionPane.showMessageDialog(null, "添加成功");

				// 更新图书表中的库存数量-1
				// 更新图书库存表，借 书时图书库存-1

				String sql2 = "update books_info set book_num=book_num-1 where book_id=?";
				PreparedStatement pstmt2 = ct.prepareStatement(sql2);
				pstmt2.setString(1, bookid);
				pstmt2.executeUpdate();
				System.out.println("修改库存成功");

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

	private String getNamebyid(Connection ct, String booksql, String id) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			PreparedStatement pstmt = ct.prepareStatement(booksql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}