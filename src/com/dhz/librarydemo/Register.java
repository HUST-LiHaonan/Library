package com.dhz.librarydemo;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTextField textField, textField_1, textField_3, textField_4;
	private static JPasswordField textField_2;

	public Register() {
		setTitle("用户注册信息表");
		final JPanel panel = new Registerpanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(300, 200, panel.getWidth(), panel.getHeight());

		JLabel label = new JLabel("个人信息");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(170, 24, 147, 44);
		panel.add(label);

		JLabel label_1 = new JLabel("学号：");
		label_1.setBounds(115, 93, 52, 28);
		panel.add(label_1);

		textField = new JTextField();
		textField.setBounds(177, 95, 120, 21);
		panel.add(textField);
		textField.setColumns(10);

		JLabel label_2 = new JLabel("姓名：");
		label_2.setBounds(115, 134, 54, 15);
		panel.add(label_2);

		textField_1 = new JTextField();
		textField_1.setBounds(177, 130, 120, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_3 = new JLabel("系部：");
		label_3.setBounds(115, 169, 54, 15);
		panel.add(label_3);

		textField_3 = new JTextField();
		textField_3.setBounds(177, 165, 120, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);

		JLabel label_4 = new JLabel("班级:");
		label_4.setBounds(115, 205, 54, 15);
		panel.add(label_4);

		textField_4 = new JTextField();
		textField_4.setBounds(177, 200, 120, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JLabel label_5 = new JLabel("密码：");
		label_5.setBounds(115, 245, 54, 15);
		panel.add(label_5);

		textField_2 = new JPasswordField();
		textField_2.setBounds(177, 240, 120, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JButton button = new JButton("注册");
		button.setBounds(110, 298, 66, 23);
		panel.add(button);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String userid = textField.getText();
				String username = textField_1.getText().toString();
				String department = textField_3.getText().toString();
				String uclass = textField_4.getText().toString();
				@SuppressWarnings("deprecation")
				String password = textField_2.getText().toString();
				Connection conn = BaseDao.getConn();
				String sql = "insert into user_info(userid,username,department,uclass,password) values(?,?,?,?,?)";
				try {
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userid);
					pstmt.setString(2, username);
					pstmt.setString(3, department);
					pstmt.setString(4, uclass);
					pstmt.setString(5, password);
					pstmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "注册成功");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}
			}
		});

		JButton button_1 = new JButton("重置");
		button_1.setBounds(200, 298, 66, 23);
		panel.add(button_1);
		button_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}
		});

		JButton button_2 = new JButton("返回");
		button_2.setBounds(290, 298, 66, 23);
		panel.add(button_2);
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Login();
				setVisible(false);
			}
		});

		add(panel);
		setSize(460, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Register();
	}
}
