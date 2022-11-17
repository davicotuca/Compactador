import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Compactador
{
    byte[] fileBytes;
    FilaDePrioridades filaDePrioridades = new FilaDePrioridades();
    Arvore arvore;

    public void compact(String path) throws Exception {
        readFile(path);
        createPriorityList();
        System.out.println(filaDePrioridades);
        arvore = new Arvore((FilaDePrioridades)filaDePrioridades.clone());
        System.out.println(arvore);
        createCompactFile(path);
    }

    private void createCompactFile(String path){
        try {
            File myObj = new File(path+".compac");
            FileWriter myWriter = new FileWriter(myObj.getAbsolutePath());
            myWriter.write(filaDePrioridades.toString());
            myWriter.write(compactFromTree(arvore));
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing data to file");
            e.printStackTrace();
        }
    }

    private String compactFromTree(Arvore a){
        String result = "";
        Map<Byte, String>  map = a.toHashMap();
        for (byte fileByte : this.fileBytes) {
            result += map.get(fileByte);
        }
        return result;
    }

    private void readFile(String path) throws  Exception{
        File newFIle = new File(path);
        RandomAccessFile raf = new RandomAccessFile(newFIle, "r");
        raf.seek(0);
        this.fileBytes = new byte[(int)raf.length()];
        raf.read(this.fileBytes);
        raf.close();
    }

    private void createPriorityList() {
        Map<Byte, Integer> bytesFreq = new HashMap<>();

        for (byte fileByte : this.fileBytes) {
            if(!bytesFreq.containsKey(fileByte)) {
                bytesFreq.put(fileByte, 1);

            } else {
                bytesFreq.put(fileByte,bytesFreq.get(fileByte) + 1);
            }
        }

        for (byte key : bytesFreq.keySet()) {
            filaDePrioridades.queue(new No(key, bytesFreq.get(key)));
        }
    }
}