import java.util.HashMap;

public class Hierarchy {
    private Node node;
    private HashMap<String, Hierarchy> childNodes = new HashMap<>();

    public Hierarchy(String nodeName) {
        node = new Node(nodeName);
    }

    public Hierarchy(Node node, HashMap<String, Hierarchy> childNodes) {
        this.node = node;
        this.childNodes = childNodes;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public HashMap<String, Hierarchy> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(HashMap<String, Hierarchy> childNodes) {
        this.childNodes = childNodes;
    }
}
