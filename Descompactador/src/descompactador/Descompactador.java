/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descompactador;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Leo Minoru
 */
public class Descompactador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try
        {
            System.out.println("Digite o endereço do arquivo");
            BufferedReader tc = new BufferedReader(new InputStreamReader(System.in));

            String recebe   = tc.readLine();

            String nomeArq = recebe; 
            String caminho = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\";

            if (recebe.contains("\\")){//outro caminho
                nomeArq = recebe.substring(recebe.lastIndexOf("\\")+1); 
                caminho = recebe.replace(nomeArq, "");
            }
            System.out.println("Descompactando..."+caminho+nomeArq);
        }
        catch(Exception err){}
    }
    
}
