package uniritter.edu.br;

import java.util.ArrayList;
import java.util.Random;

public class Servidor extends Thread {

	String nome;
	static public ArrayList<Servidor> servidores = new ArrayList<>();
	int nmodifjekyl = 1;
	int nmodifhyde = 1;
	boolean trancou = false;

	public void criaListaServidores() {
		Servidor jl = new Servidor();
		Servidor hl = new Servidor();
		jl.nome = "jekyl";
		hl.nome = "hyde";
		servidores.add(jl);
		servidores.add(hl);

	}

	public void run() {
		for (int i = 0; i < Main.eAltClientes; i++) {

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
		}

		this.notifyAll();

	}

	synchronized void sincroniza(int servidor, int arq) throws InterruptedException {

		if (servidores.get(servidor).nome == "jekyl") {
			while (nmodifjekyl == 1) {
				this.wait();
			}

			if (Main.arqModificadojekyl == Main.arqModificadohyde) {
				HTTP.mutex.lock();
				trancou = true;
			}

			System.out.println("Sincronizado no Hyde... " + Main.arqModificadojekyl);

			Main.arqModificadojekyl = 1000;

			if (trancou == true) {
				HTTP.mutex.unlock();
			}

			nmodifjekyl = 1;

		} else {
			while (nmodifhyde == 1) {
				this.wait();
			}

			if (Main.arqModificadojekyl == Main.arqModificadohyde) {
				HTTP.mutex.lock();
				trancou = true;
			}

			System.out.println("Sincronizado no Jekyl... " + Main.arqModificadohyde);

			Main.arqModificadohyde = 2000;

			if (trancou == true) {
				HTTP.mutex.unlock();
			}

			nmodifjekyl = 1;
		}

		this.notifyAll();

	}
}
