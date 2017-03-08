import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

 
class Screen4 implements ActionListener
{
	JFrame frame;
	JTextField deliverychallan;
	JTextArea customercode;
	JTextArea custaddress;
	JTextField custengname;
	JLabel orderstatus;
	JLabel dc,cc,cad,cen;
	JPanel p1,p2,p3,p4,panel,panel1,panel2;
	Border blackline;
	JButton subbut;
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
	
	
	Screen4()
	{
		try{
      Class.forName("com.mysql.jdbc.Driver");

      
      System.out.println("Connecting to database...");
   	
     msg=new JLabel("");
	
	subbut=new JButton("Update Form");	
	blackline = BorderFactory.createLineBorder(Color.black);
	frame=new JFrame("Machine Installation");
    deliverychallan=new JTextField();
	deliverychallan.setPreferredSize(new Dimension(300,36));
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
	custengname=new JTextField();
    custengname.setPreferredSize(new Dimension(300,36));
    msg.setFont(new Font("Arial Black", Font.BOLD, 15));
	msg.setForeground(Color.red);
	msg.setAlignmentX(Component.RIGHT_ALIGNMENT);

    
    subbut.addActionListener(this);
	panel1=new JPanel();
	panel2=new JPanel();
	
		
	dc=new JLabel("Delivery Challan No :");
	cc=new JLabel("Customer Code  :");
	cad=new JLabel("Customer Address:");
    cen=new JLabel("Customer Engineer Name :");
    panel=new JPanel();
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	p4=new JPanel();
	p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p4.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p1.add(dc);
	p1.add(deliverychallan);
	p2.add(cc);
	p2.add(customercode);
	p3.add(cad);
	p3.add(custaddress);
	p4.add(cen);
	p4.add(custengname);
	panel.setBorder(blackline);
	orderstatus=new JLabel("Order Status:INVG");
	orderstatus.setFont(new Font("Arial", Font.ITALIC, 40));
    frame.setResizable(false);
	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	panel.add(msg);
	panel.add(p1);
	panel.add(p2);
	panel.add(p3);
	panel.add(p3);
	panel.add(p4);
	
	panel1.setLayout(new GridLayout(2,1));
	panel1.setSize(50,50);
    panel1.setBackground(Color.cyan);
	panel1.add(orderstatus);
	panel1.add(subbut);
	subbut.setFont(new Font("Arial", Font.ITALIC, 30));
	frame.setLayout(new GridLayout(3,1));
	frame.add(new JLabel(new ImageIcon("sms1.png")));
	frame.add(panel);
	frame.add(panel1);
	frame.setVisible(true);
	frame.pack();
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
conn = DriverManager.getConnection(DB_URL,USER,PASS);
	
}catch(Exception eee){eee.printStackTrace();}
}
public static void main(String[] args) 
{
	new Screen4();
}
		
public void actionPerformed(ActionEvent e)
{

if(e.getSource()==subbut)
{	try{
stmt = conn.createStatement();

	
	if(count==1)
	{
	if(ostat.equals("INVG"))
	{
		String sql;
		sql = "update order_tracking set order_status=\"MACI\" where order_no="+Integer.toString(ono);
	stmt.executeUpdate(sql);
	sql="update ORDER_HEADER set order_status=\"MACI\","+"machine_installed_by=\""+custengname.getText().trim()+"\" where order_no="+Integer.toString(ono);
	stmt.executeUpdate(sql);
	 orderstatus.setText("Order Status:MACI");
	panel1.setBackground(Color.GREEN);
	subbut.setEnabled(false);

	}	
  	else
	{
msg.setText("Invoice not generated yet.");
deliverychallan.setText("");
custengname.setText("");
deliverychallan.requestFocusInWindow();
}
}
else
{

int cno=0;
String dcc=deliverychallan.getText();	
if((dcc.matches("\\d{4}")||dcc.matches("\\d{3}")||dcc.matches("\\d{2}")||dcc.matches("\\d{1}")||dcc.matches("\\d{5}"))&&!custengname.getText().trim().equals(""))
{
msg.setText("");	

String sql;
      sql = "select order_no,delivery_challan_no,order_status,customer_slno from ORDER_HEADER where delivery_challan_no="+deliverychallan.getText().trim();

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
msg.setText("Order not valid.");
custengname.setText("");
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
	msg.setText("Complete missing fields.");
}
}
}catch(Exception le){
      le.printStackTrace();
   }
}

}		


}