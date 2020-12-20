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

public class chartdata extends JFrame{

	private JPanel contentPane;
	 JTextField chart_id;
	JButton btnNewButton_1_3;
	JButton btnNewButton_1_3_1;
	JButton btnNewButton_1_3_2;
	JButton quit;
	 JTextField chart_contents;
	 JTextField nur_id;
	 JTextField treat_id;
	 JTextField doc_id;
	 JTextField pat_id;
	 JTextArea selectcp = new JTextArea();;
	 JButton btnNewButton_1;
	 private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chartdata frame = new chartdata();
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
		   private JTextField gmanager;
		   private JLabel lblNewLabel_1;
		   private JLabel lblNewLabel_1_1;
		   private JLabel lblNewLabel_1_1_1;
		   private JLabel lblNewLabel_2;
		   private JLabel lblNewLabel_1_1_2;
		   private JLabel lblNewLabel_1_1_3;
		   private JLabel lblNewLabel_1_2;
		   private JLabel lblNewLabel_1_3;
		   
		public void conDB() { 
		     try {
		       Class.forName("com.mysql.cj.jdbc.Driver");   
		     //  System.out.println("����̹� �ε� ����");
		     } catch(ClassNotFoundException e1) {
		         e1.printStackTrace();
		     }
		   try {
		     //  System.out.println("�����ͺ��̽� ���� �غ�...");
		       con=DriverManager.getConnection(url, userid, pwd); 
		      // System.out.println("�����ͺ��̽� ���� ����");
		     } catch(SQLException e1) {
		         e1.printStackTrace();
		       }
		   }
		public void printdata() {
			selectcp.setText("��Ʈ��ȣ \t ����ID \t �ǻ�ID \t ȯ��ID \t ��ȣ��ID \t ��Ʈ����\n");
			try {
				stmt2 = con.createStatement();
				String query2=" select * from charts"; /* SQL �� */
				rs2 = stmt2.executeQuery(query2);
				
				while(rs2.next()) {
					String str = rs2.getInt(1) + "\t" + rs2.getInt(2) + "\t" + rs2.getInt(3) + "\t" + rs2.getInt(4)
		            +  "\t" + rs2.getInt(5)+"\t"+ rs2.getString(6)+"\n";
					selectcp.append(str);
				}
			}catch(Exception e1) {
				System.out.println(e1);
			}
		}	
		
