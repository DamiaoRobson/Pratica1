package comparador;

import java.util.Comparator;

import grafo.Aresta;

public class comparaPesoAresta implements Comparator<Aresta> {
	
	@Override
	public int compare(Aresta a1, Aresta a2) {
		if (a1.getPeso() > a2.getPeso())
			return 1;
		if (a1.getPeso() < a2.getPeso())
			return -1;
		return 0;
	}

}
