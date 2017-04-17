package javaFx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Welcome");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(sceneTitle);
        grid.add(stackPane, 0, 0, 3, 1);
        Label userText = new Label("Enter your text:");
        grid.add(userText, 0, 1);

        Label text = new Label("Your text:");
        grid.add(text, 0, 2);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Scene scene = new Scene(grid, 640, 480);
        primaryStage.setScene(scene);

        Button btn = new Button("Press me");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);

        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 2, 1);

        Text output = new Text();
        output.setText("Here will be your text");
        grid.add(output, 1, 2, 2, 1);
        output.setFill(Color.DARKGREEN);

        Label errors = new Label("Errors:");
        grid.add(errors, 0, 3);

        Text errorsOutput = new Text();
        errorsOutput.setText("");
        errorsOutput.setFill(Color.RED);
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
        StringChecker strCheck = new StringChecker(DIGITS);
        return !strCheck.containSymbols(DIGITS);
    }

    private void printException(RuntimeException e, Text output) {
        output.setText(e.getMessage());
    }

}
