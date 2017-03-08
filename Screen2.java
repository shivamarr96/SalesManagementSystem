import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
class Screen2 implements ActionListener,ItemListener
{
	JFrame frame;
	JTextArea	deliverychallan,shipmentdate,transportername;
	JTextField ordernumber;
	JLabel orderstatus;
	JLabel on,dc,sd,tn;
	JPanel p1,p2,p3,p4,panel,panel1,panel2;
	Border blackline;
	Date date;
	JButton subbut;
	JLabel msg;
	ResultSet rs;
	JComboBox data;
	JScrollPane scroll;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/sms?autoReconnect=true&useSSL=false";

   
   static final String USER = "root";
   static final String PASS = "hello123";
   
	Connection conn = null;
   Statement stmt = null;
	String prdate;
	Screen2()
	{
	try{
      Class.forName("com.mysql.jdbc.Driver");

      
      System.out.println("Connecting to database...");
   	msg=new JLabel("");
	blackline = BorderFactory.createLineBorder(Color.black);
	date=new Date();
	frame=new JFrame("Shipment");
	subbut=new JButton("Submit");
	panel1=new JPanel();
	panel2=new JPanel();
	ordernumber=new JTextField();
	ordernumber.setEditable(false);
	ordernumber.setPreferredSize(new Dimension(220,40));
	prdate=Integer.toString(date.getYear()+1900)+"-"+Integer.toString(date.getMonth())+"-"+Integer.toString(date.getDate());
	shipmentdate=new JTextArea(prdate);
	shipmentdate.setEditable(false);
	shipmentdate.setPreferredSize(new Dimension(220,40));
	int random = (int)(Math.random()*29999 + 1);
	deliverychallan=new JTextArea(Integer.toString(random));
	deliverychallan.setPreferredSize(new Dimension(220,40));
	deliverychallan.setEditable(false);
	transportername=new JTextArea();
	transportername.setPreferredSize(new Dimension(220,40));
	transportername.setLineWrap(true);
	transportername.setWrapStyleWord(true);
	subbut.addActionListener(this);

	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	Vector<String> al=new Vector<String>();
	String sql;
	stmt = conn.createStatement();
	sql = "select order_no from ORDER_HEADER where order_status=\"SCHD\";";
	rs = stmt.executeQuery(sql);
      while(rs.next())
      {
      int onn=rs.getInt("order_no");
      String tempon=Integer.toString(onn);
      al.add(tempon);
  }
	data= new JComboBox(al);
	data.addItemListener(this);
	
		
	on=new JLabel("Order Number :");
	dc=new JLabel("Delivery Challan No :");
	sd=new JLabel("Shipment Date :");
    tn=new JLabel("Transporter Name :");

	subbut.setFont(new Font("", Font.ITALIC, 25));
	ordernumber.setFont(new Font("Arial", Font.PLAIN, 22));
	ordernumber.setBorder(blackline);
	deliverychallan.setFont(new Font("Arial", Font.PLAIN, 20));
	deliverychallan.setBorder(blackline);
	shipmentdate.setFont(new Font("Arial", Font.PLAIN, 20));
	shipmentdate.setBorder(blackline);
	transportername.setFont(new Font("Arial", Font.PLAIN, 17));
	transportername.setBorder(blackline);
	msg.setFont(new Font("Arial Black", Font.BOLD, 15));
	msg.setForeground(Color.red);
	msg.setAlignmentX(Component.RIGHT_ALIGNMENT);

    panel=new JPanel();

	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	p4=new JPanel();
	p1.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p4.setLayout(new FlowLayout(FlowLayout.RIGHT));
	p1.add(on);
	p1.add(ordernumber);
	p2.add(dc);
	p2.add(deliverychallan);
	p3.add(sd);
	p3.add(shipmentdate);
	p4.add(tn);
	p4.add(transportername);
	panel.setBorder(blackline);
	orderstatus=new JLabel("Order Status:SCHD");
	orderstatus.setFont(new Font("Arial", Font.ITALIC, 40));
    frame.setResizable(false);
	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	panel.add(data);
	panel.add(msg);
	panel.add(p1);
	panel.add(p2);
	panel.add(p3);
	panel.add(p3);
	panel.add(p4);

   	scroll=new JScrollPane(panel);
	panel1.setLayout(new GridLayout(2,1));
	panel1.setSize(25,25);
	panel1.setBackground(Color.cyan);
	panel1.add(orderstatus);
	panel1.add(subbut);
	subbut.setFont(new Font("Arial", Font.ITALIC, 30));
	frame.setLayout(new GridLayout(3,1));
	frame.add(new JLabel(new ImageIcon("sms1.png")));
	frame.add(scroll);
	frame.add(panel1);
	frame.setVisible(true);
	frame.setSize(400,700);
	
}catch(Exception eee){eee.printStackTrace();}
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
public  void itemStateChanged(ItemEvent  e)
{
      Object item = e.getItem();
      if (e.getStateChange() == ItemEvent.SELECTED) {
        ordernumber.setText((String)item);
        //System.out.println(item + "  has  been  selected");
      } 
    }

public void actionPerformed(ActionEvent e)
{
if(e.getSource()==subbut)
{
if(transportername.getText().equals("")&&ordernumber.getText().equals(""))
{

msg.setText("Complete Form.");
}
else if(transportername.getText().equals(""))
	{
msg.setText("*Transporter Name");

	}
else if(ordernumber.getText().trim().equals(""))
{

msg.setText("*Order Number");

}
else{
try{
	msg.setText(" ");
	stmt = conn.createStatement();
      String sql;
	
      int o=Integer.parseInt(ordernumber.getText().trim());
      String dch=deliverychallan.getText().trim();
	String d="\""+shipmentdate.getText().trim()+"\"";
	String tname="\""+transportername.getText().trim()+"\"";

	sql="select * from order_tracking where order_no="+ordernumber.getText().trim();
	String temp="";
      rs = stmt.executeQuery(sql);
      while(rs.next())
      {  	temp=rs.getString("order_status");   	
        }
	 if(temp.equals("SCHD"))
         {
         	
   sql = "update order_tracking set order_status=\"SHIP\" where order_no="+ordernumber.getText().trim();
	stmt.executeUpdate(sql);
	sql="update ORDER_HEADER set order_status=\"SHIP\",delivery_challan_no="+dch+",transporter_name="+tname+",shipment_date=\""+prdate+"\" where order_no="+ordernumber.getText().trim();
	stmt.executeUpdate(sql);
	orderstatus.setText("Order Status:SHIP");
panel1.setBackground(Color.GREEN);subbut.setEnabled(false);

}
else
{
msg.setText("Order not valid.");
ordernumber.setText("");
transportername.setText("");
}
	}catch(Exception le){
      le.printStackTrace();
   }finally{
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }

	//try{Thread.sleep(5000);}catch(Exception ef){}
	//frame.dispose();	
}	
}
}
public static void main(String[] args) 
{
	new Screen2();
}
		
}