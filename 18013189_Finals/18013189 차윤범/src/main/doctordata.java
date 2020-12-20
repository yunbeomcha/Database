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

public class doctordata extends JFrame{

	private JPanel contentPane;
	 JTextField doc_id;
	JButton btnNewButton_1_3;
	JButton btnNewButton_1_3_1;
	JButton btnNewButton_1_3_2;
	JButton quit;
	 JTextField major_treat;
	 JTextField doc_name;
	 JTextField doc_gen;
	 JTextField doc_phone;
	 JTextField doc_email;
	 JTextField doc_position;
	 JTextArea selectcp = new JTextArea();;
	 JButton btnNewButton_1;
	 private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					doctordata frame = new doctordata();
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
		   private JLabel lblNewLabel_8;
		   
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
			selectcp.setText("�ǻ�ID \t ���������� \t ���� \t ���� \t ��ȭ��ȣ \t �̸���\t\t ���� \n");
			try {
				stmt2 = con.createStatement();
				String query=" select * from doctors;";
				rs = stmt2.executeQuery(query);
	             while(rs.next()) {
	                String str = rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
	                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+ "\t" + rs.getString(7)+"\n";
	                selectcp.append(str);
	             }
			}catch(Exception e1) {
				System.out.println(e1);
			}
		}	
		
	public doctordata() {
		setTitle("18013189 ������ -�ǻ����� ����������");
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
		
		doc_id = new JTextField();
		doc_id.setColumns(10);
		doc_id.setBounds(126, 101, 72, 20);
		panel.add(doc_id);
			
		
		JButton btnNewButton = new JButton("�˻�");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectcp.setText("");
					stmt2 = con.createStatement();
					String id = doc_id.getText();
					selectcp.setText("�ǻ� ID \t ���������� \t ���� \t ���� \t ��ȭ��ȣ \t �̸���\t\t ���� \n");
					String query2=" select * from doctors where doc_id='"+id+"';"; /* SQL �� */
					rs2 = stmt2.executeQuery(query2);
					String str = null;
					if(rs2.next()) {
			            str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
			            +  "\t" + rs2.getString(5)+ "\t" + rs2.getString(6)+"\t"+ rs2.getString(7)+"\n";
					}
		            selectcp.append(str);
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(210, 100, 57, 23);
		panel.add(btnNewButton);
		
		major_treat = new JTextField();
		major_treat.setColumns(10);
		major_treat.setBounds(126, 140, 141, 21);
		panel.add(major_treat);
		
		doc_name = new JTextField();
		doc_name.setColumns(10);
		doc_name.setBounds(126, 176, 141, 21);
		panel.add(doc_name);
		
		doc_gen = new JTextField();
		doc_gen.setColumns(10);
		doc_gen.setBounds(126, 212, 141, 21);
		panel.add(doc_gen);
		
		doc_phone = new JTextField();
		doc_phone.setColumns(10);
		doc_phone.setBounds(126, 250, 141, 21);
		panel.add(doc_phone);
		
		doc_email = new JTextField();
		doc_email.setColumns(10);
		doc_email.setBounds(126, 286, 141, 21);
		panel.add(doc_email);
		
		doc_position = new JTextField();
		doc_position.setColumns(10);
		doc_position.setBounds(126, 321, 141, 21);
		panel.add(doc_position);
		
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
				String major_treat1 = major_treat.getText();
				String doc_name1 = doc_name.getText();
				String doc_gen1 = doc_gen.getText();
				String doc_email1 = doc_email.getText();
				String doc_phone1 = doc_phone.getText();
				String doc_position1 = doc_position.getText();
				String id = doc_id.getText();
				try {
						stmt = con.createStatement();
						String query="insert into doctors(doc_id,major_treat,doc_name,doc_gen,doc_phone,doc_email,doc_position)"
						+"values('"+id+"','"+major_treat1+"','"+doc_name1+"','"+doc_gen1+"','"+doc_phone1+"','"+doc_email1+"','"+doc_position1+"')";
						int result =  stmt.executeUpdate(query);
						if (result == 1) {
							JOptionPane.showMessageDialog(btnNewButton_1_3_2, "�Է¿Ϸ�!");
							printdata();
						}else {
							JOptionPane.showMessageDialog(btnNewButton_1_3_2, "�ٽ��Է����ּ���!");
						}
				}catch(Exception e1) {
					System.out.println(e1);
					JOptionPane.showMessageDialog(btnNewButton_1_3_2, "�Է¿���!");
					printdata();
				}
			}
		});
		btnNewButton_1_3_2.setForeground(Color.BLACK);
		btnNewButton_1_3_2.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton_1_3_2.setBounds(42, 361, 70, 22);
		panel.add(btnNewButton_1_3_2);
		
		lblNewLabel = new JLabel("\uD68C\uC0ACID \uAC80\uC0C9\uD6C4 \uC218\uC815,\uC0AD\uC81C\uD558\uC2DC\uBA74 \uB354 \uD3B8\uB9AC\uD569\uB2C8\uB2E4.");
		lblNewLabel.setBounds(389, 49, 256, 15);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("�ǻ�ID�Է�");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_1.setBounds(16, 100, 120, 20);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("�ǻ� ���� �Է� ");
		lblNewLabel_2.setFont(new Font("����ưưüB", Font.BOLD, 28));
		lblNewLabel_2.setBounds(317, 4, 391, 39);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("������");
		lblNewLabel_3.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_3.setBounds(16, 143, 96, 20);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("�̸�");
		lblNewLabel_4.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_4.setBounds(16, 176, 96, 20);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("����");
		lblNewLabel_5.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_5.setBounds(16, 215, 96, 20);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("��ȭ��ȣ");
		lblNewLabel_6.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_6.setBounds(16, 253, 96, 20);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("�̸���");
		lblNewLabel_7.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_7.setBounds(16, 289, 96, 20);
		panel.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("����");
		lblNewLabel_8.setFont(new Font("����", Font.BOLD, 16));
		lblNewLabel_8.setBounds(16, 327, 96, 20);
		panel.add(lblNewLabel_8);
		
	}
	

	
}


