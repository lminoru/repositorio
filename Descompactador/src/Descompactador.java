/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import descompactador.*;
import codigo.Codigo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 *
 * @author Leo Minoru
 */
public class Descompactador {

    /**
     * @param args the command line arguments
     */
    /*public static void main(String[] args) {
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
            
            //receber bytes do arquivo
            RandomAccessFile file = new RandomAccessFile(caminho+nomeArq, "rw");
            int lixo = file.read(); 
            int diferentes = file.read();
            
            Codigo[] cabecalho = new Codigo[256];
            for (int i=0; i<diferentes; i++){
                cabecalho[file.readByte()]
            }
            int[] bytesComp = new int[(int)file.length()];           
            for(int i= 0; i < file.length(); i++){
                int lido = file.read();
                System.out.println("lido"+lido);
                if (lido!=0)
                    bytesComp[i] = lido;
            }
            file.close();
            
            //passar cabecalho para vetor codigo
            System.out.println(bytesComp[0]);
            System.out.println(bytesComp[1]);
            int lixo = bytesComp[0];
            int dife = bytesComp[1];
            
            System.out.println("indice:"+bytesComp[2]);
            System.out.println("tamanho:"+bytesComp[3]);
            System.out.println("char:"+bytesComp[4]);
            System.out.println("char:"+bytesComp[5]);
            System.out.println("char:"+bytesComp[6]);
            System.out.println("char:"+bytesComp[7]);
            System.out.println("char:"+bytesComp[8]);
            System.out.println("char:"+bytesComp[9]);
            System.out.println("char:"+bytesComp[10]);
            System.out.println("char:"+bytesComp[11]);
            System.out.println("char:"+bytesComp[12]);
            System.out.println("char:"+bytesComp[13]);
            
            Codigo[] cabecalho = new Codigo[256];
            System.out.println("TABELA");
            
            int i=2;
            int iWhile = 0;
            while (iWhile<dife){
                System.out.println("indice:"+bytesComp[i]);System.out.println("tamanho codigo:"+bytesComp[i+1]);
                
                
                cabecalho[bytesComp[i]]= new Codigo();
                for (int j=0; j<bytesComp[i+1]; j++){
                    cabecalho[bytesComp[i]].mais(bytesComp[i+2+j]);
                    System.out.println("char "+j+":"+bytesComp[i+2+j]);
                }
                System.out.println("codigo:"+cabecalho[bytesComp[i]].getCod());
                
                i += 2+bytesComp[i+1];
                iWhile++;
            }
            
            //Passa
            System.out.println("length:"+cabecalho.length);
            System.out.println(cabecalho); 
            
        }
        catch(Exception err){}
    }*/
    
}
