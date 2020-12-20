package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.ComponentOrientation;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JSlider;
import java.awt.List;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Window.Type;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.sql.*;

public class admin extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	doctordata delform;
	Nursedata campform;
	patientdata cusform;
	treatdata grgform;
	chartdata chaform;
	JMenuItem mntmNewMenuItem_1_4;
	JMenuItem mntmNewMenuItem_1_4_1;
	JMenuItem mntmNewMenuItem_1_4_2;
	JTextArea txtResult;
	JButton resetbtn = new JButton("�ʱ�ȭ");
	JTextArea srchtxt = new JTextArea();
    JTextField torepair;
    private JTextField grgid;
    private JTextField rlog;
    private JTextField rfixdate;
    private JTextField rprice;
    private JTextField rduedate;
    private JTextField rotherinfo;
    
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					admin frame = new admin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	// SQL ����
	static Connection con;
	   Statement stmt,stmt1,stmt2,stmt3,stmt4;
	   ResultSet rs,rs2,rs4;
	   String Driver="";
	   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
	   String userid="madang";
	   String pwd="madang";
	   
	   
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
	       //System.out.println("�����ͺ��̽� ���� ����");
	     } catch(SQLException e1) {
	         e1.printStackTrace();
	       }
	   }
	
	
	JMenuItem cpcregist;
	JMenuItem cpcedit;
	JMenuItem cpcdelete;
	
	JMenuItem cpcregist_1;
	JMenuItem cpcedit_1;
	JMenuItem cpcdelete_1;
	
	JMenuItem cpcregist_2;
	JMenuItem cpcedit_2;
	JMenuItem cpcdelete_2;
	
	JMenuItem cpcregist_3;
	JMenuItem cpcedit_3;
	JMenuItem cpcdelete_3;
	public admin() {
		setTitle("18013189/������");
		conDB();
		txtResult = new JTextArea();
		delform = new doctordata();
		delform.quit.addActionListener(this);
		
		campform = new Nursedata();
		campform.quit.addActionListener(this);
		
		cusform = new patientdata();
		cusform.quit.addActionListener(this);
		
		grgform = new treatdata();
		grgform.quit.addActionListener(this);
		
		chaform = new chartdata();
		chaform.quit.addActionListener(this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 setBounds(500,500,680,600);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		setJMenuBar(menuBar);
		//�뿩ȸ��---------------------------------------------------------------------
		JMenu mnNewMenu = new JMenu("Doctors");
		mnNewMenu.setHorizontalTextPosition(SwingConstants.CENTER);
		mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.setIconTextGap(30);
		menuBar.add(mnNewMenu);
		
		mntmNewMenuItem_1_4 = new JMenuItem("���");
		mntmNewMenuItem_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				delform.setVisible(true);
			}
		});
		mntmNewMenuItem_1_4.setMargin(new Insets(0, 30, 0, 0));
		mntmNewMenuItem_1_4.setPreferredSize(new Dimension(150, 30));
		mntmNewMenuItem_1_4.setHorizontalTextPosition(SwingConstants.CENTER);
		mntmNewMenuItem_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu.add(mntmNewMenuItem_1_4);
		//---------------------------------------------------------------------
		
		//�뿩ȸ��---------------------------------------------------------------------
		JMenu mnNewMenu_1 = new JMenu("nurses");
		mnNewMenu_1.setIconTextGap(30);
		mnNewMenu_1.setHorizontalTextPosition(SwingConstants.CENTER);
		mnNewMenu_1.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_1);
		
		cpcregist = new JMenuItem("���");
		cpcregist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				campform.setVisible(true);
			}
		});
		cpcregist.setPreferredSize(new Dimension(150, 30));
		cpcregist.setMargin(new Insets(0, 30, 0, 0));
		cpcregist.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcregist.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_1.add(cpcregist);
		
		//ķ��ī---------------------------------------------------------------------
		JMenu mnNewMenu_2 = new JMenu("Patients");
		mnNewMenu_2.setIconTextGap(30);
		mnNewMenu_2.setHorizontalTextPosition(SwingConstants.CENTER);
		mnNewMenu_2.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_2);
		
		cpcregist_1 = new JMenuItem("���");
		cpcregist_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				cusform.setVisible(true);
			}
		});
		
		cpcregist_1.setPreferredSize(new Dimension(150, 30));
		cpcregist_1.setMargin(new Insets(0, 30, 0, 0));
		cpcregist_1.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcregist_1.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_2.add(cpcregist_1);
		
		//��---------------------------------------------------------------------
		
		JMenu mnNewMenu_3 = new JMenu("Treatment");
		mnNewMenu_3.setIconTextGap(30);
		mnNewMenu_3.setHorizontalTextPosition(SwingConstants.CENTER);
		mnNewMenu_3.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_3);
		
		cpcregist_2 = new JMenuItem("���");
		cpcregist_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				grgform.setVisible(true);
			}
		});
		
		cpcregist_2.setPreferredSize(new Dimension(150, 30));
		cpcregist_2.setMargin(new Insets(0, 30, 0, 0));
		cpcregist_2.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcregist_2.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_3.add(cpcregist_2);
		//------------------------------------------------
		
		JMenu mnNewMenu_4 = new JMenu("Charts");
		mnNewMenu_4.setIconTextGap(30);
		mnNewMenu_4.setHorizontalTextPosition(SwingConstants.CENTER);
		mnNewMenu_4.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mnNewMenu_4);
		
		cpcregist_3 = new JMenuItem("���");
		cpcregist_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				chaform.setVisible(true);
			}
		});
		
		cpcregist_3.setPreferredSize(new Dimension(150, 30));
		cpcregist_3.setMargin(new Insets(0, 30, 0, 0));
		cpcregist_3.setHorizontalTextPosition(SwingConstants.CENTER);
		cpcregist_3.setHorizontalAlignment(SwingConstants.CENTER);
		mnNewMenu_4.add(cpcregist_3);
		//--------------------------------------------------------------------
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		srchtxt.setBounds(10, 110, 630, 500);
		panel.add(srchtxt);
		
		JButton btn1 = new JButton("�˻�1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("�˻�1 ���\n");
				try {
					stmt = con.createStatement();
					String query = "select * from doctors";
					rs = stmt.executeQuery(query);
					String str =null;
					while(rs.next()) {
							str=rs.getString(1) + "\t" +rs.getString(2) + "\t"+ rs.getString(3) + "\t"+ rs.getString(4) + "\t"+ rs.getString(5) + "\t"+ rs.getString(6) + "\t"+ rs.getString(7) + "\t"+"\n";
							srchtxt.append(str);
						}
					}catch(Exception e1) {
						System.out.println(e1);
					}
				}
		});
		btn1.setBounds(535, 26, 105, 23);
		panel.add(btn1);
		
		JButton btn2 = new JButton("�˻�2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("�˻�2 ���\n");
				try {
				stmt = con.createStatement();
				String query = "select doc_id ,doc_name\r\n" + 
						"from doctors dc\r\n" + 
						"where exists (select *\r\n" + 
						"				from patients pt\r\n" + 
						"                where pt.pat_addr like '%����%' and pt.doc_id = dc.doc_id)\r\n" + 
						"group by dc.doc_id, dc.doc_name;";
				rs = stmt.executeQuery(query);
				String str =null;
				while(rs.next()) {
					str=rs.getInt(1) +"\t"+ rs.getString(2)+"\n";
					srchtxt.append(str);
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		
		JButton btn3 = new JButton("�˻�3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				srchtxt.setText("�˻�3 ���\n");
				try {
				stmt = con.createStatement();
				String query = "select pat_name ,doc_name\r\n" + 
						"from doctors dc join patients pt using(doc_id)\r\n" + 
						"where pt.doc_id = dc.doc_id and exists (select pt.doc_id, dc.doc_id\r\n" + 
						"from patients pt join charts ch using(doc_id)\r\n" + 
						"where exists (select ch.pat_id, ch.doc_id\r\n" + 
						"from charts ch\r\n" + 
						"where ch.chart_contents like '%����Ϸ�%' and ch.doc_id = dc.doc_id and ch.pat_id = pt.pat_id))\r\n" + 
						"group by pt.pat_name, dc.doc_name;\r\n";
				rs = stmt.executeQuery(query);
				String str =null;
				while(rs.next()) {
					str=rs.getString(1) + "\t" +rs.getString(2) + "\t"+"\n";
					srchtxt.append(str);
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		btn3.setBounds(535, 79, 105, 23);
		panel.add(btn3);
		btn2.setBounds(535, 53, 105, 23);
		panel.add(btn2);
		
		JLabel lblNewLabel_7 = new JLabel("1. �ǻ� ����� ���̽ÿ�.");
		lblNewLabel_7.setBounds(10, 30, 321, 15);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("2. �ּҰ� ������ ȯ�ڸ� �ð��ִ� �ǻ�ID�� �ǻ��̸��� ���̽ÿ�");
		lblNewLabel_8.setBounds(10, 57, 400, 15);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("3. ��Ʈ���뿡 ����Ϸ��� ȯ���̸��� ȯ�ڸ� ����ϴ� �ǻ��̸��� ���̽ÿ�");
		lblNewLabel_9.setBounds(10, 83, 450, 15);
		panel.add(lblNewLabel_9);
		
		resetbtn.setForeground(Color.WHITE);
		resetbtn.setBounds(535, 5, 105, 19);
		panel.add(resetbtn);
		resetbtn.setBorder(new CompoundBorder());
		resetbtn.setBackground(new Color(140, 140, 70));
		resetbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 try {
		            	int size=0;
		            	stmt1 = con.createStatement();
		            	String query="DELETE FROM charts;";
		                size=stmt1.executeUpdate(query);
		                System.out.println("charts "+size+"��\n");
		                
		                stmt1 = con.createStatement();
		                query="DELETE FROM treatments;";
		                size=stmt1.executeUpdate(query);
		                System.out.println("treatments "+size+"��\n");
		                
		                stmt1 = con.createStatement();
		                query="DELETE FROM patients;";
		                size=stmt1.executeUpdate(query);
		                System.out.println("patients "+size+"��\n");
		                
		                stmt1 = con.createStatement();
		                query="DELETE FROM nurses;";
		                size=stmt1.executeUpdate(query);
		                System.out.println("nurses "+size+"��\n");
		                
		                stmt1 = con.createStatement();
		               query="DELETE FROM doctors;";
		               size=stmt1.executeUpdate(query);
		               System.out.println("doctors "+size+"��\n");
		               
		               System.out.println("�����Ϸ�");
		               
		            }catch(Exception D) {
		               System.out.println("������ ���� ���� �߻�!\n"+D);
		               //������ ���� �ÿ� ���� ��¹��� ����.
		            }
				
		            //�����͸� �����ϴ� SQL���̴�.
		            
		            try {
		            	stmt1 = con.createStatement();
		               String query="insert into doctors values(980312,'�Ҿư�','������', 'M','010-333-1340', 'ltj@hanbh.com', '����')\r\n" + 
		               		",(000601,'����','�ȼ���', 'M','011-222-0987', 'ask@hanbh.com', '����')\r\n" + 
		               		",(001208,'�ܰ�','�����', 'M','010-333-8743', 'kmj@hanbh.com', '����')\r\n" + 
		               		",(020403,'�Ǻΰ�','���¼�', 'M','019-777-3764', 'lts@hanbh.com', '����')\r\n" + 
		               		",(050900,'�Ҿư�','�迬��', 'F','010-555-3746', 'kya@hanbh.com', '������')\r\n" + 
		               		",(050101,'����','������', 'M','011-222-7643', 'cth@hanbh.com', '������')\r\n" + 
		               		",(062019,'�Ҿư�','������', 'F','010-999-1265', 'jjh@hanbh.com', '������')\r\n" + 
		               		",(070576,'�Ǻΰ�','ȫ�浿', 'M','016-333-7263', 'hgd@hanbh.com', '������')\r\n" + 
		               		",(080543,'��缱��','���缮', 'M','010-222-1263', 'yjs@hanbh.com', '����')\r\n" + 
		               		",(091001,'�ܰ�','�躴��', 'M','010-555-3542', 'kbm@hanbh.com', '������')\r\n" + 
		               		",(11,'�ܰ�','����', 'F','011-101-9010', 'nuilpac@naver.com', '������Ʈ')\r\n" + 
		               		",(12,'��缱��','���缮', 'M','010-1111-6840', 'qwfdzg@naver.com', '����')\r\n" + 
		               		",(13,'����ΰ�','�缮��', 'M','011-121-1943', 'rwhx@naver.com', '����')\r\n" + 
		               		",(14,'����','�ȱ���', 'M','011-131-5123', 'fudn@naver.com', '����')\r\n" + 
		               		",(15,'�ܰ�','������', 'M','011-3341-1390', 'q1yehzb@naver.com', '����')\r\n" + 
		               		",(16,'�Ǻΰ�','�躴��', 'M','010-141-8674', 'yusvz@naver.com', '����')\r\n" + 
		               		",(17,'��缱��','������', 'F','010-151-9731', 'wbtuxva@naver.com', '������Ʈ')\r\n" + 
		               		",(18,'�Ҿư�','������', 'M','041-3321-4662', 'dsvweyx@naver.com', '����')\r\n" + 
		               		",(19,'�Ǻΰ�','������', 'F','081-3322-1315', 'cavdsyca@naver.com', '����')\r\n" + 
		               		",(20,'�Ű�ܰ�','ä��ȭ', 'F','010-3643-4651', 'ascqtgav@naver.com', '����');";
		               stmt1.executeUpdate(query);
		               
		               stmt1 = con.createStatement();
		                     query="insert into nurses values(050302,'�Ҿư�','������', 'F','010-555-8751', 'key@hanbh.com', '����ȣ��')\r\n" + 
		                     		",(050021,'����','������', 'F','016-333-8745', 'ysa@hanbh.com', '����ȣ��')\r\n" + 
		                     		",(040089,'�Ǻΰ�','������', 'M','010-666-7646', 'sjw@hanbh.com', '����')\r\n" + 
		                     		",(070605,'��缱��','����ȭ', 'F','010-333-4588', 'yjh@hanbh.com', '����')\r\n" + 
		                     		",(070804,'����','���ϳ�', 'F','010-222-1340', 'nhn@hanbh.com', '����')\r\n" + 
		                     		",(071018,'�Ҿư�','��ȭ��', 'F','019-888-4116', 'khk@hanbh.com', '����')\r\n" + 
		                     		",(100356,'�Ҿư�','�̼���', 'M','010-777-1234', 'lsy@hanbh.com', '��ȣ��')\r\n" + 
		                     		",(104145,'�ܰ�','����', 'M','010-999-8520', 'kh@hanbh.com', '��ȣ��')\r\n" + 
		                     		",(120309,'�Ǻΰ�','�ڼ���', 'M','010-777-4996', 'psw@hanbh.com', '��ȣ��')\r\n" + 
		                     		",(130211,'�ܰ�','�̼���', 'F','010-222-3214', 'lsy2@hanbh.com', '��ȣ��')\r\n" + 
		                     		",(31,'�ܰ�','�����', 'F','011-8761-7651', 'nvsydz@hanbh.com', '������Ʈ')\r\n" + 
		                     		",(32,'��缱��','���γ�', 'M','010-1313-4313', 'vqetdv@hanbh.com', '����')\r\n" + 
		                     		",(33,'����ΰ�','������', 'M','011-5103-7131', 'avzvw@hanbh.com', '����')\r\n" + 
		                     		",(34,'����','�ȼ���', 'M','011-6485-4313', 'fudasasc@hanbh.com', '����')\r\n" + 
		                     		",(35,'�ܰ�','������', 'M','011-786-6781', 'vsatzaasdfq@hanbh.com', '����')\r\n" + 
		                     		",(36,'�Ǻΰ�','������', 'M','010-8641-4531', 'vaegfdzvq@hanbh.com', '����')\r\n" + 
		                     		",(37,'��缱��','ȫ��ǥ', 'F','010-8648-7613', 'wvqegd@hanbh.com', '������Ʈ')\r\n" + 
		                     		",(38,'�Ҿư�','����ȣ', 'M','041-8139-7861', 'tehsv@hanbh.com', '����')\r\n" + 
		                     		",(39,'�Ǻΰ�','���ξ�', 'F','081-6871-7315', 'cqtqtc@hanbh.com', '����')\r\n" + 
		                     		",(40,'�Ű�ܰ�','������', 'F','010-7651-4351', 'vacaetaf@hanbh.com', '����');";
		                     stmt1.executeUpdate(query);

		                     stmt1 = con.createStatement();
		                     query="insert into patients values(2345,'050302', '980312','�Ȼ��','M','232345','����','010-555-7845','ask@ab.com','ȸ���');\r\n";
		                     stmt1.executeUpdate(query);
		                     query="insert into patients values(3545,'040089', '020403','�輺��', 'M','543545','����','010-333-7812','ksr@bb.com','�ڿ���');\r\n";
		                     stmt1.executeUpdate(query);
		                     query = "insert into patients values(3424,'070605', '080543','������', 'M','433424','�λ�','019-888-4859','ljj@ab.com','ȸ���');\r\n";
		                     stmt1.executeUpdate(query);
		                     query = "insert into patients values(7675,'100356', '050900','�ֱ���', 'M','677675','����','010-222-4847','cks@cc.com','ȸ���');\r\n";
		                     stmt1.executeUpdate(query);
		                     query = "insert into patients values(4533,'070804', '000601','���Ѱ�', 'M','744533','����','010-777-9630','jhk@ab.com','����');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(5546,'120309', '070576','������', 'M','765546','�뱸','016-777-0214','ywh@cc.com','�ڿ���');\r\n";
		                     stmt1.executeUpdate(query);
		                     query = "insert into patients values(4543,'070804', '050101','������', 'M','454543','�λ�','010-555-4187','cjj@bb.com','ȸ���');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query = "insert into patients values(9768,'130211', '091001','������', 'F','119768','����','010-888-3675','ljh@ab.com','����');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(4234,'130211', '091001','������', 'F','234234','����','010-999-6541','onm@cc.com','�л�');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(7643,'071018', '062019','�����', 'M','987643','����','010-222-5874','ssm@bb.com','�л�');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(51,'31', '11','���ÿ�', 'M','405616','����','010-333-7812','ysy@bb.com','����');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(52,'32', '12','���Ʊ�', 'M','544894','����','010-333-7812','khk@bb.com','�ڿ���');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(53,'33', '13','������', 'M','433876','�λ�','019-888-4859','ljy@ab.com','ȸ���');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(54,'34', '14','�����', 'M','677868','����','010-222-4847','jyj@cc.com','������');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(55,'35', '15','�ֿ���', 'M','891397','����','010-777-9630','cwj@ab.com','����');\r\n";
		                     stmt1.executeUpdate(query);
		                     query="insert into patients values(56,'36', '16','��â��', 'M','757651','�뱸','016-777-0214','gcm@cc.com','�ڿ���');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(57,'37', '17','�Ѵ���', 'M','984130','�λ�','010-555-4187','hdg@bb.com','ȸ���');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(58,'38', '18','������', 'F','645033','����','010-888-3675','yjy@ab.com','����');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(59,'39', '19','��ȿ��', 'F','284689','����','010-999-6541','khe@cc.com','�л�');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into patients values(60,'40', '20','��ΰ�', 'M','894166','����','010-222-5874','kmk@bb.com','����');";
		                     stmt1.executeUpdate(query);
		                   
		                     
		                     stmt1 = con.createStatement();
		                     query="insert into treatments values(130516023,'2345','980312','���� ����','2013-05-16');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(130628100,'3545','020403','�Ǻ� Ʈ���� ġ��','2013-06-28');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(131205056,'3424','080543','�� ��ũ�� MRI�Կ�','2013-12-05');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(131218024,'7675','050900','���̿�','2013-12-18');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(131224012,'4533','000601','�忰','2013-12-24');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(140103001,'5546','070576','���帧 ġ��','2014-01-03');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(140109026,'4543','050101','����','2014-01-09');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(140226102,'9768','091001','ȭ�� ġ��','2014-02-26');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(140303003,'4234','091001','������ �ܻ�ġ��','2014-03-03');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(140308087,'7643','062019','�忰','2014-03-08');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(71,'51','11','ȭ��','2015-11-26');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(72,'52','12','����','2012-12-01');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(73,'53','13','����','2013-05-12');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(74,'54','14','������','2016-06-30');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(75,'55','15','����','2017-08-21');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(76,'56','16','���� ����','2019-09-23');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(77,'57','17','������','2020-01-31');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(78,'58','18','�忰','2014-03-15');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(79,'59','19','ġ��','2012-12-24');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into treatments values(80,'60','20','���','2020-12-26');";
		                     stmt1.executeUpdate(query);
		                     
		                     stmt1 = con.createStatement();
		                     query="insert into charts values(81,'130516023','980312','2345','050302','����Ϸ�');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(82,'130628100','020403','3545','050021','��ó��');\r\n";
		                     stmt1.executeUpdate(query);
		                     query = "insert into charts values(83,'131205056','080543','3424','040089','�齺');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(84,'131218024','050900','7675','070605','����ó��');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(85,'131224012','000601','4533','070804','����ó��');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(86,'140103001','070576','5546','071018','��ó��');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(87,'140109026','050101','4543','100356','����ó��');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(88,'140226102','091001','9768','104145','�齺');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(89,'140303003','091001','4234','120309','�齺');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(90,'140308087','062019','7643','130211','����Ϸ�');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(91,'71','11','51','31','����Ϸ�');\r\n";
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(92,'72','12','52','32','��ó��');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(93,'73','13','53','33','����ó��');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(94,'74','14','54','34','����Ϸ�');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(95,'75','15','55','35','����Ϸ�');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(96,'76','16','56','36','��ó��');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(97,'77','17','57','37','�齺');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(98,'78','18','58','38','����Ϸ�');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(99,'79','19','59','39','����Ϸ�');\r\n"; 
		                     stmt1.executeUpdate(query);
		                     query ="insert into charts values(100,'80','20','60','40','����Ϸ�');";
		                     stmt1.executeUpdate(query);
		                     System.out.println("�Է¿Ϸ�!");
		                    
		                     //�ʱ�ȭ ��ư�� ������ �����ǰ� INSERT������ �����͸� �����Ѵ�.
		            }catch(Exception Insert) {
		               System.out.println("������ �Է¿� ���� �߻�!\n"+Insert);
		               //������ ���� �ÿ� ���� ��¹��� ����.
		            }
		         }
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==delform.quit) {
			delform.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==campform.quit) {
			campform.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==cusform.quit) {
			cusform.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==grgform.quit) {
			grgform.setVisible(false);
			setVisible(true);
		}
		if(e.getSource()==chaform.quit) {
			chaform.setVisible(false);
			setVisible(true);
		}
	}
}
