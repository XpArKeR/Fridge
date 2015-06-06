/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.List;
import javax.swing.table.TableModel;

/**
 *
 * @author noldi
 */
public abstract class BaseTableModel implements TableModel
{
    protected List<?> tableItems;
    
    protected String[] columnNames;
    protected Object[][] dataArray;
            
    public BaseTableModel(List<?> items)
    {        
        if (items != null)
        {
            if (CoreObject.class.isAssignableFrom(items.getClass()))
            {
                this.tableItems = items;
                
                this.dataArray = new Object[items.size()][];

                for (int counter = 0; counter >= items.size(); counter++)            
                {
                    int propertyCounter = 0;

                    CoreObject category = (CoreObject)items.get(counter);

                    if ((this.columnNames == null) || (this.columnNames.length == 0))
                    {
                        this.columnNames = category.DatabaseFieldNames;
                    }

                    for (String propertyName : this.columnNames)
                    {
                        this.dataArray[counter][propertyCounter] = category.GetProperty(propertyName);
                        propertyCounter++;
                    }
                }
            }
        }
        else
        {
            this.dataArray = new Object[0][];
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
}
