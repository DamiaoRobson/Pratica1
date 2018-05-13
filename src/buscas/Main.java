package buscas;

import grafo.Aresta;
import grafo.Graph;
import main.GraphLib;

public class Main {

	
	
	public static void main(String[] args) throws Exception {
		
		GraphLib library;
		Graph grafoSemPeso;
		Graph grafoComPeso;
		BuscaNoGrafo busca;
		
		library = new GraphLib();
		grafoSemPeso = library.readGraph("grafo1.txt");
		grafoComPeso = library.readGraph("grafo2.txt");
		busca = new BuscaNoGrafo();
		System.out.println(busca.BSF(grafoSemPeso, 1));
		System.out.println(busca.DFS(grafoSemPeso, 1));
		
	}

}
