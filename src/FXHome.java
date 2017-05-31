import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class FXHome {

    public static Scene createScene() {
        Label welcomeLabel = new Label("Welcome to Baccarat");
        welcomeLabel.setId("home-welcome-label");

        Button startButton = new Button("Start");
        startButton.setId("home-start-button");

        Label authorLabel = new Label("Created by Jeffrey Zhang");

        Hyperlink sourceLink = new Hyperlink("Source Code on GitHub");
        sourceLink.setOnAction(e -> {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().browse( new URI("https://github.com/jzhang11235/baccarat") );
                }
                catch (IOException | URISyntaxException e1) {
                    e1.printStackTrace();
                }
            }
        });

        VBox centerVBox = new VBox();
        centerVBox.getChildren().addAll(welcomeLabel, startButton);
        centerVBox.setId("home-center-vbox");

        VBox bottomVBox = new VBox();
        bottomVBox.getChildren().addAll(authorLabel, sourceLink);
        bottomVBox.setId("home-bottom-vbox");

        BorderPane layout = new BorderPane();
        layout.setCenter(centerVBox);
        layout.setBottom(bottomVBox);

        Scene scene = new Scene(layout, 800, 600);
        scene.getStylesheets().add("Casino.css");

        return scene;
    }

}
