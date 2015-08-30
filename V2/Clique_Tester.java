
package clique_algo2;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.Vector;



public class Clique_Tester {
	public static Random Rand = new Random(0);
	public static int minQ = 8, maxQ=10;
	public static double TH = 0.75;
	public static boolean Debug = true;
	public static int MAX_CLIQUE = 10000000;
	public static boolean Convert = true;
	
	
	public static void main(String[] args) {  // test1.csv_DG.txt  0.8 5 7
		
		int[][] tGraph = {
				{1,2,3,4,5,6},
				{2,3,4,5,6},
				{3,4,5},
				{4},
				{5,6},
				{1},
				{}
		};
		CheckGraphLoader("Check1.c",10);
		CheckGraphLoader("Check2.c",100);
		CheckGraphLoader("Check3.c",1000);
		
		Graph Graph = new Graph("Check3.c");
		Graph.All_Cliques_DFS("res.csv", 7, 15);
		System.out.println("Done");
		
		CheckMaxGraphClique("CompleteGraph.c",CreateCompleteGraph(200),200);
		
		System.out.println("test some graph");
		CheckMaxGraphClique("CompleteGraph.c",tGraph,5);
		
		CheckCompleteGraphCliueAtSize("CompleteGraph.c",8);
	}
	
	/*
	 * thius function create file of size, and check the loader and saver of the graph loader
	 * all the clique at size k in complete graph at size n shuld be n!/(k!(n-k)!) 
	 */
	
	private static int Factorial(int n) 
	{
		int f = 1;
		
		for(;n > 1;n--)
			f *= n;
		
		return f;
	}
	
	public static void CheckCompleteGraphCliueAtSize(String File,int Size) 
	{
		GraphLoader Loader = new GraphLoader(File),Saver =  new GraphLoader(File);
		int n =0;
		Saver.setGraph(CreateCompleteGraph(Size));
		Saver.Save();
		Graph Graph = new Graph(File);

		
		for(int i=3;i<Size;i++) 
		{
			int S = Factorial(Size) / (Factorial(i) * Factorial(Size - i)) ;
			
			if(Graph.All_Cliques_DFS(i).size() == S)
				System.out.println("good");
			else 
				System.out.println("false !! + ," + i);
		}
	}
	
	public static void CheckMaxGraphClique(String File,int[][] ArrA,int BiggestSize) 
	{
		GraphLoader Loader = new GraphLoader(File),Saver =  new GraphLoader(File);
		int n =0;
		Saver.setGraph(ArrA);
		Saver.Save();
		
		Graph Graph = new Graph(File);
		
		if((n = Graph.Max_Cliques_Size()) == BiggestSize)
			System.out.println("true");
		else 
			System.out.println("false !! + ," + n);
	}
	
	public static void CheckGraphLoader(String File,int Size) 
	{
		int[][] ArrA = CreateArr(Size);

		
		GraphLoader Loader = new GraphLoader(File),Saver =  new GraphLoader(File);
		
		Saver.setGraph(ArrA);
		Saver.Save();
		
		Loader.Load();
		
		if(CompareArray(ArrA,Loader.getGraph()))
			System.out.println("true");
		else 
			System.out.println("false");
	}
	
	public static int[][] CreateCompleteGraph(int Size) 
	{
		int[][] Arr = new int[Size][];
		
		for(int i=0;i< Size;i++) 
		{
			Arr[i] = new int[Size - i  - 1];
			
			for(int j=0;j<Size - i - 1;j++)
				Arr[i][j] = j + i + 1;
		}
		
		return Arr;
	}
	
	/*
	 * create random 2d array  Without redundancies (in vertex)
	 */
	
	public static int[][] CreateArr(int Size) 
	{
		int[][] Arr = new int[Size][];
		int[] RandHelpArr = new int[Size];
		int RandPointer,Help;
		
		for(int i=0;i< Size;i++) 
			RandHelpArr[i] = i;
		
		for(int i=0;i< Size;i++) 
		{
			Arr[i] = new int[Rand.nextInt(Size - i)];
			RandPointer = 0;
			
			for(int j=0;j< Arr[i].length;j++) 
			{
				while(Arr[i][j] <= i) {
					Swap(RandHelpArr,Rand.nextInt(Size - RandPointer) + RandPointer,RandPointer);
					Arr[i][j] = RandHelpArr[RandPointer++];
				}
				
			}
		}
		
		return Arr;
	}
	
	private static void Swap(int[] Arr,int i, int j) 
	{
		int Temp = Arr[i];
		
		Arr[i] = Arr[j];
		Arr[j] = Temp;
	}
	
	public static boolean CompareArray(int[][] ArrA,int[][] ArrB) 
	{
		if(ArrA.length != ArrB.length)
			return false;
		
		for(int i=0;i< ArrA.length;i++) 
		{
			if(ArrA[i].length != ArrB[i].length)
				return false;
			
			for(int j=0;j< ArrA[i].length;j++) 
			{
				if(ArrA[i][j] != ArrB[i][j])
					return false;
			}
				
		}
		
		return true;
	}
	
}