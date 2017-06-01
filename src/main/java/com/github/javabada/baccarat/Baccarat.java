package com.github.javabada.baccarat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Baccarat extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene homeScene = FXHome.createScene();

        primaryStage.setTitle("Baccarat");
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

}
