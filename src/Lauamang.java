import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
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
import java.util.ArrayList;

public class Lauamang extends ActionEvent{

    Stage TsirkuseMang;
    StackPane Game;
    BorderPane GameBoard;
    GridPane ManguRuudustik;
    Rectangle Ruut;
    static Integer ManguRuutudeArv=20;
    static StackPane[] ManguRuudud = new StackPane[ManguRuutudeArv];

    static ArrayList<Mangija> Mangijad = new ArrayList<Mangija>();
    private Integer MangijateArv;
    private Integer MangijaNumber;

    static Rectangle TaringuRuut;
    static VBox NuppudeAla;
    double ManguLauaLaius = 600.0;
    double ManguLauaKorgus = 350.0;
    static boolean mangLabi = false;

    private Button VeeretamisNupp;
    private Button LiikumisNupp;
    private Button JargmineMangija;

    private Label Info;

    public Lauamang(){
        TsirkuseMang = new Stage();
        //Loome uue mänguvälja
        LooManguValjak();

        //Loome MänguVäljaku päise, kuhu tuleb pealkiri
        LooManguPais();

        //Lisame mänguväljaku vasaku ääre, kuhu tuleb nupud ja täringud
        LooVasakVali();

        //Loome mänguväljaku jalusesse ala kuhu tulevad mängu juht nupud.
        LooManguJalus();

        //Lisame mänguväljaku keskele mänguruudud. Parameetrina mänguruutude arv
        LooManguLaud(ManguRuutudeArv);

        //Lisame kohe mängulauale täringu
        new taring();

        //Lisame mängijad
        //KesMangivad();

        Mangijad.add(new Mangija("Peep", "Punane"));
        Mangijad.add(new Mangija("Kaspar", "Kollane"));
        //Mangijad.add(new Mangija("Mikk", "Roheline"));
        System.out.println();

        //Hakkame mängima
        Mangime();

    }



