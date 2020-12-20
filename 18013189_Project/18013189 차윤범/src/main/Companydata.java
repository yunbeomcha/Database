package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JDesktopPane;
import java.awt.Panel;
import javax.swing.JLabel;

public class Companydata extends JFrame{

	private JPanel contentPane;
	 JTextField cpid;
	JButton btnNewButton_1_3;
	JButton btnNewButton_1_3_1;
	JButton btnNewButton_1_3_2;
	JButton quit;
	 JTextField cpname;
	 JTextField cpaddress;
	 JTextField cpnumber;
	 JTextField cpmngemail;
	 JTextField cpmngname;
	 JTextArea selectcp = new JTextArea();;
	 JButton btnNewButton_1;
	 private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Companydata frame = new Companydata();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// SQL ����
		static Connection con;
		   Statement stmt,stmt2;
		   ResultSet rs,rs2;
		   String Driver="";
		   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
		   String userid="madang";
		   String pwd="madang";
		   private JLabel lblNewLabel_1;
		   private JLabel lblNewLabel_2;
		   private JLabel lblNewLabel_3;
		   private JLabel lblNewLabel_4;
		   private JLabel lblNewLabel_5;
		   private JLabel lblNewLabel_6;
		   private JLabel lblNewLabel_7;
		   
		   
		public void conDB() { 
		     try {
		       Class.forName("com.mysql.cj.jdbc.Driver");   
		       //System.out.println("����̹� �ε� ����");
		     } catch(ClassNotFoundException e1) {
		         e1.printStackTrace();
		     }
		   try {
		      // System.out.println("�����ͺ��̽� ���� �غ�...");
		       con=DriverManager.getConnection(url, userid, pwd); 
		      // System.out.println("�����ͺ��̽� ���� ����");
		     } catch(SQLException e1) {
		         e1.printStackTrace();
		       }
		   }
		public void printdata() {
			selectcp.setText("ȸ�� ID \t ȸ��� \t �ּ� \t ��ȭ��ȣ \t ������̸��� \t\t ������̸� \n");
			try {
				stmt2 = con.createStatement();
				String query=" select * from campingcar_rent_company;";
				rs = stmt2.executeQuery(query);
	             while(rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
	                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\n";
	                selectcp.append(str);
	             }
			}catch(Exception e1) {
				System.out.println(e1);
			}
		}	
		
	public Companydata() {
		setTitle("18013189 ������ -ȸ������ ����������");
		conDB();
		
		printdata();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		/* ��� ���� */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 652, 287);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(selectcp);
		
		cpid = new JTextField();
		cpid.setColumns(10);
		cpid.setBounds(126, 101, 72, 20);
		panel.add(cpid);
			
		
		JButton btnNewButton = new JButton("�˻�");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectcp.setText("");
					stmt2 = con.createStatement();
					String id = cpid.getText();
					selectcp.setText("ȸ�� ID \t ȸ��� \t �ּ� \t ��ȭ��ȣ \t ������̸��� \t\t ������̸� \n");
					String query2=" select * from campingcar_rent_company where camping_rent_company_id='"+id+"';"; /* SQL �� */
					rs2 = stmt2.executeQuery(query2);
					String str = null;
					if(rs2.next()) {
			            str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
			            +  "\t" + rs2.getString(5)+ "\t" + rs2.getString(6)+"\n";
					}
		            selectcp.append(str);
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(210, 100, 57, 23);
		panel.add(btnNewButton);
		
		cpname = new JTextField();
		cpname.setColumns(10);
		cpname.setBounds(126, 140, 141, 21);
		panel.add(cpname);
		
		cpaddress = new JTextField();
		cpaddress.setColumns(10);
		cpaddress.setBounds(126, 176, 141, 21);
		panel.add(cpaddress);
		
		cpnumber = new JTextField();
		cpnumber.setColumns(10);
		cpnumber.setBounds(126, 212, 141, 21);
		panel.add(cpnumber);
		
		cpmngemail = new JTextField();
		cpmngemail.setColumns(10);
		cpmngemail.setBounds(126, 250, 141, 21);
		panel.add(cpmngemail);
		
