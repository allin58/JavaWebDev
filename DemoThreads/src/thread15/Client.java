package thread15;

public class Client extends Thread {
    private  boolean reading = false;
    private ChannelPool<AudioChannel> pool;

    public Client(ChannelPool<AudioChannel> pool) {
        this.pool = pool;
    }


    @Override
    public void run() {
      AudioChannel channel = null;

        try {
            channel = pool.getRsource(500);
            reading = true;
            System.out.println("Channel client #" + this.getId() + " took channel #" + channel.getChannelId());
            channel.using();
        } catch (ResourceException e) {
            System.out.println("Client #" + this.getId() + " lost ->" + e.getMessage());
        } finally {
            if (channel != null) {
                reading = false;
                System.out.println("Channel client #" + this.getId() + " : " +channel.getChannelId() + " channel relased");

                pool.returnResource(channel);
            }


        }


    }

    public boolean isReading() {
        return reading;
    }
}
