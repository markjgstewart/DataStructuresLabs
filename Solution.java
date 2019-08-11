// Mark Stewart - Program designed to measure runtimes for lab 2
//outputs a textfile that can be used to make a graph of runtime as a function of N
//the graph can then be used to determine O(N)

import java.io.PrintWriter;
import java.io.IOException;

public class Solution
{
	public static void main(String[] args) throws IOException
	{
		if (args.length<1) System.out.println("You need to provide the largest value of N as an input!");
		else {
			int max = Integer.parseInt(args[0]);
			PrintWriter pw = new PrintWriter("Runtimes.txt","UTF-8");
			for (int N=10; N<max; N*=2) {
				long a=System.currentTimeMillis();	
				Stewart.method1(N);
				//Stewart.method2(N);
				//Stewart.method3(N);
				//System.out.println(N+","+(System.currentTimeMillis()-a));
				pw.println(N+","+(System.currentTimeMillis()-a));
			}
			pw.close();
		}
	}
}
