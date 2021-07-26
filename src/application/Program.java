package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		String strPath = "C:\\temp\\produtos.csv";
		List<Product> list = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(strPath))){
			String line = br.readLine();

			while (line != null) {
				String[] vect = line.split(",");
				String nome = vect[0];
				double price = Double.parseDouble(vect[1]);
				int quantity = Integer.parseInt(vect[2]);
				
				list.add(new Product(nome, price, quantity));
				line = br.readLine();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		File path = new File(strPath);
		new File(path.getParent() + "\\out").mkdir();
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(path.getParent() + "\\out\\Summary.csv"))){
			for(Product prod : list) {				
				bw.write(prod.getName() + "," + prod.valueTotal(prod.getPrice(), prod.getQuantity()));
				bw.newLine();
			}
		}
		catch (IOException e){
			e.printStackTrace();
		}
		
		
	}

}
