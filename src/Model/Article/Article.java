package Model.Article;

import Core.Globals;
import Model.CoreObject;
import java.util.ArrayList;
import java.util.List;

public class Article extends CoreObject
{
    public String Name;
    public String Description;
    public Category Category;
    public List<ArticleLine> Lines;
    public List<Barcode> Codes;
    public int Amount;
        
    public Article()
    {        
        this.DatabaseTableName = "Article";
        this.DatabaseFieldNames = new String[]{"ID", "Name", "CategoryID", "Description", "Amount"};
        this.DatabaseFieldTypes = new String[]{"String", "String", "String", "String", "int"};
                
        this.Codes = new ArrayList<>();
        this.Lines = new ArrayList<>();
    }
    
    @Override
    public void SetReference(String reference)
    {
        super.SetReference(reference);
                        
        for(ArticleLine articleLine : this.Lines)
        {
            articleLine.Article = this;
        }
        
        for (Barcode barcode : this.Codes)
        {
            barcode.ParentArticle = this;
        }
    }
    
    @Override
    public void SetProperty(String property, Object value)
    {
        switch (property)
        {
            case "Name":
                this.Name = (String)value;
                break;
            case "Description":
                this.Description = (String)value;
                break;
            case "Lines":
                this.Lines = (List<ArticleLine>)value;
                break;
            case "Codes":
                this.Codes = (List<Barcode>)value;
                break;
            case "CategoryID":
                if (this.Category == null)
                {
                    this.Category = new Category();
                }
                
                this.Category.Load(value.toString());
                
                break;
                
            case "Amount":
                this.Amount = (int)value;
                break;
                
            default:
                super.SetProperty(property,value);
                break;
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
                
            case "Lines":
                return this.Lines;
                
            case "Codes":
                return this.Codes;
                
            case "Amount":
                return this.Amount;
            case "CategoryID":
                if (this.Category != null)
                {
                    return this.Category.Reference;
                }
                
            default:
                return super.GetProperty(property);                
        }
    }
    
    public int GetTotalAmount()
    {
        int amount = 0;
        
        if (this.Lines != null)
        {
            for (ArticleLine line : this.Lines)
            {
                amount += line.Amount;
            }
        }
        
        return amount;
    }
    
    public Boolean AddArticleLine(ArticleLine line)
    {
        boolean success = false;
        
        if (line != null)
        {
            if (!this.Lines.contains(line))
            {
                this.Lines.add(line);
            }
        }   
        
        return success;
    }
    
    public List<ArticleLine> GetLines()
    {
        this.Lines = Globals.Database.LoadAll(ArticleLine.class, this);
        
        return this.Lines;
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
