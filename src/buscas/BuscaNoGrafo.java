package buscas;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import comparador.ComparadorVertice;
import grafo.Aresta;
import grafo.Graph;
import grafo.Vertice;

/**
 * @author Danielle de Lima Vieira
 *
 */
public class BuscaNoGrafo {
	
	public String BSF(Graph grafo, int raiz) {
		List<Vertice> vertices = new ArrayList<>();
		vertices = Arrays.asList(grafo.getVertices());
		
		Collections.sort(vertices, new ComparadorVertice());
	
		Queue<Vertice> fila = new LinkedBlockingQueue<>();
		Map<Vertice, Integer> nivel = new HashMap<>();
		Map<Vertice, Vertice> predecessor = new HashMap<>();
		List<Vertice> marcados = new ArrayList<>();
		
		
		for (Vertice vertice : vertices) {
			if(vertice.getValue() == raiz) {
				nivel.put(vertice, 0);
				predecessor.put(vertice, null);
				fila.offer(vertice);
				marcados.add(vertice);
			}
		}
	
		while (!fila.isEmpty()) {
			Vertice cabecaDaFila = fila.peek();
			int valor = cabecaDaFila.getValue();
			List<Aresta> arestas = new ArrayList<>();
			arestas = Arrays.asList(vertices.get(valor - 1).getArestas());
			Vertice vizinho = encontrarVizinhoBSF(arestas, fila, marcados);
			
			if(!arestas.isEmpty() && vizinho != null) {
				fila.offer(vizinho);
				predecessor.put(vizinho, cabecaDaFila);
				nivel.put(vizinho, nivel.get(cabecaDaFila) + 1);
			} else {
				fila.remove(cabecaDaFila);
			}
		}
		
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
	
	private Vertice encontrarVizinhoBSF(List<Aresta> arestas, Queue<Vertice> fila, List<Vertice> marcados) {
		if(!arestas.isEmpty()) {
			for (Aresta aresta : arestas) {
				if(!fila.contains(aresta.getStart()) && !marcados.contains(aresta.getStart())) {
					marcados.add(aresta.getStart());
					return aresta.getStart(); 
				} else if(!fila.contains(aresta.getEnd()) && !marcados.contains(aresta.getEnd())) {
					marcados.add(aresta.getEnd());
					return aresta.getEnd();
				}
			}
			return null;
		}
		return null;
	}
	
	public String DFS(Graph grafo, int raiz) {
		List<Vertice> vertices = new ArrayList<>();
		vertices = Arrays.asList(grafo.getVertices());
		
		Collections.sort(vertices, new ComparadorVertice());
	
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
			}
		}
		
		while (!pilha.isEmpty()) {
			Vertice cabecaDaPilha = pilha.peek();
			int valor = cabecaDaPilha.getValue();
			List<Aresta> arestas = new ArrayList<>();
			arestas = Arrays.asList(vertices.get(valor - 1).getArestas());
			Vertice vizinho = encontrarVizinhoDFS(arestas, pilha, marcados);
			
			if(!arestas.isEmpty() && vizinho != null) {
				pilha.push(vizinho);
				predecessor.put(vizinho, cabecaDaPilha);
				nivel.put(vizinho, nivel.get(cabecaDaPilha) + 1);
			} else {
				pilha.pop();
			}
		}
		
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
	
	private Vertice encontrarVizinhoDFS(List<Aresta> arestas, Deque<Vertice> pilha, List<Vertice> marcados) {
		if(!arestas.isEmpty()) {
			for (Aresta aresta : arestas) {
				if(!pilha.contains(aresta.getStart()) && !marcados.contains(aresta.getStart())) {
					marcados.add(aresta.getStart());
					return aresta.getStart(); 
				} else if(!pilha.contains(aresta.getEnd()) && !marcados.contains(aresta.getEnd())) {
					marcados.add(aresta.getEnd());
					return aresta.getEnd();
				}
			}
			return null;
		}
		return null;
	}
	
	
}
