package a1_p01_JS_MJ_test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;
import org.junit.BeforeClass;
import org.junit.Test;

import a1_p01_JS_MJ.Breadth_First_Search;
import a1_p01_JS_MJ.Depth_First_Search;
import a1_p01_JS_MJ.GraphParser;
import a1_p01_JS_MJ.GraphvizAdapter;

public class a1Test {

	static AbstractBaseGraph<String, DefaultEdge> graph1;
	static AbstractBaseGraph<String, DefaultEdge> graph2;
	static List<String> shortestPath1;
	static List<String> shortestPath2;
	 
	@BeforeClass
	public static void init() {
		
		try {
			graph1 = GraphParser.parseGraphFile("Graph1.gka");
			graph2 = GraphParser.parseGraphFile("Graph2.gka");
		} catch(IOException ex) {
			ex.printStackTrace();
		}
		
		shortestPath1 = new ArrayList<String>(){{
			add("a");
//			add("k");
			add("c");
			add("g");
			add("e");
			add("f");
		}};
		
		shortestPath2 = new ArrayList<String>(){{
			add("a");
			add("c");
			add("f");
		}};
	}
	
	@Test
	public void breadth_first_graph1_test() {
		List<String> result = Breadth_First_Search.searchShortestPath(graph1, "a", "f");
		assertEquals(shortestPath1, result);
		assertEquals(4, result.size()-1);
		
		try {
			GraphvizAdapter.buildDotFileWithPathHighlighting("breadth_first_graph1", graph1, result);
			GraphvizAdapter.compileDotFile("breadth_first_graph1");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
	}
	
	@Test
	public void breadth_first_graph2_test() {
		List<String> result = Breadth_First_Search.searchShortestPath(graph2, "a", "f");
		assertEquals(shortestPath2, result);
		assertEquals(2, result.size()-1);
		
		try {
			GraphvizAdapter.buildDotFileWithPathHighlighting("breadth_first_graph2", graph2, result);
			GraphvizAdapter.compileDotFile("breadth_first_graph2");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
	}
	
	@Test
	public void depth_first_graph1_test() {
		List<String> result = Depth_First_Search.searchShortestPath(graph1, "a", "f");
		assertEquals(shortestPath1, result);
		assertEquals(4, result.size()-1);
		
		try {
			GraphvizAdapter.buildDotFileWithPathHighlighting("depth_first_graph1", graph1, result);
			GraphvizAdapter.compileDotFile("depth_first_graph1");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
	}
	
	@Test
	public void deapth_first_graph2_test() {
		List<String> result = Depth_First_Search.searchShortestPath(graph2, "a", "f");
		assertEquals(shortestPath2, result);
		assertEquals(2, result.size()-1);
		
		try {
			GraphvizAdapter.buildDotFileWithPathHighlighting("depth_first_graph2", graph2, result);
			GraphvizAdapter.compileDotFile("depth_first_graph2");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
	}
}
