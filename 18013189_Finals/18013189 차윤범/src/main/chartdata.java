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

	// SQL 연결
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
		     //  System.out.println("드라이버 로드 성공");
		     } catch(ClassNotFoundException e1) {
		         e1.printStackTrace();
		     }
		   try {
		     //  System.out.println("데이터베이스 연결 준비...");
		       con=DriverManager.getConnection(url, userid, pwd); 
		      // System.out.println("데이터베이스 연결 성공");
		     } catch(SQLException e1) {
		         e1.printStackTrace();
		       }
		   }
		public void printdata() {
			selectcp.setText("차트번호 \t 진료ID \t 의사ID \t 환자ID \t 간호사ID \t 차트내용\n");
			try {
				stmt2 = con.createStatement();
				String query2=" select * from charts"; /* SQL 문 */
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
		setTitle("18013189 차윤범 -차트정보 관리페이지");
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
		/* 출력 영역 */
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(311, 98, 576, 287);
		panel.add(scrollPane);
		
		scrollPane.setViewportView(selectcp);
		
		chart_id = new JTextField();
		chart_id.setColumns(10);
		chart_id.setBounds(158, 99, 72, 20);
		panel.add(chart_id);
		
		JButton btnNewButton = new JButton("검색");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectcp.setText("");
					stmt2 = con.createStatement();
					String id = chart_id.getText();
					selectcp.setText("차트번호 \t 진료ID \t 의사ID \t 환자ID \t 간호사ID \t 차트내용\n");
					String query2=" select * from charts where chart_id='"+id+"';"; /* SQL 문 */
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
		
		quit = new JButton("닫기");
		quit.setForeground(Color.BLACK);
		quit.setFont(new Font("굴림", Font.BOLD, 15));
		quit.setBounds(115, 355, 70, 22);
		panel.add(quit);
		
		btnNewButton_1 = new JButton("새로고침");
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
		
		btnNewButton_1_3_2 = new JButton("입력");
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
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "입력완료!");
						printdata();
					}else {
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "다시입력해주세요!");
					}
				}catch(Exception e1) {
					System.out.println(e1);
					JOptionPane.showMessageDialog(btnNewButton_1_3_2, "입력오류!");
					printdata();
					if(a1.length()==0||a2.length()==0||a3.length()==0||a4.length()==0||id.length()==0){
						JOptionPane.showMessageDialog(btnNewButton_1_3_2, "빈칸을 모두채워주세요"); 
						printdata();
					}
					//System.out.println(e1);
				}
			}
		});
		btnNewButton_1_3_2.setForeground(Color.BLACK);
		btnNewButton_1_3_2.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton_1_3_2.setBounds(34, 355, 70, 22);
		panel.add(btnNewButton_1_3_2);
		
		lblNewLabel = new JLabel("차트ID를 입력하세요");
		lblNewLabel.setBounds(329, 55, 294, 15);
		panel.add(lblNewLabel);
		
		
		lblNewLabel_1 = new JLabel("차트내용");
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1.setBounds(16, 141, 120, 20);
		panel.add(lblNewLabel_1);
		
		lblNewLabel_1_1 = new JLabel("간호사ID");
		lblNewLabel_1_1.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(16, 178, 120, 20);
		panel.add(lblNewLabel_1_1);
		
		
		lblNewLabel_2 = new JLabel("진료 정보 입력");
		lblNewLabel_2.setFont(new Font("양재튼튼체B", Font.BOLD, 28));
		lblNewLabel_2.setBounds(274, 10, 432, 39);
		panel.add(lblNewLabel_2);
		
		lblNewLabel_1_1_2 = new JLabel("의사ID");
		lblNewLabel_1_1_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(16, 255, 120, 20);
		panel.add(lblNewLabel_1_1_2);
		
		lblNewLabel_1_1_3 = new JLabel("차트ID입력");
		lblNewLabel_1_1_3.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_1_3.setBounds(16, 102, 120, 20);
		panel.add(lblNewLabel_1_1_3);
		
		lblNewLabel_1_2 = new JLabel("진료ID");
		lblNewLabel_1_2.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(16, 216, 120, 20);
		panel.add(lblNewLabel_1_2);
		
		lblNewLabel_1_3 = new JLabel("환자ID");
		lblNewLabel_1_3.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(16, 294, 120, 20);
		panel.add(lblNewLabel_1_3);
		
	}
}


