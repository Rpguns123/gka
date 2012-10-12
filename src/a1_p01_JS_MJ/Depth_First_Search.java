package a1_p01_JS_MJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.*;

public class Depth_First_Search {

	public static List<String> searchShortestPath(
			AbstractBaseGraph<String, DefaultEdge> graph, String start,
			String end) {
		if (!graph.containsVertex(end))
			return new ArrayList<String>();
		List<String> open = new ArrayList<String>();
		open.add(start);
		if (start == end)
			return open;

		Map<String, Integer> closed = new HashMap<String, Integer>();
		closed.put(start, 0);
		/*closed.putAll(*/dfs_rek1(open, closed, end, 0, graph);
		/*
		 * while (!open.isEmpty()) {
		 * 
		 * String vertex = open.remove(0); if (vertex == end) { break; }
		 * List<String> neighbours = Graphs.neighborListOf(graph, vertex);
		 * String neigh1 = neighbours.remove(0); closed.put(neigh1,
		 * closed.get(vertex));
		 * 
		 * }
		 */
		
		return createPath(closed,end,graph);
	}
	
	private static List<String> createPath(Map<String,Integer> closed,String end,AbstractBaseGraph<String, DefaultEdge> graph)
	{
		List<String> ret = new ArrayList<String>();
		if(closed.containsKey(end)){
			int count = closed.get(end);
			String[] path = new String[count+1];
			path[count]=end;
			for (count = closed.get(end);count>0;--count){
				for (String s : Graphs.neighborListOf(graph, path[count]))
				{
					int i = count; 
					if (i>closed.get(s) && graph.containsEdge(s, path[count])){// i= closed.get(s)+1;
						i = closed.get(s);
						path[count-1] = s; 
					}
				}				
			}			
			for (String s : path)
			{
				ret.add(s);
			}
		}
		return ret;
	}

	/**
	 * Erste Rekursive Methode;
	 * @param l
	 * @param m
	 * @param end
	 * @param count
	 * @param g
	 * @return
	 */
	
	
	private static Map<String, Integer> dfs_rek1(List<String> l,
			Map<String, Integer> m, String end, int count,
			AbstractBaseGraph<String, DefaultEdge> g) {

		int i = count;
		while (!l.isEmpty()) {
			String s = l.remove(l.size() - 1);
			/*if (!m.containsKey(s) || i + 1 < m.get(s)){
				i+=1;
			m.put(s, i);}*/
			List<String> t = Graphs.neighborListOf(g, s);
			
			for (String ss : t)
			{
				if ((!m.containsKey(ss)|| m.get(ss)>(m.get(s)+1))&& g.containsEdge(s, ss)){ 
					m.put(ss, m.get(s)+1);

					if (!l.contains(ss))
						l.add(ss);
				}
			}
			
			
			//List<String> ll = new ArrayList<String>();
			//ll.add(l.get(l.size()-1));
			dfs_rek1(l,m,end,i,g);
			//dfs_rek3(g, s, end, m.get(s), m);

		}

		return null;
	}
}