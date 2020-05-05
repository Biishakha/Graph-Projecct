package graph.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import graph.IGraph;
import graph.INode;
import graph.NodeVisitor;

/**
 * A basic representation of a graph that can perform BFS, DFS, Dijkstra,
 * and Prim-Jarnik's algorithm for a minimum spanning tree.
 * 
 * @author jspacco
 *
 */
public class Graph implements IGraph
{
	private Map<String, INode> helper = new HashMap<>();
	private INode node;
    private Collection<INode> allNodes = new ArrayList<>();
    private String x;
    /**
     * Return the {@link Node} with the given name.
     * 
     * If no {@link Node} with the given name exists, create
     * a new node with the given name and return it. Subsequent
     * calls to this method with the same name should
     * then return the node just created.
     * 
     * @param name
     * @return
     */
    public INode getOrCreateNode(String name) {
        if (helper.containsKey(name)) {
        	return helper.get(name);
        }
        else {
        	node = new Node(name); 
        	helper.put(name, node);
        	allNodes.add(node);
            return helper.get(name);
        }
    }

    /**
     * Return true if the graph contains a node with the given name,
     * and false otherwise.
     * 
     * @param name
     * @return
     */
    public boolean containsNode(String name) {
        return helper.containsKey(name);
    }

    /**
     * Return a collection of all of the nodes in the graph.
     * 
     * @return
     */
    public Collection<INode> getAllNodes() {
        return allNodes;
    }
    
    /**
     * Perform a breadth-first search on the graph, starting at the node
     * with the given name. The visit method of the {@link NodeVisitor} should
     * be called on each node the first time we visit the node.
     * 
     * 
     * @param startNodeName
     * @param v
     */
    public void breadthFirstSearch(String startNodeName, NodeVisitor v)
    {
        Set<String> visited= new HashSet<>();
        LinkedList<String> toVisit = new LinkedList<>();
        
        toVisit.add(startNodeName);
        while (toVisit.size() != 0) {
        	x = toVisit.poll();
        	node = helper.get(x);
        	if (visited.contains(x)) {
        		continue; 
        		
        	}
        	v.visit(node);
        	visited.add(x);
        	
        	for (INode n: node.getNeighbors()) {
        		if (!visited.contains(n.getName())) {
        			toVisit.add(n.getName());
        		}
        	}
        }
       
    }

    /**
     * Perform a depth-first search on the graph, starting at the node
     * with the given name. The visit method of the {@link NodeVisitor} should
     * be called on each node the first time we visit the node.
     * 
     * 
     * @param startNodeName
     * @param v
     */
    public void depthFirstSearch(String startNodeName, NodeVisitor v)
    {
    	Set<String> visited= new HashSet<>();
        Stack<String> toVisit = new Stack<>();
        
        toVisit.add(startNodeName);
        
        while (toVisit.size() != 0) {
        	x = toVisit.pop();
        	node = helper.get(x);
        	if (visited.contains(x)) {
        		continue; 
        		
        	}
        	v.visit(node);
        	visited.add(x);
        	
        	for (INode n: node.getNeighbors()) {
        		if (!visited.contains(n.getName())) {
        			toVisit.add(n.getName());
        		}
        	}
        }
    }

    /**
     * Perform Dijkstra's algorithm for computing the cost of the shortest path
     * to every node in the graph starting at the node with the given name.
     * Return a mapping from every node in the graph to the total minimum cost of reaching
     * that node from the given start node.
     * 
     * <b>Hint:</b> Creating a helper class called Path, which stores a destination
     * (String) and a cost (Integer), and making it implement Comparable, can be
     * helpful. Well, either than or repeated linear scans.
     * 
     * @param startName
     * @return
     */
    public Map<INode,Integer> dijkstra(String startName) {
       Map <INode, Integer> result = new HashMap<>();
       PriorityQueue <Path> toDo = new PriorityQueue<>();
       Path nextPath;
       int cost;
       
       toDo.add(new Path (startName, 0));
       while (result.size() < allNodes.size()) {
    	   nextPath= toDo.poll();
    	   node = helper.get(nextPath.getNode());
    	   if (result.containsKey(node)) {
    		   continue;
    	   }
    	  cost=nextPath.getCost();
    	  result.put(node, cost);
    	  
    	  for (INode n: node.getNeighbors()) {
    		  toDo.add(new Path (n.getName(), cost+ node.getWeight(n)));
    	  }
    		   
    	   
       }
       return result;
       
    }
    
    /**
     * Perform Prim-Jarnik's algorithm to compute a Minimum Spanning Tree (MST).
     * 
     * The MST is itself a graph containing the same nodes and a subset of the edges 
     * from the original graph.
     * 
     * @return
     */
    
    /*
    public IGraph primJarnik() {
        IGraph pj = new Graph();
        Edge edge;
        
        INode start= (INode) this.getAllNodes().toArray()[0];  //starting node
        PriorityQueue<Edge> toDo = new PriorityQueue<>();
        
        for ( INode destination: start.getNeighbors()) {
        	toDo.add(new Edge (destination, start.getWeight(destination), start));
        }
        
         while ...
        
    }
    */
}