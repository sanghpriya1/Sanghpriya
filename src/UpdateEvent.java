public class UpdateEvent implements EventListener {
    private String path;
    private String data;

    public UpdateEvent(String path, String data) {
        this.path = path;
        this.data = data;
    }

    @Override
    public void printEvent() {
        System.out.println("Update node " + path + ":" + data);
    }
}