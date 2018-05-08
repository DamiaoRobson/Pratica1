package main;

import grafo.Graph;

/**
 * 
 * @author Damiao Robson Domiciano
 *
 */
public class GraphLib {
	
	
	
	public GraphLib(){

	}

	/**
	 * Ler um grafo a partir de um arquivo de texto.O grafo sera descrito
	 * segundo o seguinte formato: a primeira linha informa o numero de vertices
	 * do grafo. Cada linha subsequente informa as arestas do mesmo.
	 * 
	 * @param path
	 *            caminho de localizacao do grafo em arquivo texto.
	 */
	public Graph readGraph(String path) {
		return new Graph().readGraph(path);
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

	/**
	 * Representação de grafos utilizando tanto uma matriz de adjacência (type =
	 * “AM”), quanto uma lista de adjacência (type = “AL"). O usuário podera
	 * escolher a representação a ser utilizada.
	 * 
	 * @param graph
	 * @param type
	 * @return
	 */
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
