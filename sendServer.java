package deSo1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class sendServer implements Runnable{
int port=3000;
DataInputStream dis;
String destFileName;
BufferedReader br;
DataOutputStream dos;
ServerSocket serverSocket;
Socket socket;
Thread thread;
public sendServer() throws IOException {

thread=new Thread(this);
thread.start();
thread.suspend();


serverSocket=new ServerSocket(port);

}
public void startServer(){
	thread.resume();
}
@Override
	public void run() {
		try {
			socket=serverSocket.accept();

			System.out.println("thanh cong 3000");
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String message=br.readLine();
			StringTokenizer sourceAndDest=new StringTokenizer(message, "\t");
			String source=sourceAndDest.nextToken();
			String dest=sourceAndDest.nextToken();
			File sourceFile=new File(source);
			File destFile=new File(dest);
			dis=new DataInputStream(new FileInputStream(sourceFile));
			dos=new DataOutputStream(new FileOutputStream(destFile));
			byte b[]=new byte[1024];
			int data;
			while((data=dis.read(b))!=-1){
				dos.write(b, 0, data);
			}
			dis.close();
			dos.close();
			thread.suspend();
		} catch (Exception e) {
			
		}
		
	}


}
