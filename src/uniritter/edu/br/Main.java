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

		Arquivos jekyllarq = new Arquivos();
		Arquivos hydearq = new Arquivos();
		vetorDeModif vmJekyll = new vetorDeModif();
		vetorDeModif vmHyde = new vetorDeModif();
		HTTP http = new HTTP();
		jekyllarq.criaArquivos("Jekyll");
		hydearq.criaArquivos("Hyde");
		http.criaHTTPS();

		System.out.println("------------------------ Lista de Arquivos ------------------------");

		System.out.println("Jekyll\t\tTamanho\t\tHyde\t\tTamanho");
		for (int i = 0; i < eQuantArq; i++) {
			System.out.println("Arquivo " + jekyllarq.arqs[i].nomedoarq + "\t" + jekyllarq.arqs[i].tamanho + "\t\t"
					+ "Arquivo " + hydearq.arqs[i].nomedoarq + "\t" + hydearq.arqs[i].tamanho);
		}
		System.out.println("-------------------------------------------------------------------");

		Cliente jekyllcliente = new Cliente();
		
		Thread tjekyllcliente = new Thread(() -> {
			jekyllcliente.rodaCliente(eAltClientes, vmJekyll, jekyllarq.arqs);
		});
		
		tjekyllcliente.start();

		Cliente hydecliente = new Cliente();
		
		Thread thydecliente = new Thread(() -> {
			hydecliente.rodaCliente(eAltClientes, vmHyde, hydearq.arqs);
		});
		
		thydecliente.start();

		Servidor jekyllserver = new Servidor();
		
		Thread tjekyllserver = new Thread(() -> {
			jekyllserver.rodaServidor(vmJekyll, jekyllarq.arqs, hydearq.arqs);
		});
		
		tjekyllserver.start();

		Servidor hydeserver = new Servidor();
		
		Thread thydeserver = new Thread(() -> {
			hydeserver.rodaServidor(vmHyde, hydearq.arqs, jekyllarq.arqs);
		});
		
		thydeserver.start();
		
		for (int i = 0; i < equantThreadsHTTP; i++) {
			String nt = Integer.toString(i);
			
			new Thread(() -> {	
				Thread.currentThread().setName(nt);
				http.rodaHTTP(jekyllarq.arqs,hydearq.arqs );
			}).start();
		}
		
		
		
		
		
		tjekyllcliente.join();
		thydecliente.join();
		tjekyllserver.join();
		thydeserver.join();
		
		
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
