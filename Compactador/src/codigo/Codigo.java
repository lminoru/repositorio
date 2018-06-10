/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

/**
 *
 * @author Leo Minoru
 */
public class Codigo {
        String cod;
        
        public Codigo (){
            cod = "";
        }
                          
	public void mais(int bit){
		this.cod += bit;
	}

	public void tiraUltimo(){
		cod.substring(0, cod.length() - 1);//algo assm
        }
        
        public String toString (){
            String ret = "{";
            ret += cod;
            ret += "}";
            
            return ret;
        }
        
        public int hashCode (){
            int ret = 13;
            ret = ret * 7 + cod.hashCode();
            
            return ret;
        }
        
        public boolean equals (Object obj){
            if (this==obj)
                return true;
            if (this==null)
                return false;
            if (!(obj instanceof Codigo))
                return false;
            
            Codigo modelo = (Codigo)obj;
            
            if (this.cod!=modelo.cod)
                return false;
            
            return true;
            
        }
        
        public Codigo clone (){
            Codigo ret = null;
            try {
                ret = new Codigo(this);
            }
            catch(Exception err){}
            
            return ret;
        }
        
        
        public Codigo (Codigo modelo) throws Exception{
            if(modelo == null)
                throw new Exception("modelo nulo!");
            
            this.cod = modelo.cod;
            
            
        }
}