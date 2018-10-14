package com.dhz.librarydemo;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReadRegisterinfo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReadRegisterinfo(final String userid){
		setTitle("用户注册信息表");
   	    final JPanel panel=new Registerpanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(300,200, panel.getWidth(), panel.getHeight());
		
		JLabel label = new JLabel("个人信息");
		label.setFont(new Font("宋体", Font.PLAIN, 30));
		label.setBounds(170, 24, 147, 44);
		panel.add(label);
		
		JLabel label_1 = new JLabel("学号：");
		label_1.setBounds(115, 93, 52, 28);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("姓名：");
		label_2.setBounds(115, 134, 54, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("系部：");
		label_3.setBounds(115, 169, 54, 15);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("班级:");
		label_4.setBounds(115, 205, 54, 15);
		panel.add(label_4);
		
		
		JLabel label_5 = new JLabel("密码：");
		label_5.setBounds(115, 245, 54, 15);
		panel.add(label_5);
		
		Connection conn=BaseDao.getConn();
		String sql="select userid,username,department,uclass,password from user_info where userid=?";//+userName;
		System.out.println(sql);
        try {
			PreparedStatement pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs=pstmt.executeQuery();	
			while(rs.next()){
				
				String user_id=rs.getString(1);
				String username=rs.getString(2);
				String department=rs.getString(3);
				String uclass=rs.getString(4);
				String password=rs.getString(5);
				
//				System.out.println("xingming"+username+"id"+userid+"pwd"+password);
				
				JLabel j1 = new JLabel();
				j1.setText(user_id);
				j1.setBounds(222, 95, 120, 21);
				panel.add(j1);
				
				JLabel j2 = new JLabel();
				j2.setText(username);
				j2.setBounds(222, 128, 120, 21);
				panel.add(j2);
				
				JLabel j3 = new JLabel();
				j3.setText(department);
				j3.setBounds(222, 165, 120, 21);
				panel.add(j3);
				
				JLabel j4 = new JLabel();
				j4.setText(uclass);
				j4.setBounds(222, 200, 120, 21);
				panel.add(j4);
				
				JLabel j5 = new JLabel();
				j5.setText(password);
				j5.setBounds(222, 240, 120, 21);
				panel.add(j5);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*JButton button_2= new JButton("返回");
		button_2.setBounds(180, 298, 66, 23);
		panel.add(button_2); 
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new Reader_Main(userName);
				setVisible(false);
			}
		});
		*/
		add(panel);
		setSize(460, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}
//	public static void main(String[] args){
//		new ReadRegisterinfo(userName);
//	}
}
