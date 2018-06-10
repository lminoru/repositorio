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
    private static Codigo[] cod = new Codigo[256];
    public static void main(String[] args){
        // TODO code application logic here
        try
        {
            System.out.println("Digite o endereço do arquivo");
            BufferedReader tc = new BufferedReader(new InputStreamReader(System.in));
            String nomeArq = tc.readLine();
            
            
            //byte[] byteFile = new byte[Integer.valueOf(new Long(file.length()).toString())];;;
            //        file.read(byteFile);
            int[] vetInt = new int[256];
            for(int i = 0; i < 256; i++)
                vetInt[i]=0;
            
            RandomAccessFile file = new RandomAccessFile(nomeArq, "rw");            
            for(long i= 0; i < file.length(); i++)
                vetInt[file.read()]++;             
            file.close();
            
            
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

            No<Informacao> arvore = vetNo[0];
            
            Codigo c = new Codigo();
            print(arvore, c);
            
            RandomAccessFile fileVelho = new RandomAccessFile(nomeArq, "rw");
            Codigo codComp = new Codigo();
            for(long i= 0; i < fileVelho.length(); i++)
                codComp.mais(cod[fileVelho.read()]);
            fileVelho.close();
           
            if(codComp.length() % 8 != 0)
                for(int i = 0;i< 8-(codComp.length()%8); i++)
                    codComp.mais("0");
            
            byte[] bytesComp = codComp.toByteArray();
                                 
            System.out.println("Qual o nome que deseja para o arquivo compactado?");
            String nome = tc.readLine();
            RandomAccessFile fileNovo = new RandomAccessFile(nome, "rw");   
            
            file.write(bytesComp);
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
    
    public static void print(No<Informacao> raiz, Codigo c)
    {
        if(raiz != null)
        {
            if(raiz.getInfo().getInd()!=666)
                cod[raiz.getInfo().getInd()] = (Codigo)c.clone();
            else
            {
                c.mais("0");
                print(raiz.getLeft(), c);
                c.tiraUltimo();
                c.mais("1");
                print(raiz.getRight(), c);
            }
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
