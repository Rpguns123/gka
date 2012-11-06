package a1_p01_JS_MJ;

import java.io.*;
import java.util.Scanner;

import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.*;

import a2_p01_JS_MJ.AttributedNode;

public class GraphParser {
	
	public static AbstractBaseGraph<String, DefaultEdge> parseGraphFile(String filename) throws IOException {
		File file = new File(filename);
		Scanner scanner = new Scanner(new FileReader(file));
	    try {
	    	// Erste Zeile: #gerichtet|ungerichtet
	    	// Zweite Zeile: [#attributiert] | [#gewichted] | [#attributiert,gewichted]
	    	
	    	// <NameKnoten1>[,<Attribut1>],<NameEcke2>[,<Attribut2>][,<Gewicht>]
	    	
	    	String firstLine = scanner.nextLine();
	    	
	    	// Hier m�sste man schauen ob es die zweite Line �berhaupt gibt und den Scanner ggf. zur�ck setzen. 
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

    public static WeightedGraph<String, DefaultWeightedEdge> parseWeightedGraph(Scanner scanner)
    {
        WeightedGraph<String, DefaultWeightedEdge> graph = new WeightedPseudograph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        String[] line;
        while(scanner.hasNextLine())
        {
            line = splitLine(scanner.nextLine());
            Graphs.addEdgeWithVertices(graph,line[0],line[2]);
            graph.setEdgeWeight(graph.getEdge(line[0],line[2]),Double.parseDouble(line[4]));
        }
        return graph;
    }



    public static WeightedGraph<AttributedNode<String>,DefaultWeightedEdge> parseWeightedAttributedGraph(Scanner scanner)
    {
        WeightedGraph<AttributedNode<String>,DefaultWeightedEdge> graph = new WeightedPseudograph<AttributedNode<String>, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        String[] line;
        while (scanner.hasNextLine())
        {
            line = splitLine(scanner.nextLine());
            AttributedNode<String> n1 =  new AttributedNode<String>(line[0],Double.parseDouble(line[1]));
            AttributedNode<String> n2 =  new AttributedNode<String>(line[2],Double.parseDouble(line[3]));
            Graphs.addEdgeWithVertices(graph,n1,n2);
            graph.setEdgeWeight(graph.getEdge(n1,n2),Double.parseDouble(line[4]));
        }
        return graph;
    }

	private static String[] splitLine(String line){
		return line.split(",");
	}
}
