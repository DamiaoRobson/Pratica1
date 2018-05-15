package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import comparador.comparaPesoAresta;
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

	/**
	 * Ler um grafo a partir de um arquivo de texto.O grafo sera descrito
	 * segundo o seguinte formato: a primeira linha informa o numero de vertices
	 * do grafo. Cada linha subsequente informa as arestas e peso do mesmo.
	 * 
	 * @param path
	 *            caminho de localizacao do grafo em arquivo texto.
	 */
	public Graph readWeightedGraph(String path) {
		return new Graph().readGraph(path);
	}

	/**
	 * RepresentaÃ§Ã£o de grafos utilizando tanto uma matriz de adjacÃªncia
	 * (type = â€œAMâ€�), quanto uma lista de adjacÃªncia (type = â€œAL"). O
	 * usuÃ¡rio podera escolher a representaÃ§Ã£o a ser utilizada.
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
	 * Metodo que conta a quantidade de vertices de um determinado grafo
	 * @param graph
	 * @return quantidade de vertices do grafo
	 */
	public int getVertexNumber(Graph graph) {
		return graph.getQtdVertices();
	}

	/**
	 * @author Ivan
	 *  Metodo que conta a quantidade de arestas de um determinado grafo
	 * @param graph
	 * @return quantidade de arestas do grafo
	 */
	public int getEdgeNumber(Graph graph) {
		int qntVertices = 0;
		for (Vertice vertice : graph.getVertices()) {
			qntVertices += vertice.getQntArestas();
		}
		return (qntVertices / 2);
	}

	/**
	 * @author Ivan 
	 * Metodo que calcula o grau medio de um determinado grafo
	 * @param graph
	 * @return grau medio do grafo
	 */
	public float getMeanEdge(Graph graph) {
		float grauTotal = 0;
		for (Vertice vertice : graph.getVertices()) {
			grauTotal += vertice.getQntArestas();
		}
		return grauTotal/graph.getQtdVertices();
	}

	/**
	 * @author Danielle de Lima Vieira
	 * O método recebe um grafo conectado e um vertice raiz e percorre o grafo realizando uma  busca em largura. Como resultado o método
	 * retorna uma string que representa a árvore gerada pela busca.  
	 * @param verticeRaiz - vertice do qual o método iniciará a busca
	 * @return string que representa a árvore de busca
	 */
	public String BFS(Graph graph, int verticeRaiz) {
		return graph.BFS(verticeRaiz);
	}

	/**
	 * @author Danielle de Lima Vieira
	 * O método recebe um grafo conectado e um vertice raiz e percorre o grafo realizando uma  busca em profundidade. Como resultado o método
	 * retorna uma string que representa a árvore gerada pela busca. 
	 * @param verticeRaiz - vertice do qual o método iniciará a busca
	 * @return string que representa a árvore de busca
	 */
	public String DFS(Graph graph, int verticeRaiz) {
		return graph.DFS(verticeRaiz);
	}

	public boolean connected(Graph graph) {
		return graph.connected();
	}

	public String mst(Graph graph) {
		ArrayList<Aresta> arestas = new ArrayList(); 
		arestas.addAll(getArestas(graph.getVertices()));
		/*Collections.sort(arestas, new comparaPesoAresta());
		HashSet<Vertice> vertices = new HashSet<>();
		
		for (Aresta aresta : arestas) {
			vertices.add(aresta.getStart());
			vertices.add(aresta.getEnd());
		}*/
		
		return "nao implementado";//fazerString(vertices);
	}
	
	private String fazerString(HashSet<Aresta> arestas) {
		String aux = "";
		for (Aresta aresta : arestas) {
//			aux= String 
		}
		return null;
	}

	// Filtra as arestas para n�o haver repetidas em um alista
		private HashSet<Aresta> getArestas(Vertice[] vertices) {
			HashSet<Aresta> arestas = new HashSet<>();
			for (Vertice vertice : vertices) {
				for (Aresta aresta : vertice.getArestas()) {
					arestas.add(aresta);
				}
			}
			return arestas;
		}

}