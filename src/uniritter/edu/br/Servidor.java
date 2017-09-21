package uniritter.edu.br;

public class Servidor{

	
	int arqModificado;
	static public int nmodif = 1;
	static public int sinc = 1;
	int q = 0;
	Arquivos a = new Arquivos();
	
	static public boolean nmodificou() {
		
		return nmodif == 1;
	}
	
static public boolean sinc() {
		
		return sinc == 1;
	}

	synchronized void sincroniza()   throws InterruptedException {
		
		 			while (nmodificou()) {
		 				this.wait();
		 			}
					
				a.hyde.get(arqModificado).setConteudo(a.jekyl.get(arqModificado).getConteudo());
				a.hyde.get(arqModificado).setTamanho(a.jekyl.get(arqModificado).getTamanho());
				
				System.out.println(a.jekyl.get(arqModificado).getConteudo());
				System.out.println(a.hyde.get(arqModificado).getConteudo());
				
			}
	 
}
