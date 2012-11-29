package a3_p01_JS_MJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import a1_p01_JS_MJ.SearchResult;

public class Kruskal<T> {

	public SearchResult searchSpanningTree(WeightedGraph<T, DefaultWeightedEdge> graph){
		int accesses=0;
		Set<DefaultWeightedEdge> edgeSet = graph.edgeSet();
		ArrayList<DefaultWeightedEdge> edges = new ArrayList<DefaultWeightedEdge>(edgeSet.size());
		ArrayList<Double> weight = new ArrayList<Double>(edgeSet.size());
		for(DefaultWeightedEdge e : edgeSet){
			
			//System.out.println("Test1: "+ e +" ("+graph.getEdgeWeight(e)+"), ");

			accesses++;
			if(edges.isEmpty()){
				edges.add(e);
				weight.add(graph.getEdgeWeight(e));	
			} else {
				double w = graph.getEdgeWeight(e);
				boolean b = true;
				for(int i=0; i<weight.size();i++){
					if(w < weight.get(i)){
						edges.add(i, e);
						weight.add(i, w);
						b = false;
						break;						
					}
				}
				if(b){
					edges.add(e);
					weight.add(w);	
				}
			}
		}
		System.out.println();
		
		Set<T> vertexSet = graph.vertexSet();
		Map<T, Integer> komponents = new HashMap<T, Integer>();
		int komponentId = 1;
		for(T s : vertexSet){
			komponents.put(s, komponentId);
			komponentId++;
		}
		
		List<DefaultWeightedEdge> path = new ArrayList<DefaultWeightedEdge>();
		for(DefaultWeightedEdge e : edges){
			accesses++;
			T source = graph.getEdgeSource(e); 
			T target = graph.getEdgeTarget(e);
			
			if(komponents.get(source) != komponents.get(target)){
				path.add(e);
				
				int idS = komponents.get(source);
				int idT = komponents.get(target);
				
				for(Entry<T, Integer> entry : komponents.entrySet()){
					if(entry.getValue() == idT){
						komponents.put(entry.getKey(), idS);
					}
				}
			}
		}
		
		return new SearchResult(graph, path, null, accesses);
	}
}
