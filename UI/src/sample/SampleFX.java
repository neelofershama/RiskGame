package UI.src.sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SampleFX extends Application {

    Stage map;
    int column_constraint_Space = 50;
    int row_constraint_space = 50;
    int metaphor_radius = (int) (column_constraint_Space * .25);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        map = primaryStage;
        map.setTitle("Risk Game");
        map.setWidth(500);
        map.setHeight(500);
        map.initStyle(StageStyle.DECORATED);

        /*
        // Creating Nodes to be added to grid pane
        Label name_1 = new Label("A");
        Label name_2 = new Label("B");

        // Creating grid pane
        GridPane grid = new GridPane();
        grid.add(name_1, 0, 0);
        grid.setHalignment(name_1, HPos.RIGHT);
        grid.add(name_2, 1, 0);
        grid.setHalignment(name_2, HPos.RIGHT);


        // Setting column constraints
        ColumnConstraints col_1 = new ColumnConstraints();
        col_1.setPrefWidth(100);

        ColumnConstraints col_2 = new ColumnConstraints();
        col_2.setPrefWidth(30);

        grid.getColumnConstraints().addAll(col_1, col_2);
         */


        // Drawing node of shape rectangle
        Rectangle r1 = new Rectangle(0,0,column_constraint_Space, row_constraint_space);
        r1.setFill(null);
        r1.setStrokeWidth(1);
        r1.setStroke(Color.BLACK);

        Rectangle r2 = new Rectangle(0,0,column_constraint_Space, row_constraint_space);
        r2.setFill(null);
        r2.setStrokeWidth(1);
        r2.setStroke(Color.BLACK);

        // Drawing node of shape circle
        Circle c1 = new Circle(0, 0, metaphor_radius);
        c1.setFill(Color.GREEN);
        Text t1 = new Text("2");
        t1.setFill(Color.BLACK);
        // text.setBoundsType(TextBoundsType.VISUAL);
        StackPane s_11 = new StackPane();
        s_11.getChildren().addAll(c1, t1);
        StackPane s1 = new StackPane();

        s1.getChildren().addAll(r1, s_11);



        Circle c2 = new Circle(0, 0, metaphor_radius);
        c2.setFill(Color.GREEN);
        Text t2 = new Text("20");
        t2.setFill(Color.BLACK);
        // text.setBoundsType(TextBoundsType.VISUAL);
        StackPane s_22 = new StackPane();
        s_22.getChildren().addAll(c2, t2);
        StackPane s2 = new StackPane();
        s2.getChildren().addAll(r2, s_22);


        // Creating grid pane
        GridPane grid = new GridPane();
        grid.add(s1, 0, 0);
        grid.add(s2, 1, 1);

        // Setting column constraints
        ColumnConstraints col_1 = new ColumnConstraints();
        col_1.setPrefWidth(column_constraint_Space);

        // Setting row constraints
        RowConstraints row_1 = new RowConstraints();
        row_1.setMaxHeight(row_constraint_space);

        grid.getColumnConstraints().add(col_1);
        grid.getRowConstraints().add(row_1);
        // GridPane.setConstraints(r1, 0, 0);
        grid.setPrefSize(800,800);
        grid.setGridLinesVisible(true);


        Scene scene = new Scene(grid);
        s_11.setOnMouseEntered(e -> s_11.setCursor(Cursor.HAND));

        // s_11.setOnMouseClicked(e -> System.out.println("Mouse Clicked Testing")); // working good
        s_11.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                /* drag was detected, start a drag-and-drop gesture*/
                /* allow any transfer mode */
                Dragboard db = s_11.startDragAndDrop(TransferMode.ANY);

                /* Put a string on a dragboard */
                ClipboardContent content = new ClipboardContent();
                Text text = (Text) s_11.getChildren().get(1);
                Circle circle = (Circle) s_11.getChildren().get(0);
                String color = circle.getFill().toString();
                String troops = text.getText();
                // text.setText("0");
                // s_11.getChildren().add(1, text);
                // content.putString(troops);
                content.putString(troops+","+color);
                db.setContent(content);

                event.consume();
            }
        }); // working good

        s2.setOnDragExited(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

                /* data is dragged over the target */
                /* accept it only if it is not dragged from the same node
                 * and if it has a string data */
                if (event.getGestureSource() != s_22 &&
                        event.getDragboard().hasString()) {
                    String info = (String)event.getDragboard().getString();
                    String[] data = info.split(",");
                    Circle circle = (Circle) s_22.getChildren().get(0);
                    String color = circle.getFill().toString();
                    if(!data[1].equalsIgnoreCase(color)) {
                        /* allow for both copying and moving, whatever user chooses */
                        event.acceptTransferModes(TransferMode.MOVE);
                        String troops = data[0];
                        Text text = (Text) s_22.getChildren().get(1);
                        // System.out.println(text.getText());
                        int number_of_troops = Integer.parseInt(troops) + Integer.parseInt(text.getText());
                        text.setText(Integer.toString(number_of_troops));
                        // System.out.println(number_of_troops);
                        // text.setText(Integer.toString(number_of_troops));
                        // s2.getChildren().add(1, text);
                    }
                }
            }
        });
        s_11.setOnDragDropped(e -> System.out.println("Something is dragged"));
        map.setScene(scene);
        map.show();
    }



}

