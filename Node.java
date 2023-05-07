import java.io.Serializable;
import java.util.*;

public class Node implements Serializable {

    private double d = 10000;
    private int x;
    private int y;

    private int id;
    private static int nextID=0;
    private Map<Node, Double> edges_to = new HashMap<>();
    private Map<Node, Double> edges_from = new HashMap<>();

    public String[] tombAmiKell() {
        String[] a = new String[0];
        a[0] = String.valueOf(this.getID());
        a[1] = String.valueOf(this.getDistance());
        return a;
    }

    public Node(int _x, int _y) {
        this.id = nextID++;
        x = _x;
        y = _y;
    }

    public void setNextIDDef() {
        this.nextID = 0;
    }

    public int getID() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getDistance() {
        return d;
    }

    public void setDistance(double a) {
        this.d = a;
    }

    public void setNeighborTo(Node endPoint, double weight) {
        this.edges_to.put(endPoint, weight);
    }

    public void setNeighborFrom(Node starterPoint, double weight) {
        this.edges_from.put(starterPoint, weight);
    }

    public double getWeightTo(Node to) {
        return this.edges_to.get(to);
    }

    public double getWeightFrom(Node from) {
        return this.edges_from.get(from);
    }

    public Map<Node, Double> getNeighborsTo() {
        return edges_to;
    }

    public List<Node> getNeighborToList() {
        List<Node> tmp = new ArrayList<>(edges_to.keySet());
        return tmp;
    }

    public void deleteMap(){
        System.out.print("del");
        edges_to.clear();
        edges_from.clear();
    }

}

