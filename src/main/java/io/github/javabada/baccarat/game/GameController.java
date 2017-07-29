package io.github.javabada.baccarat.game;

import io.github.javabada.baccarat.card.Card;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.text.NumberFormat;
import java.util.Locale;

public class GameController {

  private Player player;
  private Game game;
  private Coup currentCoup;
  private boolean coupPlayed;

  @FXML private Label balanceLabel;
  @FXML private Label messageLabel;
  @FXML private Button tieButton;
  @FXML private Button bankerButton;
  @FXML private Button playerButton;
  @FXML private Button playButton;
  @FXML private Button clearButton;
  @FXML private ToggleGroup chipsToggleGroup;
  @FXML private ImageView playerCardImageView1;
  @FXML private ImageView playerCardImageView2;
  @FXML private ImageView playerCardImageView3;
  @FXML private ImageView bankerCardImageView1;
  @FXML private ImageView bankerCardImageView2;
  @FXML private ImageView bankerCardImageView3;

  @FXML
  private void initialize() {
    player = new Player("10000");
    game = new Game();
    game.newShoe(8);
    coupPlayed = false;

    balanceLabel.setText(formatCurrency(player.getBalance()));
    tieButton.setText("Tie");
    bankerButton.setText("Banker");
    playerButton.setText("Player");
    playButton.setText("Play");
    clearButton.setDisable(true);
  }

  @FXML
  private void onPlaceWagerAction(ActionEvent event) {
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
      messageLabel.setText("Insufficient funds");
    }
  }

  @FXML
  private void onPlayButtonAction() {
    if (!coupPlayed) {
      currentCoup = game.playNewCoup();
      coupPlayed = true;

      if (currentCoup.getOutcome() == Outcome.TIE) {
        messageLabel.setText("Tie");
      } else if (currentCoup.getOutcome() == Outcome.BANKER) {
        messageLabel.setText("Banker wins");
      } else {
        messageLabel.setText("Player wins");
      }
      tieButton.setDisable(true);
      bankerButton.setDisable(true);
      playerButton.setDisable(true);
      playButton.setText("Continue");
      clearButton.setDisable(true);

      displayCards();
    } else {
      player.settleWagers(currentCoup.getOutcome());
      coupPlayed = false;

      balanceLabel.setText(formatCurrency(player.getBalance()));
      messageLabel.setText("");
      tieButton.setText("Tie");
      tieButton.setDisable(false);
      bankerButton.setText("Banker");
      bankerButton.setDisable(false);
      playerButton.setText("Player");
      playerButton.setDisable(false);
      playButton.setText("Play");

      clearCards();
    }
  }

  @FXML
  private void onClearButtonAction() {
    player.clearWagers();

    balanceLabel.setText(formatCurrency(player.getBalance()));
    messageLabel.setText("");
    tieButton.setText("Tie");
    bankerButton.setText("Banker");
    playerButton.setText("Player");
    clearButton.setDisable(true);
  }

  private String formatCurrency(Object o) {
    return NumberFormat.getCurrencyInstance(Locale.US).format(o);
  }

  private void displayCards() {
    playerCardImageView1.setImage(loadCardImage(currentCoup.getPlayerCard1()));
    bankerCardImageView1.setImage(loadCardImage(currentCoup.getBankerCard1()));
    playerCardImageView2.setImage(loadCardImage(currentCoup.getPlayerCard2()));
    bankerCardImageView2.setImage(loadCardImage(currentCoup.getBankerCard2()));
    if (currentCoup.getPlayerCard3() != null) {
      playerCardImageView3.setImage(loadCardImage(currentCoup.getPlayerCard3()));
    }
    if (currentCoup.getBankerCard3() != null) {
      bankerCardImageView3.setImage(loadCardImage(currentCoup.getBankerCard3()));
    }
  }

  private Image loadCardImage(Card card) {
    return new Image("/images/cards/" + card.toString().replace(" ", "_") + ".png");
  }

  private void clearCards() {
    playerCardImageView1.setImage(null);
    playerCardImageView2.setImage(null);
    playerCardImageView3.setImage(null);
    bankerCardImageView1.setImage(null);
    bankerCardImageView2.setImage(null);
    bankerCardImageView3.setImage(null);
  }

}
