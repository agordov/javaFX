package javaFx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class InputChecker extends Application{

    private String inputLine;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Text&Numbers");

        GridPane grid = WindowUtils.createGridPane();

        Text sceneTitle = WindowUtils.createText("Welcome");

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(sceneTitle);
        grid.add(stackPane, 0, 0, 3, 1);

        Label userText = WindowUtils.createLabel("Enter your text:");
        grid.add(userText, 0, 1);

        Label text = WindowUtils.createLabel("Your text:");
        grid.add(text, 0, 2);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Scene scene = WindowUtils.createScene(grid);
        primaryStage.setScene(scene);

        Button btn = new Button("Check digits");
        HBox hbBtn = WindowUtils.createHBox(btn, 10, Pos.BOTTOM_RIGHT);
        grid.add(hbBtn, 2, 1);

        Text output = WindowUtils.createText("Here will be your text", Color.DARKGREEN);
        grid.add(output, 1, 2, 2, 1);

        Label errors = WindowUtils.createLabel("Errors:");
        grid.add(errors, 0, 3);

        Text errorsOutput = WindowUtils.createText("", Color.RED);
        grid.add(errorsOutput, 1, 3, 2, 1);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (checkInput(userTextField)) {
                        setOutput(output);
                    }
                } catch (RuntimeException e) {
                    printException(e, errorsOutput);
                }
            }
        });
        primaryStage.show();
    }

    private static final String DIGITS = "[0-9]";

    private void setOutput(Text output) {
        output.setText(inputLine);
    }

    private boolean checkInput(TextField textField) {
        inputLine = textField.getText();
        if (Objects.isNull(inputLine)) {
            throw new NullPointerException("Null string");
        }
        if (inputLine.isEmpty()) {
            throw new RuntimeException("Empty string");
        }
        StringChecker strCheck = new StringChecker(inputLine);
        return !strCheck.containSymbols(DIGITS);
    }

    private void printException(RuntimeException e, Text output) {
        output.setText(e.getMessage());
    }

}
