import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    static String  host = "127.0.0.1";
    static int port = 6666;

    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner sc = new Scanner(System.in);
            while (true) {
                String s = sc.next();
                dataOutputStream.writeUTF(s);
                int res = dataInputStream.readInt();
                System.out.println(res);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
