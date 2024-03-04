package repaso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RepasoProcesos {

	public static void main(String[] args) {
		/*do {
		while();*/
		String[] p = { "mysqladmin", " -uroot", "-proot", "--port=3307", "shutdown" };
		ProcessBuilder pb = new ProcessBuilder(p);
		BufferedReader br = null;
		try {
			Process pr = pb.start();
			int cod = pr.waitFor();
			System.out.println("Codigo de salida: " + cod);
			if (cod == 0) {
				System.out.println("Se ha apagado correctamente");
			} else {
				InputStream is = pr.getErrorStream();
				br = new BufferedReader(new InputStreamReader(is));
				String linea;
				while ((linea = br.readLine()) != null) {
					System.out.println(linea);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
