package cnf;

import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class GenerateurCNF {
	
	public int nbVariables = 1;
	public int nbClauses = 1;
	public double ratio = 1;
	public boolean[] k;
	public Random random = new Random();
	public Scanner scanner = new Scanner(System.in);
	public StringBuffer buffer = new StringBuffer();
	public FileWriter writer;
	
	public static void main(String[] args) {
		new GenerateurCNF().start();
	}
	
	public void utilisateur() {
		System.out.println("Entrez le nombre de variables voulu:");
		this.nbVariables = Integer.parseInt(scanner.nextLine());
		System.out.println("Entrez le nombre de clauses voulu:");
		this.nbClauses = Integer.parseInt(scanner.nextLine());
		System.out.println("Entrez le pourcentage de nulles voulu:");
		this.ratio = Double.parseDouble(scanner.nextLine());
	}
	
	public void start() {
		this.utilisateur();
		writer = Files.loadFileWriter(nbVariables+"CNF");
		buffer.append("p cnf "+nbClauses+" "+nbVariables+"\n");
		
		for(int j = 0; j < nbClauses; j++) {
			k = new boolean[this.nbVariables*2];
			for(int i = 0; i < this.nbVariables*2; i+=2) {
				if(random.nextDouble()*100 < ratio) {
					if(random.nextBoolean()) {
						k[i] = true;
					}
					k[i+1] = true;
				}
			}
			writeIn();
		}
		
		try {
			writer.write(buffer.toString());
			writer.flush();
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public void writeIn() {
		for(int l = 0; l < this.nbVariables*2; l+=2) {
			if(k[l+1]) {
				if(k[l]){
					buffer.append((l/2+1) + " ");
				}else {
					buffer.append(-(l/2+1) + " ");
				}
			}
		}
		buffer.append("0 \n");
	}

}