    private void Mangime() {
        System.out.println("Alustame Mänguga");

        //Määrame milliseid nuppe kasutada saame
        VeeretamisNupp.setDisable(false);
        LiikumisNupp.setDisable(true);
        JargmineMangija.setDisable(true);

        //Mitu mängijat meil on
        MangijateArv = Mangijad.size();

        //Alustab 1. mängija
        MangijaNumber = 0;

        //Kõik nupud lähevad mängulaua algusesse
        if (Mangijad.get(MangijaNumber).sammudeArv==0){
            Mangijad.get(MangijaNumber).liigutaNuppAlgusesse();
        }

        Info.setText(Mangijad.get(MangijaNumber).nimi + " palun veereta täringut");
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
            if (Mangijad.size()>1) {
                Game.getChildren().remove(taust);
                Mangime();
            } else {
                System.out.println("Mangijaid peab olema rohkem kui 2");
            }
        });
        // Mida teeb lisaMangija nupp
        lisaMangija.setOnAction(event -> {
            String mangijaNimi = mangijaNimeInput.getText();
            String nupuVarv = String.valueOf(nupuVarvid.getValue());

            if (mangijaNimi.length()>0 && !nupuVarv.equals("null")) {
                //loome uue mängija
                System.out.println("Loome mängija nimega " + mangijaNimi);
                Mangijad.add(new Mangija(mangijaNimi, nupuVarv));
                //Lisame mangija ka nimekirja.
                mangijateList.add(mangijaNimi + " - " + nupuVarv);
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
        Info = new Label("Tsirkuse Mäng");
        Info.setTextFill(Color.YELLOW);
        Info.setFont(Font.font("Verdana", 30));
        Info.setTextAlignment(TextAlignment.JUSTIFY);
        Info.setMinHeight(100);
        Info.setPrefWidth(Double.MAX_VALUE);
        Info.setAlignment(Pos.CENTER);
        Info.setStyle("-fx-background-color: green");

        GameBoard.setTop(Info);
    }

    private void LooManguJalus() {
        HBox nupud = new HBox();
        nupud.setPadding(new Insets(10));
        nupud.setSpacing(10);

        //Loome mangu alustamise nupu
        Button AlustamisNupp = new Button("Alusta Manguga");
        AlustamisNupp.setOnAction(event1 -> {
            Mangime();
            AlustamisNupp.setVisible(false);
        });


        /*Loome Veeretamise nupu*/
        VeeretamisNupp = new Button();
        VeeretamisNupp.setText("Veereta Täringut");
        //Mida veeretamisNupp teeb
        VeeretamisNupp.setOnAction(event -> {
            Mangijad.get(MangijaNumber).veeretaTaringut();
            //Mangijad.get(MangijaNumber).onTaringutVeeretanud = true;
            Info.setText(Mangijad.get(MangijaNumber).nimi + " saab käia edasi " + Mangijad.get(MangijaNumber).sammudeArv + " sammu.");
            VeeretamisNupp.setDisable(true);
            LiikumisNupp.setDisable(false);
        });

        //Loome edasiliikumise nupu
        LiikumisNupp = new Button("Liiguta Nuppu");
        LiikumisNupp.setOnAction(event -> {

            System.out.println("Mangija " + Mangijad.get(MangijaNumber).nimi + " saab liikuda edasi " + Mangijad.get(MangijaNumber).sammudeArv + " sammu");
            Mangijad.get(MangijaNumber).liigutaNuppuEdasi(Mangijad.get(MangijaNumber).sammudeArv);
            Mangijad.get(MangijaNumber).onNuppuLiigutanud = true;
            JargmineMangija.setDisable(false);
            LiikumisNupp.setDisable(true);
            /*
            if (Mangijad.get(MangijaNumber).onTaringutVeeretanud==true && Mangijad.get(MangijaNumber).onNuppuLiigutanud==false){
            } else if (Mangijad.get(MangijaNumber).onTaringutVeeretanud==true && Mangijad.get(MangijaNumber).onNuppuLiigutanud==true){
                System.out.println("Sinu kord on läbi. Järgmine mängija");
            } else {
                System.out.println(Mangijad.get(MangijaNumber).nimi + " pole veel taringut veeretanud!!!");
            }*/

        });

        //Loome nupu mängija vahetamiseks
        JargmineMangija = new Button("Järgmine mängija");
        JargmineMangija.setOnAction(event -> {
            if (MangijaNumber == MangijateArv-1){
                MangijaNumber = 0;
            } else {
                MangijaNumber++;
            }
            if (Mangijad.get(MangijaNumber).sammudeArv==0) {
                Mangijad.get(MangijaNumber).liigutaNuppAlgusesse();
            }
            Info.setText(Mangijad.get(MangijaNumber).nimi + ", sinu kord on veeretada täringut.");
            JargmineMangija.setDisable(true);
            VeeretamisNupp.setDisable(false);
        });



        nupud.getChildren().addAll(VeeretamisNupp, LiikumisNupp, JargmineMangija);
        GameBoard.setBottom(nupud);
    }

    private void LooManguValjak() {
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

        //Loome koha kuhu tuleb täring
        TaringuRuut = new Rectangle();
        TaringuRuut.setHeight(60);
        TaringuRuut.setWidth(60);

        //Loome koha kuhu tulevad mängijate nupud
        NuppudeAla = new VBox();
        NuppudeAla.setSpacing(10);

        //Paneme Vasaku välja kokku ja lisame mängulauale
        VasakVali.getChildren().addAll(TaringuRuut, NuppudeAla);
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
            ManguRuudud[i-1] = new StackPane();
            //ManguRuudud[i].setId("manguruut-"+Integer.toString(i));

            Ruut = new Rectangle(ruuduLaius,ruuduKorgus);
            Ruut.setId("ruut-"+Integer.toString(i));
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

            ManguRuudud[i-1].getChildren().addAll(Ruut, RuuduNumber);
            ManguRuudustik.add(ManguRuudud[i-1],veerg,rida);

            if (suundTagasi){
                veerg--;
            } else {
                veerg++;
            }
            if ((i)%10==0){
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
        AnchorPane.setLeftAnchor(ManguRuudustik, ruuduLaius / 2);
        AnchorPane.setTopAnchor(ManguRuudustik, 1.0);

        //Paneme mängulaua kokku
        ManguLaud.getChildren().add(ManguLauaRaam);
        ManguLaud.setPadding(new Insets(10));
        GameBoard.setRight(ManguLaud);

    }
}
