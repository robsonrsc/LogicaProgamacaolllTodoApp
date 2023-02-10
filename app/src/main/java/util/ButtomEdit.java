/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.awt.Component;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.task;

/**
 *
 * @author robson
 */
public class ButtomEdit extends DefaultTableCellRenderer {
    
    
    
    private String typeButtom;
    
    public ButtomEdit (String typeButtom){
        this.typeButtom = typeButtom;
        
    }

    public String getTypeButtom() {
        return typeButtom;
    }

    public void setTypeButtom(String typeButtom) {
        this.typeButtom = typeButtom;
    }
    
    
    
    
    
         @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        //Cells are by default rendered as a JLabel.
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        label.setHorizontalAlignment( JLabel.CENTER );

        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/" + typeButtom + ".png")));
        

        return label;
    }
    
    
}
