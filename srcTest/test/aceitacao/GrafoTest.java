package test.aceitacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import grafo.Graph;

public class GrafoTest {

	private Graph grafo;
	private Graph grafoSemPeso;
	private Graph grafoComPeso;
	
	@Before
	public void setUp(){
		grafo = new Graph();
	}
	
	@Test
	public void testCriaGrafo() {
		grafoSemPeso = grafo.readGraph("grafo1.txt");
		
		System.out.println(grafoSemPeso);
		
	}
	
	@Test
	public void testCriaGrafoComPeso() {
		grafoComPeso = grafo.readGraph("grafo2.txt");
		
		System.out.println(grafoComPeso);
	}

}
