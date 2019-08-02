//Stewart sample queue class part of Lab 4
//May not use the java LinkedList data type
import java.util.Iterator;

public class BathroomQueue
{
	public static void main(String[] args) //main program to test Queue
	{
		BathroomQueue m = new BathroomQueue();
		for (int i=0;i<5;i++) m.enqueue(i,0);
		for (int i=0;i<6;i++) 
			if (!(m.isEmpty())) System.out.println(m.dequeue().timeNeeded);
	}

	Node front;
	Node back;
	int length=0;

	public Iterator elements() {
		return new BQIterator();
	}

	public void enqueue(int i, int timeNeeded)
	{
		if (back != null)
		{
			Node temp = back;
			back = new Node(i, timeNeeded, null);
			temp.next=back;
		}
		else back = new Node(i,timeNeeded, null);
		if (front==null) front=back;
		length++;
	}
	public Node dequeue()
	{
		length--;
		Node temp = front;
		front = front.next;
		return temp;
	}
	public boolean isEmpty()
	{
		return (front==null);
	}

	public static class Node
	{
		public int timeEntered;
		public int timeNeeded;
		public Node next;

		public Node(int i, int j, Node n)
		{
			timeEntered=i;
			timeNeeded=j;
			next =n;
		}
	}
	public class BQIterator implements Iterator
	{
		public Node here;
		public BQIterator() {here=front;}
		public Node next() {Node temp = here; here=here.next; return temp;}
		public boolean hasNext() {return here!=null;}
	}
}
