package uniritter.edu.br;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Arquivos {

	int nome;
	int tamanho;
	String conteudo;
	ReentrantReadWriteLock copias;
	

	public Arquivos[] arqs;
	

	void criaArquivos() {
		arqs = new Arquivos[Main.eQuantArq];

		for (int i = 0; i < Main.eQuantArq; i++) {
			Arquivos c = new Arquivos();
			c.copias = new ReentrantReadWriteLock();
			c.setNome(i + 1);
			c.setTamanho(1);
			c.setConteudo("Vazio");
			arqs[i] = c;
		}

	}

	public int getNome() {
		return nome;
	}

	public void setNome(int nome) {
		this.nome = nome;
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
