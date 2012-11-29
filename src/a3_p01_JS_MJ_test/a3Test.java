package a3_p01_JS_MJ_test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.BeforeClass;
import org.junit.Test;

import a1_p01_JS_MJ.GraphParser;
import a1_p01_JS_MJ.GraphvizAdapter;
import a1_p01_JS_MJ.SearchResult;
import a2_p01_JS_MJ.AttributedNode;
import a2_p01_JS_MJ.Dijkstra;
import a2_p01_JS_MJ.GraphGenerator;
import a3_p01_JS_MJ.Kruskal;
import a3_p01_JS_MJ.Prim;

public class a3Test {

	static Graph graph4;
	static Graph graph5;
	static List<DefaultWeightedEdge> graph4_spanningTree;
	static WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> genGraph ;
	@BeforeClass
	public static void init() {		
		try {
			graph4 = GraphParser.parseGraphFile("Graph4.gka");
			graph5 = GraphParser.parseGraphFile("Graph5.gka");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		genGraph = GraphGenerator.generateAttributedWeightedGraph(200, 40);
		graph4_spanningTree = new ArrayList<DefaultWeightedEdge>(){{
			add((DefaultWeightedEdge) graph4.getEdge("c", "a")); //3
			add((DefaultWeightedEdge) graph4.getEdge("a", "b")); //2
			add((DefaultWeightedEdge) graph4.getEdge("h", "b")); //1
			add((DefaultWeightedEdge) graph4.getEdge("e", "b")); //2
			add((DefaultWeightedEdge) graph4.getEdge("i", "c")); //4
			add((DefaultWeightedEdge) graph4.getEdge("k", "c")); //3
			add((DefaultWeightedEdge) graph4.getEdge("b", "j")); //4
			add((DefaultWeightedEdge) graph4.getEdge("g", "d")); //4
			add((DefaultWeightedEdge) graph4.getEdge("g", "e")); //2
			add((DefaultWeightedEdge) graph4.getEdge("e", "f")); //2
		}};
	}
	
	@Test
	public void kruskal_graph4_test(){
		Kruskal<String> kruskal = new Kruskal<String>();
		SearchResult result = kruskal.searchSpanningTree((WeightedGraph<String, DefaultWeightedEdge>) graph4);
		printTestResult("Test 1: Kruskal - Graph 4", result);
		
		System.out.print("Path: ");
		for(DefaultWeightedEdge s : (ArrayList<DefaultWeightedEdge>) graph4_spanningTree){
			System.out.print(s+ " ("+graph4.getEdgeWeight(s)+"), ");
		}
		
		System.out.println();
		
		for(DefaultWeightedEdge s : (ArrayList<DefaultWeightedEdge>) result.getPath()){
			System.out.println("Test THIS: "+ s +" ("+graph4.getEdgeWeight(s)+"), ");
			assertTrue(graph4_spanningTree.contains(s));
		}
		
		try {
			GraphvizAdapter.buildDotFile2("Kruskal_Graph4", (WeightedGraph<String, DefaultWeightedEdge>) result.getGraph(), result.getPath());
			GraphvizAdapter.compileDotFile("Kruskal_Graph4");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	@Test
	public void kruskal_graph5_test(){
		Kruskal<String> kruskal = new Kruskal<String>();
		SearchResult result = kruskal.searchSpanningTree((WeightedGraph<String, DefaultWeightedEdge>) graph5);
		printTestResult("Test Kruskal - Graph 5", result);
		
		double weight = 0.0;
		for(DefaultWeightedEdge s : (ArrayList<DefaultWeightedEdge>) result.getPath()){
			weight += graph5.getEdgeWeight(s);
		}
		
		double[] a = new double[1];
		double[] b = new double[1];
		a[0] = 17.0;
		b[0] = weight;
		assertArrayEquals("Yeag", a, b, 1.0);
		
		try {
			GraphvizAdapter.buildDotFile2("Kruskal_Graph4", (WeightedGraph<String, DefaultWeightedEdge>) result.getGraph(), result.getPath());
			GraphvizAdapter.compileDotFile("Kruskal_Graph4");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	
	@SuppressWarnings("unchecked")
	@Test
	public void prim_graph4_test(){
		Prim<String> prim= new Prim<String>();
		WeightedGraph<String, DefaultWeightedEdge> g = prim.algorithm((WeightedGraph<String, DefaultWeightedEdge>)graph4);
		WeightedGraph<String, DefaultWeightedEdge> g2 = prim.algorithmWithFibo((WeightedGraph<String, DefaultWeightedEdge>)graph4);
		assertTrue(g.edgeSet().containsAll(g2.edgeSet()));
		
		
		Kruskal<String> kruskal = new Kruskal<String>();
		SearchResult res = kruskal.searchSpanningTree((WeightedGraph<String, DefaultWeightedEdge>) graph4);
		assertTrue(res.getGraph().edgeSet().containsAll(g2.edgeSet()));
		
		List<DefaultWeightedEdge> path = new ArrayList<DefaultWeightedEdge>();
		for(DefaultWeightedEdge e : g.edgeSet()){
			System.out.println("Test THIS: "+ e +" ("+graph4.getEdgeWeight(e)+"), ");
//			assertTrue(graph4_spanningTree.contains(e));
			path.add(e);
		}
		
		try {
			GraphvizAdapter.buildDotFile2("Prim_Graph4", (WeightedGraph<String, DefaultWeightedEdge>) g, path);
			GraphvizAdapter.compileDotFile("Prim_Graph4");
		} catch(Exception ex){
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	
	@Test
	public void prim_graphGen_test()
	{
		Prim<AttributedNode<String>> prim = new Prim<AttributedNode<String>>();
		WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> g = prim.algorithm(genGraph);
		WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> g2 = prim.algorithm(genGraph);
		assertTrue(g.edgeSet().containsAll(g2.edgeSet()));		
	}
	
	@Test
	public void bigGraph_test(){
		Prim<AttributedNode<String>> prim = new Prim<AttributedNode<String>>();
		WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> g = prim.algorithm(genGraph);
		
		Kruskal<AttributedNode<String>> kruskal = new Kruskal<AttributedNode<String>>();
		SearchResult result = kruskal.searchSpanningTree((WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) genGraph);
		printTestResult("Test: Kruskal - Big Graph", result);
		
		//assertTrue(g.edgeSet().containsAll(result.getPath()));
		
		System.out.println("Anzahl der Knoten: "+genGraph.vertexSet().size());
		System.out.println("Anzahl der Kanten: "+result.getPath().size());
		
		try {
			GraphvizAdapter.buildDotFile3("BigGraph", (WeightedGraph<AttributedNode<String>, DefaultWeightedEdge>) result.getGraph(), result.getPath());
			GraphvizAdapter.compileDotFile("BigGraph");
		} catch(Exception ex){
			ex.printStackTrace();
			fail("Could not draw Graph file.");
		}
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	public void printTestResult(String testname, SearchResult result){
		System.out.println(testname);
		System.out.print("Path: ");
		for(DefaultWeightedEdge s : (ArrayList<DefaultWeightedEdge>) result.getPath()){
			System.out.print(s+ " ("+graph4.getEdgeWeight(s)+"), ");
		}
		System.out.println("");
		System.out.println("Path Length " + result.getPathLength());
		System.out.println("Accssess Counter: " + result.getAccsessCounter());
	}
}
