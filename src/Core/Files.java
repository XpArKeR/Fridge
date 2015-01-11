package Core;

public class Files 
{    
    public static String BaseDirectory = System.getProperty("user.dir");
    
    public static String DatabaseFile = "Resources\\Data\\Database.sql";    
    
    public static String GetFilePath(String fileName)
    {
        if ((fileName != null) && (!fileName.isEmpty()))
        {
            return BaseDirectory + "\\" + DatabaseFile;
        }
        
        return "";
    }
}
