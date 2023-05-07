import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Buttons extends JPanel {
    JButton new_b;
    JButton simulation_b;
    JButton random_b;

    public Buttons() {
        new_b = new JButton("New");
        simulation_b = new JButton("Simulation");
        random_b = new JButton("Random graph");

        new_b.setMaximumSize(new Dimension(120,30));
        new_b.addActionListener((ae) -> {
            Graph.getInstance().newGraph();
            GraphicsFrame frame = (GraphicsFrame) SwingUtilities.getWindowAncestor(this);
            frame.repaint();
        });

        simulation_b.setMaximumSize(new Dimension(120,30));
        simulation_b.addActionListener(new SimulationButtonActionListener());

        random_b.setMaximumSize(new Dimension(120,30));
        random_b.addActionListener((ae) -> {
            Graph.getInstance().makeRandomGraph();
            GraphicsFrame frame = (GraphicsFrame) SwingUtilities.getWindowAncestor(this);
            frame.repaint();
        });

        this.add(new_b);
        this.add(simulation_b);
        this.add(random_b);
    }

    class SimulationButtonActionListener implements ActionListener {
        int k;
        public void actionPerformed(ActionEvent ae) {
            //makeBlankScreen();
            System.out.print("simulation");
            JFrame j = new JFrame();
            j.setTitle("Kezdocsucs");
            j.setVisible(true);
            j.setSize(300, 300);
            j.setLocation(100, 200);

            JPanel p = new JPanel();
            p.setSize(300,300);

            JTextField text = new JTextField("Melyik csucsbol induljon?");
            text.setEditable(false);
            JTextField answer = new JTextField("Kerlek ide ird be a szamot");
            answer.setEditable(true);
            String s;

            JButton button = new JButton(new AbstractAction("Ok") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int i = Integer.parseInt(answer.getText());
                    k = i;
                    j.dispatchEvent(new WindowEvent(j, WindowEvent.WINDOW_CLOSING));

                    Node n = Graph.getInstance().makeStarter(k);
                    Logic.run(Graph.getInstance(), n);

                    TableFrame tf = new TableFrame();
                }
            });
            j.add(p);
            p.add(text);
            p.add(answer);
            p.add(button);

          /*  List nodes = (List) Graph.getInstance().getNodes();
            JFrame tablaFrame = new JFrame();
            tablaFrame.setTitle("Futasi eredmeny");
            tablaFrame.setVisible(true);
            tablaFrame.setSize(300, 300);
            tablaFrame.setLocation(100, 200);
            tablaFrame.setLayout(new BorderLayout());
            JTable jt = new JTable((TableModel) nodes); //
            tablaFrame.add(new JScrollPane(jt), BorderLayout.CENTER);
            jt.setFillsViewportHeight(true); */



        }
    }

    class NewButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            System.out.print("new");
            Graph.getInstance().newGraph();

        }
    }

    class RandomButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            //makeBlankScreen();
            System.out.print("randoo");
            Graph.getInstance().makeRandomGraph();
        }
    }
}

