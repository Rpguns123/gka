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
	    	
	    	// Hier müsste man schauen ob es die zweite Line überhaupt gibt. 
//	    	String secondLine = scanner.nextLine();

	    	// nur gerichtet
	    	// gerichtet und gewichtet
	    	// gerichtet und gewichtet und attributiert
	    	
	    	// nur ungerichtet
	    	// ungerichtet und gewichtet
	    	// ungerichtet und gewichtet und attributiert
	    	
	    	if(firstLine.equalsIgnoreCase("#gerichtet")){
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
	
	private Pseudograph<String, DefaultEdge> parseUndircetedGraph(Scanner scanner){
		Pseudograph<String, DefaultEdge> graph = 
				new Pseudograph<String, DefaultEdge>(DefaultEdge.class);
		
		// Format: v1,v2
		String[] line; 
    	while (scanner.hasNextLine()){
    		line = splitLine(scanner.nextLine());
    		print(line[0] +" to "+line[1]);
    		graph.addVertex(line[0]);
    		graph.addVertex(line[1]);
    		graph.addEdge(line[0], line[1]);
	    }
    	
    	return graph;
	}
	
	private AbstractBaseGraph<String, DefaultEdge> parseDircetedGraph(Scanner scanner){
		DefaultDirectedGraph<String, DefaultEdge> graph = 
				new DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
		
		// Format: v1,v2
		String[] line; 
    	while (scanner.hasNextLine()){
    		line = splitLine(scanner.nextLine());
    		print(line[0] +" to "+line[1]);
    		graph.addVertex(line[0]);
    		graph.addVertex(line[1]);
    		graph.addEdge(line[0], line[1]);
	    }
    	
    	return graph;
	}
	
	private String[] splitLine(String line){
		return line.split(",");
	}
	
	private void print(String s){
		System.out.println(s);
	}
}
