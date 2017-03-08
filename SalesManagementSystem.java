import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;

class SalesManagementSystem implements ActionListener
{
	JFrame frame;
	JButton orderentry,ordership,invoicegen,macinstall,paystat,orderstat,exitbutton;
	Border blackline;
	JLabel label;
	JPanel panel,finalpanel;
	JScrollPane scroll;
	
	SalesManagementSystem()
	{
	frame=new JFrame("Sales	Management System");
	finalpanel=new JPanel();
	scroll=new JScrollPane(finalpanel);
	orderentry=new JButton("1.ENTER A NEW ORDER");
	orderentry.setBackground(Color.white);
	orderentry.setFont(new Font("Arial Black", Font.PLAIN, 17));
	orderentry.setForeground(Color.blue);
	orderentry.setHorizontalAlignment(SwingConstants.LEFT);
	ordership=new JButton("2.SCHEDULE ORDER FOR SHIPMENT");
	ordership.setBackground(Color.white);
	ordership.setFont(new Font("Arial Black", Font.PLAIN, 17));
	ordership.setForeground(Color.blue);
	ordership.setHorizontalAlignment(SwingConstants.LEFT);
	invoicegen=new JButton("3.GENERATE INVOICE");
	invoicegen.setBackground(Color.white);
	invoicegen.setFont(new Font("Arial Black", Font.PLAIN, 17));
	invoicegen.setForeground(Color.blue);
	invoicegen.setHorizontalAlignment(SwingConstants.LEFT);	
	macinstall=new JButton("4.SCHEDULE MACHINE INSTALLATION.");
	macinstall.setBackground(Color.white);
	macinstall.setFont(new Font("Arial Black", Font.PLAIN, 17));
	macinstall.setForeground(Color.blue);
	macinstall.setHorizontalAlignment(SwingConstants.LEFT);	
	paystat=new JButton("5.PAYMENT STATUS");
	paystat.setHorizontalAlignment(SwingConstants.LEFT);	
	paystat.setFont(new Font("Arial Black", Font.PLAIN, 17));
	paystat.setForeground(Color.blue);
	paystat.setBackground(Color.white);
	orderstat=new JButton("6.ORDER STATUS");
	orderstat.setHorizontalAlignment(SwingConstants.LEFT);	
	orderstat.setFont(new Font("Arial Black", Font.PLAIN, 17));
	orderstat.setForeground(Color.blue);
	orderstat.setBackground(Color.white);
	exitbutton=new JButton("7.EXIT");
	exitbutton.setHorizontalAlignment(SwingConstants.LEFT);	
	exitbutton.setBackground(Color.white);
	exitbutton.setFont(new Font("Arial Black", Font.PLAIN, 17));
	exitbutton.setForeground(Color.blue);
	label=new JLabel(new ImageIcon("sms2.png"));
	panel=new JPanel();
	panel.setLayout(new GridLayout(1,1));
	panel.add(label);
	panel.setBackground(Color.white);


	orderentry.addActionListener(this);
	ordership.addActionListener(this);
	invoicegen.addActionListener(this);
	macinstall.addActionListener(this);
	paystat.addActionListener(this);
	orderstat.addActionListener(this);
	exitbutton.addActionListener(this);

	frame.setSize(1300,760);
	frame.setLayout(new GridLayout(1,1));
	frame.add(scroll);
	finalpanel.setLayout(new GridLayout(8,1));
	finalpanel.add(panel);
	finalpanel.add(orderentry);
	finalpanel.add(ordership);
	finalpanel.add(invoicegen);
	finalpanel.add(macinstall);
	finalpanel.add(paystat);
	finalpanel.add(orderstat);
	finalpanel.add(exitbutton);
	frame.setVisible(true);

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
	public void actionPerformed(ActionEvent e)
{
if(e.getSource()==orderentry)
{
new Screen1();
}
if(e.getSource()==ordership)
{
new Screen2();
}
if(e.getSource()==invoicegen)
{
new Screen3();
}
if(e.getSource()==macinstall)
{
new Screen4();
}
if(e.getSource()==paystat)
{
new Screen5();
}
if(e.getSource()==orderstat)
{
new Screen6();
}
if(e.getSource()==exitbutton)
{
System.exit(0);
}
}
public static void main(String[] args)
	{
		new SalesManagementSystem();
	}
}