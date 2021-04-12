package utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class configUtil {
    private static Map<String,String> configMap;

    static{
        configMap = new HashMap<>();
        readConfig();
    }

    public static void readConfig(){
        File f = new File("./config.properties");
        try{
            Scanner sc = new Scanner(f);
            // read config settings
            while(sc.hasNextLine()){

                String line = sc.nextLine();
                if(line.startsWith("#")){
                    continue;
                }
                String[] arr = line.split("=");
                if(arr.length == 2){
                    configMap.put(arr[0].trim(),arr[1].trim());
                }

            }
        }catch(IOException e){
            // printUtil.printRedBackground("Cannot find config file");
        }
    }

    /**
     * @param key
     * @param defaultValue If value doesn't exist, then use default value.
     * @return the corespond value
     */
    public static String getConfig(String key, String defaultValue){
        return configMap.get(key) == null ? defaultValue: configMap.get(key);
    }

    /**
     * 
     * @param key
     * @param defaultValue
     * @return integer value of config value
     */
    public static int getConfigInt(String key, int defaultValue){
        String value = configMap.get(key);
        if(value == null){
            return defaultValue;
        }
        else{
            return Integer.parseInt(value);
        }
    }

    public static int getConfigInt(String key){
        return Integer.parseInt(configMap.get(key));
    }
}
