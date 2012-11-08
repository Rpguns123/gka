package a2_p01_JS_MJ_test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.AttributedCharacterIterator.Attribute;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

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
import a2_p01_JS_MJ.AStar;
import a2_p01_JS_MJ.AttributedNode;
import a2_p01_JS_MJ.Dijkstra;
import a2_p01_JS_MJ.GraphGenerator;

public class a2Test {

	static Graph graph3;
	static List<String> Graph3_shortestPath_Husum_Hamburg;
	static List<String> Graph3_shortestPath_Minden_Hamburg;
	static List<String> Graph3_shortestPath_Muenster_Hamburg;

	@BeforeClass
	public static void init() {
		
		try {
			graph3 = GraphParser.parseGraphFile("Graph3B.gka");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		Graph3_shortestPath_Muenster_Hamburg = new ArrayList<String>(){{
			add("Muenster");
			add("Bremen");
			add("Hamburg");
		}};
		
		Graph3_shortestPath_Husum_Hamburg = new ArrayList<String>(){{
			add("Husum");
			add("Kiel");
			add("Uelzen");
			add("Rotenburg");
			add("Soltau");
			add("Buxtehude");
			add("Hamburg");
		}};
		
		Graph3_shortestPath_Minden_Hamburg = new ArrayList<String>(){{
			add("Minden");
			add("Walsrode");
			add("Hamburg");
		}};
	}
	
	@Test
	public void dijkstra_graph3_test1(){
		Dijkstra dijkstra = new Dijkstra();
		
		SearchResult result = dijkstra.searchShortestPath((WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) graph3, "Muenster", "Hamburg");
		printTestResult("Test 1: Dijkstra Muenster_Hamburg - Graph 3", result);
		assertEquals(Graph3_shortestPath_Muenster_Hamburg, result.getPath());
		
		try {
			GraphvizAdapter.buildDotFile("Dijkstra_Muenster_Hamburg", (WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) result.getGraph(), result.getPath());
			GraphvizAdapter.compileDotFile("Dijkstra_Muenster_Hamburg");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");

	}
	
	@Test
	public void dijkstra_graph3_test2(){
		Dijkstra dijkstra = new Dijkstra();
			
		SearchResult result = dijkstra.searchShortestPath((WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) graph3, "Minden", "Hamburg");
		printTestResult("Test 1: Dijkstra Minden_Hamburg - Graph 3", result);
		assertEquals(Graph3_shortestPath_Minden_Hamburg, result.getPath());
		
		try {
			GraphvizAdapter.buildDotFile("Dijkstra_Minden_Hamburg", (WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) result.getGraph(), result.getPath());
			GraphvizAdapter.compileDotFile("Dijkstra_Minden_Hamburg");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");

	}
	
	@Test
	public void dijkstra_graph3_test3(){
		Dijkstra dijkstra = new Dijkstra();

		SearchResult result = dijkstra.searchShortestPath((WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) graph3, "Husum", "Hamburg");
		printTestResult("Test 1: Dijkstra Husum_Hamburg - Graph 3", result);
		assertEquals(Graph3_shortestPath_Husum_Hamburg, result.getPath());
		
		try {
			GraphvizAdapter.buildDotFile("Dijkstra_Husum_Hamburg", (WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) result.getGraph(), result.getPath());
			GraphvizAdapter.compileDotFile("Dijkstra_Husum_Hamburg");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	@Test
	public void astar_graph3_test1(){
		SearchResult result = AStar.searchShortestPath((WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) graph3, "Muenster", "Hamburg");
		printTestResult("Test 2: A-Stern Muenster_Hamburg - Graph 3", result);
		assertEquals(Graph3_shortestPath_Muenster_Hamburg, result.getPath());
		
		try {
			GraphvizAdapter.buildDotFile("A_Stern_Muenster_Hamburg", (WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) result.getGraph(), result.getPath());
			GraphvizAdapter.compileDotFile("A_Stern_Muenster_Hamburg");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	@Test
	public void astar_graph3_test2(){
		SearchResult result = AStar.searchShortestPath((WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) graph3, "Minden", "Hamburg");
		printTestResult("Test 2: A-Stern Minden_Hamburg - Graph 3", result);
		assertEquals(Graph3_shortestPath_Minden_Hamburg, result.getPath());
		
		try {
			GraphvizAdapter.buildDotFile("A_Stern_Minden_Hamburg", (WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) result.getGraph(), result.getPath());
			GraphvizAdapter.compileDotFile("A_Stern_Minden_Hamburg");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	@Test
	public void astar_graph3_test3(){	
		SearchResult result = AStar.searchShortestPath((WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) graph3, "Husum", "Hamburg");
		printTestResult("Test 2: A-Stern Husum_Hamburg - Graph 3", result);
		assertEquals(Graph3_shortestPath_Husum_Hamburg, result.getPath());
		
		try {
			GraphvizAdapter.buildDotFile("A_Stern_Husum_Hamburg", (WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) result.getGraph(), result.getPath());
			GraphvizAdapter.compileDotFile("A_Stern_Husum_Hamburg");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	@Test
	public void graphN_test(){	
		WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> graph = GraphGenerator.generateAttributedWeightedGraph(20);
		Dijkstra dijkstra = new Dijkstra();
		
		SearchResult resultD = dijkstra.searchShortestPath((WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) graph, "19", "target");
		SearchResult resultA = AStar.searchShortestPath((WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) graph, "19", "target");
		
		printTestResult("Test 3: A-Stern Graph N", resultA);
		printTestResult("Test 3: Dijkstra Graph N", resultD);
		assertEquals(resultA.getPath(), resultD.getPath());
		
		try {
			GraphvizAdapter.buildDotFile("graphN", (WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) graph, resultD.getPath());
			GraphvizAdapter.compileDotFile("graphN");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");
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
