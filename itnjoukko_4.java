
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] lukujoukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
		IntJoukko(KAPASITEETTI,OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
		IntJoukko(kapasiteetti,OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti ei voi olla negatiivinen");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei voi olla negatiivinen");//heitin vaan jotain :D
        }
        lukujoukko = new int[kapasiteetti];
        for (int i = 0; i < lukujoukko.length; i++) {
            lukujoukko[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;

    }
	
	private void lisaaTyhjaanJoukkoon(int lisaaTama){
        lukujoukko[0] = lisaaTama;
        alkioidenLkm++;			
	}
	
	private void lisaaPuuttuvaLukuJoukkoon(int lisaaTama){
		lukujoukko[alkioidenLkm] = lisaaTama;
        alkioidenLkm++;		
	}
	
	private void kasvataJoukonKokoa(){
		int[] vanhaJoukko = new int[lukujoukko.length];
		vanhaJoukko = lukujoukko;
		kopioiJoukko(lukujoukko, vanhaJoukko);
		lukujoukko = new int[alkioidenLkm + kasvatuskoko];
		kopioiJoukko(vanhaJoukko, lukujoukko);	
	}

    public boolean lisaa(int luku) {

		boolean joukkoOnTyhja = (alkioidenLkm==0);
		
        if (joukkoOnTyhja) {
			lisaaTyhjaanJoukkoon(luku)
            return true;
        } 
		else {
		
		boolean lukuOnJoJoukossa = (kuuluu(luku))
			if (lukuOnJoJoukossa){
				return false;
			}
		
			else{
				lisaaPuuttuvaLukuJoukkoon(luku);

				boolean joukkoaPitaaKasvattaa=(alkioidenLkm % lukujoukko.length == 0);

				if (joukkoaPitaaKasvattaa) {
					kasvataJoukonKokoa()
				}
				
				return true;
			}
		}
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujoukko[i]) {
                return true;
            }
        }
            return false;
    }

	private int annaLuvunIndeksi(int luku){
        int kohta = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujoukko[i]) {
                kohta = i; //siis luku löytyy tuosta kohdasta :D
            }
        }
		return kohta;
	}
	
	private void poistaLukuJoukosta(int luku) {
		
		lukujoukko[kohta] = 0;
		int apu;
		
        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            apu = lukujoukko[j];
            lukujoukko[j] = lukujoukko[j + 1];
            lukujoukko[j + 1] = apu;
        }
		
        alkioidenLkm--;			
	
	}

    public boolean poista(int luku) {
        int kohta = annaLuvunIndeksi(luku);
		
		boolean lukuaEiLoydy=(kohta == -1);
		
		if(lukuaEiLoydy){
			return false;
		}
		else{
			poistaLukuJoukosta(luku)
            return true;
        }

    }

    private void kopioiJoukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + lukujoukko[0] + "}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += lukujoukko[i];
                tuotos += ", ";
            }
            tuotos += lukujoukko[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] lukujoukko = new int[alkioidenLkm];
        for (int i = 0; i < lukujoukko.length; i++) {
            lukujoukko[i] = lukujoukko[i];
        }
        return lukujoukko;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        int[] aJoukko = a.toIntArray();
        int[] bJoukko = b.toIntArray();
        for (int i = 0; i < aJoukko.length; i++) {
            x.lisaa(aJoukko[i]);
        }
        for (int i = 0; i < bJoukko.length; i++) {
            x.lisaa(bJoukko[i]);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        int[] aJoukko = a.toIntArray();
        int[] bJoukko = b.toIntArray();
        for (int i = 0; i < aJoukko.length; i++) {
            for (int j = 0; j < bJoukko.length; j++) {
                if (aJoukko[i] == bJoukko[j]) {
                    y.lisaa(bJoukko[j]);
                }
            }
        }
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        int[] aJoukko = a.toIntArray();
        int[] bJoukko = b.toIntArray();
        for (int i = 0; i < aJoukko.length; i++) {
            z.lisaa(aJoukko[i]);
        }
        for (int i = 0; i < bJoukko.length; i++) {
            z.poista(bJoukko[i]);
        }
 
        return z;
    }
        
}