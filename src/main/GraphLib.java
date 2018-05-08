package main;

import grafo.Graph;
import grafo.representacao.RepresentacaoGrafo;

/**
 * 
 * @author Damiao Robson Domiciano
 * 
 *         Gerecia os metodos da biblioteca, delegando as funcionalidades
 *
 */
public class GraphLib {

	/**
	 * Construtor
	 */
	public GraphLib() {

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
	 * Representa��o de grafos utilizando tanto uma matriz de adjac�ncia (type =
	 * �AM�), quanto uma lista de adjac�ncia (type = �AL"). O usu�rio podera
	 * escolher a representa��o a ser utilizada.
	 * 
	 * @param graph
	 *            Grajo regado por esta mesma biblioteca
	 * @param type
	 *            O tipo de representacao Matriz de adjacencia AM e Lista de
	 *            Adjacencia AL
	 * @return Uma String com o tipo de representacao selecionado
	 */
	public String graphRepresentation(Graph graph, String type) {
		return new RepresentacaoGrafo().representacao(graph, type);
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
