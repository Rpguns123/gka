package a2_p01_JS_MJ_test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.BeforeClass;
import org.junit.Test;

import a1_p01_JS_MJ.Breadth_First_Search;
import a1_p01_JS_MJ.Depth_First_Search;
import a1_p01_JS_MJ.GraphParser;
import a1_p01_JS_MJ.GraphvizAdapter;
import a1_p01_JS_MJ.SearchResult;
import a2_p01_JS_MJ.AttributedNode;
import a2_p01_JS_MJ.Dijkstra;

public class a2Test {

	static Graph graph3;
	static List<String> Graph3_shortestPath;
	 
	@BeforeClass
	public static void init() {
		
		try {
			graph3 = GraphParser.parseGraphFile("Graph3.gka");
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		GraphvizAdapter.buildDotFile("graph3", (WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) graph3);
		GraphvizAdapter.compileDotFile("graph3");
		
		Graph3_shortestPath = new ArrayList<String>(){{
			add("a");
			add("c");
			add("f");
		}};
	}
	
	@Test
	public void dijkstra_graph3_test(){
		Dijkstra dijkstra = new Dijkstra();
		
		SearchResult result = dijkstra.searchShortestPath((WeightedGraph<String, DefaultWeightedEdge>) graph3, "a", "f");
		printTestResult("Test 1: Dijkstra - Graph 3", result);
		
		assertEquals(Graph3_shortestPath, result.getPath());
		assertEquals(4, result.getPathLength());
	}
	
	public void printTestResult(String testname, SearchResult result){
		System.out.println(testname);
		System.out.print("Path: ");
		for(String s : result.getPath()){
			System.out.print(s+ ", ");
		}
		System.out.println("");
		System.out.println("Path Length " + result.getPathLength());
		System.out.println("Accssess Counter: " + result.getAccsessCounter());
	}
}
