package a1_p01_jenz_schaa;

import java.io.*;
import java.util.Scanner;

import org.jgrapht.graph.*;

public class GraphParser {

	File file;
	
	public static void main(String args[]){
		GraphParser gp = new GraphParser();
		try {
			gp.parseGraphFile("Graph2.gka");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GraphParser(){
		 
	}
	
	public AbstractBaseGraph<String, DefaultEdge> parseGraphFile(String filename) throws IOException {
		file = new File(filename);
		Scanner scanner = new Scanner(new FileReader(file));
	    try {
	    	// Erste Zeile: #gerichtet|ungerichtet
	    	// Zweite Zeile: [#attributiert] | [#gewichted] | [#attributiert,gewichted]
	    	
	    	//<NameKnoten1>[,<Attribut1>],<NameEcke2>[,<Attribut2>][,<Gewicht>]
	    	
	    	String firstLine = scanner.nextLine();
	    	String secondLine = scanner.nextLine();
	    	
	    	// nur gerichtet
	    	// gerichtet und gewichtet
	    	// gerichtet und gewichtet und attributiert
	    	
	    	// nur ungerichtet
	    	// ungerichtet und gewichtet
	    	// ungerichtet und gewichtet und attributiert
	    	
	    	if(firstLine.equalsIgnoreCase("#gerichtet") && secondLine.equalsIgnoreCase("")){
	    		// Graph ist gerichtet
	    		return parseDircetedGraph(scanner);
	    	} else if(firstLine.equalsIgnoreCase("#ungerichtet")){
	    		// Graph ist ungerichtet
	    		return parseUndircetedGraph(scanner);
	    	} else {
	    		throw new IOException("Falscher Header");
	    	}
	    	
	      }
	      finally {
	        scanner.close();
	      }
	}
	
	private AbstractBaseGraph<String, DefaultEdge> parseUndircetedGraph(Scanner scanner){
		AbstractBaseGraph<String, DefaultEdge> graph = 
				new SimpleGraph<String, DefaultEdge>(DefaultEdge.class);
		
		// Format: v1,v2
		String[] line; 
    	while (scanner.hasNextLine()){
    		line = splitLine(scanner.nextLine());
    		addVertexAndEdge(graph, line[0], line[1]);
	    }
    	
    	return graph;
	}
	
	private AbstractBaseGraph<String, DefaultEdge> parseDircetedGraph(Scanner scanner){
		AbstractBaseGraph<String, DefaultEdge> graph = 
				new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		
		// Format: v1,v2
		String[] line; 
    	while (scanner.hasNextLine()){
    		line = splitLine(scanner.nextLine());
    		addVertexAndEdge(graph, line[0], line[1]);
	    }
    	
    	return graph;
	}
	
	private AbstractBaseGraph<String, DefaultEdge> parseDircetedWeightedGraph(Scanner scanner){
		DefaultDirectedWeightedGraph<String, DefaultEdge> graph = 
				new DefaultDirectedWeightedGraph<String, DefaultEdge>(DefaultEdge.class);
		    	
		// Format: v1,v2,Gewicht
		String[] line; 
    	while (scanner.hasNextLine()){
    		line = splitLine(scanner.nextLine());
    		addVertexAndEdgeWithWeight(graph, line[0], line[1], Double.parseDouble(line[2]));
	    }
		
    	return graph;
	}
	
	private void addVertexAndEdge(AbstractBaseGraph<String, DefaultEdge> graph, String vertex1, String vertex2){
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		graph.addEdge(vertex1, vertex2);
	}
	
	private void addVertexAndEdgeWithWeight(AbstractBaseGraph<String, DefaultEdge> graph, String vertex1, String vertex2, double weight){
		graph.addVertex(vertex1);
		graph.addVertex(vertex2);
		DefaultEdge e = graph.addEdge(vertex1, vertex2);
		graph.setEdgeWeight(e, weight);
	}
	
	private String[] splitLine(String line){
		return line.split(",");
	}
	
	private void print(String s){
		System.out.println(s);
	}
}
