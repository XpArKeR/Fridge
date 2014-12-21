/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Article;

import Core.Globals;
import Model.CoreObject;

/**
 *
 * @author noldi
 */
public class Barcode extends CoreObject
{
    public String Barcode;
    public Article ParentArticle;
    
    public Barcode()
    {
        this.DatabaseTableName = "Barcodes";
        this.DatabaseFieldNames = new String[] {"ID", "ArticleID", "Barcode"};
        this.DatabaseFieldTypes = new String[] {"String", "String", "String"};
    }
    
    @Override
    public void SetProperty(String propertyName, Object value)
    {
        switch (propertyName)
        {
            case "Barcode":
                this.Barcode = value.toString();
                break;
                
            case "ArticleID":
                if (this.ParentArticle == null)
                {
                    this.ParentArticle = new Article();
                }
                
                this.ParentArticle.Load(value.toString());
                
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
            case "Barcode":
                return this.Barcode;
                
            case "ArticleID":
                if (this.ParentArticle != null)
                {
                    return this.ParentArticle.Reference;
                }
            
            default:
                return super.GetProperty(property);                
        }
    }
    
    @Override
    public String toString()
    {
        if ((this.Barcode != null) && (!this.Barcode.isEmpty()))
        {
            return this.Barcode;
        }
        
        return super.toString();
    }
    
    public Boolean Exists(String reference)
    {
        return this.Exists(reference, false);
    }
    
    public Boolean Exists(String reference, Boolean loadIfFound)
    {
        Boolean exists = Globals.Database.Exists(this, reference, "Barcode", loadIfFound);
        
        if ((exists) && (loadIfFound))
        {
            if ((this.ParentArticle != null) && (!this.ParentArticle.Codes.contains(this)))
            {
                this.ParentArticle.Codes.add(this);
            }
        }
        
        return exists;
    }
}
