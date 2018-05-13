package grafo;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import comparador.ComparadorAresta;

/**
 * @author Damiao Robson Domiciano
 *
 *         Possui um valor, funiona com no de duas arestas
 */
public class Vertice {

	private int valuer;
	private List<Aresta> arestas;
	
	public int getQntArestas() {
		return arestas.size();
	}
	
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
		return "Vertice (valuer=" + valuer + ")";
	}

	/**
	 * Insere nova vertice a aresta e ordena de acordo com o vertice Start
	 * 
	 * @param novaVertice
	 * @return retorna verdadeiro para vercice dentro do padrao e nao
	 *         addicionada anteriormente
	 */
	public boolean adicionaAresta(Aresta novaVertice) {
		boolean guard = arestas.add(novaVertice);
		Collections.sort(this.arestas, new ComparadorAresta());
		return guard;
	}

	/**
	 * Get das arestas ligadas a vertice
	 * 
	 * @return Um array de arestas que estao ligadas a vertice
	 */
	public Aresta[] getArestas() {
		Aresta[] arest = new Aresta[this.arestas.size()];
		for (int i = 0; i < this.arestas.size(); i++)
			arest[i] = this.arestas.get(i);

		return arest;
	}

	public int getValue() {
		return this.valuer;
	}
	

}
