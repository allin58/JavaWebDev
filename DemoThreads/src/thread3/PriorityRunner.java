package thread3;

public class PriorityRunner {

    public static void main(String[] args) {
        PriorThread min = new PriorThread("min");
        PriorThread max = new PriorThread("max");
        PriorThread norm = new PriorThread("norm");

        min.setPriority(Thread.MIN_PRIORITY);
        max.setPriority(Thread.MAX_PRIORITY);
        norm.setPriority(Thread.NORM_PRIORITY);

        min.start();
        max.start();
        norm.start();

    }

}
