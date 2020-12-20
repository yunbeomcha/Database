import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*; 
import java.io.*; 
import java.sql.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
// SQL ���� Ŭ������ java.sql .*�� ���ԵǾ� �ִ�.
// ���� ���� Ŭ������ javax.swing.*�� ���ԵǾ� �ִ�.
// ���̾ƿ�, �̺�Ʈ �� java.awt.��� ���ԵǾ��ִ�.
public class BookListSwing extends JFrame implements ActionListener{
   private static final String String = null;
   JTextField orderid,custid,bookid,saleprice,orderdate; //text�ʵ� ����
   JButton btnbook, btnReset,btnorder, btnCUSTOMER, orderinput, btnir,btnAdd; //��ư ����
   JTextArea txtResult;
   JPanel p1, p2,p3,p4,p5,p6,p7, panWest,panSouth, input; //�г�
   static Connection con;
   Statement stmt1;
   ResultSet rs,rs2,rs3,rs4;
   String Driver="";
   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
   String userid="madang";
   String pwd="madang";
private EventObject e;
// Ŭ���� booklist�� �����Ѵ�. java.sql�� Connection ��ü con�� �����Ѵ�.     
   public BookListSwing() {
      super("18013189 ������"); //������ �й� �̸�
      layInit();
      conDB();
      setVisible(true);
      setBounds(500,500,700,700);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void layInit() {
      btnbook = new JButton ("�˻�1");
      btnorder = new JButton ("�˻�2");
      btnCUSTOMER = new JButton ("�˻�3");
      btnReset = new JButton("ȭ�� �ʱ�ȭ");
      btnir = new JButton("�ʱ�ȭ");
      orderinput = new JButton ("�Է�1");
      txtResult = new JTextArea();
      
      p1 = new JPanel();
      p1.add(btnbook);
      p1.add(btnorder);
      p1.add(btnCUSTOMER);
      p1.add(btnReset);
      p1.add(btnir);
      panWest =new JPanel(new GridLayout(6, 6));
     
      p2 =new JPanel(new FlowLayout(FlowLayout.LEFT));
       p2.add(new JLabel("orderid"));
       p2.add(orderid =new JTextField(15));
       panWest.add(p2);

       p3 =new JPanel(new FlowLayout(FlowLayout.LEFT));
       p3.add(new JLabel("custid"));
       p3.add(custid =new JTextField(15));
       panWest.add(p3);

       p4 =new JPanel(new FlowLayout(FlowLayout.LEFT));
       p4.add(new JLabel("bookid"));
       p4.add(bookid =new JTextField(15));
       panWest.add(p4);
       
       p5 =new JPanel(new FlowLayout(FlowLayout.LEFT));
       p5.add(new JLabel("saleprice"));
       p5.add(saleprice =new JTextField(15));
       panWest.add(p5);
       
       p6 =new JPanel(new FlowLayout(FlowLayout.LEFT));
       p6.add(new JLabel("orderdate"));
       p6.add(orderdate =new JTextField(20));
       panWest.add(p6);
       panWest.add(orderinput);
       //orderid,bookid, custid, saleprice, orderdate �ؽ�Ʈ �ʵ带 ������ش�.
       
      txtResult.setEditable(false);
      JScrollPane scroll = new JScrollPane(txtResult);
      
      add("North", p1);
      add("Center",scroll);
      add("South", panWest);
      
      btnbook.addActionListener(this);
      btnorder.addActionListener(this);
      btnCUSTOMER.addActionListener(this);
      orderinput.addActionListener(this);
      btnReset.addActionListener(this);
      btnir.addActionListener(this);
      }
   
   public void conDB() {
// ���Ӻ����� �ʱ�ȭ�Ѵ�. url�� �ڹ� ����̹� �̸�, ȣ��Ʈ��(localhost), ��Ʈ��ȣ�� �Է��Ѵ�
// userid�� ������(madang), pwd�� ������� ��й�ȣ(madang)�� �Է��Ѵ�.    
     try { /* ����̹��� ã�� ���� */
       Class.forName("com.mysql.cj.jdbc.Driver");   
       System.out.println("����̹� �ε� ����");
     } catch(ClassNotFoundException e1) {
         e1.printStackTrace();
     }
// Class.forName()���� ����̹��� �ε��Ѵ�. ����̹� �̸��� Class.forName�� �Է��Ѵ�.      
   try { /* �����ͺ��̽��� �����ϴ� ���� */
       System.out.println("�����ͺ��̽� ���� �غ�...");
       con=DriverManager.getConnection(url, userid, pwd); 
       System.out.println("�����ͺ��̽� ���� ����");
     } catch(SQLException e1) {
         e1.printStackTrace();
       }
   }
// ���� ��ü con�� DriverManager.getConnection �Լ��� �����Ѵ�. 
// ������ �����ϸ� "�����ͺ��̽� ���� ����"�� ����ϵ��� �Ѵ�.  
// ���ڿ� query�� ������ SQL ���� �Է��Ѵ�.
   
   //action listener
   
   public void actionPerformed(ActionEvent e) {
      try {
         stmt1 = con.createStatement();
         //orders ���̺� ��ư
         if(e.getSource()==btnorder) {
            try {
            
               String query="SELECT * FROM orders"; /* SQL �� */
               //select������ orders���̺��� ���δ�.
               txtResult.setText("");
               txtResult.setText("orderid \tcustid \t bookid \t salprice \t orderdate");
               rs = stmt1.executeQuery(query);
               while(rs.next()) {
                  String str = "\n" + rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5)
                  + "\n";
                  txtResult.append(str);
               }
            }catch(Exception e1) {
               System.out.println("orders select ����!\n"+e1); 
               //������ ���� �ÿ� ���� ��¹��� ����.
            }
         }
         //book ���̺� ��ư
         else if(e.getSource()==btnbook) {
            try {
               String query="SELECT * FROM Book"; /* SQL �� */
               //select������ Book���̺��� ����.
               txtResult.setText("");
               txtResult.setText("Book no \tBook name\t Publisher \tPrice");
               rs2 = stmt1.executeQuery(query);
               while(rs2.next()) {
                  String str = "\n"+ rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getInt(4)
                   + "\n";
                  txtResult.append(str);
               }
            }catch(Exception e2) {
               System.out.println("book select ����!\n"+e2);
               //������ ���� �ÿ� ���� ��¹��� ����.
            }
         }
         //CUSTOMER ���̺� ��ư
         else if(e.getSource()==btnCUSTOMER) {
            
            try {
               String query="SELECT * FROM CUSTOMER"; /* SQL �� */
               //select������ customer���̺��� ����.
               txtResult.setText("");
               txtResult.setText("custid \tname \t address \t phone ");
               rs3 = stmt1.executeQuery(query);
               while(rs3.next()) {
                  String str = "\n"+ rs3.getInt(1) + "\t" + rs3.getString(2) + "\t" + rs3.getString(3) + "\t" + rs3.getString(4)
                  + "\n";
                  txtResult.append(str);
               }
            }catch(Exception e3) {
               System.out.println("CUSTOMER select ����!\n"+e3);
               //������ ���� �ÿ� ���� ��¹��� ����.
            }
         }
         //�Է� �� �ִ� ��ư
         else if(e.getSource()==orderinput) {
            try {
              String oid = orderid.getText();
              String cid = custid.getText();
              String bid = bookid.getText();
              String sid = saleprice.getText();
              String ord = orderdate.getText();
              String query="INSERT INTO Orders VALUES(" +oid+ "," +cid+ "," +bid+ "," +sid+ "," + "STR_TO_DATE('" + ord+ "'," + " '%Y-%m-%d' ))";
              stmt1.executeUpdate(query);
             //������ �ؽ�Ʈ���� ���´�.
            }catch(Exception ie) {
               System.out.println("������ ���� �߻�! �ߺ��̳� null�� �� ������ ���� �߻�!\n"+ie);
               //������ ���� �ÿ� ���� ��¹��� ����.
            }
         }
         
         else if(e.getSource()==btnReset) {
            txtResult.setText("");
            //ȭ�� �ʱ�ȭ ��ư�� ������ ȭ���� �ʱ�ȭ�Ѵ�.
         }
         
         else if(e.getSource()==btnir) {
            
            try {
            int size=0;
               String query="DELETE FROM orders";
               size=stmt1.executeUpdate(query);
               System.out.println("ORDER "+size+"��\n");
               query="DELETE FROM book";
               size=stmt1.executeUpdate(query);
               System.out.println("BOOK "+size+"��\n");
               query="DELETE FROM CUSTOMER";
               size=stmt1.executeUpdate(query);
               System.out.println("CUTOMER "+size+"��\n");
               System.out.println("�����Ϸ�");
            }catch(Exception D) {
               System.out.println("������ ���� ���� �߻�!\n"+D);
               //������ ���� �ÿ� ���� ��¹��� ����.
            }
            //�����͸� �����ϴ� SQL���̴�.
            
            try {
               String query="INSERT INTO Book VALUES(1, '�౸�� ����', '�½�����', 7000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(2, '�౸�ƴ� ����', '������', 13000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(3, '�౸�� ����', '���ѹ̵��', 22000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(4, '���� ���̺�', '���ѹ̵��', 35000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(5, '�ǰ� ����', '�½�����', 8000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(6, '���� �ܰ躰���', '�½�����', 6000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(7, '�߱��� �߾�', '�̻�̵��', 20000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(8, '�߱��� ��Ź��', '�̻�̵��', 13000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(9, '�ø��� �̾߱�', '�Ｚ��', 7500)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(10, 'Olympic Champions', 'Pearson', 13000)";
               stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(11, '���α׷����� ����', '���α׷���', 17000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(12, '��ȣ�� �ý���', '���ڻ�Ȱ', 12000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(13, '������ ����', '�̽�����', 7200)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(14, '��Ʃ�� �����ϱ�', '�������̸�Ʈ', 20000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(15, '��̻�Ȱ', '������Ȱ', 10000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(16, '���׿��극����', '������Ȱ', 15000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(17, 'ȭ��1', '�̺񿡽�', 18000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(18, '�̺а� ����', '�������ǻ�', 13000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(19, '��������', '�Ž��ø���', 21000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(20, '���������', '�Ž��ø���', 8000)";
                     stmt1.executeUpdate(query);

                     
                     
                     query="INSERT INTO CUSTOMER VALUES(1, '������', '���� ��ü��Ÿ', '000-5000-0001')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(2, '�迬��', '���ѹα� ����', '000-6000-0001')";  
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(3, '��̶�', '���ѹα� ������', '000-7000-0001')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(4, '�߽ż�', '�̱� Ŭ������', '000-8000-0001')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(5, '�ڼ���', '���ѹα� ����',  NULL)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(6, '������', '�̱� �ν���������', '006-7890-7610')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(7, '�����', '�غ����Ʒü�', '007-5048-0801')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(8, '�⼺��', '���ѹα� ���� ���ϱ�', '008-5780-4681')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(9, '����', '���ѹα� ���� ������', '009-8060-0611')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(10, '������', '���ѹα� ���� ������', '010-4501-6781')";
                     stmt1.executeUpdate(query);
                     
                     
                     query="INSERT INTO ORDERS VALUES(1, 1, 1, 6000, STR_TO_DATE('2014-07-01','%Y-%m-%d'))"; 
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(2, 1, 3, 21000, STR_TO_DATE('2014-07-03','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(3, 2, 5, 8000, STR_TO_DATE('2014-07-03','%Y-%m-%d'))"; 
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(4, 3, 6, 6000, STR_TO_DATE('2014-07-04','%Y-%m-%d'))"; 
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(5, 4, 7, 20000, STR_TO_DATE('2014-07-05','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(6, 1, 2, 12000, STR_TO_DATE('2014-07-07','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(7, 4, 8, 13000, STR_TO_DATE( '2014-07-07','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(8, 3, 10, 12000, STR_TO_DATE('2014-07-08','%Y-%m-%d'))"; 
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(9, 2, 10, 7000, STR_TO_DATE('2014-07-09','%Y-%m-%d'))"; 
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(10, 3, 8, 13000, STR_TO_DATE('2014-07-10','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(11, 1, 1, 12000, STR_TO_DATE('2019-07-09','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(12, 10, 4, 3000, STR_TO_DATE('2011-11-11','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(13, 4, 9, 4000, STR_TO_DATE('2017-11-25','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(14, 9, 10, 9000, STR_TO_DATE('2004-01-24','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(15, 10, 10, 12000, STR_TO_DATE('2007-01-27','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(16, 8, 8, 19000, STR_TO_DATE('2008-11-29','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(17, 9, 7, 18000, STR_TO_DATE('2006-08-31','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(18, 1, 2, 10000, STR_TO_DATE('2015-07-28','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(19, 8, 3, 11150, STR_TO_DATE('2014-09-14','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO ORDERS VALUES(20, 6, 8, 9000, STR_TO_DATE('2019-07-16','%Y-%m-%d'))";
                     stmt1.executeUpdate(query);
                     //�ʱ�ȭ ��ư�� ������ �����ǰ� INSERT������ �����͸� �����Ѵ�.
            }catch(Exception Insert) {
               System.out.println("������ �Է¿� ���� �߻�!\n"+Insert);
               //������ ���� �ÿ� ���� ��¹��� ����.
            }
         }
      }catch (Exception e2) {
         System.out.println("���� �б� ����"+e2);
      }
   }
   
     public static void main(String args[]) {
        BookListSwing BLS = new BookListSwing();
        BLS.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent we) {
             try {
                con.close();
             }catch(Exception e4) {
                System.out.println("���α׷� ���� ����!");
                System.exit(0);
             }
             System.exit(0);
          }
       });
     }
}