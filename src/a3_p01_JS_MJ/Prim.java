package a3_p01_JS_MJ;

import java.util.ArrayList;
import java.util.List;

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
		//Set<AttributedNode<String>> s = graph.vertexSet();
		
		List<AttributedNode<String>> neigh = Graphs.neighborListOf(graph, firstNode);
		List<DefaultWeightedEdge> l = new ArrayList<DefaultWeightedEdge>();
		/* Die Anzahl der Knoten prüfen, und für jeden Knoten einen Zähler um eins hochzählen
		 * Dann stoppen wenn die Zähler = Anzahl Knoten
		 * --> For Schleife.
		 */		
		for (AttributedNode<String> node : neigh)//Alle Kanten suchen die von uns abgehen.
		{
			l.add(graph.getEdge(sourceNode, node));
		}
		double min = Double.MAX_VALUE;
		DefaultWeightedEdge nextEdge=null;
		for (DefaultWeightedEdge e : l)//Minimale Edge suchen.
		{
			if (graph.getEdgeWeight(e)<min)
			{
				nextEdge = e;
			}
		}
		l.remove(nextEdge);//Hier könnte u.U. eine nullPointerException auftauchen, wenn keine gewichte kleine sind als Double.MAX_VALUE
		Graphs.addEdgeWithVertices(newGraph, graph, nextEdge);
		if(checkForCircles(newGraph))
		{
			;//Kante wieder wegnehmen.
		}
		sourceNode=graph.getEdgeTarget(nextEdge);
		
		return null;
	}

}
