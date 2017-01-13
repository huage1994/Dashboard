package work.utils;


import java.util.HashMap;
import java.util.Map;


/**
 * Created by I320997 on 1/10/2017.
 */
public class TransferData {
    public static Map<String,Object> trans = new HashMap<String,Object>();

    public static Object getTrans(String key){
        return trans.get(key);
    }

    public static void setTrans(String key,Object ob){
        trans.put(key,ob);
    }
}
