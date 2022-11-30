import java.io.*;
import java.util.*;

public class Compactador
{
    byte[] fileBytes;
    FilaDePrioridades filaDePrioridades = new FilaDePrioridades();
    ArvoreHuffman arvore;



    public void compact(String path) throws Exception {
        readFile(path);
        createPriorityList();
        arvore = new ArvoreHuffman((FilaDePrioridades)filaDePrioridades.clone());
        createCompactFile(path);
    }



    private void createCompactFile(String path){
        try {
            File myObj = new File(path+".compac");
            BitOutputStream out = new BitOutputStream(new BufferedOutputStream(new FileOutputStream(myObj)));
            FileWriter myWriter = new FileWriter(myObj.getAbsolutePath());
            myWriter.write(filaDePrioridades.toString());
            compactFromTree(arvore, out);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing data to file");
            e.printStackTrace();
        }
    }

    private String compactFromTree(ArvoreHuffman a, BitOutputStream out){
        ArrayList<String> result = new ArrayList<>();
        try {
            HashMap<Byte, ArrayList<Integer>> map = a.toHashMap();
            System.out.println(fileBytes.length);
            for(int i = 0; i < fileBytes.length; i++){
                List<Integer> code = map.get(fileBytes[i]);
                for(int j =0; j < code.size(); j++){
                    out.write(code.get(j));
                }


            }
        }catch (Exception e){
            System.out.println(e);
        }

//        for (byte fileByte : this.fileBytes) {
//            result += map.get(fileByte);
//        }
        String resultString = "";
        for (int i = 0; i < result.size(); i++)
            resultString += result.get(i);
        return resultString;
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