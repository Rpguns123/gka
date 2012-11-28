package a3_p01_JS_MJ;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import a2_p01_JS_MJ.AttributedNode;

public class Prim<T extends Comparable<T>> {
	
	
	public WeightedGraph<T,DefaultWeightedEdge> algorithm(WeightedGraph<T, DefaultWeightedEdge> graph)
	{
		Set<T> nodeSet = graph.vertexSet();		
		Iterator<T> it = nodeSet.iterator();
		T sourceNode = it.next();
		WeightedGraph<T,DefaultWeightedEdge> newGraph = new WeightedPseudograph<T, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		newGraph.addVertex(sourceNode);
		
		int nodeCount = nodeSet.size();
		
		List<T> neigh; 
		List<DefaultWeightedEdge> l = new ArrayList<DefaultWeightedEdge>();
		PriorityQueue<QueueEntry<T>> prioQueue = new PriorityQueue<QueueEntry<T>>();
		/* Die Anzahl der Knoten prüfen, und für jeden Knoten einen Zähler um eins hochzählen
		 * Dann stoppen wenn die Zähler = Anzahl Knoten
		 * --> For Schleife.
		 */		
		for (int count =  1; count < nodeCount;)
		{
			neigh = Graphs.neighborListOf(graph, sourceNode);
			neigh.retainAll(nodeSet);//Wir benutzen nur die Knoten die noch nciht im Baum drin sind.
			//Also die Schnittmenge der Nachbarn udn der noch nicht vorhandenen Knoten.
			
			for (T node : neigh)//Alle Kanten suchen die von uns abgehen.
			{
				prioQueue.add(getQueueEntry(graph.getAllEdges(sourceNode, node),graph));//Falls wir PArallelen haben muessen wuir alle Edges hinzufuegen.
			}
			QueueEntry<T> q = prioQueue.poll();
			newGraph.addVertex(q.getTargetNode());
			newGraph.addEdge(q.getSourceNode(), q.getTargetNode(), q.getEdge());		
			//Graphs.addEdgeWithVertices(newGraph, graph, q.getEdge());
			if(Util.checkForCircles(newGraph))
			{
				newGraph.removeEdge(q.getEdge());
			}else {
				count++;
				nodeSet.remove(q.getTargetNode());
				graph.setEdgeWeight(q.getEdge(), q.getWeight());
			}
			sourceNode=q.getTargetNode();
		}
		return newGraph;
	}
	
	private QueueEntry<T> getQueueEntry(Set<DefaultWeightedEdge> set, WeightedGraph<T, DefaultWeightedEdge> g)
	{
		double weight = Double.MAX_VALUE;
		DefaultWeightedEdge edge = null;
		for(DefaultWeightedEdge e : set)
		{
			double d = g.getEdgeWeight(e);
			if (d<weight)
			{
				edge = e;
				weight = d;
			}
		}		
		return new QueueEntry<T>(edge,g.getEdgeSource(edge), g.getEdgeTarget(edge), weight);
	}

}

class QueueEntry<T extends Comparable<T>> implements Comparable<QueueEntry>{
	private DefaultWeightedEdge edge;
	private T targetNode,sourceNode;
	private double weight;
	QueueEntry(DefaultWeightedEdge e,T sourceNode, T targetNode, double weight)
	{
		this.sourceNode = sourceNode;
		this.edge = e;
		this.targetNode = targetNode;
		this.weight = weight;
	}

	public T getSourceNode() {
		return sourceNode;
	}

	@Override
	public int compareTo(QueueEntry o) {
		return (int)(this.weight-o.weight);
		
	}
	
	DefaultWeightedEdge getEdge()
	{
		return edge;
	}
	
	T getTargetNode()
	{
		return targetNode;
	}
	double getWeight()
	{
		return weight;
	}
	
}
