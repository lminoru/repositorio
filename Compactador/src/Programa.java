/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Informacao.Informacao;
import codigo.Codigo;
import java.io.*;
import no.No;
import compactador.Compactador;

/**
 *
 * @author Luciano
 */
public class Programa {

    /**
     * @param args the command line arguments
     */
    private static Codigo[] cod = new Codigo[256];
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
           
           /* System.out.println("Digite o endereço do arquivo");
            
            String recebe   = tc.readLine();

            String nomeArq = recebe; 
            String caminho = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\";

            if (recebe.contains("\\")){//outro caminho
                nomeArq = recebe.substring(recebe.lastIndexOf("\\")+1); 
                caminho = recebe.replace(nomeArq, "");
            }
            System.out.println("Compactando..."+caminho+nomeArq);
            
            
            //byte[] byteFile = new byte[Integer.valueOf(new Long(file.length()).toString())];;;
            //        file.read(byteFile);
            int[] vetInt = new int[256];
            for(int i = 0; i < 256; i++)
                vetInt[i]=0;
            
            
            RandomAccessFile file = new RandomAccessFile(caminho+nomeArq, "rw");            
            for(long i= 0; i < file.length(); i++){
                int indice = file.read();
                vetInt[indice]++;             
            }
            file.close();
            
            No<Informacao>[] vetNo = new No[256]; 
            
            int j = 0;
            for(int i = 0; i< 256; i++)
                if(vetInt[i] > 0)
                {
                    vetNo[j] = new No(new Informacao(i, vetInt[i]));
                    j++;
                }
            
             System.out.println(".");
            int controle = ordenar(vetNo);
            int qtdDif = controle;
            while(controle != 1)
            {              
                int novaQtd = vetNo[controle-2].getInfo().getQtd() + vetNo[controle-1].getInfo().getQtd();
                No<Informacao> novoNoDir = new No(vetNo[controle-1]);
                No<Informacao> novoNoEsq = new No(vetNo[controle-2]);
                vetNo[controle-2] = new No(new Informacao(666,novaQtd), novoNoDir, novoNoEsq);
                vetNo[controle-1] = null;
                
                controle = ordenar(vetNo);
            }
           
            No<Informacao> arvore = vetNo[0];
            System.out.println("altura:"+arvore.altura());
            System.out.println("arvore:"+arvore.toString());
            Codigo c = new Codigo();
            print(arvore, c);
            System.out.println("..");
            file = new RandomAccessFile(caminho+nomeArq, "rw");
            System.out.println("...");
            System.out.println(arvore.altura());
            Codigo codComp = new Codigo();
            for(long i= 0; i < file.length(); i++){
                int indice = file.read();
                codComp.mais(cod[indice]);
            }
            System.out.println("....");
            file.close();
            
            byte[] bytesComp = codComp.toByteArray();
                               
            System.out.println("Qual o nome que deseja para o arquivo compactado?");
            recebe = tc.readLine();
            
            String nome = recebe;
            caminho = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\";
            
            if (recebe.contains("\\")){//outro caminho
                nome = recebe.substring(recebe.lastIndexOf("\\")+1); 
                caminho = recebe.replace(nome, "");
            }
            
            if(nome.equals(nomeArq))
                nome +="(Compactado)";
            
            RandomAccessFile fileNovo = new RandomAccessFile(caminho + nome+".hentai", "rw");  
            
            //cabeçalho
            byte qtdLixo = Byte.parseByte(Integer.toString(8-((codComp.getCod().length()%8)==0?8:codComp.getCod().length()%8)));
            fileNovo.writeByte(qtdLixo);System.out.println(qtdLixo);
            fileNovo.writeInt(qtdDif);System.out.println(qtdDif);
            for (int i = 0; i<256; i++) {
                if(cod[i] != null)
                {
                    System.out.println("indice:"+i);
                    System.out.println("tamanho:"+cod[i].getCod().length());
                    System.out.println("codigo:"+cod[i].getCod());
                    
                    fileNovo.write(i & 0xFF); 
                    fileNovo.writeByte(cod[i].getCod().length());
                    for (int l=0; l<cod[i].getCod().length(); l++)
                        fileNovo.writeChar(cod[i].getCod().charAt(l));
                }
            }

            System.out.println("codComp:" + codComp.getCod());
            fileNovo.write(bytesComp);

            
            fileNovo.close();*/
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
    
    public static void print(No<Informacao> raiz, Codigo c)
    {
        if(raiz != null)
        {
            if(raiz.getInfo().getInd()!=666)
                cod[raiz.getInfo().getInd()] = (Codigo)c.clone();
            else
            {
                c.mais(0);
                print(raiz.getLeft(), c);
                c.tiraUltimo();
                c.mais(1);
                print(raiz.getRight(), c);
                c.tiraUltimo();
            }
        }
    }

    private static int ordenar(No<Informacao>[] vetNo) 
    {       
       int lento = 0;
        while(vetNo[lento] != null)
        {
            int oMaior = lento;
            int rapido = lento;
            while(vetNo[rapido] != null) //oMaior apontar para o No de maior freq
            {
                if(vetNo[rapido].getInfo().getQtd() > vetNo[oMaior].getInfo().getQtd())
                    oMaior = rapido;
                
                rapido++;
                
                if (rapido==256)
                    break;
            }
            
            if(vetNo[oMaior] != vetNo[lento]) //coloca o de maior freq no|trocan 
            {                                 //indice que esta sendo ver|do pos
                No<Informacao> aux = vetNo[oMaior];//ificado do vetor    |deles
                vetNo[oMaior] = vetNo[lento];
                vetNo[lento] = aux;
            }

            lento++;
            
            if (lento==256)
                    break;
        }
       
        return lento;
    }
}
