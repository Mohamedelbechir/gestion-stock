package utility;

import javax.swing.table.*;
import java.sql.*;
import java.util.*;

public class TableModelFromRS extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	// the Column names based on the DB
	private String[] columnName;
	// ArrayList to hold each row composed of an array of Object
	private ArrayList<Object[]> data;
	
	public TableModelFromRS(ResultSet rs) {
		try {
			// meta data to get info on the database
			ResultSetMetaData metaData = rs.getMetaData();
			
			// number of columns present in the ResultSet
			int nbCol = metaData.getColumnCount();
			columnName = new String[nbCol];
			// Load the Array of column names
			for (int i = 0; i < nbCol; ++i) {
				columnName[i] = metaData.getColumnLabel(i + 1); // +1 SQL columns start at 1
			}

			// Now get the rows
			data = new ArrayList<Object[]>();
			// while there are still rows in the ResultSet
			while (rs.next()) {
				Object[] row = new Object[nbCol];
				// retreive each column
				for (int i = 0; i < nbCol; i++) {
					row[i] = rs.getObject(i+1);				// +1 SQL columns start at 1
				}
				// insert into arrayList
				data.add(row);
			}
			rs.close();
		} catch (Exception e) {
			System.out.println("Error building TableModel: " + e);
			//e.printStackTrace();
		}
	}

    // the number of rows is the number of entries in the ArrayList
	public int getRowCount() {
		return data.size();
	}
	
	public void removeRow(int row) {
        data.remove(row);
        //Notifier la table
        fireTableRowsDeleted(row, row);
       
    }

	// the number of columms is the size of the the Array of column name
	public int getColumnCount() {
		return columnName.length;
	}

	// we retreive the array of Object[] at that index in the ArrayList
	// then the object at the required column
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data.get(rowIndex)[columnIndex];
	}
	
	// the column name is in the String[] array of column name
	public String getColumnName(int columnIndex) {
		return columnName[columnIndex];
	}

}