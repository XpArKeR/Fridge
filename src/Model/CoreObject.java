package Model;

import Core.Globals;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author noldi
 */
public class CoreObject extends Object
{
    public String Reference;
   
    public String DatabaseTableName = "";
    public String DatabaseRestrictionField = "";
    public String[] DatabaseFieldNames = {"ID"};
    public String[] DatabaseFieldTypes = {"String"};
            
    public String GetReference()
    {
        return this.Reference;
    }
    
    public void SetReference(String reference)
    {
        this.Reference = reference;        
    }
    
    public void SetProperty(String property, Object value)
    {
        switch (property)
        {
            case "ID":
            case "Reference":
                this.Reference = value.toString();
        }
    }
    
    public Object GetProperty(String property)
    {
        switch (property)
        {
            case "ID":
            case "Reference":
                return this.Reference;
        }
        
        return null;
    }
    
    @Override
    public String toString()
    {
        if (!this.Reference.isEmpty())
        {
            return this.Reference;
        }
        
        return super.toString();
    }
    
    public Boolean Load(String reference)
    {
        Boolean loadSuccessful = false;
        
        if (!this.DatabaseTableName.isEmpty())
        {
            loadSuccessful = Globals.Database.Load(this, reference);
            
            if (loadSuccessful)
            {
                this.Reference = reference;
            }
        }
        
        return loadSuccessful;
    }
    
    public Boolean Save()
    {
        Boolean saveSuccess = false;
        
        if (!this.DatabaseTableName.isEmpty())
        {
            saveSuccess = Globals.Database.Save(this);             
        }
        
        return saveSuccess;
    }    
}
