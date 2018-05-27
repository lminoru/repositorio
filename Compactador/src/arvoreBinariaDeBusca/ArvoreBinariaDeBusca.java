package arvoreBinariaDeBusca;

import no.No;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author u17183
 */
public class ArvoreBinariaDeBusca<X extends Comparable> implements Cloneable
{
    protected No<X> raiz;

    public ArvoreBinariaDeBusca()
    {
        this.raiz = null;
    }
    
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

   public String toString() 
   {
        return "(" + toString(this.raiz) + ")"; 
   }

   private String toString(No no)//auxialiar
   {
        String ret = "(";
        if(no != null)
        {
            ret += no.getInfo().toString();
            ret += toString(no.getLeft());
            ret += toString(no.getRight());
        }
        ret += ")";
        return ret;
   }

   public int hashCode() 
   {
        return hashCode(this.raiz);
   }

   private int hashCode(No no)
   {
        int ret = 1;
        if(no != null)
        {
            ret = ret*7 + no.getInfo().hashCode();
            ret = ret*7 + hashCode(no.getLeft());
            ret = ret*7 + hashCode(no.getRight());
        }

        return ret;
   }


    

    public Object clone ()
    {
        ArvoreBinariaDeBusca<X> ret = null;
        try
        {
            ret = new ArvoreBinariaDeBusca<X>(this);
        }
        catch(Exception err){}

        return ret;
    }

   public ArvoreBinariaDeBusca(ArvoreBinariaDeBusca<X> modelo) throws Exception 
   {
        if(modelo == null)
            throw new Exception("modelo nulo!");

        guarde((X) modelo.raiz);
   }

    public void guarde(X x) throws Exception
    {
        X info = tentaClonar(x);

        if(vazia())
        {
            this.raiz = new No(info);
            return;
        }

        No<X> atual = this.raiz;
        
        while(true)
        {
            int comp = info.compareTo(atual.getInfo());

            if(comp == 0)
                throw new Exception("Informação repetida");

            if(comp < 0)
            {
                if(atual.getLeft() == null)
                {
                    atual.setLeft(new No(info));
                    return;
                }

                atual = atual.getLeft();
            }
            else //comp > 0
            {
                    if(atual.getRight() == null)
                    {
                            atual.setRight(new No(info));
                            return;
                    }

                    atual = atual.getRight();
            }
        }
    }

    public boolean vazia()
    {
        return this.raiz == null;
    }

    public boolean tem(X x) throws Exception
    {
        if(x == null)
                throw new Exception("Parametro nulo");

        if(vazia())
                return false;

        No atual = this.raiz;

        while(true)
        {
            int comp = x.compareTo(atual.getInfo());

            if(comp == 0)
                    return true;

            if(comp < 0)
            {
                    if(atual.getLeft() == null)
                            return false;

                    atual = atual.getLeft();
            }
            else //comp > 0
            {
                    if(atual.getRight() == null)
                            return false;

                    atual = atual.getRight();
            }
        }
    }

/*exclusão em arvores binarias de busca
Para realizar essa situação raciocinaremos da seguinte forma:
a) Caso a informação a ser excluida resida em uma folha, simplesmente removemos o nó que a contém tornando null o
   ponteiro do nó superior que o aponta
b) No caso da informação a ser excluida residir em um nó de derivação o nó não será propriamente removido; deveremos
   localizar em uma de suas subarvores uma informação em um nó que este sim será removido e a

c) Caso o nó que contém a informação a ser excluida tiver apenas uma subarvores (não importa que esse nó seja um nó de derivação)
   Elimina-lo-emos fazendo o ponteiro que apontava para ele no nó superior passar a apontar para a sua subarvores
*/
    public void excluir(X x) throws Exception
    {
        No atual = this.raiz;

        for(;;)
        {
            int comp = x.compareTo(atual.getInfo());

            if(comp == 0)
            {
                //folha
                if(atual.getLeft() == null && atual.getRight() == null) 
                {
                    if(atual.getPai().getLeft().equals(atual))
                    {
                        atual.getPai().setLeft(null);
                    }
                    else
                        atual.getPai().setRight(null);

                    return;
                }
                
                //derivacao com apenas um filho 
                if (atual.getLeft() == null) //filho a direita
                {
                    if(atual.getPai().getLeft().equals(atual))
                    {
                        atual.getPai().setLeft(atual.getRight());
                    }
                    else
                        atual.getPai().setRight(atual.getRight());
                    
                    return;
                }
                if (atual.getRight() == null)//filho a esquerda
                {
                    if(atual.getPai().getLeft().equals(atual))
                    {
                        atual.getPai().setLeft(atual.getLeft());
                    }
                    else
                        atual.getPai().setRight(atual.getLeft());
                    
                    return;
                }
                    
                //derivacao com dois filhos
                
                //extrema direita da sub-arvore esquerda
                No aux = atual.getLeft();
                while (aux.getRight()!=null)
                {
                    aux = aux.getRight();
                }
                atual.setInfo(aux.getInfo());
                aux.getPai().setRight(null);
                
                return;
            }

            if(comp < 0)
            {
                    if(atual.getLeft() == null)
                            return;

                    atual = atual.getLeft();
            }
            else //comp > 0
            {
                    if(atual.getRight() == null)
                            return;

                    atual = atual.getRight();
            }
        }
    }

    public boolean equals (Object obj) 
    {
        if (obj == this)
            return true;

        if(obj == null)
            return false;

        if(!(obj instanceof ArvoreBinariaDeBusca))
            return false;

        ArvoreBinariaDeBusca arv = (ArvoreBinariaDeBusca)obj;

        if(!this.raiz.equals(arv.raiz))
            return false;

        return true;
    }
}