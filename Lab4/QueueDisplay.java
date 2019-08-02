//Stewart for Lab 4
//GUI to view bathroom queues

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class QueueDisplay extends JFrame
{
	int line1=0,line2=0;
	JPanel[] q1;
	JPanel[] q2;
	int size=100;

	public QueueDisplay()
	{
		Container c = getContentPane();
		c.setLayout(new GridLayout(4,1)); //two lines and two labels
		JPanel a = new JPanel();
		a.setLayout(new GridLayout(1,size)); //lines can hold at most 100 customers
		JPanel b = new JPanel();
		b.setLayout(new GridLayout(1,size));
		q1 = new JPanel[size];
		q2 = new JPanel[size];
		for(int i =0;i<q1.length;i++) 
		{
			q1[i] = new JPanel(); q2[i] = new JPanel();
			q1[i].setBackground(Color.BLUE); q2[i].setBackground(Color.GRAY);
			a.add(q1[i]); b.add(q2[i]);
		}
		c.add(new JTextArea("   Line for Bathroom 1"));
		c.add(a);
		c.add(new JTextArea("   Line for Bathroom 2"));
		c.add(b);
		setSize(800,200);
		setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void update(BathroomQueue line1, BathroomQueue line2) //this is a little inefficient - drawing rectangles might be better
	{
		Iterator it = line1.elements(); int j=0;
		while (it.hasNext() && j<size) {
			BathroomQueue.Node here = (BathroomQueue.Node)it.next(); 
			q1[j++].setBackground(new Color( here.timeNeeded*10 ,0,0));
		}
		while (j<100) q1[j++].setBackground(Color.BLUE); 

		it = line2.elements(); j=0;		
		while (it.hasNext() && j<size) {
			BathroomQueue.Node here = (BathroomQueue.Node)it.next(); 
			q2[j++].setBackground(new Color( here.timeNeeded*10 ,0,0));
		}
		while (j<100) q2[j++].setBackground(Color.GRAY); 
	}
}
	
