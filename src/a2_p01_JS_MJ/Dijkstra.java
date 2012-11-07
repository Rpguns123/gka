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



public class Dijkstra<T extends Comparable<T>> {

    public SearchResult searchShortestPath(WeightedGraph<T, DefaultWeightedEdge> graph, T start, T end) {
        List<T> open = new ArrayList();
        List<String> _tmp = new ArrayList<String>();
        //TODO Map erstellen
        //Map mit Key String, Distanz int, Vorg String, ok Boolean
        Map<T,MapEntry> closed = new HashMap<T, MapEntry>();
        //Map<String,MapEntry> tempMap = new HashMap<String, MapEntry>();
        if(!graph.containsVertex(end) || !graph.containsVertex(start))
            return new SearchResult((Graph)graph, _tmp, null, 0);
        T node = start;
        MapEntry me = new MapEntry(0.0,start,true);
        closed.put(start,me);
        _tmp.add(start.toString());
        if(end.equals(start))
            return new SearchResult((Graph)graph, _tmp, null, 0);


        boolean unfinished = true;
        T naechster = start, ueberNaechster = start;

        double min2 = Double.MAX_VALUE;

        while(unfinished) //Wenn wir noch abzuarbeitende
        {
            List<T> strList = Graphs.neighborListOf(graph,node);
            double minDistance= Double.MAX_VALUE;

            for (T neighbor : strList)
            {
                if (closed.containsKey(neighbor) && closed.get(neighbor).getOk()) continue; // DEr einfachheit halber erst einmal mit continue
                //if (!closed.containsKey(neighbor) || !closed.get(neighbor).getOk())// Wenn der Nachbar schon ok ist. Brauchen wir ihn nicht betrachten.
                //{
                    double distance = graph.getEdgeWeight(graph.getEdge(node,neighbor))+ closed.get(node).getDistance(); //Wir ermitteln die Distanz
                    if (!closed.containsKey(neighbor) || (closed.get(neighbor).getDistance()>distance)){//Wenn der Knoten noch nicht in der Map ist, oder die Distanz sihc verkürzt hat.
                            //sollte sich die Distanz nicht verkürzen merken wir uns das neue minimum nicht.
                            if (minDistance> distance)//Wir suchen den Knoten mit der niedrigsten entfernung
                            {
                                min2 = minDistance;
                                ueberNaechster = naechster;
                                minDistance=distance;
                                naechster = neighbor;
                            }
                        closed.put(neighbor,new MapEntry(distance,node,false));
                    }
                //}
            }
            closed.put(naechster,new MapEntry(minDistance,node,true));
            if (closed.containsKey(end) && closed.get(end).getOk())
                unfinished = false;
            node = naechster;
            naechster = ueberNaechster;
            minDistance = min2;

        }

        //Pfadfindung
        List<String> path = new ArrayList<String>();
        path.add(0,end.toString());
        MapEntry mapEntry=null;
        while (!mapEntry.precursor.equals(start)){
            mapEntry = closed.get(end);
            path.add(0,mapEntry.getPrecursor().toString());
        }
        path.add(0,start.toString());
//public SearchResult(Graph<T, DefaultEdge> graph, List<T> path, Map<T, Integer> map, int accesses) {
        SearchResult result = new SearchResult((Graph)graph,path,null,0);

        return result;
    }





    private class MapEntry {
        private T  precursor;
        private double distance;
        private boolean ok;
        public MapEntry(double distance, T precursor, boolean ok)
        {
            this.distance = distance;
            this.precursor = precursor;
            this.ok = ok;
        }

        public void setDistance(int distance)
        {
            this.distance = distance;
        }

        public void setPrecursor( T precursor ){
            this.precursor=precursor;
        }

        public void setOk(boolean ok)
        {
            this.ok=ok;
        }

        public double getDistance()
        {
            return distance;
        }


        public T getPrecursor(){
            return precursor;
        }
        public boolean getOk()
        {
            return ok;
        }

    }
    }

