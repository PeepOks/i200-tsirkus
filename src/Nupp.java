import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Nupp {
    private static Image kollane = new Image("pildid/kollane.gif");
    private static ImagePattern kollaseNupuPilt = new ImagePattern(kollane);
    private static Image punane = new Image("pildid/punane.gif");
    private static ImagePattern punaseNupuPilt = new ImagePattern(punane);
    private static Image roheline = new Image("pildid/roheline.gif");
    private static ImagePattern roheliseNupuPilt = new ImagePattern(roheline);
    private static Image sinine = new Image("pildid/sinine.gif");
    private static ImagePattern siniseNupuPilt = new ImagePattern(sinine);
    private static int nupuKorgus = 30;
    private static int nupuLaius = 20;
    String nupuVarv;
    Rectangle nupp;

    public Nupp(String varvus){
        nupuVarv = varvus;
        looNupp(nupuVarv);

    }

    private Rectangle looNupp(String varv){
        if (varv.toLowerCase().equals("punane")){
            //System.out.println("Lisame " + varv + " nupu");
            nupp = new Rectangle();
            nupp.setHeight(nupuKorgus);
            nupp.setWidth(nupuLaius);
            nupp.setFill(punaseNupuPilt);
            return nupp;
        } else if (varv.toLowerCase().equals("kollane")){
            //System.out.println("Lisame " + varv + " nupu");
            nupp = new Rectangle();
            nupp.setHeight(nupuKorgus);
            nupp.setWidth(nupuLaius);
            nupp.setFill(kollaseNupuPilt);
            return nupp;
        } else if (varv.toLowerCase().equals("roheline")){
            //System.out.println("Lisame " + varv + " nupu");
            nupp = new Rectangle();
            nupp.setHeight(nupuKorgus);
            nupp.setWidth(nupuLaius);
            nupp.setFill(roheliseNupuPilt);
            return nupp;
        } else if (varv.toLowerCase().equals("sinine")){
            //System.out.println("Lisame " + varv + " nupu");
            nupp = new Rectangle();
            nupp.setHeight(nupuKorgus);
            nupp.setWidth(nupuLaius);
            nupp.setFill(siniseNupuPilt);
            return nupp;
        }
        return null;
    }

    public void mineRuudule(int ruuduNumber) {
        //System.out.println("Nupp läheb ruudule " + ruuduNumber);

        Lauamang.ManguRuudud[ruuduNumber-1].getChildren().add(this.nupp);
    }

    public void mineLauale() {
        //System.out.println("Nupp läheb mängulauale");
        Lauamang.NuppudeAla.getChildren().add(this.nupp);
    }
}
