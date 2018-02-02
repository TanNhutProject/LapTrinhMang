package deSo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	Socket socket;
public Client() throws UnknownHostException, IOException{
	 socket=new Socket("127.0.0.1", 9999);
	BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	PrintWriter pw=new PrintWriter(socket.getOutputStream());
	Scanner sc=new Scanner(System.in);
	System.out.println("nhap lenh");
while(true){
	String message=sc.nextLine();
pw.println(message);
pw.flush();
}

}
public void closeSocket() throws IOException{
	socket.close();
}

}
