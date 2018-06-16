/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package descompactador;

import codigo.Codigo;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.BitSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import no.No;

/**
 *
 * @author Leo Minoru
 */
public class Descompactador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
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
            int lixo = file.readByte();
            int diferentes = file.read();
            System.out.println(lixo);System.out.println(diferentes);
            
            int tamanhoCabecalho = 2;
            //passar cabecalho para vetor codigo
            Codigo[] cabecalho = new Codigo[256];
            for (int i=0; i<diferentes; i++){
                int indice  = file.read();
                int tamanho = file.readByte();
                tamanhoCabecalho+=2;
                cabecalho[indice] = new Codigo();
                for (int j=0; j<tamanho; j++){
                    cabecalho[indice].mais(file.readChar());
                    tamanhoCabecalho++;
                }
            }  
            
            
            //montar arvore do cabecalho
            No<Integer> arvore = new No<Integer>(666);
            for (int i=0; i<cabecalho.length; i++){
                if (cabecalho[i]!=null){
                    char[] percurso = cabecalho[i].getCod().toCharArray();
                    No atual = arvore;
                    for (int j=0; j<percurso.length; j++){
                        if (percurso[j]=='0'){
                            if (atual.getLeft()==null)
                                atual.setLeft(new No(666));
                            
                            atual = atual.getLeft();
                        }
                        else
                        {
                            if (atual.getRight()==null)
                                atual.setRight(new No(666));
                            
                                atual = atual.getRight();
                        }
                            
                    }
                    
                    atual.setInfo(i);
                    
                }
                
            }
            System.out.println("altura:"+arvore.altura());
            System.out.println("arvore:"+arvore.toString());
            
            
            //arquivo descompactado
            System.out.println("Digite o nome para arquivo descompactado");
            tc = new BufferedReader(new InputStreamReader(System.in));
            nomeArq = tc.readLine();
            RandomAccessFile fileDesc = new RandomAccessFile(caminho+nomeArq, "rw");
            
            
            
            
            
            
            
            
            
            
            //Ler byte a byte, se for zero segue pra esquerda da arvore, se for
            //1 direita da arvore. Qnd chegar na folha imprimir no arquivo o info
            //da folha, como byte (writeByte)
            No<Integer> atual = arvore;
            for (long i = tamanhoCabecalho; i<file.length(); i++){
                byte direcao = (byte)(file.read() & 0xFF);
                System.out.println("direcao:"+direcao);
                System.out.println(Integer.toBinaryString(direcao));
                String oi = Integer.toBinaryString(direcao);
                while (oi.length()<8)
                    oi = "0"+oi;
                System.out.println(oi);
                
            
                
                //mexer aqui
                for (int j=0; j<8; j++){
                    System.out.println(j);
                    if (atual.getLeft()==null && atual.getRight()==null){
                        fileDesc.writeByte(atual.getInfo());
                        System.out.println(i+","+j+":"+atual.getInfo());
                        atual = arvore;
                        j--;
                    }
                    else{
                    
                        if(oi.charAt(j)=='1'){//dir
                            if (atual.getRight()!=null)
                                atual = atual.getRight();

                        }
                        else //esq  
                            if (atual.getLeft()!=null)
                                atual = atual.getLeft();
                    }      
                }
            }
           file.close();
           //fileDesc.close();
           System.out.println("alo");
        
  
       
        
        }
        catch (Exception ex) {
            Logger.getLogger(Descompactador.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
   
}
