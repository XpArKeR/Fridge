package Core;

import Localization.LocalizationCore;
import java.io.File;
import java.sql.Date;

/**
 *
 * @author XpArKeR
 */
public class Globals 
{    
    public static double DatabaseVersion = 1.0;
    
    public static LocalizationCore LanguageEngine = new LocalizationCore();
    
    public static Database Database = new Database();
        
    private static String _FilePath = "";
    
    public static boolean IsNumeric(String value)
    {
        try
        {
            int test = Integer.parseInt(value);            
            return true;
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
        }
        return false;
    }
    
    public static Date ConvertToDate(String text)
    {
        try
        {
            if (text.indexOf("-") > 2)
            {
                return java.sql.Date.valueOf(text);
            }
            else if (text.indexOf(".") > -1)
            {
                String[] arrPieces = text.split("\\.");
                if (arrPieces.length == 3)
                {
                    return java.sql.Date.valueOf(arrPieces[2] + "-" + arrPieces[1] + "-" + arrPieces[0]);
                }
            }
        }
        catch (Exception exception)
        {
            
        }
        
        return null;
    }
    
    public static String GetBasePath()
    {
        if (_FilePath == null || Globals._FilePath.trim().contentEquals(""))
        {
            try
            {
                String path = new File(".").getAbsolutePath();
                _FilePath = path.substring(0, path.length() - 2) + File.separator;
            }
            catch (Exception exception)
            {
                return "";
            }
        }
        
        return _FilePath;
    }
}
