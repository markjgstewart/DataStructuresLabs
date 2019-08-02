//Lab 1 Duck Duck Goose Solution
//Stewart - 2005 for Data Structures course at NWC WY
import java.util.Scanner;

public class DDG
{
	public Node ring;
	public int numPlayers;
	public boolean isRandom;
	public int skip;

	public static void main(String[] args) {
		String winner = "none yet";
		DDG thisGame = new DDG();
		thisGame.print(10); //prints 10 items in the ring to see if it is working
		winner = thisGame.playGame();
		System.out.println("winner is:"+winner);
	}

	public DDG()
	{
		getParameters();
		ring = new Node("player "+1);
		Node temp = ring;
		//System.out.println(temp.name);
		for (int i=2;i<numPlayers+1;i++) //create players in the ring
		{	
			temp.next = new Node("player "+i);
			temp = temp.next;
		}
		temp.next = ring;
	}

	public void print(int N) {
		Node node = ring;		
		for (int i=0;i<N;i++) {
			System.out.println(node.name);
			node=node.next;
		}
		System.out.println();
	}

	public void getParameters() { //unprotected inputs. Program can crash.
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Do you want random (1) or fixed (0)? (if random, skip will be max value)");
		isRandom = (keyboard.nextInt()==1);
		System.out.println("Enter skip amount, ie: for one-potatoes = every 8th person eliminated, enter 8");
		skip = keyboard.nextInt();
		System.out.println("Enter number of players");
		numPlayers = keyboard.nextInt();
	}
			
	public String playGame()
	{
		int skipTemp = skip;
		Node temp = ring;
		while (temp!=temp.next) //game stops when 1 player left in ring
		{
			if (isRandom) skipTemp = (int)(skip*Math.random());
			System.out.println("Skipping over next "+(skipTemp-1)+" players");
			for (int i=1; i<skipTemp; i++)
			{
				System.out.println("Skipping player "+temp.name);
				temp = temp.next;
			}
			//remove next player
			System.out.println("removing "+temp.next.name);
			temp.next = temp.next.next;
		}
		return temp.name;
	}		
}
