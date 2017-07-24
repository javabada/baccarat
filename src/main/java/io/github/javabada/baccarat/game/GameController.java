package io.github.javabada.baccarat.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import java.text.NumberFormat;
import java.util.Locale;

public class GameController {

  private Player player;

  @FXML private Label balanceLabel;
  @FXML private Label messageLabel;
  @FXML private Button tieButton;
  @FXML private Button bankerButton;
  @FXML private Button playerButton;
  @FXML private Button playButton;
  @FXML private Button clearButton;
  @FXML private ToggleGroup chipsToggleGroup;
  @FXML private ImageView playerCard1;
  @FXML private ImageView playerCard2;
  @FXML private ImageView playerCard3;
  @FXML private ImageView bankerCard1;
  @FXML private ImageView bankerCard2;
  @FXML private ImageView bankerCard3;

  @FXML
  private void initialize() {
    player = new Player("10000");

    balanceLabel.setText(formatCurrency(player.getBalance()));
    tieButton.setText(tieButton.getUserData().toString());
    bankerButton.setText(bankerButton.getUserData().toString());
    playerButton.setText(playerButton.getUserData().toString());
    playButton.setText("Play");
    clearButton.setDisable(true);
  }

  @FXML
  private void placeWagerAction(ActionEvent event) {
    if (chipsToggleGroup.getSelectedToggle() == null) {
      messageLabel.setText("Please select a chip");
      return;
    }

    String chipValue = chipsToggleGroup.getSelectedToggle().getUserData().toString();

    Button wagerButton;
    Outcome wagerOutcome;

    if (event.getSource() == tieButton) {
      wagerButton = tieButton;
      wagerOutcome = Outcome.TIE;
    } else if (event.getSource() == bankerButton) {
      wagerButton = bankerButton;
      wagerOutcome = Outcome.BANKER;
    } else {
      wagerButton = playerButton;
      wagerOutcome = Outcome.PLAYER;
    }

    boolean wagerPlaced = player.placeWager(wagerOutcome, chipValue);

    if (wagerPlaced) {
      balanceLabel.setText(formatCurrency(player.getBalance()));
      messageLabel.setText("");
      wagerButton.setText(wagerButton.getUserData().toString() + "\n" +
          formatCurrency(player.checkWager(wagerOutcome)));
      clearButton.setDisable(false);
    } else {
      messageLabel.setText("Insufficient balance");
    }
  }

  @FXML
  private void clearAction() {
    player.clearWagers();

    balanceLabel.setText(formatCurrency(player.getBalance()));
    messageLabel.setText("");
    tieButton.setText(tieButton.getUserData().toString());
    bankerButton.setText(bankerButton.getUserData().toString());
    playerButton.setText(playerButton.getUserData().toString());
    clearButton.setDisable(true);
  }

  private String formatCurrency(Object o) {
    return NumberFormat.getCurrencyInstance(Locale.US).format(o);
  }

}

  /*
  @FXML
  private void handleActionButtonAction(ActionEvent event) {
    if (coup.isFinished()) {
      player.settleWagers(coup.getWinner());
      coup = new Coup(game.getShoe());

      messageLabel.setText("");
      balanceNumberLabel.setText(formatCurrency(player.getBalance()));
      actionButton.setText("Deal");
      tieButton.setText("Tie");
      tieButton.setDisable(false);
      bankerButton.setText("Banker");
      bankerButton.setDisable(false);
      playerButton.setText("Player");
      playerButton.setDisable(false);
      clearCards();
    }
    else {
      clearButton.setDisable(true);
      tieButton.setDisable(true);
      bankerButton.setDisable(true);
      playerButton.setDisable(true);

      Card card = coup.deal();
      displayCard(card);

      if (coup.isFinished()) {
        actionButton.setText("Continue");
        if (coup.getWinner() == Outcome.PLAYER) {
          messageLabel.setText("Player wins");
        }
        else if (coup.getWinner() == Outcome.BANKER) {
          messageLabel.setText("Banker wins");
        }
        else {
          messageLabel.setText("Tie");
        }
      }
    }
  }

  @FXML
  private void handleClearButtonAction(ActionEvent event) {
    player.clearWagers();
    messageLabel.setText("");
    balanceNumberLabel.setText(formatCurrency(player.getBalance()));
    clearButton.setDisable(true);
    tieButton.setText("Tie");
    bankerButton.setText("Banker");
    playerButton.setText("Player");
  }

  private String formatCurrency(Object o) {
    return NumberFormat.getCurrencyInstance(Locale.US).format(o);
  }

  private void displayCard(Card card) {
    String cardPath = "/images/cards/" + card.toString().replace(" ", "_") + ".png";
    Image cardImage = new Image(cardPath);

    switch (coup.getCardNumber()) {
      case 1:
        playerCardView1.setImage(cardImage);
        break;
      case 2:
        bankerCardView1.setImage(cardImage);
        break;
      case 3:
        playerCardView2.setImage(cardImage);
        break;
      case 4:
        bankerCardView2.setImage(cardImage);
        break;
      case 5:
        if (coup.getCurrentHand() == Coup.Hand.PLAYER) {
          playerCardView3.setImage(cardImage);
        }
        else {
          bankerCardView3.setImage(cardImage);
        }
        break;
      case 6:
        bankerCardView3.setImage(cardImage);
        break;
    }
  }

  private void clearCards() {
    playerCardView1.setImage(null);
    playerCardView2.setImage(null);
    playerCardView3.setImage(null);
    bankerCardView1.setImage(null);
    bankerCardView2.setImage(null);
    bankerCardView3.setImage(null);
  }
  */
