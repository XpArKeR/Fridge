/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Article.Tables;

import Model.Article.Category;
import Model.BaseTableModel;
import Model.CoreObject;

import java.util.List;
import javax.swing.event.TableModelListener;

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

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
