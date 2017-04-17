package javaFx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class WindowUtils {

    private static final Pos DEFAULT_POS = Pos.CENTER;
    private static final int DEFAULT_HGAP = 10;
    private static final int DEFAULT_VGAP = 10;
    private static final int DEFAULT_TOPPAD = 25;
    private static final int DEFAULT_RIGHTPAD = 25;
    private static final int DEFAULT_BOTPAD = 25;
    private static final int DEFAULT_LEFTPAD = 25;
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;
    private static final String DEFAULT_FONT = "Tahoma";
    private static final FontWeight DEFAULT_WEIGHT = FontWeight.NORMAL;
    private static final double DEFAULT_SIZE = 14;
    private static final double DEFAULT_SPACING = 10;
    private static final Color DEFAULT_COLOR = Color.BLACK;

    public static GridPane createGridPane(Pos pos, int hGap, int vGap, int topPad, int rightPad, int bottomPad, int leftPad) {
        GridPane grid = new GridPane();
        grid.setAlignment(pos);
        grid.setHgap(hGap);
        grid.setVgap(vGap);
        grid.setPadding(new Insets(topPad, rightPad, bottomPad, leftPad));
        return grid;
    }

    public static GridPane createGridPane() {
        return createGridPane(DEFAULT_POS, DEFAULT_HGAP, DEFAULT_VGAP, DEFAULT_TOPPAD, DEFAULT_RIGHTPAD, DEFAULT_BOTPAD, DEFAULT_LEFTPAD);
    }

    public static Scene createScene(Parent root, int width, int height) {
        return new Scene(root, width, height);
    }

    public static Scene createScene(Parent root) {
        return createScene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public static Text createText(String str, String font, FontWeight weight, double size, Color color) {
        Text text = new Text();
        text.setText(str);
        text.setFont(Font.font(font, weight, size));
        text.setFill(color);
        return text;
    }

    public static Text createText(String str, Color color) {
        return createText(str, DEFAULT_FONT, DEFAULT_WEIGHT, DEFAULT_SIZE, color);
    }

    public static Text createText(String str) {
        return createText(str, DEFAULT_FONT, DEFAULT_WEIGHT, DEFAULT_SIZE, DEFAULT_COLOR);
    }

    public static Label createLabel(String str, String font, FontWeight weight, double size, Color color) {
        Label label = new Label(str);
        label.setFont(Font.font(font, weight, size));
        label.setTextFill(color);
        return label;
    }

    public static Label createLabel(String str, Color color) {
        return createLabel(str, DEFAULT_FONT, DEFAULT_WEIGHT,DEFAULT_SIZE, color);
    }

    public static Label createLabel(String str){
        return createLabel(str, DEFAULT_FONT, DEFAULT_WEIGHT, DEFAULT_SIZE, DEFAULT_COLOR);
    }

    public static HBox createHBox(Button btn, double spacing, Pos pos) {
        HBox hBox = new HBox(spacing);
        hBox.setAlignment(pos);
        hBox.getChildren().add(btn);
        return hBox;
    }

    public static HBox createHBox(Button btn) {
        return createHBox(btn, DEFAULT_SPACING, DEFAULT_POS);
    }

}
