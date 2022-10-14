import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int port = 6666;
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            socket.setTcpNoDelay(true);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String s = dataInputStream.readUTF();
                if (! s.contains("+")) {
                    dataOutputStream.writeInt(0);
                    continue;
                }
                try {
                    int idx = s.indexOf('+');
                    int a = Integer.parseInt(s.substring(0, idx));
                    int b = Integer.parseInt(s.substring(idx + 1));
                    dataOutputStream.writeInt(a + b);
                } catch (Exception e) {
                    dataOutputStream.writeInt(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
