/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Article;

import Model.CoreObject;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public class ArticleLine extends CoreObject
{
    public String Description;
    public Article Article;
    public double Weight;
    public int Amount;
    public Shop Shop;
    public Date ExpiryDate;
    public Date DateAdded;
    
    public ArticleLine()
    {
        this.DatabaseTableName = "ArticleLines";
        this.DatabaseRestrictionField = "ArticleID";
        this.DatabaseFieldNames = new String[]{"ID", "ArticleID","Description","Amount","Weight","DateAdded","ExpiryDate","ShopID"};
        this.DatabaseFieldTypes = new String[]{"String","String","String","int","double","Date","Date","int"};
    }
    
    public static Comparator<ArticleLine> GetComparator()
    {
         return new Comparator<ArticleLine>() { public int compare(ArticleLine lhs, ArticleLine rhs) { return lhs.Description.compareTo(rhs.Description); } };
    }
    
    @Override
    public void SetProperty(String property, Object value)
    {
        switch (property)
        {
            case "Amount":
                this.Amount = (int)value;
                break;
            case "ArticleID":
                if (this.Article == null)
                {
                    this.Article = new Article();
                }
                
                this.Article.Load((String)value);
                
                break;
            case "Weight":
                this.Weight = Double.valueOf(value.toString());
                break;
            case "ExpiryDate":
                this.ExpiryDate = (Date)value;
                break;
            case "DateAdded":
                this.DateAdded = (Date)value;
                break;
            case "ShopID":
                if (this.Shop == null)
                {
                    this.Shop = new Shop();
                }
                
                this.Shop.Load((String)value);
                break;
            default: 
                super.SetProperty(property, value);
                break;
        }
    }
    
    @Override
    public Object GetProperty(String property)
    {
        switch (property)
        {
            case "Amount":
                return this.Amount;                
                
            case "ArticleID":
                if (this.Article != null)
                {
                    return this.Article.Reference;
                }
                break;
                
            case "Weight":
                return this.Weight;
                
            case "ExpiryDate":
                if (this.ExpiryDate == null)
                {
                   Calendar calendar = Calendar.getInstance();
                   calendar.setTime(new Date());
                   calendar.add(Calendar.DATE, 1);
                   this.ExpiryDate = new java.sql.Date(calendar.getTimeInMillis());
                }                
                
                return this.ExpiryDate;
                
            case "DateAdded":
                if (this.DateAdded == null)
                {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(new Date());
                    this.DateAdded = new java.sql.Date(calendar.getTimeInMillis());                 
                }                
                
                return this.DateAdded;
            case "ShopID":
                if (this.Shop != null)
                {
                    return this.Shop.Reference;
                }
                
                break;
                
            default: 
                return super.GetProperty(property);
        }
        
        return null;
    }
       
    @Override
    public String toString()
    {
        if ((this.Description != null) && (!this.Description.isEmpty()))
        {
            return this.Description;
        }
        
        return super.toString();
    }
}
