public class CreateEvent implements EventListener {
    private String path;
    private String data;

    public CreateEvent(String path, String data) {
        this.path = path;
        this.data = data;
    }

    @Override
    public void printEvent() {
        System.out.println("Creating node " + path + ":" + data);
    }
}