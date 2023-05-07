import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {
    JTable table;

    public Table() {

        String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding",},
                {"John", "Doe",
                        "Rowing", },
                {"Sue", "Black",
                        "Knitting", },
                {"Jane", "White",
                        "Speed reading", },
                {"Joe", "Brown",
                        "Pool", }
        };
        table = new JTable(data, columnNames);

    }
}