package a1_p01_JS_MJ;

import java.io.*;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.*;


public class GraphWriter {


	/**
	 * A method that saves a Graph to its current directory, as an gka file.
	 * @param graph The graph that is to be saved
	 * @param fileName The desired filename .gka will be appended by the method
	 * @return Returns true if succesful, false if not
	 */
	public static boolean writeGraph(AbstractBaseGraph <String, DefaultEdge> graph, String fileName)
	{
		fileName.concat(".gka");
		File file = new File(fileName);
		try {
			if(!file.createNewFile())
			{
				System.out.println("File Already Exists!\nFile will be overwritten!");
				file.delete();
				file.createNewFile();
			}
		} catch (IOException e) {
//			e.printStackTrace();
			return false;
		}
		if(!file.canWrite())
		{
			System.out.println("Can't write to file");
		}
		try{
		FileWriter fstream = new FileWriter(fileName);
		BufferedWriter out = new BufferedWriter(fstream);
		if (graph instanceof DirectedGraph)
		out.write("#gerichtet");
		else 
			out.write("#ungerichtet");
		/*if (graph instanceof WeightedGraph)
			out.write("#gewichted");*/
		Set<DefaultEdge> edges = graph.edgeSet();
		StringBuilder sb = new StringBuilder();
		for (DefaultEdge e : edges)
		{
			
			sb.append(graph.getEdgeSource(e));
			sb.append(",");
			sb.append(graph.getEdgeTarget(e));
		}
		out.close();
		// Erste Zeile: #gerichtet|ungerichtet
    	// Zweite Zeile: [#attributiert] | [#gewichted] | [#attributiert,gewichted]
    	
    	// <NameKnoten1>[,<Attribut1>],<NameEcke2>[,<Attribut2>][,<Gewicht>]
		}catch(Exception e)
		{
			return false;
		}
		return true;
	}
	
	
}
