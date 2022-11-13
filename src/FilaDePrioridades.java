import java.util.LinkedList;
import java.util.List;

public class FilaDePrioridades implements Cloneable{ //implements Cloneable
    private List<No> lista;

    public void queue(No item){
        for (int i = 0; i < lista.size(); i++){
            if(lista.get(i).getFrequencia() > item.getFrequencia()) { // se o item que estamos tentando inserir é menor que o proximo item, é o lugar de inserir
                lista.add(i, item);
                return;
            }
        }// se chegou aqui ele é o maior ou a lista está vazia, então é só adicionar no final
        lista.add(item);

    }

    public No pop() throws Exception {
        if(lista.isEmpty())
            throw new Exception("Empty queue!");

        return lista.remove(0);
    }

    public int size(){
        return lista.size();
    }

    public boolean isEmpty(){
        return lista.isEmpty();
    }

    public  FilaDePrioridades(){
        lista = new LinkedList<No>();
    }

    public String toString(){
        String result = "";
        for(No no : lista){
            result += no.getInfo() + " "+ no.getFrequencia() + " ";
        }
        return result;
    }

    public FilaDePrioridades (FilaDePrioridades modelo) throws Exception
    {
        if (modelo==null) throw new Exception ("modelo ausente");

        this.lista = copia(modelo.lista);
    }

    private List copia (List<No> lista)
    {
        if (lista==null) return null;

        List<No> result =  new LinkedList ();

        for (No item: lista) {
            result.add( new No(item.getEsq(), item.getInfo(), item.getFrequencia(), item.getDir()));
        }

        return result;
    }

    public Object clone ()
    {
        FilaDePrioridades ret = null;

        try
        {
            ret = new FilaDePrioridades (this);
        }
        catch (Exception erro)
        {}

        return ret;
    }
}
