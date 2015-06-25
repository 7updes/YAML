package ua.denysov;


import org.ho.yaml.YamlEncoder;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Alex on 23.06.2015.
 */
public class App {

    public static void main(String[] args) throws IOException {
        int[] array = {23,11,4,61,11,4,98,23,98,77,123,88,0,14,123,0,14,88,15,15,61};

        System.out.println(searchNumberWithoutPair(parser(createFileWithArray(array))));

    }
    //
    public static File createFileWithArray(int[] array) throws IOException {

        File file;
        OutputStream out = null;
        YamlEncoder enc = null;
        try {
            file = new File("file.yml");
            if (!file.exists()) {
                file.createNewFile();
            }
            out = new FileOutputStream(file);
            enc = new YamlEncoder(out);
            enc.writeObject(array);

        } finally {
            enc.close();
            out.close();
        }
        return file;
    }

    public static int[] parser(File file) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        List<Integer> list = new ArrayList<Integer>();
        String tmp;
        while((tmp = bufferedReader.readLine()) != null){
            //add second part of strings to list, only integer
            try {
                list.add(Integer.valueOf(tmp.split(" ")[1]));
            }catch (NumberFormatException e){}

        }
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
                array[i] = Integer.valueOf(list.get(i));
        }
        return array;
    }

    public static int searchNumberWithoutPair(int[] array){
        int result = -1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                //dont check itself
                if(i == j){
                    continue;
                } else if(array[i] != array[j]) {
                    result = array[i];
                    if(j==array.length-1){
                        //if found and j is the last one element - stop iterations
                        return result;
                    }
                    //if found same element
                }else break;
            }
        }
        if(result < 0){
            System.out.println("No number without pair");
        }
        return result;
    }
}
