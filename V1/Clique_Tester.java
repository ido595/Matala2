
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
		CheckGraphLoader("Check1.c",10);
		CheckGraphLoader("Check2.c",100);
		CheckGraphLoader("Check3.c",1000);
		
		Graph Graph = new Graph("Check3.c");
		Graph.All_Cliques_DFS("res.csv", 7, 15);
		System.out.println("Done");
	}
	
	/*
	 * thius function create file of size, and check the loader and saver of the graph loader
	 */
	
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
	
	/*
	 * create random 2d array  Without redundancies (in 1d array)
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
		
		Graph G = new Graph();
		G.setGraph(Arr);
		Arr = G;
		
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