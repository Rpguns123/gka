package a2_p01_JS_MJ;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import a2_p01_JS_MJ.AttributedNode;


import a1_p01_JS_MJ.SearchResult;

public class AStar {

	public static SearchResult searchShortestPath(WeightedGraph<AttributedNode<String>,DefaultWeightedEdge> graph, String start, String end){
		
		AttributedNode<String> startNode = null;
		AttributedNode<String> endNode = null;
		
		Map<String, ArrayList<AttributedNode<String>>> neigborList = new HashMap<String, ArrayList< AttributedNode<String>>>();
		for(AttributedNode<String> node : graph.vertexSet()){
			if(node.getValue().equalsIgnoreCase(start)) startNode = node;
			if(node.getValue().equalsIgnoreCase(end)) endNode = node;
			
			ArrayList<AttributedNode<String>> list;
			if(neigborList.containsKey(node.getValue())){
				list = neigborList.get(node.getValue());
			} else {
				list = new ArrayList<AttributedNode<String>>();
			}
			
			for(DefaultWeightedEdge e : graph.edgesOf(node)){
				AttributedNode<String> n = graph.getEdgeTarget(e);
				if(!n.equals(node)){
					list.add(n);
				} else {
					list.add(graph.getEdgeSource(e));
				}
			}
			neigborList.put(node.getValue(), list);
		}
		
		// AttributeNode => G
		Set<AttributedNode<String>> closed = new HashSet<AttributedNode<String>>();
		Map<AttributedNode<String>, Double> open = new HashMap<AttributedNode<String>, Double>();
		Map<String, AttributedNode<String>> predecessor = new HashMap<String, AttributedNode<String>>();
		int acc = 1;
		
		open.put(startNode, startNode.getAttribute());
		predecessor.put(startNode.getValue(), null);
		
		AttributedNode<String> vertex;
		while (!open.isEmpty()){
			vertex = minMap(open);
			double g = open.get(vertex);
			open.remove(vertex);
			
			if (vertex.equals(endNode)){
				break;
			}
			
			for(AttributedNode<String> n : neigborList.get(vertex.getValue())){
				acc++;
				if(!closed.contains(n)){

					DefaultWeightedEdge edge = graph.getEdge(vertex, n);
					if(!open.containsKey(n) || open.get(n) > g + graph.getEdgeWeight(edge)){
						open.put(n, g + graph.getEdgeWeight(edge)); // das Kantengewicht
						predecessor.put(n.getValue(), vertex);
					}
					closed.add(vertex);
				}
			}
		}
		
		LinkedList<String> path = new LinkedList<String>();
		path.addFirst(endNode.getValue());
		
		while(true){
			String node = path.getFirst();
			if(node.equals(startNode.getValue())) break;
			path.addFirst(predecessor.get(node).getValue());
		}
		
		return new SearchResult(graph, (List) path, null, acc);
	}
	
	public static AttributedNode<String> minMap(Map<AttributedNode<String>, Double> map){
		double min = 100000000;
		AttributedNode<String> minNode = null;
		for(Entry<AttributedNode<String>, Double> e : map.entrySet()){
			// G + H = F
			if(e.getValue() + e.getKey().getAttribute() < min){
				min = e.getValue() + e.getKey().getAttribute();
				minNode = e.getKey();
			}
		}
		
		return minNode;
	}
	
	public static void p(String s){
		System.out.println(s);
	}
}


