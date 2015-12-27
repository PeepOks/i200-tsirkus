import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.lang.annotation.Inherited;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static javafx.geometry.Pos.CENTER;

public class Lauamang extends ActionEvent{

    Stage TsirkuseMang;
    StackPane Game;
    BorderPane GameBoard;
    GridPane ManguRuudustik;
    Rectangle Ruut;
    static Integer ManguRuutudeArv=120;
    static StackPane[] ManguRuudud = new StackPane[ManguRuutudeArv];

    static ArrayList<Mangija> Mangijad = new ArrayList<>();
    private Integer MangijaNumber;

    static Rectangle TaringuRuut;
    static VBox NuppudeAla;
    double ManguLauaLaius = 450.0;
    double ManguLauaKorgus = 420.0;
    static boolean mangLabi = false;

    private Button VeeretamisNupp;
    private Button LiikumisNupp;
    private Button JargmineMangija;

    private Label Info;

    static HashMap<Integer, Integer> Redelid;
    static HashMap<Integer, Integer> Ussid;

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
        LooManguLaud();

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

        //Alustab 1. mängija
        MangijaNumber = 0;

        //Kui nupp pole mängus siis läheb mängulaua algusesse
        if (Mangijad.get(MangijaNumber).nupuasukoht==0){
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
        taust.setAlignment(CENTER);
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
        Info.setAlignment(CENTER);
        Info.setStyle("-fx-background-color: green");

        GameBoard.setTop(Info);
    }

    private void LooManguJalus() {
        HBox nupud = new HBox();
        nupud.setPadding(new Insets(10));
        nupud.setSpacing(10);

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

            if (Mangijad.get(MangijaNumber).sammudeArv == 6) {
                Info.setText(Mangijad.get(MangijaNumber).nimi + " saab veel veeretada.");
                VeeretamisNupp.setDisable(false);
                LiikumisNupp.setDisable(true);
            } else {
                JargmineMangija.setDisable(false);
                LiikumisNupp.setDisable(true);
            }
        });

        //Loome nupu mängija vahetamiseks
        JargmineMangija = new Button("Järgmine mängija");
        JargmineMangija.setOnAction(event -> {
            if (MangijaNumber == Mangijad.size()-1){
                MangijaNumber = 0;
            } else {
                MangijaNumber++;
            }
            //Kui nupp pole mängus siis läheb mängulaua algusesse
            if (Mangijad.get(MangijaNumber).nupuasukoht==0){
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
        GameBoard.setMinSize(650, 600);
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

    private void LooManguLaud () {
        // Teeb sobiva mängulaua
        StackPane ManguLaud = new StackPane();


        //Loome mänguruudustiku
        ManguRuudustik = new GridPane();
        ManguRuudustik.setPrefSize(ManguLauaLaius,ManguLauaKorgus);
        ManguRuudustik.setGridLinesVisible(false);
        ManguRuudustik.setHgap(3);
        ManguRuudustik.setVgap(3);


        double ruuduLaius = (ManguLauaLaius-(ManguRuudustik.getHgap()*11))/10;
        double ruuduKorgus = (ManguLauaKorgus-(ManguRuudustik.getVgap()*13))/(ManguRuutudeArv/10);

        System.out.println("Laius " + ruuduLaius);
        System.out.println("Korgus " + ruuduKorgus);
        int rida = ManguRuutudeArv/10;
        int veerg = 1;
        boolean suundTagasi = false;
        for (int i=1; i<=ManguRuutudeArv; i++){
            ManguRuudud[i-1] = new StackPane();
            //ManguRuudud[i].setId("manguruut-"+Integer.toString(i));

            Ruut = new Rectangle(ruuduLaius,ruuduKorgus);
            Ruut.setId("ruut-"+Integer.toString(i));
            Ruut.setOpacity(0);
            //Label RuuduNumber = new Label(Integer.toString(i));
            //RuuduNumber.setFont(Font.font("Verdana", 12));
            //RuuduNumber.setTextAlignment(TextAlignment.RIGHT);
            //RuuduNumber.setStyle("-fx-border-color:red; -fx-background-color: Green;");
            //RuuduNumber.setPadding(new Insets(10,0,0,10));

            //Värvime ruudu tausta
            /*
            if (i%2==0){
                Ruut.setFill(Color.BEIGE);
            } else {
                Ruut.setFill(Color.LIGHTGREEN);
            }
            */
            ManguRuudud[i-1].getChildren().addAll(Ruut);
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
        ImagePattern taust = new ImagePattern(new Image("pildid/laua-taust.png"));
        Rectangle ManguLauaTaust = new Rectangle(ManguLauaLaius, ManguLauaKorgus);
        ManguLauaTaust.setArcHeight(20.0);
        ManguLauaTaust.setArcWidth(20.0);
        ManguLauaTaust.setFill(taust);


        ManguLaud.getChildren().addAll(ManguLauaTaust, ManguRuudustik);
        ManguLaud.setPadding(new Insets(15));
        GameBoard.setRight(ManguLaud);

        //Lisame mängu täringu
        new taring();
        // Lisame uusid ja redelid mangulauale
        LooUssidJaRedelid();

    }

    private void LooUssidJaRedelid() {
        //Loome redelite asukohad
        Redelid = new HashMap<>();
        Redelid.put(3,37);
        Redelid.put(5,15);
        Redelid.put(14,34);
        Redelid.put(21,42);
        Redelid.put(31,50);
        Redelid.put(38,43);
        Redelid.put(47,92);
        Redelid.put(57,66);
        Redelid.put(60,81);
        Redelid.put(74,86);
        Redelid.put(87,108);
        Redelid.put(95,115);

        //Loome usside asukohad
        Ussid = new HashMap<>();
        Ussid.put(20,2);
        Ussid.put(30,10);
        Ussid.put(35,25);
        Ussid.put(48,28);
        Ussid.put(55,45);
        Ussid.put(70,52);
        Ussid.put(85,59);
        Ussid.put(103,82);
        Ussid.put(109,88);
        Ussid.put(113,107);
        Ussid.put(117,97);
        Ussid.put(119,103);
    }
}
