public class DeleteEvent implements EventListener {
    private String path;

    public DeleteEvent(String path) {
        this.path = path;
    }

    @Override
    public void printEvent() {
        System.out.println("Deleting node " + path);
    }
}