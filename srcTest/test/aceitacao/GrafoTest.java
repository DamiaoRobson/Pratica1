package test.aceitacao;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import grafo.Graph;
import grafo.Vertice;
import main.GraphLib;

public class GrafoTest {

	private GraphLib library;
	private Graph grafoSemPeso;
	private Graph grafoComPeso;

	
	@Before
	public void setUp(){
		library = new GraphLib();
		grafoSemPeso = library.readGraph("grafo1.txt");
		grafoComPeso = library.readGraph("grafo1.txt");
	}
	
	@Test
	public void testCriaGrafo() {
		Graph newGrafoSemPeso = library.readGraph("grafo1.txt");
		Graph newGrafoComPeso = library.readGraph("grafo2.txt");
		
		
		System.out.println(library.graphRepresentation(newGrafoSemPeso, "AM"));
		System.out.println(library.graphRepresentation(newGrafoComPeso, "AM"));
		
	}
	
	@Test
	public void testCriaGrafoComPeso() {
	}
	
	@Test
	public void testShorttestPath() {
		Graph newGrafo = library.readGraph("grafo2.txt");
		Vertice[] vertices = newGrafo.getVertices();
		try {
			Vertice vinicial = newGrafo.getVerticePerValue(1);
			Vertice vfinal = newGrafo.getVerticePerValue(5);
			String mapaDeMenoresCaminhos = newGrafo.shortestPath(vinicial, vfinal);
			System.out.println("Mapa de menores caminhos: " + mapaDeMenoresCaminhos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
