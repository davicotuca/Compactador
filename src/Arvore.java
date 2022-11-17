import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Arvore implements Cloneable
{
	private No raiz=null;

	private String preOrdem (No r)
	{
		if (r==null) return "";

		return r.getInfo()+" "+
		       this.preOrdem(r.getEsq())+" "+
				this.preOrdem(r.getDir());
	}

	private String inOrdem (No r)
	{
		if (r==null) return "";

		return this.inOrdem(r.getEsq())+" "+
		       r.getInfo()+" "+
				this.inOrdem(r.getDir());
	}

	private String posOrdem (No r)
	{
		if (r==null) return "";

		return this.posOrdem(r.getEsq())+" "+
				this.posOrdem(r.getDir())+" "+
			   r.getInfo();
	}

	@Override
	public String toString ()
	{
		String pre=this.preOrdem(this.raiz),
		       in =this.inOrdem (this.raiz),
			   pos=this.posOrdem(this.raiz);

		return "Pré-ordem: "+(pre.equals("")?"árvore vazia":pre)+"\n"+
		       "In-ordem : "+(in .equals("")?"árvore vazia":in )+"\n"+
			   "Pós-ordem: "+(pos.equals("")?"árvore vazia":pos);
	}

	private boolean equals (No raizA, No raizB)
	{
		if (raizA==null && raizB!=null) return false;
		if (raizA!=null && raizB==null) return false;
		if (raizA==null && raizB==null) return true;

		if (!raizA.getInfo().equals(raizB.getInfo())) return false;

		return equals (raizA.getEsq(),raizB.getEsq()) &&
			   equals (raizA.getDir(),raizB.getDir());
	}

	@Override
    public boolean equals (Object obj)
	{
		if (obj==this) return true;
		if (obj==null) return false;
		if (this.getClass()!=obj.getClass()) return false;

		Arvore arv = (Arvore)obj;

		return equals(this.raiz,arv.raiz);
	}

    private int hashCode (No raiz)
	{
		int ret = 1;

		if (raiz!=null)
		{
			ret = 13*ret + raiz.getInfo().hashCode();
			ret = 13*ret + hashCode (raiz.getEsq());
			ret = 13*ret + hashCode (raiz.getDir());
		}

		return ret;
	}

	@Override
	public int hashCode ()
	{
		return hashCode (this.raiz);
	}

	private No copia (No raiz)
	{
		if (raiz==null) return null;

		return new No (copia(raiz.getEsq()),
			           raiz.getInfo(),
						raiz.getFrequencia(),
			           copia(raiz.getDir()));
	}

	public Arvore (Arvore modelo) throws Exception
	{
		if (modelo==null) throw new Exception ("modelo ausente");

		this.raiz = copia(modelo.raiz);
	}
	public Arvore (){}

	public Arvore (FilaDePrioridades fila){
		try {
			while (fila.size() >= 2){

					No no = new No(
							fila.pop(),
							0,
							fila.pop());
					no.setFrenquencia(no.getDir().getFrequencia() + no.getEsq().getFrequencia());
					fila.queue(no);
			}
			this.raiz = fila.pop();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	private void toHashMap(No n, Map<Byte, String> map, String codigo){
		if(n.getDir() == null && n.getEsq()== null)  // Chegou uma uma folha
				map.put((Byte)n.getInfo(), codigo);
		if(n.getDir() != null)
			toHashMap(n.getDir(), map, codigo+"1");

		if(n.getEsq() != null)
			toHashMap(n.getEsq(), map, codigo+"0");
	}

	public Map<Byte, String> toHashMap() {
		Map<Byte, String> map = new HashMap<>();
		toHashMap(this.raiz, map, "");
		return map;
	}

	

	public Object clone ()
	{
		Arvore ret = null;

		try
		{
			ret = new Arvore (this);
		}
		catch (Exception erro)
		{}

		return ret;
	}

	public ArrayList<Comparable> encontraByte(String binario) {
		No no = this.raiz;
		ArrayList<Comparable> ret = new ArrayList<>();
		for (int i = 0; i < binario.length(); i++) {
			if (no.getDir() == null && no.getEsq() == null) {
				ret.add(i);
				ret.add(no.getInfo());
				//ret.put(binario.substring(0, i+1),no.getInfo());
				break;
			}
			if (binario.charAt(i) == '0') {
				no = no.getEsq();
			}
			else {
				no = no.getDir();
			}
		}
		return ret;
	}
}

