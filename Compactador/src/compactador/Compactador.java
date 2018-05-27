/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compactador;

import arvoreBinariaDeBusca.ArvoreBinariaDeBusca;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leo Minoru
 */
public class Compactador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ArvoreBinariaDeBusca arv = new ArvoreBinariaDeBusca();
            arv.guarde(25);
            
            arv.guarde(20);
            arv.guarde(30);
            
            System.out.println(arv.toString());
            
            arv.guarde(27);
            arv.guarde(31);
            
            System.out.println(arv.toString());
            
            arv.excluir(30);
            
            System.out.println(arv.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}
