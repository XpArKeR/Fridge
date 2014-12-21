package Model.Article;

import Model.CoreObject;

public class Shop extends CoreObject
{
    public String Name;
    public String Description;
    
    public Shop()
    {
        this.DatabaseTableName = "Shops";
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
}
