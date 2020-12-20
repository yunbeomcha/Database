import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*; 
import java.io.*; 
import java.sql.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
// SQL 관련 클래스는 java.sql .*에 포함되어 있다.
// 스윙 관련 클래스는 javax.swing.*에 포함되어 있다.
// 레이아웃, 이벤트 등 java.awt.등등 포함되어있다.
public class BookListSwing extends JFrame implements ActionListener{
   private static final String String = null;
   JTextField orderid,custid,bookid,saleprice,orderdate; //text필드 선언
   JButton btnbook, btnReset,btnorder, btnCUSTOMER, orderinput, btnir,btnAdd; //버튼 생성
   JTextArea txtResult;
   JPanel p1, p2,p3,p4,p5,p6,p7, panWest,panSouth, input; //패널
   static Connection con;
   Statement stmt1;
   ResultSet rs,rs2,rs3,rs4;
   String Driver="";
   String url="jdbc:mysql://localhost:3306/madang?&serverTimezone=Asia/Seoul&useSSL=false"; 
   String userid="madang";
   String pwd="madang";
private EventObject e;
// 클래스 booklist를 선언한다. java.sql의 Connection 객체 con을 선언한다.     
   public BookListSwing() {
      super("18013189 차윤범"); //윈도우 학번 이름
      layInit();
      conDB();
      setVisible(true);
      setBounds(500,500,700,700);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
   public void layInit() {
      btnbook = new JButton ("검색1");
      btnorder = new JButton ("검색2");
      btnCUSTOMER = new JButton ("검색3");
      btnReset = new JButton("화면 초기화");
      btnir = new JButton("초기화");
      orderinput = new JButton ("입력1");
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
       //orderid,bookid, custid, saleprice, orderdate 텍스트 필드를 만들어준다.
       
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
// 접속변수를 초기화한다. url은 자바 드라이버 이름, 호스트명(localhost), 포트번호를 입력한다
// userid는 관리자(madang), pwd는 사용자의 비밀번호(madang)를 입력한다.    
     try { /* 드라이버를 찾는 과정 */
       Class.forName("com.mysql.cj.jdbc.Driver");   
       System.out.println("드라이버 로드 성공");
     } catch(ClassNotFoundException e1) {
         e1.printStackTrace();
     }
// Class.forName()으로 드라이버를 로딩한다. 드라이버 이름을 Class.forName에 입력한다.      
   try { /* 데이터베이스를 연결하는 과정 */
       System.out.println("데이터베이스 연결 준비...");
       con=DriverManager.getConnection(url, userid, pwd); 
       System.out.println("데이터베이스 연결 성공");
     } catch(SQLException e1) {
         e1.printStackTrace();
       }
   }
// 접속 객체 con을 DriverManager.getConnection 함수로 생성한다. 
// 접속이 성공하면 "데이터베이스 연결 성공"을 출력하도록 한다.  
// 문자열 query에 수행할 SQL 문을 입력한다.
   
   //action listener
   
   public void actionPerformed(ActionEvent e) {
      try {
         stmt1 = con.createStatement();
         //orders 테이블 버튼
         if(e.getSource()==btnorder) {
            try {
            
               String query="SELECT * FROM orders"; /* SQL 문 */
               //select문으로 orders테이블을 보인다.
               txtResult.setText("");
               txtResult.setText("orderid \tcustid \t bookid \t salprice \t orderdate");
               rs = stmt1.executeQuery(query);
               while(rs.next()) {
                  String str = "\n" + rs.getInt(1) + "\t" + rs.getInt(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4) + "\t" + rs.getString(5)
                  + "\n";
                  txtResult.append(str);
               }
            }catch(Exception e1) {
               System.out.println("orders select 오류!\n"+e1); 
               //에러가 났을 시에 오류 출력문을 띄운다.
            }
         }
         //book 테이블 버튼
         else if(e.getSource()==btnbook) {
            try {
               String query="SELECT * FROM Book"; /* SQL 문 */
               //select문으로 Book테이블을 띄운다.
               txtResult.setText("");
               txtResult.setText("Book no \tBook name\t Publisher \tPrice");
               rs2 = stmt1.executeQuery(query);
               while(rs2.next()) {
                  String str = "\n"+ rs2.getInt(1) + "\t" + rs2.getString(2) + "\t" + rs2.getString(3) + "\t" + rs2.getInt(4)
                   + "\n";
                  txtResult.append(str);
               }
            }catch(Exception e2) {
               System.out.println("book select 오류!\n"+e2);
               //에러가 났을 시에 오류 출력문을 띄운다.
            }
         }
         //CUSTOMER 테이블 버튼
         else if(e.getSource()==btnCUSTOMER) {
            
            try {
               String query="SELECT * FROM CUSTOMER"; /* SQL 문 */
               //select문으로 customer테이블을 띄운다.
               txtResult.setText("");
               txtResult.setText("custid \tname \t address \t phone ");
               rs3 = stmt1.executeQuery(query);
               while(rs3.next()) {
                  String str = "\n"+ rs3.getInt(1) + "\t" + rs3.getString(2) + "\t" + rs3.getString(3) + "\t" + rs3.getString(4)
                  + "\n";
                  txtResult.append(str);
               }
            }catch(Exception e3) {
               System.out.println("CUSTOMER select 오류!\n"+e3);
               //에러가 났을 시에 오류 출력문을 띄운다.
            }
         }
         //입력 값 넣는 버튼
         else if(e.getSource()==orderinput) {
            try {
              String oid = orderid.getText();
              String cid = custid.getText();
              String bid = bookid.getText();
              String sid = saleprice.getText();
              String ord = orderdate.getText();
              String query="INSERT INTO Orders VALUES(" +oid+ "," +cid+ "," +bid+ "," +sid+ "," + "STR_TO_DATE('" + ord+ "'," + " '%Y-%m-%d' ))";
              stmt1.executeUpdate(query);
             //선언한 텍스트들을 얻어온다.
            }catch(Exception ie) {
               System.out.println("데이터 오류 발생! 중복이나 null값 등 예외적 오류 발생!\n"+ie);
               //에러가 났을 시에 오류 출력문을 띄운다.
            }
         }
         
         else if(e.getSource()==btnReset) {
            txtResult.setText("");
            //화면 초기화 버튼을 누르면 화면을 초기화한다.
         }
         
         else if(e.getSource()==btnir) {
            
            try {
            int size=0;
               String query="DELETE FROM orders";
               size=stmt1.executeUpdate(query);
               System.out.println("ORDER "+size+"행\n");
               query="DELETE FROM book";
               size=stmt1.executeUpdate(query);
               System.out.println("BOOK "+size+"행\n");
               query="DELETE FROM CUSTOMER";
               size=stmt1.executeUpdate(query);
               System.out.println("CUTOMER "+size+"행\n");
               System.out.println("삭제완료");
            }catch(Exception D) {
               System.out.println("데이터 삭제 오류 발생!\n"+D);
               //에러가 났을 시에 오류 출력문을 띄운다.
            }
            //데이터를 삭제하는 SQL문이다.
            
            try {
               String query="INSERT INTO Book VALUES(1, '축구의 역사', '굿스포츠', 7000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(2, '축구아는 여자', '나무수', 13000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(3, '축구의 이해', '대한미디어', 22000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(4, '골프 바이블', '대한미디어', 35000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(5, '피겨 교본', '굿스포츠', 8000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(6, '역도 단계별기술', '굿스포츠', 6000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(7, '야구의 추억', '이상미디어', 20000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(8, '야구를 부탁해', '이상미디어', 13000)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(9, '올림픽 이야기', '삼성당', 7500)";
               stmt1.executeUpdate(query);
               query="INSERT INTO Book VALUES(10, 'Olympic Champions', 'Pearson', 13000)";
               stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(11, '프로그래밍의 역사', '프로그래머', 17000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(12, '신호와 시스템', '전자생활', 12000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(13, '게임의 정석', '이스포츠', 7200)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(14, '유튜브 시작하기', '엔터테이먼트', 20000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(15, '취미생활', '여가생활', 10000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(16, '리그오브레전드', '여가생활', 15000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(17, '화학1', '이비에스', 18000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(18, '미분과 적분', '수학출판사', 13000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(19, '공업수학', '매스컬리지', 21000)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO Book VALUES(20, '선형대수학', '매스컬리지', 8000)";
                     stmt1.executeUpdate(query);

                     
                     
                     query="INSERT INTO CUSTOMER VALUES(1, '박지성', '영국 맨체스타', '000-5000-0001')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(2, '김연아', '대한민국 서울', '000-6000-0001')";  
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(3, '장미란', '대한민국 강원도', '000-7000-0001')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(4, '추신수', '미국 클리블랜드', '000-8000-0001')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(5, '박세리', '대한민국 대전',  NULL)";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(6, '류현진', '미국 로스앤젤레스', '006-7890-7610')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(7, '손흥민', '해병대훈련소', '007-5048-0801')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(8, '기성용', '대한민국 서울 강북구', '008-5780-4681')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(9, '김우빈', '대한민국 서울 강남구', '009-8060-0611')";
                     stmt1.executeUpdate(query);
                     query="INSERT INTO CUSTOMER VALUES(10, '이종석', '대한민국 서울 강서구', '010-4501-6781')";
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
                     //초기화 버튼을 누르면 삭제되고 INSERT문으로 데이터를 삽입한다.
            }catch(Exception Insert) {
               System.out.println("데이터 입력에 오류 발생!\n"+Insert);
               //에러가 났을 시에 오류 출력문을 띄운다.
            }
         }
      }catch (Exception e2) {
         System.out.println("쿼리 읽기 실패"+e2);
      }
   }
   
     public static void main(String args[]) {
        BookListSwing BLS = new BookListSwing();
        BLS.addWindowListener(new WindowAdapter() {
          public void windowClosing(WindowEvent we) {
             try {
                con.close();
             }catch(Exception e4) {
                System.out.println("프로그램 완전 종료!");
                System.exit(0);
             }
             System.exit(0);
          }
       });
     }
}