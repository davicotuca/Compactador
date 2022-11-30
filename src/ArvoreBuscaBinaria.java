public class ArvoreBuscaBinaria<X extends Comparable<X>> implements Cloneable
{
	private class No
	{
		private X  info;
		private No esq,dir;

		public No (X i)
		{
			this.esq =null;
			this.info=i;
			this.dir =null;
		}

		public No (No e, X i, No d)
		{
			this.esq =e;
			this.info=i;
			this.dir =d;
		}

		public No getEsq () 
		{
			return this.esq;
		}

		public X getInfo ()
		{
			return this.info;
		}

		public No getDir ()
		{
			return this.dir;
		}

		public void setEsq (No e)
		{
			this.esq=e;
		}

		public void setInfo (X i)
		{
			this.info=i;
		}

		public void setDir (No d)
		{
			this.dir=d;
		}
	}

	private No raiz=null;

	public void inclua (X i) throws Exception
	{
		if (i==null) throw new Exception ("Informacao Ausente");

		if (this.raiz==null)
		{
			this.raiz = new No (i);
			return;
		}

		No  ant=null, atual=this.raiz;
		int comp;

		do
		{
    		comp = i.compareTo(atual.getInfo());

			if (comp==0) throw new Exception ("Elemento repetido");

			ant=atual;
			if (comp<0)
				atual=atual.getEsq();
			else
			    atual=atual.getDir();
		}
		while (atual!=null);

		if (comp<0)
			ant.setEsq(new No (i));
		else
			ant.setDir(new No (i));
	}

	private String preOrdem(No r)
	{
		if (r==null) return "";

		return r.getInfo()+" "+
		       this.preOrdem(r.getEsq())+" "+
			   this.preOrdem(r.getDir());
	}

	private String inOrdem(No r)
	{
		if (r==null) return "";

		return this.inOrdem(r.getEsq())+" "+
		       r.getInfo()+" "+
			   this.inOrdem(r.getDir());
	}

	private String posOrdem(No r)
	{
		if (r==null) return "";

		return this.posOrdem(r.getEsq())+" "+
			   this.posOrdem(r.getDir())+" "+
			   r.getInfo();
	}

	public X getItem(X item) throws Exception {

		No atual = this.raiz;
		while(atual != null){
			int comp = item.compareTo(atual.info);
			if(comp == 0)
				return atual.info;
			if(comp > 0)
				atual = atual.dir;
			else
				atual = atual.esq;
		}
		throw new Exception ("Item inexistente");
	}

	public void remova (X i) throws Exception
	{
		No aRemover=this.raiz, pai=null;

		for(;;)
		{
			if (aRemover==null) break;

			int cmp=i.compareTo(aRemover.getInfo());

			if (cmp==0) break;

			pai=aRemover;
			if (cmp<0)
				aRemover = aRemover.getEsq();
			else
				aRemover = aRemover.getDir();
		}

		if (aRemover==null) throw new Exception ("Remocao de algo inexistente");

		if (aRemover.getEsq()==null && aRemover.getDir()==null) // nó a remover é folha
			if (aRemover==this.raiz)
				this.raiz=null;
			else
			if (aRemover==pai.getEsq())
				pai.setEsq(null);
			else // aRemover==pai.getDir()
				pai.setDir(null);
		else // não é folha
			if (aRemover.getEsq()==null || aRemover.getDir()==null) // nó a remover tem 1 filho só
			{
				No filho;
				if (aRemover.getEsq()!=null)
					filho=aRemover.getEsq();
				else // aRemover.getDir()!=null
					filho=aRemover.getDir();

				if (aRemover==pai.getEsq())
					pai.setEsq(filho);
				else // aRemover==pai.getDir()
					pai.setDir(filho);
			}
			else // nó a remover tem 2 filhos
			{
				No extrDirDaSubArvEsq = aRemover.getEsq();
				while (extrDirDaSubArvEsq.getDir()!=null)
					extrDirDaSubArvEsq=extrDirDaSubArvEsq.getDir();

				X aSubstituir = extrDirDaSubArvEsq.getInfo();

				this.remova(aSubstituir);

				aRemover.setInfo(aSubstituir);
			}

	}

	@Override
	public String toString ()
	{
		String pre= this.preOrdem(this.raiz),
		       in = this.inOrdem (this.raiz),
			   pos= this.posOrdem(this.raiz);

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

		ArvoreBuscaBinaria<X> arv = (ArvoreBuscaBinaria<X>)obj;

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
			           copia(raiz.getDir()));
	}

	public ArvoreBuscaBinaria(){}

	public ArvoreBuscaBinaria(ArvoreBuscaBinaria modelo) throws Exception
	{
		if (modelo==null) throw new Exception ("modelo ausente");

		this.raiz = copia(modelo.raiz);
	}

	public Object clone ()
	{
		ArvoreBuscaBinaria ret = null;

		try
		{
			ret = new ArvoreBuscaBinaria<X>(this);
		}
		catch (Exception erro)
		{}

		return ret;
	}
}

