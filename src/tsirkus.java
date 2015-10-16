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
    @Override
    public void start(Stage laud) {

        BorderPane Raamistik = new BorderPane();
        Raamistik.setMinSize(600,300);


        Label Pealkiri = new Label();
        Pealkiri.setText("Tsirkuse Mäng");
        Pealkiri.setFont(Font.font("Verdana", 30));
        Pealkiri.setPrefHeight(50);
        Raamistik.setTop(Pealkiri);
       // Raamistik.setCenter(LooManguLaud(50));
        Raamistik.setLeft(LooVasakVali());

        Scene scene = new Scene(Raamistik, 800, 500);
        laud.setTitle("Tsirkus");
        laud.setScene(scene);
        laud.show();

    }
    public VBox LooVasakVali (){
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

        taring t1 = new taring();


        VasakVali.getChildren().add(t1.);

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

    static int sammud () {
        boolean viskauuesti = true;
        int tulemus = 0;
        int vise = 0;
        while(viskauuesti) {
            vise = taring.VeeretaTaringut();
            if (vise == 6) {
                System.out.println("Viskasin täringul " + vise +  " ja saan uue viske :)");
                tulemus = tulemus + vise;
                viskauuesti = true;
            } else {
                System.out.println("Viskasin täringul " + vise);
                tulemus = tulemus + vise;
                viskauuesti = false;
            }
        }
        return tulemus;
    }


}
