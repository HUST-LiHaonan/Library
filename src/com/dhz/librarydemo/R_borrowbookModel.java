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
	// rowData��������ݣ�columnNames�������
	Vector rowData, columnNames;

	// �����������ݿ�ı���
	PreparedStatement pstmt = null;
	Connection ct = null;
	ResultSet rs = null;

	// ��ʼ��
	public void init(String sql, String user_id) {
		if (sql.equals("")) {
			sql = "select  b.id,b.book_id,b.username,b.bookname,u.department,u.uclass,b.borrowdate,b.yreturndate,b.sreturndate,b.fakuan"
					+ " from borrow_info b,user_info u"
					+ " where b.user_id=u.userid AND b.user_id =? and b.sreturndate is null";
		}
		// �м�
		// ��������
		columnNames = new Vector();

		columnNames.add("���");
		columnNames.add("ͼ����");
		columnNames.add("�û���");
		columnNames.add("ͼ����");
		columnNames.add("ϵ��");
		columnNames.add("�༶");
		columnNames.add("��������");
		columnNames.add("Ӧ�黹����");
		columnNames.add("ʵ�ʹ黹����");
		columnNames.add("����");

		// rowData��Ŷ���
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
				// ���뵽rowData��
				rowData.add(hang);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����ѧ������
	public void addBook(String sql) {
		// �����û������sql��䣬����������
	}

	// �ڶ������캯����ͨ�����ݵ�sql������������ģ��
	public R_borrowbookModel(String sql, String user_id) {
		this.init(sql, user_id);
	}

	// ���캯�������ڳ�ʼ���ҵ�����ģ�ͣ���
	public R_borrowbookModel(String user_id) {
		this.init("", user_id);
	}

	// �õ����ж�����
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	// �õ����ж�����
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	// �õ�ĳ��ĳ�е�����
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector) (this.rowData.get(row))).get(column);
	}

	// �õ���������
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}
}