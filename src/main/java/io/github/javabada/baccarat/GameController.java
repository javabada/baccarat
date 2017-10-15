package io.github.javabada.baccarat;

import io.github.javabada.baccarat.card.Card;
import io.github.javabada.baccarat.game.Coup;
import io.github.javabada.baccarat.game.Outcome;
import io.github.javabada.baccarat.game.Player;
import io.github.javabada.baccarat.game.Shoe;
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
  private Shoe shoe;
  private Coup coup;

  @FXML private Label balanceLabel;
  @FXML private Label shoeCountLabel;
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

  public void setPlayer(Player player) {
    this.player = player;
  }

  public void setShoe(Shoe shoe) {
    this.shoe = shoe;
  }

  // Cannot use FXML initialize() method, player and shoe needs to be set first
  public void init() {
    balanceLabel.setText(formatCurrency(player.getBalance()));
    shoeCountLabel.setText(Integer.toString(shoe.count()));
    tieButton.setText("Tie");
    bankerButton.setText("Banker");
    playerButton.setText("Player");
    playButton.setText("Begin");
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
    if (playButton.getText().equals("Begin")) {
      shoe.fill();
      int cardsBurnt = shoe.burn();

      shoeCountLabel.setText(Integer.toString(shoe.count()));
      messageLabel.setText("New shoe, " + cardsBurnt + " " +
          (cardsBurnt == 1 ? "card" : "cards") + " burnt");
      playButton.setText("Play");
    } else if (playButton.getText().equals("Play")) {
      coup = new Coup(shoe);
      coup.play();

      shoeCountLabel.setText(Integer.toString(shoe.count()));
      if (coup.getOutcome() == Outcome.TIE) {
        messageLabel.setText("Tie");
      } else if (coup.getOutcome() == Outcome.BANKER) {
        messageLabel.setText("Banker wins");
      } else {
        messageLabel.setText("Player wins");
      }
      tieButton.setDisable(true);
      bankerButton.setDisable(true);
      playerButton.setDisable(true);
      playButton.setText("Continue");
      clearButton.setDisable(true);

      displayCards(coup);
    } else {
      player.settleWagers(coup.getOutcome());

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

  private void displayCards(Coup coup) {
    playerCardImageView1.setImage(loadCardImage(coup.getPlayerCard1()));
    bankerCardImageView1.setImage(loadCardImage(coup.getBankerCard1()));
    playerCardImageView2.setImage(loadCardImage(coup.getPlayerCard2()));
    bankerCardImageView2.setImage(loadCardImage(coup.getBankerCard2()));
    if (coup.getPlayerCard3() != null) {
      playerCardImageView3.setImage(loadCardImage(coup.getPlayerCard3()));
    }
    if (coup.getBankerCard3() != null) {
      bankerCardImageView3.setImage(loadCardImage(coup.getBankerCard3()));
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
