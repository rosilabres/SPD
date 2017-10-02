package uniritter.edu.br;

import java.util.Random;

public class HTTP extends Thread{

	String nomeThread;
	int nr = 0;
	public HTTP[] lista_HTTP = new HTTP[Main.equantThreadsHTTP];
	private long threadBloqueada = 0;

	void criaHTTPS() {

		for (int i = 0; i < Main.equantThreadsHTTP; i++) {
			HTTP hs = new HTTP();
			hs.nomeThread = "HTTP - "+Integer.toString(i);
			lista_HTTP[i] = hs;
		}
	}
	
	public void run() {
		
		for (int i = 0; i < Main.eSimulHTTP; i++) {
			Random randomGenerator1 = new Random();
			Random randomGenerator2 = new Random();
			int sorteaDir = randomGenerator1.nextInt(2);
			int sorteaArq = randomGenerator2.nextInt(Main.eQuantArq);

			try {
				if (sorteaDir == 0)
					this.leitor(Arquivos.jekyllarqs, sorteaArq, "Jekyll");
				else
					this.leitor(Arquivos.hydearqs, sorteaArq, "Hyde");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void leitor(Arquivos[] arquivos, int sorteaArq, String name) throws InterruptedException {

		long tempoParada = arquivos[sorteaArq].tamanho; 
		arquivos[sorteaArq].copias.readLock().lock();
		System.out.println("Thread " + this.nomeThread + " Lendo no diretório " + name + " Arquivo: " + sorteaArq);
		Thread.sleep(arquivos[sorteaArq].tamanho);
		this.threadBloqueada += tempoParada;
		System.out.println("UNLOCKED read  - Thread "+ this.nomeThread + " Leu do diretório " + name + " Arquivo: " + sorteaArq);
		arquivos[sorteaArq].copias.readLock().unlock();
	}

	public long getThreadBloqueada() {
		return threadBloqueada;
	}

	public void setThreadBloqueada(long threadBloqueada) {
		this.threadBloqueada = threadBloqueada;
	}

}
