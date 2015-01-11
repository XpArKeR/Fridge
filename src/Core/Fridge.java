package Core;

import Model.Article.Shop;
import Views.MainView;

public class Fridge 
{
    public static void main(String[] args) 
    {
        Settings.Initialize();
        
        Globals.LanguageEngine.Initialize();
                
        try
        {
            Globals.Database.Server = Settings.GetPropertyValue("DatabaseServer").toString();
            Globals.Database.DatabaseName = Settings.GetPropertyValue("DatabaseName").toString();
            Globals.Database.Username = Settings.GetPropertyValue("DatabaseUser").toString();
            Globals.Database.Password = Settings.GetPropertyValue("DatabasePassword").toString();
        }
        catch (Exception exception)
        {
            System.out.println(Globals.LanguageEngine.Translate("ERROR_CRITICAL_DATABASE") + " Settings not configured");
        }
        
        if (Globals.Database.Initialize())
        {
            System.out.println("Connection successfully established");            
                        
            MainView mainView = new MainView();
            
            mainView.LoadSettings();
            mainView.LoadLocalization();
            
            mainView.setVisible(true);            
        }        
    }    
    
    public static void Log(String message)
    {
        System.out.println(message);
    }
}
