import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

 
class Screen3 implements ActionListener
{
	JFrame frame;
	JTextField deliverychallan;
	JTextArea customercode;
	JTextArea custaddress,invoicenumber,invoicevalue;
	JLabel orderstatus;
	JLabel dc,cc,cad,in,iv;
	JPanel p1,p2,p3,p4,p5,panel,panel1,panel2,finpane;
	Border blackline;
	Date date;
	JButton subbut;
	JScrollPane scrolpane;
	JLabel msg;
	int count=0;
	String ostat="";
int ono=0;
	
	ResultSet rs;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/sms?autoReconnect=true&useSSL=false";

   
   static final String USER = "root";
   static final String PASS = "hello123";
   
	Connection conn = null;
   Statement stmt = null;
	
	
	Screen3()
	{
try{
      Class.forName("com.mysql.jdbc.Driver");

      
      System.out.println("Connecting to database...");
   	
	msg=new JLabel("");
	
	blackline = BorderFactory.createLineBorder(Color.black);	
	subbut=new JButton("Update Form");	
	date=new Date();
	frame=new JFrame("Invoice Generation(Accounts Dept.)");
    deliverychallan=new JTextField();
	deliverychallan.setPreferredSize(new Dimension(220,36));
	customercode=new JTextArea();
	customercode.setPreferredSize(new Dimension(220,36));
	customercode.setEditable(false);
	custaddress=new JTextArea();
    custaddress.setPreferredSize(new Dimension(220,72));
	custaddress.setEditable(false);
	custaddress.setLineWrap(true);
	custaddress.setWrapStyleWord(true);
	int random = (int)(Math.random()*29999 + 1);
	invoicenumber=new JTextArea(Integer.toString(random));
    invoicenumber.setPreferredSize(new Dimension(220,36));
    invoicenumber.setEditable(false);
    invoicevalue=new JTextArea();
    invoicevalue.setPreferredSize(new Dimension(220,36));
	invoicevalue.setEditable(false);
	subbut.addActionListener(this);
	panel1=new JPanel();
	panel2=new JPanel();
	
		
	dc=new JLabel("Delivery Challan No :");
	cc=new JLabel("Customer Code  :");
	cad=new JLabel("Customer Address:");
    in=new JLabel("Invoice Number :");
	iv=new JLabel("Invoice Value :");
    panel=new JPanel();
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	p4=new JPanel();
	p5=new JPanel();
	FlowLayout fl=new FlowLayout(FlowLayout.RIGHT);
	//fl.setVgap(15);
	p1.setLayout(fl);
	p2.setLayout(fl);
	p3.setLayout(fl);
	p4.setLayout(fl);
	p5.setLayout(fl);
	p1.add(dc);
	p1.add(deliverychallan);
	deliverychallan.setBorder(blackline);
	p2.add(cc);
	p2.add(customercode);
	customercode.setBorder(blackline);
	p3.add(cad);
	p3.add(custaddress);
	custaddress.setBorder(blackline);
	p4.add(in);
	p4.add(invoicenumber);
	invoicenumber.setBorder(blackline);
	p5.add(iv);
	p5.add(invoicevalue);
	invoicevalue.setBorder(blackline);
	panel.setBorder(blackline);
	orderstatus=new JLabel("Order Status:SHIP");
	orderstatus.setFont(new Font("Arial", Font.ITALIC, 40));
	msg.setFont(new Font("Arial Black", Font.BOLD, 15));
	msg.setForeground(Color.red);
	msg.setAlignmentX(Component.RIGHT_ALIGNMENT);

    
	frame.setResizable(false);
	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	panel.add(msg);
	panel.add(p1);
	panel.add(p2);
	panel.add(p3);
	panel.add(p3);
	panel.add(p4);
	panel.add(p5);
	
	panel1.setLayout(new GridLayout(2,1));
	panel1.setSize(50,50);
    panel1.setBackground(Color.cyan);
	panel1.add(orderstatus);
	panel1.add(subbut);
	subbut.setFont(new Font("Arial", Font.ITALIC, 30));
	finpane=new JPanel();
	scrolpane=new JScrollPane(panel);
	finpane.setLayout(new GridLayout(3,1));
	finpane.add(new JLabel(new ImageIcon("sms1.png")));
	finpane.add(scrolpane);
	finpane.add(panel1);
	frame.setLayout(new GridLayout(1,1));
	frame.add(finpane);
	frame.setVisible(true);
	frame.setSize(450,760);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	
}catch(Exception eee){eee.printStackTrace();}
}
public static void main(String[] args) 
{
	new Screen3();
}
public void actionPerformed(ActionEvent e)
{

if(e.getSource()==subbut)
{	try{
stmt = conn.createStatement();

	if(count==1)
	{
	if(ostat.equals("SHIP"))
	{
		String sql;
		sql = "update order_tracking set order_status=\"INVG\" where order_no="+Integer.toString(ono);
	stmt.executeUpdate(sql);
	String dd="\""+Integer.toString(date.getYear()+1900)+"-"+Integer.toString(date.getMonth())+"-"+Integer.toString(date.getDate())+"\"";
	sql="update ORDER_HEADER set order_status=\"INVG\","+"invoice_number="+invoicenumber.getText().trim()+",invoice_date="+dd+"where order_no="+Integer.toString(ono);
	stmt.executeUpdate(sql);
	orderstatus.setText("Order Status:INVG");
	panel1.setBackground(Color.GREEN);subbut.setEnabled(false);
	}	
	else
	{
msg.setText("Order not valid.");
deliverychallan.setText("");
}
   
}
else
{
String dcc=deliverychallan.getText();	
if(dcc.matches("\\d{4}")||dcc.matches("\\d{3}")||dcc.matches("\\d{2}")||dcc.matches("\\d{1}")||dcc.matches("\\d{5}"))
{
msg.setText("");	

int oval=0,cno=0;
String sql;
      sql = "select order_no,delivery_challan_no,order_status,order_value,customer_slno from ORDER_HEADER where delivery_challan_no="+deliverychallan.getText().trim();

     rs = stmt.executeQuery(sql);
      while(rs.next())
      {  
      	ono=rs.getInt("order_no");
      	ostat=rs.getString("order_status");
         oval=rs.getInt("order_value");
         cno=rs.getInt("customer_slno");
         invoicevalue.setText(Integer.toString(oval));
         customercode.setText(Integer.toString(cno));
               }
               if(ono==0)
               {
msg.setText("Order not valid.");
deliverychallan.setText("");
return;
               }
               sql="select * from customer_master where cust_slno="+Integer.toString(cno);
 rs = stmt.executeQuery(sql);
      while(rs.next())
      {  
      	custaddress.append(rs.getString("cust_name")+",");
         custaddress.append(rs.getString("cust_add1")+",");
         custaddress.append(" "+rs.getString("cust_add2")+",");
         custaddress.append(" "+rs.getString("cust_add3")+",");
         custaddress.append(" "+rs.getString("cust_city"));
              }
 subbut.setText("Submit");
 count=1;
}
else
{
	msg.setText("Enter Delivery_challan_no.");
}

}
}catch(Exception le){
      le.printStackTrace();
   }
}

}		


}