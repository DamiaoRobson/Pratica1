package grafo;

/**
 * @author Damiao Robson Domiciano
 *
 *         Liga uma Vertice a outra
 */
public class Aresta {

	private Vertice start;
	private Vertice end;
	private double peso;

	/**
	 * Inicia a aresta com Vertice de inicio e Vertice final
	 * 
	 * @param start Inicio da aresta
	 * @param end Fim da aresta
	 */
	public Aresta(Vertice start, Vertice end) {
		this.start = start;
		this.end = end;
		this.peso = 1;
	}

	/**
	 * Inicia a aresta com Vertice de inicio, Vertice final e peso do aresta
	 * 
	 * @param start Inicio da aresta
	 * @param end Fim da aresta
	 * @param peso Peso da aresta
	 */
	public Aresta(Vertice start, Vertice end, double peso) {
		this.start = start;
		this.end = end;
		this.peso = peso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		Aresta other = (Aresta) obj;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Aresta (start=" + start + ", end=" + end + ", peso=" + peso + ")";
	}

	public Vertice getStart() {
		return start;
	}

	public void setStart(Vertice start) {
		this.start = start;
	}

	public Vertice getEnd() {
		return end;
	}

	public void setEnd(Vertice end) {
		this.end = end;
	}

	public double getPeso() {
		return this.peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	

}
