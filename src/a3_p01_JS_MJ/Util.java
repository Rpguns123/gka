package a3_p01_JS_MJ;

import org.jgrapht.Graph;

public class Util {

	public static boolean checkForCircles(Graph<?,?> g)
	{
		return !(g.vertexSet().size()==(g.edgeSet().size()+1));
	}
	
}
