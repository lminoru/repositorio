/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Informacao;

/**
 *
 * @author Luciano
 */
public class Informacao {
    private int ind;
    private int qtd;
    
    public Informacao(int ind, int qtd) throws Exception
    {
        this.ind = ind;
        this.qtd = qtd;
    }
    
    public int getInd()
    {
        return this.ind;
    }
    
    public int getQtd()
    {
        return this.qtd;
    }
}
