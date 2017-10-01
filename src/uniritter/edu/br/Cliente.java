package uniritter.edu.br;

import java.util.Random;

public class Cliente {
	Random randomGenerator = new Random();
	
	void rodaCliente(int eAltClientes, vetorDeModif vm, String name, Arquivos[] arquivos)
	{
		for (int i = 0; i < Main.eAltClientes; i++) {
			
			int sorteaArq = randomGenerator.nextInt(Main.eQuantArq);
			 try {
	   		arquivos[sorteaArq].copias.writeLock().lock();
	   		System.out.println("Alterando arquivo " + sorteaArq + " no servidor " + name);
	   		Thread.sleep(1000*(i%10));
			arquivos[sorteaArq].setTamanho(arquivos[sorteaArq].getTamanho() + 100);
			arquivos[sorteaArq].copias.writeLock().unlock(); 
		    
					vm.put(sorteaArq);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
	}
}
	
	
