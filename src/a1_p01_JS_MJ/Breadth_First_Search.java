package a1_p01_JS_MJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.*;

public class Breadth_First_Search {

	public List<String> searchShortestPath(AbstractBaseGraph<String, DefaultEdge> graph, String start, String end){
		Map<String, Integer> closed = new HashMap<String, Integer>();
		List<String> open = new ArrayList<String>();
//		int accesses = 0;
		int layer = 0;
		
		open.add(start);
		closed.put(start, 0);
		
		while (!open.isEmpty()){
			String vertex = open.remove(0);
			layer+=1;
			
			if (vertex == end){
				break;
			}
			List<String> neighbor = Graphs.neighborListOf(graph, vertex);
			for(String s : neighbor){
				if(!closed.containsKey(s)){
					System.out.println("conditional: "+!closed.containsKey(s));
					System.out.println("BUM! von "+ vertex +" zu "+ s +" mit "+ layer);
					if(!open.contains(s)){
						open.add(s);
					}
					closed.put(s, layer);
				}
			}
		}
		
//		int length = closed.get(end);
//		System.out.println("length "+ length);
//		List<String> path = new ArrayList<String>(length+1);
//		for(int i=0; i <= length; i++){
//			path.add("");
//		}
//		
//		path.add(length, end);
//		for(int i=length; i>=0; i--){
//			List<String> neighbor = Graphs.neighborListOf(graph, path.get(i));
//			for(String s : neighbor){
//				if(closed.get(s) == i-1){
//					path.add(i-1, s);
//					break;
//				}
//			}
//		}
		
		int length = closed.get(end);
		System.out.println("length "+ length);
		String[] path = new String[length+1];
		
		path[length] = end;
		for(int i=length; i>=0; i--){
			List<String> neighbor = Graphs.neighborListOf(graph, path[i]);
			for(String s : neighbor){
				if(closed.get(s) == i-1){
					path[i-1] = s;
					break;
				}
			}
		}
		
		
		for(String s : path){
			System.out.print(s +", ");
		}
		System.out.println();
		System.out.println("path size" + path.length);
		
		List<String> pathList = new ArrayList<String>();
		for(int i=0; i<path.length;i++){
			pathList.add(path[i]);
		}
			
		
		return pathList;
	}
}
