package grafo;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.swing.JOptionPane;

import comparador.ComparadorVertice;
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
	 * Caminho m�nimo. A biblioteca deve ser capaz de
	 * encontrar o caminho mais curto entre dois v�rtices.
	 * 
	 * USO DO ALGORITMO DE BELLMAN-FORD:
	 * Para cada vertice, atualizar sua distancia do "vertice raiz" sempre que necessario e analisar todas as suas outras arestas.
	 *  **Ambos algoritmos (Bellman-Ford e Djikstra) falham em grafos com ciclos negativos!
	 */
	//Por enquanto o metodo ta "calculando" um mapa com as menores distancias entre um vertice "v" e todos os outros vertices
	public String shortestPath(Vertice v, Vertice vfinal) {
		
		// Mapa associando Vertice como chave e a distancia de um "vertice raiz" passado no parametro at� ele como valor.
		Map<Vertice, Double> mapaDistancias = new HashMap<Vertice, Double>();
		Map<Vertice, Vertice> mapaVerticePai = new HashMap<Vertice, Vertice>(); //Mapa associando cada vertice ao seu vertice pai. 
		//Dizemos que V1 � pai de V2, se V1 foi usado para chegar at� V2 por um "menor caminho". Sendo V1 e V2 v�rtices
		
		//Configurando o mapa/tabela:
		mapaDistancias.put(v, 0.0); //A distancia entre o "vertice raiz" e ele mesmo � 0, ja a dist entre o vertice raiz e os outros � infinito inicialmente.
		mapaVerticePai.put(v, v); //O vertice usado para chegar no vertice inicial com a menor distancia possivel, � ele mesmo
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
			
		//Percorrendo pai por pai, do vfinal at� o vinicial(v), para produzir a String de sa�da do menor caminho entre v e vfinal.
		String retorno = "";
		retorno = vfinal.getValue() + retorno;
		Vertice vpai = mapaVerticePai.get(vfinal);
		while(!vpai.equals(v)) {
			retorno = vpai.getValue() + " " + retorno;
			vpai = mapaVerticePai.get(vpai);
			
		}
		
		retorno = vpai.getValue() + " " + retorno;
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
	
	
	/**
	 * @author Pedro
	 * Retorna true se o grafo � conectado, ou false se o grafo nao � conectado
	 * @return
	 */
	public boolean connected() {
		Map<Vertice, Boolean> vtcs = BfsAuxiliar(this.getVertices()[0]);
		for(Vertice v : vtcs.keySet()) {
			if (vtcs.get(v) == false) { //Significa que na bfs, houve vertices nao visitados, logo, o grafo e desconectado
				return false;
			}
		}
		
		return true; //todos os vertices foram visitados.
		
	}
	
	
	
	/**
	 * @param vertice
	 * @author Pedro
	 * Metodo auxiliar para ser usado no m�todo connected.
	 * -> Dado um vertice, retorna todos os vertices percorridos feita uma busca em 
	 * largura(bfs) a partir do vertice passado.
	 * @return Mapa com a chave sendo os vertices do grafo e o valor como sendo "true" se o vertice foi visitado
	 * ou "false" se o vertice nao foi visitado pela busca em largura
	 */
	private Map<Vertice, Boolean> BfsAuxiliar(Vertice vertice){
		Map<Vertice, Boolean> verticesMarcados = new HashMap<Vertice, Boolean>();
		Queue<Vertice> fila = new LinkedList<Vertice>();
		for(Vertice v : this.getVertices()) {//adiciona todos os vertices no mapa como nao marcados
			verticesMarcados.put(v, false);
		}
		
		//Marca o vertice inicial e enfileira ele
		verticesMarcados.put(vertice, true);
		fila.add(vertice);
		
		while(!fila.isEmpty()) {
			Vertice currentVertice = fila.remove();
			
			for(Vertice v : verticesMarcados.keySet()) { //Loop pra fixar o erro do "id" do objeto...
				if (v.getValue() == currentVertice.getValue()) {
					currentVertice = v;
				}
			}
			
			for(Vertice v : getAdjacentVertices(currentVertice)) {
				if(verticesMarcados.get(v) == false) {
					verticesMarcados.put(v, true);
					fila.add(v);	
				}
			}
			
		}
		
		
		return verticesMarcados;
		
		
		
	}
	
	//Metodo auxiliar usado na bfs
	private List<Vertice> getAdjacentVertices(Vertice vertice) {
		List<Vertice> adjacentVertices = new LinkedList<Vertice>();
		
		for(Aresta aresta : vertice.getArestas()) {
			if(aresta.getEnd().equals(vertice)) {
				adjacentVertices.add(aresta.getStart());
			}else {
				adjacentVertices.add(aresta.getEnd());
			}
		}
		
		return adjacentVertices;
	}
	
	/**
	 * @author Danielle de Lima Vieira
	 * O método recebe um grafo conectado e um vertice raiz e percorre o grafo realizando uma  busca em profundidade. Como resultado o método
	 * retorna uma string que representa a árvore gerada pela busca. 
	 * @param raiz - vertice do qual o método iniciará a busca
	 * @return string que representa a árvore de busca
	 */
	public String DFS(int raiz) {
		List<Vertice> vertices = geraListaOrdenadaDeVerticesDoGrafo();
	
		Deque<Vertice> pilha = new ArrayDeque<>();
		Map<Vertice, Integer> nivel = new HashMap<>();
		Map<Vertice, Vertice> predecessor = new HashMap<>();
		List<Vertice> marcados = new ArrayList<>();
		
		
		for (Vertice vertice : vertices) {
			if(vertice.getValue() == raiz) {
				nivel.put(vertice, 0);
				predecessor.put(vertice, null);
				pilha.push(vertice);
				marcados.add(vertice);
				break;
			}
		}
		
		while (!pilha.isEmpty()) {
			Vertice cabecaDaPilha = pilha.peek();
			int valor = cabecaDaPilha.getValue();
			List<Aresta> arestas = new ArrayList<>();
			arestas = Arrays.asList(vertices.get(valor - 1).getArestas());
			Vertice vizinho = encontraVerticeVizinho(arestas, pilha, marcados);
			
			if(!arestas.isEmpty() && vizinho != null) {
				pilha.push(vizinho);
				predecessor.put(vizinho, cabecaDaPilha);
				nivel.put(vizinho, nivel.get(cabecaDaPilha) + 1);
			} else {
				pilha.pop();
			}
		}

		return geraStringDeSaidaBFSeDFS(vertices, nivel, predecessor);
	}
	
	/**
	 * @author Danielle de Lima Vieira
	 * O método recebe um grafo conectado e um vertice raiz e percorre o grafo realizando uma  busca em largura. Como resultado o método
	 * retorna uma string que representa a árvore gerada pela busca.  
	 * @param raiz - vertice do qual o método iniciará a busca
	 * @return string que representa a árvore de busca
	 */
	public String BFS(int raiz) {
		List<Vertice> vertices = geraListaOrdenadaDeVerticesDoGrafo();
	
		Deque<Vertice> fila = new ArrayDeque<>();
		Map<Vertice, Integer> nivel = new HashMap<>();
		Map<Vertice, Vertice> predecessor = new HashMap<>();
		List<Vertice> marcados = new ArrayList<>();
		
		
		for (Vertice vertice : vertices) {
			if(vertice.getValue() == raiz) {
				nivel.put(vertice, 0);
				predecessor.put(vertice, null);
				fila.offer(vertice);
				marcados.add(vertice);
				break;
			}
		}
	
		while (!fila.isEmpty()) {
			Vertice cabecaDaFila = fila.peek();
			int valor = cabecaDaFila.getValue();
			List<Aresta> arestas = new ArrayList<>();
			arestas = Arrays.asList(vertices.get(valor - 1).getArestas());
			Vertice vizinho = encontraVerticeVizinho(arestas, fila, marcados);
			
			if(!arestas.isEmpty() && vizinho != null) {
				fila.offer(vizinho);
				predecessor.put(vizinho, cabecaDaFila);
				nivel.put(vizinho, nivel.get(cabecaDaFila) + 1);
			} else {
				fila.remove();
			}
		}
		
		return geraStringDeSaidaBFSeDFS(vertices, nivel, predecessor);
	}
	
	/* encontra e retorna um vertice vizinho que ainda não tenha sido marcado para os métodos BFS e DFS ou retorna null caso 
	não existam vertices vizinhos não marcados */
	private Vertice encontraVerticeVizinho(List<Aresta> arestas, Deque<Vertice> pilhaOuFila, List<Vertice> marcados) {
		if(!arestas.isEmpty()) {
			for (Aresta aresta : arestas) {
				if(!pilhaOuFila.contains(aresta.getStart()) && !marcados.contains(aresta.getStart())) {
					marcados.add(aresta.getStart());
					return aresta.getStart(); 
				} else if(!pilhaOuFila.contains(aresta.getEnd()) && !marcados.contains(aresta.getEnd())) {
					marcados.add(aresta.getEnd());
					return aresta.getEnd();
				}
			}
			return null;
		}
		return null;
	}
	
	//gera e retorna um ArrayList ordenado a partir do conjunto de vertices do grafo.
	private List<Vertice> geraListaOrdenadaDeVerticesDoGrafo() {
		List<Vertice> vertices = new ArrayList<>();
		vertices = Arrays.asList(this.getVertices());
		Collections.sort(vertices, new ComparadorVertice());
		return vertices;
	}
	
	//gera e retorna uma string que representa a árvore de busca resultante dos métodos BFS ou DSF.
	private String geraStringDeSaidaBFSeDFS(List<Vertice> vertices, Map<Vertice, Integer> nivel, Map<Vertice, Vertice> predecessor) {
		Iterator<Vertice> itr = vertices.iterator();
		String str = "";
		while (itr.hasNext()) {
			Vertice vertice = itr.next();
			if(predecessor.get(vertice) == null) {
				str += vertice.getValue() + " - " + nivel.get(vertice) + " -" + System.lineSeparator();
			} else {
				str += vertice.getValue() + " - " + nivel.get(vertice) + " "  + predecessor.get(vertice).getValue() + System.lineSeparator();
			}
		}
		return str;
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
	
	/**
	 * @author Itamar da Silva Farias
	 * 			Método que acha a arvore geradora minima através de uma matriz inicial
	 * 			e uma matriz de recorrência mínima
	 * @param matrizOriginal
	 * @return matriz representando arvore geradora mínima
	 */
	public int[][] MST(int[][] matrizOriginal) {

		ArrayList<Boolean> verticesVisitados = new ArrayList<>();

		ArrayList<Integer> distanciasRelativas = new ArrayList<>();

		ArrayList<Integer> nosAdjacentes = new ArrayList<>();

		for (Integer contador = 0; contador < matrizOriginal[0].length; contador++) {
			verticesVisitados.add(false);
			nosAdjacentes.add(0); // verifica se são adjacentes
			distanciasRelativas.add(Integer.MAX_VALUE); // Distancias relativas e equivalentes

		}

		distanciasRelativas.set(0, new Integer(0));

		// definição de quem será o ponto da raiz resultante

		Integer pontoAvaliado = 0; // Iniciando as variáveis
		Integer adjacente = 0;

		// Execucação do algoritmo de Prim??

		for (Integer contadorPontosAvaliados = 0; contadorPontosAvaliados < matrizOriginal[0].length; contadorPontosAvaliados++) {
			for (Integer contadorAdjacentes = 0; contadorAdjacentes < matrizOriginal[0].length; contadorAdjacentes++) {
				// verificação do nó
				if ((verticesVisitados.get(contadorAdjacentes)) || (contadorAdjacentes == pontoAvaliado))
					continue;
				/**
				 * Comparações 1º - Verifica se a matrizOriginal tem algum valor na coluna que é
				 * > 0. Se sim segnifica que existe uma aresta entre estes dois pontos* 2º -
				 * Verifica se o peso da aresta entre os dois nós é menor que a atual distancia
				 * do nó vizinho
				 * 
				 **/

				if ((matrizOriginal[pontoAvaliado][contadorAdjacentes] > 0)
						&& ((matrizOriginal[pontoAvaliado][contadorAdjacentes] < distanciasRelativas
								.get(contadorAdjacentes)))) {

					distanciasRelativas.set(contadorAdjacentes, matrizOriginal[pontoAvaliado][contadorAdjacentes]);
					nosAdjacentes.set(contadorAdjacentes, pontoAvaliado);
				}
			}
		}

		/**
		 * Marca os vértices de ponto avaliados como pontos já verificados
		 */

		verticesVisitados.set(pontoAvaliado, true);
		pontoAvaliado = new Integer(0);
		Integer distanciaAtualAComparar = new Integer(Integer.MAX_VALUE);

		/**
		 * Selecionar o próximo vértice a ser avaliado
		 */

		for (Integer contador = 1; contador < verticesVisitados.size(); contador++) {
			/**
			 * Verifica se o vértice foi validade anteriormente (true) para para próxima
			 * iteração
			 */
			if (verticesVisitados.get(contador))
				continue;

			if (distanciasRelativas.get(contador) < distanciaAtualAComparar)
				distanciaAtualAComparar = distanciasRelativas.get(contador);

		}

		int[][] matrizFinal = new int[matrizOriginal[0].length][matrizOriginal[0].length];

		/**
		 * Cria uma matriz original com uma arvore resultante do algoritmo de prim
		 */

		for (int contador = 0; contador < nosAdjacentes.size(); contador++) { // estabelecer um valor
			matrizFinal[contador][nosAdjacentes.get(contador)] = matrizOriginal[contador][nosAdjacentes.get(contador)];
			matrizFinal[nosAdjacentes.get(contador)][contador] = matrizFinal[contador][nosAdjacentes.get(contador)];
		}

		return matrizFinal;

	}
	
}
