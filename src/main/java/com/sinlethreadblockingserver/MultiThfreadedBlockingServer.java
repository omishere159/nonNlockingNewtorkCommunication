package com.sinlethreadblockingserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThfreadedBlockingServer {

  public static void main(String[] args) throws IOException {
    ServerSocket lServerSocket = new ServerSocket(8081);
    handle(lServerSocket);
    return;
  }

  private static void handle(ServerSocket serverSocket) throws IOException {

    new Thread(() -> {
      while(true)
      {
        Socket ss = null;
        InputStream lInputStream = null;
        OutputStream lOutputStream = null;
        try {
          ss=serverSocket.accept();
          System.out.println("Connected to: "+ss);
          lInputStream = ss.getInputStream();
          lOutputStream = ss.getOutputStream();
          int data;
          while(  (data = lInputStream.read()) != -1 )
          {
            lOutputStream.write(transfrom(data));
          }
          lOutputStream.close();
          lInputStream.close();
          ss.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
        finally {
          System.out.println("Disconnecting from: "+serverSocket);
        }
      }
    });
  }

  private static int transfrom(int data) {
    return Character.isLetter(data) ? data ^ ' ' : data;
  }

}
