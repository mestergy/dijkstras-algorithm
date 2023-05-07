import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph implements Serializable {
    private static Graph instance = new Graph();
    public static Graph getInstance() {return instance;}

    private List<Node> nodes;

    private Graph() {
        nodes = new ArrayList<Node>();
    }

    public List<Node> getNodes() {
        return nodes;
    }



    public void addNode(Node n) {
        nodes.add(n);
        System.out.println(nodes);
    }
    public void addEdge(Node innen, Node ide, double suly) {
        innen.setNeighborTo(ide, suly);
        ide.setNeighborFrom(innen, suly);
        System.out.println(innen.toString() + '/' + ide.toString() + '/' + suly);
    }

    public static void save(File selectedFile) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(selectedFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(Graph.getInstance());
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException ignored) {
        }
    }

    public static void load(File file) {
        try {
            FileInputStream fileOutputStream = new FileInputStream(file);
            ObjectInputStream objectOutputStream = new ObjectInputStream(fileOutputStream);
            Graph read = (Graph) objectOutputStream.readObject();
            objectOutputStream.close();
            fileOutputStream.close();
            instance = read;
        } catch (IOException | ClassNotFoundException ignored) {
            ignored.printStackTrace();
        }
    }

    public void newGraph() {
        for(Node n : this.getNodes()) {
            n.setNextIDDef();
        }
        nodes.clear();

    }

    public void makeRandomGraph() {
        this.newGraph();
        Random rand = new Random();
        int randomNum = rand.nextInt((10 - 1) + 1) + 1;
        for(int i = 1; i <= randomNum; i++) {
            Random rand2 = new Random();
            Random rand3 = new Random();
            int randX = rand2.nextInt(1000-30) + 1;
            int randY = rand3.nextInt(400-30) + 1;
            Node n = new Node(randX, randY);
            this.addNode(n);
        }
        Random rand4 = new Random();
        Random rand5 = new Random();
        int randNodeNum = rand4.nextInt((randomNum*(randomNum-1)/2-1) + 1) +1;
        for(int i = 0; i <= randNodeNum; i++) {
            int randWeight= rand5.nextInt((100) +1) +1;
            Random rand6 = new Random();
            Random rand7 = new Random();
            int randNoden = rand6.nextInt((randNodeNum-1) + 1) +1;
            int randNodep = rand7.nextInt((randNodeNum-1) + 1) +1;
            Node n = this.makeStarter(randNoden);
            Node p = this.makeStarter(randNodep);
            this.addEdge(n, p, randWeight);
        }
    }

    public Node makeStarter(int i) {
        for(Node n : getInstance().getNodes()) {
            if(n.getID() == i ) {
                return n;
            }
        }
        return getInstance().getNodes().get(0);
    }

}
