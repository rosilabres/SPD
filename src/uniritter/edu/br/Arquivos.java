package uniritter.edu.br;

import java.util.ArrayList;
import java.util.Random;

public class Arquivos {
	
	int nome;
	int tamanho;
	String conteudo;
	
	static public ArrayList<Arquivos> jekyl = new ArrayList<>();
	static public ArrayList<Arquivos> hyde = new ArrayList<>();
	
	void criaArquivos() {		
				
		Random randomGenerator = new Random();		

		for (int i = 0; i < Main.eQuantArq; i++) {
			int randomInt = randomGenerator.nextInt(1000);
			Arquivos c = new Arquivos();
			c.setNome(i + 1);
			c.setTamanho(randomInt);
			c.setConteudo("Vazio");
			jekyl.add(c);
			hyde.add(c);
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
		
	public void imprimVetorJekyl() {

		for (int i =0; i < jekyl.size(); i++) {
			System.out.println("Arquivo: " + jekyl.get(i).getNome() + " - " + jekyl.get(i).getConteudo());
		}
	}
	
	public void imprimVetorHyde() {

		for (int i =0; i < hyde.size(); i++) {
			System.out.println("Arquivo: " + hyde.get(i).getNome() + " - " + hyde.get(i).getConteudo());
		}
	}
}
