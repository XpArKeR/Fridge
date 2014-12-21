/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Core.Helper;

import Model.CoreObject;
import java.util.UUID;

/**
 *
 * @author noldi
 */
public class SQLHelper 
{
    public static String GetSelect(String tableName, String[] fields, String where)
    {
        if (((tableName != null) && (!tableName.isEmpty())) && ((fields != null) && (fields.length > 0)))
        {
            String returnValue = "SELECT ";
            
            for (String field : fields)
            {
                returnValue += field + ", ";
            }
                      
            returnValue = returnValue.substring(0, returnValue.length() - 2);
            
            returnValue += " FROM " + tableName;
            
            if ((where != null) && (!where.isEmpty()))
            {
                returnValue += " " + where;
            }
                
            return returnValue;
        }
        
        return null;
    }
    
    public static String GetUpdate(CoreObject coreObject)
    {
        if ( ((coreObject.DatabaseTableName != null) &&(!coreObject.DatabaseTableName.isEmpty())) && ((coreObject.DatabaseFieldNames != null) &&(coreObject.DatabaseFieldNames.length > 0)) && ((coreObject.DatabaseFieldTypes != null) &&(coreObject.DatabaseFieldTypes.length > 0)) )
        {
            String returnValue = "UPDATE " + coreObject.DatabaseTableName + " SET (";
            
            for (int i = 0; i < coreObject.DatabaseFieldNames.length; i++)
            {
                if (!coreObject.DatabaseFieldNames[i].equals("ID"))
                {
                    returnValue += coreObject.DatabaseFieldNames[i] + "  = " + GetSQLEncodedString(coreObject.GetProperty(coreObject.DatabaseFieldNames[i]), coreObject.DatabaseFieldTypes[i]) + ", ";
                }
            }            
            
            returnValue = returnValue.substring(0, returnValue.length() - 2);
            
            returnValue += ")";
            
            return returnValue;
        } 
        
        return null;
    }
    
    public static String GetInsert(CoreObject coreObject)
    {
        if ( ((coreObject.DatabaseTableName != null) &&(!coreObject.DatabaseTableName.isEmpty())) && ((coreObject.DatabaseFieldNames != null) &&(coreObject.DatabaseFieldNames.length > 0)) && ((coreObject.DatabaseFieldTypes != null) &&(coreObject.DatabaseFieldTypes.length > 0)) )
        {
            String returnValue = "INSERT INTO " + coreObject.DatabaseTableName + " (";
            
            String values = "";
            
            for (int i = 0; i < coreObject.DatabaseFieldNames.length; i++)
            {
                returnValue += coreObject.DatabaseFieldNames[i] + ", ";
                
                if (!coreObject.DatabaseFieldNames[i].equals("ID"))
                {
                    values += GetSQLEncodedString(coreObject.GetProperty(coreObject.DatabaseFieldNames[i]), coreObject.DatabaseFieldTypes[i]) + ", ";
                }
                else
                {
                    coreObject.SetReference(UUID.randomUUID().toString());
                    values +=  "'" + coreObject.Reference + "', ";                    
                }
            }
            
            values = values.substring(0, values.length() - 2);
            returnValue = returnValue.substring(0, returnValue.length() - 2);
            
            returnValue += ") VALUES (" + values + ")";
            
            return returnValue;
        }
        
        return null;
    }
    
    public static String GetSQLEncodedString(Object value)
    {
        return GetSQLEncodedString(value, value.getClass().toString());
    }    
    
    public static String GetSQLEncodedString(Object value, String type)
    {
        String encodedValue = "";
                
        switch(type)
        {
            case "Date":
            case "String":                
                encodedValue = "'" + value + "'";                
                break;           

            case "int":
            case "Integer":
                if (value == null)
                {
                    value = 0;
                }
                
                encodedValue = "" + value;
                break;
        }       
        
        return encodedValue;
    }
}
