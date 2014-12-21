package Core;

import Model.Configuration.Setting;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 *
 * @author noldi
 */
public class Settings 
{
    private final static String SettingsFile = "Fridge.conf";
    private static HashMap<String,Setting> Settings;
    
    public static void Initialize()
    {
        File file = new File(SettingsFile);
        
        if (!file.exists())
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException exception)
            {
                System.out.println("Error when creating settings file: " + exception.getMessage());
            }
        }
        
        if (Settings == null)
        {
            Settings = new HashMap<>();
        }

        try
        {
            Properties properties = new Properties();

            properties.load(new FileInputStream(SettingsFile));

            for(String key : properties.stringPropertyNames()) 
            {
                Settings.put(key,new Setting(key, properties.getProperty(key)));
            }
        }
        catch (IOException exception)
        {
            System.out.println(exception.getMessage());
        }                       
    }
    
    public static void StoreSettings()
    {
        try 
        {
            Properties properties = new Properties();
            
            for (Setting setting : Settings.values())
            {
                properties.setProperty(setting.Reference, setting.Value.toString());
            }
            
            File settingsFile = new File(SettingsFile);
            OutputStream outputStream = new FileOutputStream( settingsFile );
            properties.store(outputStream, "Fridge Config file. Please do not modify unless you know what you are doing.");
        }
        catch (IOException e) 
        {
        }
    }
    
    public static Setting GetProperty(String name)
    {
        if (Settings.containsKey(name))
        {
            return Settings.get(name);
        }
    
        return null;
    }
    
    public static Object GetPropertyValue(String name)
    {
        Setting setting = GetProperty(name);
        
        if (setting != null)
        {
            return setting.Value;
        }
    
        return null;
    }
    
    public static void SetProperty(Setting setting)
    {        
        Settings.put(setting.Reference, setting);
    }
    
    public static void SetProperty(String name, Object value)
    {
        Settings.put(name, new Setting(name,value));
    }    
}
