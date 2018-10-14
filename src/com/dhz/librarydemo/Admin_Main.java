package com.dhz.librarydemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Admin_Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Admin_Main() {
		setTitle("����Ա������");
		final JPanel panel = new Admin_Mainpanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(300, 200, panel.getWidth(), panel.getHeight());

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(22, 139, 200, 25);
		panel.add(menuBar);
		menuBar.setOpaque(false);

		JMenu jMenu1 = new JMenu("\u53E4\u7C4D\u4FE1\u606F");
		JMenuItem item1 = new JMenuItem("\u53E4\u7C4D\u7BA1\u7406");
		item1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new TestBook();
			}
		});
		jMenu1.add(item1);

		JMenu jMenu2 = new JMenu("�û���Ϣ");
		JMenuItem item5 = new JMenuItem("�û�����");
		item5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new TestUser();
			}
		});
		JMenu jMenu3 = new JMenu("������Ϣ");

		menuBar.add(jMenu1);
		menuBar.add(jMenu2);
		menuBar.add(jMenu3);
		panel.add(menuBar);

		jMenu2.add(item5);
		JMenuItem item7 = new JMenuItem("���Ĺ���");
		jMenu3.add(item7);
		item7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new TestBorrow();
			}
		});

		JButton jbutton1 = new JButton("�˳�ϵͳ");
		jbutton1.setContentAreaFilled(false);
		jbutton1.setBounds(690, 137, 90, 25);
		panel.add(jbutton1);
		jbutton1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				setVisible(false);
				new Login();
			}
		});

		setSize(815, 590);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Admin_Main();
	}
}
