package com.dhz.librarydemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Reader_Main extends JFrame {	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userid = null;   
	public Reader_Main(final String userid){
		this.userid = userid;
		setTitle("读者主界面");
		final JPanel panel=new Admin_Mainpanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(22, 139, 200, 25);
		menuBar.setOpaque(false);
		panel.add(menuBar);
		JMenu jMenu1=new JMenu("基本信息");
		JMenu jMenu2=new JMenu("借阅信息");
		JMenu jMenu3=new JMenu("\u53E4\u7C4D\u4FE1\u606F");
		
		menuBar.add(jMenu1);
		menuBar.add(jMenu2);
		menuBar.add(jMenu3);
		
		JMenuItem item1=new JMenuItem("个人注册信息");
		jMenu1.add(item1);
		item1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				new ReadRegisterinfo(userid);
			}
		});
		
		JMenuItem item5=new JMenuItem("\u5168\u90E8\u53E4\u7C4D");
		jMenu3.add(item5);
		item5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new R_allbookinfo(userid);
			}
		});
		
		JMenuItem item7=new JMenuItem("借书信息");
		jMenu2.add(item7);
		JMenuItem item8=new JMenuItem("还书信息");
		jMenu2.add(item8);
		item7.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new R_borrowbookinfo(userid);
//				JOptionPane.showMessageDialog(null, "您还没借阅过任何图书！");
			}
		});
		item8.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new R_returnbookinfo(userid);
			}
		});
		
		JButton jbt=new JButton("退出系统");
		jbt.setBounds(690, 137, 90, 25);
		jbt.setContentAreaFilled(false);
		panel.add(jbt);
		jbt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				setVisible(false);
				new Login();
			}
		});
		setSize(815,590);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

}
