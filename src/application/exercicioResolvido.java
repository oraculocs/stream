package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Produto;

public class exercicioResolvido {

	public static void main(String[] args) {
		
		/*
		 * Fazer um programa para ler um conjunto de produtos a partir de um
		 * arquivo em formato .csv (suponha que exista pelo menos um produto).
		 * Em seguida mostrar o preço médio dos produtos. Depois, mostrar os
		 * nomes, em ordem decrescente, dos produtos que possuem preço
		 * inferior ao preço médio
		 * 
		 * Input file:
		 * Tv,900.00
		 * Mouse,50.00
		 * Tablet,350.50
		 * HD Case,80.90
		 * Computer,850.00
		 * Monitor,290.00
		 * 
		 * Execution:
		 * Enter full file path: c:\temp\in.txt
		 * Average price: 420.23
		 * Tablet
		 * Mouse
		 * Monitor
		 * HD Case
		 */
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Entre com o caminho do arquivo: ");
		String path = sc.nextLine();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			
			List<Produto> list = new ArrayList<>();
			
			String line = br.readLine();
			while(line != null) {
				String fields[] = line.split(",");
				list.add(new Produto(fields[0], Double.parseDouble(fields[1])));
				line = br.readLine();
			}
			
			double media = list.stream()
						   .map(p -> p.getPreco())
						   .reduce(0.0, (x,y) -> x + y) / list.size();
			
			System.out.println("Média dos Preços: " + String.format("%.2f", media));
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> nomes = list.stream()
								 .filter(p -> p.getPreco() < media)
								 .map(p -> p.getNome())
								 .sorted(comp.reversed())
								 .collect(Collectors.toList());
			
			nomes.forEach(System.out::println);
			
		}catch(IOException e) {
			System.out.println("Error : " + e.getMessage());
		}
		
		
		
		
		
		sc.close();

	}

}
