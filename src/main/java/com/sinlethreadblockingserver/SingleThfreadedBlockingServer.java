package com.sinlethreadblockingserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThfreadedBlockingServer {

  public static void main(String[] args) throws IOException {
    ServerSocket lServerSocket = new ServerSocket(8081);
    while(true)
    {
      Socket ss = lServerSocket.accept();
      System.out.println("Connected to: "+ss);
      InputStream lInputStream = ss.getInputStream();
      OutputStream lOutputStream = ss.getOutputStream();
      int data;
      while(  (data = lInputStream.read()) != -1 )
      {
        lOutputStream.write(transfrom(data));
      }
      lOutputStream.close();
      lInputStream.close();
      System.out.println("Disconnecting from: "+ss.isClosed());
      ss.close();
      System.out.println("Disconnecting from: "+ss.isClosed());
    }
  }

  private static int transfrom(int data) {
    return Character.isLetter(data) ? data ^ ' ' : data;
  }

}
