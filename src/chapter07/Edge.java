package chapter07;

public class Edge implements Comparable{
	public int start,end;
	public double weight;
	public Edge(int start,int end,double weight){
		this.start=start;
		this.end=end;
		this.weight=weight;
	}
	

	@Override
	public int compareTo(Object o) {
		Edge to=(Edge)o;
		if(this.weight>to.weight) return 1;
		else if(this.weight==to.weight) return 0;
		else return -1;
		
	}
	@Override
	public String toString() {
		return "start="+start+"||end="+end+"||w="+weight;
	}
}
