package utility;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellRender extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,
			int row,int column){
		Component component= super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		setHorizontalAlignment(SwingConstants.CENTER);
		
	     JLabel label = (JLabel)super.getTableCellRendererComponent(
	             table, value, isSelected, hasFocus, row, column);
	     if(row==0 && column==0){
	    	// label.setBackground(Color.RED);
	    	 
	     }
		return component;
	}

}
