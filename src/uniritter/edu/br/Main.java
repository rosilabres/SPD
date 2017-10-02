package uniritter.edu.br;

import javax.swing.JOptionPane;


public class Main {

	static int eQuantArq;
	static int eAltClientes;
	static int equantThreadsHTTP;
	static int eSimulHTTP;
	public static int t;

	public static void main(String[] args) throws InterruptedException {

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

		vetorDeModif vmJekyll = new vetorDeModif();
		vetorDeModif vmHyde = new vetorDeModif();
		HTTP http = new HTTP();
		Arquivos.criaArquivos();
		http.criaHTTPS();

		System.out.println("------------------------ Lista de Arquivos ------------------------");

		System.out.println("Jekyll\t\tTamanho\t\tHyde\t\tTamanho");
		for (int i = 0; i < eQuantArq; i++) {
			System.out.println("Arquivo " + Arquivos.jekyllarqs[i].nomedoarq + "\t" + Arquivos.jekyllarqs[i].tamanho + "\t\t"
					+ "Arquivo " + Arquivos.hydearqs[i].nomedoarq + "\t" +  Arquivos.hydearqs[i].tamanho);
		}
		System.out.println("-------------------------------------------------------------------");

		Cliente jekyllcliente = new Cliente();
		
		Thread tjekyllcliente = new Thread(() -> {
			jekyllcliente.rodaCliente(eAltClientes, vmJekyll, Arquivos.jekyllarqs, "Jekyll");
		});
		
		tjekyllcliente.start();

		Cliente hydecliente = new Cliente();
		
		Thread thydecliente = new Thread(() -> {
			hydecliente.rodaCliente(eAltClientes, vmHyde, Arquivos.hydearqs, "Hyde");
		});
		
		thydecliente.start();

		Servidor jekyllserver = new Servidor();
		
		Thread tjekyllserver = new Thread(() -> {
			jekyllserver.rodaServidor(vmJekyll, Arquivos.jekyllarqs, Arquivos.hydearqs, "Jekyl", "Hyde");
		});
		
		tjekyllserver.start();

		Servidor hydeserver = new Servidor();
		
		Thread thydeserver = new Thread(() -> {
			hydeserver.rodaServidor(vmHyde, Arquivos.hydearqs, Arquivos.jekyllarqs, "Hyde", "Jekyll");
		});
		
		thydeserver.start();
		
		for (int i = 0; i < equantThreadsHTTP; i++) {								
				http.lista_HTTP[i].start();
				Thread.currentThread().setName("HTTP - "+Integer.toString(i));
		}
		
		for (int i = 0; i < equantThreadsHTTP; i++) {
			
			http.lista_HTTP[i].join();
	
	}
				
		
		tjekyllcliente.join();
		thydecliente.join();
		tjekyllserver.join();
		thydeserver.join();
		
		
		System.out.println("----------------- Lista de Arquivos Pós Alterações -----------------");

		System.out.println("Jekyll\t\tTamanho\t\tHyde\t\tTamanho");
		for (int i = 0; i < eQuantArq; i++) {
			System.out.println("Arquivo " + Arquivos.jekyllarqs[i].nomedoarq + "\t" + Arquivos.jekyllarqs[i].tamanho + "\t\t"
					+ "Arquivo " + Arquivos.hydearqs[i].nomedoarq + "\t" +  Arquivos.hydearqs[i].tamanho);
		}
		System.out.println("-------------------------------------------------------------------");
		
		
		System.out.println("------------------------ Tempo das Threads Bloqueadas ------------------------");
		
		System.out.println("Thread Jekyll Cliente ficou parada " + jekyllcliente.getThreadBloqueada() );
		System.out.println("Thread Hyde Cliente ficou parada " + hydecliente.getThreadBloqueada() );
		System.out.println("Thread Jekyll Servidor ficou parada " + jekyllserver.getThreadBloqueada());
		System.out.println("Thread Hyde Servidor ficou parada " + hydeserver.getThreadBloqueada() );
		
		for (int i = 0; i < equantThreadsHTTP; i++) {			
				System.out.println(http.lista_HTTP[i].nomeThread + " ficou " + http.lista_HTTP[i].getThreadBloqueada());
					}
		
	}
	
	
}
