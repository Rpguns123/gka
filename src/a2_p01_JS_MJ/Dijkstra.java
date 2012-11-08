package a2_p01_JS_MJ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import a1_p01_JS_MJ.SearchResult;

public class Dijkstra{

	public SearchResult searchShortestPath(
			WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> graph,
			String startingNode, String endingNode) {
		int acc = 1;
		
		List<AttributedNode<String>> open = new ArrayList();
		List<String> _tmp = new ArrayList<String>();
		AttributedNode<String> start = new AttributedNode<String>(startingNode,
				0);
		AttributedNode<String> end = new AttributedNode<String>(endingNode, 0);

		Map<AttributedNode<String>, MapEntry> closed = new HashMap<AttributedNode<String>, MapEntry>();
				if (!graph.containsVertex(start))
			return new SearchResult((Graph) graph, _tmp, null, 0);

		if (!graph.containsVertex(end))
			return new SearchResult((Graph) graph, _tmp, null, 0);
		AttributedNode<String> node = start;
		MapEntry me = new MapEntry(0.0, start, true);
		closed.put(start, me);
		_tmp.add(start.toString());
		if (end.equals(start))
			return new SearchResult((Graph) graph, _tmp, null, 0);

		boolean unfinished = true;
		while (unfinished) // Wenn wir noch abzuarbeitende
		{
			List<AttributedNode<String>> strList = new ArrayList<AttributedNode<String>>();
			List<AttributedNode<String>> _tmpList = Graphs.neighborListOf(
					graph, node);
			for (AttributedNode<String> elem : _tmpList) // Die Elemente
															// Aussortieren, die
															// Okay sind.
			{
				if (!closed.containsKey(elem) || !closed.get(elem).getOk())
					strList.add(elem);
			}

			double minDistance = Double.MAX_VALUE;

			for (AttributedNode<String> neighbor : strList) {
				double distance = graph.getEdgeWeight(graph.getEdge(node,
						neighbor)) + closed.get(node).getDistance(); // Wir
																		// ermitteln
																		// die
																		// Distanz
				acc++;
				if (!closed.containsKey(neighbor)
						|| (closed.get(neighbor).getDistance() > distance)) {
					closed.put(neighbor, new MapEntry(distance, node, false));
				}

			}
			double min = Double.MAX_VALUE;
			AttributedNode<String> minNode = null;
			for (Map.Entry<AttributedNode<String>, MapEntry> e : closed
					.entrySet()) {
				if (!e.getValue().getOk()) {
					if (min > e.getValue().getDistance()) {
						min = e.getValue().getDistance();
						minNode = e.getKey();
					}
				}

			}
			node = minNode;
			closed.get(node).setOk(true);
			if (closed.containsKey(end) && closed.get(end).getOk())
				unfinished = false;

		}

		// Pfadfindung
		List<String> path = new ArrayList<String>();
		path.add(0, end.getValue());
		MapEntry mapEntry = closed.get(end); // Ziel in den Pfad einfuegen.
		while (!mapEntry.getPrecursor().equals(start)) {// Wenn wir beim 2.
														// Knoten sind abbrechen
			path.add(0, mapEntry.getPrecursor().getValue());// Den Vorgaenger
															// einfuegen.
			mapEntry = closed.get(mapEntry.getPrecursor());// Den MApeintrag des
															// Vorgaengers
															// holen.
		}
		path.add(0, start.getValue());
		SearchResult result = new SearchResult((Graph) graph, path, null, acc);

		return result;
	}

	private class MapEntry {
		private AttributedNode<String> precursor;
		private double distance;
		private boolean ok;

		public MapEntry(double distance, AttributedNode<String> precursor,
				boolean ok) {
			this.distance = distance;
			this.precursor = precursor;
			this.ok = ok;
		}

		public void setDistance(int distance) {
			this.distance = distance;
		}

		public void setPrecursor(AttributedNode<String> precursor) {
			this.precursor = precursor;
		}

		public void setOk(boolean ok) {
			this.ok = ok;
		}

		public double getDistance() {
			return distance;
		}

		public AttributedNode<String> getPrecursor() {
			return precursor;
		}

		public boolean getOk() {
			return ok;
		}

		public String toString() {
			String s = ">>>Distance: " + getDistance() + " Precursor: "
					+ getPrecursor().toString() + " " + getOk() + "<<<\n";
			return s;
		}

	}
}
