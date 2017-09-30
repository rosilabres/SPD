package uniritter.edu.br;

import java.util.Random;

public class Servidor {

	Random randomGenerator = new Random();

	void rodaServidor(vetorDeModif vm, String name, Arquivos[] arquivos) {
		for (int i = 0; i < Main.eAltClientes; i++) {

<<<<<<< HEAD
			int sorteaArq = randomGenerator.nextInt(Main.eQuantArq);
			try {
				// Thread.sleep(1000*(i%10));
				vm.get();
				sincroniza(sorteaArq, arquivos, name);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
=======
			Random randomGenerator1 = new Random();
			Random randomGenerator2 = new Random();
			int sorteaDir = randomGenerator1.nextInt(2);
			int sorteaArq = randomGenerator2.nextInt(Main.eQuantArq);
				
				try {
					Main.copias[sorteaArq].acquire();
					this.escritor(sorteaDir, sorteaArq);
					this.sincroniza(sorteaDir, sorteaArq);
					Main.copias[sorteaArq].release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}					
				
				
	}
	}

	synchronized void escritor(int servidor, int arq) throws InterruptedException {

		Main.q.acquire();

		Main.recurso.acquire();

		System.out.println("Escrevendo no " + servidores.get(servidor).nome + arq);
		Main.recurso.release();
		System.out.println("Saiu da escrita no " + servidores.get(servidor).nome + arq);

		Main.q.release();

		if (servidores.get(servidor).nome == "jekyl") {
			nmodifjekyl = 0;
			Main.arqModificadojekyl = arq;
		} else {
			nmodifhyde = 0;
			Main.arqModificadohyde = arq;
>>>>>>> f1fc5a7c532d80fd7ecc7d1c145dbdd8cc62acdb
		}

	}

	void sincroniza(int arq, Arquivos[] arquivos, String name) throws
	 InterruptedException {
		
	Main.jekyllarq.arqs[arq].copias.readLock();	
	 System.out.println("Escrevendo no ");
	
	 System.out.println("Saiu da escrita no");

	
	 }

}
