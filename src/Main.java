import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public static void main(String[] args){
        new Front();
    }



}


//public class Main {
//    public static void main(String[] args) {
//
//        Compactador compactador = new Compactador();
//        try {
//           compactador.compact("/home/davicostadeoliveira/COTUCA/Compactador-main/src/sun.png");
//           System.out.println("Arquivo Compactador");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        Descompactador descompactador = new Descompactador();
//        try {
//            descompactador.extract("/home/davicostadeoliveira/COTUCA/Compactador-main/src/sun.png.compac");
//        } catch (Exception e) {
//            throw new RuntimeException((e));
//        }
//
//
//    }
//}