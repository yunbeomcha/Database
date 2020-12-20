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

public class Nursedata extends JFrame{

	private JPanel contentPane;
	 JTextField nur_id;
	JButton btnNewButton_1_3;
	JButton btnNewButton_1_3_1;
	JButton btnNewButton_1_3_2;
	JButton quit;
	 JTextField major_job;
	 JTextField nur_name;
	 JTextField nur_gen;
	 JTextField nur_phone;
	 JTextField nur_email;
	 JTextArea selectcp = new JTextArea();;
	 JButton btnNewButton_1;
	 private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nursedata frame = new Nursedata();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// SQL 연결
		static Connection con;
		   Statement stmt,stmt2;
		   ResultSet rs,rs2;
		   String Driver="";
		   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
		   String userid="madang";
		   String pwd="madang";
		   private JTextField nur_position;
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
		   
		   
		public void conDB() { 
		     try {
		       Class.forName("com.mysql.cj.jdbc.Driver");   
		       //System.out.println("드라이버 로드 성공");
		     } catch(ClassNotFoundException e1) {
		         e1.printStackTrace();
		     }
		   try {
		       //System.out.println("데이터베이스 연결 준비...");
		       con=DriverManager.getConnection(url, userid, pwd); 
		      // System.out.println("데이터베이스 연결 성공");
		     } catch(SQLException e1) {
		         e1.printStackTrace();
		       }
		   }
		public void printdata() {
			selectcp.setText("간호사ID \t 담당업무 \t 성명 \t 성별 \t 전화번호 \t 이메일 \t\t 직급 \n");
			try {
				stmt2 = con.createStatement();
				String query2=" select * from nurses"; /* SQL 문 */
				rs2 = stmt2.executeQuery(query2);
				
				while(rs2.next()) {
					String str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
		            +  "\t" + rs2.getString(5)+ "\t" + rs2.getString(6)+"\t" + rs2.getString(7)+"\t" +"\n";
					selectcp.append(str);
				}
			}catch(Exception e1) {
				System.out.println(e1);
			}
		}	
		
	public Nursedata() {
		setTitle("18013189 차윤범 -간호사 정보관리페이지");
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
		/* 출력 영역 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 840, 343);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(selectcp);
		
		nur_id = new JTextField();
		nur_id.setColumns(10);
		nur_id.setBounds(126, 49, 72, 20);
		panel.add(nur_id);
			
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectcp.setText("");
					stmt2 = con.createStatement();
					String id = nur_id.getText();
					selectcp.setText("간호사ID \t 담당업무 \t 성명\t 성별 \t 전화번호 \t 이메일 \t \t직급 \n");
					String query2=" select * from nurses where nur_id='"+id+"';"; /* SQL 문 */
					rs2 = stmt2.executeQuery(query2);
					String str = null;
					if(rs2.next()) {
			            str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
			            +  "\t" + rs2.getString(5)+ "\t" + rs2.getString(6)+ "\t"+ rs2.getString(7)+ "\n";
					}
		            selectcp.append(str);
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(210, 48, 57, 23);
		panel.add(btnNewButton);
		
		major_job = new JTextField();
		major_job.setColumns(10);
		major_job.setBounds(126, 81, 141, 21);
		panel.add(major_job);
		
		nur_name = new JTextField();
		nur_name.setColumns(10);
		nur_name.setBounds(126, 112, 141, 21);
		panel.add(nur_name);
		
		nur_gen = new JTextField();
		nur_gen.setColumns(10);
		nur_gen.setBounds(126, 143, 141, 21);
		panel.add(nur_gen);
		
		nur_phone = new JTextField();
		nur_phone.setColumns(10);
		nur_phone.setBounds(126, 181, 141, 21);
		panel.add(nur_phone);
		
		nur_email = new JTextField();
		nur_email.setColumns(10);
		nur_email.setBounds(126, 217, 141, 21);
		panel.add(nur_email);
		
		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(115, 419, 70, 22);
		panel.add(quit);
		
		btnNewButton_1 = new JButton("새로고침");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printdata();
				major_job.setText("");
				nur_name.setText("");
				nur_gen.setText("");
				nur_phone.setText("");
				nur_email.setText("");
				nur_position.setText("");
				nur_id.setText("");
			}
		});
		btnNewButton_1.setBounds(16, 18, 96, 20);
		panel.add(btnNewButton_1);
		
		btnNewButton_1_3_2 = new JButton("입력");
		btnNewButton_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a1 = major_job.getText();
				String a2 = nur_name.getText();
				String a3 = nur_gen.getText();
				String a4 = nur_phone.getText();
				String a5 = nur_email.getText();
				String a6 = nur_position.getText();
				String id = nur_id.getText();
				try {
					stmt = con.createStatement();
					String query="insert into nurses(nur_id,major_job,nur_name,nur_gen,nur_phone,nur_email,nur_position)"
							+ " values('"+id+"','"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"')";
					int result =  stmt.executeUpdate(query);
					if (result == 1) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "입력완료!");
						printdata();
					}else {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "다시입력해주세요!");
					}
				}catch(Exception e1) {
					System.out.println(e1);
					JOptionPane.showMessageDialog(btnNewButton_1_3_2, "입력오류!");
					printdata();
					if(a1.length()==0||a2.length()==0||a3.length()==0||a4.length()==0||a5.length()==0||a6.length()==0) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "입력오류!");
						printdata();
					}
					//System.out.println(e1);
				}
			}
		});
		btnNewButton_1_3_2.setForeground(Color.BLACK);
		btnNewButton_1_3_2.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3_2.setBounds(35, 419, 70, 22);
		panel.add(btnNewButton_1_3_2);
		
		lblNewLabel = new JLabel("간호사ID 검색후 수정,삭제하시면 더 편리합니다.");
		lblNewLabel.setBounds(526, 49, 273, 15);
		panel.add(lblNewLabel);
		
		nur_position = new JTextField();
		nur_position.setColumns(10);
		nur_position.setBounds(126, 248, 141, 21);
		panel.add(nur_position);
		
		
		
		lblNewLabel_1 = new JLabel("간호사ID입력");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_1.setBounds(16, 49, 110, 20);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("간호사정보 입력");
		lblNewLabel_2.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		lblNewLabel_2.setBounds(457, 0, 443, 39);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("담당업무");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_3.setBounds(16, 80, 110, 20);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("성명");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_4.setBounds(16, 115, 102, 20);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("성별");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_5.setBounds(16, 146, 102, 20);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("전화번호");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_6.setBounds(16, 184, 102, 20);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("이메일");
		lblNewLabel_7.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_7.setBounds(16, 218, 102, 20);
		panel.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("직급");
		lblNewLabel_8.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_8.setBounds(16, 251, 102, 20);
		panel.add(lblNewLabel_8);
		
		
	}
}


