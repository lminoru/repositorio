/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import compactador.Compactador;

/**
 *
 * @author Luciano
 */
public class Programa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        try
        {
            BufferedReader tc = new BufferedReader(new InputStreamReader(System.in));
            String opcao;
            String arqRemet;
            String arqDest;
            do{                
                System.out.println("Voce deseja: (1)Compactar, (2)Descompactar ou (3)Sair");
                opcao = tc.readLine();
                
                switch(opcao)
                {
                    case "1": 
                        System.out.println("Digite o endereço do arquivo que deseja compactar(c/ extensao): ");           
                        arqRemet   = tc.readLine();
                        
                        //verificacao  sem nome
                        if("".equals(arqRemet.trim())){
                            System.err.println("Nome do arquivo não especificado");
                            return;
                        }
                        
                        String nomeArq = arqRemet; 
                        String caminho = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\";

                        if (arqRemet.contains("\\")){//outro caminho
                            nomeArq = arqRemet.substring(arqRemet.lastIndexOf("\\")+1); 
                            caminho = arqRemet.replace(nomeArq, "");
                        }
                        
                           
                            
                        System.out.println("Digite o endereço/nome para o arquivo compactado(s/ extensao): ");
                        arqDest = tc.readLine();
                        
                        //verificacao  sem nome
                        if("".equals(arqDest.trim())){
                            System.err.println("Nome do arquivo não especificado");
                            return;
                        }
                        
                        String nomeArq2 = arqDest; 
                        String caminho2 = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\";

                        if (arqRemet.contains("\\")){//outro caminho
                            nomeArq2 = arqDest.substring(arqRemet.lastIndexOf("\\")+1); 
                            caminho2 = arqDest.replace(nomeArq, "");
                        }
                        
                        
                        
                        System.out.println("");
                        System.out.print("Compactando...");
                        Compactador.compactar(caminho+nomeArq, caminho2 + nomeArq2);
                        System.out.println("");
                    break;
                    case "2": 
                        System.out.println("Digite o endereço/nome do arquivo que deseja descompactar(s/ extensao): ");           
                        arqRemet   = tc.readLine();
                        
                        
                        //verificacao  sem nome
                        if("".equals(arqRemet.trim())){
                            System.err.println("Nome do arquivo não especificado");
                            return;
                        }
                        
                        String nomeArqdes = arqRemet+".hentai"; 
                        String caminhodes = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\";

                        if (arqRemet.contains("\\")){//outro caminho
                            nomeArqdes = arqRemet.substring(arqRemet.lastIndexOf("\\")+1); 
                            caminhodes = arqRemet.replace(nomeArqdes, "");
                        }
                        

                        
                        System.out.println("Digite o endereço para o arquivo descompactado(c/ extensao): ");
                        arqDest = tc.readLine();
                        
                        
                        //verificacao  sem nome
                        if("".equals(arqDest.trim())){
                            System.err.println("Nome do arquivo não especificado");
                            return;
                        }
                        
                        
                        
                        String nomeArq2des = arqDest; 
                        String caminho2des = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\";

                        
                        if (arqRemet.contains("\\")){//outro caminho
                            nomeArq2des = arqDest.substring(arqDest.lastIndexOf("\\")+1); 
                            caminho2des = arqDest.replace(nomeArq2des, "");
                        }
                           
                        
                        
                        System.out.println("");
                        System.out.print("Descompactando...");
                        Compactador.descompactar(caminhodes+nomeArqdes, caminho2des + nomeArq2des);  
                        System.out.println("");
                    break;
                    case "3": break;
                    default: System.out.println("Não existe essa opcao");
                }
                
            }while(!opcao.equals("3"));
        }
        catch(FileNotFoundException er)
        {
            System.err.println("Arquivo não encontrado");
        }
        catch(Exception err)
        {
            System.out.println(err.getMessage());
        }
    }   
    
}
