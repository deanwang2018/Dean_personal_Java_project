package util;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author wangdian
 * @package util
 * @date 2020/11/9
 * @time 9:51
 */
public class FakerUtils {


    public static Object[][] getTestData (String fileName){
        String projectRoot = new File("").getAbsolutePath();
        String charset = "utf-8";
        ArrayList<String[]> list = new ArrayList<>();
        try (CSVReader csvReader = new CSVReaderBuilder(new BufferedReader(new InputStreamReader(new FileInputStream(new File(projectRoot+fileName)), charset))).build()) {
            csvReader.skip(1);
            Iterator<String[]> iterator = csvReader.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Object data[][] = new Object[list.size()][];
        for(int i=0;i<list.size();i++)
        {
            data[i]=list.get(i);
        }
        return data;
    }
}