		cpmngname = new JTextField();
		cpmngname.setColumns(10);
		cpmngname.setBounds(126, 286, 141, 21);
		panel.add(cpmngname);
		btnNewButton_1_3 = new JButton("����");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a1 = cpname.getText();
				String a2 = cpaddress.getText();
				String a3 = cpnumber.getText();
				String a4 = cpmngname.getText();
				String a5 = cpmngemail.getText();
				String id = cpid.getText();
				try {
					selectcp.setText("");
					stmt = con.createStatement();
					String query="update campingcar_rent_company set cp_name='"+a1+"'"
							+ ",cp_address='"+a2+"',cp_number='"+a3+"'"
							+ ",cp_mng_name='"+a4+"',cp_mng_email='"+a5+"'"
							+ " where camping_rent_company_id='"+id+"'";
				
					int result = stmt.executeUpdate(query);
					if(result==1) {
		            JOptionPane.showMessageDialog(btnNewButton_1_3, "�����Ϸ�"); 
		            //�����ϰ������!
		            printdata();
					}else {
						 JOptionPane.showMessageDialog(btnNewButton_1_3, "�ٽ��Է��ϼ���!"); 
					}
				}catch(Exception e1) {
					if(a1.length()==0||a2.length()==0||a3.length()==0||a4.length()==0||a5.length()==0||id.length()==0) {
						JOptionPane.showMessageDialog(btnNewButton_1_3, "��ĭ�� ���ä���ּ���"); 
						printdata();
					}
					//System.out.println(e1);
				}
			}
		});
		btnNewButton_1_3.setForeground(Color.BLACK);
		btnNewButton_1_3.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton_1_3.setBounds(123, 322, 70, 29);
		panel.add(btnNewButton_1_3);
		
		
		btnNewButton_1_3_1 = new JButton("����");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					stmt = con.createStatement();
					
					String id = cpid.getText();
					String query="DELETE FROM campingcar_rent_company WHERE camping_rent_company_id = '"+id+"'";
					int result = stmt.executeUpdate(query);
					
					if(result == 1) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_1, "���� �Ϸ�");
						printdata();
						cpid.setText("");
					}else {
						JOptionPane.showMessageDialog(btnNewButton_1_3_1, "ID�� �Է����ּ���.");
						//System.out.println("����");
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			 			
			}
		});
		btnNewButton_1_3_1.setForeground(Color.BLACK);
		btnNewButton_1_3_1.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton_1_3_1.setBounds(205, 322, 70, 29);
		panel.add(btnNewButton_1_3_1);
		
		quit = new JButton("�ݱ�");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("����", Font.BOLD, 15));
		quit.setBounds(123, 361, 70, 22);
		panel.add(quit);
		
		btnNewButton_1 = new JButton("���ΰ�ħ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printdata();
			}
		});
		btnNewButton_1.setBounds(16, 18, 96, 20);
		panel.add(btnNewButton_1);
		
		btnNewButton_1_3_2 = new JButton("�Է�");
		btnNewButton_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cpname1 = cpname.getText();
				String cpaddress1 = cpaddress.getText();
				String cpnumber1 = cpnumber.getText();
				String cpmngname1 = cpmngname.getText();
				String cpmngemail1 = cpmngemail.getText();
				try {
					
						stmt = con.createStatement();
						String query="insert into campingcar_rent_company(cp_name,cp_address,cp_number,cp_mng_email,cp_mng_name) values('"+cpname1+"','"+cpaddress1+"','"+cpnumber1+"','"+cpmngemail1+"','"+cpmngname1+"')";
						
						if(cpname1.length()==0||cpaddress1.length()==0||cpnumber1.length()==0||cpmngname1.length()==0||cpmngemail1.length()==0){
							JOptionPane.showMessageDialog(btnNewButton_1_3_2, "ȸ��ID�� ������ ������ ��ĭ�� ���ä���ּ���"); 
							printdata();
						}else {
							int result =  stmt.executeUpdate(query);
							if (result == 1) {
								JOptionPane.showMessageDialog(btnNewButton_1_3_2, "�Է¿Ϸ�!");
								printdata();
							}
						}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton_1_3_2.setForeground(Color.BLACK);
		btnNewButton_1_3_2.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton_1_3_2.setBounds(42, 322, 70, 29);
		panel.add(btnNewButton_1_3_2);
		
		lblNewLabel = new JLabel("\uD68C\uC0ACID \uAC80\uC0C9\uD6C4 \uC218\uC815,\uC0AD\uC81C\uD558\uC2DC\uBA74 \uB354 \uD3B8\uB9AC\uD569\uB2C8\uB2E4.");
		lblNewLabel.setBounds(389, 49, 256, 15);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("ȸ��ID�Է�");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_1.setBounds(16, 100, 120, 20);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("ȸ�� ���� �Է� | ���� | ����");
		lblNewLabel_2.setFont(new Font("����ưưüB", Font.BOLD, 28));
		lblNewLabel_2.setBounds(317, 4, 391, 39);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("ȸ���");
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_3.setBounds(16, 143, 96, 20);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("�ּ�");
		lblNewLabel_4.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_4.setBounds(16, 176, 96, 20);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("��ȭ��ȣ");
		lblNewLabel_5.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_5.setBounds(16, 215, 96, 20);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("������̸���");
		lblNewLabel_6.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_6.setBounds(16, 253, 96, 20);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("������̸�");
		lblNewLabel_7.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_7.setBounds(16, 289, 96, 20);
		panel.add(lblNewLabel_7);
		
		
	}
	

	
}


