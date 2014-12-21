package Model.Locale;

import Model.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author noldi
 */
public class Language extends CoreObject
{
    public String ShortName;
    public String Name;     
    private final HashMap<String, String> Tags = new HashMap<>();
    private final HashMap<String, List<String>> RandomTags = new HashMap<>();
    
    public void AddTag (String key, String value)
    {
        this.Tags.put(key, value);        
    }   
    
    public void AddRandomTag (String key, String translations)
    {
        List<String> randomTags = new ArrayList<>();
        
        translations = translations.replace( "{" , "");
        translations = translations.replace( "}" , "");
        
        randomTags.addAll(Arrays.asList(translations.split(";")));
        
        RandomTags.put(key,randomTags);
    }
    
    public String Translate(String tag)
    {
        if (this.RandomTags.containsKey(tag))
        {
            return this.TranslateRandom(tag);
        }
        else if (Tags.containsKey(tag))
        {
            return Tags.get(tag);
        }
        
        return tag;
    }
    
    public String TranslateRandom(String tag)
    {
        if (this.RandomTags.containsKey(tag))
        {
            List<String> translations = this.RandomTags.get(tag);
            
            if (translations.size() > 0)
            {
                Random randomGenerator = new Random();
                
                int randomIndex = randomGenerator.nextInt(translations.size());
                
                return translations.get(randomIndex);
            }            
        }
        
        return tag;
    }
    
    @Override
    public String toString()
    {
        if (!this.Name.isEmpty())
        {
            return this.Name;
        }
        else if (!this.ShortName.isEmpty())
        {
            return this.ShortName;
        }
        else
        {
            return super.toString();
        }
    }
}
