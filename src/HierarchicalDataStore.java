import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HierarchicalDataStore {

    private Hierarchy root = new Hierarchy("/");
    private List<EventListener> listeners = new ArrayList<>();

    public HierarchicalDataStore() {
    }

    public void createNode(String path, String data) {
        if (path == null) {
            return;
        }
        String[] paths = path.split("/");
        Hierarchy currentNode = root;
        for (int pos = 1; pos < paths.length; ++pos) {
            if (!currentNode.getChildNodes().containsKey(paths[pos])) {
                currentNode.getChildNodes().put(paths[pos], new Hierarchy(paths[pos]));
            }
            currentNode = currentNode.getChildNodes().get(paths[pos]);
        }

        currentNode.getNode().setNodeValue(data);
        listeners.add(new CreateEvent(path, data));
    }

    public void updateNode(String path, String data) {
        if (path == null) {
            return;
        }
        String[] paths = path.split("/");
        Hierarchy currentNode = root;
        for (int pos = 1; pos < paths.length; ++pos) {
            if (!currentNode.getChildNodes().containsKey(paths[pos])) {
                return;
            }
            currentNode = currentNode.getChildNodes().get(paths[pos]);
        }

        currentNode.getNode().setNodeValue(data);
        listeners.add(new UpdateEvent(path, data));
    }

    public void deleteNode(String path) {
        if (path == null) {
            return;
        }
        String[] paths = path.split("/");
        Hierarchy currentNode = root;
        for (int pos = 1; pos < paths.length - 1; ++pos) {
            if (!currentNode.getChildNodes().containsKey(paths[pos])) {
                return;
            }
            currentNode = currentNode.getChildNodes().get(paths[pos]);
        }

        if (currentNode.getChildNodes().containsKey(paths[paths.length - 1])) {
            HashMap<String, Hierarchy> childNodes = currentNode.getChildNodes();
            childNodes.remove(paths[paths.length - 1]);
            currentNode.setChildNodes(childNodes);
        }
        listeners.add(new DeleteEvent(path));
    }

    public String getNodeData(String path) {
        if (path == null) {
            return null;
        }
        String[] paths = path.split("/");
        Hierarchy currentNode = root;
        for (int pos = 1; pos < paths.length; ++pos) {
            if (!currentNode.getChildNodes().containsKey(paths[pos])) {
                return null;
            }
            currentNode = currentNode.getChildNodes().get(paths[pos]);
        }
        return currentNode.getNode().getNodeValue();
    }

    public List<Node> getChildNodes(String path) {
        if (path == null) {
            return new ArrayList<>();
        }
        String[] paths = path.split("/");
        Hierarchy currentNode = root;
        List<Node> listOfChildNodes = new ArrayList<>();
        for (int pos = 1; pos < paths.length; ++pos) {
            if (!currentNode.getChildNodes().containsKey(paths[pos])) {
                return new ArrayList<>();
            }
            currentNode = currentNode.getChildNodes().get(paths[pos]);
        }
        HashMap<String, Hierarchy> childNodes = currentNode.getChildNodes();
        for (Hierarchy hierarchy : childNodes.values()) {
            listOfChildNodes.add(hierarchy.getNode());
        }
        return listOfChildNodes;
    }

    public void notifyEvents() {
        for (EventListener eventListener : listeners) {
            eventListener.printEvent();
        }
    }
}