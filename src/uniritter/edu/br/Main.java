package uniritter.edu.br;

import java.util.Random;


public class Main {
	

	public static void main(String[] args) throws InterruptedException {

		Arquivos a = new Arquivos();	
		a.criaArquivos();
		HTTP http = new HTTP();		

		Thread sincroniza = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				//Thread.sleep(300);
				
				try {
					http.sincroniza("jekyl");
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		});	
		
//		Thread sincroniza1 = new Thread(() -> {
//			for (int i = 0; i < 10; i++) {
//				//Thread.sleep(300);
//				
//				try {
//					http.sincroniza("hyde");
//				} catch (InterruptedException e) {
//					
//					e.printStackTrace();
//				}
//			}
//		});
		
		Thread usuario1 = new Thread(() -> {
			for (int i = 0; i < 4; i++) {
				//Thread.sleep(300);
				Random randomGenerator = new Random();
				int arq = randomGenerator.nextInt(a.jekyl.size());
				try {
					http.escreveServidor("Teste", "jekyl", arq);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		Thread usuario2 = new Thread(() -> {
			for (int i = 0; i < 4; i++) {
				//Thread.sleep(300);
				Random randomGenerator = new Random();
				int arq = randomGenerator.nextInt(a.jekyl.size());
				try {
					http.leServidor("jekyl", arq);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		});
//		Thread usuario2 = new Thread(() -> {
//			for (int i = 0; i < 2; i++) {
//				//Thread.sleep(300);
//				Random randomGenerator = new Random();
//				int arq = randomGenerator.nextInt(a.hyde.size());
//				try {
//					http.escreveServidor("Teste2", "hyde", arq);
//				} catch (InterruptedException e) {
//					
//					e.printStackTrace();
//				}
//			}
//		});
		
	
		sincroniza.start();
		//sincroniza1.start();
		usuario1.start();
		usuario2.start();
		
		//usuario2.start();
	
		
		sincroniza.join();
		//sincroniza1.start();
		usuario1.join();
		usuario2.join();
		

		
		
	 }

	}

