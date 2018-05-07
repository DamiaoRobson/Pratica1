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
	private List<Aresta> vertices;

	/**
	 * Cria Vertice com o valor e a lista que sai ou entra
	 * 
	 * @param valuer
	 *            valor da aresta
	 */
	public Vertice(int valuer) {
		this.valuer = valuer;
		this.vertices = new LinkedList<>();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + valuer;
		result = prime * result + ((vertices == null) ? 0 : vertices.hashCode());
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
		if (vertices == null) {
			if (other.vertices != null)
				return false;
		} else if (!vertices.equals(other.vertices))
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
	public boolean adicionaVertice(Aresta novaVertice) {
		return vertices.add(novaVertice);
	}

}
