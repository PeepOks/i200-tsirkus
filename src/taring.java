import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import sun.plugin2.message.GetAppletMessage;

/**
 * Created by peep on 16.10.15.
 */
public class taring {
    public int TaringuNumber;
    String TaringuVarv;

    public taring(){
        TaringuNumber = TaringuNumber();

        looTaring();
    }

    private void looTaring() {
        StackPane stack = new StackPane();
        Rectangle taringukuju = new Rectangle(30, 30, 50, 50);
        taringukuju.setArcHeight(10.0);
        taringukuju.setArcWidth(10.0);

        taringukuju.setFill(Color.RED);

        Text taringunumber = new Text();
        //Täringu sisu
        taringunumber.setText(Integer.toString(TaringuNumber));
        taringunumber.setFill(Color.WHITE);
        taringunumber.setFont(Font.font("Verdana", 20));
        taringunumber.setTextAlignment(TextAlignment.RIGHT);
        stack.getChildren().addAll(taringukuju,taringunumber);

    }


    private static int TaringuNumber () {
        return (int)(Math.random()*6)+1;
    }

    public int sammud () {
        boolean viskauuesti = true;
        int tulemus = 0;
        int vise = 0;
        while(viskauuesti) {
            vise = TaringuNumber();
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
