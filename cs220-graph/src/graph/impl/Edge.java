package graph.impl;

import graph.INode;

public class Edge implements Comparable<Edge> {
	private int weight;
	private INode destination;
	private INode sourceNode;
	
	public Edge(INode destination, int weight,INode sourceNode ) {
		this.destination = destination;
		this.weight = weight;
		this.sourceNode=sourceNode;
	}
	
	public INode getDest() {
		return this.destination;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	public INode getSource() {
		return this.sourceNode;
	}
	
	
	

	@Override
	public int compareTo(Edge o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	

}
