package clique_algo;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Vector;

public class Clique_Tester {
	public static int minQ = 6, maxQ=10;
	public static double TH = 0.75;
	public static String in_file = "test1.csv";
	public static String out_file = null;
	public static boolean Debug = true;
	public static int MAX_CLIQUE = 100000;
	public static boolean Convert = true;
	
	
	public static void main(String[] args) {  // test1.csv_DG.txt  0.8 5 7
		if(args==null || args.length<3) {
			help();
		}
		else {
			parse(args);
		}

			Graph G = new Graph(in_file, TH);
		
			G.All_Cliques_DFS(minQ,maxQ);
			System.out.println(clique_algo.VertexSet.Iter);
		//}
	}
	static void help() {
		System.out.println("Wrong Parameters! should use: java -jar All_Cliques <input file> <round value> <min clique> <max clique> <output file> <max_cliques> <Graph convert flag>");
		System.out.println("Wrong Parameters! should use: java -jar All_Cliques test1.csv 0.7 5 7 test1_out.txt 10000 true");
	}
	static void parse(String[] a){
		try {
			in_file = a[0];
			TH = new Double(a[1]);
			minQ=new Integer(a[2]);
			maxQ=new Integer(a[3]);
			if(a.length>4 ) out_file = a[4];
			if(a.length>5) MAX_CLIQUE = new Integer(a[5]);
			if(a.length>6) Convert = new Boolean(a[6]);}
		catch(Exception e) {
			e.printStackTrace();
			help();
		}
	}
	
	/*static void printAll(Vector<VertexSet> c) {
		for(int i=0;i<c.size();i++) {
			VertexSet curr = c.elementAt(i);
			if(curr.size()>=minQ) {
				System.out.println(i+") "+curr);
			}
		}
	}
	
	
	static void write2file(Vector<VertexSet> c) {
		FileWriter fw=null;
		try {fw = new FileWriter(out_file);} 
		catch (IOException e) {e.printStackTrace();}
		PrintWriter os = new PrintWriter(fw);
		os.println("ALL_Cliques: of file: "+in_file+",  TH:"+TH+" Max Q:"+maxQ);
		for(int i=0;i<c.size();i++) {
			VertexSet curr = c.elementAt(i);
			if(curr.size()>=minQ) os.println(i+") "+curr);
		}
		os.close();
		try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}