import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class TableFrame extends JFrame {
    public TableFrame() {
        this.setTitle("Results");
        this.setVisible(true);
        this.setSize(300, 300);
        this.setLocation(100, 200);

        String[] columnNames = {"ID", "Tavolsag"};
        ArrayList<String[]> dataList = new ArrayList<String[]>();
        for(Node n : Graph.getInstance().getNodes()) {
            String[] res = {String.valueOf(n.getID()),String.valueOf(n.getDistance())};
            dataList.add(res);
        }

        String[][] data = dataList.toArray(new String[0][0]);

        JTable table = new JTable(data, columnNames);
        table.setBounds(30, 40, 200, 300);
        JScrollPane sp = new JScrollPane(table);
        this.add(sp);
        this.setSize(500, 200);
        // Frame Visible = true
        this.setVisible(true);

    }

}
