package grafo;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Damiao Robson Domiciano
 *
 *         Possui um valor, funiona com no de duas arestas
 */
public class Vertice {

	private int valuer;
	private List<Aresta> arestas;

	/**
	 * Cria Vertice com o valor e a lista que sai ou entra
	 * 
	 * @param valuer
	 *            valor da aresta
	 */
	public Vertice(int valuer) {
		this.valuer = valuer;
		this.arestas = new LinkedList<>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + valuer;
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
		Vertice other = (Vertice) obj;
		if (valuer != other.valuer)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vertice [valuer=" + valuer + "]";
	}

	/**
	 * Insere nova vertice a aresta
	 * 
	 * @param novaVertice
	 * @return retorna verdadeiro para vercice dentro do padrao e nao addicionada
	 *         anteriormente
	 */
	public boolean adicionaAresta(Aresta novaVertice) {
		return arestas.add(novaVertice);
	}
	
	public int getValue() {
		return this.valuer;
	}

}
