package com.github.javabada.baccarat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HomeController {

    @FXML private Button startButton;

    @FXML
    private void handleStartButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Game.fxml"));
        startButton.getScene().setRoot(root);
    }

    @FXML
    private void handleHyperlinkAction(ActionEvent event) throws IOException, URISyntaxException {
        if (Desktop.isDesktopSupported()) {
            Desktop.getDesktop().browse( new URI("https://github.com/javabada/baccarat") );
        }
    }

}
