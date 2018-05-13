package comparador;

import java.util.Comparator;

import grafo.Aresta;

/**
 * 
 * @author Damiao Robson Domiciano
 * 
 *         Comaparador de Arestas
 *
 */
public class ComparadorAresta implements Comparator<Aresta> {

	/**
	 * Realiza a omparacao entre arestas
	 */
	@Override
	public int compare(Aresta a1, Aresta a2) {
		if (a1.getStart().getValue() > a2.getStart().getValue())
			return 1;
		if (a1.getStart().getValue() < a2.getStart().getValue())
			return -1;
		return 0;
	}
}
