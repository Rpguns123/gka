	package a4_p01_JS_MJ_test;

	import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.junit.BeforeClass;
import org.junit.Test;

import a1_p01_JS_MJ.GraphParser;
import a1_p01_JS_MJ.SearchResult;
import a2_p01_JS_MJ.AttributedNode;
import a2_p01_JS_MJ.GraphGenerator;
import a4_p01_JS_MJ.Hierholzer;

	public class a4Test2 {

		static Graph graph4;
		static Graph graph5;
		static List<DefaultWeightedEdge> graph4_spanningTree;
		static WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> genGraph ;
		@SuppressWarnings({ "serial", "unchecked" })
		@BeforeClass
		public static void init() {		
			try {
//				graph4 = GraphParser.parseGraphFile("graphMarcus.gka");
				graph4 = GraphParser.parseGraphFile("graph_selbst3.gka");
				graph5 = GraphParser.parseGraphFile("graph_selbst4.gka");
				
				//graph5 = GraphParser.parseGraphFile("Graph5.gka");
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
		@Test
		public void prim_graphGen_test()
		{			
			Hierholzer<String> h = new Hierholzer<String>();			
			SearchResult res1 = h.algorithm((UndirectedGraph<String, DefaultEdge>)graph4);
			List<DefaultEdge> path = res1.getPath();			
			assertTrue(path.containsAll(graph4.edgeSet()));
			assertTrue(graph4.edgeSet().containsAll(path));
			
			SearchResult res2 = h.algorithm((UndirectedGraph<String, DefaultEdge>)graph5);
			 path = res1.getPath();			
			assertTrue(path.containsAll(graph5.edgeSet()));
			assertTrue(graph5.edgeSet().containsAll(path));
			
			
//			UndirectedGraph<String, DefaultEdge> g = GraphGenerator.generateEulerGraph(10, 4);
//			SearchResult res2 = h.algorithm((UndirectedGraph<String, DefaultEdge>)g);
//			 path = res2.getPath();			
//			assertTrue(path.containsAll(g.edgeSet()));
//			assertTrue(g.edgeSet().containsAll(path));
			
		}
		
	}


