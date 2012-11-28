package a3_p01_JS_MJ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;
import org.jgrapht.util.FibonacciHeap;
import org.jgrapht.util.FibonacciHeapNode;

import a2_p01_JS_MJ.AttributedNode;

public class Prim<T extends Comparable<T>> {
	
	
	public WeightedGraph<T,DefaultWeightedEdge> algorithm(WeightedGraph<T, DefaultWeightedEdge> graph)
	{
		
		Set<T> nodeSet =new HashSet<T>(graph.vertexSet());
		Iterator<T> it = nodeSet.iterator();
		T sourceNode = it.next();
		nodeSet.remove(sourceNode);
		WeightedGraph<T,DefaultWeightedEdge> newGraph = new WeightedPseudograph<T, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		newGraph.addVertex(sourceNode);
		
		int nodeCount = nodeSet.size();
		
		List<T> neigh; 
		List<DefaultWeightedEdge> l = new ArrayList<DefaultWeightedEdge>();
		PriorityQueue<QueueEntry<T>> prioQueue = new PriorityQueue<QueueEntry<T>>();

		while(!nodeSet.isEmpty())
		{
			neigh = Graphs.neighborListOf(graph, sourceNode);//Wir benutzen nur die Knoten die noch nciht im Baum drin sind.
			neigh.retainAll(nodeSet);//Also die Schnittmenge der Nachbarn udn der noch nicht vorhandenen Knoten.						
			for (T node : neigh)//Alle Kanten suchen die von uns abgehen.
			{
				prioQueue.add(getQueueEntry(graph.getAllEdges(sourceNode, node),graph));//Falls wir PArallelen haben muessen wuir alle Edges hinzufuegen.
			}
			QueueEntry<T> q = prioQueue.poll();
	
			newGraph.addVertex(q.getTargetNode());
			newGraph.addVertex(q.getSourceNode());
			newGraph.addEdge(q.getSourceNode(), q.getTargetNode(), q.getEdge());	
			//Graphs.addEdgeWithVertices(newGraph, graph, q.getEdge());
			if(Util.checkForCircles(newGraph))
			{
			newGraph.removeEdge(q.getEdge());
			}else {
			//count++;
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

	public WeightedGraph<T,DefaultWeightedEdge> algorithmWithFibo(WeightedGraph<T, DefaultWeightedEdge> graph)
	{
		
		Set<T> nodeSet =new HashSet<T>(graph.vertexSet());
		Iterator<T> it = nodeSet.iterator();
		
		T sourceNode = it.next();
		nodeSet.remove(sourceNode);
		WeightedGraph<T,DefaultWeightedEdge> newGraph = new WeightedPseudograph<T, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		newGraph.addVertex(sourceNode);
		
		int nodeCount = nodeSet.size();
		
		List<T> neigh; 
		List<DefaultWeightedEdge> l = new ArrayList<DefaultWeightedEdge>();
		FibonacciHeap<QueueEntry<T>> heap = new FibonacciHeap<QueueEntry<T>>();
//		PriorityQueue<QueueEntry<T>> prioQueue = new PriorityQueue<QueueEntry<T>>();

		while(!nodeSet.isEmpty())
		{
			neigh = Graphs.neighborListOf(graph, sourceNode);//Wir benutzen nur die Knoten die noch nciht im Baum drin sind.
			neigh.retainAll(nodeSet);//Also die Schnittmenge der Nachbarn udn der noch nicht vorhandenen Knoten.						
			for (T node : neigh)//Alle Kanten suchen die von uns abgehen.
			{
				QueueEntry<T> q = getQueueEntry(graph.getAllEdges(sourceNode, node),graph);
//				prioQueue.add(q);//Falls wir PArallelen haben muessen wuir alle Edges hinzufuegen.
				heap.insert(new FibonacciHeapNode<QueueEntry<T>>(q), q.getWeight());
			}
//			QueueEntry<T> q = prioQueue.poll();
			QueueEntry<T> q=heap.removeMin().getData();
	
			newGraph.addVertex(q.getTargetNode());
			newGraph.addVertex(q.getSourceNode());
			newGraph.addEdge(q.getSourceNode(), q.getTargetNode(), q.getEdge());	
			//Graphs.addEdgeWithVertices(newGraph, graph, q.getEdge());
			if(Util.checkForCircles(newGraph))
			{
			newGraph.removeEdge(q.getEdge());
			}else {
			//count++;
			nodeSet.remove(q.getTargetNode());
			graph.setEdgeWeight(q.getEdge(), q.getWeight());
			}
			sourceNode=q.getTargetNode();
		}
		return newGraph;
	}	
	
}

class QueueEntry<T extends Comparable<T>> implements Comparable<QueueEntry>{
	private DefaultWeightedEdge edge;
	private T targetNode,sourceNode;
	private double weight;
	QueueEntry(DefaultWeightedEdge e,T sourceNode, T targetNode, double weight)
	{
		this.sourceNode = targetNode;
		this.edge = e;
		this.targetNode =sourceNode ;
		this.weight = weight;
	}

	 T getSourceNode() {
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
	
	public String toString()
	{
		return "("+sourceNode.toString()+","+targetNode.toString()+","+weight+") "+ edge.toString();
	}
	
}
