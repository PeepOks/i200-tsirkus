import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.lang.annotation.Inherited;

public class Lauamang{

    Stage TsirkuseMang;
    StackPane Game;
    BorderPane GameBoard;
    GridPane ManguRuudustik;
    StackPane ManguRuut;
    static Rectangle TaringuRuut;
    static VBox NuppudeAla;
    double ManguLauaLaius = 600.0;
    double ManguLauaKorgus = 350.0;


    public Lauamang(){
        TsirkuseMang = new Stage();
        //Loome uue mänguvälja
        LooManguVali();

        //Lisame pealkirja Mänguväljale päisesse
        LooManguPais();

        //Lisame mänguvälja vasaku ääre kuhu tuleb nupud ja täringud
        LooVasakVali();

        //Lisame mänguvälja keskele mänguruudud. Parameetrina mänguruutude arv
        LooManguLaud(100);

        //Lisame kohe mängulauale täringu
        new taring();

        //Lisame mängijad
        //KesMangivad();
        Nupp.lisaNuppMangu("Kollane");


    }

    private void KesMangivad() {
        ObservableList mangijateList = FXCollections.observableArrayList();
        ObservableList nupuVarviList = FXCollections.observableArrayList();

        FlowPane taust = new FlowPane();
        taust.setMaxHeight(300);
        taust.setMaxWidth(330);
        taust.setPadding(new Insets(10));
        taust.setAlignment(Pos.CENTER);
        taust.setStyle("-fx-background-color: white; -fx-border-color:black; -fx-border-radius:20;");

        VBox mangijaSisestus = new VBox();
        mangijaSisestus.setSpacing(10);
        HBox MangijaInfo = new HBox();

        VBox MangijaNimeKast = new VBox();
        Label MangijaNimi = new Label("Sisesta mängija nimi");
        TextField mangijaNimeInput = new TextField();
        MangijaNimeKast.getChildren().addAll(MangijaNimi, mangijaNimeInput);

        VBox MangijaNupuVarviKast = new VBox();
        Label MangijaNupuVarv = new Label("Vali omale nupu värv");
        nupuVarviList.addAll("Punane", "Sinine", "Kollane", "Roheline");
        ComboBox nupuVarvid = new ComboBox(nupuVarviList);
        nupuVarvid.setEditable(false);
        nupuVarvid.setPromptText("Saada olevad värvid");
        MangijaNupuVarviKast.getChildren().addAll(MangijaNupuVarv,nupuVarvid);

        MangijaInfo.getChildren().addAll(MangijaNimeKast,MangijaNupuVarviKast);


        // Toome välja kes mängivad
        VBox mangijateNimekiri = new VBox();
        Label kesMangivad = new Label("Mängijad:");
        ListView nimeKiri = new ListView();
        nimeKiri.setMaxHeight(taust.getMaxHeight()/2);
        mangijateNimekiri.getChildren().addAll(kesMangivad,nimeKiri);

        Button lisaMangija = new Button("Lisa mängija");
        Button hakkameMangima = new Button("Hakkame mängima");

        //Mida teeb hakkameMangima Nupp
        hakkameMangima.setOnAction(event -> {
            if (mangijateList.size()>0) {
                Game.getChildren().remove(taust);
            }
        });
        // Mida teeb lisaMangija nupp
        lisaMangija.setOnAction(event -> {
            String mangijaNimi = mangijaNimeInput.getText();
            String nupuVarv = String.valueOf(nupuVarvid.getValue());

            if (mangijaNimi.length()>0 && !nupuVarv.equals("null")) {
                //loome uue mängija
                System.out.println("Loome mängija nimega " + mangijaNimi);

                Mangija mangija = new Mangija(mangijaNimi, nupuVarv);

                mangijateList.add(mangija.nimi + " - " + mangija.nupuVarv);
                nimeKiri.setItems(mangijateList);
                nupuVarviList.remove(nupuVarv);
                mangijaNimeInput.setText("");
            }

        });

        //Lisame kõik elemendid kokku
        mangijaSisestus.getChildren().addAll(MangijaInfo,lisaMangija,mangijateNimekiri,hakkameMangima);
        taust.getChildren().add(mangijaSisestus);
        Game.getChildren().addAll(taust);
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
        VasakVali.setPadding(new Insets(10));
        VasakVali.setMaxWidth(100);
        VasakVali.setMaxHeight(100);

        /*Loome Veeretamise nupu*/
        Button VeeretamisNupp = new Button();
        VeeretamisNupp.setText("Veereta Täringut");
        //Mida veeretamisNupp teeb
        VeeretamisNupp.setOnAction(event -> {
            Mangija.veeretaTaringut();
        });

        //Loome koha kuhu tuleb täring
        TaringuRuut = new Rectangle();
        TaringuRuut.setHeight(60);
        TaringuRuut.setWidth(60);

        //Loome koha kuhu tulevad mängijate nupud
        NuppudeAla = new VBox();
        NuppudeAla.setSpacing(10);

        //Paneme Vasaku välja kokku ja lisame mängulauale
        VasakVali.getChildren().addAll(VeeretamisNupp,TaringuRuut, NuppudeAla);
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
}
