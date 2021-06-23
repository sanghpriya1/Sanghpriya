import java.util.List;

public class Main {

    public static void main(String[] args) {

        HierarchicalDataStore hierarchicalDataStore = new HierarchicalDataStore();

        // adding node to the tree
        hierarchicalDataStore.createNode("/root", "nothing");
        hierarchicalDataStore.createNode("/root/child1", "childdata1");
        hierarchicalDataStore.createNode("/root/child2", "childdata2");
        hierarchicalDataStore.createNode("/root/child1/subchild1", "subchilddata1");

        // Print data for each node
        System.out.println("Data for path /root" + ":" + hierarchicalDataStore.getNodeData("/root"));
        System.out.println("Data for path /root/child1" + ":" + hierarchicalDataStore.getNodeData("/root/child1"));
        System.out.println("Data for path /root/child2" + ":" + hierarchicalDataStore.getNodeData("/root/child2"));
        System.out.println("Data for path /root/child1/subchild1" + ":" + hierarchicalDataStore.getNodeData("/root/child1/subchild1"));

        System.out.println("-----------------------");

        // Get root child nodes
        List<Node> childNodes = hierarchicalDataStore.getChildNodes("/root");
        for (Node node : childNodes) {
            System.out.println(node.getNodeName() + ":" + node.getNodeValue());
        }

        System.out.println("-----------------------");

        // deleting the node
        hierarchicalDataStore.deleteNode("/root/child2");


        // Get root child nodes
        childNodes = hierarchicalDataStore.getChildNodes("/root");
        for (Node node : childNodes) {
            System.out.println(node.getNodeName() + ":" + node.getNodeValue());
        }

        System.out.println("-----------------------");

        // notify all events
        hierarchicalDataStore.notifyEvents();
    }
}
