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
	// 定义一些控件
	JPanel jp1, jp2;
	JLabel jl1, jl2;
	JButton jb1, jb2, jb3, jb4, jb5;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	BookModel bm;
	// 定义连接数据库的变量
	Statement stat = null;
	PreparedStatement ps;
	Connection ct = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		new TestBook();
	}

	public TestBook() {
		this.setTitle("图书管理");
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

		// //创建模型对象
		bm = new BookModel();
		// 初始化
		jt = new JTable(bm);
		jsp = new JScrollPane(jt);
		jsp.setBounds(26, 166, 754, 337);

		// 将jp1,jp2,jsp放入到j1中
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
		// 判断是哪个按钮被点击
		if (a.getSource() == jb5) {
			new Admin_Main();
			setVisible(false);
		}
		if (a.getSource() == jb1) {
			// System.out.println("用户希望被查询...");
			// 把对表的数据封装到BookModel中
			String keyword = this.jtf.getText().toString();
			if (keyword == null || keyword.equals("")) {
				// 查询所有信息
				bm = new BookModel();
				jt.setModel(bm);
			} else {
				String sql = "select * from books_info where book_name like '%" + keyword + "%' or book_id like'%"
						+ keyword + "%' or author like'%" + keyword + "%' or book_press like'%" + keyword + "%'  ";
				System.out.println("查询的sql语句是:" + sql);
				bm = new BookModel(sql);
				jt.setModel(bm);
			}

		} else if (a.getSource() == jb2) {
			// System.out.println("添加...");
			new BookAdd(this, "添加书籍", true);
			// 重新再获得新的数据模型,
			bm = new BookModel();
			jt.setModel(bm);
		}
		// 删除记录
		else if (a.getSource() == jb4) {
			// 1.得到图书的ID
			int rowNum = this.jt.getSelectedRow();// getSelectedRow会返回给用户点中的行
			// 如果该用户一行都没有选，就返回-1
			if (rowNum == -1) {
				// 提示
				JOptionPane.showMessageDialog(this, "请选中一行");
				return;
			}
			// 得到书籍编号
			String book_id = (String) bm.getValueAt(rowNum, 1);
			// System.out.println("Id： "+book_id);
			try {

				ct = BaseDao.getConn();
				System.out.println("连接成功");
				ps = ct.prepareStatement("delete from books_info where book_id = ?");
				ps.setString(1, book_id);
				ps.executeUpdate();
				JOptionPane.showMessageDialog(null, "删除成功");
			} catch (Exception e) {
				// e.printStackTrace();
				JOptionPane.showMessageDialog(null, "该书在借阅中不能删除！");
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
			// 更新jtable
			jt.setModel(bm);
		}
		// 修改操作
		else if (a.getSource() == jb3) {
			// System.out.println("11111");
			// 三、选择一行管理员要修改的
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				// 提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return;
			}
			// System.out.println( "12435");
			new BookUpdate(this, "修改图书信息", true, bm, rowNum);
			bm = new BookModel();
			jt.setModel(bm);
		}
	}
}