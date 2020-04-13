package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Connection {


    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private String ipAddress;
    private int portNumber;


    public Connection(String ipAddress, int portNumber) throws IOException {
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
        setUpConnection();
    }

    public void setUpConnection() throws IOException, NullPointerException {
        socket = new Socket(ipAddress, portNumber);

        output = new PrintWriter(socket.getOutputStream(), true);


        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public PrintWriter getOutput() {
        return output;
    }

    public void setOutput(PrintWriter output) {
        this.output = output;
    }

    public BufferedReader getInput() {
        return input;
    }

    public void setInput(BufferedReader input) {
        this.input = input;
    }
}
