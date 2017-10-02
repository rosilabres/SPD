package uniritter.edu.br;

import java.util.Random;


public class Cliente {
	Random randomGenerator1 = new Random();
	Random randomGenerator2 = new Random();
	private long threadBloqueada = 0;
	private long tempoParada = 0;
	

	void rodaCliente(int eAltClientes, vetorDeModif vm, Arquivos[] origem, String nomeorigem) {
		for (int i = 0; i < Main.eAltClientes; i++) {

			int sorteaArq = randomGenerator1.nextInt(Main.eQuantArq);
			int tamanhoDaAtualizacao = 500 + randomGenerator2.nextInt(2000);
			try {
				
				
				origem[sorteaArq].copias.writeLock().lock();
				System.out.println(
						"Modificando no servidor " + nomeorigem + " o arquivo " + sorteaArq);
				tempoParada = tamanhoDaAtualizacao;
				this.threadBloqueada += tempoParada;
				origem[sorteaArq].setTamanho(origem[sorteaArq].getTamanho() + tamanhoDaAtualizacao);
				Thread.sleep(origem[sorteaArq].tamanho);
				origem[sorteaArq].copias.writeLock().unlock();

				vm.put(sorteaArq);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public long getThreadBloqueada() {
		return threadBloqueada;
	}

	public void setThreadBloqueada(long threadBloqueada) {
		this.threadBloqueada = threadBloqueada;
	}
}
