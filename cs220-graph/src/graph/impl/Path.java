package graph.impl;

public class Path implements Comparable<Path>{
	private String destination;
	private int cost;
	
	public Path(String destination, int cost) {
		this.destination = destination;
		this.cost = cost;
	}
	
	public String getNode() {
		return this.destination;
	}
	
	public int getCost() {
		return this.cost;
	}

	@Override
	public int compareTo(Path o) {
		// TODO Auto-generated method stub
		return this.cost - o.cost;  
	}

}
