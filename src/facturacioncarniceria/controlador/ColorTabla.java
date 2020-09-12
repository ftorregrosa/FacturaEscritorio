/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facturacioncarniceria.controlador;

import java.awt.Color;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Component;
import javax.swing.JTable;

/**
 *
 * @author JORDY
 */
public class ColorTabla extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent (JTable table, Object value, boolean Selected, boolean hasFocus, int row, int col){
        super.getTableCellRendererComponent(table, value, Selected, hasFocus, row, col);

        if(Float.parseFloat(table.getValueAt(row, 4).toString())<=3){
            setBackground(Color.LIGHT_GRAY);  
        } else if(Float.parseFloat(table.getValueAt(row, 4).toString())<0){
            setBackground(Color.YELLOW);  
        }
        else{
            setBackground(Color.white);
        }
        return this;
    }
    
}
