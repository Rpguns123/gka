package a4_p01_JS_MJ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.alg.EulerianCircuit;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;

import a1_p01_JS_MJ.Breadth_First_Search;
import a1_p01_JS_MJ.Depth_First_Search;
import a1_p01_JS_MJ.SearchResult;

public class Fleury<T extends Comparable<T>> {

	public SearchResult search(Pseudograph<T, DefaultEdge> graph){
		ArrayList<DefaultEdge> eulerianpath = new ArrayList<DefaultEdge>(); 
		int overalledgecount = graph.edgeSet().size();
		int accesses = 0;
		accesses++;
		
		System.out.println("Edges: "+overalledgecount);
		T vertex = random(graph.vertexSet());
		while(eulerianpath.size() != overalledgecount){
			Set<DefaultEdge> edges = graph.edgesOf(vertex);
			Set<DefaultEdge> unmakededges = new HashSet<DefaultEdge>();
			for(DefaultEdge e : edges){
				if(!eulerianpath.contains(e)){
					unmakededges.add(e);
				}
				accesses++;
			}
			
			if(unmakededges.size() == 0) break;
			
			DefaultEdge choosenedge;
			while(true){
				accesses++;
				choosenedge = random(unmakededges);
				if(unmakededges.size() == 1 || isNotBridgeEdge(graph, eulerianpath, choosenedge)) break;
				unmakededges.remove(choosenedge);
			}
			eulerianpath.add(choosenedge);
				
			T s = graph.getEdgeSource(choosenedge);
			T t = graph.getEdgeTarget(choosenedge);
			if(s.equals(vertex)){
				vertex = t;
			} else {
				vertex = s;
			}
			
			System.out.print(vertex+", ");
		}
		
		System.out.println();
		return new SearchResult(graph, eulerianpath, null, accesses);
	}
	
	@SuppressWarnings("unchecked")
	public boolean isNotBridgeEdge(Pseudograph<T, DefaultEdge> graph, ArrayList<DefaultEdge> eulerianpath,  DefaultEdge edge){
		Pseudograph<T, DefaultEdge> g2 = (Pseudograph<T, DefaultEdge>) graph.clone();
		g2.removeEdge(edge);
		for(DefaultEdge e : eulerianpath) g2.removeEdge(e);
//		SearchResult result = Depth_First_Search.searchShortestPath((AbstractBaseGraph<String, DefaultEdge>) g2, (String) graph.getEdgeSource(edge), (String) graph.getEdgeTarget(edge));
		SearchResult result = Breadth_First_Search.searchShortestPath((AbstractBaseGraph<String, DefaultEdge>) g2, (String) graph.getEdgeSource(edge), (String) graph.getEdgeTarget(edge));
		System.out.println("Accesses Search "+result.getAccsessCounter());
		if(result.getPath() != null) return true;
		return false;
	}
	
	public T random(Set<T> set){
		ArrayList<T> arraylist = new ArrayList<T>();
		arraylist.addAll(set);
		return  arraylist.get((int) Math.round(Math.random()*(arraylist.size()-1)));
	}
	
	public DefaultEdge random(Set<DefaultEdge> set){
		ArrayList<DefaultEdge> arraylist = new ArrayList<DefaultEdge>();
		arraylist.addAll(set);
		return  arraylist.get((int) Math.round(Math.random()*(arraylist.size()-1)));
	}
}
