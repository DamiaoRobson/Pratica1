package grafo;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import persistencia.IOArquivo;

/**
 * 
 * @author Damiao Robson Domciciano
 *
 *         Grafo com suas funcionalidades
 */
public class Graph {

	private final int POSICAO_QTD_VERTICES = 0;

	private Vertice[] vertices;
	private int qtdVertices = 0;

	/**
	 * Contrutor
	 */
	public Graph() {

	}

	/**
	 * Ler um grafo a partir de um arquivo de texto.O grafo sera descrito
	 * segundo o seguinte formato: a primeira linha informa o numero de vertices
	 * do grafo. Cada linha subsequente informa as arestas do mesmo.
	 * 
	 * @param path
	 *            Caminho para encontrar o arquivo de texto com o grafo, caso nao
	 *            encontre gera uma exception
	 * @return Um grafo
	 */
	public Graph readGraph(String path) {
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

		for (int i = 1; i < arq.size(); i++) {
			if (arq.get(i).length == 1)
				vertices[this.qtdVertices++] = new Vertice(Integer.parseInt(arq.get(i)[0]));
			else if (arq.get(i).length == 2)
				criarAresta(arq.get(i)[0], arq.get(i)[1]);
			else if (arq.get(i).length == 3)
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

		Aresta aresta = new Aresta(vertice1, vertice2, Double.parseDouble(peso));

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

	/**
	 * @author Pedro
	 * Caminho mínimo. A biblioteca deve ser capaz de
	 * encontrar o caminho mais curto entre dois vértices.
	 * 
	 * USO DO ALGORITMO DE BELLMAN-FORD:
	 * Para cada vertice, atualizar sua distancia do "vertice raiz" sempre que necessario e analisar todas as suas outras arestas.
	 *  **Ambos algoritmos (Bellman-Ford e Djikstra) falham em grafos com ciclos negativos!
	 */
	//Por enquanto o metodo ta "calculando" um mapa com as menores distancias entre um vertice "v" e todos os outros vertices
	public String shortestPath(Vertice v, Vertice vfinal) {
		
		// Mapa associando Vertice como chave e a distancia de um "vertice raiz" passado no parametro até ele como valor.
		Map<Vertice, Double> mapaDistancias = new HashMap<Vertice, Double>();
		Map<Vertice, Vertice> mapaVerticePai = new HashMap<Vertice, Vertice>(); //Mapa associando cada vertice ao seu vertice pai. 
		//Dizemos que V1 é pai de V2, se V1 foi usado para chegar até V2 por um "menor caminho". Sendo V1 e V2 vértices
		
		//Configurando o mapa/tabela:
		mapaDistancias.put(v, 0.0); //A distancia entre o "vertice raiz" e ele mesmo é 0, ja a dist entre o vertice raiz e os outros é infinito inicialmente.
		mapaVerticePai.put(v, v); //O vertice usado para chegar no vertice inicial com a menor distancia possivel, é ele mesmo
		for(int i = 0; i < this.getVertices().length; i++) {
			if(!this.getVertices()[i].equals(v)) {
				mapaDistancias.put(this.getVertices()[i], Double.POSITIVE_INFINITY);	
				mapaVerticePai.put(this.getVertices()[i], null);
				
			}
		}
		
		
		
		//Em V-1 iteracoes, examinar cada vertice do grafo, observando os pesos das arestas que sao ligadas a ele, pois, temos como objetivo analisar todas as arestas do grafo
		// V-1 iteracoes: Pois, nao precisa "caminhar" por todos os vertices do grafo para ter acesso a todas as arestas.
			for(int i = 0; i < this.getQtdVertices() -1; i++) {
				double currentPeso = mapaDistancias.get(this.getVertices()[i]);
				Vertice vertice = this.getVertices()[i];
				for(Aresta aresta : Arrays.asList(vertice.getArestas())) { //No if e no else if, esta havendo a atualizacao para uma distancia menor
					if(!aresta.getStart().equals(vertice) && (mapaDistancias.get(aresta.getStart()) > aresta.getPeso() + currentPeso)) {
						mapaDistancias.put(aresta.getStart(), aresta.getPeso() + currentPeso);
						mapaVerticePai.put(aresta.getStart(), vertice); //Atualiza o pai no mapa
					}else if (aresta.getStart().equals(vertice) && (mapaDistancias.get(aresta.getEnd()) > aresta.getPeso() + currentPeso)) {
						mapaDistancias.put(aresta.getEnd(), aresta.getPeso() + currentPeso);
						mapaVerticePai.put(aresta.getEnd(), vertice);
					}
				}
			}
			
		//Percorrendo pai por pai, do vfinal até o vinicial(v), para produzir a String de saída do menor caminho entre v e vfinal.
		String retorno = "";
		retorno = vfinal.getValue() + retorno;
		Vertice vpai = mapaVerticePai.get(vfinal);
		while(!vpai.equals(v)) {
			retorno = vpai.getValue() + " " + retorno;
			vpai = mapaVerticePai.get(vpai);
			
		}
		
		retorno = vpai.getValue() + " " + retorno + System.lineSeparator() + "Mapa Vertice Pai: "+mapaVerticePai + System.lineSeparator()
		+ "Vertice inicial e final: " + v.getValue() + vfinal.getValue();
		return retorno;
	}
	
	public Vertice getVerticePerValue(int value) throws Exception {
		for(Vertice v : this.getVertices()) {
			if (v.getValue() == value){
				return v;
			}
		}
		throw new Exception("Vertice with value " + value + " not found");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + qtdVertices;
		result = prime * result + Arrays.hashCode(vertices);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Graph other = (Graph) obj;
		if (qtdVertices != other.qtdVertices)
			return false;
		if (!Arrays.equals(vertices, other.vertices))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Graph [ qtdVertices=" + qtdVertices + "vertices=" + Arrays.toString(vertices) + "]";
	}

	public Vertice[] getVertices() {
		return vertices;
	}

	public int getQtdVertices() {
		return qtdVertices;
	}
	
}
