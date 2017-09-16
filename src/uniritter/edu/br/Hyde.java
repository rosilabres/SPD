package uniritter.edu.br;

import java.util.ArrayList;

public class Hyde {

	ArrayList<Integer> hyde = new ArrayList<Integer>();

	public void criaCopiaHyde(Jekyl j) {

		for (int i = 0; i < j.jekyl.size(); i++) {
			hyde.add(j.jekyl.get(i).intValue());
		}
	}

	public void imprimeHyde() {
		for (int i = 0; i < hyde.size(); i++) {
			System.out.println("Hyde " + i + ": " + hyde.get(i).intValue());
		}

	}

}