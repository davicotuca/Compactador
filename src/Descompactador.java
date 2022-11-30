import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Descompactador {
    String binario;
    FilaDePrioridades filaDePrioridades = new FilaDePrioridades();
    ArvoreHuffman arvore;

    String extension;
    String fileName;

    public void extract(String compactedPath) throws Exception {
        String path = compactedPath.replace(".compac","");
        String[] fileComponents = path.split("\\.");

        fileName = fileComponents[0];
        extension= "." + fileComponents[1];

        readFile(compactedPath);
        arvore = new ArvoreHuffman((FilaDePrioridades)filaDePrioridades.clone());
        createExtractedFile();
    }

    private void readFile(String path) throws IOException {
        String content = Files.readString(Paths.get(path));
        List<String> separatedContent = Arrays.asList(content.split(" "));
        binario = separatedContent.get(separatedContent.size() - 1);

        Map<Integer, Integer> bytesFreq = new HashMap<>();
        for (int i = 0; i < separatedContent.size() - 1; i+=2) {
            bytesFreq.put(Integer.valueOf(separatedContent.get(i)), Integer.valueOf(separatedContent.get(i+1)));
        }

        createPriorityList(bytesFreq);
    }

    private void createPriorityList(Map<Integer, Integer> bytesFreq) {
        bytesFreq.forEach((key, value) -> filaDePrioridades.queue(new No(key, value)));
    }

    private void createExtractedFile() throws Exception{
        byte[] bytes = extractBytesFromTree();
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName+"(1)"+extension);
            outputStream.write(bytes);
        } catch (IOException e) {
            System.out.println("An error occurred while writing data to file");
            e.printStackTrace();
        }
    }

    private byte[] extractBytesFromTree() {
        String binarioAlt = this.binario;
        ArrayList<Comparable> bytesAlt = new ArrayList<>();
        while (binarioAlt != "") {
            ArrayList<Comparable> resultado = arvore.findByte(binarioAlt);
            binarioAlt = binarioAlt.substring((Integer)resultado.get(0));
            bytesAlt.add(resultado.get(1));
        }

        byte[] bytes = new byte[bytesAlt.size()];
        for (int i = 0; i < bytesAlt.size(); i++) {
            bytes[i] = (byte) Integer.parseInt(bytesAlt.get(i).toString());
        }

        return bytes;
    }

}
