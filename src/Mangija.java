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
        //Saame uue nupu asukoha
        nupuasukoht = nupuasukoht + kaikudearv;
        //Otsime kas on kas nupu uues asukohas on usse või redeleid või muid takistusi.
        nupuasukoht = kasManguruudulOnTakistusi(nupuasukoht);

        if (nupuasukoht > Lauamang.ManguRuutudeArv){
            manguNupp.mineRuudule(Lauamang.ManguRuutudeArv);
            nupuasukoht = Lauamang.ManguRuutudeArv - (nupuasukoht - Lauamang.ManguRuutudeArv);
            //teeme uuesti kontrollid
            nupuasukoht = kasManguruudulOnTakistusi(nupuasukoht);

            //Liigume vabale ruudule
            manguNupp.mineRuudule(nupuasukoht);
        } else if(nupuasukoht == Lauamang.ManguRuutudeArv){
            manguNupp.mineRuudule(nupuasukoht);
            Lauamang.mangSaiLabi();
            System.out.println("Mang sai läbi");
        } else {
            //System.out.println("Nupp liigutatakse positsioonile " + nupuasukoht);
            manguNupp.mineRuudule(nupuasukoht);
        }

    }

    private Integer kasManguruudulOnTakistusi(Integer nupuasukoht) {
        int uusNupuAsukoht = nupuasukoht;
        if(Lauamang.Redelid.containsKey(nupuasukoht)){
            //System.out.println("Siin on redel ja lähme ruudule " + Lauamang.Redelid.get(nupuasukoht));
            uusNupuAsukoht = Lauamang.Redelid.get(nupuasukoht);
            return uusNupuAsukoht;
        } else if (Lauamang.Ussid.containsKey(nupuasukoht)){
            //System.out.println("Siin on uss ja lähme ruudule " + Lauamang.Ussid.get(nupuasukoht));
            uusNupuAsukoht = Lauamang.Ussid.get(nupuasukoht);
            return uusNupuAsukoht;
        }
        for (int i = 0; i < Lauamang.Mangijad.size(); i++) {
            if (Lauamang.Mangijad.get(i).nupuasukoht == nupuasukoht && !this.nupuVarv.equals(Lauamang.Mangijad.get(i).nupuVarv)){
                //System.out.println("Keegi on juba sellel ruudul");
                uusNupuAsukoht--;
            } else if ( nupuasukoht == 1) {
                break;
            }
        }
        return uusNupuAsukoht;
    }

    public void liigutaNuppAlgusesse() {
        nupuasukoht = 1;
        manguNupp.mineRuudule(nupuasukoht);
    }
}
