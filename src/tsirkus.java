import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.*;

import java.util.Collection;


public class tsirkus extends Application implements EventHandler<ActionEvent>{

    public static void main(String[] args) {
        launch(args);
    }
    Stage TsirkuseMang;
    public static StackPane Game;
    BorderPane GameBoard;
    GridPane ManguRuudustik;
    StackPane ManguRuut;
    double ManguLauaLaius = 600.0;
    double ManguLauaKorgus = 350.0;

    @Override
    public void start(Stage PrimaryStage) {
        TsirkuseMang = PrimaryStage;
        //Loome uue mänguvälja
        LooManguVali();
        //Lisame pealkirja Mänguväljale päisesse
        LooManguPais();
        //Lisame mänguvälja vasaku ääre kuhu tuleb nupud ja täringud
        LooVasakVali();
        //Lisame mänguvälja keskele mänguruudud. Parameetrina mänguruutude arv
        LooManguLaud(100);
    }

    private void LooManguPais() {
        //Loome mänguväljale Pealkijra
        Label GameTitle = new Label();
        GameTitle.setText("Tsirkuse Mäng");
        GameTitle.setTextFill(Color.YELLOW);
        GameTitle.setFont(Font.font("Verdana", 30));
        GameTitle.setTextAlignment(TextAlignment.JUSTIFY);
        GameTitle.setMinHeight(100);
        GameTitle.setPrefWidth(Double.MAX_VALUE);
        GameTitle.setAlignment(Pos.CENTER);
        GameTitle.setStyle("-fx-background-color: green");


        GameBoard.setTop(GameTitle);
    }

    private void LooManguVali() {
        //Loome Mängu
        Game = new StackPane();
        Game.setStyle("-fx-background-color: brown;");
        //Loome Mängulaua
        GameBoard = new BorderPane();
        GameBoard.setMinSize(800, 600);
        Game.getChildren().add(GameBoard);

        Scene scene = new Scene(Game);
        //TsirkuseMang.setMaximized(true);
        TsirkuseMang.setTitle("Tsirkus");
        TsirkuseMang.setScene(scene);
        TsirkuseMang.show();
        TsirkuseMang.setOnCloseRequest(event -> {
            System.exit(0);
        }); //akna sulgedes läheb programm kinni
    }

    public void LooVasakVali (){
    /* Loome vasaku välja */
        VBox VasakVali = new VBox();
        VasakVali.setSpacing(10);

        /*Loome Veeretamise nupu*/
        Button VeeretamisNupp = new Button();
        VeeretamisNupp.setText("Veereta Täringut");
        VeeretamisNupp.setOnAction(this);
        VasakVali.getChildren().add(VeeretamisNupp);

        VeeretamisNupp.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle (ActionEvent e){
                // Siia tegevus, kui nupp on vajutatud.
                taring t = new taring();
            }
        });

        //Täringuala
        Rectangle taringuAla = new Rectangle(50, 50, 50, 50);
        taringuAla.setFill(Color.WHITESMOKE);

        VasakVali.getChildren().add(taringuAla);

        //Täringu sisu

        //Lisame täringule sisu
        //Saadame Vasaku välja tagasi
        GameBoard.setLeft(VasakVali);
    }

    private void LooManguLaud (int RuuteLaual) {
        // Teeb sobiva mängulaua
        StackPane ManguLaud = new StackPane();

        //Loome mänguruudustiku
        double ruuduLaius = ManguLauaLaius/11;
        double ruuduKorgus = ManguLauaKorgus/10;
        ManguRuudustik = new GridPane();
        ManguRuudustik.setGridLinesVisible(true);
        int rida = 10;
        int veerg = 1;
        boolean suundTagasi = false;
        for (int i=1; i<=RuuteLaual; i++){
            ManguRuut = new StackPane();
            ManguRuut.setId(Integer.toString(i));

            Rectangle Ruut = new Rectangle(ruuduLaius,ruuduKorgus);
            Label RuuduNumber = new Label(Integer.toString(i));
            RuuduNumber.setFont(Font.font("Verdana", 12));
            RuuduNumber.setTextAlignment(TextAlignment.RIGHT);
            //RuuduNumber.setStyle("-fx-border-color:red; -fx-background-color: Green;");
            //RuuduNumber.setPadding(new Insets(10,0,0,10));

            if (i%2==0){
                Ruut.setFill(Color.BEIGE);
            } else {
                Ruut.setFill(Color.LIGHTGREEN);
            }
            ManguRuut.getChildren().addAll(Ruut, RuuduNumber);
            ManguRuudustik.add(ManguRuut, veerg, rida);

            if (suundTagasi){
                veerg--;
            } else {
                veerg++;
            }
            if (i%10==0){
                rida--;
                if (!suundTagasi) {
                    suundTagasi = true;
                    veerg--;
                } else {
                    suundTagasi = false;
                    veerg++;
                }
            }
        }

        //Loome mängulauale ümarate äärtega tausta
        Rectangle ManguLauaTaust = new Rectangle(ManguLauaLaius+2.0, ManguLauaKorgus+2.0);
        ManguLauaTaust.setArcHeight(20.0);
        ManguLauaTaust.setArcWidth(20.0);
        ManguLauaTaust.setFill(Color.AQUAMARINE);
        ManguLauaTaust.setStroke(Color.BLACK);

        //Loome mängulaua raami, et tausta ja ruudustikku koos hoida
        AnchorPane ManguLauaRaam = new AnchorPane();
        ManguLauaRaam.getChildren().addAll(ManguLauaTaust,ManguRuudustik);
        ManguLauaRaam.setLeftAnchor(ManguRuudustik, ruuduLaius / 2);
        ManguLauaRaam.setTopAnchor(ManguRuudustik, 1.0);

        //Paneme mängulaua kokku
        ManguLaud.getChildren().add(ManguLauaRaam);
        ManguLaud.setPadding(new Insets(10));
        GameBoard.setRight(ManguLaud);

    }

    @Override
    public void handle(ActionEvent event) {

    }


}
