package uniritter.edu.br;

public class vetorDeModif {

	int limit = Main.eAltClientes;
	int[] buffer = new int[limit];
	int np = 0, nc = 0;
	int size = 0;

	synchronized boolean full() {

		return size == limit;
	}

	synchronized boolean empty() {

		return size == 0;
	}

	synchronized void put(int e) throws InterruptedException {

		while (full()) {
			this.wait();
		}

		buffer[np++ % limit] = e;

		size++;
		this.notifyAll();
	}

	synchronized int get() throws InterruptedException {

		int e;

		while (empty()) {
			this.wait();
		}

		e = buffer[nc++ % limit];

		size--;

		this.notifyAll();
		return e;

	}

}
