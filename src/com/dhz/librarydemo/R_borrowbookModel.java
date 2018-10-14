package com.dhz.librarydemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.table.*;

public class R_borrowbookModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// rowData存放行数据，columnNames存放列名
	Vector rowData, columnNames;

	// 定义连接数据库的变量
	PreparedStatement pstmt = null;
	Connection ct = null;
	ResultSet rs = null;

	// 初始化
	public void init(String sql, String user_id) {
		if (sql.equals("")) {
			sql = "select  b.id,b.book_id,b.username,b.bookname,u.department,u.uclass,b.borrowdate,b.yreturndate,b.sreturndate,b.fakuan"
					+ " from borrow_info b,user_info u"
					+ " where b.user_id=u.userid AND b.user_id =? and b.sreturndate is null";
		}
		// 中间
		// 设置列名
		columnNames = new Vector();

		columnNames.add("序号");
		columnNames.add("图书编号");
		columnNames.add("用户名");
		columnNames.add("图书名");
		columnNames.add("系部");
		columnNames.add("班级");
		columnNames.add("借书日期");
		columnNames.add("应归还日期");
		columnNames.add("实际归还日期");
		columnNames.add("罚款");

		// rowData存放多行
		rowData = new Vector();

		try {
			ct = BaseDao.getConn();
			pstmt = ct.prepareStatement(sql);
			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Vector hang = new Vector();
				hang.add(rs.getString(1));
				hang.add(rs.getString(2));
				hang.add(rs.getString(3));
				hang.add(rs.getString(4));
				hang.add(rs.getString(5));
				hang.add(rs.getString(6));
				hang.add(rs.getString(7));
				hang.add(rs.getString(8));
				hang.add(rs.getString(9));
				hang.add(rs.getString(10));
				System.out.println("xingming" + rs.getString(1));
				// 加入到rowData中
				rowData.add(hang);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 增加学生函数
	public void addBook(String sql) {
		// 根据用户输入的sql语句，完成添加任务
	}

	// 第二个构造函数，通过传递的sql语句来获得数据模型
	public R_borrowbookModel(String sql, String user_id) {
		this.init(sql, user_id);
	}

	// 构造函数，用于初始化我的数据模型（表）
	public R_borrowbookModel(String user_id) {
		this.init("", user_id);
	}

	// 得到共有多少行
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	// 得到共有多少列
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	// 得到某行某列的数据
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector) (this.rowData.get(row))).get(column);
	}

	// 得到属性名字
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}
}