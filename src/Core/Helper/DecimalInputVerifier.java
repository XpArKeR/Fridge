/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Core.Helper;

import java.math.BigDecimal;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 *
 * @author noldi
 */
public class DecimalInputVerifier extends InputVerifier
{
    @Override
    public boolean verify(JComponent input) 
    {        
        String text = ((JTextField) input).getText();
        try {
            BigDecimal value = new BigDecimal(text);
            return (value.scale() <= Math.abs(2)); 
        } catch (NumberFormatException e) {
            return false;
        }
    }    
}
