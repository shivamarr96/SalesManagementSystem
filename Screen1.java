import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
import java.io.*;
import java.util.Date;
import java.sql.*;
import javax.swing.table.DefaultTableModel;


class Screen1 implements ActionListener
{
	JFrame frame;
	JTextField	ordercrdate,orderstatus;
	JTextField ordernumber;
	JTextField customercode;
	JTextField	customerrefdate;
	JTextField customername,custpin,custcity;
	JTextArea custaddress;
	JTextField	ordervalue;
	JTextArea shippingplant;
	JLabel on,ocd,os,cc,crd,cn,cad,cp,ccy,ov,sp,msg;
	JPanel p1,p2,p3,p4,p5,p6,panel;
	Border blackline;	
	ResultSet rs,rs1;
	JTable tbl;
	int count=0,flag1=-1,flag2=-1;
	float sum=0.0f;
	DefaultTableModel dtm ;
	JButton subbut,complete,addbut;
	int flag=1;
	Date date;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost/sms?autoReconnect=true&useSSL=false";

   
   static final String USER = "root";
   static final String PASS = "hello123";
   
	Connection conn = null;
   Statement stmt = null;
	Screen1()
	{
   
      try{
      Class.forName("com.mysql.jdbc.Driver");

      
      System.out.println("Connecting to database...");
   	conn = DriverManager.getConnection(DB_URL,USER,PASS);
	date=new Date();
	frame=new JFrame("Order Entry");
	subbut=new JButton("Place Order");
	complete=new JButton("Update Order");
	addbut=new JButton("Add Item");
	int random = (int)(Math.random()*26999 + 1);
	ordernumber=new JTextField(Integer.toString(random));
	ordernumber.setPreferredSize(new Dimension(220,25));
	ordernumber.setEditable(false);
	String prdate=Integer.toString(date.getYear()+1900)+"-"+Integer.toString(date.getMonth())+"-"+Integer.toString(date.getDate());
	ordercrdate=new JTextField(prdate);
	ordercrdate.setEditable(false);
	ordercrdate.setPreferredSize(new Dimension(220,25));
	orderstatus=new JTextField("BLOC");
	orderstatus.setPreferredSize(new Dimension(220,25));
	orderstatus.setEditable(false);
	customercode=new JTextField();
	 customercode.setPreferredSize(new Dimension(220,25));
	customerrefdate=new JTextField("YYYY-MM-DD");
	 customerrefdate.setPreferredSize(new Dimension(220,25));
	customername=new JTextField();
	customername.setEditable(false);
	 customername.setPreferredSize(new Dimension(220,25));
	custaddress=new JTextArea();
	custaddress.setEditable(false);
	 custaddress.setPreferredSize(new Dimension(220,55));
	 custaddress.setLineWrap(true);
	custaddress.setWrapStyleWord(true);
		
	custpin=new JTextField();
	 custpin.setPreferredSize(new Dimension(220,25));
	 custpin.setEditable(false);
	custcity=new JTextField();
	custcity.setEditable(false);
	 custcity.setPreferredSize(new Dimension(220,25));
	ordervalue=new JTextField();
	 ordervalue.setPreferredSize(new Dimension(220,25));
	 ordervalue.setEditable(false);
	shippingplant=new JTextArea();
	 shippingplant.setPreferredSize(new Dimension(220,25));
	 shippingplant.setEditable(false);

	subbut.addActionListener(this);
	complete.addActionListener(this);
	addbut.addActionListener(this);

	on=new JLabel("Order Number:");
	ocd=new JLabel("Order Creation Date:");
	os=new JLabel("Order Status:");
    cc=new JLabel("Customer Code:");
    crd=new JLabel("Customer Ref No-date:");
    cn=new JLabel("Customer Name:");
    cad=new JLabel("Customer Address:");
    cp=new JLabel("Cust Pincode:");
    ccy=new JLabel("City:");
    ov=new JLabel("Order Value:");
    sp=new JLabel("Shipping Plant:");
    msg=new JLabel(" ");
    panel=new JPanel();
	p1=new JPanel();
	p2=new JPanel();
	p3=new JPanel();
	p4=new JPanel();
	p5=new JPanel();
	p6=new JPanel();
	FlowLayout fl=new FlowLayout(FlowLayout.TRAILING);
	
	fl.setHgap(100);
	fl.setVgap(15);
	p1.setLayout(fl);
	p2.setLayout(fl);
	p3.setLayout(fl);
	p4.setLayout(fl);
	p5.setLayout(fl);
	p6.setLayout(fl);
	blackline = BorderFactory.createLineBorder(Color.black);
	ordernumber.setFont(new Font("Arial", Font.BOLD, 15));
	on.setFont(new Font("Arial", Font.BOLD, 15));
ocd.setFont(new Font("Arial", Font.BOLD, 15));
os.setFont(new Font("Arial", Font.BOLD, 15));
cc.setFont(new Font("Arial", Font.BOLD, 15));
crd.setFont(new Font("Arial", Font.BOLD, 15));
cn.setFont(new Font("Arial", Font.BOLD, 15));
cad.setFont(new Font("Arial", Font.BOLD, 15));
cp.setFont(new Font("Arial", Font.BOLD, 15));
ccy.setFont(new Font("Arial", Font.BOLD, 15));
ov.setFont(new Font("Arial", Font.BOLD, 15));
sp.setFont(new Font("Arial", Font.BOLD, 15));
msg.setFont(new Font("Arial Black", Font.BOLD, 15));
msg.setForeground(Color.red);


ordernumber.setFont(new Font("Arial", Font.BOLD, 15));
ordercrdate.setFont(new Font("Arial", Font.BOLD, 15));
orderstatus.setFont(new Font("Arial", Font.BOLD, 15));
	customercode.setFont(new Font("Arial", Font.BOLD, 15));
	customerrefdate.setFont(new Font("Arial", Font.BOLD, 15));
	customername.setFont(new Font("Arial", Font.BOLD, 15));
	custaddress.setFont(new Font("Arial", Font.BOLD, 15));
	custpin.setFont(new Font("Arial", Font.BOLD, 15));
	custcity.setFont(new Font("Arial", Font.BOLD, 15));
	ordervalue.setFont(new Font("Arial", Font.BOLD, 15));
	shippingplant.setFont(new Font("Arial", Font.BOLD, 15));

	p1.add(on);
	p1.add(ordernumber);
	p1.add(ocd);
	p1.add(ordercrdate);
	p2.add(os);
	p2.add(orderstatus);
	p2.add(cc);
	p2.add(customercode);
	p3.add(crd);
	p3.add(customerrefdate);
	p3.add(cn);
	p3.add(customername);
	p4.add(cad);
	p4.add(custaddress);
    p4.add(cp);
    p4.add(custpin);
    p5.add(ccy);
    p5.add(custcity);
    p5.add(ov);
    p5.add(ordervalue);
    p6.add(msg);
    p6.add(sp);
    p6.add(shippingplant);
    
    panel.setBorder(blackline);
    custaddress.setBorder(blackline);
    frame.setSize(1300,760);
    frame.setResizable(false);
	panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
	JLabel imlabel=new JLabel(new ImageIcon("sms1.png"));
	imlabel.setAlignmentX(0.5f);
	panel.add(imlabel);
	panel.add(p1);
	panel.add(p2);
	panel.add(p3);
	panel.add(p3);
	panel.add(p4);
	panel.add(p5);
	panel.add(p6);
 dtm = new DefaultTableModel(0, 0);
tbl=new JTable() {
    @Override
    public boolean isCellEditable(int row, int column) {                
        return (column==1||column==3);               
    };
};
String header[] = new String[] {"Sno"," Material Code","Description", "Item Qty","Item Val"};


		stmt = conn.createStatement();
      String sql,ss="";
    sql = "select * from material_master where material_code=\"COMP001\"";
float f=0.0f;
tbl.setModel(dtm);

 dtm.setColumnIdentifiers(header);
      rs = stmt.executeQuery(sql);
      while(rs.next())
      {  
        ss=rs.getString("material_description");
		f=rs.getFloat("material_price");
      }
count++;
dtm.addRow(new Object[] { new Integer(count),"COMP001", ss,
                "1", Float.toString(f) });

   
  tbl.setRowHeight(40);

JPanel finalpanel=new JPanel();
JScrollPane scrollPane = new JScrollPane(finalpanel);
JScrollPane scrolltable = new JScrollPane(tbl);
tbl.setFillsViewportHeight(true);
JPanel panel1=new JPanel();
panel1.setLayout(null);
panel1.add(complete);
complete.setBounds(100,20,200,50);
panel1.add(addbut);
addbut.setBounds(500,20,200,50);
panel1.add(subbut);
subbut.setBounds(900,20,200,50);
	finalpanel.setLayout(new GridLayout(3,1));
	finalpanel.add(panel);
	finalpanel.add(scrolltable);
	finalpanel.add(panel1);
	frame.setLayout(new GridLayout(1,1));
	frame.add(scrollPane);
	frame.setVisible(true);
	customercode.requestFocusInWindow();
	
}catch(Exception eee){eee.printStackTrace();}
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
}
public static void main(String[] args) 
{
	new Screen1();
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==subbut)
{ if(flag1==1)
	{
	try{
		stmt = conn.createStatement();
      String sql;
      int o=Integer.parseInt(ordernumber.getText().trim());
      String st="\""+orderstatus.getText().trim()+"\"";
      String d="\""+ordercrdate.getText().trim()+"\"";
      int c=Integer.parseInt(customercode.getText().trim());
      String refd="\""+customerrefdate.getText().trim()+"\"";
      float oval=Float.parseFloat(ordervalue.getText().trim());
      String shi="\""+shippingplant.getText().trim()+"\"";
      
      sql = "insert into order_tracking values("+o+","+st+","+d+");";

      stmt.executeUpdate(sql);
	sql = "insert into ORDER_HEADER(order_no,order_creation_date,order_status,customer_ref_date,order_value,material_required_date) values("+o+","+d+","+st+","+refd+","+oval+","+refd+");";
     stmt.executeUpdate(sql);
     sql = "select * from customer_master where cust_slno="+customercode.getText().trim();
String cinit="",cno="";
      rs = stmt.executeQuery(sql);
      while(rs.next())
      {  
      cinit=rs.getString("cust_initial");
      cno=rs.getString("contact_person_number");
      cno.trim();
  }	
     sql="update ORDER_HEADER set customer_slno="+customercode.getText().trim()+",customer_initial=\""+cinit+"\",customer_ref_no=\""+cno+"\" where order_no="+o;
      stmt.executeUpdate(sql);
      for(int i=0;i<tbl.getRowCount()-1;i++)
               {
               String tem=new String();
             tem=(String)tbl.getValueAt(i,1);
             tem.trim();
             tem="\""+tem+"\"";  	
             String qty=(String)tbl.getValueAt(i,3);
             String vll=new String(); 
             vll=(String)tbl.getValueAt(i,4);
  sql = "insert into ORDER_DETAIL values("+o+","+tem+","+qty+","+vll+");";
//System.out.println(tbl.getRowCount());
      stmt.executeUpdate(sql);
      sql="update stock_master set stock_qty=stock_qty-1 where material_code="+tem;
      stmt.executeUpdate(sql);
  }
  sql="update ORDER_HEADER set order_status=\"SCHD\" where order_no="+o;
      stmt.executeUpdate(sql);
sql="update order_tracking set order_status=\"SCHD\" where order_no="+o;
      stmt.executeUpdate(sql);

	rs.close();
    stmt.close();
    conn.close();}
    catch(Exception ee){System.out.println(ee);}
    frame.dispose();
new Confirm();}
    else
    {
msg.setText("Update Form First.");
    }	
}
if(e.getSource()==addbut)
{
count++;	
dtm.insertRow(tbl.getRowCount(),new Object[] { new Integer(count), null, null,
                "1", "000"});

}		
if(e.getSource()==complete)
{
	String sss[]=new String[tbl.getRowCount()];
	for(int i=0;i<tbl.getRowCount();i++)
               {
	sss[i]=(String)tbl.getValueAt(i,3);
	int ttt=Integer.parseInt(sss[i]);
	if(ttt<=0)
	{
		flag2=1;
	}else{flag2=-1;}
}
		if(customercode.getText().trim().equals("")||customerrefdate.getText().trim().equals(""))
		{msg.setText("Complete Missing Fields.");
		}
		else if(flag2==1)
		{
			msg.setText("Item Qty can't be 0 or less");
		}
		else if(!customerrefdate.getText().matches("\\d{4}"+"-"+"\\d{2}"+"-"+"\\d{2}"))
		{
			msg.setText("Enter Customer Ref No-date(YYYY-MM-DD)");
		}
		else
		{
			try{String sql,spp="";
	flag1=1;
	for(int i=0;i<count;i++)
               {
               	String tem=new String();
             tem=(String)tbl.getValueAt(i,1);
             tem.trim();
             tem="\""+tem+"\"";  	
 		sql = "select * from stock_master where material_code="+tem;
      rs = stmt.executeQuery(sql);
      while(rs.next())
      {  
        int sqt=rs.getInt("stock_qty");
        if(sqt<=0)
{
        msg.setText("Item not in stock.");
        return;
    }	
	}
}
	msg.setText(" ");


customerrefdate.requestFocusInWindow();
tbl.setRowSelectionInterval(0,0);	
      stmt = conn.createStatement();
      
      sql = "select * from customer_master where cust_slno="+customercode.getText().trim();

      rs = stmt.executeQuery(sql);
      while(rs.next())
      {  
      	String nme=rs.getString("cust_name");
      	customername.setText(nme);
      	 custaddress.append(rs.getString("cust_add1")+",");
         custaddress.append(" "+rs.getString("cust_add2")+",");
         custaddress.append(" "+rs.getString("cust_add3"));
         custcity.setText(rs.getString("cust_city"));
         custpin.setText(rs.getString("cust_pincode"));
               }
               if(customername.getText().trim().equals(""))
      	{	
      		customername.setEditable(true);
      		custaddress.setEditable(true);
      		custcity.setEditable(true);
      		custpin.setEditable(true);
      		return;
      	}
               String ss="";
               for(int i=0;i<count;i++)
               {
               	String tem=new String();
             tem=(String)tbl.getValueAt(i,1);
             tem.trim();
             tem="\""+tem+"\"";  	
 		sql = "select * from material_master where material_code="+tem;
	  float f=0.0f;
      rs = stmt.executeQuery(sql);
      while(rs.next())
      {  
        ss=rs.getString("material_description");
		f=rs.getFloat("material_price");
		spp=rs.getString("shipping_plant");
		shippingplant.append(spp+" ");
      }
      String ff=Float.toString(f);
dtm.setValueAt(ss,i,2);
dtm.setValueAt(ff,i,4);

}
               	if(flag==1)
               	{
               		flag=-1;

               String temp="",qq="";
               float mm=0.0f;
               //System.out.println(tbl.getRowCount());
               for(int i=0;i<count;i++)
               {
               	temp=(String)tbl.getValueAt(i,4);
               	mm=Float.parseFloat(temp);
               	qq=(String)tbl.getValueAt(i,3);
               	int qty=Integer.parseInt(qq);
               	sum=sum+mm*qty;
               	} 

dtm.addRow(new Object[]{"Total",null,null,null,new Float(sum)});
ordervalue.setText(Float.toString(sum));
complete.setEnabled(false);
customercode.setEditable(false);
customername.setEditable(false);
custaddress.setEditable(false);
custcity.setEditable(false);
custpin.setEditable(false);

addbut.setEnabled(false);
tbl.setEnabled(false);

}    
   }catch(SQLException se){
      
      se.printStackTrace();
   }catch(Exception eww){
      
      eww.printStackTrace();
   }}
}

}
}