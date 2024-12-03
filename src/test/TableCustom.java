package test;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class TableCustom extends JTable {
       
    public TableCustom() {
        setOpaque(false);
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);

        //  Alternate row color
        if (!isRowSelected(row)) {
            c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);
        }

        return c;
    }
};

