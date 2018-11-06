package t4Exemplo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws IOException {
		ArrayList<String> verificadas = new ArrayList<String>();
		ler(verificadas);
		FileWriter fw = new FileWriter("C:/Projects/Lprog-T4/verified.txt",false);
        BufferedWriter bw=new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);

		for (String string : verificadas) {
			out.write(string + "  -  ");
			out.write("\n");
		}
		
		out.close();
		/*File file1 = new File("C:/Projects/Lprog-T4/words.txt");
		File file2 = new File("C:/Projects/Lprog-T4/fragment.txt");
		char CharCounter = 0;       
		BufferedReader in = (new BufferedReader(new FileReader(file1)));
		PrintWriter out = (new PrintWriter(new FileWriter(file2)));
		int ch;
		while ((ch = in.read()) != -1)
		{
		   if (Character.isLowerCase(ch))
		   {
		        ch=Character.toUpperCase(ch);// convert assign variable
		   }
		out.write(ch);
		}
		in.close();
		out.close();
		*/
	}

	private static void ler(ArrayList<String> verificadas) throws IOException {
		ArrayList<String> palavras = new ArrayList<String>();
		ArrayList<String> palavraslowercase = new ArrayList<String>();
		ArrayList<String> fragmentos = new ArrayList<String>();
		ArrayList<String> fragmentoslowercase = new ArrayList<String>();
		
		// 1 ler lista de plavras
		File file = new File("C:/Projects/Lprog-T4/words.txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String word;
		while ((word = br.readLine()) != null) {
			palavraslowercase.add(word);
		}
		//System.out.println(palavras.toString());
		
		//2ler lista de fragmentos
		File file2 = new File("C:/Projects/Lprog-T4/fragments.txt");

		BufferedReader br2 = new BufferedReader(new FileReader(file2));

		String fragment;
		while ((fragment = br2.readLine()) != null) {
			fragmentoslowercase.add(fragment);
		}
		//System.out.println(fragmentos.toString());
		
		
		//por tudo para lowwer case
		
		for(String palavra : palavraslowercase ) {
			palavras.add(palavra.toLowerCase());
		}
		
		for(String palavra : fragmentoslowercase ) {
			fragmentos.add(palavra.toLowerCase());
		}
		
		
		boolean flag = false;
		
		//3 organizar fragmentos por tamanho
		Collections.sort(fragmentos, Comparator.comparing(String::length).reversed());
		
		String aux, lastFragment = null;
		int i =0;
		
		//4 percorre lista de palavras
		for(String palavra : palavras ) {
			//System.out.println("palavra atual: " + palavra);
			//14 zerar tudo
			aux = palavra;
			flag=false;
			
			//5 enquanto o tamanho da palavra nao zerar
			while(aux.length()!=0 || flag==false) {
				aux=palavra;
				//6percorre lista de fragmentos
				for(String fragmento : fragmentos ) {
					//System.out.println("fragmento atual: " + fragmento);
					
					if(fragmento!= null) {
						//6 se a palavra conter o fragmento
						if(aux.toLowerCase().contains(fragmento.toLowerCase())) {
							
							//8 remove o fragmento de dentro da palavra
							aux = aux.replaceAll(fragmento, "");
							//System.out.println("palavra agora: " + aux);
							
							//9 para quando o tamanho da palavra zerar
							if(aux.length()==0) {
								System.out.println("a palavra " + palavra + " pode ser verificada!!!");
								verificadas.add(palavra);
								flag=true;
								break;
							}
						}
					}else {
						break;
					}
				}
				
				if(flag==false) {
					//10 se nao encontrar de primeira
					//11 se for a primeira vez que percorre, remove a primeira palavra e salva pra adicionar depois
					if(i==0) {
						lastFragment = fragmentos.get(i);
						//System.out.println("fragmento retirada: " + lastFragment);
						fragmentos.remove(i);
						i++;
					//12 se for a ultima, para e escreve como verificada
					}else if(i>=fragmentos.size()){
						//System.out.println("tamanho de i alcançou fragmento");
						i=0;
						flag=true;
						break;	
					}else {
						//13 em qualquer outro caso, adiciona a ultima palavra removida e remove outra a nova
						fragmentos.add(lastFragment);
						i++;
						//System.out.println("fragmento adicionado novamente: " + lastFragment);
						lastFragment = fragmentos.get(i);
						//System.out.println("fragmento retirada: " + lastFragment);
						fragmentos.remove(i);
						
					}
				}else {
					//14 zerar tudo
					fragmentos.add(lastFragment);
					i=0;
				}
				
			}
			/////
			
		}

	}
}