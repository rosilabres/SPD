package uniritter.edu.br;

public class vetorDeModif {	
	
	int[] buffer = new int[Main.eQuantArq];
	int np = 0, nc = 0;
	int size = 0;

	synchronized
	boolean full() {
		
		return size == Main.eQuantArq;
	}

	synchronized
	boolean empty() {
		
		return size == 0;
	}
	
	synchronized void put(int e) throws InterruptedException {

		while (full()){
			System.out.println("Modificado");
			this.wait();
			}
		buffer[np++ % Main.eQuantArq] = e;

		size++;

		this.notifyAll();
	}

	synchronized int get() throws InterruptedException {
		
		int e;
	
		while (empty()){
			System.out.println("Parado");
			this.wait();
		}
		e = buffer[nc++ % Main.eQuantArq];

		size--;

		this.notifyAll();
		return e;

	}
}
