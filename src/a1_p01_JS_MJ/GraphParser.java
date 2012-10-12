package a1_p01_JS_MJ;

import java.io.*;
import java.util.Scanner;

import org.jgrapht.graph.*;

public class GraphParser {
	
	public static AbstractBaseGraph<String, DefaultEdge> parseGraphFile(String filename) throws IOException {
		File file = new File(filename);
		Scanner scanner = new Scanner(new FileReader(file));
	    try {
	    	// Erste Zeile: #gerichtet|ungerichtet
	    	// Zweite Zeile: [#attributiert] | [#gewichted] | [#attributiert,gewichted]
	    	
	    	// <NameKnoten1>[,<Attribut1>],<NameEcke2>[,<Attribut2>][,<Gewicht>]
	    	
	    	String firstLine = scanner.nextLine();
	    	
	    	// Hier müsste man schauen ob es die zweite Line überhaupt gibt und den Scanner ggf. zurück setzen. 
	    	// String secondLine = scanner.nextLine();

	    	// nur gerichtet
	    	// gerichtet und gewichtet
	    	// gerichtet und gewichtet und attributiert
	    	
	    	// nur ungerichtet
	    	// ungerichtet und gewichtet
	    	// ungerichtet und gewichtet und attributiert
	    	
	    	if(firstLine.equalsIgnoreCase("#gerichtet")){
	    		return parseDircetedGraph(scanner);
	    		
	    	} else if(firstLine.equalsIgnoreCase("#ungerichtet")){
	    		return parseUndircetedGraph(scanner);
	    		
	    	} else {
	    		throw new IOException("Falscher Header");
	    	}
	    	
	      }
	      finally {
	        scanner.close();
	      }
	}
	
	private static Pseudograph<String, DefaultEdge> parseUndircetedGraph(Scanner scanner){
		Pseudograph<String, DefaultEdge> graph = 
				new Pseudograph<String, DefaultEdge>(DefaultEdge.class);
		
		String[] line; 
    	while (scanner.hasNextLine()){
    		line = splitLine(scanner.nextLine());
    		graph.addVertex(line[0]);
    		graph.addVertex(line[1]);
    		graph.addEdge(line[0], line[1]);
	    }
    	
    	return graph;
	}
	
	private static DirectedMultigraph<String, DefaultEdge> parseDircetedGraph(Scanner scanner){
		DirectedMultigraph<String, DefaultEdge> graph = 
				new DirectedMultigraph<String, DefaultEdge>(DefaultEdge.class);
		
		String[] line; 
    	while (scanner.hasNextLine()){
    		line = splitLine(scanner.nextLine());
    		graph.addVertex(line[0]);
    		graph.addVertex(line[1]);
    		graph.addEdge(line[0], line[1]);
	    }
    	
    	return graph;
	}
	
	private static String[] splitLine(String line){
		return line.split(",");
	}
	
	private void print(String s){
		System.out.println(s);
	}
}
