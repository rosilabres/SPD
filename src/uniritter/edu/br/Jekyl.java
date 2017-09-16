package uniritter.edu.br;

import java.util.ArrayList;
import java.util.Random;

public class Jekyl {

	ArrayList<Integer> jekyl = new ArrayList<Integer>();

	public void criaJekyl() {

		Random tamanho = new Random();
		Random randomGenerator = new Random();
		int t = tamanho.nextInt(20);

		for (int i = 0; i < t; i++) {
			int randomInt = randomGenerator.nextInt(1000);
			jekyl.add(randomInt);

		}
	}

	public void imprimeJekyl() {
		for (int i = 0; i < jekyl.size(); i++) {
			System.out.println("Jekyl " + i + ": " + jekyl.get(i).intValue());
		}

	}
}
