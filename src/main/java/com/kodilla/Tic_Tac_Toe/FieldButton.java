
package com.kodilla.Tic_Tac_Toe;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FieldButton extends Button {

    public FieldButton() {
        this.setPrefSize(200, 200);
        this.setFont(Font.font("Arial", FontWeight.MEDIUM, 70));
    }

    public void signEmpty() {
        this.setText("");
    }

    public void signX() {
        this.setText("X");
        this.setDisable(true);
    }

    public void signO() {
        this.setText("O");
        this.setDisable(true);
    }

}