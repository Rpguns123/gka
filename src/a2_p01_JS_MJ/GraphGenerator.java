package a2_p01_JS_MJ;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedPseudograph;

public class GraphGenerator {

    public static WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> generateAttributedWeightedGraph(int nodeCount){
        WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> graph = new WeightedPseudograph<AttributedNode<String>, DefaultWeightedEdge>(DefaultWeightedEdge.class);
        List<AttributedNode<String>> ary =new ArrayList<AttributedNode<String>>(nodeCount);
        AttributedNode<String> target= new AttributedNode<String>("target", 0);
        graph.addVertex(target);
        ary.add(target);
        for (int i =1; i < nodeCount;i++)
        {
            AttributedNode<String> node = new AttributedNode<String>(Integer.toString(i), (int)(Math.random()*100));
            ary.add(node);
            graph.addVertex(node);
        }
        for (AttributedNode<String> elem : ary)//Edges erstellen. Jeden Knoten einmal nehmen.
        {
            for (int i=0;i<5;i++)//5 Edges Pro Knoten.
            {
            	AttributedNode<String > n;
            	do{

                n = ary.get((int)(Math.random()*nodeCount));
            	}while(n.equals(elem));//Keine Schleifen zulassen.
                graph.addEdge(elem, n);            	
                double w1=(elem.getAttribute()>n.getAttribute())?elem.getAttribute():n.getAttribute();
                double w2=(elem.getAttribute()>n.getAttribute())?elem.getAttribute():n.getAttribute();
                double weight = w1-w2+Math.random()*23;
                graph.setEdgeWeight(graph.getEdge(elem, n), weight);
               
            }
        }
       
        return graph;
    }
   
}

