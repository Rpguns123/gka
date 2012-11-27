package a3_p01_JS_MJ;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

import a2_p01_JS_MJ.AttributedNode;

public class Prim {
	
	public static Graph<AttributedNode<String>,DefaultWeightedEdge> algorithm(WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> graph, AttributedNode<String> firstNode)
	{
		WeightedGraph<AttributedNode<String>,DefaultWeightedEdge> newGraph = new WeightedPseudograph<AttributedNode<String>, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		AttributedNode<String> sourceNode = firstNode;
		newGraph.addVertex(sourceNode);
		Set<AttributedNode<String>> nodeSet = graph.vertexSet();
		int nodeCount = nodeSet.size();
		
		List<AttributedNode<String>> neigh; 
		List<DefaultWeightedEdge> l = new ArrayList<DefaultWeightedEdge>();
		PriorityQueue<QueueEntry> prioQueue = new PriorityQueue<QueueEntry>();
		/* Die Anzahl der Knoten prüfen, und für jeden Knoten einen Zähler um eins hochzählen
		 * Dann stoppen wenn die Zähler = Anzahl Knoten
		 * --> For Schleife.
		 */		
		for (int count =  1; count < nodeCount;)
		{
			neigh = Graphs.neighborListOf(graph, sourceNode);
			neigh.retainAll(nodeSet);//Wir benutzen nur die Knoten die noch nciht im Baum drin sind.
			//Also die Schnittmenge der Nachbarn udn der noch nicht vorhandenen Knoten.
			
			for (AttributedNode<String> node : neigh)//Alle Kanten suchen die von uns abgehen.
			{
				prioQueue.add(getQueueEntry(graph.getAllEdges(sourceNode, node),graph));//Falls wir PArallelen haben muessen wuir alle Edges hinzufuegen.
			}
			QueueEntry q = prioQueue.poll();
			graph.addVertex(q.getTargetNode());
			graph.addEdge(q.getSourceNode(), q.getTargetNode(), q.getEdge());
			graph.setEdgeWeight(q.getEdge(), q.getWeight());
			//Graphs.addEdgeWithVertices(newGraph, graph, q.getEdge());
			if(Util.checkForCircles(newGraph))
			{
				newGraph.removeEdge(q.getEdge());
			}else {
				count++;
				nodeSet.remove(q.getTargetNode());
			}
			sourceNode=q.getTargetNode();
		}
		return newGraph;
	}
	
	private static QueueEntry getQueueEntry(Set<DefaultWeightedEdge> set, WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> g)
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
		return new QueueEntry(edge,g.getEdgeSource(edge), g.getEdgeTarget(edge), weight);
	}

}

class QueueEntry implements Comparable<QueueEntry>{
	private DefaultWeightedEdge edge;
	private AttributedNode<String> targetNode,sourceNode;
	private double weight;
	QueueEntry(DefaultWeightedEdge e,AttributedNode<String> sourceNode, AttributedNode<String> targetNode, double weight)
	{
		this.sourceNode = sourceNode;
		this.edge = e;
		this.targetNode = targetNode;
		this.weight = weight;
	}

	public AttributedNode<String> getSourceNode() {
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
	
	AttributedNode<String> getTargetNode()
	{
		return targetNode;
	}
	double getWeight()
	{
		return weight;
	}
	
}
