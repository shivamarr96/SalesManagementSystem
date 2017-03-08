import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.util.*;


 
class Screen6 implements ActionListener,ItemListener
{
	JFrame frame;
	JTextField ordernum;
	JTextArea customercode;
	JTextArea custaddress;
	JLabel orderstatus;
	JLabel on,cc,cad;
	JPanel p1,p2,p3,panel,panel1,panel2;
	Border blackline;
	JButton subbut;
	JScrollPane scrollpanel;
	ResultSet rs;
	JLabel msg;
	JComboBox data;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/sms?autoReconnect=true&useSSL=false";

   
   static final String USER = "root";
   static final String PASS = "hello123";
   
	Connection conn = null;
   Statement stmt = null;

	Screen6()
	{
	try{
      Class.forName("com.mysql.jdbc.Driver");

      
      System.out.println("Connecting to database...");
   	
		msg=new JLabel("");
	subbut=new JButton("Check Status");	
	blackline = BorderFactory.createLineBorder(Color.black);
	frame=new JFrame("Order Enquiry");
    ordernum=new JTextField();
	ordernum.setPreferredSize(new Dimension(300,36));
	ordernum.setBorder(blackline);
	ordernum.setEditable(false);
	ordernum.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
	customercode=new JTextArea();
	customercode.setBorder(blackline);
	customercode.setPreferredSize(new Dimension(300,36));
	customercode.setEditable(false);
	custaddress=new JTextArea();
    custaddress.setBorder(blackline);
    custaddress.setPreferredSize(new Dimension(300,72));
	custaddress.setEditable(false);
	custaddress.setLineWrap(true);
	custaddress.setWrapStyleWord(true);
	msg.setFont(new Font("Arial Black", Font.BOLD, 15));
	msg.setForeground(Color.red);
	msg.setAlignmentX(Component.RIGHT_ALIGNMENT);

	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	Vector<String> al=new Vector<String>();
	String sql;
	stmt = conn.createStatement();
	sql = "select order_no from ORDER_HEADER;";
	rs = stmt.executeQuery(sql);
      while(rs.next())
      {
      int onn=rs.getInt("order_no");
      String tempon=Integer.toString(onn);
      al.add(tempon);
  }
	data= new JComboBox(al);
	data.addItemListener(this);
	
	subbut.addActionListener(this);
	panel1=new JPanel();
	panel2=new JPanel();
	
		
	on=new JLabel("Order Number* :");
	cc=new JLabel("Customer Code  :");
	cad=new JLabel("Customer Address:");
    panel=new JPanel();
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p1.add(on);
	p1.add(ordernum);
	p2.add(cc);
	p2.add(customercode);
	p3.add(cad);
	p3.add(custaddress);
	panel.setBorder(blackline);
	orderstatus=new JLabel("Order Status:");
	orderstatus.setFont(new Font("Arial", Font.ITALIC, 25));
    frame.setResizable(false);
	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	panel.add(data);
	panel.add(msg);
	panel.add(p1);
	panel.add(p2);
	panel.add(p3);
	panel.add(p3);
	scrollpanel=new JScrollPane(panel);
	panel1.setLayout(new GridLayout(2,1));
	panel1.setSize(50,50);
    panel1.setBackground(Color.cyan);
	panel1.add(orderstatus);
	panel1.add(subbut);
	frame.setLayout(new GridLayout(3,1));
	frame.add(new JLabel(new ImageIcon("sms1.png")));
	frame.add(scrollpanel);
	frame.add(panel1);
	frame.setVisible(true);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
}catch(Exception eee){eee.printStackTrace();}

}
public static void main(String[] args) 
{
	new Screen6();
}
public  void itemStateChanged(ItemEvent  e)
{
      Object item = e.getItem();
      if (e.getStateChange() == ItemEvent.SELECTED) {
        ordernum.setText((String)item);
        //System.out.println(item + "  has  been  selected");
      } 
    }
		
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==subbut)
{	try{
stmt = conn.createStatement();
String dcc=ordernum.getText();
	String ostat="";
int ono=0;
int cno=0;
String sql;
if(dcc.matches("\\d{4}")||dcc.matches("\\d{3}")||dcc.matches("\\d{2}")||dcc.matches("\\d{1}")||dcc.matches("\\d{5}"))
{
msg.setText("");	
      sql = "select order_no,order_status,customer_slno from ORDER_HEADER where order_no="+ordernum.getText().trim();

     rs = stmt.executeQuery(sql);
      while(rs.next())
      {
        ono=rs.getInt("order_no");
      	ostat=rs.getString("order_status");
         cno=rs.getInt("customer_slno");
         customercode.setText(Integer.toString(cno));
               }
               if(ono==0)
               {
msg.setText("Order no. not valid.");
ordernum.setText("");
ordernum.requestFocusInWindow();
customercode.setText("");
return;
               }
               sql="select * from customer_master where cust_slno="+Integer.toString(cno);
 rs = stmt.executeQuery(sql);
      while(rs.next())
      { custaddress.setText(""); 
      	custaddress.append(rs.getString("cust_name")+",");
         custaddress.append(rs.getString("cust_add1")+",");
         custaddress.append(" "+rs.getString("cust_add2")+",");
         custaddress.append(" "+rs.getString("cust_add3")+",");
         custaddress.append(" "+rs.getString("cust_city"));
              }
    orderstatus.setText("Order Status: "+ostat.trim());
	panel1.setBackground(Color.GREEN);
}
	else
{
	msg.setText("Enter Order No.");
	ordernum.requestFocusInWindow();
}

}catch(Exception le)
	{le.printStackTrace();}
}

}		


}