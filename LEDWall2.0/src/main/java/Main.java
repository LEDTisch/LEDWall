import java.nio.channels.SocketChannel;

public class Main {
   static SocketController sc = new SocketController();

    public static void main(String[] args) {
        sc.initSocket();
    }
}
