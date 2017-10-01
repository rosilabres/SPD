package uniritter.edu.br;

import java.util.Random;

public class HTTP {

	int nome;
	int nr = 0;
	public HTTP[] lista_HTTP;

	void criaHTTPS() {

		lista_HTTP = new HTTP[Main.equantThreadsHTTP];

		for (int i = 0; i < Main.equantThreadsHTTP; i++) {
			HTTP hs = new HTTP();
			hs.nome = i + 1;
			lista_HTTP[i] = hs;
		}
	}

	public void rodaHTTP(Arquivos[] jekyll, Arquivos[] hyde) {

		for (int i = 0; i < Main.eSimulHTTP; i++) {
			Random randomGenerator1 = new Random();
			Random randomGenerator2 = new Random();
			int sorteaDir = randomGenerator1.nextInt(2);
			int sorteaArq = randomGenerator2.nextInt(Main.eQuantArq);

			try {
				if (sorteaDir == 0)
					this.leitor(jekyll, sorteaArq, "Jekyll");
				else
					this.leitor(hyde, sorteaArq, "Hyde");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void leitor(Arquivos[] arquivos, int sorteaArq, String name) throws InterruptedException {

		arquivos[sorteaArq].copias.readLock().lock();
		System.out.println("Lendo no diretório " + name + " Arquivo: " + sorteaArq);

		System.out.println("UNLOCKED read no diretório " + name + " Arquivo: " + sorteaArq);
		arquivos[sorteaArq].copias.readLock().unlock();
	}

}
