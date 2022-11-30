public class Info implements Comparable<Info> {

    private Byte b;
    private String codigo;

    public Info(byte b, String c){
        this.b = b;
        this.codigo = c;
    }

    public Byte getB() {
        return b;
    }

    public String getCodigo() {
        return codigo;
    }

    @Override
    public int compareTo(Info o) {
        if(b.intValue() == o.getB().intValue())
            return 0;
        if(b.intValue() > o.getB().intValue())
            return 1;
        else
            return -1;
    }
}
