package a2_p01_JS_MJ;

import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.*;

import a2_p01_JS_MJ.AttributedNode;


import a1_p01_JS_MJ.SearchResult;

public class AStar {

	public static SearchResult searchShortestPath(WeightedGraph<AttributedNode<String>,DefaultWeightedEdge> graph, String start, String end){
		
		HashMap<String, AttributedNode<String>> nodeList = new HashMap<String, AttributedNode<String>>();
		for(AttributedNode<String> node : graph.vertexSet()){
			nodeList.put(node.getValue(), node);
		}
		AttributedNode<String> startNode = nodeList.get(start);
		AttributedNode<String> endNode = nodeList.get(end);
		
		// AttributeNode => G
		Map<AttributedNode<String>, Double> closed = new HashMap<AttributedNode<String>, Double>();
		Map<AttributedNode<String>, Double> open = new HashMap<AttributedNode<String>, Double>();
		Map<AttributedNode<String>, AttributedNode<String>> predecessor = new HashMap<AttributedNode<String>, AttributedNode<String>>();
		int acc = 1;
		
		open.put(startNode, startNode.getAttribute());
		predecessor.put(startNode, null);
		
		AttributedNode<String> vertex;
		while (!open.isEmpty()){
			vertex = minMap(open);
			double g = open.get(vertex);
			
			open.remove(vertex);
			
			if (vertex == endNode){
				break;
			}
			
			List<AttributedNode<String>> neighbor = Graphs.neighborListOf(graph, vertex);
			
			for(AttributedNode<String> n : neighbor){
				acc++;
				if(!closed.containsKey(n) && graph.containsEdge(vertex, n)){

					DefaultWeightedEdge edge = graph.getEdge(vertex, n);
					
					if(!open.containsKey(n) || open.get(n) > g + graph.getEdgeWeight(edge)){
						open.put(n, g + graph.getEdgeWeight(edge)); // das Kantengewicht
						predecessor.put(n, vertex);
					}
					closed.put(vertex, g);
				}
			}
		}
		
		return null;
	}
	
	public static AttributedNode<String> minMap(Map<AttributedNode<String>, Double> map){
		double min = 100000000;
		AttributedNode<String> minNode = null;
		
		for(Entry<AttributedNode<String>, Double> e : map.entrySet()){
			// G + H = F
			if(e.getValue() + e.getKey().getAttribute() < min){
				min = e.getValue();
				minNode = e.getKey();
			}
		}
		
		return minNode;
	}
}


