package org.example.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.example.Constants;
import org.example.Main;

import java.io.IOException;
import java.util.Objects;

public class DieSelectController implements Controller{
    public DieSelectController() {
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public Parent render() throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("view/DieSelect.fxml")));

        ChoiceBox dieSelectBox = (ChoiceBox) parent.lookup("#dieSelect");
        Button bonusSubButton = (Button) parent.lookup("#bonusSub");
        TextField bonusField = (TextField) parent.lookup("#bonusField");
        Button bonusAddButton = (Button) parent.lookup("#bonusAdd");
        Button bonusResetButton = (Button) parent.lookup("#bonusReset");

        dieSelectBox.setItems(FXCollections.observableArrayList(Constants.DICE_NAME));
        dieSelectBox.setValue(Constants.DICE_NAME.get(0));

        bonusSubButton.setOnAction(action -> {
            int bonus = Integer.parseInt(bonusField.getText());
            if (bonus > 0) {
                bonus--;
            }
            bonusField.setText(String.valueOf(bonus));
        });

        bonusAddButton.setOnAction(action -> {
            int bonus = Integer.parseInt(bonusField.getText());
            if (bonus < 100) {
                bonus++;
            }
            bonusField.setText(String.valueOf(bonus));
        });

        bonusResetButton.setOnAction(action -> {
            bonusField.setText("0");
        });

        return parent;
    }

    @Override
    public void destroy() {

    }
}
