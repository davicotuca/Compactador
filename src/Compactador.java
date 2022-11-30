import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Compactador
{
    byte[] fileBytes;
    FilaDePrioridades filaDePrioridades = new FilaDePrioridades();
    Arvore arvore;

    public void compact(String path) throws Exception {
        readFile(path);
        createPriorityList();
        arvore = new Arvore((FilaDePrioridades)filaDePrioridades.clone());
        createCompactFile(path);
    }

    private void createCompactFile(String path){
        try {
            //File myObj = new File(path+".txt");

            //ByteOutputStream file = new ByteOutputStream(myObj);
            //file.write(compactFromTree(arvore));
            //file.close();

            //FileOutputStream outputStream = new FileOutputStream(path+".compac");
            //outputStream.write(compactFromTree(arvore));

            File myObj = new File(path+".compac");
            //outputStream.write(filaDePrioridades.toString());
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
        ArrayList<String> result1 = new ArrayList<>();
        HashMap<Byte, String>  map = a.toHashMap();
        for (byte fileByte : this.fileBytes) {
            result1.add(map.get(fileByte));
        }

        String result = result1.stream().collect(Collectors.joining(""));;

        //byte[] bytes = new byte[result.length()];

        //for (int i = 0; i < result.length(); i++) {
        //    bytes[i] = (byte) result.charAt(i);
        //}

        //byte[] bytes = new byte[result1.size()];
        //for (int i = 0; i < result1.size(); i++) {
        //    bytes[i] = (byte) Integer.parseInt(result1.get(i));
        //}

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