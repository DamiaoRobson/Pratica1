package comparador;

import java.util.Comparator;

import grafo.Aresta;

public class ComparadorAresta implements Comparator<Aresta> {

	@Override
	public int compare(Aresta a1, Aresta a2) {
		if (a1.getStart().getValue() > a2.getStart().getValue())	return 1;
		if (a1.getStart().getValue() < a2.getStart().getValue())	return -1;
		return 0;
	}
}
