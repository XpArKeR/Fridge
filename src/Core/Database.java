/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Core;

import Core.Helper.SQLHelper;
import Model.Article.Category;
import Model.Article.Shop;
import Model.CoreObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author noldi
 */
public class Database 
{
    public String Server;
    public String DatabaseName;
    public String Username;
    public String Password;
    
    private String GetConnectionString()
    {
        return "jdbc:mysql://" + this.Server + "/" + this.DatabaseName;
    }
    
    public Boolean Initialize()
    {
        Boolean success = true;
        
        if (this.Server.isEmpty() || this.DatabaseName.isEmpty() || this.Username.isEmpty())
        {
            success = false;
        }
        else
        {
            success = this.LoadClass();
            
            if (success)
            {
                System.out.println("MySQL JDBC Driver Registered!");
                
                success = this.TestConnection();
            }
        }
        
        return success;
    }
    
    private Boolean LoadClass()
    {
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            return true;
	} 
        catch (ClassNotFoundException e) 
        {
            System.out.println("Where is your MySQL JDBC Driver?");
            System.out.println(e.getMessage());
            return false;
	}
    }
    
    private Boolean TestConnection()
    {
        Boolean success = true;
        
        Connection connection = this.GetConnection();
	 
	if (connection == null) 
        {
            System.out.println("Failed to make connection!");
            success = false;
	}
        else
        {
            try 
            {
                connection.close();
                
                success = true;
                
                connection = null;
            } 
            catch (SQLException e) 
            {
                connection = null;
            }
        }
        
        return success;
    }
    
    public Connection GetConnection()
    {
        Connection connection = null;
        
        try 
        {
            connection = DriverManager.getConnection( this.GetConnectionString(), this.Username, this.Password); 
        } 
        catch (SQLException e) 
        {
            System.out.println("Connection Failed! Check output console");
            System.out.println(e.getMessage());            
	}
        
        return connection;
    }
    
    public boolean ExecuteStatement(String query)
    {
        try 
        {
            Statement statement = this.GetConnection().createStatement(Statement.CLOSE_ALL_RESULTS,Statement.RETURN_GENERATED_KEYS);
            statement.setQueryTimeout(3);
            System.out.println("Save: " + statement.execute(query));
            return true;
        }
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
            return false;
        }

    }
    
    public ResultSet Query(String query)
    {
        Connection connection = this.GetConnection();
        if (connection != null)
        { 
            try 
            {
                Statement Stmt = connection.createStatement();
                Stmt.setQueryTimeout(3);
                ResultSet rs = Stmt.executeQuery(query);
                return rs;
            }
            catch (SQLException e)  
            {
                System.out.println(e.getMessage());
            }
        }
        
        return null;
    }
    
    public Boolean Load(CoreObject coreObject, String reference)
    {
        Boolean success = false;
        
        String query = SQLHelper.GetSelect(coreObject.DatabaseTableName, coreObject.DatabaseFieldNames, " WHERE ID = '" + reference + "'");
        
        if (query != null)
        {
            ResultSet results = this.Query(query);
            
            try 
            {
                while (results.next()) 
                {       
                    for (String field : coreObject.DatabaseFieldNames)
                    {
                        coreObject.SetProperty(field, results.getObject(field));
                    }                
                    
                    success = true;
                }                
            }
            catch (SQLException e)  
            {
                System.out.println(e.getMessage());
            }
        }
        
        return success;
    }
    
    public Boolean Save(CoreObject coreObject)
    {
        Boolean success;
        
        if ((coreObject.Reference == null) || (coreObject.Reference.isEmpty()))
        {
            success = this.Insert(coreObject);
        }
        else
        {
            success = this.Update(coreObject);
        }
        
        return success;        
    }
    
    public Boolean Update(CoreObject coreObject)
    {
        Boolean success = false;
        
        String insert = SQLHelper.GetUpdate(coreObject);
        
        if (insert != null)
        {
            success = ExecuteStatement(insert);
        }
        
        return success;
    }
    
    public Boolean Insert(CoreObject coreObject)
    {
        Boolean success = false;
        
        String insert = SQLHelper.GetInsert(coreObject);
        
        if (insert != null)
        {
            success = ExecuteStatement(insert);
        }
        
        return success;
    }
    
    public Boolean Exists(CoreObject coreObject, String reference, String fieldToCheck)
    {
        return this.Exists(coreObject, reference, fieldToCheck, false);
    }
    
    public Boolean Exists(CoreObject coreObject, String reference, String fieldToCheck, Boolean loadIfFound)
    {
        Boolean exists = false;
        
        String query = SQLHelper.GetSelect(coreObject.DatabaseTableName, coreObject.DatabaseFieldNames, " WHERE " + fieldToCheck + " = '" + reference + "'");
        
        if (query != null)
        {
            ResultSet resultSet = this.Query(query);
            
            if (resultSet == null)
            {
                return false;
            }
            
            try
            {
                if (resultSet.next())
                {
                    if (loadIfFound)
                    {
                        for (String field : coreObject.DatabaseFieldNames)
                        {
                            coreObject.SetProperty(field, resultSet.getObject(field));
                        }
                    }
                    
                    exists = true;
                }                
            }
            catch (SQLException exception)
            {
                
            }
        }
        
        return exists;
    }
    
    
    public <T> List<T> LoadAll(Class<T> type, CoreObject loadFor)
    {
        List<T> returnValue = new ArrayList<>();
        try
        {
            T instance = type.newInstance();
            
            if (CoreObject.class.isAssignableFrom(instance.getClass()))
            {
                CoreObject coreObject = (CoreObject)instance;
                
                String query = "SELECT * from " + coreObject.DatabaseTableName;
                
                if (loadFor != null)
                {
                    query += " where " + coreObject.DatabaseRestrictionField + " = '" + loadFor.Reference + "'";
                }

                ResultSet resultSet = this.Query(query);

                try
                {
                    while (resultSet.next())
                    {          
                        CoreObject newItem = (CoreObject)type.newInstance();
                        
                        for (String field : coreObject.DatabaseFieldNames)
                        {
                            newItem.SetProperty(field, resultSet.getObject(field));
                        }
                        
                        returnValue.add((T)newItem);
                    }

                }
                catch (SQLException exception)
                {

                }
            }
        }
        catch(InstantiationException | IllegalAccessException exception)
        {
            
        }
        
        return returnValue; 
    }   
    
    public <T> List<T> LoadAll(Class<T> type)
    {
        return this.LoadAll(type, null);
    }
}
