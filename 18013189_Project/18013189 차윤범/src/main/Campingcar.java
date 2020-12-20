package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextField;
import java.awt.SystemColor;
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Campingcar extends JFrame implements ActionListener {

	private JPanel contentPane;
	admin form;
	Customer cstform;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Campingcar frame = new Campingcar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// SQL ����
		static Connection con;
		PreparedStatement psmt;
		   Statement stmt,stmt1;
		   ResultSet rs;
		   String Driver="";
		   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
		   String userid="madang";
		   String pwd="madang";
		   private JTextField torepair;
		   private JTextField tofix;
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
		       System.out.println("CONNECTION SUCCESFUL\nREADY TO USE PROGRAM");
		     } catch(SQLException e1) {
		         e1.printStackTrace();
		       }
		   }
	public Campingcar() {
		setTitle("18013189 ������ -����������");
		conDB();
		datareset();
		cstform = new Customer();
		cstform.backbtn.addActionListener(this);
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 473);
		contentPane = new JPanel();
		
		
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		 form = new admin();
		 form.backbtn.addActionListener(this);
		 
		JButton btnNewButton = new JButton("������");
		btnNewButton.setBackground(new Color(204, 153, 153));
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton) {
					returnresult();
		        	setVisible(false);
		        	form.setVisible(true);
				}
			}
		});
		btnNewButton.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton.setBounds(130, 275, 116, 92);
		btnNewButton.setForeground(new Color(0, 0, 0));
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("�մ�");
		btnNewButton_1.setBackground(new Color(204, 153, 153));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cstform.returnresult();
				if(e.getSource() == btnNewButton_1) {
		        	setVisible(false);
		        	cstform.setVisible(true);
				}
			}
		});
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("����", Font.BOLD, 20));
		btnNewButton_1.setBounds(403, 275, 116, 92);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(278, 47, 97, 86);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SEJONG CAMPING CAR");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 40));
		lblNewLabel_1.setBounds(93, 176, 481, 58);
		contentPane.add(lblNewLabel_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cstform.backbtn) {
			cstform.setVisible(false);
        	setVisible(true);
        	
		}
		if(e.getSource() == form.backbtn) {
			
			form.setVisible(false);
        	setVisible(true);
		}
	}
	public void returnresult(){
		try {
			form.returnresulttxt.setText("���� \t ������ \t ���� \t ���� \t �������� \t ķ��īID \t �����뿩ID \n");
			stmt = con.createStatement();
			String query="SELECT * FROM campingcar_return;";
			rs = stmt.executeQuery(query);
             while(rs.next()) {
                String str = rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4)
                +  "\t" + rs.getString(5)+ "\t" + rs.getString(6)+"\t" + rs.getString(7)+"\n";
                form.returnresulttxt.append(str);
             }
		}catch(Exception e1) {
			System.out.println(e1);
		}
	}
	
	public void datareset() {
		try {
			stmt1 = con.createStatement();
			String query="DROP SCHEMA IF EXISTS `madang` ;";
			stmt1.executeUpdate(query);
			
			
			stmt1 = con.createStatement();
			query="CREATE SCHEMA IF NOT EXISTS `madang` DEFAULT CHARACTER SET utf8 ;";
			stmt1.executeUpdate(query);
			
			stmt1 = con.createStatement();
			query="USE `madang`;";
			stmt1.executeUpdate(query);
			
			stmt1 = con.createStatement();
            query="DROP TABLE IF EXISTS `customer_rent_old_list`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `rentcar_list`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `campingcar_return`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `repair_list`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `garage`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `customer_rent_list`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `campingcar_list`;";
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `customer`;"; 
             stmt1.executeUpdate(query);
             query="DROP TABLE IF EXISTS `campingcar_rent_company`;";
             stmt1.executeUpdate(query);
               // System.out.println("�����Ϸ�!");
                
           query="CREATE TABLE IF NOT EXISTS `campingcar_rent_company` (\r\n" + 
           		"  `camping_rent_company_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
           		"  `cp_name` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cp_address` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cp_number` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cp_mng_email` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cp_mng_name` VARCHAR(45) NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`camping_rent_company_id`))";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `customer` (\r\n" + 
           		"  `license_id` INT NOT NULL,\r\n" + 
           		"  `c_name` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `c_address` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `c_number` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `c_email` VARCHAR(100) NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`license_id`))";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `campingcar_list` (\r\n" + 
           		"  `campingcar_list_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
           		"  `cc_name` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_number` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_sits` INT NOT NULL,\r\n" + 
           		"  `cc_manufacture` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_manufacture_year` INT NOT NULL,\r\n" + 
           		"  `cc_mileage` INT NOT NULL,\r\n" + 
           		"  `cc_rent_price` INT NOT NULL,\r\n" + 
           		"  `cc_regist_date` DATE NOT NULL,\r\n" + 
           		"  `campingcar_rent_company_id` INT NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`campingcar_list_id`),\r\n" + 
           		"  INDEX `fk_campingcar_list_campingcar_rent_company1_idx` (`campingcar_rent_company_id` ASC) VISIBLE,\r\n" + 
           		"  CONSTRAINT `fk_campingcar_list_campingcar_rent_company1`\r\n" + 
           		"    FOREIGN KEY (`campingcar_rent_company_id`)\r\n" + 
           		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION)";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `customer_rent_list` (\r\n" + 
           		"  `rent_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
           		"  `start_date` DATE NOT NULL,\r\n" + 
           		"  `rent_time` INT NOT NULL,\r\n" + 
           		"  `due_date` DATE NOT NULL,\r\n" + 
           		"  `otherthing` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `others_price` INT NOT NULL,\r\n" + 
           		"  `campingcar_company_id` INT NOT NULL,\r\n" + 
           		"  `c_license_id` INT NOT NULL,\r\n" + 
           		"  `campingcar_id` INT NOT NULL,\r\n" + 
           		"  `cc_price` INT NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`rent_id`),\r\n" + 
           		"  INDEX `fk_customer rent list_customer1_idx` (`c_license_id` ASC) VISIBLE,\r\n" + 
           		"  INDEX `fk_customer rent list_campingcar rent company1_idx` (`campingcar_company_id` ASC) VISIBLE,\r\n" + 
           		"  INDEX `fk_customer_rent_list_campingcar_list1_idx` (`campingcar_id` ASC) VISIBLE,\r\n" + 
           		"  CONSTRAINT `fk_customer rent list_customer1`\r\n" + 
           		"    FOREIGN KEY (`c_license_id`)\r\n" + 
           		"    REFERENCES `customer` (`license_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION,\r\n" + 
           		"  CONSTRAINT `fk_customer rent list_campingcar rent company1`\r\n" + 
           		"    FOREIGN KEY (`campingcar_company_id`)\r\n" + 
           		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION,\r\n" + 
           		"  CONSTRAINT `fk_customer_rent_list_campingcar_list1`\r\n" + 
           		"    FOREIGN KEY (`campingcar_id`)\r\n" + 
           		"    REFERENCES `campingcar_list` (`campingcar_list_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION)";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `garage` (" + 
                 "  `garage_id` INT NOT NULL AUTO_INCREMENT," + 
                 "  `g_name` VARCHAR(45) NOT NULL," + 
                 "  `g_address` VARCHAR(45) NOT NULL," + 
                 "  `g_number` VARCHAR(45) NOT NULL," + 
                 "  `g_manager` VARCHAR(45) NOT NULL," + 
                 "  `g_email` VARCHAR(100) NOT NULL," + 
                 "  PRIMARY KEY (`garage_id`))";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `repair_list` (\r\n" + 
           		"  `repair list_id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
           		"  `r_log` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `r_date` DATE NOT NULL,\r\n" + 
           		"  `r_price` INT NOT NULL,\r\n" + 
           		"  `r_due_date` DATE NOT NULL,\r\n" + 
           		"  `r_other_repair` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `customer_license_id` INT NOT NULL,\r\n" + 
           		"  `campingcar_rent_company_id` INT NOT NULL,\r\n" + 
           		"  `garage_id` INT NOT NULL,\r\n" + 
           		"  `campingcar_list_id` INT NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`repair list_id`),\r\n" + 
           		"  INDEX `fk_repair list_customer1_idx` (`customer_license_id` ASC) VISIBLE,\r\n" + 
           		"  INDEX `fk_repair list_campingcar rent company1_idx` (`campingcar_rent_company_id` ASC) VISIBLE,\r\n" + 
           		"  INDEX `fk_repair list_garage1_idx` (`garage_id` ASC) VISIBLE,\r\n" + 
           		"  CONSTRAINT `fk_repair list_customer1`\r\n" + 
           		"    FOREIGN KEY (`customer_license_id`)\r\n" + 
           		"    REFERENCES `customer` (`license_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION,\r\n" + 
           		"  CONSTRAINT `fk_repair list_campingcar rent company1`\r\n" + 
           		"    FOREIGN KEY (`campingcar_rent_company_id`)\r\n" + 
           		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION,\r\n" + 
           		"  CONSTRAINT `fk_repair list_garage1`\r\n" + 
           		"    FOREIGN KEY (`garage_id`)\r\n" + 
           		"    REFERENCES `garage` (`garage_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION)";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `campingcar_return` (\r\n" + 
           		"  `front` VARCHAR(100) NOT NULL,\r\n" + 
           		"  `right` VARCHAR(100) NOT NULL,\r\n" + 
           		"  `left` VARCHAR(100) NOT NULL,\r\n" + 
           		"  `back` VARCHAR(100) NOT NULL,\r\n" + 
           		"  `fix` INT NOT NULL,\r\n" + 
           		"  `campingcar_list_id` INT NOT NULL,\r\n" + 
           		"  `custom_rent_list_id` INT NOT NULL,\r\n" + 
           		"  INDEX `fk_campingcar return_campingcar list1_idx` (`campingcar_list_id` ASC) VISIBLE,\r\n" + 
           		"  UNIQUE INDEX `custom_rent_list_id_UNIQUE` (`custom_rent_list_id` ASC) VISIBLE,\r\n" + 
           		"  CONSTRAINT `fk_campingcar return_campingcar list1`\r\n" + 
           		"    FOREIGN KEY (`campingcar_list_id`)\r\n" + 
           		"    REFERENCES `campingcar_list` (`campingcar_list_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION)";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `rentcar_list` (\r\n" + 
           		"  `rent_id` INT NOT NULL,\r\n" + 
           		"  `cc_name` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_number` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_sits` INT NOT NULL,\r\n" + 
           		"  `cc_manufacture` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `cc_manufacture_year` INT NOT NULL,\r\n" + 
           		"  `cc_mileage` INT NOT NULL,\r\n" + 
           		"  `cc_rent_price` INT NULL,\r\n" + 
           		"  `cc_regist_date` DATE NOT NULL,\r\n" + 
           		"  `camping_rent_company_id` INT NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`rent_id`),\r\n" + 
           		"  INDEX `fk_rentcar_list_campingcar_rent_company1_idx` (`camping_rent_company_id` ASC) VISIBLE,\r\n" + 
           		"  CONSTRAINT `fk_rentcar_list_campingcar_rent_company1`\r\n" + 
           		"    FOREIGN KEY (`camping_rent_company_id`)\r\n" + 
           		"    REFERENCES `campingcar_rent_company` (`camping_rent_company_id`)\r\n" + 
           		"    ON DELETE CASCADE\r\n" + 
           		"    ON UPDATE NO ACTION)";
           stmt1.executeUpdate(query);
           
           query="CREATE TABLE IF NOT EXISTS `customer_rent_old_list` (\r\n" + 
           		"  `rent_id` INT NOT NULL,\r\n" + 
           		"  `start_date` DATE NOT NULL,\r\n" + 
           		"  `retn_time` INT NOT NULL,\r\n" + 
           		"  `due_date` DATE NOT NULL,\r\n" + 
           		"  `otherthing` VARCHAR(45) NOT NULL,\r\n" + 
           		"  `others_price` INT NOT NULL,\r\n" + 
           		"  `campingcar_company_id` INT NOT NULL,\r\n" + 
           		"  `c_license_id` INT NOT NULL,\r\n" + 
           		"  `campingcar_id` INT NOT NULL,\r\n" + 
           		"  `cc_price` INT NOT NULL,\r\n" + 
           		"  PRIMARY KEY (`rent_id`))";
           stmt1.executeUpdate(query);
        //    System.out.println("���̺� �ʱ�ȭ �Ϸ�");
            
           query="insert into campingcar_rent_company values('1','SAMSUNG','����� ���ʱ�','02-345-5676','samsungcprent@naver.com','��Ｚ'),"+
        		 "('2','LG','����� ������','02-546-5474','lgcprent@naver.com','�ֿ���'),"+
                 "('3','HYUNDAI','����� ������','02-368-7374','hyundaicprent@naver.com','������'),"+
                 "('4','BOOGATI','����� ���ϱ�','02-485-9536','boogaticprent@naver.com','�����'),"+
                 "('5','SHEVORAE','����� ��걸','02-854-9642','shevoraecprent@naver.com','������'),"+
                 "('6','PORCHE','����� ���α�','02-458-4736','porchecprent@naver.com','������'),"+
                 "('7','SYOUNG','����� ������','02-146-9074','sangyoungcprent@naver.com','�ֽֿ�'),"+
                 "('8','BMW','����� ��������','02-136-9654','bmwcprent@naver.com','������'),"+
                 "('9','GMC','��⵵ ����','02-725-0526','gmccprent@naver.com','�ֿ���'),"+
                 "('10','TOYOTA','����� ���۱�','02-6427-3743','toyotacprent@naver.com','���Ÿ'),"+
                 "('11','HONDA','����� ���Ǳ�','02-9558-1584','hondacprent@naver.com','��ȥ��'),"+
                 "('12','KIA','����� ��������','02-435-3023','kiacprent@naver.com','��Ű��'),"+
                 "('13','RAMBORG','����� ������','02-435-3023','ramborgcprent@naver.com','�̶���'),"+
                 "('14','SAMSUNG','����� ���α�','02-345-5348','samsung2cprent@naver.com','�ֻ��'),"+
                 "('15','HYUNDAI','����� ������','02-546-2346','hyndai2cprent@naver.com','������');";
                stmt1.executeUpdate(query);
               
               query="insert into campingcar_list values('1','TIGERJK','23�� 4534','8','samsung','1945','20000','30','2017-04-20','1')," + 
                     "('2','LIONG2','45ȣ 3453','6','LG','1975','31000','30','2019-05-20','2')," + 
                     "('3','DOG46','33ȣ 4245','8','HYUNDAI','2021','68000','20','2021-05-20','3')," + 
                     "('4','CAT32','36�� 8553','7','BOOGATI','2020','99000','50','2020-04-25','4')," + 
                     "('5','PENCILMANIA','26�� 4525','9','SHEVORAE','2020','300000','30','2020-01-20','5')," + 
                     "('6','CORANDO','25�� 9626','10','PORCHE','1945','294033','32','2017-04-20','6')," + 
                     "('7','CAMPFILE','95�� 4216','5','SSANGYOUNG','2015','305924','54','2017-07-20','7')," + 
                     "('8','BOOMBA','73�� 4791','8','HYUNDAI','2017','24924','43','2018-01-05','3')," + 
                     "('9','GARGARRI','743�� 5274','8','samsung','2014','12353','65','2017-03-06','1')," + 
                     "('10','GOMAN','937�� 5930','8','HYUNDAI','2015','402044','85','2017-03-10','3')," + 
                     "('11','GOODSCORE','793�� 4362','5','HYUNDAI','2017','500','12','2017-07-19','3')," + 
                     "('12','DAMBOO','274�� 6927','8','samsung','2012','7942','53','2017-04-13','1')," + 
                     "('13','EARLYBURN','407�� 2423','4','KIA','2018','13414','84','2018-06-10','13')," + 
                     "('14','BESTY','501�� 5035','8','KIA','2019','93423','894','2019-07-30','15')," + 
                     "('15','ROLEBACK','36�� 6959','10','KIA','2020','19494','15','2020-05-20','11')," +
                     "('16','NONEBREAK','34�� 2423','13','HYUNDAI','2020','19494','15','2020-06-09','3'),"+
                     "('17','ROLEBACK','54�� 6959','5','KIA','1944','19494','15','1944-05-20','13')," + 
                     "('18','RANDY','23�� 3421','6','HYUNDAI','1967','35421','45','1967-05-20','6')," + 
                     "('19','ROMA','347�� 3462','7','samsung','2020','34133','46','2020-05-20','8')," + 
                     "('20','ROOMER','45�� 5684','4','SHEVORAE','2020','31546','36','2020-05-20','7')," + 
                     "('21','RANAD','613�� 9578','7','HYUNDAI','2020','23431','38','2020-05-20','8')," + 
                     "('22','CODEA','35�� 5684','8','HYUNDAI','2020','345425','35','2020-05-20','4')," + 
                     "('23','FEDCRT','38�� 3456','9','HYUNDAI','2020','34345','83','2020-05-20','1')," + 
                     "('24','NFRSV','84�� 3458','4','KIA','2020','3533','76','2020-05-20','2')," + 
                     "('25','EFCGE','29�� 3559','6','HYUNDAI','2020','2454','34','2020-05-20','3')," + 
                     "('26','ECC4','68�� 3354','8','KIA','2020','4624','47','2020-05-20','7')," + 
                     "('27','FEVSR5','35�� 3482','9','HYUNDAI','2020','34563','26','2020-05-20','9')," + 
                     "('28','NSXCT3','74�� 4594','4','KIA','2020','23462','34','2020-05-20','9')," + 
                     "('29','DDDDEF','28�� 9563','8','HYUNDAI','2020','24624','100','2020-05-20','4')," + 
                     "('30','EREER3','83�� 2348','9','KIA','2020','134345','120','2020-05-20','7'),"+
                     "('31','BESTCAR','83�� 3231','10','KIA','2020','111313','120','2020-05-20','14')";
                     
            stmt1.executeUpdate(query);
            
               query="insert into customer values('1111111','�踶��','����� ��������','010-3735-2396','uiurihappy@naver.com')," + 
                     "('2235235','������','����� ���ʱ�','010-9773-5873','cjh@naver.com')," + 
                     "('3235245','�̼�','����� ������','010-2135-1343','isu@gmail.com')," + 
                     "('4353522','����','����� ������','010-4787-9133','psy@nate.com')," + 
                     "('5356356','�����','��⵵ �ϻ�','010-1377-6464','dail@naver.com')," + 
                     "('6235565','������','����� ������','010-5468-1381','dlwlrma@naver.com')," + 
                     "('7245245','�ֿ���','����� ������','010-7893-5133','kidcozyboy@naver.com')," + 
                     "('8567433','������','����� ������','010-4861-1669','porkcutlet@naver.com')," + 
                     "('9467242','�����','��õ�� ��籸','010-1384-8319','santakiller@gmail.com')," + 
                     "('1023454','������','����� ���ϱ�','010-1731-9997','yumdda@naver.com')," + 
                     "('1124635','�ѿ���','��⵵ �д籸','010-3656-3961','yohans@gmail.com')," + 
                     "('1256735','��â��','����� ��������','010-4881-8386','ganggang@hanmail.net')," + 
                     "('1323554','������','����� ������','010-0203-3080','clloud@naver.com')," + 
                     "('1473565','��ΰ�','����� ������','010-8816-8479','liik@naver.com')," + 
                     "('1484534','�ŵ���','����� ��������','010-8403-9930','thequiet@naver.com');";
              stmt1.executeUpdate(query);
              
             query="insert into customer_rent_list values('1','2019-11-14','7','2019-11-11','ķ������','2','1','1023454','17','30')," + 
                   "('2','2019-01-04','4','2019-01-01','�ٺ�ť�׸�','4','2','1124635','18','20')," + 
                   "('3','2019-07-12','3','2019-07-09','��ź����','1','3','1256735','19','15')," + 
                   "('4','2019-08-31','5','2019-08-28','��ȸ��ǰ','1','4','1323554','20','25')," + 
                   "('5','2019-10-10','10','2019-10-07','�ٺ�ť�׸�','4','5','1111111','21','42')," + 
                   "('6','2019-06-14','8','2019-06-11','��ź����','1','6','1473565','22','35')," + 
                   "('7','2019-12-25','7','2019-12-22','��','2','7','1484534','23','30')," + 
                   "('8','2019-05-04','15','2019-05-01','��ȸ��ǰ','1','8','2235235','24','65')," + 
                   "('9','2019-08-25','12','2019-08-22','ķ������','2','9','3235245','25','50')," + 
                   "('10','2019-07-06','9','2019-07-03','õ��','3','10','4353522','26','40')," + 
                   "('11','2019-04-25','7','2019-04-22','���ô�','6','11','5356356','27','30')," + 
                   "('12','2019-02-14','6','2019-02-11','õ��','3','12','6235565','28','26')," + 
                   "('13','2019-01-13','15','2019-01-10','��ȸ��ǰ','1','13','7245245','29','65')," + 
                   "('14','2019-05-16','11','2019-05-13','ķ������','2','14','8567433','30','46')," + 
                   "('15','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','31','30')";
                   
                stmt1.executeUpdate(query);
                
                query="insert into campingcar_return values('��ȣ','��ȣ','��ȣ','��ȣ','0','30','17')," + 
                      "('��ȣ','��ȣ','��ȣ','��ȣ','0','29','18')," + 
                      "('��ȣ','��ȣ','�ҷ�','��ȣ','1','28','19')," + 
                      "('��ȣ','��ȣ','��ȣ','��ȣ','0','27','20')," + 
                      "('��ȣ','��ȣ','�ҷ�','��ȣ','1','26','21')," + 
                      "('��ȣ','��ȣ','��ȣ','��ȣ','0','25','22')," + 
                      "('�ҷ�','��ȣ','��ȣ','��ȣ','1','24','23')," + 
                      "('��ȣ','�ҷ�','��ȣ','��ȣ','1','23','24')," + 
                      "('��ȣ','��ȣ','��ȣ','��ȣ','0','22','25')," + 
                      "('��ȣ','��ȣ','��ȣ','��ȣ','0','20','27')," + 
                      "('��ȣ','��ȣ','��ȣ','�ҷ�','1','19','28')," + 
                      "('��ȣ','��ȣ','��ȣ','��ȣ','0','18','29')," + 
                      "('��ȣ','��ȣ','��ȣ','��ȣ','0','17','30')," +
                      "('��ȣ','�ҷ�','��ȣ','��ȣ','1','16','31');";
                   stmt1.executeUpdate(query);
                   
                query="insert into garage values('1','���Ƿο�ī����','����� ��õ��','010-0943-5493','��ī��','justice@naver.com')," + 
                      "('2','�߰�ħī����','����� ��õ��','010-2343-5454','�߼���','sdgse@naver.com')," + 
                      "('3','KIAPROF','����� ���α�','010-6756-7856','�̹�ȣ','sefsfsd@naver.com')," + 
                      "('4','DENTIST','����� ������','010-4536-5643','����','asdfsadf@naver.com')," + 
                      "('5','AMZACAR','����� ���ı�','010-1233-4543','���켺','sdfsdf@naver.com')," + 
                      "('6','READYCL','����� ������','010-7533-2364','����','wefwef@naver.com')," + 
                      "('7','������ī����','����� ���α�','010-4537-8543','������','sdvsv@naver.com')," + 
                      "('8','���ʱ�ī����','����� ����','010-3424-7345','������','sewewe@naver.com')," + 
                      "('9','������ī����','����� ��걸','010-7342-9774','������','dgasdd@naver.com')," + 
                      "('10','�漮��ī����','����� ���ϱ�','010-3425-6542','�ּ���','asdfsdfe@naver.com')," + 
                      "('11','�ſ��ī����','����� ���籸','010-7934-2346','�̰���','wefwefd@naver.com')," + 
                      "('12','����ī����','����� ��õ��','010-9357-4935','�̽¹�','bdadvasd@naver.com')," + 
                      "('13','���Ͽ�ī����','����� ���ʱ�','010-4824-1293','�̼���','sdfasdfae@naver.com')," + 
                      "('14','����ī����','����� ������','010-6319-0372','������','wefwefwq@naver.com')," + 
                      "('15','�򸶸�ī����','����� ������','010-3589-4524','ŷ�Ƴ�','sdadsd@naver.com');";
                stmt1.executeUpdate(query);
                
                query= "insert into repair_list values('1','Ÿ�̾�','2019-11-25','4','2019-11-28','����', '1111111', '1', '1', '342')," + 
                      "('2','�������','2019-01-11','8','2019-01-14','���͸�', '2235235', '2', '2', '342')," + 
                      "('3','Ŭ��ġ','2019-07-18','7','2019-07-21','������', '3235245', '3', '3', '45')," + 
                      "('4','���','2019-09-08','9','2019-09-11','����', '4353522', '4', '4', '655')," + 
                      "('5','������','2019-10-23','13','2019-10-26','������', '5356356', '5', '5', '956')," + 
                      "('6','�극��ũ','2019-06-25','5','2019-06-28','Ÿ�̾�', '6235565', '6', '6', '453')," + 
                      "('7','������','2020-01-04','18','2019-01-07','����', '7245245', '7', '7', '457')," + 
                      "('8','�������','2019-05-22','9','2019-05-25','����', '8567433', '8', '8', '97')," + 
                      "('9','������','2019-09-09','7','2019-09-12','���͸�', '9467242', '9', '9', '78')," + 
                      "('10','����','2019-07-18','11','2019-07-21','Ŭ��ġ', '1023454', '10', '10', '57')," + 
                      "('11','���','2019-05-05','13','2019-05-08','����', '1124635', '11', '11', '89')," + 
                      "('12','Ʈ��ũ','2019-02-23','12','2019-02-26','������', '1256735', '12', '12', '56')," + 
                      "('13','�극��ũ','2019-01-31','5','2019-02-03','�ð� ��', '1323554', '13', '13', '86')," + 
                      "('14','����','2019-05-30','16','2019-06-03','����', '1473565', '14', '14', '76')," + 
                      "('15','������','2019-12-05','13','2019-12-08','������', '1484534', '15', '15', '54');";
                 stmt1.executeUpdate(query);
                 
           query="insert into rentcar_list values('1','TIGERJK','23�� 4534','8','SAMSUNG','1945','20000','30','2017-04-20','1')," + 
                     "('2','LIONG2','45ȣ 3453','6','LG','1975','31000','30','2019-05-20','2')," + 
                     "('3','DOG46','33ȣ 4245','8','HYUNDAI','2021','68000','20','2021-05-20','3')," + 
                     "('4','CAT32','36�� 8553','7','BOOGATI','2020','99000','50','2020-04-25','4')," + 
                     "('5','PENCILMANIA','26�� 4525','9','SHEVORAE','2020','300000','30','2020-01-20','5')," + 
                     "('6','CORANDO','25�� 9626','10','PORCHE','1945','294033','32','2017-04-20','6')," + 
                     "('7','CAMPFILE','95�� 4216','5','SYOUNG','2015','305924','54','2017-07-20','7')," + 
                     "('8','BOOMBA','73�� 4791','8','HYUNDAI','2017','24924','43','2018-01-05','3')," + 
                     "('9','GARGARRI','743�� 5274','8','samsung','2014','12353','65','2017-03-06','1')," + 
                     "('10','GOMAN','937�� 5930','8','HYUNDAI','2015','402044','85','2017-03-10','3')," + 
                     "('11','GOODSCORE','793�� 4362','5','HYUNDAI','2017','500','12','2017-07-19','3')," + 
                     "('12','DAMBOO','274�� 6927','8','samsung','2012','7942','53','2017-04-13','1')," + 
                     "('13','EARLYBURN','407�� 2423','4','KIA','2018','13414','84','2018-06-10','12')," + 
                     "('14','BESTY','501�� 5035','8','KIA','2019','93423','894','2019-07-30','12')";

               stmt1.executeUpdate(query);
           /*query ="insert into customer_rent_old_list values"+
        		   +
                   "('17','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('18','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('19','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('20','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('21','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('22','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('23','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('24','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('25','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('26','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('27','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('28','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('29','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30'),"+
                   "('30','2019-11-25','7','2019-11-22','�ٺ�ť�׸�','4','15','9467242','16','30')";
           stmt1.executeUpdate(query);	    */           
         //  System.out.println("�Է¿Ϸ�!");
        }catch(Exception Insert) {
           System.out.println("������ �Է¿� ���� �߻�!\n"+Insert);
        }
	}
}
