/**
 * Created by Peep on 12.12.2015.
 */
public class Mangija {
    String nimi;
    String nupuVarv;
    private Nupp manguNupp;
    static Integer sammudeArv=0;
    private Integer nupuasukoht=0;

    public boolean onTaringutVeeretanud=false;
    public boolean onNuppuLiigutanud=false;

    public Mangija(String mangijaNimi, String varvus) {
        nimi = mangijaNimi;
        nupuVarv = varvus;
        manguNupp = new Nupp(nupuVarv);
        manguNupp.mineLauale();
    }

    public static void veeretaTaringut() {
        sammudeArv = taring.veere();
    }

    public void liigutaNuppuEdasi(int kaikudearv) {
        nupuasukoht = nupuasukoht + kaikudearv;

        if (nupuasukoht > Lauamang.ManguRuutudeArv){
            manguNupp.mineRuudule(Lauamang.ManguRuutudeArv);
            nupuasukoht = Lauamang.ManguRuutudeArv - (nupuasukoht - Lauamang.ManguRuutudeArv);
            manguNupp.mineRuudule(nupuasukoht);
        } else if(nupuasukoht == Lauamang.ManguRuutudeArv){
            manguNupp.mineRuudule(nupuasukoht);
            Lauamang.mangLabi=true;
            System.out.println("Mang sai läbi");
        } else {
            System.out.println("Nupp liigutatakse positsioonile " + nupuasukoht);
            manguNupp.mineRuudule(nupuasukoht);
        }

    }

    public void liigutaNuppAlgusesse() {
        nupuasukoht = 1;
        manguNupp.mineRuudule(nupuasukoht);
    }
}
