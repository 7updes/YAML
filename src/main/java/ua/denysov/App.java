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
        List<String> list = new ArrayList<String>();
        String tmp;
        while((tmp = bufferedReader.readLine()) != null){
            list.add(tmp);
        }
        //yaml file in array  [- 11, - 123, - 51]
        String[] ymlArray = list.toArray(new String[list.size()]);
        String[] render = new String[ymlArray.length-1];
        //delete "- " from each one element, 1 element ignored (--- !int[])
        for (int i = 1; i < ymlArray.length; i++) {
            String[] strings = ymlArray[i].split(" ");
            render[i-1] = strings[1];
        }
        //to int array
        int[] array = new int[render.length];
        for (int i = 0; i < render.length; i++) {
            array[i] = Integer.valueOf(render[i]);
        }
        return array;
    }

    public static int searchNumberWithoutPair(int[] array){
        int result = -1;
        one:for (int i = 0; i < array.length; i++) {
            two:for (int j = 0; j < array.length; j++) {
                //dont check itself
                if(i == j){
                    continue;
                } else if(array[i] != array[j]) {
                    result = array[i];
                    if(j==array.length-1){
                        //if found and j is the last one element - stop iterations
                        break one;
                    }
                //if found same element
                }else break two;
            }
        }
        return result;
    }
}
