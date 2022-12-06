package com.geekbrains.cloud.Controllers;

import com.geekbrains.cloud.network.Net;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Net net;
    public ListView<String> view;

    public ListView<String> clientView;
    public TextField input;

    private void readListFiles() {
        try {
            view.getItems().clear();
            Long filesCount = net.readLong();
            for (int i = 0; i < filesCount; i++) {
                String filename = net.readUtf();
                view.getItems().addAll(filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readClientListFiles() {
        try {
            clientView.getItems().clear();
            Long filesCount = net.readLong();
            for (int i = 0; i < filesCount; i++) {
                String filename = net.readUtf();
                clientView.getItems().addAll(filename);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void read() {
        try {
            while (true) {
                String command = net.readUtf();
                if (command.equals("#list#")) {
                    readListFiles();
                }
                if (command.equals("#client-list#")){
                    readClientListFiles();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            net = new Net("localhost", 8189);
            Thread readThread = new Thread(this::read);
            readThread.setDaemon(true);
            readThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
