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



public class Dijkstra {

    public SearchResult searchShortestPath(WeightedGraph<String, DefaultWeightedEdge> graph, String start, String end) {
        List<String> open = new ArrayList();
        //TODO Map erstellen
        //Map mit Key String, Distanz int, Vorg String, ok Boolean
        Map<String,MapEntry> closed = new HashMap<String, MapEntry>();
        //Map<String,MapEntry> tempMap = new HashMap<String, MapEntry>();
        if(!graph.containsVertex(end) || !graph.containsVertex(start))
            return new SearchResult((Graph)graph, open, new HashMap<String, Integer>(), 0);
        String node = start;
        MapEntry me = new MapEntry(0.0,start,true);
        closed.put(start,null);

        if(end.equals(start))
            return new SearchResult((Graph)graph, open, new HashMap<String, Integer>(), 0);


        boolean unfinished = true;
        String naechster = start, ueberNaechster = start;

        double min2 = Double.MAX_VALUE;

        while(unfinished) //Wenn wir noch abzuarbeitende
        {
            List<String> strList = Graphs.neighborListOf(graph,node);
            double minDistance= Double.MAX_VALUE;

            for (String neighbor : strList)
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
        path.add(0,end);
        MapEntry mapEntry=null;
        while (!mapEntry.precursor.equals(start)){
            mapEntry = closed.get(end);
            path.add(0,mapEntry.getPrecursor());
        }
        path.add(0,start);


        return null;
    }





    private class MapEntry {
        private String  precursor;
        private double distance;
        private boolean ok;
        public MapEntry(double distance, String precursor, boolean ok)
        {
            this.distance = distance;
            this.precursor = precursor;
            this.ok = ok;
        }

        public void setDistance(int distance)
        {
            this.distance = distance;
        }

        public void setPrecursor( String precursor ){
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


        public String getPrecursor(){
            return precursor;
        }
        public boolean getOk()
        {
            return ok;
        }

    }
    }