	public chartdata() {
		setTitle("18013189 ������ -��Ʈ���� ����������");
		conDB();
		
		printdata();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 921, 441);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		/* ��� ���� */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 576, 287);
		panel.add(scrollPane);
		
		scrollPane.setViewportView(selectcp);
		
		chart_id = new JTextField();
		chart_id.setColumns(10);
		chart_id.setBounds(158, 99, 72, 20);
		panel.add(chart_id);
		
		JButton btnNewButton = new JButton("�˻�");
		btnNewButton.setFont(new Font("����", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectcp.setText("");
					stmt2 = con.createStatement();
					String id = chart_id.getText();
					selectcp.setText("��Ʈ��ȣ \t ����ID \t �ǻ�ID \t ȯ��ID \t ��ȣ��ID \t ��Ʈ����\n");
					String query2=" select * from charts where chart_id='"+id+"';"; /* SQL �� */
					rs2 = stmt2.executeQuery(query2);
					String str = null;
					if(rs2.next()) {
						 str = rs2.getInt(1) + "\t" + rs2.getInt(2) + "\t" + rs2.getInt(3) + "\t" + rs2.getInt(4)
				            +  "\t" + rs2.getInt(5)+"\t"+ rs2.getString(6)+"\n";
					}
		            selectcp.append(str);
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(242, 98, 57, 23);
		panel.add(btnNewButton);
		
		chart_contents = new JTextField();
		chart_contents.setColumns(10);
		chart_contents.setBounds(158, 142, 141, 21);
		panel.add(chart_contents);
		
		nur_id = new JTextField();
		nur_id.setColumns(10);
		nur_id.setBounds(158, 179, 141, 21);
		panel.add(nur_id);
		
		treat_id = new JTextField();
		treat_id.setColumns(10);
		treat_id.setBounds(158, 216, 141, 21);
		panel.add(treat_id);
		
		doc_id = new JTextField();
		doc_id.setColumns(10);
		doc_id.setBounds(158, 253, 141, 21);
		panel.add(doc_id);
		
		pat_id = new JTextField();
		pat_id.setColumns(10);
		pat_id.setBounds(158, 290, 141, 21);
		panel.add(pat_id);
		
		quit = new JButton("�ݱ�");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("����", Font.BOLD, 15));
		quit.setBounds(115, 355, 70, 22);
		panel.add(quit);
		
		btnNewButton_1 = new JButton("���ΰ�ħ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printdata();
				chart_contents.setText("");
				nur_id.setText("");
				doc_id.setText("");
				pat_id.setText("");
				chart_id.setText("");
				treat_id.setText("");
			}
		});
		btnNewButton_1.setBounds(16, 18, 96, 20);
		panel.add(btnNewButton_1);
		
		btnNewButton_1_3_2 = new JButton("�Է�");
		btnNewButton_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = chart_id.getText();
				String a1 = chart_contents.getText();
				String a2 = nur_id.getText();
				String a3 = treat_id.getText();
				String a4 = doc_id.getText();
				String a5 = pat_id.getText();
				try {
					stmt = con.createStatement();
					String query="insert into charts(chart_id,treat_id,doc_id,pat_id,nur_id,chart_contents)"
							+ " values('"+id+"','"+a3+"','"+a4+"','"+a5+"','"+a2+"','"+a1+"')";
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
					if(a1.length()==0||a2.length()==0||a3.length()==0||a4.length()==0||id.length()==0){
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "��ĭ�� ���ä���ּ���"); 
						printdata();
					}
					//System.out.println(e1);
				}
			}
		});
		btnNewButton_1_3_2.setForeground(Color.BLACK);
		btnNewButton_1_3_2.setFont(new Font("����", Font.BOLD, 15));
		btnNewButton_1_3_2.setBounds(34, 355, 70, 22);
		panel.add(btnNewButton_1_3_2);
		
		lblNewLabel = new JLabel("��ƮID�� �Է��ϼ���");
		lblNewLabel.setBounds(329, 55, 294, 15);
		panel.add(lblNewLabel);
		
		
		lblNewLabel_1 = new JLabel("��Ʈ����");
		lblNewLabel_1.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_1.setBounds(16, 141, 120, 20);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_1_1 = new JLabel("��ȣ��ID");
		lblNewLabel_1_1.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(16, 178, 120, 20);
		panel.add(lblNewLabel_1_1);
		
		
		lblNewLabel_2 = new JLabel("���� ���� �Է�");
		lblNewLabel_2.setFont(new Font("����ưưüB", Font.BOLD, 28));
		lblNewLabel_2.setBounds(274, 10, 432, 39);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_1_1_2 = new JLabel("�ǻ�ID");
		lblNewLabel_1_1_2.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(16, 255, 120, 20);
		panel.add(lblNewLabel_1_1_2);
		
		lblNewLabel_1_1_3 = new JLabel("��ƮID�Է�");
		lblNewLabel_1_1_3.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_1_1_3.setBounds(16, 102, 120, 20);
		panel.add(lblNewLabel_1_1_3);
		
		lblNewLabel_1_2 = new JLabel("����ID");
		lblNewLabel_1_2.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(16, 216, 120, 20);
		panel.add(lblNewLabel_1_2);
		
		lblNewLabel_1_3 = new JLabel("ȯ��ID");
		lblNewLabel_1_3.setFont(new Font("����", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(16, 294, 120, 20);
		panel.add(lblNewLabel_1_3);
		
	}
}


