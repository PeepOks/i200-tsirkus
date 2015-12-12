import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

/**
 * Created by Peep on 12.12.2015.
 */
public class Nupp {
    private static Image kollane = new Image("pildid/kollane.gif");
    private static ImagePattern kollaseNupuPilt = new ImagePattern(kollane);
    private static Image punane = new Image("pildid/punane.gif");
    private static ImagePattern punaseNupuPilt = new ImagePattern(punane);
    private static Image roheline = new Image("pildid/roheline.gif");
    private static ImagePattern roheliseNupuPilt = new ImagePattern(roheline);
    private static Image sinine = new Image("pildid/sinine.gif");
    private static ImagePattern siniseNupuPilt = new ImagePattern(sinine);
    private static int nupuKorgus = 40;
    private static int nupuLaius = 25;

    public static void lisaNuppMangu(String varv) {
        switch (varv) {
            case "Punane":
                System.out.println("Lisame " + varv + " nupu");
                Rectangle punaseNupuRuut = new Rectangle();
                punaseNupuRuut.setHeight(nupuKorgus);
                punaseNupuRuut.setWidth(nupuLaius);
                punaseNupuRuut.setFill(punaseNupuPilt);
                Lauamang.NuppudeAla.getChildren().add(punaseNupuRuut);
                break;
            case "Roheline":
                System.out.println("Lisame " + varv + " nupu");
                Rectangle roheliseNupuRuut = new Rectangle();
                roheliseNupuRuut.setHeight(nupuKorgus);
                roheliseNupuRuut.setWidth(nupuLaius);
                roheliseNupuRuut.setFill(roheliseNupuPilt);
                Lauamang.NuppudeAla.getChildren().add(roheliseNupuRuut);
                break;
            case "Kollane":
                System.out.println("Lisame " + varv + " nupu");
                Rectangle kollaseNupuRuut = new Rectangle();
                kollaseNupuRuut.setHeight(nupuKorgus);
                kollaseNupuRuut.setWidth(nupuLaius);
                kollaseNupuRuut.setFill(kollaseNupuPilt);
                Lauamang.NuppudeAla.getChildren().add(kollaseNupuRuut);
                break;
            case "Sinine":
                System.out.println("Lisame " + varv + " nupu");
                Rectangle siniseNupuRuut = new Rectangle();
                siniseNupuRuut.setHeight(nupuKorgus);
                siniseNupuRuut.setWidth(nupuLaius);
                siniseNupuRuut.setFill(siniseNupuPilt);
                Lauamang.NuppudeAla.getChildren().add(siniseNupuRuut);
                break;
        }

    }
}
