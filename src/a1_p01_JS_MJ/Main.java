package a1_p01_JS_MJ;

import java.io.IOException;

import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;

public class Main {

	public static void main (String args[]){
		
		AbstractBaseGraph<String, DefaultEdge> graph = null;
		String graphName = "Graph1"; 
		
		GraphParser graphParser = new GraphParser();
		try {
			graph = graphParser.parseGraphFile(graphName +".gka");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("DFS");
		Depth_First_Search.searchShortestPath(graph, "a", "f");
		System.out.println("BFS");
		Breadth_First_Search.searchShortestPath(graph, "a", "f");
		GraphvizAdapter gravizAdapter = new GraphvizAdapter();
		gravizAdapter.buildDotFile(graphName, graph);
	}
}
