package test.aceitacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import grafo.Grafo;

public class GrafoTest {

	private Grafo grafo;
	private Grafo grafoSemPeso;
	private Grafo grafoComPeso;
	
	@Before
	public void setUp(){
		grafo = new Grafo();
	}
	
	@Test
	public void testCriaGrafo() {
		grafoSemPeso = grafo.criaGrafo("grafo1.txt");
		grafoComPeso = grafo.criaGrafo("grafo2.txt");
		
		System.out.println(grafoSemPeso);
		System.out.println(grafoComPeso);
	}

}
