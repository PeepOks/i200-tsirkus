import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;

import java.util.Stack;

public class tsirkus extends Application implements EventHandler<ActionEvent>{

    public static void main(String[] args) {
        launch(args);
    }
    public static BorderPane GameBoard;
    @Override
    public void start(Stage Game) {
        //Loome uue mänguvälja
        GameBoard = createGameBoard();

        //Lisame pealkirja Mänguväljakule päisesse
        GameBoard.setTop(GameTitle());

        //Lisame mänguvälja keskele mänguruudud. Parameetrina mänguruutude arv
        GameBoard.setCenter(LooManguLaud(50));

        //Lisame mänguvälja vasaku ääre kuhu tuleb nupud ja täringud
        GameBoard.setLeft(LooVasakVali());


        Scene scene = new Scene(GameBoard, 800, 500);
        Game.setTitle("Tsirkus");
        Game.setScene(scene);
        Game.show();

    }

    private Node GameTitle() {
        //Loome mänguväljale Pealkijra
        Label GameTitle = new Label();
        GameTitle.setText("Tsirkuse Mäng");
        GameTitle.setFont(Font.font("Verdana", 30));
        GameTitle.setPrefHeight(50);
        return GameTitle;
    }

    private BorderPane createGameBoard() {
        GameBoard = new BorderPane();
        GameBoard.setMinSize(600, 300);

        return GameBoard;
    }

    public Node LooVasakVali (){
    /* Loome vasaku välja */
        VBox VasakVali = new VBox();
        VasakVali.setSpacing(10);

        /*Loome Veeretamise nupu*/
        Button VeeretamisNupp = new Button();
        VeeretamisNupp.setText("Veereta Täringut");
        VeeretamisNupp.setOnAction(this);
        VasakVali.getChildren().add(VeeretamisNupp);
        /*
        veeretaNupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle (ActionEvent e){
                // Siia tegevus, kui nupp on vajutatud.
                taringunumber.setText(Integer.toString(sammud()));
            }
        });
        */

        /*
        //Täring
        Rectangle taring = new Rectangle(30, 30, 50, 50);
        taring.setFill(Color.RED);

        //Täringu sisu
        taringunumber.setText(Integer.toString(sammud()));
        taringunumber.setFill(Color.WHITE);
        taringunumber.setFont(Font.font("Verdana", 20));
        taringunumber.setTextAlignment(TextAlignment.RIGHT);
        */
        //Lisame täringule sisu

        //Saadame Vasaku välja tagasi
        return VasakVali;
    }
    private GridPane LooManguLaud (int RuuteLaual) {
        // Teeb sobiva mängulaua

        GridPane mangulaud = new GridPane();
        //mangulaud.setGridLinesVisible(true);
        mangulaud.setHgap(2);
        mangulaud.setVgap(2);
        mangulaud.setPadding(new Insets(10, 0, 0, 100));

        int rida = 1;
        int veerg = 1;
        boolean suundTagasi = false;
        for (int i=1; i<=RuuteLaual; i++){
            /*
            Text number = new Text(Integer.toString(i));
            */
            Label number = new Label(Integer.toString(i));
            number.setFont(Font.font("Verdana", 12));
            number.setTextAlignment(TextAlignment.CENTER);
            number.setStyle("-fx-border-color:red; -fx-background-color: Green;");
            number.setPadding(new Insets(0,0,0,10));
            number.setMinHeight(40);
            number.setMinWidth(40);

            mangulaud.add(number, veerg, rida);

            if (suundTagasi){
                veerg--;
            } else {
                veerg++;
            }
            if (i%10==0){
                rida++;
                if (!suundTagasi) {
                    suundTagasi = true;
                    veerg--;
                } else {
                    suundTagasi = false;
                    veerg++;
                }
            }
        }

        return mangulaud;
    }

    @Override
    public void handle(ActionEvent event) {

    }


}
