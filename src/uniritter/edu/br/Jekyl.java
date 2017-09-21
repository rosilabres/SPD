package uniritter.edu.br;

import java.util.concurrent.Semaphore;

public class Jekyl {

	static private Semaphore q = new Semaphore(1);
	static private Semaphore recurso = new Semaphore(1);
	static private Semaphore mr = new Semaphore(1);
	static private int nmodifjekyl = 1;

	Arquivos a = new Arquivos();
	Servidor s = new Servidor();

	int nr = 0;
	int c = 0;
	int arqModificado;
	int np = 0, nc = 0;

	

	static public boolean nmodificou() {

		return nmodifjekyl == 1;
	}


	void leitor(String servidor, int arq) throws InterruptedException {

	
			q.acquire();
			mr.acquire();
			nr++;
			if (nr == 1) {
				recurso.acquire();
			}
			mr.release();
			q.release();

			System.out.println("Lendo no Jekyl... " + arq);

			mr.acquire();
			nr--;
			if (nr == 0) {
				recurso.release();
			}
			System.out.println("UNLOCKED read no Jekyl  " + arq);
			mr.release();
	
	}

	synchronized void escritor(String conteudo, int arq, String servidor) throws InterruptedException {

	

			q.acquire();

			recurso.acquire();

			System.out.println("Escrevendo no Jekyl... " + arq);

			recurso.release();

			a.jekyl.get(arq).setConteudo(conteudo);
			q.release();

			c++;
			nmodifjekyl = 0;
			arqModificado = arq;
			this.notifyAll();
		

	
	}

	synchronized void sincroniza() throws InterruptedException {

		while (nmodificou()) {
			this.wait();
		}				

		System.out.println("Sincronizando no Hyde... " + arqModificado);

		HTTP.mutex.lock();
		a.hyde.get(arqModificado).setConteudo(a.jekyl.get(arqModificado).getConteudo());
		a.hyde.get(arqModificado).setTamanho(a.jekyl.get(arqModificado).getTamanho());
		HTTP.mutex.unlock();
	
		nmodifjekyl = 1;

	}

}
