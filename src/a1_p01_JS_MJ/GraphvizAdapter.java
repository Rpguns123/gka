package a1_p01_JS_MJ;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class GraphvizAdapter {

	public void buildDotFile(String graphName, AbstractBaseGraph<String, DefaultEdge> graph){
	
		int graphClass = 0;
		if( graph instanceof DefaultDirectedGraph){
			graphClass = 1;
		}		
		
		StringBuilder dotGraph = new StringBuilder(200);
		dotGraph.append("digraph "+ graphName +" { \n");
		String connector = "->";
		
		Set<DefaultEdge> edges = graph.edgeSet();
		for (DefaultEdge e : edges) {
			dotGraph.append( 
					graph.getEdgeSource(e) + " " + connector + " " + graph.getEdgeTarget(e) + 
					" [ label = \"" + graph.getEdgeSource(e) + " / " + graph.getEdgeTarget(e) + "\"");
			
			if( graphClass == 1 ){
				dotGraph.append(", dir=none");
			}
			
			dotGraph.append(" ];\n");
		}
		
		dotGraph.append("}");
		
		writeDotFile(graphName, dotGraph);
	}
	
	private void writeDotFile(String graphName, StringBuilder dotGraph){
		try {
			File file = new File(graphName+".dot");
			FileWriter fw = new FileWriter(file);
			
			fw.write(dotGraph.toString());
			
			fw.flush();
			fw.close();
		}
		catch( IOException e ) {
			e.printStackTrace();
		}
		
		compileDotFile(graphName);
	}
	
	private void compileDotFile(String dotFileName){
		String cmd = "/usr/local/bin/dot -Tpng -o" + dotFileName + ".png "+ dotFileName +".dot";
		try {
			Process process = Runtime.getRuntime().exec(cmd);
		} catch (IOException ex){
			ex.printStackTrace();
		}
	}
}