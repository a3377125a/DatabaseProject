package Utils;

import DAO.LogDAO;
import Entity.Log;

public class LogUtil {
    public static boolean log(String text, int operatorId){
        Log log = new Log(text,operatorId);
        return LogDAO.insertLog(log);
    }
}
