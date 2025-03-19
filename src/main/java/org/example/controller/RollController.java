package org.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.example.App;
import org.example.Main;
import org.example.service.RandomService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static org.example.Constants.*;

public class RollController implements Controller {

    private final App app;
    private final RandomService randomService;
    private final ArrayList<Controller> subControllers = new ArrayList<>();

    public RollController(App app, RandomService randomService) {
        this.app = app;
        this.randomService = randomService;
    }

    public RollController(App app) {
        this.app = app;
        this.randomService = new RandomService();
    }

    @Override
    public String getTitle() {
        return "Dice";
    }

    @Override
    public void init() {

    }

    @Override
    public Parent render() throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("view/Roll.fxml")));

        // tab singe roll
        createSingleRollTab(parent);

        // tab advanced roll
        createAdvancedRollTab(parent);

        // attack roll
        createAttackRollTab(parent);

        // tab roll stats
        createRollStatsTab(parent);

        return parent;
    }

    private void createSingleRollTab(Parent parent) {
        Button d2Button = (Button) parent.lookup("#d2Button");
        Button d4Button = (Button) parent.lookup("#d4Button");
        Button d6Button = (Button) parent.lookup("#d6Button");
        Button d8Button = (Button) parent.lookup("#d8Button");
        Button d12Button = (Button) parent.lookup("#d10Button");
        Button d10Button = (Button) parent.lookup("#d12Button");
        Button d20Button = (Button) parent.lookup("#d20Button");
        Button d100Button = (Button) parent.lookup("#d100Button");
        Button bonusSubButton = (Button) parent.lookup("#bonusSub");
        TextField bonusText = (TextField) parent.lookup("#bonusField");
        Button bonusAddButton = (Button) parent.lookup("#bonusAdd");
        Button bonusResetButton = (Button) parent.lookup("#bonusReset");
        Label resultLabel = (Label) parent.lookup("#resultLabelSingle");

        resultLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);

        d2Button.setOnAction(action -> {
            int result = randomService.roll(2);
            int bonus = Integer.parseInt(bonusText.getText());

            resultLabel.setText(createBonusOutput(result, bonus));
            resultLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);
        });

        d4Button.setOnAction(action -> {
            int result = randomService.roll(4);
            int bonus = Integer.parseInt(bonusText.getText());

            resultLabel.setText(createBonusOutput(result, bonus));
            resultLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);
        });

        d6Button.setOnAction(action -> {
            int result = randomService.roll(6);
            int bonus = Integer.parseInt(bonusText.getText());

            resultLabel.setText(createBonusOutput(result, bonus));
            resultLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);
        });

        d8Button.setOnAction(action -> {
            int result = randomService.roll(8);
            int bonus = Integer.parseInt(bonusText.getText());

            resultLabel.setText(createBonusOutput(result, bonus));
            resultLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);
        });

        d10Button.setOnAction(action -> {
            int result = randomService.roll(10);
            int bonus = Integer.parseInt(bonusText.getText());

            resultLabel.setText(createBonusOutput(result, bonus));
            resultLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);
        });

        d12Button.setOnAction(action -> {
            int result = randomService.roll(12);
            int bonus = Integer.parseInt(bonusText.getText());

            resultLabel.setText(createBonusOutput(result, bonus));
            resultLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);
        });

        d20Button.setOnAction(action -> {
            int result = randomService.roll(20);
            int bonus = Integer.parseInt(bonusText.getText());

            resultLabel.setText(createBonusOutput(result, bonus));

            if (result == 1) {
                resultLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_RED);
            } else if (result == 20) {
                resultLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_GREEN);
            } else {
                resultLabel.setStyle((FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK));
            }
        });

        d100Button.setOnAction(action -> {
            int result = randomService.roll(100);
            int bonus = Integer.parseInt(bonusText.getText());

            resultLabel.setText(createBonusOutput(result, bonus));
            resultLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);
        });

        bonusSubButton.setOnAction(action -> {
            int bonus = Integer.parseInt(bonusText.getText());
            if (bonus > -100) {
                bonus--;
            }
            bonusText.setText(String.valueOf(bonus));
        });

        bonusAddButton.setOnAction(action -> {
            int bonus = Integer.parseInt(bonusText.getText());
            if (bonus < 100) {
                bonus++;
            }
            bonusText.setText(String.valueOf(bonus));
        });

        bonusResetButton.setOnAction(action -> bonusText.setText("0"));
    }

    private void createAdvancedRollTab(Parent parent) throws IOException {
        VBox dieBoxLeft = (VBox) parent.lookup("#vboxLeft");
        VBox dieBoxRight = (VBox) parent.lookup("#vboxRight");
        Button rollButton = (Button) parent.lookup("#rollButtonAdvanced");
        Label resultLabelAdvanced = (Label) parent.lookup("#resultLabelAdvanced");

        resultLabelAdvanced.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);

        for (int i = 0; i < 8; i++) {
            final DieController dieController = new DieController();
            subControllers.add(dieController);
            dieController.init();

            if (i < 4) {
                dieBoxLeft.getChildren().add(dieController.render(i));
            } else {
                dieBoxRight.getChildren().add(dieController.render(i));
            }
        }

        rollButton.setOnAction(action -> {
            ArrayList<HBox> hBoxes = new ArrayList<>();
            for (int i = 0; i < dieBoxLeft.getChildren().size(); i++) {
                hBoxes.add((HBox) dieBoxLeft.getChildren().get(i));
                hBoxes.add((HBox) dieBoxRight.getChildren().get(i));
            }
            ArrayList<Integer> amounts = new ArrayList<>();
            for (HBox box : hBoxes) {
                TextField amountText = (TextField) box.getChildren().get(2);
                amounts.add(Integer.parseInt(amountText.getText()));
            }
            resultLabelAdvanced.setText(String.valueOf(randomService.roll(amounts)));
            resultLabelAdvanced.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);
        });
    }

    private void createAttackRollTab(Parent parent) throws IOException {
        VBox attackBox = (VBox) parent.lookup("#attackVBox");
        Button rollAttackButton = (Button) parent.lookup("#rollAttackButton");
        Label attackLabel = (Label) parent.lookup("#resultLabelAttackGes");
        HBox labelBox = (HBox) parent.lookup("#labelBox");

        for (Node child : labelBox.getChildren()) {
            Label label = (Label) child;
            label.setAlignment(Pos.CENTER);
        }

        for (int i = 0; i < 8; i++) {
            final DieSelectController dieSelectController = new DieSelectController();
            subControllers.add(dieSelectController);
            dieSelectController.init();

            attackBox.getChildren().add(dieSelectController.render());

            rollAttackButton.setOnAction(action -> {
                ArrayList<HBox> hBoxes = new ArrayList<>();
                for (Object obj : attackBox.getChildren()) {
                    hBoxes.add((HBox) obj);
                }

                int res = 0;

                for (HBox box : hBoxes) {
                    ChoiceBox choiceDie = (ChoiceBox) box.getChildren().get(0);
                    TextField amountText = (TextField) box.getChildren().get(2);
                    TextField bonusText = (TextField) box.getChildren().get(6);
                    CheckBox addCheck = (CheckBox) box.getChildren().get(9);

                    String die = choiceDie.getValue().toString();
                    int amount = Integer.parseInt(amountText.getText());
                    int bonus = Integer.parseInt(bonusText.getText());
                    boolean check = addCheck.isSelected();

                    for (int j = 0; j < amount; j++) {
                        res += randomService.roll(die);
                    }
                    if (check){
                        res += bonus*amount;
                    } else {
                        res += bonus;
                    }
                }
                attackLabel.setText(String.valueOf(res));
                attackLabel.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_COLOR + DISPLAY_BLACK);
            });
        }
    }

    private void createRollStatsTab(Parent parent) {
        VBox statVBox = (VBox) parent.lookup("#statVBox");
        Button rollStatsButton = (Button) parent.lookup("#rollStatsButton");

        rollStatsButton.setOnAction(action -> {
            statVBox.getChildren().clear();

            for (int i = 0; i < 6; i++) {
                ArrayList<Integer> stats = randomService.rollStats();
                createStatsLayout(statVBox, stats);
            }
        });
    }

    private String createBonusOutput(int roll, int bonus) {
        StringBuilder out = new StringBuilder();
        out.append(roll);

        if (bonus > 0) {
            int result = randomService.addBonus(roll, bonus);
            out.append(" + ").append(bonus).append(" = ").append(result);
        } else if (bonus < 0) {
            int result = randomService.addBonus(roll, bonus);
            out.append(" - ").append(bonus * -1).append(" = ").append(result);
        }
        return out.toString();
    }

    private void createStatsLayout(VBox vBox, ArrayList<Integer> stats) {
        StringBuilder sbLeft = new StringBuilder();
        StringBuilder sbRight = new StringBuilder();

        sbLeft.append("[");
        for (int i = 0; i < 3; i++) {
            sbLeft.append(stats.get(i)).append(", ");
        }

        sbRight.append("] = ");
        if (randomService.addStats(stats) < 10) {
            sbRight.append("0");
        }
        sbRight.append(randomService.addStats(stats));

        HBox hBox = new HBox();

        Text textLeft = new Text(sbLeft.toString());
        textLeft.setStyle(FONT_SIZE + SIZE_BIG);

        Text textMid = new Text(String.valueOf(stats.get(3)));
        textMid.setStyle(FONT_SIZE + SIZE_BIG + ";" + TEXT_STRIKE + TRUE);

        Text textRight = new Text(sbRight.toString());
        textRight.setStyle(FONT_SIZE + SIZE_BIG);

        hBox.getChildren().add(textLeft);
        hBox.getChildren().add(textMid);
        hBox.getChildren().add(textRight);

        vBox.getChildren().add(hBox);
    }

    @Override
    public void destroy() {
        subControllers.forEach(Controller::destroy);
    }
}
