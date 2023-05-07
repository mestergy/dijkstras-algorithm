import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GraphicsFrame extends JFrame {

//	JPanel p1;
//	JComboBox jcb;
//	JTextField f1, f2;
//	JButton proba;
//	JLabel label1;

    JMenuBar jmb;
   // JTable jt;
    DrawPanel dp;
   // Table t = new Table();

    public GraphicsFrame(String s) {
        super(s);
        dp = new DrawPanel(this);
        this.add(dp, BorderLayout.CENTER);
        this.add(new Buttons(), BorderLayout.PAGE_END);

        this.add(new Table(), BorderLayout.SOUTH);
//        this.add(new Table(), BorderLayout.SOUTH);

        jmb = new JMenuBar();
        jmb.add(createMenu());
        this.setJMenuBar(jmb);

       // jt = new JTable();
        //this.add(jt, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1200, 600);
        this.setLocation(200, 100);
        this.setVisible(true);
    }

    private JMenu createMenu() {
        JMenu menu, submenu;
        JMenuItem load_m, file_m;  //svg_m;

        JMenuBar mb=new JMenuBar();
        menu = new JMenu("Menu");
        submenu=new JMenu("Save");
       // new_m = new JMenuItem("New");
        load_m = new JMenuItem("Load");
        file_m = new JMenuItem("file");
        //svg_m = new JMenuItem("svg");

        load_m.addActionListener(new LoadButtonActionListener());
        file_m.addActionListener(new SaveFileButtonActionListener());

        menu.add(load_m);
        menu.addSeparator();
        submenu.add(file_m);
       // submenu.add(svg_m);
        menu.add(submenu);
        mb.add(menu);

        return menu;
    }

    class LoadButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            System.out.print("load:D");
            load();
        }
    }

    class SaveFileButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent ae) {
            System.out.print("savee");
            try {
                save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void save() throws IOException {
        JFileChooser jFileChooser = new JFileChooser();
        int result = jFileChooser.showSaveDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            Graph.save(jFileChooser.getSelectedFile());
        }
    }

    private void load() {
        JFileChooser jFileChooser = new JFileChooser();
        int result = jFileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            Graph.load(jFileChooser.getSelectedFile());
            dp.repaint();
        }
    }

}






