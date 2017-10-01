package uniritter.edu.br;

public class Servidor {

	void rodaServidor(vetorDeModif vm, String name, Arquivos[] origem, Arquivos[] destino) {
		for (int i = 0; i < Main.eAltClientes; i++) {

			try {
				// Thread.sleep(1000*(i%10));
				int alterado = vm.get();
				sincroniza(alterado, name, origem, destino);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	void sincroniza(int arq, String name, Arquivos[] origem, Arquivos[] destino) throws InterruptedException {

			origem[arq].copias.readLock().lock();
			destino[arq].copias.writeLock().lock();

			System.out.println("Sincronizando no " + name + " Arquivo: " + arq);

			System.out.println("Sincronizou no " + name + " Arquivo: " + arq);
			origem[arq].copias.readLock().unlock();
			destino[arq].copias.writeLock().unlock();
	}
}
