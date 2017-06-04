package com.github.javabada.baccarat.game;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

public class GameController {

    private Player player;

    private StringProperty balanceNumberLabelText;
    private StringProperty tieButtonText;
    private StringProperty bankerButtonText;
    private StringProperty playerButtonText;

    @FXML private Label balanceNumberLabel;
    @FXML private Button tieButton;
    @FXML private Button bankerButton;
    @FXML private Button playerButton;
    @FXML private ToggleGroup chipToggleGroup;

    @FXML
    private void initialize() {
        player = new Player("10000");

        balanceNumberLabelText = new SimpleStringProperty("$" + player.getBalanceString());
        balanceNumberLabel.textProperty().bind(balanceNumberLabelText);
        tieButtonText = new SimpleStringProperty("Tie");
        tieButton.textProperty().bind(tieButtonText);
        bankerButtonText = new SimpleStringProperty("Banker");
        bankerButton.textProperty().bind(bankerButtonText);
        playerButtonText = new SimpleStringProperty("Player");
        playerButton.textProperty().bind(playerButtonText);
    }

}
