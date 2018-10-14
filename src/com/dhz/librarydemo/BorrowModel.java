package com.dhz.librarydemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class BorrowModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// rowData存放行数据，columnNames存放列名
	Vector rowData, columnNames;

	// 定义连接数据库的变量
	Statement stat = null;
	Connection ct = null;
	ResultSet rs = null;

	// 初始化
	public void init(String sql) {
		if (sql.equals("")) {
			sql = "select b.id,b.book_id,b.username,u.department,u.uclass,b.bookname,b.borrowdate,b.yreturndate,b.sreturndate,b.fakuan"
					+ " from borrow_info b,user_info u " + " where b.user_id=u.userid";
		}
		// 中间
		// 设置列名
		columnNames = new Vector();
		columnNames.add("序号");
		columnNames.add("书号");
		columnNames.add("用户名");
		columnNames.add("系部");
		columnNames.add("班级");
		columnNames.add("书名");
		columnNames.add("借阅日期");
		columnNames.add("应归还日期");
		columnNames.add("实际归还日期");
		columnNames.add("罚款");

		// rowData存放多行
		rowData = new Vector();

		try {

			ct = BaseDao.getConn();
			stat = ct.createStatement();// 创建stat对象
			rs = stat.executeQuery(sql);// 查询结果
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
				// 加入到rowData中
				rowData.add(hang);

			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public void addBorrow(String sql) {
		// 根据用户输入的sql语句，完成添加任务

	}

	// 第二个构造函数，通过传递的sql语句来获得数据模型
	public BorrowModel(String sql) {
		this.init(sql);
	}

	// 构造函数，用于初始化我的数据模型（表）
	public BorrowModel() {
		this.init("");
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