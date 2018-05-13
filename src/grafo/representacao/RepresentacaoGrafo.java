package grafo.representacao;

import java.util.Arrays;
import java.util.HashSet;

import javax.swing.JOptionPane;

import comparador.ComparadorVertice;
import grafo.Aresta;
import grafo.Graph;
import grafo.Vertice;

public class RepresentacaoGrafo {

	private final int INI_LINHA = 0;
	private final int INI_COLUNA = 0;
	private final String FL = System.lineSeparator();

	/**
	 * Construtor da classe
	 */
	public RepresentacaoGrafo() {
	}

	/**
	 * Representação de grafos utilizando tanto uma matriz de adjacência (type =
	 * “AM”), quanto uma lista de adjacência (type = “AL"). O usuário podera
	 * escolher a representação a ser utilizada.
	 * 
	 * @param graph
	 *            Grajo regado por esta mesma biblioteca
	 * @param type
	 *            O tipo de representacao Matriz de adjacencia AM e Lista de
	 *            Adjacencia AL
	 * @return Uma String com o tipo de representacao selecionado
	 */
	public String representacao(Graph graph, String type) {
		String representacaoGrafo = "";
		Vertice[] grafoAux = Arrays.copyOf(graph.getVertices(), graph.getQtdVertices());
		ordenarArray(grafoAux);

		if (type.equalsIgnoreCase("am"))
			representacaoGrafo = criarRepresentacaoAM(grafoAux);
		else if (type.equalsIgnoreCase("al"))
			representacaoGrafo = criarRepresentacaoAL(grafoAux);
		else
			JOptionPane.showMessageDialog(null, "Tipo de representacao invalido!");
		return representacaoGrafo.trim();
	}

	private void ordenarArray(Vertice[] grafoAux) {
		Arrays.sort(grafoAux, new ComparadorVertice() );
	}

	private String criarRepresentacaoAM(Vertice[] vertices) {
		double[][] rep = gerarArrayAM(vertices.length, vertices);
		rep = matrizAdjacent(rep, vertices);
		return criarString(rep);
	}

	private String criarString(double[][] rep) {
		String str = "";
		for (double[] linha : rep) {
			for (double coluna : linha) {
				str += String.format("%.1g ", coluna);
			}
			str = str.trim();
			str += FL;
		}
		return str;
	}

	private double[][] matrizAdjacent(double[][] rep, Vertice[] vertices) {
		int iniciarLinha = 1;
		HashSet<Aresta> arestas = getArestas(vertices);
		for (Aresta aresta : arestas) {
			int vertStart = iniciarLinha + indexVertice(aresta.getStart(), vertices);
			int vertEnd = iniciarLinha + indexVertice(aresta.getEnd(), vertices);
			rep[vertStart][vertEnd] = aresta.getHasPeso()? aresta.getPeso() : 1;
			rep[vertEnd][vertStart] = aresta.getHasPeso()? aresta.getPeso() : 1;
		}

		return rep;
	}


	private int indexVertice(Vertice v, Vertice[] vertices) {
		int aux = 0;
		for (int i = 0; i < vertices.length; i++) {
			if(vertices[i].equals(v))
				aux = i;
		}
		return aux;
	}
	

	private double[][] gerarArrayAM(int qtdVertices, Vertice[] vertices) {
		double[][] aux = new double[qtdVertices + 1][qtdVertices + 1];

		aux[0][0] = 0; // psicao A11 da matriz vazia
		for (int i = 1; i <= qtdVertices; i++) {
			aux[INI_LINHA][i] = vertices[i -1].getValue();
			aux[i][INI_COLUNA] = vertices[i -1].getValue();
		}
		return aux;
	}

	private String criarRepresentacaoAL(Vertice[] vertices) {
		String rep = gerarArrayAL(vertices);
		return rep;
	}
	
	private String gerarArrayAL(Vertice[] vertices) {
		String str = "";
		for (Vertice vertice : vertices) {
			str += String.format("%d -",vertice.getValue());
			for (Aresta arest : vertice.getArestas()) {
				if(arest.getStart().getValue() != vertice.getValue())
					str += " " + arest.getStart().getValue() + (arest.getHasPeso()? String.format("(%.1g)", arest.getPeso()): ""); 
				if(arest.getEnd().getValue() != vertice.getValue())
						str += " " + arest.getEnd().getValue() + (arest.getHasPeso()? String.format("(%.1g)", arest.getPeso()): "");
			}
			str += FL;
		}
		return str;
	}

	private HashSet<Aresta> getArestas(Vertice[] vertices){
		HashSet<Aresta> arestas = new HashSet<>();
		for (Vertice vertice : vertices) {
			for (Aresta aresta : vertice.getArestas()) {
				arestas.add(aresta);
			}
		}
		return arestas;
	}
	

}
