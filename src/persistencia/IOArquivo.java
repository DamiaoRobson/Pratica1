package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Damiao Robson Domiciano
 *
 *         Busca e salvo arquivos no diretorio do computador
 */
public class IOArquivo {

	/**
	 * Busca arquivo txt no diretorio infirmado no path
	 * 
	 * @param pathc
	 *            Caminho e nome do arquivo texto do grafo
	 * @return Uma lista com o primeiro valor contendo a quantidade de arestas e os
	 *         demais o inicio e fim e peso das vertices
	 * @throws IOException
	 */
	public static List<String[]> lerArquivo(String path) throws IOException {
		List<String[]> listAux = new ArrayList<>();

		FileReader arq = new FileReader(path);
		@SuppressWarnings("resource")
		BufferedReader lerArq = new BufferedReader(arq);

		String linha = lerArq.readLine();
		while (linha != null) {
			listAux.add(linha.split(" "));

			linha = lerArq.readLine();
		}

		arq.close();

		return listAux;
	}

}
