package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParseFile {
	public String[] risorse;
	public String[] nazioni;
	public ArrayList<Region> regioni;
	public ArrayList<Project> progetti;

	public void parseFile(String path) {

		try {
			regioni = new ArrayList<>();
			progetti = new ArrayList<>();
			File f = new File(path);
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(f);

			int v = scanner.nextInt(); // Numero provider
			int s = scanner.nextInt(); // Numero servizi
			int c = scanner.nextInt(); // Numero nazioni
			int p = scanner.nextInt(); // Numero progetti

			scanner.nextLine();

			String servizi = scanner.nextLine();

			risorse = servizi.split(" ");

			String n = scanner.nextLine();

			nazioni = n.split(" ");

			/////////////////////////////////////////////////
			for (int i = 0; i < v; i++) {
				String s1 = scanner.nextLine();

				String provider = s1.split(" ")[0];
				int numRegioni = Integer.parseInt(s1.split(" ")[1]);

				for (int j = 0; j < numRegioni; j++) {
					String regione = scanner.nextLine();

					int pacchettiDisponibili = scanner.nextInt();
					float prezzo = scanner.nextFloat();

					HashMap<String, Integer> servizi_unita = new HashMap<String, Integer>();
					for (int k = 0; k < s; k++) {
						int nser = scanner.nextInt();
						servizi_unita.put(risorse[k], nser);

					}

					HashMap<String, Integer> latenze = new HashMap<>();
					for (int k = 0; k < c; k++) {
						int late = scanner.nextInt();
						latenze.put(nazioni[k], late);

					}

					regioni.add(new Region(provider, regione, servizi_unita, latenze, prezzo, pacchettiDisponibili));

					scanner.nextLine();
				}

			}

			HashMap<String, Integer> serviziR = new HashMap<>();
			int penality;
			String nn;
			for (int i = 0; i < p; i++) {

				String line = scanner.nextLine();

				penality = Integer.parseInt(line.split(" ")[0]);

				nn = line.split(" ")[1];

				for (int j = 0; j < s; j++) {

					serviziR.put(risorse[j], Integer.parseInt(line.split(" ")[j + 2]));

				}

				progetti.add(new Project(nn, penality, serviziR));

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
