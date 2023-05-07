import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class DrawPanel extends JPanel {


    public DrawPanel(GraphicsFrame parent) {
        super();

        setBackground(Color.WHITE);
        addMouseListener(new OnClick(parent));
    }

    public class OnClick extends MouseAdapter {
        private Node saved = null;
        private GraphicsFrame frame;

        public OnClick(GraphicsFrame parent) {
            frame = parent;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int ex = e.getX();
            int ey = e.getY();
            Node n = isInsideGraph(ex, ey);
            if(saved == null) {
                if (((n != null))) {
                    System.out.print("xx");
                    saved = n;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int ex = e.getX();
            int ey = e.getY();
            Node n = isInsideGraph(ex, ey);
            if(n != null) {
                String tmp = (String) JOptionPane.showInputDialog(frame, "Élsúly:", "Elsuly", JOptionPane.PLAIN_MESSAGE, null, null, "1");
                Graph.getInstance().addEdge(saved, n, Integer.parseInt(tmp));
            }
            saved = null;
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            int ex = e.getX();
            int ey = e.getY();
            Node n = isInsideGraph(ex, ey);
            if(e.getClickCount()==2) {
                Graph.getInstance().addNode(new Node(ex, ey));
                System.out.print("x = " + ex + " y = " + ey);
                repaint();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        int r = 30;
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.RED);
        for (Node n : Graph.getInstance().getNodes()) {
            //  g2.drawOval(n.getX() - r / 2, n.getY() - r / 2, r, r);
            g2.fillOval(n.getX() - r / 2, n.getY() - r / 2, r, r);
            //  jl = new JLabel();
            g2.setColor(Color.BLACK);
            String s=Integer.toString(n.getID());
            g2.drawString(s, n.getX(), n.getY());
            g2.setColor(Color.RED);
        }

        for (Node n : Graph.getInstance().getNodes()) {
            for (Node p : n.getNeighborToList()) {
                g2.drawLine(n.getX(), n.getY(), p.getX(), p.getY());

                String s = Double.toString(n.getWeightTo(p));
                String innen = Integer.toString(n.getID());
                String ide = Integer.toString(p.getID());
                g2.setColor(Color.BLUE);
                g2.drawString("(" + s + ")" + ": " + innen + "->" + ide, abs((n.getX()-p.getX())) / 2 + min(n.getX(), p.getX()), abs((n.getY() - n.getY())) / 2 + min(n.getY(), p.getY()));
                g2.setColor(Color.RED);
            }
        }
    }

    public boolean isInside(int x, int y, int ex, int ey) {
        int rad = 30;
        if((ex-rad <= x) && (ex+rad >= x) && (ey-rad <= y) && (ey+rad >= y))
            return true;
        return false;
    }

    public Node isInsideGraph(int ex, int ey) {
        for(Node n : Graph.getInstance().getNodes()) {
            if(isInside(n.getX(), n.getY(), ex, ey))
                return n;
        }
        return null;
    }

    public Node getStarterNode(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        Node n = isInsideGraph(x, y);
        return  n;
    }
}

