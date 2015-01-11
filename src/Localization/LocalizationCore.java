package Localization;

import Core.Globals;
import Model.Localization.Language;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Properties;

public class LocalizationCore 
{
    private final HashMap<String, Language> _languages = new HashMap<>();
    
    private Language ActiveLanguage;
    private int LanguageCounter = 0;
    
    public void Initialize()
    {
        File resourceDirectoy = new File(Globals.GetBasePath() + "Resources" + File.separator + "lang");
        
        FilenameFilter fileFilter = new FilenameFilter() 
        {
            @Override
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
		if (lowercaseName.endsWith(".lang")) {
                    return true;
		} 
                else 
                {
                    return false;
		}
            }
	};        
        
        File[] files = resourceDirectoy.listFiles(fileFilter);
        
        if (files == null)
        {
            System.out.println("Searching in : " + resourceDirectoy.toString());
        
            return;
        }
        
        for (File file : files)
        {
            try
            {      
                Properties properties = new Properties();
                properties.load(new FileInputStream(file.getCanonicalFile()));
                
                Language lang = new Language();
                
                lang.Name = properties.getProperty("name","");
                
                if (lang.Name.contentEquals(""))
                {
                    continue;
                }
                
                lang.ShortName = properties.getProperty("shortname","");
                lang.Reference = "" + this.LanguageCounter;
                
                for(String key : properties.stringPropertyNames()) 
                {
                    if (!key.contentEquals("Name") && !key.contentEquals("ShortName"))
                    {
                        String translationString = properties.getProperty(key);
                        
                        if (!translationString.trim().isEmpty())
                        {
                            if (translationString.startsWith("{") && translationString.endsWith("}") && translationString.contains(";"))
                            {
                                lang.AddRandomTag(key, translationString);
                            }
                            else
                            {
                                lang.AddTag(key, translationString);
                            }
                        }
                    }
                }
                
                this._languages.put(lang.ShortName, lang);
                
                this.LanguageCounter++;                
            }
            catch(Exception exception)
            {
                System.out.println(exception.getMessage());
            }
        }
    }
    
    public String Translate(String tag)
    {
        if (this.GetActiveLanguage() != null)
        {
            return this.ActiveLanguage.Translate(tag);
        }  
        else
        {
            return "No Language found!";
        }
    }
    
//    public String TranslateRandom(String tag)
//    {
//        if (this.GetActiveLanguage() != null)
//        {
//            return this.ActiveLanguage.TranslateRandom(tag);
//        }  
//        else
//        {
//            return "No Language found!";
//        }
//    }
    
    public Language GetActiveLanguage()
    {
        if (this.ActiveLanguage == null)
        {
            if (this._languages.size() > 0)
            {
                this.ActiveLanguage = (Language)this._languages.values().toArray()[0];
            }
        }
        
        return this.ActiveLanguage;
    }
}
