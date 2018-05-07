package grafo;

import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import persistencia.IOArquivo;

/**
 * 
 * @author Damiao Robson Domciciano
 *
 *         Grafo com suas funcionalidades
 */
public class Grafo {

	private final int POSICAO_QTD_VERTICES = 0;

	private Vertice[] vertices;
	private int qtdVertices = 0;

	/**
	 * Contrutor
	 */
	public Grafo() {

	}

	/**
	 * Gera o grafo
	 * 
	 * @param path
	 *            Caminho para encontrar o arquivo de texto com o grafo, caso nao
	 *            encontre gera uma exception
	 * @return Um grafo
	 */
	public Grafo criaGrafo(String path) {
		List<String[]> arq = null;
		try {
			arq = IOArquivo.lerArquivo(path);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Arquivo nao encontrado ou incorreto");
		}
		gerarGrafo(arq);

		return this;
	}

	/**
	 * Metodo auxiliar para gerar grafo, caso o grafo so possua uma vertice sera
	 * gerado
	 * 
	 * @param arq
	 *            Lista com dados para gerar grafo
	 */
	private void gerarGrafo(List<String[]> arq) {
		int qtdVertices = Integer.parseInt(arq.get(POSICAO_QTD_VERTICES)[POSICAO_QTD_VERTICES]);
		vertices = new Vertice[qtdVertices];

		for (int i = 1; i < vertices.length; i++) {
			if (arq.get(i).length == 1)
				vertices[this.qtdVertices++] = new Vertice(Integer.parseInt(arq.get(i)[0]));
			else if (arq.get(i).length == 2)
				criarAresta(arq.get(i)[0], arq.get(i)[1]);
			else
				criarArestaComPeso(arq.get(i)[0], arq.get(i)[1], arq.get(i)[2]);
		}
	}

	/**
	 * Cria aresta com os valores dos vertices de inicio e fim da aresta
	 * 
	 * @param value1
	 *            Valor da vertice inicial
	 * @param value2
	 *            Valor da vertice final
	 */
	private void criarAresta(String value1, String value2) {
		Vertice vertice1 = new Vertice(Integer.parseInt(value1));
		Vertice vertice2 = new Vertice(Integer.parseInt(value2));

		Aresta aresta = new Aresta(vertice1, vertice2);

		adicionaVertice(aresta);
	}

	/**
	 * Cria aresta com valores das vertices de inicio, fim e peso da aresta
	 * 
	 * @param value1
	 *            Valor da vertices inicio
	 * @param value2
	 *            Valor da vertices fim
	 * @param peso
	 *            Peso da aresta
	 */
	private void criarArestaComPeso(String value1, String value2, String peso) {
		Vertice vertice1 = new Vertice(Integer.parseInt(value1));
		Vertice vertice2 = new Vertice(Integer.parseInt(value2));

		Aresta aresta = new Aresta(vertice1, vertice2, Integer.parseInt(value2));

		adicionaVertice(aresta);
	}

	/**
	 * Adiciona a vertice ao grafo
	 * 
	 * @param aresta
	 *            Aresta ja com vertices e peso, caso tenha
	 */
	private void adicionaVertice(Aresta aresta) {
		if (!contemVertce(aresta.getStart())) {
			aresta.getStart().adicionaAresta(aresta);
			vertices[this.qtdVertices++] = aresta.getStart();
		} else {
			adicionarArestaEmVertice(aresta.getStart(), aresta);
		}
		if (!contemVertce(aresta.getEnd())) {
			aresta.getEnd().adicionaAresta(aresta);
			vertices[this.qtdVertices++] = aresta.getEnd();
		} else {
			adicionarArestaEmVertice(aresta.getEnd(), aresta);
		}

	}

	/**
	 * Caso a vertice ja esteja no grafo, so acrecentar a aresta
	 * 
	 * @param vertice
	 *            Vertice ja contida no grafo
	 * @param aresta
	 *            Nova aresta a ser adicionada a vertice
	 */
	private void adicionarArestaEmVertice(Vertice vertice, Aresta aresta) {
		for (Vertice v : vertices) {
			if (v != null && vertice.equals(v))
				v.adicionaAresta(aresta);
		}

	}

	/**
	 * Verifica se a vertice ja esta no grafo
	 * 
	 * @param vertice
	 *            Vertice a ser verificar se contem no grafo
	 * @return Vertorna uma boleana informando se contem ou nao o vertice no grafo
	 */
	private boolean contemVertce(Vertice vertice) {
		for (Vertice v : vertices)
			if (v != null && vertice.equals(v))
				return true;
		return false;
	}

}
