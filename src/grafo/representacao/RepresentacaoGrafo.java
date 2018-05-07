package grafo.representacao;

import grafo.Graph;

public class RepresentacaoGrafo {
	
	private String[][] matriz;
	
	public RepresentacaoGrafo(){
	}	
	

	private void criarMatriz(int qtdVertices) {
		matriz = new String[qtdVertices][qtdVertices];
		matriz[0][0] = "";
		for (int i = 1; i < matriz.length; i++) {
			matriz[i][i] = "" + i;
			
		}
		
	}


	public String representacao(Graph graph, String type) {
		return null;
	}
	

}
