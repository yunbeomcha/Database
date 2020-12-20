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

public class patientdata extends JFrame{

	private JPanel contentPane;
	 JTextField pat_id;
	JButton btnNewButton_1_3;
	JButton btnNewButton_1_3_1;
	JButton btnNewButton_1_3_2;
	JButton quit;
	 JTextField pat_name;
	 JTextField pat_gen;
	 JTextField pat_jumin;
	 JTextField pat_addr;
	 JTextField pat_phone;
	 JTextField pat_job;
	 JTextField doc_id;
	 JTextField nur_id;

	 JTextArea selectcp = new JTextArea();;
	 JButton btnNewButton_1;
	 private JLabel lblNewLabel;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					patientdata frame = new patientdata();
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
		   private JTextField pat_email;
		   
		   private JLabel lblNewLabel_1;
		   private JLabel lblNewLabel_2;
		   private JLabel lblNewLabel_3;
		   private JLabel lblNewLabel_4;
		   private JLabel lblNewLabel_5;
		   private JLabel lblNewLabel_6;
		   private JLabel lblNewLabel_7;
		   private JLabel lblNewLabel_8;
		   private JLabel lblNewLabel_9;
		   
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
			selectcp.setText("환자ID \t 간호사ID \t 의사ID \t 환자이름 \t 성별 \t 주민번호 \t 주소  \t 전화번호 \t이메일 \t직업 \n");
			try {
				stmt2 = con.createStatement();
				String query2=" select * from patients"; /* SQL 문 */
				rs2 = stmt2.executeQuery(query2);
				
				while(rs2.next()) {
					String str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
		            +  "\t" + rs2.getString(5)+ "\t" + rs2.getString(6)+"\t" + rs2.getString(7)+"\t"+ rs2.getString(8)+"\t" + rs2.getString(9)+"\t" + rs2.getString(10)+ "\n";
					selectcp.append(str);
				}
			}catch(Exception e1) {
				System.out.println(e1);
			}
		}	
		
	public patientdata() {
		setTitle("18013189 차윤범 -환자 정보관리페이지");
		conDB();
		
		printdata();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		/* 출력 영역 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 1000, 343);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(selectcp);
		
		pat_id = new JTextField();
		pat_id.setColumns(10);
		pat_id.setBounds(126, 49, 72, 20);
		panel.add(pat_id);
			
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectcp.setText("");
					stmt2 = con.createStatement();
					String id = pat_id.getText();
					selectcp.setText("환자ID \t 간호사ID \t 의사ID \t 환자이름 \t 성별 \t 주민번호 \t 주소  \t 전화번호 \t이메일 \t 직업 \n");
					String query2=" select * from patients where pat_id='"+id+"';"; /* SQL 문 */
					rs2 = stmt2.executeQuery(query2);
					String str = null;
					if(rs2.next()) {
						str = rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getString(4)
			            +  "\t" + rs2.getString(5)+ "\t" + rs2.getString(6)+"\t" + rs2.getString(7)+"\t"+ rs2.getString(8)+"\t" + rs2.getString(9)+"\t" + rs2.getString(10)+ "\n";
					}
		            selectcp.append(str);
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btnNewButton.setBounds(210, 48, 57, 23);
		panel.add(btnNewButton);
		
		pat_name = new JTextField();
		pat_name.setColumns(10);
		pat_name.setBounds(126, 81, 141, 21);
		panel.add(pat_name);
		
		pat_gen = new JTextField();
		pat_gen.setColumns(10);
		pat_gen.setBounds(126, 112, 141, 21);
		panel.add(pat_gen);
		
		pat_jumin = new JTextField();
		pat_jumin.setColumns(10);
		pat_jumin.setBounds(126, 143, 141, 21);
		panel.add(pat_jumin);
		
		pat_addr = new JTextField();
		pat_addr.setColumns(10);
		pat_addr.setBounds(126, 181, 141, 21);
		panel.add(pat_addr);
		
		pat_phone = new JTextField();
		pat_phone.setColumns(10);
		pat_phone.setBounds(126, 217, 141, 21);
		panel.add(pat_phone);
		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(115, 419, 70, 22);
		panel.add(quit);
		
		btnNewButton_1 = new JButton("새로고침");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printdata();
				pat_name.setText("");
				pat_gen.setText("");
				pat_jumin.setText("");
				pat_addr.setText("");
				pat_phone.setText("");
				pat_email.setText("");
				pat_job.setText("");
				pat_id.setText("");
				doc_id.setText("");
				nur_id.setText("");
			}
		});
		btnNewButton_1.setBounds(16, 18, 96, 20);
		panel.add(btnNewButton_1);
		
		btnNewButton_1_3_2 = new JButton("입력");
		btnNewButton_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a1 = pat_name.getText();
				String a2 = pat_gen.getText();
				String a3 = pat_jumin.getText();
				String a4 = pat_addr.getText();
				String a5 = pat_phone.getText();
				String a6 = pat_email.getText();
				String a7 = pat_job.getText();
				String id = pat_id.getText();
				String a8 = doc_id.getText();
				String a9 = nur_id.getText();
				try {
					stmt = con.createStatement();
					String query="insert into patients(pat_id, nur_id, doc_id, pat_name, pat_gen, pat_jumin, pat_addr, pat_phone, pat_email, pat_job)"
							+ " values('"+id+"','"+a9+"','"+a8+"','"+a1+"','"+a2+"','"+a3+"','"+a4+"','"+a5+"','"+a6+"','"+a7+"')";
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
					if(a1.length()==0||a2.length()==0||a3.length()==0||a4.length()==0||a5.length()==0||a6.length()==0||
							a7.length()==0||a8.length()==0||a9.length()==0) {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "빈칸을 모두채워주세요"); 
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
		
		pat_email = new JTextField();
		pat_email.setColumns(10);
		pat_email.setBounds(126, 248, 141, 21);
		panel.add(pat_email);
		
		pat_job = new JTextField();
		pat_job.setColumns(10);
		pat_job.setBounds(126, 281, 141, 21);
		panel.add(pat_job);
		
		doc_id = new JTextField();
		doc_id.setColumns(10);
		doc_id.setBounds(126, 314, 141, 21);
		panel.add(doc_id);
		
		nur_id = new JTextField();
		nur_id.setColumns(10);
		nur_id.setBounds(126, 347, 141, 21);
		panel.add(nur_id);
		
		lblNewLabel_1 = new JLabel("환자ID입력");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_1.setBounds(16, 49, 110, 20);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("환자정보 입력");
		lblNewLabel_2.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		lblNewLabel_2.setBounds(457, 0, 443, 39);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("환자이름");
		lblNewLabel_3.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_3.setBounds(16, 80, 110, 20);
		panel.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("성별");
		lblNewLabel_4.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_4.setBounds(16, 115, 102, 20);
		panel.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("주민번호");
		lblNewLabel_5.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_5.setBounds(16, 146, 102, 20);
		panel.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("주소");
		lblNewLabel_6.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_6.setBounds(16, 184, 102, 20);
		panel.add(lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel("전화번호");
		lblNewLabel_7.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_7.setBounds(16, 218, 102, 20);
		panel.add(lblNewLabel_7);
		
		lblNewLabel_8 = new JLabel("이메일");
		lblNewLabel_8.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_8.setBounds(16, 251, 102, 20);
		panel.add(lblNewLabel_8);
		
		lblNewLabel_9 = new JLabel("직업");
		lblNewLabel_9.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_9.setBounds(16, 284, 102, 20);
		panel.add(lblNewLabel_9);
		
		lblNewLabel_9 = new JLabel("의사ID");
		lblNewLabel_9.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_9.setBounds(16, 317, 102, 20);
		panel.add(lblNewLabel_9);
		
		lblNewLabel_9 = new JLabel("간호사ID");
		lblNewLabel_9.setFont(new Font("굴림", Font.BOLD, 16));
		lblNewLabel_9.setBounds(16, 350, 102, 20);
		panel.add(lblNewLabel_9);
	}
}


