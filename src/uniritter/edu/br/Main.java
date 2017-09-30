package uniritter.edu.br;

import javax.swing.JOptionPane;

public class Main {

	static int eQuantArq;
	static int eAltClientes;
	static int equantThreadsHTTP;
	static int eSimulHTTP;
	public static Arquivos jekyllarq = new Arquivos();
	public static Arquivos hydearq = new Arquivos();
	

	public static void main(String[] args) throws InterruptedException {

		vetorDeModif vmJekyll = new vetorDeModif();
		vetorDeModif vmHyde = new vetorDeModif();
		jekyllarq.criaArquivos();
		hydearq.criaArquivos();
		
		HTTP http = new HTTP();
				
		http.criaHTTPS();
		
		
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
		
		Cliente jekyll = new Cliente();
		new Thread(()-> {
			jekyll.rodaCliente(eAltClientes, vmJekyll, "Jekyll", jekyllarq.arqs);
		}).start();
		
		Cliente hyde = new Cliente();
		new Thread(()-> {
			hyde.rodaCliente(eAltClientes, vmHyde, "Hyde", hydearq.arqs);
		}).start();
		
		Servidor jekyllserver = new Servidor();
		new Thread(()-> {
			jekyllserver.rodaServidor(vmJekyll, "Jekyll", jekyllarq.arqs);
		}).start();
		
		Servidor hydeserver = new Servidor();
		new Thread(()-> {
			hydeserver.rodaServidor(vmHyde, "Hyde", hydearq.arqs);
		}).start();
		
			
		for (int i = 0; i < equantThreadsHTTP; i++) {
			HTTP.lista_HTTP.get(i).start();	
		}			

		for (int i = 0; i < equantThreadsHTTP; i++) {
			try {
				HTTP.lista_HTTP.get(i).join();
			} catch (InterruptedException ex) {
			}
		}

	}

}
