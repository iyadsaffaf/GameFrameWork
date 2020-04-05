package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Connect {
    private SocketChannel sc;
    private PrintWriter out;
    private BufferedReader in;
    public void setUpConnection(){
         sc = null;
        try {
            sc = SocketChannel.open();

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            sc.connect(new InetSocketAddress("127.0.0.1", 7789));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteBuffer bb = ByteBuffer.allocate(555);

        int bytesRead = 0;
        try {
            bytesRead = sc.read(bb);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bytesRead);

        bb.flip();
        CharBuffer c = Charset.forName("ISO-8859-1").decode(bb);
        System.out.println(c);


    }

    public void Connect(){

    }
    public void Disconnect(){

    }
    public String WriteToServer(String message) {

        try {
            out = new PrintWriter(sc.socket().getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(message);


        ByteBuffer br = ByteBuffer.allocate(555);

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            sc.read(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
        br.flip();
        String result = new String(br.array()).trim();
        System.out.println(result);
//        try {
//            sc.configureBlocking(false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        br.clear();
      return result;
    }
}
