import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Descompactador {
    String binario;
    FilaDePrioridades filaDePrioridades = new FilaDePrioridades();
    Arvore arvore;
    String extension;
    String fileName;

    public void decompact(String path) throws Exception {
        String path1 = path.replace(".compac","");
        String[] fileComponents = path1.split("\\.");
        fileName = fileComponents[0];
        extension= "." + fileComponents[1];
        System.out.println(extension);
        readFile(path);
        //createPriorityList();
        arvore = new Arvore((FilaDePrioridades)filaDePrioridades.clone());
        System.out.println(arvore);
        createDecompactedFile();
    }

    private void readFile(String path) throws IOException {
        // read file from path
        String content = Files.readString(Paths.get(path));
        // separate file according to space
        List<String> separatedContent = Arrays.asList(content.split(" "));
        //get the binary (last section)
        binario = separatedContent.get(separatedContent.size() - 1);

        //map the byte frequency and to variable bytesFreq
        Map<Integer, Integer> bytesFreq = new HashMap<>();
        for (int i = 0; i < separatedContent.size() - 1; i+=2) {
            bytesFreq.put(Integer.valueOf(separatedContent.get(i)), Integer.valueOf(separatedContent.get(i+1)));
        }

        // montar a fila de prioridade
        createPriorityList(bytesFreq);
    }

    private void createPriorityList(Map<Integer, Integer> bytesFreq) {
        bytesFreq.forEach((key, value) -> filaDePrioridades.queue(new No(key, value)));
    }

    private void createDecompactedFile() throws Exception{
        byte[] bytes = decompactFromTree();
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName+"(1)"+extension);
            outputStream.write(bytes);
        } catch (IOException e) {
            System.out.println("An error occurred while writing data to file");
            e.printStackTrace();
        }
    }

    private byte[] decompactFromTree() throws Exception {
        String binarioAlt = this.binario;
        ArrayList<Comparable> bytesAlt = new ArrayList<>();
        while (binarioAlt != "") {
            ArrayList<Comparable> resultado = arvore.encontraByte(binarioAlt);
                binarioAlt = binarioAlt.substring((Integer)resultado.get(0)+1, binarioAlt.length());
                bytesAlt.add(resultado.get(1));
        }

        byte[] bytes = new byte[bytesAlt.size()];
        for (int i = 0; i < bytesAlt.size(); i++) {
            bytes[i] = (byte) Integer.parseInt(bytesAlt.get(i).toString());
        }

        return bytes;
    }

}
