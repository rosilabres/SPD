package uniritter.edu.br;

import java.util.concurrent.Semaphore;

public class Hyde {
	
	static private Semaphore q = new Semaphore(1);
	static private Semaphore recurso = new Semaphore(1);
	static private Semaphore mr = new Semaphore(1);
	static private int nmodif = 1;
	
	Arquivos a = new Arquivos();
	Servidor s = new Servidor();

	int nr = 0;
	int c = 0;
	int arqModificado;
	int np = 0, nc = 0;
	

	static public boolean nmodificou() {

		return nmodif == 1;
	}

	void leitor(String servidor, int arq) throws InterruptedException {

		while (c < 2) {
			q.acquire();
			mr.acquire();
			nr++;
			if (nr == 1) {
				recurso.acquire();
			}
			mr.release();
			q.release();

			System.out.println("Lendo no Hyde... " + arq);

			mr.acquire();
			nr--;
			if (nr == 0) {
				recurso.release();
			}
			System.out.println("UNLOCKED read no Hyde  " + arq);
			mr.release();
			c++;

		}

		c = 0;
	}

	synchronized void escritor(String conteudo, int arq, String servidor) throws InterruptedException {

				
		while (c < 2) {

			q.acquire();

			recurso.acquire();

			System.out.println("Escrevendo no Hyde... " + arq);

			recurso.release();

			a.hyde.get(arq).setConteudo(conteudo);
			q.release();

			c++;
			nmodif = 0;
			arqModificado = arq;
			this.notifyAll();
		}

		c = 0;
	}

	synchronized void sincroniza() throws InterruptedException {

		while (nmodificou()) {
			this.wait();
		}		
		
		System.out.println("Sincronizando no Jekyl... " + arqModificado);

		HTTP.mutex.lock();
		a.jekyl.get(arqModificado).setConteudo(a.hyde.get(arqModificado).getConteudo());
		a.jekyl.get(arqModificado).setTamanho(a.hyde.get(arqModificado).getTamanho());
		HTTP.mutex.unlock();
			
		nmodif = 1;

	}

}
