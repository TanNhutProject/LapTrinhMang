package deSo1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class eachServer implements Runnable{
	String clientPath="C:\\source";
	String serverPath="C:\\dest";
	Thread thread;
	int port;
	sendServer sendSv;
public eachServer(int port) throws IOException{
	thread=new Thread(this);
this.port=port;
thread.start();
sendSv=new sendServer();
//thay doi mot so thong tin gi do
}
@Override
public void run() {
try {
	ServerSocket svSocket=new ServerSocket(port);
	Socket socket=svSocket.accept();
	BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
	PrintWriter pw=new PrintWriter(socket.getOutputStream());
System.out.println("xin chao");

	mainServerLoop: while(true){
		String Originalmessage=br.readLine();
		String message=Originalmessage.trim();
		message.replaceAll("[^\\w]", "");
	
if(message.equals("QUIT")){
System.out.println("end");
break mainServerLoop;
}

	if(message.length()>=13){
		if(message.substring(0, 14).equals("SET_SERVER_DIR")){
			serverPath=message.substring(15);
			pw.println("server path sau khi doi: "+serverPath);
			pw.flush();
		}
		if(message.substring(0, 14).equals("SET_CLIENT_DIR")){
			clientPath=message.substring(15);
			pw.println("client path sau khi doi: "+clientPath);
			pw.flush();
		}
	}
	if(message.substring(0, 4).equals("SEND")){

	try {
		StringTokenizer stn=new StringTokenizer(message,"\t");
		stn.nextToken();
		String sourceFileName=stn.nextToken();
		String destFileName=stn.nextToken();
		sendSv.startServer();
		Socket dataOfSendFile=new Socket("127.0.0.1", 3000);


		System.out.println("noi thanh cong");
		PrintWriter pwSendFile=new PrintWriter(dataOfSendFile.getOutputStream());
		DataInputStream disSendFile=new DataInputStream(socket.getInputStream());

		pwSendFile.println(clientPath+"\\"+sourceFileName+"\t"+serverPath+"\\"+destFileName);
		pwSendFile.flush();
	
		pwSendFile.close();
	} catch (Exception e) {
		System.out.println("sai cu phap");
		
	}
		
		
	}
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
} catch (Exception e) {
	// TODO: handle exception
}
	
}
}
