package test.aceitacao;

import static org.junit.Assert.*;

import java.text.DecimalFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import grafo.Graph;
import grafo.Vertice;

import main.GraphLib;

public class GrafoTest {

	private final String FL = System.lineSeparator();
	private GraphLib library;
	private Graph grafoSemPeso;
	private Graph grafoComPeso;
	private Graph grafoSemPeso2;
	private String representacaoAM ="0 1 2 3 4 5" + FL +
									"1 0 1 0 0 1" + FL +
									"2 1 0 0 0 1" + FL +
									"3 0 0 0 0 1" + FL +
									"4 0 0 0 0 1" + FL +
									"5 1 1 1 1 0";
	private String representacaoAMComPeso = "0 1 2 3 4 5" + FL +
											"1 0 0,1 0 0 1" + FL +
											"2 0,1 0 0 0 2" + FL +
											"3 0 0 0 1 5" + FL +
											"4 0 0 1 0 2" + FL +
											"5 1 2 5 2 0";
	private String representacaoAL ="1 - 2 5" + FL +
									"2 - 1 5" + FL +
									"3 - 5" + FL +
									"4 - 5" + FL +
									"5 - 1 2 4 3";
	private String representacaoALComPeso = "1 - 2(0,1) 5(1)" + FL +
											"2 - 1(0,1) 5(2)" + FL +
											"3 - 4(1) 5(5)" + FL +
											"4 - 3(1) 5(2)" + FL +
											"5 - 1(1) 2(2) 4(2) 3(5)";
	
	private String representacaoBFSSemPeso = "1 - 0 -" +  FL + 
											"2 - 1 1" + FL + 
											"3 - 2 5" + FL + 
											"4 - 2 5" + FL + 
											"5 - 1 1" + FL;
	
	private String representacaoDFSSemPeso ="1 - 0 -" +  FL + 
											"2 - 1 1" + FL + 
											"3 - 3 5" + FL + 
											"4 - 3 5" + FL + 
											"5 - 2 2" + FL;
	
	private String representacaoDFSSemPeso2 ="1 - 0 -" +  FL + 
											"2 - 1 1" + FL + 
											"3 - 2 2" + FL + 
											"4 - 2 2" + FL;
	
	private String representacaoBFSSemPeso2 ="1 - 0 -" +  FL + 
											"2 - 1 1" + FL + 
											"3 - 1 1" + FL + 
											"4 - 2 2" + FL;

	
	@Before
	public void setUp(){
		library = new GraphLib();
		grafoSemPeso = library.readGraph("grafo1.txt");
		grafoComPeso = library.readWeightedGraph("grafo2.txt");
		grafoSemPeso2 = library.readGraph("grafo3.txt");
	}
	
	@Test
	public void testCriaGrafo() {
		Graph newGrafoSemPeso = library.readGraph("grafo1.txt");
		Graph newGrafoComPeso = library.readWeightedGraph("grafo2.txt");
		assertEquals(representacaoAM, library.graphRepresentation(newGrafoSemPeso, "AM"));
		assertEquals(representacaoAMComPeso, library.graphRepresentation(newGrafoComPeso, "AM"));
	}
	
	@Test
	public void testGraphRerpesentation(){
		assertEquals(representacaoAM, library.graphRepresentation(grafoSemPeso, "AM"));
		assertEquals(representacaoAMComPeso, library.graphRepresentation(grafoComPeso, "AM"));
		assertEquals(representacaoAL, library.graphRepresentation(grafoSemPeso, "AL"));
		assertEquals(representacaoALComPeso, library.graphRepresentation(grafoComPeso, "AL"));
//		Imprimir Reprecaoo do grafo
		/*System.out.println("Representacao Grafo sem peso Matriz Adjacente:" + FL + library.graphRepresentation(grafoSemPeso, "AM"));
		System.out.println("Representacao Grafo com peso Matriz Adjacente:" + FL + library.graphRepresentation(grafoComPeso, "AM"));
		System.out.println("Representacao Grafo sem peso Lista Adjacente:" + FL + library.graphRepresentation(grafoSemPeso, "AL"));
		System.out.println("Representacao Grafo com peso lista Adjacente:" + FL + library.graphRepresentation(grafoComPeso, "AL"));*/
		
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
		Graph grafo1 = library.readGraph("grafo1.txt");
		
		DecimalFormat df = new DecimalFormat("0.#");
		String saida = df.format(library.getMeanEdge(grafo2));
		String saida1 = df.format(library.getMeanEdge(grafo1));
		
		
		//Assert.assertEquals("1,85", saida, 0);
		Assert.assertEquals("2,4", saida);
		
		Assert.assertEquals("2", saida1);
	}
	
	@Test
	public void testDFS() {
		assertEquals(representacaoDFSSemPeso, library.DFS(grafoSemPeso, 1));
		assertEquals(representacaoDFSSemPeso2, library.DFS(grafoSemPeso2, 1));
	}
	
	@Test
	public void testBFS() {
		assertEquals(representacaoBFSSemPeso, library.BFS(grafoSemPeso, 1));
		assertEquals(representacaoBFSSemPeso2, library.BFS(grafoSemPeso2, 1));
	}
	
	

}
