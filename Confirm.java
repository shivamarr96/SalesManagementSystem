import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.*;
class Confirm implements ActionListener
{
	JFrame frame;
	JLabel stmsg;
	JButton ok;
	JPanel okpane,stspane;
	Confirm()
	{
	frame=new JFrame("Order Confirmation");
	ok=new JButton("Return to Menu");
	stmsg=new JLabel("Order Status:BLOC --> SCHD");
	stspane=new JPanel();
	stspane.setBackground(Color.green);
	stspane.setLayout(new GridLayout(1,1));
	stspane.add(stmsg);
	stmsg.setFont(new Font("Arial", Font.ITALIC, 30));
    ok.setFont(new Font("Arial", Font.ITALIC, 20));
    ok.addActionListener(this);
	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
	frame.setResizable(false);
	frame.setLayout(new GridLayout(2,1));
	frame.setSize(450,300);
	frame.add(stspane);
	frame.add(ok);
	frame.setVisible(true);
	frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	
	}
	public void actionPerformed(ActionEvent e)
{
if(e.getSource()==ok)
{frame.dispose();}
}
public static void main(String[] args) {
	new Confirm();
}
}