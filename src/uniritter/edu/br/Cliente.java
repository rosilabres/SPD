package uniritter.edu.br;

import java.util.Random;

public class Cliente {
	Random randomGenerator = new Random();
	
	void rodaCliente(int eAltClientes, vetorDeModif vm, String name, Arquivos[] arquivos)
	{
		for (int i = 0; i < Main.eAltClientes; i++) {
			
			int sorteaArq = randomGenerator.nextInt(Main.eQuantArq);
	   		
			 arquivos[sorteaArq].setTamanho(arquivos[sorteaArq].getTamanho() + 100);
			 
		    	 try {
					//Thread.sleep(1000*(i%10));
					vm.put(sorteaArq);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
	}
}
	
	
