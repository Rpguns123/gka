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
import a1_p01_JS_MJ.GraphParser;
import a1_p01_JS_MJ.GraphvizAdapter;

public class Breadth_First_Search_test {

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
			
			add("d");
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
	public void testGraph1() {
		List<String> result = Breadth_First_Search.searchShortestPath(graph1, "a", "f");
		assertEquals(shortestPath1, result);
		assertEquals(4, result.size()-1);
		
		GraphvizAdapter.buildDotFileWithPathHighlighting("graph1_WithPath", graph1, shortestPath1);
		GraphvizAdapter.compileDotFile("graph1_WithPath");
	}
	
	@Test
	public void testGraph2() {
		List<String> result = Breadth_First_Search.searchShortestPath(graph2, "a", "f");
		assertEquals(shortestPath2, result);
		assertEquals(2, result.size()-1);
		
		GraphvizAdapter.buildDotFileWithPathHighlighting("graph2_WithPath", graph2, shortestPath2);
		GraphvizAdapter.compileDotFile("graph2_WithPath");
	}

}
