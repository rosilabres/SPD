package uniritter.edu.br;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Random;

public class Arquivos {

	int nomedoarq;
	int tamanho;
	String conteudo;
	String nomediretorio;
	ReentrantReadWriteLock copias;
		

	public static  Arquivos[] jekyllarqs  = new Arquivos[Main.eQuantArq];
	public static  Arquivos[] hydearqs = new Arquivos[Main.eQuantArq];
	

	public static void criaArquivos() {
		
			
		for (int i = 0; i < Main.eQuantArq; i++) {
			Random randomGenerator = new Random();
			int tamanhoarq = 50 + randomGenerator.nextInt(501);
			Arquivos c = new Arquivos();
			c.copias = new ReentrantReadWriteLock();
			c.nomedoarq = i;
			c.setTamanho(tamanhoarq);
			c.setConteudo("Vazio");
			jekyllarqs[i] = c;
			hydearqs[i] = c;
		}
		
	}

	public int getNome() {
		return nomedoarq;
	}

	public void setNome(int nomedoarq) {
		this.nomedoarq = nomedoarq;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
