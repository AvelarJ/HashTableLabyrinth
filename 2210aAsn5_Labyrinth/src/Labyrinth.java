//Jordan Avelar S/N 251083623

import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;


public class Labyrinth {
	//Instance variables
	private int width, length, start, finish;
	private int[] k;
	private Graph graph;
	
	
	public Labyrinth(String inputFile) throws LabyrinthException {
		
		try {
			
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			
			Integer.parseInt(in.readLine());	//Skip first line of file
			width = Integer.parseInt(in.readLine());
			length = Integer.parseInt(in.readLine());
			
			String kTmp = in.readLine();	//Temporary k String before picking out each number
			//Insert each value of k into an array spot
			k = new int[kTmp.length()/2];
			for(int i=0;i<Math.ceil(kTmp.length()/2);i++) {
				k[i] = Integer.parseInt(String.valueOf(kTmp.charAt(i*2)));
			}

			graph = new Graph(width * length);
			String line;
			int row = -1;
			Boolean isDoor;
			
			while((line = in.readLine()) != null) {
				
				row++;
				//Loop through all characters on the line
				for(int i=0; i<line.length();i++) {
					
					isDoor = (Character.isDigit(line.charAt(i)));
					
					if(isDoor) {
						//First checks if vertical and on first or last space
						if((i==0 && line.charAt(1)=='w') || (i==line.length()-1) && line.charAt(i-1)=='w') {
							
							graph.insertEdge(graph.getNode((i/2) +((row-1)/2)*width), 
									graph.getNode((i/2) +((row+1)/2)*width), (line.charAt(i)), "Door");
							
						}
						//Check if door is vertical with walls on both sides
						else if(line.charAt(i-1) == 'w' && (line.charAt(i+1) == 'w')) {
							
							graph.insertEdge(graph.getNode((i/2) +((row-1)/2)*width), 
									graph.getNode((i/2) +((row+1)/2)*width), (line.charAt(i)), "Door");
							
						}
						//Must be horizontal
						else {
							graph.insertEdge(graph.getNode((i/2) + (row/2)*width), 
									graph.getNode((i/2) + (row/2)*width+1), (line.charAt(i)), "Door");
						}
					}
					
					else {
						//Starting room
						if(line.charAt(i) == 's') {
							start = (i/2) + (row/2)*width;
						}
						//Final room
						else if(line.charAt(i) == 'x') {
							finish = (i/2) + (row/2)*width;
						}
						
						else if(line.charAt(i) == 'c') {
							//Checks if corridor is first or last
							if((i==0 && line.charAt(1) == 'w') || (i==line.length()-1) && line.charAt(i-1) == 'w') {
								
								graph.insertEdge(graph.getNode((i/2) +((row-1)/2)*width), 
										graph.getNode((i/2) +((row+1)/2)*width), 1, "Corridor");
							}
							//Checks for walls on left and right sides to see if vertical
							else if((line.charAt(i-1) == 'w') && (line.charAt(i+1) == 'w')) {
								
								graph.insertEdge(graph.getNode((i/2) +((row-1)/2)*width), 
										graph.getNode((i/2) +((row+1)/2)*width), 1, "Corridor");
							}
							//Must be horizontal
							else {
								
								graph.insertEdge(graph.getNode((i/2) + (row/2)*width), 
										graph.getNode((i/2) + (row/2)*width+1), 1, "Corridor");
								
							}
						}
					}
				}
			}
			
			in.close();
			
		} catch(Exception e) {
			throw new LabyrinthException("Labyrinth could not be made");
		}
		
	}
	
	
	public Graph getGraph() throws LabyrinthException {
		//Check if graph is not empty
		if(graph == null) {
			throw new LabyrinthException("Undefined graph");
		}
		else {
			return graph;
		}
		
	}
	
	
	public Iterator solve() throws LabyrinthException {
		
		ArrayList<Node> nodeList = new ArrayList<Node>();
		Iterator neighbours;
		Edge uv;
		Node u, first, second;

		//Use stack to make "path" to be followed
		Stack<Node> stack = new Stack<Node>();
		try{
			//u is first node and will be first in stack no matter what
			u = graph.getNode(start);
			u.setMark(true);
			stack.push(u);

			while (stack.isEmpty() != true) {
				//Takes first room from stack to path list
				Node curr = stack.pop();
				nodeList.add(curr);
				
				//Adds edges from curr into neighbours (incedent edges)
				neighbours = graph.incidentEdges(curr);
				
				//If current node is same as final node then must be exit
				if (curr.getName() == finish) {
					Iterator itr = nodeList.iterator();
					return itr;
				}
				//Loops as long as there's still neighbours
				while (neighbours.hasNext()) {
					
					uv = (Edge)neighbours.next();
					first = uv.firstEndpoint();
					second = uv.secondEndpoint();
					
					//If the two node are not equal and not already visited
					if((first.getName() != curr.getName()) && (first.getMark() == false)){
						//True as to be visited
						first.setMark(true);
						stack.push(first);
						
					}
					//Check same for second node
					else if((second.getName() != curr.getName()) && (second.getMark() == false)){
						
						second.setMark(true);
						stack.push(second);
					}
				}
			}
		
		}catch(Exception e){
			throw new LabyrinthException("Cannot solve labyrinth");
		}//Default return of null
		return null;
	}

}
