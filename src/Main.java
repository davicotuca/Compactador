public class Main {
    public static void main(String[] args) {

        Compactador compactador = new Compactador();
        try {
           compactador.compact("C:\\Users\\natas\\Documents\\COTUCA\\Repositorios\\Compactador\\src\\clipart940165.png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Descompactador descompactador = new Descompactador();
        try {
            descompactador.extract("C:\\Users\\natas\\Documents\\COTUCA\\Repositorios\\Compactador\\src\\clipart940165.png.compac");
        } catch (Exception e) {
            throw new RuntimeException((e));
        }


    }
}