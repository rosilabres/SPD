package uniritter.edu.br;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		Jekyl j = new Jekyl();
		Hyde h = new Hyde();

		j.criaJekyl();
		h.criaCopiaHyde(j);
		System.out.println("Quantidade de arquivos no servidor: " + j.jekyl.size());
		Thread.sleep(3000);

		j.imprimeJekyl();
		h.imprimeHyde();

	}

}
