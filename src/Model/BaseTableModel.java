/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author noldi
 */
public abstract class BaseTableModel implements TableModel
{
    protected List<? extends CoreObject> tableItems = new ArrayList<>();
    
    protected String[] columnNames = new String[]{};
    protected Object[][] dataArray = new Object[0][0];
            
    public BaseTableModel(List<? extends CoreObject> items)
    {        
        if ((items != null) && (items.size() > 0))
        {            
            this.tableItems = items;

            for (int counter = 0; counter < items.size(); counter++)            
            {
                int propertyCounter = 0;

                CoreObject category = (CoreObject)items.get(counter);

                if ((this.columnNames == null) || (this.columnNames.length == 0))
                {
                    this.columnNames = category.DatabaseFieldNames;
                }

                this.dataArray = new Object[items.size()][columnNames.length];
                
                for (String propertyName : this.columnNames)
                {
                    
                    
                    this.dataArray[counter][propertyCounter] = category.GetProperty(propertyName);
                    propertyCounter++;
                }
            }            
        }        
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) 
    {
        return this.getValueAt(0, columnIndex).getClass();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) 
    {
        return false;
    }
    
    @Override
    public int getRowCount() {
        return this.tableItems.size();
    }   

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }
    
    @Override
    public String getColumnName(int columnIndex)
    {
        return this.columnNames[columnIndex];
    }
        
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return this.dataArray[rowIndex][columnIndex];
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        CoreObject coreObject = (CoreObject)this.tableItems.get(rowIndex);
        
        coreObject.SetProperty(this.columnNames[columnIndex], aValue);
    }
    
    @Override
    public void addTableModelListener(TableModelListener l) {
        
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        
    }
}
