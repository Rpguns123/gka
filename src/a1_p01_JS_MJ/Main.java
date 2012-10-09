package a1_p01_JS_MJ;

import java.io.IOException;

import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;

public class Main {

	public static void main (String args[]){
		
		AbstractBaseGraph<String, DefaultEdge> graph = null;
		String graphName = "Graph2"; 
		
		GraphParser graphParser = new GraphParser();
		try {
			graph = graphParser.parseGraphFile(graphName +".gka");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		GraphvizAdapter gravizAdapter = new GraphvizAdapter();
		gravizAdapter.buildDotFile(graphName, graph);
	}
}
