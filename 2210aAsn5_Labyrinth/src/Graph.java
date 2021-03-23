//Jordan Avelar S/N 251083623

import java.util.*;

public class Graph implements GraphADT {
	
	private int numNode;	//Total nodes
	private Edge[][] adjMatrix;	//Adjacency matrix
	
	public Graph(int n) {
		//Initialize instance variables
		this.numNode = n;
		this.adjMatrix = new Edge[numNode][numNode];
		
	}
	
	
	public void insertEdge(Node u, Node v, int edgeType) throws GraphException {
		
		try {
			Edge e = adjMatrix[u.getName()][v.getName()];
			//Add edge going both ways if there's an empty space in adjacency matrix
			if(e == null) {
				adjMatrix[u.getName()][v.getName()] = new Edge(u, v, edgeType);
				adjMatrix[v.getName()][u.getName()] = new Edge(v, u, edgeType);
			}
			
			else {
				throw new GraphException("Edge is already in graph");
			}
			
		} catch(Exception e) {
			throw new GraphException("Nodes don't exist");
		}
		
	}
	
	
	public void insertEdge(Node u, Node v, int edgeType, String label) throws GraphException {
		
		try {
			Edge e = adjMatrix[u.getName()][v.getName()];
			//Works same as method above, adding label String
			if(e == null) {
				adjMatrix[u.getName()][v.getName()] = new Edge(u, v, edgeType, label);
				adjMatrix[v.getName()][u.getName()] = new Edge(v, u, edgeType, label);
			}
			
			else {
				throw new GraphException("Edge is already in graph");
			}
		} catch(Exception e) {
			throw new GraphException("Nodes don't exist");
		}
		
	}
	
	
	public Node getNode(int u) throws GraphException {
		//Check if node is in "scope" of total nodes
		if(u < 0 || u > numNode) {
			throw new GraphException("No node found");
		}
		
		Node n = new Node(u);
		return n;
		
	}
	
	//Returns iterator containing all edges coming from input node
	public Iterator incidentEdges(Node u) throws GraphException {
		
		ArrayList list = new ArrayList();
		Boolean flag = false;
		
		if(u.getName() < 0 || u.getName() > numNode) {
			throw new GraphException("No node found");
		}
		//Loop though all nodes surrounding u
		for(int i=0; i<numNode;i++) {
			//If edge found in adjMatrix is found from u
			if(adjMatrix[u.getName()][i] != null) {
				list.add(adjMatrix[u.getName()][i]);
				flag = true;
			}
			else if(adjMatrix[i][u.getName()] != null) {
				list.add(adjMatrix[i][u.getName()]);
				flag = true;
			}
			
		}
		
		if(flag == false) {
			return null;
		}
		//Turns arraylist into iterator to be returned
		Iterator it = list.iterator();
		return it;
		
	}
	
	
	public Edge getEdge(Node u, Node v) throws GraphException {
		
		try {
			Edge result = adjMatrix[u.getName()][v.getName()];
			
			if(result == null) return null;
			else return result;
			//Edge is verified to be in graph
		} catch(Exception e) {
			throw new GraphException("Edge not found in matrix");
		}
	}
	
	
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		
		try {
			Edge result = adjMatrix[u.getName()][v.getName()];
			
			if(result != null) return true;
			
		} catch(Exception e) {
			throw new GraphException("Nodes are not adjacent");
		}
		//Default to false if the two nodes are not in adjMatrix 
		return false;
		
	}
	

}
