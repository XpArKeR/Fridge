package Model.Configuration;

import Model.CoreObject;

/**
 *
 * @author noldi
 */
public class Setting extends CoreObject
{
    public Object Value;
    
    public Setting()
    {
    }
    
    public Setting (String reference)
    {
        this.Reference = reference;
    }
    
    public Setting (Object value)
    {
        this.Value = value;
    }
    
    public Setting (String name, Object value)
    {
        this.Reference = name;
        this.Value = value;
    }
    
    public int GetInt()
    {
        try
        {
            return Integer.parseInt(this.Value.toString());
        }
        catch (NumberFormatException numberFormatException)
        {
            System.out.println(numberFormatException.getMessage());
            return -1337;
        }        
    }
    
    public String GetString()
    {
        return this.Value.toString();
    }
    
    public double GetDouble()
    {
        try
        {
            return Double.valueOf(this.Value.toString());
        }
        catch (NumberFormatException numberFormatException)
        {
            System.out.println(numberFormatException.getMessage());
            return -1337;
        }     
    }
}
