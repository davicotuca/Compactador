import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;

public class Front {
    public JFrame frame;
    public Front(){
        frame = new JFrame("Test");

        frame.setVisible(true);
        frame.setSize(500,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);
        JButton button = new JButton("Compactar");
        panel.add(button);
        button.addActionListener (new Compactar());

        JButton button2 = new JButton("Descompactar");
        panel.add(button2);
        button2.addActionListener (new Descompactar());
    }

    class Compactar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            final JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(frame);

            if(returnVal == JFileChooser.APPROVE_OPTION){
                File filaPath = fc.getSelectedFile();
                Compactador compactador = new Compactador();
                try {
                   compactador.compact(filaPath.toString());
                    JOptionPane.showMessageDialog(frame,
                        "Arquivo compactado!!");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    class Descompactar implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            fc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos compactados", "compac");
            fc.addChoosableFileFilter(filter);
            int returnVal = fc.showOpenDialog(frame);

            if(returnVal == JFileChooser.APPROVE_OPTION){
                File filaPath = fc.getSelectedFile();
                Descompactador descompactador = new Descompactador();
                try {
                    descompactador.extract(filaPath.toString());
                    JOptionPane.showMessageDialog(frame,
                        "Arquivo descompactado!!");
                } catch (Exception ex) {
                    throw new RuntimeException((ex));
                }
            }
        }
    }
}
