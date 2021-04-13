package utils;


import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import exceptions.TestException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class DataProvider {

    private static final String PATH = System.getProperty("user.dir") + "/src/test/resources/datasets/";

    public static Object getData(String dataFile, String dataSet) {
        Map map = (Map) readData(dataFile);
        Object ds;
        try {
            if (map == null) {
                throw new TestException("Failed to read " + dataFile + ".yaml file");
            } else {
                ds = map.get(dataSet);
                if (ds == null) {
                    throw new TestException("Data set: " + dataSet + " doesn't exist");
                }
            }
        } catch (Exception e) {
            throw new TestException("Data set: " + dataSet + " doesn't exist");
        }
        return ds;
    }

    public static Object readData(String dataFile) {
        return readData(PATH, dataFile);
    }

    public static Object readData(String path, String dataFile) {
        YamlReader reader;
        try {
            reader = new YamlReader(new FileReader(path + dataFile + ".yaml"));
        } catch (FileNotFoundException e) {
            throw new TestException("File " + dataFile + ".yaml not found", e);
        }
        Object object;
        try {
            object = reader.read();
        } catch (YamlException e) {
            throw new TestException("Failed to read " + dataFile + ".yaml file", e);
        }
        return object;
    }
}
