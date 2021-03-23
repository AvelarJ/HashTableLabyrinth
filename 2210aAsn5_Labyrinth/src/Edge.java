//Jordan Avelar S/N 251083623
//Setters and getters for a simple edge in graphs

public class Edge {

	Node first, second;
	int type;
	String label;
	//Constructor with null label
	public Edge(Node u, Node v, int type) {
		this.first = u;
		this.second = v;
		this.type = type;
		this.label = null;
	}
	//Constructor including label
	public Edge(Node u, Node v, int type, String label) {
		this.first = u;
		this.second = v;
		this.type = type;
		this.label = label;
	}
	
	public Node firstEndpoint() {
		return this.first;
	}
	
	public Node secondEndpoint() {
		return this.second;
	}
	
	public int getType() {
		return this.type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	public String getLabel() {
		return this.label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	
}
