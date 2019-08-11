// Mark Stewart - 3 methods with mysterious runtimes to be tested
// note: only the .class file would be shared with the student, although I've shared the .java hoping that students would try to cheat and figure out the runtime analytically


public class Stewart
{
	public static void method1(int n)
	{
		int b= (int)(n*Math.random());
		for (int i=0;i<b;i++)
		{
			for (int j=0;j<b;j++)
				System.out.print("New Jersey");
		}
	}

	public static void method2(int n)
	{
		for (int i=1;i<n;i=i*2)
		{
			for (int j=0;j<100;j++)
				System.out.print("New Jersey");
		}
	}

	public static void method3(int n)
	{
		if (n<500) System.out.println("Ok");
		else
		{
			long m=1;
			for (int i=n/500;i>0;i--) m=m*2;
			for (long i=0;i<m;i++) System.out.println("Holy cow!");
		}
	}
}
