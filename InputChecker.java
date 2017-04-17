package javaFx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
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

import static javaFx.Constants.*;

public class InputChecker extends Application{
    private static GridPane gPane;
    private static String inputLine;
    private static Text sceneTitle;
    private static StackPane stackPane;
    private static Label userText;
    private static Label text;
    private static TextField userTextField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Text&Numbers");

        GridPane grid = createGridPane();

        sceneTitle = WindowUtils.createText("Welcome");

        stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(sceneTitle);
        grid.add(stackPane, 0, 0, 3, 1);

        userText = WindowUtils.createLabel("Enter your text:");
        grid.add(userText, 0, 1);

        text = WindowUtils.createLabel("Your text:");
        grid.add(text, 0, 2);

        userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Scene scene = createScene(grid);
        primaryStage.setScene(scene);

        Button btn = new Button("Check digits");
        HBox hbBtn = WindowUtils.createHBox(btn, 10, Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
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
                    } else {
                        throw new RuntimeException("Contains symbols: " + new StringChecker(inputLine).getSymbols(DIGITS));
                    }
                } catch (RuntimeException e) {
                    addRuntimeExceptionToOutput(e, errorsOutput);
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
        if (inputLine.isEmpty()) {
            throw new RuntimeException("Empty string");
        }
        return !new  StringChecker(inputLine).containSymbols(DIGITS);
    }

    private void addRuntimeExceptionToOutput(RuntimeException e, Text output) {
        output.setText(e.getMessage());
    }

    public static GridPane createGridPane(Pos pos, int hGap, int vGap, int topPad, int rightPad, int bottomPad, int leftPad) {
        gPane = new GridPane();
        gPane.setAlignment(pos);
        gPane.setHgap(hGap);
        gPane.setVgap(vGap);
        gPane.setPadding(new Insets(topPad, rightPad, bottomPad, leftPad));
        return gPane;
    }

    public static GridPane createGridPane() {
        return createGridPane(DEFAULT_POS, DEFAULT_HGAP, DEFAULT_VGAP,
                DEFAULT_TOPPAD, DEFAULT_RIGHTPAD, DEFAULT_BOTPAD, DEFAULT_LEFTPAD);
    }

    public static StackPane createStackPane(Pos pos) {
        stackPane = new StackPane();
        return stackPane;
    }

    public static Scene createScene(Parent root, int width, int height) {
        return new Scene(root, width, height);
    }

    public static Scene createScene(Parent root) {
        return createScene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
