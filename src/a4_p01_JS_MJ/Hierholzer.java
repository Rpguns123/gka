package a4_p01_JS_MJ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgraph.graph.Edge;
import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;

public class Hierholzer<T extends Comparable<T>> {
	
	
	/**
	 * foo
	 * @param graph
	 */
	public void algorithm(UndirectedGraph<T, Edge> graph)
	{
		
		List<List<Edge>> subCircleList = new ArrayList();
		HashSet<Edge> edgeSet = new HashSet(graph.edgeSet());
		List<T> vertexList = new ArrayList(graph.vertexSet());
		Set<T> schnittpunkte = new HashSet();
		for(T v : graph.vertexSet())//PRECONDITIONS
		{	int grad=graph.degreeOf(v) ;
			if((grad%2)!=0)
			{
				throw new IllegalArgumentException();
			}
			else if(grad>2)
			{
				schnittpunkte.add(v);
			}
			else if(grad==0)
			{
				vertexList.remove(v);
			}
		}	
		while(!edgeSet.isEmpty())
			{
				T aktVertex = vertexList.remove(0);
				List<Edge> _tmp = buildSubCircle(aktVertex, edgeSet, graph);
				if(_tmp!=null)
				subCircleList.add(_tmp);							
			}
		//TODO weitermachen
	}
	
	private List<Edge> buildSubCircle(T vertex, HashSet<Edge> edgeSet,UndirectedGraph<T, Edge>graph)
	{
		T aktVertex = vertex;
		List<Edge> subCircle = new ArrayList();
		List<T> subCircleVertex = new ArrayList();
		boolean circleNotClosed = false;
		while (circleNotClosed)
		{
			List<T>neigh= Graphs.neighborListOf(graph, vertex);
			T target;
			if(neigh.contains(aktVertex))
			{
				target=aktVertex;
				neigh.remove(target);
			}else{
			target = neigh.remove(0);
			}
			Edge e = graph.getEdge(aktVertex, target);
			
			while(!edgeSet.contains(e))//Eine Kante finden die noch nicht frei ist.
			{
				try{
					target = neigh.remove(0);
				}catch (IndexOutOfBoundsException ex)
				{
					edgeSet.addAll(subCircle);
					return null;
				}

				e = graph.getEdge(aktVertex, target);
			}
			if(target.equals(vertex))
			{
				circleNotClosed = true;
			}
			subCircle.add(e);
			subCircleVertex.add(target);
			edgeSet.remove(e);
			aktVertex= target; 
		}		
		return subCircle;
	}
	

}
