package no;

import java.lang.reflect.Method;

/**
 *
 * @author u17191
 */
public class No<X> implements Cloneable
{
    protected No<X> right;
    protected No<X> left;
    protected No<X> pai;
    protected X info;

    protected X meuCloneDeX (X x)
    {
        X ret = null;

        try 
        {
            Class<?> classe = x.getClass();
            Class<?>[] tipoDoParametroFormal = null;
            Method metodo = classe.getMethod ("clone", tipoDoParametroFormal);
            Object[] parametroReal = null;

            ret = ((X)metodo.invoke (x, parametroReal));
        }
        catch(Exception err){}

        return ret;
    }

    protected X tentaClonar(X x)
    {
        X info;
        if(x instanceof Cloneable)
                info = meuCloneDeX(x);
        else
                info = x;

        return info;
    }

    public No (X x, No<X> dir, No<X> esq) throws Exception
    {
        if(x == null)
            throw new Exception("Parametro nulo");
        
        if(dir == null)
            throw new Exception("Parametro nulo");
        
        if(esq == null)
            throw new Exception("Parametro nulo");
        
        this.info = x;
        this.right = dir;
        this.left = esq;
        esq.pai = this;
        dir.pai = this;
    }

    public No (X x) throws Exception
    {
        if(x == null)
            throw new Exception("Parametro nulo");
        
        this.info = x;
    }
    
    public No getPai()
    {
        return this.pai;
    }

    public void setPai(No<X> no) throws Exception
    {
        if(no == null)
            throw new Exception("Parametro nulo");
        
        this.pai = no;
    }
    
    public No<X> getRight()
    {
        return this.right;
    }

    public No<X> getLeft()
    {
        return this.left;
    }

    public void setRight(No<X> no) 
    {
        this.right = no;
        
        if (no!=null)
            no.pai = this;
    }

    public void setLeft(No<X> no) 
    {
        this.left = no;
        
        if (no!=null)
            no.pai = this;
    }
    
    public X getInfo()
    {
        return this.info;
    }
    
    public void setInfo(X info) throws Exception
    {
        if(info == null)
            throw new Exception("Parametro nulo");
        
        this.info = info;
    }
            
    public String toString()
    {
        String ret = info.toString();
        
        return ret;
    }
    
    public boolean equals(Object modelo)
    {
        if(modelo == this)
            return true;
        
        if(modelo == null)
            return false;
        
        if (!(modelo instanceof No)) 
            return false;

        No<X> no = (No<X>)modelo;

        if(this.right != null)
        if (!this.right.equals(no.right))
            return false;

        if(this.left != null)
        if (!this.left.equals(no.left))
            return false;      
        
        if (!(this.info.equals(no.info)))
            return false;
            
        return true;
    }
    
    public int hashCode()
    {
        int ret = 666;
        
        ret = ret*7 + this.info.hashCode();
        
        return ret;
    }

    public No(No modelo) throws Exception
    {
        if(modelo == null)
            throw new Exception("modelo nulo!");

        if(modelo.right != null)
            this.setRight(new No(modelo.right));

        if(modelo.left != null)
            this.setLeft(new No(modelo.left));

        this.info = tentaClonar((X)modelo.info);
    }

    public No clone()
    {
        No ret = null;
        try{ret = new No(this);}catch(Exception err){}
        return ret;
    }
}
