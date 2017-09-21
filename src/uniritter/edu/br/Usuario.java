package uniritter.edu.br;

import java.util.concurrent.Semaphore;

public class Usuario {

	static Semaphore q = new Semaphore(1);
	static Semaphore recurso = new Semaphore(1);
	static Semaphore mr = new Semaphore(1);

	Arquivos a = new Arquivos();
	Servidor s = new Servidor();

	int nr = 0;
	int c = 0;

	int np = 0, nc = 0;
	int size = 0;

	void leitor(int arq, String servidor) throws InterruptedException {

		while (c < 2) {
			q.acquire();
			mr.acquire();
			nr++;
			if (nr == 1) {
				recurso.acquire();
			}
			mr.release();
			q.release();

			System.out.println("Lendo... " + arq);

			mr.acquire();
			nr--;
			if (nr == 0) {
				recurso.release();
			}
			System.out.println("UNLOCKED read  " + arq);
			mr.release();
			c++;

		}

		c = 0;
	}

	synchronized void escritor(String conteudo, int arq, String servidor) throws InterruptedException {

		
		
			while (c < 2) {

				q.acquire();

				recurso.acquire();

				System.out.println("Escrevendo... " + arq);

				recurso.release();

				a.jekyl.get(arq).setConteudo(conteudo);
				q.release();

				c++;
				
				Servidor.nmodif = 0;
				Servidor.class.notify();
		}
			

		c = 0;
	}

}
