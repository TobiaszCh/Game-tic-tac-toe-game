package com.kodilla.Tic_Tac_Toe;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class TicTacToe extends Application {

    private final List<FieldButton> buttons = new ArrayList<>();
    GridPane grid = new GridPane();
    private final String empty = "";
    Random random = new Random();
    private boolean easyLevel = false;
    private boolean mediumLevel = false;
//    private boolean hardLevel = false;
    private boolean playerss = false;
    private String p1;
    private boolean endRound = true;
    private boolean endRound1 = true;
    private boolean levelStop = true;
    private int score = 0;
    private int score1 = 0;
    private int tie = 0;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {


        Button newGame = new Button("New game");
        newGame.setOnAction(event -> {
            for (int i = 0; i < 9; i++) {
                buttons.get(i).signEmpty();
                buttons.get(i).setDisable(false);
                endRound = true;
                levelStop = true;
                endRound1 = true;

            }
        });
        newGame.setPrefWidth(200);
        grid.add(newGame, 0, 3);

        Button button2 = new Button("Result");
        button2.setPrefWidth(200);
        grid.add(button2, 1, 3);
        button2.setOnAction(restart -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Winner");
                    alert.setHeaderText("Result: " + score + " : " + score1 + "\n" + "Draws: " + tie);
                    alert.show();
                }
        );

        Button endGame = new Button("End game");
        endGame.setPrefWidth(200);
        grid.add(endGame, 2, 3);
        endGame.setOnAction(exit -> Platform.exit());

        FieldButton field1 = new FieldButton();
        FieldButton field2 = new FieldButton();
        FieldButton field3 = new FieldButton();
        FieldButton field4 = new FieldButton();
        FieldButton field5 = new FieldButton();
        FieldButton field6 = new FieldButton();
        FieldButton field7 = new FieldButton();
        FieldButton field8 = new FieldButton();
        FieldButton field9 = new FieldButton();

        buttons.add(field1);
        buttons.add(field2);
        buttons.add(field3);
        buttons.add(field4);
        buttons.add(field5);
        buttons.add(field6);
        buttons.add(field7);
        buttons.add(field8);
        buttons.add(field9);


        grid.add(field1, 0, 0);
        grid.add(field2, 1, 0);
        grid.add(field3, 2, 0);
        grid.add(field4, 0, 1);
        grid.add(field5, 1, 1);
        grid.add(field6, 2, 1);
        grid.add(field7, 0, 2);
        grid.add(field8, 1, 2);
        grid.add(field9, 2, 2);

        for (int i = 0; i < 9; i++) {
            buttons.get(i).setOnAction(this::handle);
        }
        Scene scene = new Scene(grid,600, 600);

        Button players = new Button("Player vs Player");
        players.setOnAction(event -> {
            playerss = true;
            primaryStage.setScene(scene);
        });
        players.setPrefSize(300,50);
        players.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Button easy = new Button("Easy");
        easy.setOnAction(event -> {
            easyLevel = true;
            primaryStage.setScene(scene);
        });
        easy.setPrefSize(300,50);
        easy.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Button medium = new Button("Medium");
        medium.setOnAction(event -> {
            mediumLevel = true;
            primaryStage.setScene(scene);
        });
        medium.setPrefSize(300,50);
        medium.setFont(Font.font("Arial", FontWeight.BOLD, 18));

/*        Button hard = new Button("Hard");
        hard.setOnAction(event -> {
            hardLevel = true;
            primaryStage.setScene(scene);
        });
        hard.setPrefSize(300,50);
        hard.setFont(Font.font("Arial", FontWeight.BOLD, 18));*/

        VBox vBox = new VBox();
        vBox.getChildren().addAll(players ,easy, medium);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(0,0,0,0));
        vBox.setSpacing(50);


        Scene main = new Scene(vBox, 600, 600);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(main);
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(true);
        primaryStage.show();

    }

    public void handle(ActionEvent event) {
        for (int i = 0; i < 9; i++) {
            if (buttons.get(i) == event.getSource()) {
                 if (easyLevel) {
                    buttons.get(i).signX();
                    checkScore();
                    if (endRound) {
                        easyLevel();
                        checkScore();
                    }
                }
                else if (mediumLevel) {
                    buttons.get(i).signX();
                    checkScore();
                    if (endRound) {
                        mediumLevel();
                        checkScore();
                    }
                }
 /*               else if (hardLevel) {
                    buttons.get(i).signX();
                    checkScore();
                     if (endRound) {
                         hardLevel();
                         checkScore();
                     }
                }*/
                else if (playerss) {
                    buttons.get(i).signX();
                    checkScore();
                    playerss = false;
                    if (endRound) {
                        checkScore();
                    }
                }
                else {
                    buttons.get(i).signO();
                    checkScore();
                    playerss = true;
                    if (endRound) {
                        checkScore();
                    }
                }
            }
        }
    }

    private void  checkScore() {
        int a = 0;
        int b = 0;
        for (int i = 0; i < 3; i++) {

            if (buttons.get(a).getText().equals(buttons.get(a + 1).getText()) && buttons.get(a).getText().equals(buttons.get(a + 2).getText()) && !Objects.equals(buttons.get(a).getText(), empty)) {
                if (Objects.equals(buttons.get(a).getText(), "X")) {
                    p1 = "You win ";
                    score++;
                }
                else if (Objects.equals(buttons.get(a).getText(), "O")) {
                    if (easyLevel || mediumLevel) {
                        p1 = "The computer wins ";
                    }
                    else {
                        p1 = "Your friend wins ";
                    }
                    score1++;
                }
                endRound();
                endRound = false;
                for (int j = 0; j < 9; j++) {
                    buttons.get(j).setDisable(true);

                }
            }
            if (buttons.get(i).getText().equals(buttons.get(b + 3).getText()) && buttons.get(i).getText().equals(buttons.get(b + 6).getText()) && !Objects.equals(buttons.get(i).getText(), empty)) {
                if (Objects.equals(buttons.get(i).getText(), "X")) {
                    p1 = "You win ";
                    score++;
                }
                else if (Objects.equals(buttons.get(i).getText(), "O")) {
                    if (easyLevel || mediumLevel) {
                        p1 = "The computer wins ";
                    }
                    else {
                        p1 = "Your friend wins ";
                    }
                    score1++;
                }
                endRound();
                endRound = false;
                for (int j = 0; j < 9; j++) {
                    buttons.get(j).setDisable(true);

                }
            }
            a = a + 3;
            b++;

        }
        if (buttons.get(0).getText().equals(buttons.get(4).getText()) && buttons.get(0).getText().equals(buttons.get(8).getText()) && !Objects.equals(buttons.get(0).getText(), empty)) {
            if (Objects.equals(buttons.get(0).getText(), "X")) {
                p1 = "You win ";
                score++;
            }
            else if (Objects.equals(buttons.get(0).getText(), "O")) {
                if (easyLevel || mediumLevel) {
                    p1 = "The computer wins ";
                }
                else {
                    p1 = "Your friend wins ";
                }
                score1++;
            }
            endRound();
            endRound = false;
            for (int j = 0; j < 9; j++) {
                buttons.get(j).setDisable(true);
            }
        }
        if (buttons.get(2).getText().equals(buttons.get(4).getText()) && buttons.get(2).getText().equals(buttons.get(6).getText()) && !Objects.equals(buttons.get(2).getText(), empty)) {
            if (Objects.equals(buttons.get(2).getText(), "X")) {
                p1 = "You win ";
                score++;
            }
            else if (Objects.equals(buttons.get(2).getText(), "O")) {
                if (easyLevel || mediumLevel) {
                    p1 = "The computer wins ";
                }
                else {
                    p1 = "Your friend wins ";
                }
                score1++;
            }
            endRound();
            endRound = false;
            for (int j = 0; j < 9; j++) {
                buttons.get(j).setDisable(true);

            }
        }
        int c = 0;
        for (int i = 0; i < 9 ; i++) {
            if (!Objects.equals(buttons.get(i).getText(), empty)) {
                c++;
            }
        }
        if (c ==9 && endRound1) {
            endRound = false;
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Result");
            alert.setHeaderText("Tie!");
            alert.show();
            tie++;

        }

    }
    public void easyLevel() {
        for (int i = 0; i < 9; i++) {
            if (!Objects.equals(buttons.get(i).getText(), "X") && !Objects.equals(buttons.get(i).getText(), "O")) {
                buttons.get(i).signO();
                break;
            }
        }
    }
    public void mediumLevel() {
        int a = 0;
        int b = 0;

        for (int i = 0; i < 3; i++) {

            if (Objects.equals(buttons.get(a).getText(), empty) && Objects.equals(buttons.get(a + 1).getText(), "O") && Objects.equals(buttons.get(a + 2).getText(), "O")) {
                buttons.get(a).signO();
                levelStop = false;

            } else if (Objects.equals(buttons.get(a + 1).getText(), empty) && Objects.equals(buttons.get(a).getText(), "O") && Objects.equals(buttons.get(a + 2).getText(), "O")) {
                buttons.get(a + 1).signO();
                levelStop = false;

            } else if (Objects.equals(buttons.get(a + 2).getText(), empty) && Objects.equals(buttons.get(a).getText(), "O") && Objects.equals(buttons.get(a + 1).getText(), "O")) {
                buttons.get(a + 2).signO();
                levelStop = false;
            }

            else if (Objects.equals(buttons.get(i).getText(), empty) && Objects.equals(buttons.get(b + 3).getText(), "O") && Objects.equals(buttons.get(b + 6).getText(), "O")) {
                buttons.get(i).signO();
                levelStop = false;
            } else if (Objects.equals(buttons.get(b + 3).getText(), empty) && Objects.equals(buttons.get(i).getText(), "O") && Objects.equals(buttons.get(b + 6).getText(), "O")) {
                buttons.get(b + 3).signO();
                levelStop = false;
            } else if (Objects.equals(buttons.get(b + 6).getText(), empty) && Objects.equals(buttons.get(i).getText(), "O") && Objects.equals(buttons.get(b + 3).getText(), "O")) {
                buttons.get(b + 6).signO();
                levelStop = false;
            }
            a = a + 3;
            b++;
        }

        if (Objects.equals(buttons.get(0).getText(), empty) && Objects.equals(buttons.get(4).getText(), "O") && Objects.equals(buttons.get(8).getText(), "O")) {
            buttons.get(0).signO();
            levelStop = false;
        } else if (Objects.equals(buttons.get(4).getText(), empty) && Objects.equals(buttons.get(0).getText(), "O") && Objects.equals(buttons.get(8).getText(), "O")) {
            buttons.get(4).signO();
            levelStop = false;
        } else if (Objects.equals(buttons.get(8).getText(), empty) && Objects.equals(buttons.get(0).getText(), "O") && Objects.equals(buttons.get(4).getText(), "O")) {
            buttons.get(8).signO();
            levelStop = false;
        }

        else if (Objects.equals(buttons.get(2).getText(), empty) && Objects.equals(buttons.get(4).getText(), "O") && Objects.equals(buttons.get(6).getText(), "O")) {
            buttons.get(2).signO();
            levelStop = false;
        } else if (Objects.equals(buttons.get(4).getText(), empty) && Objects.equals(buttons.get(2).getText(), "O") && Objects.equals(buttons.get(6).getText(), "O")) {
            buttons.get(4).signO();
            levelStop = false;
        } else if (Objects.equals(buttons.get(6).getText(), empty) && Objects.equals(buttons.get(2).getText(), "O") && Objects.equals(buttons.get(4).getText(), "O")) {
            buttons.get(6).signO();
            levelStop = false;
        }
        if (levelStop) {

            for (int i = 0; i < 9; i++) {
                int number = random.nextInt(9);
                if (!Objects.equals(buttons.get(number).getText(), "X") && !Objects.equals(buttons.get(number).getText(), "O")) {
                    buttons.get(number).signO();
                    break;
                } else if (!Objects.equals(buttons.get(i).getText(), "X") && !Objects.equals(buttons.get(i).getText(), "O")) {
                    buttons.get(i).signO();
                    break;
                }
            }
        }
    }

 /*   public void hardLevel() {

        if (levelStop) {
            int s = 0;
            for (int i = 0; i < 9; i++) {
                if (Objects.equals(buttons.get(i).getText(), empty) && !(Objects.equals(buttons.get(4).getText(), "X"))) {
                    s++;
                }
            }
            if (s == 8) {
                buttons.get(4).signO();
            } else if ((Objects.equals(buttons.get(4).getText(), "X") && (Objects.equals(buttons.get(0).getText(), empty)))) {
                buttons.get(0).signO();
            }
            levelStop = false;
        }
        int a = 0;
        int b = 0;
        for (int i = 0; i < 3; i++) {

            if (Objects.equals(buttons.get(a).getText(), empty) || Objects.equals(buttons.get(a + 1).getText(), empty) || Objects.equals(buttons.get(a + 2).getText(), empty)) {
                if (Objects.equals(buttons.get(a).getText(), empty) && Objects.equals(buttons.get(a + 1).getText(), "O") && Objects.equals(buttons.get(a + 2).getText(), "O")) {
                    buttons.get(a).signO();
                } else if (Objects.equals(buttons.get(a).getText(), empty) && !Objects.equals(buttons.get(a + 1).getText(), empty) && !Objects.equals(buttons.get(a + 2).getText(), empty)) {
                    buttons.get(a).signO();
                }
                else {
                    easyLevel();
                }
            }

            } else if (Objects.equals(buttons.get(a + 1).getText(), empty) && Objects.equals(buttons.get(a).getText(), "O") && Objects.equals(buttons.get(a + 2).getText(), "O")) {
                buttons.get(a + 1).signO();
                levelStop = false;

            } else if (Objects.equals(buttons.get(a + 2).getText(), empty) && Objects.equals(buttons.get(a).getText(), "O") && Objects.equals(buttons.get(a + 1).getText(), "O")) {
                buttons.get(a + 2).signO();
                levelStop = false;
            } else if (Objects.equals(buttons.get(i).getText(), empty) && Objects.equals(buttons.get(b + 3).getText(), "O") && Objects.equals(buttons.get(b + 6).getText(), "O")) {
                buttons.get(i).signO();
                levelStop = false;
            } else if (Objects.equals(buttons.get(b + 3).getText(), empty) && Objects.equals(buttons.get(i).getText(), "O") && Objects.equals(buttons.get(b + 6).getText(), "O")) {
                buttons.get(b + 3).signO();
                levelStop = false;
            } else if (Objects.equals(buttons.get(b + 6).getText(), empty) && Objects.equals(buttons.get(i).getText(), "O") && Objects.equals(buttons.get(b + 3).getText(), "O")) {
                buttons.get(b + 6).signO();
                levelStop = false;
            }
            a = a + 3;
            b++;
        }

        if (Objects.equals(buttons.get(0).getText(), empty) && Objects.equals(buttons.get(4).getText(), "O") && Objects.equals(buttons.get(8).getText(), "O")) {
            buttons.get(0).signO();
            levelStop = false;
        } else if (Objects.equals(buttons.get(4).getText(), empty) && Objects.equals(buttons.get(0).getText(), "O") && Objects.equals(buttons.get(8).getText(), "O")) {
            buttons.get(4).signO();
            levelStop = false;
        } else if (Objects.equals(buttons.get(8).getText(), empty) && Objects.equals(buttons.get(0).getText(), "O") && Objects.equals(buttons.get(4).getText(), "O")) {
            buttons.get(8).signO();
            levelStop = false;
        } else if (Objects.equals(buttons.get(2).getText(), empty) && Objects.equals(buttons.get(4).getText(), "O") && Objects.equals(buttons.get(6).getText(), "O")) {
            buttons.get(2).signO();
            levelStop = false;
        } else if (Objects.equals(buttons.get(4).getText(), empty) && Objects.equals(buttons.get(2).getText(), "O") && Objects.equals(buttons.get(6).getText(), "O")) {
            buttons.get(4).signO();
            levelStop = false;
        } else if (Objects.equals(buttons.get(6).getText(), empty) && Objects.equals(buttons.get(2).getText(), "O") && Objects.equals(buttons.get(4).getText(), "O")) {
            buttons.get(6).signO();
            levelStop = false;
        }
        a = 0;
        b = 0;

            for (int i = 0; i < 3; i++) {

                if (Objects.equals(buttons.get(a).getText(), empty) && !Objects.equals(buttons.get(a + 1).getText(), empty) && !Objects.equals(buttons.get(a + 2).getText(), empty)) {
                    buttons.get(a).signO();
                    levelStop = false;

                } else if (Objects.equals(buttons.get(a + 1).getText(), empty) && !Objects.equals(buttons.get(a).getText(), empty) && !Objects.equals(buttons.get(a + 2).getText(), empty)) {
                    buttons.get(a + 1).signO();
                    levelStop = false;

                } else if (Objects.equals(buttons.get(a + 2).getText(), empty) && !Objects.equals(buttons.get(a).getText(), empty) && !Objects.equals(buttons.get(a + 1).getText(), empty)) {
                    buttons.get(a + 2).signO();
                    levelStop = false;
                } else if (Objects.equals(buttons.get(i).getText(), empty) && !Objects.equals(buttons.get(b + 3).getText(), empty) && !Objects.equals(buttons.get(b + 6).getText(), empty)) {
                    buttons.get(i).signO();
                    levelStop = false;
                } else if (Objects.equals(buttons.get(b + 3).getText(), empty) && !Objects.equals(buttons.get(i).getText(), empty) && !Objects.equals(buttons.get(b + 6).getText(), empty)) {
                    buttons.get(b + 3).signO();
                    levelStop = false;
                } else if (Objects.equals(buttons.get(b + 6).getText(), empty) && !Objects.equals(buttons.get(i).getText(), empty) && !Objects.equals(buttons.get(b + 3).getText(), empty)) {
                    buttons.get(b + 6).signO();
                    levelStop = false;
                }
                a = a + 3;
                b++;
            }


            if (Objects.equals(buttons.get(0).getText(), empty) && !Objects.equals(buttons.get(4).getText(), empty) && !Objects.equals(buttons.get(8).getText(), empty)) {
                buttons.get(0).signO();
                levelStop = false;
            } else if (Objects.equals(buttons.get(4).getText(), empty) && !Objects.equals(buttons.get(0).getText(), empty) && !Objects.equals(buttons.get(8).getText(), empty)) {
                buttons.get(4).signO();
                levelStop = false;
            } else if (Objects.equals(buttons.get(8).getText(), empty) && !Objects.equals(buttons.get(0).getText(), empty) && !Objects.equals(buttons.get(4).getText(), empty)) {
                buttons.get(8).signO();
                levelStop = false;
            } else if (Objects.equals(buttons.get(2).getText(), empty) && !Objects.equals(buttons.get(4).getText(), empty) && !Objects.equals(buttons.get(6).getText(), empty)) {
                buttons.get(2).signO();
                levelStop = false;
            } else if (Objects.equals(buttons.get(4).getText(), empty) && !Objects.equals(buttons.get(2).getText(), empty) && !Objects.equals(buttons.get(6).getText(), empty)) {
                buttons.get(4).signO();
                levelStop = false;
            } else if (Objects.equals(buttons.get(6).getText(), empty) && !Objects.equals(buttons.get(2).getText(), empty) && !Objects.equals(buttons.get(4).getText(), empty)) {
                buttons.get(6).signO();
                levelStop = false;
            }

/*        if (Objects.equals(buttons.get(1).getText(), "X") && Objects.equals(buttons.get(3).getText(), empty) && Objects.equals(buttons.get(7).getText(), empty)
                && Objects.equals(buttons.get(5).getText(), "X") && Objects.equals(buttons.get(4).getText(), "O")){
            buttons.get(0).signO();
        }
        else if (Objects.equals(buttons.get(5).getText(), "X") && Objects.equals(buttons.get(1).getText(), empty) && Objects.equals(buttons.get(3).getText(), empty)
                && Objects.equals(buttons.get(7).getText(), "X") && Objects.equals(buttons.get(4).getText(), "O")) {
            buttons.get(0).signO();
        }
        else if (Objects.equals(buttons.get(7).getText(), "X") && Objects.equals(buttons.get(5).getText(), empty) && Objects.equals(buttons.get(1).getText(), empty)
                && Objects.equals(buttons.get(3).getText(), "X") && Objects.equals(buttons.get(4).getText(), "O")) {
            buttons.get(0).signO();
        }
        else if (Objects.equals(buttons.get(3).getText(), "X") && Objects.equals(buttons.get(7).getText(), empty) && Objects.equals(buttons.get(5).getText(), empty)
                && Objects.equals(buttons.get(1).getText(), "X") && Objects.equals(buttons.get(4).getText(), "O")) {
            buttons.get(0).signO();
        }
        else if (Objects.equals(buttons.get(1).getText(), "X") && Objects.equals(buttons.get(3).getText(), empty) && Objects.equals(buttons.get(5).getText(), empty)
                && Objects.equals(buttons.get(7).getText(), "X") && Objects.equals(buttons.get(4).getText(), "O")) {
            buttons.get(0).signO();
        }
        else if (Objects.equals(buttons.get(3).getText(), "X") && Objects.equals(buttons.get(1).getText(), empty) && Objects.equals(buttons.get(7).getText(), empty)
                && Objects.equals(buttons.get(5).getText(), "X") && Objects.equals(buttons.get(4).getText(), "O")) {
            buttons.get(0).signO();
        }


        }*/

    public void endRound() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Winner");
        alert.setHeaderText(p1 + "this round!");
        alert.show();
        endRound1 = false;

    }
}

