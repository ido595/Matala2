package clique_algo2;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Vector;

/*
 * this class created to help the graph class loading a graph 
 * the repsention is by group each edge and each vertex connected to .
 */
public class GraphLoader 
{
	private String fileName;
	private int[][] Graph;
	
	public GraphLoader(String fileName) 
	{
		this.fileName = fileName;
	}
	
	
	
	public boolean Save() 
	{
		try {
			FileOutputStream File = new FileOutputStream(fileName);
			ObjectOutputStream Stream = new ObjectOutputStream(File);
			
			Stream.writeInt(Graph.length);
			
			for(int i=0;i<Graph.length;i++) 
			{
				Stream.writeInt(Graph[i].length);
				
				for(int j=0;j<Graph[i].length;j++) 
					Stream.writeInt(Graph[i][j]);
			}
			
			Stream.close();
			File.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return false;
	}
	
	public boolean Load() 
	{
		try {
			FileInputStream File = new FileInputStream(fileName);
			ObjectInputStream Stream = new ObjectInputStream(File);
			
			Graph = new int[Stream.readInt()][];
			
			for(int i=0;i<Graph.length;i++) 
			{
				Graph[i] = new int[Stream.readInt()];
				
				for(int j=0;j<Graph[i].length;j++) 
					Graph[i][j] = Stream.readInt();
			}
			
			Stream.close();
			File.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	public boolean[][] toBoolean() 
	{
		boolean[][] Arr = new boolean[Graph.length][Graph.length];
		
		for(int i=0;i<Graph.length;i++) 
		{
			for(int j=0;j<Graph[i].length;j++) 
			{
				Arr[i][Graph[i][j]] = true;
			}
		}
		
		return Arr;
	}
	
	public void fromVertex(Vector<VertexSet> VertexSets)  
	{
		Graph = new int[VertexSets.size()][];
		
		for(int i=0;i<VertexSets.size();i++)
			Graph[i] = VertexSets.elementAt(i).toArray();
	}
	
	public Vector<VertexSet> toVertex() 
	{
		Vector<VertexSet> VertexSets = new Vector<VertexSet>(Graph.length);
		
		for(int i=0;i<Graph.length;i++) 
			VertexSets.add(new VertexSet(Graph[i]));
		
		return VertexSets;
	}

	public void setGraph(int[][] Graph) 
	{
		this.Graph = Graph;
	}
	
	public int[][] getGraph() 
	{
		return this.Graph;
	}
	
	public String toString() 
	{
		String Str = "\n";
		
		for(int i=0;i<Graph.length;i++)
			Str = Arrays.toString(Graph[i]) + "\n";
		
		return Str;
	}
}
