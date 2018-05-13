package comparador;

import java.util.Comparator;

import grafo.Vertice;

/**
 * 
 * @author Damiao Robson Domiciano
 * 
 *         Comparador de Vertice
 *
 */
public class ComparadorVertice implements Comparator<Vertice> {

	/**
	 * Compara as Vertices para serem ordenadas
	 */
	@Override
	public int compare(Vertice v1, Vertice v2) {
		if (v1.getValue() > v2.getValue())
			return 1;
		if (v1.getValue() < v2.getValue())
			return -1;
		return 0;
	}

}
