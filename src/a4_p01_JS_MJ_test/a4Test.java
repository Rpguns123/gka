package a4_p01_JS_MJ_test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;
import org.junit.BeforeClass;
import org.junit.Test;

import a1_p01_JS_MJ.GraphParser;
import a1_p01_JS_MJ.GraphvizAdapter;
import a1_p01_JS_MJ.SearchResult;
import a2_p01_JS_MJ.AttributedNode;
import a2_p01_JS_MJ.GraphGenerator;
import a3_p01_JS_MJ.Kruskal;
import a4_p01_JS_MJ.Fleury;
import a4_p01_JS_MJ.Hierholzer;

public class a4Test {

	static Graph graphk5;
	static Pseudograph<String, DefaultEdge> genGraph;
	
	@BeforeClass
	public static void init() {		
		try {
			graphk5 = GraphParser.parseGraphFile("GraphK5.gka");
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		genGraph = GraphGenerator.generateEulBig(300);
	}
	
	@Test
	public void fleury_test(){
		Fleury<String> fleury = new Fleury<String>();
		SearchResult result = fleury.search((Pseudograph<String, DefaultEdge>) graphk5);
		printTestResult("Test 1: Fleury - Graph K5", result);
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	@Test
	public void fleury_test_big(){
		Fleury<String> fleury = new Fleury<String>();
		SearchResult result = fleury.search((Pseudograph<String, DefaultEdge>) genGraph);
		printTestResult("Test 2: Fleury - Graph Big", result);
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	@Test
	public void hierholzer_test(){
		Hierholzer<String> hierholzer = new Hierholzer<String>();
		SearchResult result = hierholzer.algorithm
				((Pseudograph<String, DefaultEdge>) graphk5);
		printTestResult("Test 1: Hierholzer - Graph K5", result);
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	@Test
	public void hierholzer_test_big(){
		Hierholzer<String> hierholzer = new Hierholzer<String>();
		SearchResult result = hierholzer.algorithm
				((Pseudograph<String, DefaultEdge>) genGraph);
		printTestResult("Test 1: Hierholzer - Graph Big", result);
		System.out.println("Test Green.");
		System.out.println("");
	}
	
	public void printTestResult(String testname, SearchResult result){
		System.out.println(testname);
		System.out.print("Path: ");
		for(DefaultEdge s : (ArrayList<DefaultEdge>) result.getPath()){
			System.out.print(s+", ");
		}
		System.out.println("");
		System.out.println("Path Length " + (result.getPathLength()+1));
		System.out.println("Accssess Counter: " + result.getAccsessCounter());
	}
}
