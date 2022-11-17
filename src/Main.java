import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //Compactador compactador = new Compactador();
        //try {
        //   compactador.compact("C:\\Users\\natas\\Documents\\COTUCA\\Repositorios\\Compactador\\src\\file.txt");
        //} catch (Exception e) {
        //    throw new RuntimeException(e);
        //}

        Descompactador descompactador = new Descompactador();
        try {
            descompactador.decompact("C:\\Users\\natas\\Documents\\COTUCA\\Repositorios\\Compactador\\src\\file.txt.compac");
        } catch (Exception e) {
            throw new RuntimeException((e));
        }


    }
}