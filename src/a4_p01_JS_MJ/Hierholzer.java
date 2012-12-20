package a4_p01_JS_MJ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import a1_p01_JS_MJ.SearchResult;

public class Hierholzer<T extends Comparable<T>> {
	
	int accesses = 0;
	/**
	 * foo
	 * @param graph
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SearchResult algorithm(UndirectedGraph<T, DefaultEdge> graph)
	{
		List<List<T>> subCircleNodeList = new ArrayList();
		List<List<MyEdge<T>>> subCircleList = new ArrayList();
		HashSet<DefaultEdge> edgeSet = new HashSet(graph.edgeSet());
		List<T> vertexList = new ArrayList(graph.vertexSet());
		Set<T> schnittpunkte = new HashSet();
		for(T v : graph.vertexSet())//PRECONDITIONS
		{	int grad=graph.degreeOf(v) ;
			accesses++;
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
		//UndirectedGraph<T, DefaultEdge> g;
		int index = 0;
		T aktVertex = vertexList.remove(0);
		while(!edgeSet.isEmpty())
		{
			while(!subCircleNodeList.isEmpty() && !subCircleNodeList.get(index).isEmpty())
			{
				boolean found = false;
				for(int i = 1;i<subCircleNodeList.get(index).size();++i)
				{
					if (graph.degreeOf(subCircleNodeList.get(index).get(i))>0)
					{
						aktVertex = subCircleNodeList.get(index).get(i);
//						index++;
						found = true;
						break;
					}						
				}
				if(!found)
				{
					index--;
				}else
				{
					index++;
					break;
				}
			}
			
			if(graph.degreeOf(aktVertex)>0){
				List<MyEdge<T>> _tmp = buildSubCircle2(aktVertex, edgeSet, graph,subCircleNodeList);
				if(_tmp!=null)
					subCircleList.add(_tmp);
			}
		}
		for (List<MyEdge<T>> l : subCircleList)
		{
			for (MyEdge<T> e : l)
			{
				
				graph.addEdge((T)e.source, (T)e.target, e.edge);
				
			}
		}
		List<DefaultEdge> path = createPath(subCircleList,graph,subCircleNodeList);		
		//TODO weitermachen
		return new SearchResult(graph, path, null, accesses);
	}
	

	
	private List<DefaultEdge> createPath(List<List<MyEdge<T>>> subCircleList, UndirectedGraph<T, DefaultEdge> graph,List<List<T>> subCircleNodeList) {
		List<DefaultEdge> path = new ArrayList();
		List<Integer> edgeNumbers = new ArrayList(subCircleList.size());
		boolean pathUnfinished = true;
		int index = 0;
		for (int i=0;i<subCircleList.size();++i)
		{
			edgeNumbers.add(0);
		}
		T target=null;
		while(pathUnfinished)
		{
			if(index>=subCircleList.size())
			{//Zur noch nochmal am Anfang anfangen
				index=0;
			}
			List<MyEdge<T>> aktKreis ;
			aktKreis = subCircleList.get(index);
//			while ((aktKreis = subCircleList.get(index)).isEmpty())
//			{//Fertige KReise rausnehmen
//				subCircleList.remove(index);
//			}
			
			
			if(target != null)
			{
				for(int i =0;i<aktKreis.size();i++)//Wir schauen dass wir beim Target der LEtzten kante anfangen.  
				{					
					boolean b =  aktKreis.get(i).contains(target);
					if (b)
					{
						edgeNumbers.set(index,i);
						break;
					}
					 
				}
			}
			DefaultEdge edge = aktKreis.get(edgeNumbers.get(index)).edge;
			boolean b = true;
			// edge != null && graph.degreeOf(graph.getEdgeSource(edge))<=2
			while(b)//while(index>0)
			{				
				path.add(edge);
				target = graph.getEdgeTarget(edge);
				int degree = graph.degreeOf(target);
				if(graph.degreeOf(target)>2)
				{
					for(int i = index+1;i<subCircleList.size();i++)
					{//Wenn wir einen Knoten mit grossemKantengrad finden schauen wir ob er in einem anderen KReis ist.
						if (subCircleNodeList.get(i).contains(target))
						{
							b= false;
							index = i;
							i = Integer.MAX_VALUE-1;
						}
						
					}
					
				}
				aktKreis.remove((int)edgeNumbers.get(index));
				
				if (!aktKreis.isEmpty())
				{
					while(true)
					try{
						edge = aktKreis.get((int)edgeNumbers.get(index)).edge;
						break;
					}catch (IndexOutOfBoundsException ex)
					{
						edgeNumbers.set(index,(edgeNumbers.get(index)-1));
					}
				}else{
					edge = null;
					b = false;
					index--;
					subCircleList.remove(aktKreis);
				}

			}
			if(subCircleList.isEmpty())
				pathUnfinished=false;
		}
		return path;
	}

	private List<MyEdge<T>> buildSubCircle2(T vertex, HashSet<DefaultEdge> edgeSet,UndirectedGraph<T, DefaultEdge>graph, List<List<T>> subCircleNodeList)
	{
		T aktVertex = vertex;
		T target = null;
		List<MyEdge<T>> subCircle = new ArrayList();
		List<T> subCircleNodes = new ArrayList();
		while(!vertex.equals(target))
		{
			List<T> neigh = Graphs.neighborListOf(graph, aktVertex);
			target = neigh.remove(0);
			DefaultEdge edge =graph.getEdge(aktVertex, target); 
			subCircle.add(new MyEdge<T>(graph.getEdgeSource(edge),graph.getEdgeTarget(edge), edge));
			subCircleNodes.add(graph.getEdgeSource(edge));
			subCircleNodes.add(graph.getEdgeTarget(edge));
			graph.removeEdge(edge);
			edgeSet.remove(edge);
			aktVertex=target;
			//TODO was machen wenn kein Weg gefunden wird!
		}	
		subCircleNodeList.add(subCircleNodes);
		return subCircle;
		
	}
	
	class MyEdge<T extends Comparable<T>>{
		T source;
		T target;
		DefaultEdge edge;
		public MyEdge(T source, T target, DefaultEdge edge)
		{
			this.source = source;
			this.target = target;
			this.edge = edge;
		}
		
		public String toString()
		{
			return edge.toString();
		}
		
		public boolean contains(T vertex)
		{
			return (source.equals(vertex) || target.equals(vertex))?true:false;
		}
		
	}

}
