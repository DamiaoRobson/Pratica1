package main;

/**
 * 
 * @author Damiao Robson Domiciano
 *
 */
public class Graph {
	

	/**
	 * Ler um grafo a partir de um arquivo de texto.O grafo sera descrito segundo o
	 * seguinte formato: a primeira linha informa o numero de vertices do grafo.
	 * Cada linha subsequente informa as arestas do mesmo.
	 * 
	 * @param path
	 *            caminho de localizacao do grafo em arquivo texto.
	 */
	public Graph readGraph(String path) {
		return null;
	}

	public Graph readWeightedGraph(String path) {
		return null;
	}

	public int getVertexNumber(Graph graph) {
		return 0;
	}

	public int getEdgeNumber(Graph graph) {
		return 0;
	}

	public float getMeanEdge(Graph graph) {
		return 0;
	}
	
	public String graphRepresentation(Graph graph, String type) {
		return null;
	}
	
	public String BFS(Graph graph, String v) {
		return null;
	}
	
	public String DFS(Graph graph, String v) {
		return null;
	}
	
	public String SCC(Graph graph) {
		return null;
	}
	
	public String shortestPath(int v1, int v2) {
		return null;
	}
	
	public String mst(Graph graph) {
		return null;
	}

}
