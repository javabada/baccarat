package com.github.javabada.baccarat.game;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameController {

    private Player player;

    private StringProperty balanceNumber;

    @FXML
    private Label balanceNumberLabel;

    @FXML
    private void initialize() {
        player = new Player("10000");

        balanceNumber = new SimpleStringProperty("$" + player.getBalanceString());
        balanceNumberLabel.textProperty().bind(balanceNumber);
    }

}
