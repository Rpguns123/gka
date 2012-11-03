package a1_p01_JS_MJ;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;

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

        WeightedGraph<AttributedNode<String>,DefaultWeightedEdge> g;

         graphName = "Graph3";

        try {
            File file = new File(graphName+".gka");
            Scanner scanner = new Scanner(new FileReader(file));
            g = GraphParser.parseWeightedAttributedGraph(scanner);
            Dijkstra d = new Dijkstra();
            d.searchShortestPath(g,new AttributedNode<String>("",0.0),new AttributedNode<String>("",0.0));
        } catch (IOException e) {
            e.printStackTrace();
        }

		/*GraphvizAdapter gravizAdapter = new GraphvizAdapter();
		gravizAdapter.buildDotFile(graphName, graph);*/
	}
}
