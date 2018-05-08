package test.aceitacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import grafo.Graph;
import main.GraphLib;

public class GrafoTest {

	private GraphLib grafo;
	private Graph grafoSemPeso;
	private Graph grafoComPeso;
	
	@Before
	public void setUp(){
		grafo = new GraphLib();
	}
	
	@Test
	public void testCriaGrafo() {
		grafoSemPeso = grafo.readGraph("grafo1.txt");
		grafoComPeso = grafo.readGraph("grafo2.txt");
		
		System.out.println(grafoComPeso);
		
		System.out.println(grafoSemPeso);
		
	}
	
	@Test
	public void testCriaGrafoComPeso() {
		grafoComPeso = grafo.readGraph("grafo2.txt");
		
		System.out.println(grafoComPeso);
	}

}
