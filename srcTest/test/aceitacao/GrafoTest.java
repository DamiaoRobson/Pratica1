package test.aceitacao;

import static org.junit.Assert.*;

import java.text.DecimalFormat;
import java.util.Map;

import org.junit.Assert;
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
		grafoComPeso = library.readGraph("grafo2.txt");
	}
	
	@Test
	public void testCriaGrafo() {
		Graph newGrafoSemPeso = library.readGraph("grafo1.txt");
		Graph newGrafoComPeso = library.readGraph("grafo2.txt");
	}
	
	@Test
	public void testGraphRerpesentation(){
		System.out.println(library.graphRepresentation(grafoSemPeso, "AM"));
		System.out.println(library.graphRepresentation(grafoComPeso, "AM"));
		System.out.println(library.graphRepresentation(grafoSemPeso, "AL"));
		System.out.println(library.graphRepresentation(grafoComPeso, "AL"));
		
	}
	
	@Test
	public void testShorttestPath() {
		Graph newGrafo = library.readGraph("grafo2.txt");
		Vertice[] vertices = newGrafo.getVertices();
		try {
			Vertice vinicial = newGrafo.getVerticePerValue(1);
			Vertice vfinal = newGrafo.getVerticePerValue(4);
			String mapaDeMenoresCaminhos = newGrafo.shortestPath(vinicial, vfinal);
			Assert.assertTrue("1 5 4".equals(mapaDeMenoresCaminhos));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConnected() {
		Graph newGrafo = library.readGraph("grafo1.txt");
		Graph grafoDesconectado = library.readGraph("grafoDesconectado.txt");
		System.out.println("Arestas" + newGrafo.getVertices()[4].getArestas().length);
		Assert.assertEquals(true, library.connected(newGrafo));
		Assert.assertEquals(false, library.connected(grafoDesconectado));
		
	}
	
	
	@Test
	public void testVertexNumber() {
		Graph grafo1 = library.readGraph("grafo1.txt");
		Graph grafo2 = library.readGraph("grafo2.txt");
		Graph grafo3 = library.readGraph("grafo3.txt");
		
		//library.getVertexNumber(grafo1);
		//library.getVertexNumber(grafo3);
		
		Assert.assertEquals(5, library.getVertexNumber(grafo1));
		Assert.assertEquals(5, library.getVertexNumber(grafo2));
		Assert.assertEquals(4, library.getVertexNumber(grafo3));
	}
	
	@Test
	public void testGetEdgeNumber() {
		Graph grafo1 = library.readGraph("grafo1.txt");
		Graph grafo2 = library.readGraph("grafo2.txt");
		Graph grafo3 = library.readGraph("grafo3.txt");
		
		library.getEdgeNumber(grafo1);
		library.getEdgeNumber(grafo2);
		library.getEdgeNumber(grafo3);
		
		Assert.assertEquals(5, library.getEdgeNumber(grafo1));
		Assert.assertEquals(6, library.getEdgeNumber(grafo2));
		Assert.assertEquals(4, library.getEdgeNumber(grafo3));
	}
	
	@Test
	public void testGetMeanEdge() {
		Graph grafo2 = library.readGraph("grafo2.txt");
		
		library.getMeanEdge(grafo2);
		DecimalFormat df = new DecimalFormat("0.##");
		String saida = df.format(library.getMeanEdge(grafo2));
		
		
		//Assert.assertEquals("1,85", saida, 0);
		Assert.assertEquals("1,85", saida);
	}

}
