/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compactador;

import Informacao.Informacao;
import java.io.*;
import no.No;

/**
 *
 * @author Luciano
 */
public class Compactador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        try
        {
            System.out.println("Digite o endereço do arquivo");
            BufferedReader tc = new BufferedReader(new InputStreamReader(System.in));
            String nomeArq = tc.readLine();
            RandomAccessFile file = new RandomAccessFile(nomeArq, "rw");
            
            //byte[] byteFile = new byte[Integer.valueOf(new Long(file.length()).toString())];;;
            //        file.read(byteFile);
            int[] vetInt = new int[256];
            for(int i = 0; i < 256; i++)
                vetInt[i]=0;
                        
            for(long i= 0; i < file.length(); i++)              
                vetInt[file.read()]++;             
            
            No<Informacao>[] vetNo = new No[256];
            
            int j = 0;
            for(int i = 0; i< 256; i++)
                if(vetInt[i] > 0)
                {
                    vetNo[j] = new No(new Informacao(i, vetInt[i]));
                    j++;
                }
            
            int controle = 256;
            while(controle != 1)
            {
                controle = ordenar(vetNo);
                int novaQtd = vetNo[controle-2].getInfo().getQtd() + vetNo[controle-1].getInfo().getQtd();
                No<Informacao> novoNoDir = new No(vetNo[controle-1]);
                No<Informacao> novoNoEsq = new No(vetNo[controle-2]);
                vetNo[controle-2] = new No(new Informacao(666,novaQtd), novoNoDir, novoNoEsq);
            }            
        }
        catch(FileNotFoundException er)
        {
            System.err.println("Arquivo não encontrado");
        }
        catch(Exception err)
        {
            System.err.println(err.getMessage());
        }
    }   

    private static int ordenar(No<Informacao>[] vetNo) 
    {       
        int lento = 0;
        while(vetNo[lento] != null)
        {
            int oMenor = lento;
            int rapido = 0;
            while(vetNo[rapido] != null)
            {
                if(vetNo[rapido].getInfo().getQtd() < vetNo[oMenor].getInfo().getQtd())
                    oMenor = rapido;
                
                rapido++;
            }
            
            if(vetNo[oMenor] != vetNo[lento])
            {
                No<Informacao> aux = vetNo[oMenor];
                vetNo[oMenor] = vetNo[lento];
                vetNo[lento] = aux;
            }

            lento++;
        }
       
        return lento;
    }
}
