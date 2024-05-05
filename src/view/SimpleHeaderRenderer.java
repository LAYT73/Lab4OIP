package view;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * A simple renderer class for JTable header.
 * This class implements TableCellRenderer interface to render table header cells.
 */
public class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {

    /**
     * Constructs a SimpleHeaderRenderer object.
     *
     * @param mainForm The MainForm instance for accessing theme and font colors
     */
    public SimpleHeaderRenderer(MainForm mainForm) {
        // Set font, foreground, background, border, alignment, and cursor for the header
        setFont(new Font("Arial", Font.BOLD, 18));
        setForeground(mainForm.fontColor);
        setBackground(mainForm.themeColor);
        setBorder(BorderFactory.createEtchedBorder());
        setHorizontalAlignment(SwingConstants.CENTER);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * Gets the renderer component for the table header cell.
     *
     * @param table      The JTable component
     * @param value      The value to assign to the cell at [row, column]
     * @param isSelected True if cell is selected
     * @param hasFocus   True if cell has focus
     * @param row        The row of the cell to render
     * @param column     The column of the cell to render
     * @return The component used for rendering the cell
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // Set text value for the header cell
        setText(value.toString());
        return this;
    }

    /**
     * Overrides getPreferredSize() method to set the preferred size of the header.
     *
     * @return The preferred size of the header
     */
    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        // Set the height of the header
        size.height = 32;
        return size;
    }
}
