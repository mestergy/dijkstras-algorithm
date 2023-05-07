import java.util.Map;
import java.util.PriorityQueue;

public class Logic {

    public static void run(Graph graph, Node starter) {

        for (Node n:graph.getNodes()) {
            double infinity = 10000;
            n.setDistance(infinity);
        }

        starter.setDistance(0);

        PriorityQueue<Node> Q=new PriorityQueue<>((o1, o2) -> (int) (((Node)o1).getDistance()-((Node)o2).getDistance()));
        Q.add(starter);
        while (Q.size()!=0) {
            Node elKezdoPont=Q.remove();
            System.out.print("oooo");
            Map<Node, Double> nodesTo = elKezdoPont.getNeighborsTo();

            for (Node elVegPont: nodesTo.keySet()) {
                System.out.print("oooo");
                if (elVegPont.getDistance() > elKezdoPont.getDistance()+ nodesTo.get(elVegPont) ) {
                    elVegPont.setDistance(elKezdoPont.getDistance() + nodesTo.get(elVegPont));
                    Q.add(elVegPont);
                }
            }
        }
        for(Node n: graph.getNodes()) {
            System.out.println("ID: " + n.getID() + ", distance: " + n.getDistance());
        }
    }
}