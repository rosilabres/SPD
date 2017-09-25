package uniritter.edu.br;

import java.util.concurrent.Semaphore;
import javax.swing.JOptionPane;

public class Main {

	static int arqModificadojekyl = 1000;
	static int arqModificadohyde = 2000;
	static int eQuantArq;
	static int eAltClientes;
	static int equantThreadsHTTP;
	static int eSimulHTTP;
	static public Semaphore q = new Semaphore(1);
	static public Semaphore recurso = new Semaphore(1);
	static public Semaphore mr = new Semaphore(1);

	public static void main(String[] args) throws InterruptedException {

		Arquivos a = new Arquivos();
		HTTP http = new HTTP();
		Servidor s = new Servidor();
		
			

		String quantArquivos = "Quantidade de Arquivos?";
		eQuantArq = Integer.valueOf(JOptionPane.showInputDialog(quantArquivos));
		System.out.println("Quantidade de Arquivos: " + eQuantArq);

		String altClientes = "Alterações de cada cliente?";
		eAltClientes = Integer.valueOf(JOptionPane.showInputDialog(altClientes));
		System.out.println("Alterações de cada cliente: " + eAltClientes);

		String quantThreadsHTTP = "Quantidade de Threads HTTP?";
		equantThreadsHTTP = Integer.valueOf(JOptionPane.showInputDialog(quantThreadsHTTP));
		System.out.println("Quantidade de Threads HTTP: " + equantThreadsHTTP);

		String simulHTTP = "Quantidade de simulações de Threads HTTP?";
		eSimulHTTP = Integer.valueOf(JOptionPane.showInputDialog(simulHTTP));
		System.out.println("Quantidade de Arquivos: " + eSimulHTTP);
		

		
		a.criaArquivos();
		http.criaHTTPS();
		s.criaListaServidores();

		for (int i = 0; i < equantThreadsHTTP; i++) {
			HTTP.lista_HTTP.get(i).start();
			
		}		
	

		Servidor.servidores.get(0).start();
		Servidor.servidores.get(1).start();

		for (int i = 0; i < equantThreadsHTTP; i++) {
			try {
				HTTP.lista_HTTP.get(i).join();
			} catch (InterruptedException ex) {
			}
		}

		Servidor.servidores.get(0).join();
		Servidor.servidores.get(1).join();

	}

}
