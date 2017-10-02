package uniritter.edu.br;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.Random;

public class Arquivos {

	int nomedoarq;
	int tamanho;
	String conteudo;
	String nomediretorio;
	ReentrantReadWriteLock copias;
	Random randomGenerator = new Random();
	

	public Arquivos[] arqs;
	

	void criaArquivos(String nome) {
		arqs = new Arquivos[Main.eQuantArq];		

		for (int i = 0; i < Main.eQuantArq; i++) {
			Arquivos c = new Arquivos();
			c.nomediretorio = nome;
			c.copias = new ReentrantReadWriteLock();
			c.nomedoarq = i + 1;
			c.setTamanho(1);
			c.setConteudo("Vazio");
			arqs[i] = c;
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
