package a1_p01_JS_MJ;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.*;

public class GraphvizAdapter {

	public static void buildDotFile(String graphName, AbstractBaseGraph<String, DefaultEdge> graph){
	
		int graphClass = 0;
		if( graph instanceof DirectedGraph){
			graphClass = 1;
		}		
		
		StringBuilder dotGraph = new StringBuilder(200);
		dotGraph.append("digraph "+ graphName +" { \n");
		String connector = "->";
		
		dotGraph.append("label=\""+ graphName +"\"" +
				"size=\"36,36\";\n" +
				"node [fontname=\"Verdana\", size=\"30,30\", style=filled];\n" +
				"overlap = scale;\n" +
				"splines = true;\n");
		
		Set<DefaultEdge> edges = graph.edgeSet();
		for (DefaultEdge e : edges) {
			dotGraph.append( 
					graph.getEdgeSource(e) + " " + connector + " " + graph.getEdgeTarget(e) + 
					" [ label = \"" + graph.getEdgeSource(e) + "/" + graph.getEdgeTarget(e) + "\"");
			
			if( graphClass == 0 ){
				dotGraph.append(", dir=none");
			}
			
			dotGraph.append(" ];\n");
		}
		
		dotGraph.append("}");
		
		writeDotFile(graphName, dotGraph);
	}
	
	private static void writeDotFile(String graphName, StringBuilder dotGraph){
		try {
			File file = new File(graphName+".dot");
			FileWriter fw = new FileWriter(file);
			
			fw.write(dotGraph.toString());
			
			fw.flush();
			fw.close();
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
	}
	
	public static void compileDotFile(String dotFileName){
		String cmd = "/usr/local/bin/dot -Tpng -o" + dotFileName + ".png "+ dotFileName +".dot";
		try {
			Process process = Runtime.getRuntime().exec(cmd);
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
	
	public static void buildDotFileWithPathHighlighting(String graphName, AbstractBaseGraph<String, DefaultEdge> graph, List<String> path){
		
		Set<DefaultEdge> pathEdges = new HashSet<DefaultEdge>();
		for(int i=0; i < path.size()-1; i++){
			pathEdges.add(graph.getEdge(path.get(i), path.get(i+1)));
		}
		
		
		int graphClass = 0;
		if( graph instanceof DirectedGraph){
			graphClass = 1;
		}		
		
		StringBuilder dotGraph = new StringBuilder(200);
		dotGraph.append("digraph "+ graphName +" { \n");
		String connector = "->";
		
		dotGraph.append("label=\""+ graphName +"\"" +
				"size=\"36,36\";\n" +
				"node [fontname=\"Verdana\", size=\"30,30\", style=filled];\n" +
				"overlap = scale;\n" +
				"splines = true;\n");
		
		Set<DefaultEdge> edges = graph.edgeSet();
		for (DefaultEdge e : edges) {
			dotGraph.append( 
					graph.getEdgeSource(e) + " " + connector + " " + graph.getEdgeTarget(e) + 
					" [ label = \"" + graph.getEdgeSource(e) + "/" + graph.getEdgeTarget(e) + "\"");
			
			if( graphClass == 0 ){
				dotGraph.append(", dir=none");
			}
			if( pathEdges.contains(e)){
				dotGraph.append(", color=\"red\"");
			}
			
			dotGraph.append(" ];\n");
		}
		
		dotGraph.append("}");
		
		writeDotFile(graphName, dotGraph);
	}
	
	public static void buildDotFileWithSearchResult(String graphName, SearchResult result){
		
		ArrayList<String> path = (ArrayList<String>) result.getPath();
		AbstractBaseGraph<String, DefaultEdge> graph = result.getGraph();
		Map<String, Integer> nodeLable = result.getNodeMap();
		
		Set<DefaultEdge> pathEdges = new HashSet<DefaultEdge>();
		for(int i=0; i < path.size()-1; i++){
			pathEdges.add(graph.getEdge(path.get(i), path.get(i+1)));
		}
		
		int graphClass = 0;
		if( graph instanceof DirectedGraph){
			graphClass = 1;
		}		
		
		StringBuilder dotGraph = new StringBuilder(200);
		dotGraph.append("digraph "+ graphName +" { \n");
		String connector = "->";
		
		dotGraph.append("label=\""+ graphName +"\"\n" +
				"size=\"36,36\";\n" +
//				"node [color=grey, style=filled];\n" +
				"node [fontname=\"Verdana\", size=\"30,30\", style=filled];\n" +
				"overlap = scale;\n" +
				"splines = true;\n");
//		

		
		Set<DefaultEdge> edges = graph.edgeSet();
		for (DefaultEdge e : edges) {
			dotGraph.append( 
					graph.getEdgeSource(e) + " " + connector + " " + graph.getEdgeTarget(e) + 
					" [ label = \"" + graph.getEdgeSource(e) + "/" + graph.getEdgeTarget(e) + "\"");
			
			if( graphClass == 0 ){
				dotGraph.append(", dir=none");
			}
			if( pathEdges.contains(e)){
				dotGraph.append(", color=\"red\"");
			}
			
			dotGraph.append(" ];\n");
		}
		
//		for(String node : nodeLable.keySet()){
//			dotGraph.append(node+" [ label =\"" + node + " ("+ nodeLable.get(node) +") \" ];\n");
//		}
		
		dotGraph.append("}");
		
		writeDotFile(graphName, dotGraph);
	}
}