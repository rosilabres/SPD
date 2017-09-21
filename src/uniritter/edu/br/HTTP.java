package uniritter.edu.br;

import java.util.concurrent.locks.ReentrantLock;

public class HTTP {
		
	Jekyl j = new Jekyl();
	Hyde h = new Hyde();
	static public ReentrantLock mutex = new ReentrantLock();
	
	public void sincroniza(String servidor) throws InterruptedException {
		if (servidor == "jekyl") {
			j.sincroniza();
		}else {
			h.sincroniza();
		}
		
	}
	
	public void escreveServidor(String conteudo, String servidor, int arq) throws InterruptedException {
		if (servidor == "jekyl") {
			j.escritor(conteudo, arq, servidor);
		}else
		{
			h.escritor(conteudo, arq, servidor);	
	}
	}
	
	public void leServidor(String servidor, int arq) throws InterruptedException {
		if (servidor == "jekyl") {
			j.leitor(servidor, arq);
		}else
		{
			h.leitor(servidor, arq);	
	}
		
	}	
	
}

