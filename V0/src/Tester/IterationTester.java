package Tester;



public class IterationTester 
{
	public static int minQ = 14, maxQ=20;
	public static double TH = 0.75;
	public static String[] args;
	
	public static void main(String[] args) 
	{
		IterationTester.args = args;
		
		Test(10,20,0.74);
		Test(15,20,0.7);
	}
	
	public static void Test(int minQ,int maxQ,double TH) 
	{
		IterationTester.minQ = minQ;
		IterationTester.maxQ = maxQ;
		IterationTester.TH = TH;
		
		Install();
		
		clique_algo2.Clique_Tester.main(args);
		clique_algo.Clique_Tester.main(args);
		
	}
	
	public static void Install() 
	{
		clique_algo.Clique_Tester.maxQ = maxQ;
		clique_algo.Clique_Tester.minQ = minQ;
		clique_algo.Clique_Tester.TH = TH;
		
		clique_algo2.Clique_Tester.maxQ = maxQ;
		clique_algo2.Clique_Tester.minQ = minQ;
		clique_algo2.Clique_Tester.TH = TH;
	}
}
