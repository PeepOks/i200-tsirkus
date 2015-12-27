public class Mangija {
    String nimi;
    String nupuVarv;
    private Nupp manguNupp;
    public Integer sammudeArv=0;
    public Integer nupuasukoht=0;

    public Mangija(String mangijaNimi, String varvus) {
        nimi = mangijaNimi;
        nupuVarv = varvus;
        manguNupp = new Nupp(nupuVarv);
        manguNupp.mineLauale();
    }

    public void veeretaTaringut() {
        sammudeArv = taring.veere();
    }

    public void liigutaNuppuEdasi(int kaikudearv) {
        System.out.println("Nupu asukoht oli alguses " + nupuasukoht);
        nupuasukoht = nupuasukoht + kaikudearv;
        System.out.println("Nupp peaks minema ruudule " + nupuasukoht);

        //Otsime kas on kas nupu uues asukohas on usse või redeleid
        if(Lauamang.Redelid.containsKey(nupuasukoht)){
            System.out.println("Siin on redel ja lähme ruudule " + Lauamang.Redelid.get(nupuasukoht));
            nupuasukoht = Lauamang.Redelid.get(nupuasukoht);
        } else if (Lauamang.Ussid.containsKey(nupuasukoht)){
            System.out.println("Siin on uss ja lähme ruudule " + Lauamang.Ussid.get(nupuasukoht));
            nupuasukoht = Lauamang.Ussid.get(nupuasukoht);
        }

        //Kontrollime kas see ruut on vaba ja vajadusel saame uue asukoha kuhu minna
        nupuasukoht = kasManguRuutOnVaba(nupuasukoht);

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

    private Integer kasManguRuutOnVaba(Integer nupuasukoht) {
            for (int i = 0; i < Lauamang.Mangijad.size(); i++) {
                if (Lauamang.Mangijad.get(i).nupuasukoht == nupuasukoht && !this.nupuVarv.equals(Lauamang.Mangijad.get(i).nupuVarv)){
                    System.out.println("Keegi on juba sellel ruudul");
                    nupuasukoht--;
                } else if ( nupuasukoht == 1) {
                    break;
                }
            }
        return nupuasukoht;
    }

    public void liigutaNuppAlgusesse() {
        nupuasukoht = 1;
        manguNupp.mineRuudule(nupuasukoht);
    }
}
