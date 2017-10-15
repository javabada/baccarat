package io.github.javabada.baccarat;

import io.github.javabada.baccarat.game.Player;
import io.github.javabada.baccarat.game.Shoe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Baccarat extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
    Parent root = loader.load();
    GameController controller = loader.getController();

    Player player = new Player("10000");
    Shoe shoe = new Shoe(8);
    controller.setPlayer(player);
    controller.setShoe(shoe);
    controller.init();

    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.setTitle("Baccarat");
    stage.show();
  }

}
