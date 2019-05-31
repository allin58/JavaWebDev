package thread7;

public class SyncThread extends Thread {
    private Resource res;

    public SyncThread(String name, Resource res) {
        super(name);
        this.res = res;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            res.writing(getName(),i);
        }
    }
}
