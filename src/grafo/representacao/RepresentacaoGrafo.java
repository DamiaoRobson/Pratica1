package grafo.representacao;

import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JOptionPane;

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
		return representacaoGrafo;
	}

	private void ordenarArray(Vertice[] grafoAux) {
		Arrays.sort(grafoAux, new Comparator<Vertice>() {
			@Override
			public int compare(Vertice v1, Vertice v2) {
				if (v1.getValue() > v2.getValue())	return 1;
				if (v1.getValue() < v2.getValue())	return -1;
				return 0;
			}
		});
	}

	private String criarRepresentacaoAM(Vertice[] vertices) {
		String[][] rep = gerarArrayAM(vertices.length, vertices);
		rep = matrisAdjacent(rep, vertices);
		return criarString(rep);
	}

	private String criarString(String[][] rep) {
		String str = "";
		for (String[] linha : rep) {
			for (String coluna : linha) {
				str += coluna + " ";
			}
			str += FL;
		}
		return str;
	}

	private String[][] matrisAdjacent(String[][] rep, Vertice[] vertices) {
		int iniciarLinha = 1;

		for (int i = iniciarLinha; i <= vertices.length; i++)
			for (int j = iniciarLinha; j <= vertices.length; j++)
				rep[i][j] = verificarAdjacencia(i, j, vertices);

		return rep;
	}

	private String verificarAdjacencia(int i, int j, Vertice[] vertices) {
		for (Aresta aresta : vertices[j -1].getArestas())
			if (vertices[i -1].equals(aresta.getStart()) && vertices[i -1].equals(aresta.getEnd())) {
				if (aresta.getPeso() > 0)
					return "" + aresta.getPeso();
				else
					return "1";
			}

		return "0";
	}

	private String[][] gerarArrayAM(int qtdVertices, Vertice[] vertices) {
		String[][] aux = new String[qtdVertices + 1][qtdVertices + 1];

		aux[0][0] = " "; // psicao A11 da matriz vazia
		for (int i = 1; i <= qtdVertices; i++) {
			aux[INI_LINHA][i] = "" + vertices[i -1].getValue();
			aux[i][INI_COLUNA] = "" + vertices[i -1].getValue();
		}
		return aux;
	}

	private String criarRepresentacaoAL(Vertice[] grafoAux) {
		// TODO Auto-generated method stub
		return null;
	}

}
