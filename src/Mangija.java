/**
 * Created by Peep on 12.12.2015.
 */
public class Mangija {
    String nimi;
    String nupuVarv;
    Nupp manguNupp;
    static Integer kaikudeArv=0;

    public Mangija(String mangijaNimi, String varvus) {
        nimi = mangijaNimi;
        nupuVarv = varvus;
        manguNupp = new Nupp(nupuVarv);
        manguNupp.mineLauale();
    }


    public static void veeretaTaringut() {
        kaikudeArv = taring.veere();
    }
}
