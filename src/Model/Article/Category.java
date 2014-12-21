package Model.Article;

import Model.CoreObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author noldi
 */
public class Category extends CoreObject
{
    public String Name;
    public String Description;
    
    public Category()
    {
        this.DatabaseTableName = "Categories";
        this.DatabaseFieldNames = new String[] {"ID", "Name", "Description"};
        this.DatabaseFieldTypes = new String[] {"String", "String", "String"};
    }
    
    @Override
    public void SetProperty(String propertyName, Object value)
    {
        switch (propertyName)
        {
            case "Name":
                this.Name = value.toString();
                break;
                
            case "Description":
                this.Description = value.toString();
                break;
                
            default:
                super.SetProperty(propertyName,value);
        }
    }
    
    @Override
    public Object GetProperty(String property)
    {
        switch (property)
        {
            case "Name":
                return this.Name;
                
            case "Description":
                return this.Description;            
                
            default:
                return super.GetProperty(property);                
        }
    }
    
    @Override
    public String toString()
    {
        if ((this.Name != null) && (!this.Name.isEmpty()))
        {
            return this.Name;
        }
        
        return super.toString();
    }
}
