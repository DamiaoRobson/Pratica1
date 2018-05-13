/**
 * 
 */
package test.aceitacao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import buscas.BuscaNoGrafo;
import grafo.Graph;
import main.GraphLib;

/**
 * @author danie
 *
 */
public class BuscaNoGrafoTest {

	private GraphLib library;
	private Graph grafoSemPeso;
	private Graph grafoComPeso;
	private BuscaNoGrafo busca;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		library = new GraphLib();
		grafoSemPeso = library.readGraph("grafo1.txt");
		grafoComPeso = library.readGraph("grafo2.txt");
		busca = new BuscaNoGrafo();
	}

	/**
	 * Test method for {@link buscas.BuscaNoGrafo#BSF(grafo.Graph, int)}.
	 */
	@Test
	public void testBSF() {
		System.out.println(busca.BSF(grafoSemPeso, 1));
		fail("Not yet implemented");
	}

}
