package a2_p01_JS_MJ;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.WeightedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.Pseudograph;
import org.jgrapht.graph.WeightedPseudograph;

public class GraphGenerator {

	public static int EDGES_PER_NODE = 40;
    public static WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> generateAttributedWeightedGraph(int nodeCount){
    	EDGES_PER_NODE = 5;
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
            for (int i=0;i<EDGES_PER_NODE;i++)//5 Edges Pro Knoten.
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
    
    public static WeightedGraph<AttributedNode<String>, DefaultWeightedEdge> generateAttributedWeightedGraph(int nodeCount, int edgesPerNode){
    	EDGES_PER_NODE= edgesPerNode;
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
            for (int i=0;i<EDGES_PER_NODE;i++)//5 Edges Pro Knoten.
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
            graph.addEdge(elem, target);
            graph.setEdgeWeight(graph.getEdge(elem, target), Math.PI*ary.size()+2);
        }
       
        return graph;
    }
    
    public static Pseudograph<String, DefaultEdge> generateEulBig(int nodeCount){
    	Pseudograph<String, DefaultEdge> graph = new Pseudograph<String, DefaultEdge>(DefaultEdge.class);
    	
    	ArrayList<String> blatter = new ArrayList<String>();
    	
    	String name1 = Integer.toString((int)(Math.random()*10000));
		String name2 = Integer.toString((int)(Math.random()*10000));
		String name3 = Integer.toString((int)(Math.random()*10000));
		
		blatter.add(name1);
		blatter.add(name2);
		blatter.add(name3);
    	
		graph.addVertex(name1);
		graph.addVertex(name2);
		graph.addVertex(name3);
		
		graph.addEdge(name1, name2);
		graph.addEdge(name2, name3);
		graph.addEdge(name3, name1);
		
    	for(int i=0; i<nodeCount;i++){
    		int index = random(blatter);
    		
    		String v1 = blatter.get(index);
    		String v2 = Integer.toString((int)(Math.random()*10000));
    		String v3 = Integer.toString((int)(Math.random()*10000));
    		
    		
    		blatter.remove(index);
    		blatter.add(v2);
    		blatter.add(v3);
    		
    		graph.addVertex(v2);
    		graph.addVertex(v3);
    		
    		graph.addEdge(v1, v2);
    		graph.addEdge(v2, v3);
    		graph.addEdge(v3, v1);
    	}
    	
    	return graph;
    }
   
    
	public static int random(ArrayList<String> arraylist){
		return (int) Math.round(Math.random()*(arraylist.size()-1));
	}
}

