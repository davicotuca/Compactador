import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        FilaDePrioridades filaPrioriades = new FilaDePrioridades();
//
//
//        No<Byte> p1 = new No<Byte>((byte)'E', 1);
//
//        No<Byte> p2 = new No<Byte>((byte)'e', 8);
//        No<Byte> p3 = new No<Byte>((byte)'r', 2);
//        No<Byte> p4 = new No<Byte>((byte)'i', 1);
//        No<Byte> p5 = new No<Byte>((byte)' ', 4);
//        No<Byte> p6 = new No<Byte>((byte)'y', 1);
//        No<Byte> p7 = new No<Byte>((byte)'s', 2);
//        No<Byte> p8 = new No<Byte>((byte)'n', 2);
//        No<Byte> p9 = new No<Byte>((byte)'a', 2);
//        No<Byte> p10 = new No<Byte>((byte)'l', 1);
//        No<Byte> p11 = new No<Byte>((byte)'k', 1);
//        No<Byte> p12 = new No<Byte>((byte)'.', 1);
//
//        filaPrioriades.queue(p1);
//        filaPrioriades.queue(p2);
//        filaPrioriades.queue(p3);
//        filaPrioriades.queue(p4);
//        filaPrioriades.queue(p5);
//        filaPrioriades.queue(p6);
//        filaPrioriades.queue(p7);
//        filaPrioriades.queue(p8);
//        filaPrioriades.queue(p9);
//        filaPrioriades.queue(p10);
//        filaPrioriades.queue(p11);
//        filaPrioriades.queue(p12);
//
//        System.out.println(filaPrioriades);
//
//        Arvore a = new Arvore(filaPrioriades);
//
//        Map<Byte, String>  map = a.toHashMap();
//
//        System.out.println(map);

        Compactador compactador = new Compactador();
        try {
            compactador.compact("C:\\Davi\\Cotuca\\Andre\\Compactador_Descompactador\\out\\production\\Compactador_Descompactador/file.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        /*while (filaPrioriades.size() >= 2){
            Arvore.No no = new Arvore.No(new No(0));

            try {
                No info1 = filaPrioriades.pop();
                No info2 = filaPrioriades.pop();
                raiz.inclua(info1);
                raiz.inclua(info2);
            } catch (Exception e) {}// não vai dar erro, sabemos que tem pelo menos 2 elementos


        }*/

    }
}