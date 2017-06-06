package com.github.javabada.baccarat.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;

import java.text.NumberFormat;
import java.util.Locale;

public class GameController {

    private static final Locale LOCALE = Locale.US;

    private Player player;

    @FXML private Label balanceNumberLabel;
    @FXML private Label messageLabel;
    @FXML private Button tieButton;
    @FXML private Button bankerButton;
    @FXML private Button playerButton;
    @FXML private ToggleGroup chipToggleGroup;

    @FXML
    private void initialize() {
        player = new Player("10000");

        balanceNumberLabel.setText(formatToCurrency(player.getBalance()));
        tieButton.setText("Tie");
        bankerButton.setText("Banker");
        playerButton.setText("Player");
    }

    @FXML
    private void handlePlaceWagerButtonAction(ActionEvent event) {
        if (chipToggleGroup.getSelectedToggle() == null) {
            messageLabel.setText("No chips selected");
            return;
        }

        messageLabel.setText("");
        String wagerAmount = chipToggleGroup.getSelectedToggle().getUserData().toString();
        boolean wagerPlaced;

        if (event.getSource() == tieButton) {
            wagerPlaced = player.placeWager(Outcome.TIE, wagerAmount);
            if (wagerPlaced) {
                tieButton.setText("Tie\n" + formatToCurrency(player.checkWager(Outcome.TIE)));
            }
        }
        else if (event.getSource() == bankerButton) {
            wagerPlaced = player.placeWager(Outcome.BANKER, wagerAmount);
            if (wagerPlaced) {
                bankerButton.setText("Banker\n" + formatToCurrency(player.checkWager(Outcome.BANKER)));
            }
        }
        else {
            wagerPlaced = player.placeWager(Outcome.PLAYER, wagerAmount);
            if (wagerPlaced) {
                playerButton.setText("Player\n" + formatToCurrency(player.checkWager(Outcome.PLAYER)));
            }
        }

        if (!wagerPlaced) {
            messageLabel.setText("Not enough money");
        }

        balanceNumberLabel.setText(formatToCurrency(player.getBalance()));
    }

    @FXML
    private void handleClearButtonAction(ActionEvent event) {
        player.clear();

        messageLabel.setText("");
        balanceNumberLabel.setText(formatToCurrency(player.getBalance()));
        tieButton.setText("Tie");
        bankerButton.setText("Banker");
        playerButton.setText("Player");
    }

    private String formatToCurrency(Object obj) {
        return NumberFormat.getCurrencyInstance(LOCALE).format(obj);
    }

}
