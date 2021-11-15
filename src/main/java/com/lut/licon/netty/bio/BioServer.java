package com.lut.licon.netty.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Describe:
 *
 * @author Licon
 * @date 2020/12/22 13:13
 */
public class BioServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(8023);

        ExecutorService executorService = Executors.newFixedThreadPool(50);


        while (true){
            Socket accept = serverSocket.accept();
            if (accept == null){
                break;
            }
            executorService.execute(()->{
                try {
                    new BioServer().threadHandler(accept);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void threadHandler(Socket accept) throws Exception{

        byte[] bytes = new byte[1024];
        for (;;){
            InputStream read = accept.getInputStream();
            int result = read.read(bytes);
            if (result != -1){
                System.out.println(new String(bytes,0,result));
            }else {
                break;
            }
        }
    }
}
