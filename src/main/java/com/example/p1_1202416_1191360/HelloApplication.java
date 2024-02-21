package com.example.p1_1202416_1191360;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class HelloApplication extends Application {
    private final char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };

    private final Button[][] buttons = new Button[3][3];
    public Label playerLabel = new Label();
    public VBox combineAllHBox = new VBox();
    int counter=0, computerWinsCounter=0, tieCounter=0, playerWinsCounter=0;
    public Label computerWinsCounterLabel = new Label("Computer: " + computerWinsCounter);
    public Label playerWinsCounterLabel = new Label("Player: " + playerWinsCounter);
    public Label tieCounterLabel = new Label("Tie: " + tieCounter);

    @Override
    public void start(Stage stage) {

        Label label = new Label("Tic Tac Toe");
        label.setAlignment(Pos.CENTER);

        label.setFont(Font.font("Chalkboard", FontWeight.BOLD, 70));
        label.setTextFill(Color.BLACK);

        playerLabel.setFont(Font.font("Chalkboard", FontWeight.BOLD, 20));
        playerLabel.setTextFill(Color.BLACK);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setPrefWidth(100);
                buttons[i][j].setPrefHeight(100);
                buttons[i][j].setFont(Font.font("Chalkboard", FontWeight.BOLD, 40));
                buttons[i][j].setStyle("-fx-border-color: #000000; " + "-fx-border-width: 4px");
                buttons[i][j].setTextFill(Color.BLACK);

                BackgroundFill backgroundFill = new BackgroundFill(Color.HONEYDEW, CornerRadii.EMPTY, Insets.EMPTY);
                Background background = new Background(backgroundFill);
                buttons[i][j].setBackground(background);

                int chosenRow = i;
                int chosenColumn = j;
                buttons[i][j].setOnAction(e -> play(chosenRow, chosenColumn));
            }
        }

        Random random = new Random();
        boolean playerFirst = random.nextBoolean();

        if (playerFirst) {
            playerLabel.setText("You Play First!");
        } else {
            playerLabel.setText("Computer Plays First!");
            computerPlays();
            updateUIBoard();
        }


        HBox firstThreeButtons = new HBox();
        firstThreeButtons.getChildren().addAll(buttons[0]);
        firstThreeButtons.setAlignment(Pos.CENTER);

        HBox secondThreeButtons = new HBox();
        secondThreeButtons.getChildren().addAll(buttons[1]);
        secondThreeButtons.setAlignment(Pos.CENTER);

        HBox lastThreeButtons = new HBox();
        lastThreeButtons.getChildren().addAll(buttons[2]);
        lastThreeButtons.setAlignment(Pos.CENTER);

        combineAllHBox.getChildren().addAll(firstThreeButtons, secondThreeButtons, lastThreeButtons);
        combineAllHBox.setAlignment(Pos.CENTER);

        VBox ticTacToeBox = new VBox(20);
        ticTacToeBox.getChildren().addAll(label, combineAllHBox, playerLabel);
        ticTacToeBox.setAlignment(Pos.CENTER);

        HBox displayBox = new HBox(10);
        displayBox.setAlignment(Pos.CENTER);

        playerWinsCounterLabel.setAlignment(Pos.CENTER);
        playerWinsCounterLabel.setFont(Font.font("Chalkboard", FontWeight.BOLD, 25));
        playerWinsCounterLabel.setTextFill(Color.BLACK);

        tieCounterLabel.setAlignment(Pos.CENTER);
        tieCounterLabel.setFont(Font.font("Chalkboard", FontWeight.BOLD, 25));
        tieCounterLabel.setTextFill(Color.BLACK);

        VBox countersBox = new VBox(20);
        countersBox.setAlignment(Pos.CENTER);
        countersBox.getChildren().addAll(playerWinsCounterLabel, tieCounterLabel);

        computerWinsCounterLabel.setAlignment(Pos.CENTER);
        computerWinsCounterLabel.setFont(Font.font("Chalkboard", FontWeight.BOLD, 25));
        computerWinsCounterLabel.setTextFill(Color.BLACK);

        displayBox.getChildren().addAll(countersBox, ticTacToeBox, computerWinsCounterLabel);

        BackgroundFill backgroundFill = new BackgroundFill(Color.DARKSEAGREEN, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(backgroundFill);
        displayBox.setBackground(background);


        Scene scene = new Scene(displayBox, 850, 600);
        stage.setTitle("Artificial Intelligence Second Project");
        stage.setScene(scene);
        stage.show();
    }

    private void resetGame(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText(" ");
                buttons[i][j].setDisable(false);
            }
        }
        combineAllHBox.setDisable(false);

        Random random = new Random();
        boolean playerFirst = random.nextBoolean();

        if (playerFirst) {
            playerLabel.setText("You Play First!");
        } else {
            playerLabel.setText("Computer Plays First!");
            computerPlays();
            updateUIBoard();
        }

        counter++;
        if(computerWinsCounter==3){
            playerLabel.setText("COMPUTER IS THE WINNER!!");
            playerLabel.setFont(Font.font("Chalkboard", FontWeight.BOLD, 40));
            playerLabel.setTextFill(Color.DARKGREEN);
            combineAllHBox.setDisable(true);
        }

        else if(counter==5){
            playerLabel.setText("IT'S A TIE!!");
            playerLabel.setFont(Font.font("Chalkboard", FontWeight.BOLD, 40));
            playerLabel.setTextFill(Color.DARKGREEN);
            combineAllHBox.setDisable(true);
        }
        else if (playerWinsCounter==3){
            playerLabel.setText("YOU ARE THE WINNER!!");
            playerLabel.setFont(Font.font("Chalkboard", FontWeight.BOLD, 40));
            playerLabel.setTextFill(Color.DARKGREEN);
            combineAllHBox.setDisable(true);

        }

    }

    private void play(int row, int col) {
        if (isEmpty(row, col)) {
            board[row][col] = 'X';

            if (gameOver(board)) {
                combineAllHBox.setDisable(true);
                return;
            }

            computerPlays();
            if (gameOver(board)) {
                combineAllHBox.setDisable(true);
                return;
            }

            updateUIBoard();
        }
    }


    private boolean isEmpty(int row, int col) {
        return board[row][col] == ' ';
    }

    private void computerPlays() {
        int[] optimalAction = optimalMove(board);
        board[optimalAction[0]][optimalAction[1]] = 'O';
    }

    private static int[] optimalMove(char[][] board) {
        int[] optimalAction = {-1, -1};
        int optimalScore = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    if (winningMove(board, 'O')) {
                        board[i][j] = ' '; // backtracks
                        return new int[]{i, j};
                    }
                    board[i][j] = ' ';
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = 'O';
                    int miniMaxScore = miniMaxAlgorithm(board, 0, false);
                    board[i][j] = ' ';

                    if (miniMaxScore > optimalScore) {
                        optimalScore = miniMaxScore;
                        optimalAction[0] = i;
                        optimalAction[1] = j;
                    }
                }
            }
        }

        return optimalAction;
    }

    private void updateUIBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                char type = board[i][j];
                String buttonText = type == ' ' ? "" : String.valueOf(type);
                updateUIButton(i, j, buttonText);
            }
        }
    }

    private static int miniMaxAlgorithm(char[][] board, int depth, boolean isMaximizing) {
        if (winningMove(board, 'X')) {
            return -1;
        }
        if (winningMove(board, 'O')) {
            return 1;
        }
        if (isBoardFull(board)) {
            return 0;
        }

        if (isMaximizing) {
            int optimalScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'O';
                        int miniMaxScore = miniMaxAlgorithm(board, depth + 1, false);
                        board[i][j] = ' ';
                        optimalScore = Math.max(miniMaxScore, optimalScore);
                    }
                }
            }
            return optimalScore;
        } else {
            int optimalScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == ' ') {
                        board[i][j] = 'X';
                        int miniMaxScore = miniMaxAlgorithm(board, depth + 1, true);
                        board[i][j] = ' ';
                        optimalScore = Math.min(miniMaxScore, optimalScore);
                    }
                }
            }
            return optimalScore;
        }
    }




    private void updateUIButton(int row, int col, String text) {
        buttons[row][col].setText(text);
    }


    private static boolean winningMove(char[][] board, char player) {
        // row or column
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }

        // diagonal
        return (board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player);
    }

    private boolean gameOver(char[][] board) {
        if (winningMove(board, 'O')) {
            updateUIBoard();
            playerLabel.setText("Computer wins!");
            computerWinsCounter++;
            computerWinsCounterLabel.setText("Computer: " + computerWinsCounter);
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> resetGame());
            pause.play();
            return true;
        } else if (winningMove(board, 'X')) {
            updateUIBoard();
            playerLabel.setText("You win!");
            playerWinsCounter++;
            playerWinsCounterLabel.setText("Player: " + playerWinsCounter);
            PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Set the desired duration in seconds
            pause.setOnFinished(event -> resetGame());
            pause.play();
            return true;
        } else if (isBoardFull(board)) {
            updateUIBoard();
            playerLabel.setText("It's a tie!");
            tieCounter++;
            tieCounterLabel.setText("Tie: " + tieCounter);
            PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Set the desired duration in seconds
            pause.setOnFinished(event -> resetGame());
            pause.play();
            return true;
        }
        return false;
    }

    private static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch();
    }
}