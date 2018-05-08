package test.aceitacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import grafo.Graph;
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

}
