package clique_algo2;
/**
 * this class represents a simple ORDERD set of vertices. used by Cliques
 * The code was written in C like flavor - no java.util, no abstraction  
 * @author Boaz
 *
 */
public class VertexSet {
	private int[] _set = null;
	private int _sp;
	public final static int INIT_SIZE=20, INC=50;
	
	public VertexSet() {
		_set = new int[INIT_SIZE];
		_sp=0;
	}
	
	public VertexSet(VertexSet ot) {
		_set = new int[ot._sp];
		_sp=ot._sp;
		for(int i=0;i<ot.size();i++) this._set[i] = ot._set[i];
	}
	
	public void add(int a) {
		if(_sp==_set.length) resize();
		_set[_sp] = a;
		_sp++;
	}
	public int size() {return _sp;}
	public int at(int i) {return _set[i];}
	
	public String toString() {
		String ans = "Set: |"+size()+"| ";
		for(int i=0;i<size();i++) ans+=this.at(i)+", ";
		return ans;
	}
	public String toFile() {
		String ans = " ";
		for(int i=0;i<size();i++) ans+=this.at(i)+", ";
		return ans;
	}
	/**
	 * this method computes the intersection between this set and ot set.
	 * @param ot - the other set
	 */
	public VertexSet intersection(VertexSet ot) {
		VertexSet ans = new VertexSet();
		int i1=0,i2=0;
		while(i1<this.size() & i2 < ot.size()) {
			int a1=this.at(i1), a2 = ot.at(i2);

			if(a1==a2) {
				ans.add(a1); i1++; i2++;}
			else if(a1<a2) {i1++;}
			else i2++;
		}
		return ans;
	}
	
	private void resize() {
		int[] tmp = new int[_sp+INC];
		for(int i=0;i<_sp;i++) tmp[i]=_set[i];
		_set=tmp;
	}
	
	public VertexSet(int[] ot) {
		_set = new int[ot.length];
		_sp=ot.length;
		for(int i=0;i<ot.length;i++) this._set[i] = ot[i];
	}
	
	public int[] toArray() 
	{
		int[] Arr = new int[_sp];
		
		for(int i=0;i<Arr.length;i++)
			Arr[i] = _set[i];
		
		return Arr;
	}
}
