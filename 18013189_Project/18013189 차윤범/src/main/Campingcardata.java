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

public class Campingcardata extends JFrame{

	private JPanel contentPane;
	 JTextField cpcid;
	JButton btnNewButton_1_3;
	JButton btnNewButton_1_3_1;
	JButton btnNewButton_1_3_2;
	JButton quit;
	 JTextField cpcname;
	 JTextField cpcnum;
	 JTextField cpcsits;
	 JTextField cpcmanufacture;
	 JTextField cpcyear;
	 JTextArea selectcp = new JTextArea();;
	 JButton btnNewButton_1;
	 private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Campingcardata frame = new Campingcardata();
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
		   private JTextField cpcdistance;
		   private JTextField cpcprice;
		   private JTextField cpid;
		   private JTextField registdate;
		   private JLabel lblNewLabel_1;
		   private JLabel lblNewLabel_2;
		   private JLabel lblNewLabel_3;
		   private JLabel lblNewLabel_4;
		   private JLabel lblNewLabel_5;
		   private JLabel lblNewLabel_6;
		   private JLabel lblNewLabel_7;
		   private JLabel lblNewLabel_8;
		   private JLabel lblNewLabel_9;
		   private JLabel lblNewLabel_10;
		   private JLabel lblNewLabel_11;
		   
		   
		public void conDB() { 
		     try {
		       Class.forName("com.mysql.cj.jdbc.Driver");   
		       //System.out.println("����̹� �ε� ����");
		     } catch(ClassNotFoundException e1) {
		         e1.printStackTrace();
		     }
		   try {
		       //System.out.println("�����ͺ��̽� ���� �غ�...");
		       con=DriverManager.getConnection(url, userid, pwd); 
		      // System.out.println("�����ͺ��̽� ���� ����");
		     } catch(SQLException e1) {
		         e1.printStackTrace();
		       }
		   }
		public void printdata() {
			selectcp.setText("ķ��īID \t ���� \t ������ȣ \t �����ο��� \t ����ȸ�� \t �������� \t ��������Ÿ� \t �뿩��� \tķ��ī������� \t �뿩ȸ��ID \n");
			try {
				stmt2 = con.createStatement();
				String query2=" select * from campingcar_list"; /* SQL �� */
				rs2 = stmt2.executeQuery(query2);
				
				while(rs2.next()) {
					String str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
		            +  "\t" + rs2.getString(5)+ "\t" + rs2.getString(6)+"\t" + rs2.getString(7)+ "\t" +rs2.getString(8)
		            + "\t" +rs2.getString(9)+ "\t" +rs2.getString(10)+"\n";
					selectcp.append(str);
				}
			}catch(Exception e1) {
				System.out.println(e1);
			}
		}	
		
	public Campingcardata() {
		setTitle("18013189 ������ -ķ��ī ��������������");
		conDB();
		
		printdata();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1174, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		/* ��� ���� */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 840, 343);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(selectcp);
		
		cpcid = new JTextField();
		cpcid.setColumns(10);
		cpcid.setBounds(126, 49, 72, 20);
		panel.add(cpcid);
			
		
		JButton btnNewButton = new JButton("�˻�");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectcp.setText("");
					stmt2 = con.createStatement();
					String id = cpcid.getText();
					selectcp.setText("ķ��īID \t ���� \t ������ȣ \t �����ο��� \t ����ȸ�� \t �������� \t ��������Ÿ� \t �뿩��� \tķ��ī������� \t �뿩ȸ��ID \n");
					String query2=" select * from campingcar_list where campingcar_list_id='"+id+"';"; /* SQL �� */
					rs2 = stmt2.executeQuery(query2);
					String str = null;
					if(rs2.next()) {
			            str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
			            +  "\t" + rs2.getString(5)+ "\t" + rs2.getString(6)+ "\t"+ rs2.getString(7)+ "\t"+ rs2.getString(8)
			            + "\t" + rs2.getString(9)+ "\t"+ rs2.getString(10)+"\n";
					}
		            selectcp.append(str);
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(210, 48, 57, 23);
		panel.add(btnNewButton);
		
