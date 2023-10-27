package org.example.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.Main;

import java.io.IOException;
import java.util.Objects;

public class DieController implements Controller {
    private int count = 0;
    private Label nameLabel;
    private Button subButton;
    private TextField countField;
    private Button addButton;
    private Button resetButton;

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public Parent render() throws IOException {
        return null;
    }

    public Parent render(int count) throws IOException {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("view/Die.fxml")));

        nameLabel = (Label) parent.lookup("#nameLabel");
        subButton = (Button) parent.lookup("#subButton");
        countField = (TextField) parent.lookup("#countField");
        addButton = (Button) parent.lookup("#addButton");
        resetButton = (Button) parent.lookup("#resetButton");

        if (count == 0){
            createDie(2);
        } else if (count == 1) {
            createDie(6);
        } else if (count == 2) {
            createDie(10);
        } else if (count == 3) {
            createDie(20);
        } else if (count == 4) {
            createDie(4);
        } else if (count == 5) {
            createDie(8);
        } else if (count == 6) {
            createDie(12);
        }else if (count == 7){
            createDie(100);
        }

        return parent;
    }

    private void createDie(int die) {

        if (die == 2){
            nameLabel.setText("d2:");
        } else if (die == 4) {
            nameLabel.setText("d4:");
        } else if (die == 6) {
            nameLabel.setText("d6:");
        } else if (die == 8) {
            nameLabel.setText("d8:");
        } else if (die == 10) {
            nameLabel.setText("d10:");
        } else if (die == 12) {
            nameLabel.setText("d12:");
        } else if (die == 20) {
            nameLabel.setText("d20:");
        } else if (die == 100) {
            nameLabel.setText("d100:");
        }

        countField.setId(countField.getId()+die);

        subButton.setOnAction(action -> {
            int bonus = Integer.parseInt(countField.getText());
            if (bonus > 0) {
                bonus--;
            }
            countField.setText(String.valueOf(bonus));
        });

        addButton.setOnAction(action -> {
            int bonus = Integer.parseInt(countField.getText());
            if (bonus < 100) {
                bonus++;
            }
            countField.setText(String.valueOf(bonus));
        });

        resetButton.setOnAction(action -> {
            countField.setText("0");
        });
    }

    @Override
    public void destroy() {

    }
}
