import java.io.PipedReader;

public class Pedaco <X> implements Comparable{
    private X info;
    public int frequencia;

    public Pedaco(X info, int frequencia){
        this.info = info;
        this.frequencia = frequencia;
    }

    public Pedaco(int frequencia){
        this.info = null;
        this.frequencia = frequencia;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public X getInfo() {
        return info;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public void setValor(X info) {
        this.info = info;
    }

    @Override
    public int compareTo(Object o) {
        Pedaco p = (Pedaco) o;
        if (this.frequencia == p.frequencia) return 0;
        if (this.frequencia > p.frequencia) return 1;
        return -1;
    }
}
