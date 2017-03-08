import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.util.*;
 
class Screen5 implements ActionListener,ItemListener
{
	JFrame frame;
	JTextField ordernum;
	JTextField customercode;
	JTextArea custaddress;
	JTextField payment,chqno,bank_name;
	JLabel orderstatus;
	JLabel on,cc,cad,pt,cn,bn;
	JPanel p1,p2,p3,p4,p5,p6,panel,panel1,panel2;
	Border blackline;
	JButton subbut;
	JScrollPane scrollpane;
	int count=0;
	ResultSet rs;
	JComboBox data;
	JLabel msg;

	String ostat;
int ono=0;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/sms?autoReconnect=true&useSSL=false";

   
   static final String USER = "root";
   static final String PASS = "hello123";
   
	Connection conn = null;
   Statement stmt = null;
	
	
	Screen5()
	{
		try{
      Class.forName("com.mysql.jdbc.Driver");

      
      System.out.println("Connecting to database...");
   	msg=new JLabel("");
	
	
	blackline = BorderFactory.createLineBorder(Color.black);
	subbut=new JButton("Update Form");	
	frame=new JFrame("Payment Received");
    ordernum=new JTextField();
	ordernum.setPreferredSize(new Dimension(300,36));
	ordernum.setEditable(false);
	customercode=new JTextField();
	customercode.setPreferredSize(new Dimension(300,36));
	customercode.setEditable(false);
	custaddress=new JTextArea();
	custaddress.setBorder(blackline);
    custaddress.setPreferredSize(new Dimension(300,72));
	custaddress.setEditable(false);
	custaddress.setLineWrap(true);
	custaddress.setWrapStyleWord(true);
	payment=new JTextField();
    payment.setPreferredSize(new Dimension(300,36));
    chqno=new JTextField();
    chqno.setPreferredSize(new Dimension(300,36));
    bank_name=new JTextField();
    bank_name.setPreferredSize(new Dimension(300,36));
    msg.setFont(new Font("Arial Black", Font.BOLD, 15));
	msg.setForeground(Color.red);
	msg.setAlignmentX(Component.RIGHT_ALIGNMENT);

    conn = DriverManager.getConnection(DB_URL,USER,PASS);
	Vector<String> al=new Vector<String>();
	String sql;
	stmt = conn.createStatement();
	sql = "select order_no from ORDER_HEADER where order_status=\"MACI\";";
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
	panel=new JPanel();
    scrollpane=new JScrollPane(panel);
		
	on=new JLabel("Order Number* :");
	cc=new JLabel("Customer Code  :");
	cad=new JLabel("Customer Address:");
    pt=new JLabel("Payment Received* :");
    cn=new JLabel("Cheque_no :");
    bn=new JLabel("Bank_name  :");
    
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	p4=new JPanel();
	p5=new JPanel();
	p6=new JPanel();
	p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p4.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p5.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p6.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p1.add(on);
	p1.add(ordernum);
	p2.add(cc);
	p2.add(customercode);
	p3.add(cad);
	p3.add(custaddress);
	p4.add(pt);
	p4.add(payment);
	p5.add(cn);
	p5.add(chqno);
	p6.add(bn);
	p6.add(bank_name);
	panel.setBorder(blackline);
	orderstatus=new JLabel("Order Status:MACI");
	orderstatus.setFont(new Font("Arial", Font.ITALIC, 40));
    frame.setResizable(false);
	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	panel.add(data);
	panel.add(msg);
	panel.add(p1);
	panel.add(p2);
	panel.add(p3);
	panel.add(p4);
	panel.add(p5);
	panel.add(p6);
	
	panel1.setLayout(new GridLayout(2,1));
	panel1.setSize(50,50);
    panel1.setBackground(Color.cyan);
	panel1.add(orderstatus);
	panel1.add(subbut);
	subbut.setFont(new Font("Arial", Font.ITALIC, 30));
	frame.setLayout(new GridLayout(3,1));
	frame.add(new JLabel(new ImageIcon("sms1.png")));
	frame.add(scrollpane);
	frame.add(panel1);
	frame.setVisible(true);
	frame.setSize(450,760);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
}catch(Exception eee){eee.printStackTrace();}
}
public static void main(String[] args) 
{
	new Screen5();
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

	if(count==1)
	{
	if(ostat.equals("MACI"))
	{
		String sql;
		sql = "update order_tracking set order_status=\"PYMR\" where order_no="+ordernum.getText();
	stmt.executeUpdate(sql);
	int payrec=Integer.parseInt(payment.getText().trim());
	sql="update ORDER_HEADER set order_status=\"PYMR\","+"cheque_no=\""+chqno.getText().trim()+"\",bank_name=\""+bank_name.getText().trim()+"\" where order_no="+ordernum.getText();
	stmt.executeUpdate(sql);
	orderstatus.setText("Order Status:PYMR");
	panel1.setBackground(Color.GREEN);subbut.setEnabled(false);
	
	}	
   else
	{
msg.setText("Machine not installed  yet.");
ordernum.setText("");
}
}
else
{
String dcc=ordernum.getText();	

String pcc=payment.getText();
int cno=0;
String sql;
if((dcc.matches("\\d{4}")||dcc.matches("\\d{3}")||dcc.matches("\\d{2}")||dcc.matches("\\d{1}")||dcc.matches("\\d{5}"))&&(pcc.matches("\\d{4}")||pcc.matches("\\d{3}")||pcc.matches("\\d{2}")||pcc.matches("\\d{1}")||pcc.matches("\\d{5}")))
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
	msg.setText("Order not valid");
	ordernum.requestFocusInWindow();
}


}
}catch(Exception le){
      le.printStackTrace();
   }
}

}		


}