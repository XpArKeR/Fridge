/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Article.Tables;

import Model.Article.Category;
import Model.BaseTableModel;

import java.util.List;

/**
 *
 * @author noldi
 */
public class CategoryTableModel extends BaseTableModel
{    
    public CategoryTableModel(List<Category> categories)
    {    
        super(categories);       
    }    
}
