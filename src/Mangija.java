/**
 * Created by Peep on 12.12.2015.
 */
public class Mangija {
    String nimi;
    String nupuVarv;
    static Integer kaikudeArv;

    public Mangija(String mangijaNimi) {
        nimi = mangijaNimi;
    }


    public static void veeretaTaringut() {
        kaikudeArv = taring.veere();
    }
}
