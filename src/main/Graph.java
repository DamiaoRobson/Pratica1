package main;

import grafo.Grafo;

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
	public Grafo readGraph(String path) {
		return null;
	}

	public Grafo readWeightedGraph(String path) {
		return null;
	}

	public int getVertexNumber(Grafo graph) {
		return 0;
	}

	public int getEdgeNumber(Grafo graph) {
		return 0;
	}

	public float getMeanEdge(Grafo graph) {
		return 0;
	}
	
	public String graphRepresentation(Grafo graph, String type) {
		return null;
	}
	
	public String BFS(Grafo graph, String v) {
		return null;
	}
	
	public String DFS(Grafo graph, String v) {
		return null;
	}
	
	public String SCC(Grafo graph) {
		return null;
	}
	
	public String shortestPath(int v1, int v2) {
		return null;
	}
	
	public String mst(Grafo graph) {
		return null;
	}

}
