package compactador;

import Informacao.Informacao;
import codigo.Codigo;
import java.io.RandomAccessFile;
import no.No;

public class Compactador
{
	private static Codigo[] cod = new Codigo[256];

	public static void compactar(String remet, String destino) throws Exception
	{
		int[] vetInt = new int[256];
        for(int i = 0; i < 256; i++)
            vetInt[i]=0;      
        
        RandomAccessFile file = new RandomAccessFile(remet, "rw");            
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
        Codigo c = new Codigo();
        print(arvore, c);
        file = new RandomAccessFile(remet, "rw");
        Codigo codComp = new Codigo();
        for(long i= 0; i < file.length(); i++){
            int indice = file.read();
            codComp.mais(cod[indice]);
        }
        file.close();
        
        System.out.println("Arvore:"+arvore.toString());
        
        byte[] bytesComp = codComp.toByteArray();

        RandomAccessFile fileNovo = new RandomAccessFile(destino+".hentai", "rw");  
        
        //cabeçalho
        byte qtdLixo = Byte.parseByte(Integer.toString(8-((codComp.getCod().length()%8)==0?8:codComp.getCod().length()%8)));
        fileNovo.writeByte(qtdLixo);
        fileNovo.writeInt(qtdDif);
        for (int i = 0; i<256; i++) {
            if(cod[i] != null)
            {                    
                fileNovo.write(i & 0xFF); 
                fileNovo.writeByte(cod[i].getCod().length());
                for (int l=0; l<cod[i].getCod().length(); l++)
                    fileNovo.writeChar(cod[i].getCod().charAt(l));
            }
        }

        fileNovo.write(bytesComp);
       
        fileNovo.close();
	}

	public static void descompactar(String remet, String destino) throws Exception
	{
            RandomAccessFile file = new RandomAccessFile(remet, "rw");
            byte lixo = file.readByte();
            int diferentes = file.readInt();
            
            //saber tamnho do do cabecalho
            int tamanhoCabecalho = 5;
            
            //passar cabecalho para vetor codigo
            Codigo[] cabecalho = new Codigo[256];
            for (int i=0; i<diferentes; i++){
                int indice  = file.read() & 0xFF;
                int tamanho = file.readByte();
                tamanhoCabecalho+=2;
                cabecalho[indice] = new Codigo();
                for (int j=0; j<tamanho; j++){
                    char ch = file.readChar();
                    cabecalho[indice].mais(ch);
                    tamanhoCabecalho += 2;
                }
            }   
            
            
            //montar arvore do cabecalho
            No<Integer> arvore = new No(666);
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
            
            
            System.out.println("Arvore:"+arvore.toString());
            
            
            //arquivo descompactado
            RandomAccessFile fileDesc = new RandomAccessFile(destino, "rw");
            

            //Ler byte a byte, se for zero segue pra esquerda da arvore, se for
            //1 direita da arvore. Qnd chegar na folha imprimir no arquivo o info
            //da folha, como byte (writeByte)
            No<Integer> atual = arvore;
            for (long i = tamanhoCabecalho; i<file.length(); i++){
                int direcao = (int)(file.read()& 0xFF);
                String oi = Integer.toBinaryString(direcao);
                while (oi.length()<8)
                    oi = "0"+oi;
                
                if (i==file.length()-1)//tirar lixo
                    oi = oi.substring(0, (8-lixo));
                
                
                
                for (int j=0; j<oi.length(); j++){

                    if(oi.charAt(j)=='1'){//dir
                        if (atual.getRight()!=null)
                            atual = atual.getRight();

                    }
                    else //esq  
                        if (atual.getLeft()!=null)
                            atual = atual.getLeft();
                    
                    
                    if (atual.getLeft()==null && atual.getRight()==null){
                        fileDesc.writeByte(atual.getInfo());
                        atual = arvore;
                    }
                }
            }
           file.close();
           fileDesc.close();
	}

    private static void print(No<Informacao> raiz, Codigo c)
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