		cpcname = new JTextField();
		cpcname.setColumns(10);
		cpcname.setBounds(126, 81, 141, 21);
		panel.add(cpcname);
		
		cpcnum = new JTextField();
		cpcnum.setColumns(10);
		cpcnum.setBounds(126, 112, 141, 21);
		panel.add(cpcnum);
		
		cpcsits = new JTextField();
		cpcsits.setColumns(10);
		cpcsits.setBounds(126, 143, 141, 21);
		panel.add(cpcsits);
		
		cpcmanufacture = new JTextField();
		cpcmanufacture.setColumns(10);
		cpcmanufacture.setBounds(126, 181, 141, 21);
		panel.add(cpcmanufacture);
		
		cpcyear = new JTextField();
		cpcyear.setColumns(10);
		cpcyear.setBounds(126, 217, 141, 21);
		panel.add(cpcyear);
		btnNewButton_1_3 = new JButton("����");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a1 = cpcname.getText();
				String a2 = cpcnum.getText();
				String a3 = cpcsits.getText();
				String a4 = cpcmanufacture.getText();
				String a5 = cpcyear.getText();
				String a6 = cpcdistance.getText();
				String a7 = cpcprice.getText();
				String a8 = registdate.getText();
				String a9 = cpid.getText();
				String id = cpcid.getText();
				try {
					selectcp.setText("");
					stmt = con.createStatement();
					
					String query="update campingcar_list set cc_name='"+a1+"'"
							+ ",cc_number='"+a2+"',cc_sits='"+a3+"'"
							+ ",cc_manufacture='"+a4+"',cc_manufacture_year='"+a5+"',cc_mileage='"+a6+"',cc_rent_price='"+a7+"',cc_regist_date='"+a8+"',campingcar_rent_company_id='"+a9+"'"
							+ " where campingcar_list_id='"+id+"'";
				
					int result = stmt.executeUpdate(query);
					if(result==1) {
							JOptionPane.showMessageDialog(btnNewButton_1_3, "�����Ϸ�"); 
							printdata();
					}else {
						 JOptionPane.showMessageDialog(btnNewButton_1_3, "�ٽ��Է��ϼ���!");
						 printdata();
					}
				}catch(Exception e1) {
					if(a1.length()==0||a2.length()==0||a3.length()==0||a4.length()==0||a5.length()==0||a6.length()==0||
							a7.length()==0||a8.length()==0||a9.length()==0||id.length()==0) {
						JOptionPane.showMessageDialog(btnNewButton_1_3, "��ĭ�� ���ä���ּ���"); 
						printdata();
					}
					System.err.println(e1);
				}
			}
		});
		btnNewButton_1_3.setForeground(Color.BLACK);
		btnNewButton_1_3.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton_1_3.setBounds(115, 380, 70, 29);
		panel.add(btnNewButton_1_3);
		
		
		btnNewButton_1_3_1 = new JButton("����");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = cpcid.getText();
				try {
					stmt = con.createStatement();
					
					String query="DELETE FROM campingcar_list WHERE campingcar_list_id = '"+id+"'";
					int result = stmt.executeUpdate(query);
					query="DELETE FROM rentcar_list WHERE rent_id = '"+id+"'";
					int result2 = stmt.executeUpdate(query);
					if(result == 1&&result2==1) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_1, "���� �Ϸ�");
						printdata();
						cpcid.setText("");
					}else {
						JOptionPane.showMessageDialog(btnNewButton_1_3_1, "ID�� �Է����ּ���.");
					}
				}catch(Exception e1) {
					//System.out.println(e1);
					
				}
			 			
			}
		});
		btnNewButton_1_3_1.setForeground(Color.BLACK);
		btnNewButton_1_3_1.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton_1_3_1.setBounds(197, 380, 70, 29);
		panel.add(btnNewButton_1_3_1);
		
		quit = new JButton("�ݱ�");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("����", Font.BOLD, 15));
		quit.setBounds(115, 419, 70, 22);
		panel.add(quit);
		
		btnNewButton_1 = new JButton("���ΰ�ħ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printdata();
				cpcname.setText("");
				cpcnum.setText("");
				cpcsits.setText("");
				cpcmanufacture.setText("");
				cpcyear.setText("");
				cpcdistance.setText("");
				cpcprice.setText("");
				registdate.setText("");
				cpid.setText("");
				cpcid.setText("");
			}
		});
		btnNewButton_1.setBounds(16, 18, 96, 20);
		panel.add(btnNewButton_1);
		
		btnNewButton_1_3_2 = new JButton("�Է�");
		btnNewButton_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a1 = cpcname.getText();
				String a2 = cpcnum.getText();
				String a3 = cpcsits.getText();
				String a4 = cpcmanufacture.getText();
				String a5 = cpcyear.getText();
				String a6 = cpcdistance.getText();
				String a7 = cpcprice.getText();
				String a8 = registdate.getText();
				String a9 = cpid.getText();
				try {
					stmt = con.createStatement();
					String query="insert into campingcar_list(cc_name,cc_number,cc_sits,cc_manufacture,cc_manufacture_year,cc_mileage,cc_rent_price,cc_regist_date,campingcar_rent_company_id)"
							+ " values('"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"','"+a7+"','"+a8+"','"+a9+"')";
					int result =  stmt.executeUpdate(query);
					if (result == 1) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "�Է¿Ϸ�!");
						printdata();
					}else {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "�ٽ��Է����ּ���!");
					}
				}catch(Exception e1) {
					if(a1.length()==0||a2.length()==0||a3.length()==0||a4.length()==0||a5.length()==0||a6.length()==0||
							a7.length()==0||a8.length()==0||a9.length()==0) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "��ĭ�� ���ä���ּ���"); 
						printdata();
					}
					//System.out.println(e1);
				}
			}
		});
		btnNewButton_1_3_2.setForeground(Color.BLACK);
		btnNewButton_1_3_2.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton_1_3_2.setBounds(34, 380, 70, 29);
		panel.add(btnNewButton_1_3_2);
		
		lblNewLabel = new JLabel("ķ��īID �˻��� ����,�����Ͻø� �� ���մϴ�.");
		lblNewLabel.setBounds(526, 49, 273, 15);
		panel.add(lblNewLabel);
		
		cpcdistance = new JTextField();
		cpcdistance.setColumns(10);
		cpcdistance.setBounds(126, 248, 141, 21);
		panel.add(cpcdistance);
		
		cpcprice = new JTextField();
		cpcprice.setColumns(10);
		cpcprice.setBounds(126, 279, 141, 21);
		panel.add(cpcprice);
		
		cpid = new JTextField();
		cpid.setColumns(10);
		cpid.setBounds(126, 310, 141, 21);
		panel.add(cpid);
		
		registdate = new JTextField();
		registdate.setColumns(10);
		registdate.setBounds(126, 345, 141, 21);
		panel.add(registdate);
		
		lblNewLabel_1 = new JLabel("ķ��īID�Է�");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_1.setBounds(16, 49, 110, 20);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("ķ��ī���� �Է� | ���� | ����");
		lblNewLabel_2.setFont(new Font("����ưưüB", Font.BOLD, 28));
		lblNewLabel_2.setBounds(457, 0, 443, 39);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("����");
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_3.setBounds(16, 80, 110, 20);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("������ȣ");
		lblNewLabel_4.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_4.setBounds(16, 115, 102, 20);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("�����ο���");
		lblNewLabel_5.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_5.setBounds(16, 146, 102, 20);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("����ȸ��");
		lblNewLabel_6.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_6.setBounds(16, 184, 102, 20);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("�����⵵");
		lblNewLabel_7.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_7.setBounds(16, 218, 102, 20);
		panel.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("��������Ÿ�");
		lblNewLabel_8.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_8.setBounds(16, 251, 102, 20);
		panel.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("�뿩���");
		lblNewLabel_9.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_9.setBounds(16, 282, 102, 20);
		panel.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("�뿩ȸ��ID");
		lblNewLabel_10.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_10.setBounds(16, 313, 102, 20);
		panel.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("���������");
		lblNewLabel_11.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_11.setBounds(16, 345, 102, 20);
		panel.add(lblNewLabel_11);
		
		
	}
}


