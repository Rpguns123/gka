package a1_p01_JS_MJ;

import java.util.List;
import java.util.Map;

import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultEdge;

public class SearchResult {

	private AbstractBaseGraph<String, DefaultEdge> graph;
	private List<String> path;
	private Map<String, Integer> map;
	private int acceses;
	
	public SearchResult(AbstractBaseGraph<String, DefaultEdge> graph, List<String> path, Map<String, Integer> map, int accesses) {
		this.graph = graph;
		this.path = path;
		this.map = map;
		this.acceses = accesses;
	}
	
	public AbstractBaseGraph<String, DefaultEdge> getGraph(){
		return graph;
	}

	public int getPathLength(){
		return path.size()-1;
	}
	
	public List<String> getPath(){
		return path;
	}
	
	public Map<String, Integer> getNodeMap(){
		return map;
	}
	
	public int getAccsessCounter(){
		return acceses;
	}
	
}
