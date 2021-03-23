//Jordan Avelar S/N 251083623
//Setters and getters to make a simple node for graphs

public class Node {
	//Instance variables
	int name;
	boolean mark;
	
	public Node(int name){
		this.name = name;
	}
	
	public void setMark(boolean mark){
		this.mark = mark;
	}
	
	public boolean getMark() {
		return this.mark;
	}
	
	public int getName() {
		return this.name;
	}
	
}
