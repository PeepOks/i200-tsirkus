import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


/**
 * Created by peep on 16.10.15.
 */
public class taring {

    private static Image number1 = new Image("pildid/taring-1.png");
    private static ImagePattern taringNumber1 = new ImagePattern(number1);
    private static Image number2 = new Image("pildid/taring-2.png");
    private static ImagePattern taringNumber2 = new ImagePattern(number2);
    private static Image number3 = new Image("pildid/taring-3.png");
    private static ImagePattern taringNumber3 = new ImagePattern(number3);
    private static Image number4 = new Image("pildid/taring-4.png");
    private static ImagePattern taringNumber4 = new ImagePattern(number4);
    private static Image number5 = new Image("pildid/taring-5.png");
    private static ImagePattern taringNumber5 = new ImagePattern(number5);
    private static Image number6 = new Image("pildid/taring-6.png");
    private static ImagePattern taringNumber6 = new ImagePattern(number6);

    public int TaringuNumber;
    public taring(){
        kujutaTaringut(RandomTaringuNumber());
    }

    private static int RandomTaringuNumber () {
        return (int)(Math.random()*6)+1;
    }

    public int sammud () {
        boolean viskauuesti = true;
        int tulemus = 0;
        int vise = 0;
        while(viskauuesti) {
            vise = RandomTaringuNumber();
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

    private static void kujutaTaringut(int taringuNumber){
        switch (taringuNumber){
            case 1:
                System.out.println(taringuNumber);
                Lauamang.TaringuRuut.setFill(taringNumber1);
                break;
            case 2:
                System.out.println(taringuNumber);
                Lauamang.TaringuRuut.setFill(taringNumber2);
                break;
            case 3:
                System.out.println(taringuNumber);
                Lauamang.TaringuRuut.setFill(taringNumber3);
                break;
            case 4:
                System.out.println(taringuNumber);
                Lauamang.TaringuRuut.setFill(taringNumber4);
                break;
            case 5:
                System.out.println(taringuNumber);
                Lauamang.TaringuRuut.setFill(taringNumber5);
                break;
            case 6:
                System.out.println(taringuNumber);
                Lauamang.TaringuRuut.setFill(taringNumber6);
                break;
        }
    }

    public static int veere() {
        int taringuNumber = RandomTaringuNumber();
        kujutaTaringut(taringuNumber);
        return taringuNumber;
    }
}
