package com.geekbrains.cloud;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class FileMessageHandler implements Runnable {

    private File dir;
    private File clientDir;
    private final DataInputStream is;
    private final DataOutputStream os;

    public FileMessageHandler(Socket socket) throws IOException {
        is = new DataInputStream(socket.getInputStream());
        os = new DataOutputStream(socket.getOutputStream());
        System.out.println("Client accepted");
        showFile(is, os, clientDir, "client-files", "#client-list#");
        showFile(is, os, dir, "files", "#list#");
    }

    private void showFile(DataInputStream is, DataOutputStream os, File directory, String dirName, String command) throws IOException {
        directory = new File(dirName);
        String [] files = directory.list();
        os.writeUTF(command);
        os.writeLong(files.length);
        for (String file : files) {
            os.writeUTF(file);
        }
    }

    @Override
    public void run() {

        try {
            while (true) {
                String utf = is.readUTF();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
