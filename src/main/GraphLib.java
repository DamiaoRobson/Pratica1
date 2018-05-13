package main;

import grafo.Aresta;
import grafo.Graph;
import grafo.Vertice;
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

	/**
	 * RepresentaÃ§Ã£o de grafos utilizando tanto uma matriz de adjacÃªncia (type =
	 * â€œAMâ€�), quanto uma lista de adjacÃªncia (type = â€œAL"). O usuÃ¡rio podera
	 * escolher a representaÃ§Ã£o a ser utilizada.
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
	
	/**
	 * @author Ivan
	 * MÃ©todo que conta a quantidade de vertices de um determinado grafo
	 * @param graph
	 * @return quantidade de vertices do grafo
	 */
	public int getVertexNumber(Graph graph) {
		return graph.getQtdVertices();
	}
	
	/**
	 * @author Ivan
	 * MÃ©todo que conta a quantidade de arestas de um determinado grafo
	 * @param graph
	 * @return quantidade de arestas do grafo
	 */
	public int getEdgeNumber(Graph graph) {
		int qntVertices = 0;
		for (Vertice vertice : graph.getVertices()) {
			qntVertices += vertice.getQntArestas();
		}
		return (qntVertices/2);
	}
	
	/**
	 * @author Ivan
	 * MÃ©todo que calcula o grau medio de um determinado grafo
	 * @param graph
	 * @return grau medio do grafo
	 */
	public float getMeanEdge(Graph graph) {
		float grauTotal = 0;
		for (Vertice vertice : graph.getVertices()) {
			for (Aresta aresta : vertice.getArestas()) {
				grauTotal += aresta.getPeso();
			}
		}
		return (grauTotal/(2*this.getEdgeNumber(graph)));
	}

	public String BFS(Graph graph, String v) {
		return null;
	}

	public String DFS(Graph graph, String v) {
		return null;
	}

	public boolean connected(Graph graph) {
		return graph.connected();
	}

	public String mst(Graph graph) {
		return null;
	}

}