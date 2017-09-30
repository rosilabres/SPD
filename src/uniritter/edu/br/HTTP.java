package uniritter.edu.br;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class HTTP extends Thread {

	int nome;
	int nr = 0;
	static public ReentrantLock mutex = new ReentrantLock();
	static public ArrayList<HTTP> lista_HTTP = new ArrayList<>();
	

	void criaHTTPS() {
		for (int i = 0; i < Main.equantThreadsHTTP; i++) {
			HTTP hs = new HTTP();
			hs.nome = i + 1;
			lista_HTTP.add(hs);
		}
	}

	public void run() {

		for (int i = 0; i < Main.eSimulHTTP; i++) {
			Random randomGenerator1 = new Random();
			Random randomGenerator2 = new Random();
			int sorteaDir = randomGenerator1.nextInt(2);
			int sorteaArq = randomGenerator2.nextInt(Main.eQuantArq);

			try {
				this.leitor(sorteaDir, sorteaArq);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void leitor(int sorteaDir, int sorteaArq) throws InterruptedException {

			
			System.out.println("Lendo no Jekyl... " + sorteaArq);
	
		
			System.out.println("UNLOCKED read no Jekyl  " + sorteaArq);
		}

}

	

