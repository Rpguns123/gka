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

public class a1Test {

	static AbstractBaseGraph<String, DefaultEdge> graph1;
	static AbstractBaseGraph<String, DefaultEdge> graph2;
	static AbstractBaseGraph<String, DefaultEdge> graphk5;
	static List<String> shortestPath1;
	static List<String> shortestPath2;
	 
	@SuppressWarnings("serial")
	@BeforeClass
	public static void init() {
		
		try {
			graph1 = GraphParser.parseGraphFile("Graph1.gka");
			graph2 = GraphParser.parseGraphFile("Graph2.gka");
			graphk5 = GraphParser.parseGraphFile("GraphK5.gka");
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
		//Von A zu allen Anderen	
		assertEquals(shortestPath1, result);
		assertEquals(4, result.size()-1);
		
		/*try {
			GraphvizAdapter.buildDotFileWithPathHighlighting("breadth_first_graph1", graph1, result);
			GraphvizAdapter.compileDotFile("breadth_first_graph1");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}*/
	}
	
	@Test
	public void breadth_first_graph2_test() {
		List<String> result = Breadth_First_Search.searchShortestPath(graph2, "a", "f");
		assertEquals(shortestPath2, result);
		assertEquals(2, result.size()-1);
		
		/*try {
			GraphvizAdapter.buildDotFileWithPathHighlighting("breadth_first_graph2", graph2, result);
			GraphvizAdapter.compileDotFile("breadth_first_graph2");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}*/
	}
	
	@SuppressWarnings("serial")
	@Test
	public void breadth_first_graphK5_test() {
		assertEquals(new ArrayList<String>(){{ add("a");add("b"); }}, Breadth_First_Search.searchShortestPath(graphk5, "a", "b"));
		assertEquals(new ArrayList<String>(){{ add("a");add("c"); }}, Breadth_First_Search.searchShortestPath(graphk5, "a", "c"));
		assertEquals(new ArrayList<String>(){{ add("a");add("d"); }}, Breadth_First_Search.searchShortestPath(graphk5, "a", "d"));
		assertEquals(new ArrayList<String>(){{ add("a");add("e"); }}, Breadth_First_Search.searchShortestPath(graphk5, "a", "e"));
		assertEquals(new ArrayList<String>(){{ add("a");add("f"); }}, Breadth_First_Search.searchShortestPath(graphk5, "a", "f"));
		//Von B zu allen Anderen
		assertEquals(new ArrayList<String>(){{ add("b");add("a"); }}, Breadth_First_Search.searchShortestPath(graphk5, "b", "a"));
		assertEquals(new ArrayList<String>(){{ add("b");add("c"); }}, Breadth_First_Search.searchShortestPath(graphk5, "b", "c"));
		assertEquals(new ArrayList<String>(){{ add("b");add("d"); }}, Breadth_First_Search.searchShortestPath(graphk5, "b", "d"));
		assertEquals(new ArrayList<String>(){{ add("b");add("e"); }}, Breadth_First_Search.searchShortestPath(graphk5, "b", "e"));
		assertEquals(new ArrayList<String>(){{ add("b");add("f"); }}, Breadth_First_Search.searchShortestPath(graphk5, "b", "f"));
		//Von C zu allen Anderen
		assertEquals(new ArrayList<String>(){{ add("c");add("b"); }}, Breadth_First_Search.searchShortestPath(graphk5, "c", "b"));
		assertEquals(new ArrayList<String>(){{ add("c");add("a"); }}, Breadth_First_Search.searchShortestPath(graphk5, "c", "a"));
		assertEquals(new ArrayList<String>(){{ add("c");add("d"); }}, Breadth_First_Search.searchShortestPath(graphk5, "c", "d"));
		assertEquals(new ArrayList<String>(){{ add("c");add("e"); }}, Breadth_First_Search.searchShortestPath(graphk5, "c", "e"));
		assertEquals(new ArrayList<String>(){{ add("c");add("f"); }}, Breadth_First_Search.searchShortestPath(graphk5, "c", "f"));
		//Von D zu allen Anderen
		assertEquals(new ArrayList<String>(){{ add("d");add("b"); }}, Breadth_First_Search.searchShortestPath(graphk5, "d", "b"));
		assertEquals(new ArrayList<String>(){{ add("d");add("c"); }}, Breadth_First_Search.searchShortestPath(graphk5, "d", "c"));
		assertEquals(new ArrayList<String>(){{ add("d");add("a"); }}, Breadth_First_Search.searchShortestPath(graphk5, "d", "a"));
		assertEquals(new ArrayList<String>(){{ add("d");add("e"); }}, Breadth_First_Search.searchShortestPath(graphk5, "d", "e"));
		assertEquals(new ArrayList<String>(){{ add("d");add("f"); }}, Breadth_First_Search.searchShortestPath(graphk5, "d", "f"));
		//Von E zu allen Anderen
		assertEquals(new ArrayList<String>(){{ add("e");add("b"); }}, Breadth_First_Search.searchShortestPath(graphk5, "e", "b"));
		assertEquals(new ArrayList<String>(){{ add("e");add("c"); }}, Breadth_First_Search.searchShortestPath(graphk5, "e", "c"));
		assertEquals(new ArrayList<String>(){{ add("e");add("d"); }}, Breadth_First_Search.searchShortestPath(graphk5, "e", "d"));
		assertEquals(new ArrayList<String>(){{ add("e");add("a"); }}, Breadth_First_Search.searchShortestPath(graphk5, "e", "a"));
		assertEquals(new ArrayList<String>(){{ add("e");add("f"); }}, Breadth_First_Search.searchShortestPath(graphk5, "e", "f"));
		//Von F zu allen Anderen
		assertEquals(new ArrayList<String>(){{ add("f");add("b"); }}, Breadth_First_Search.searchShortestPath(graphk5, "f", "b"));
		assertEquals(new ArrayList<String>(){{ add("f");add("c"); }}, Breadth_First_Search.searchShortestPath(graphk5, "f", "c"));
		assertEquals(new ArrayList<String>(){{ add("f");add("d"); }}, Breadth_First_Search.searchShortestPath(graphk5, "f", "d"));
		assertEquals(new ArrayList<String>(){{ add("f");add("e"); }}, Breadth_First_Search.searchShortestPath(graphk5, "f", "e"));
		assertEquals(new ArrayList<String>(){{ add("f");add("a"); }}, Breadth_First_Search.searchShortestPath(graphk5, "f", "a"));
		
		
		/*try {
			GraphvizAdapter.buildDotFileWithPathHighlighting("breadth_first_graph2", graph2, result);
			GraphvizAdapter.compileDotFile("breadth_first_graph2");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}*/
	}
	
	
	
	@Test
	public void depth_first_graph1_test() {
		List<String> result = Depth_First_Search.searchShortestPath(graph1, "a", "f");
		assertEquals(shortestPath1, result);
		assertEquals(4, result.size()-1);
		
//		try {
//			GraphvizAdapter.buildDotFileWithPathHighlighting("depth_first_graph1", graph1, result);
//			GraphvizAdapter.compileDotFile("depth_first_graph1");
//		} catch(Exception ex){
//			fail("Could not draw Graph file.");
//		}
	}
	
	@Test
	public void deapth_first_graph2_test() {
		List<String> result = Depth_First_Search.searchShortestPath(graph2, "a", "f");
		assertEquals(shortestPath2, result);
		assertEquals(2, result.size()-1);
		
//		try {
//			GraphvizAdapter.buildDotFileWithPathHighlighting("depth_first_graph2", graph2, result);
//			GraphvizAdapter.compileDotFile("depth_first_graph2");
//		} catch(Exception ex){
//			fail("Could not draw Graph file.");
//		}
	}
	@SuppressWarnings("serial")
	@Test
	public void deapth_first_graphK5_test() {
		assertEquals(new ArrayList<String>(){{ add("a");add("b"); }}, Depth_First_Search.searchShortestPath(graphk5, "a", "b"));
		assertEquals(new ArrayList<String>(){{ add("a");add("c"); }}, Depth_First_Search.searchShortestPath(graphk5, "a", "c"));
		assertEquals(new ArrayList<String>(){{ add("a");add("d"); }}, Depth_First_Search.searchShortestPath(graphk5, "a", "d"));
		assertEquals(new ArrayList<String>(){{ add("a");add("e"); }}, Depth_First_Search.searchShortestPath(graphk5, "a", "e"));
		assertEquals(new ArrayList<String>(){{ add("a");add("f"); }}, Depth_First_Search.searchShortestPath(graphk5, "a", "f"));
		//Von B zu allen Anderen
		assertEquals(new ArrayList<String>(){{ add("b");add("a"); }}, Depth_First_Search.searchShortestPath(graphk5, "b", "a"));
		assertEquals(new ArrayList<String>(){{ add("b");add("c"); }}, Depth_First_Search.searchShortestPath(graphk5, "b", "c"));
		assertEquals(new ArrayList<String>(){{ add("b");add("d"); }}, Depth_First_Search.searchShortestPath(graphk5, "b", "d"));
		assertEquals(new ArrayList<String>(){{ add("b");add("e"); }}, Depth_First_Search.searchShortestPath(graphk5, "b", "e"));
		assertEquals(new ArrayList<String>(){{ add("b");add("f"); }}, Depth_First_Search.searchShortestPath(graphk5, "b", "f"));
		//Von C zu allen Anderen
		assertEquals(new ArrayList<String>(){{ add("c");add("b"); }}, Depth_First_Search.searchShortestPath(graphk5, "c", "b"));
		assertEquals(new ArrayList<String>(){{ add("c");add("a"); }}, Depth_First_Search.searchShortestPath(graphk5, "c", "a"));
		assertEquals(new ArrayList<String>(){{ add("c");add("d"); }}, Depth_First_Search.searchShortestPath(graphk5, "c", "d"));
		assertEquals(new ArrayList<String>(){{ add("c");add("e"); }}, Depth_First_Search.searchShortestPath(graphk5, "c", "e"));
		assertEquals(new ArrayList<String>(){{ add("c");add("f"); }}, Depth_First_Search.searchShortestPath(graphk5, "c", "f"));
		//Von D zu allen Anderen
		assertEquals(new ArrayList<String>(){{ add("d");add("b"); }}, Depth_First_Search.searchShortestPath(graphk5, "d", "b"));
		assertEquals(new ArrayList<String>(){{ add("d");add("c"); }}, Depth_First_Search.searchShortestPath(graphk5, "d", "c"));
		assertEquals(new ArrayList<String>(){{ add("d");add("a"); }}, Depth_First_Search.searchShortestPath(graphk5, "d", "a"));
		assertEquals(new ArrayList<String>(){{ add("d");add("e"); }}, Depth_First_Search.searchShortestPath(graphk5, "d", "e"));
		assertEquals(new ArrayList<String>(){{ add("d");add("f"); }}, Depth_First_Search.searchShortestPath(graphk5, "d", "f"));
		//Von E zu allen Anderen
		assertEquals(new ArrayList<String>(){{ add("e");add("b"); }}, Depth_First_Search.searchShortestPath(graphk5, "e", "b"));
		assertEquals(new ArrayList<String>(){{ add("e");add("c"); }}, Depth_First_Search.searchShortestPath(graphk5, "e", "c"));
		assertEquals(new ArrayList<String>(){{ add("e");add("d"); }}, Depth_First_Search.searchShortestPath(graphk5, "e", "d"));
		assertEquals(new ArrayList<String>(){{ add("e");add("a"); }}, Depth_First_Search.searchShortestPath(graphk5, "e", "a"));
		assertEquals(new ArrayList<String>(){{ add("e");add("f"); }}, Depth_First_Search.searchShortestPath(graphk5, "e", "f"));
		//Von F zu allen Anderen
		assertEquals(new ArrayList<String>(){{ add("f");add("b"); }}, Depth_First_Search.searchShortestPath(graphk5, "f", "b"));
		assertEquals(new ArrayList<String>(){{ add("f");add("c"); }}, Depth_First_Search.searchShortestPath(graphk5, "f", "c"));
		assertEquals(new ArrayList<String>(){{ add("f");add("d"); }}, Depth_First_Search.searchShortestPath(graphk5, "f", "d"));
		assertEquals(new ArrayList<String>(){{ add("f");add("a"); }}, Depth_First_Search.searchShortestPath(graphk5, "f", "a"));
		assertEquals(new ArrayList<String>(){{ add("f");add("a"); }}, Depth_First_Search.searchShortestPath(graphk5, "f", "a"));
		
		
		
//		try {
//			GraphvizAdapter.buildDotFileWithPathHighlighting("depth_first_graph2", graph2, result);
//			GraphvizAdapter.compileDotFile("depth_first_graph2");
//		} catch(Exception ex){
//			fail("Could not draw Graph file.");
//		}
	}
}
