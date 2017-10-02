package uniritter.edu.br;

public class Servidor {

	private long threadBloqueada = 0;
	private long tempoParada = 0;

	void rodaServidor(vetorDeModif vm, Arquivos[] origem, Arquivos[] destino, String nomeorigem, String nomedestino) {
		for (int i = 0; i < Main.eAltClientes; i++) {

			try {

				int alterado = vm.get();
				sincroniza(alterado, origem, destino, nomedestino);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	void sincroniza(int arq, Arquivos[] origem, Arquivos[] destino, String nomedestino) throws InterruptedException {

		
		if (origem[arq].nomediretorio == "Jekyll") {
			origem[arq].copias.readLock().lock();
			destino[arq].copias.writeLock().lock();

			tempoParada = origem[arq].tamanho;
			this.threadBloqueada += tempoParada;
			Thread.sleep(origem[arq].tamanho);
			
			System.out.println("Sincronizando no " + nomedestino + " Arquivo: " + arq);

			origem[arq].copias.readLock().unlock();
			destino[arq].copias.writeLock().unlock();
		} else {
			destino[arq].copias.writeLock().lock();
			origem[arq].copias.readLock().lock();

			setThreadBloqueada(origem[arq].tamanho);
			Thread.sleep(origem[arq].tamanho);

			System.out.println("Sincronizando no " + nomedestino + " Arquivo: " + arq);

			destino[arq].copias.writeLock().unlock();
			origem[arq].copias.readLock().unlock();

		}

	}

	public long getThreadBloqueada() {
		return threadBloqueada;
	}

	public void setThreadBloqueada(long threadBloqueada) {
		this.threadBloqueada = threadBloqueada;
	}

	public long getTempoParada() {
		return tempoParada;
	}

	public void setTempoParada(long tempoParada) {
		this.tempoParada = tempoParada;
	}
}
