package com.dhz.librarydemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Login extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid;
	private JLabel u_jlable;
	private JTextField u_jtf;

	private JLabel pwd_jlable;
	private JPasswordField pwd_jtf;

	private JRadioButton guanli;
	private JRadioButton duzhe;

	private JButton sbt_jbt;
	private JButton re_jbt;

	public Login() {
		setTitle("古籍管理系统");
		final JPanel panel = new Loginpanel();
		panel.setLayout(null);
		getContentPane().add(panel);

		u_jlable = new JLabel("用户名：");
		u_jlable.setBounds(200, 170, 190, 25);
		panel.add(u_jlable);

		u_jtf = new JTextField(); // 用户文本框
		u_jtf.setBounds(250, 170, 140, 22);
		panel.add(u_jtf);
		u_jtf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sbt_jbt.doClick();
				}
			}
		});

		pwd_jlable = new JLabel("密码：");
		pwd_jlable.setBounds(200, 205, 200, 25);
		panel.add(pwd_jlable);

		pwd_jtf = new JPasswordField();// 密码框
		pwd_jtf.setBounds(250, 205, 140, 22);
		panel.add(pwd_jtf);
		pwd_jtf.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					sbt_jbt.doClick();
				}
			}
		});

		guanli = new JRadioButton("管理员");
		guanli.setSelected(true);
		guanli.setContentAreaFilled(false);
		guanli.setBounds(205, 240, 72, 23);
		panel.add(guanli);

		duzhe = new JRadioButton("读者");
		duzhe.setContentAreaFilled(false);
		duzhe.setBounds(305, 240, 72, 23);
		panel.add(duzhe);

		ButtonGroup bg = new ButtonGroup();
		bg.add(duzhe);
		bg.add(guanli);

		sbt_jbt = new JButton("登录");
		sbt_jbt.setBounds(205, 280, 70, 25);
		panel.add(sbt_jbt);

		sbt_jbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if ((JButton) e.getSource() == sbt_jbt) {
					String uname = u_jtf.getText().toString();
					@SuppressWarnings("deprecation")
					String pwd = pwd_jtf.getText().toString();
					try {
						Connection conn = BaseDao.getConn();
						if (guanli.isSelected()) {
							String sql = "select * from admin where adminName=?";
							PreparedStatement stm = conn.prepareStatement(sql);
							stm.setString(1, uname);
							ResultSet res = stm.executeQuery();
							if (res.next()) {
								if (uname.equals(res.getString("adminName"))) {
									if (pwd.equals(res.getString("password"))) {
										setVisible(false);
										new Admin_Main();
									} else {
										JOptionPane.showMessageDialog(null, "您输入的密码有误，请重新输入");
									}
								} else {
									JOptionPane.showMessageDialog(null, "您输入的账号有误，请重新输入");
								}

							} else {
								JOptionPane.showMessageDialog(null, "您输入的账号有误，请重新输入");
							}
						}
						if (duzhe.isSelected()) {
							String sql1 = "select * from user_info where userid=?";
							PreparedStatement stmt = conn.prepareStatement(sql1);
							stmt.setString(1, uname);
							ResultSet rs = stmt.executeQuery();
							if (rs.next()) {
								if (uname.equals(rs.getString("userid"))) {
									if (pwd.equals(rs.getString("password"))) {
										setVisible(false);
										userid = u_jtf.getText();
										new Reader_Main(userid);
									} else {
										JOptionPane.showMessageDialog(null, "您输入的密码有误，请重新输入");
									}
								} else {
									JOptionPane.showMessageDialog(null, "您输入的账号有误，请重新输入");
								}
							} else {
								JOptionPane.showMessageDialog(null, "您输入的账号有误，请重新输入");
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			}
		});
		re_jbt = new JButton("注册");
		re_jbt.setBounds(305, 280, 70, 25);
		panel.add(re_jbt);
		re_jbt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Register();
				setVisible(false);
			}
		});

		getContentPane().add(panel);

		JLabel label = new JLabel("\u8BFB\u8005\u4F7F\u7528\u5B66\u53F7\u767B\u5F55");
		label.setBounds(196, 306, 129, 19);
		panel.add(label);
		setSize(463, 370);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}
}
