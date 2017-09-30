package uniritter.edu.br;

import java.util.Random;

public class Servidor {

	Random randomGenerator = new Random();

	void rodaServidor(vetorDeModif vm, String name, Arquivos[] arquivos) {
		for (int i = 0; i < Main.eAltClientes; i++) {

			int sorteaArq = randomGenerator.nextInt(Main.eQuantArq);
			try {
				// Thread.sleep(1000*(i%10));
				vm.get();
				sincroniza(sorteaArq, arquivos, name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	void sincroniza(int arq, Arquivos[] arquivos, String name) throws
	 InterruptedException {
		
	Main.jekyllarq.arqs[arq].copias.readLock();	
	 System.out.println("Escrevendo no ");
	
	 System.out.println("Saiu da escrita no");

	
	 }

}
