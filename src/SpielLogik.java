class SpielLogik {
  private FarbInfo FarbeAmZug;
  private Spielfeld spielfeld;
  private SpielfeldZelle aktivierteZelle;
  private Zug naechsterZug;

  private enum Zug{
    SETZEN,
    ENTFERNEN,
    VERSCHIEBEN,
    ENDE
  }

  public SpielLogik(){
    this.spielfeld=new Spielfeld();
    FarbInfo weiss=new FarbInfo(SpielfeldZelle.Farbe.WEISS, 9, 0, null);
    FarbInfo schwarz=new FarbInfo(SpielfeldZelle.Farbe.SCHWARZ, 9, 0, weiss);
    weiss.setGegner(schwarz);
    this.FarbeAmZug=weiss;
    this.naechsterZug=Zug.SETZEN;
  }

  private FarbInfo getFarbeAmZug(){
    return this.FarbeAmZug;
  }
  private SpielfeldZelle getAktivierteZelle(){
    return this.aktivierteZelle;
  }
  private void setAktivierteZelle(SpielfeldZelle z){
    this.aktivierteZelle=z;
    this.spielfeld.setAktiveZelle(z);
  }
  private void SpielerWechsel(){
    this.FarbeAmZug=this.FarbeAmZug.getGegner();
  }
  private boolean KannSetzen(){
    return this.FarbeAmZug.getZuSetzen()>0;
  }
  private boolean KannEntfernen(){
    if(this.FarbeAmZug.getZuEntfernen()==0){
      return false;
    }
    boolean kannEntfernen=false;
    for(int i=0;i<this.spielfeld.getAnzahlZeilen();i++){
      for(int j=0;j<this.spielfeld.getAnzahlSpalten();j++){
        SpielfeldZelle zelle = this.spielfeld.getSpielfeldZelle(i,j);
        if(zelle!=null){
           if(zelle.hatFarbe(this.FarbeAmZug.getFarbe().getGegner()) && !zelle.inMuehle()){
            kannEntfernen=true;
          }
        }
      }
    }
    if(!kannEntfernen){
      this.FarbeAmZug.setZuEntfernen(0);
    }
    return kannEntfernen;
  }
  private boolean KannVerschieben(){
    if(this.FarbeAmZug.getImSpiel()<3){
      return false;
    }
    if(this.FarbeAmZug.getImSpiel()==3){
      return true;
    }
    boolean kannVerschieben=false;
    for(int i=0;i<this.spielfeld.getAnzahlZeilen();i++){
      for(int j=0;j<this.spielfeld.getAnzahlSpalten();j++){
        SpielfeldZelle zelle = this.spielfeld.getSpielfeldZelle(i,j);
        if(zelle!=null){
          if(zelle.kannSichBewegen(this.FarbeAmZug.getFarbe())){
            kannVerschieben=true;
          }
        }
      }
    }
    return kannVerschieben;
  }
  private boolean SetzZug(SpielfeldZelle Zelle){
    if(!this.KannSetzen()){
      return false;
    }
    if(Zelle.setzeSpielstein(this.FarbeAmZug.getFarbe())){
      this.FarbeAmZug.verringereZuSetzen();
      this.FarbeAmZug.erhoeheImSpiel();
      if(Zelle.vertikalMuehle()){
        FarbeAmZug.erhoeheZuEntfernen();
      }
      if(Zelle.horizontalMuehle()){
        FarbeAmZug.erhoeheZuEntfernen();
      }
      this.bestimmeNaechstenZug();
      return true;
    }else{
      return false;
    }
  }
  private void entferneZug(SpielfeldZelle Zelle){
    boolean farbTest = Zelle.hatFarbe(this.FarbeAmZug.getFarbe().getGegner());
    boolean muehleTest = Zelle.inMuehle();
    if(farbTest&&!muehleTest){
      Zelle.entferneSpielstein();
      this.FarbeAmZug.verringereZuEntfernen();
      this.FarbeAmZug.getGegner().verringereImSpiel();
      this.bestimmeNaechstenZug();
    }
  }
  private boolean darfSpringen(){
    return this.FarbeAmZug.getImSpiel()==3;
  }
  private boolean Bewegen(SpielfeldZelle Start, SpielfeldZelle Ziel){
    if(Start==null || Ziel==null){
      return false;
    }
    if(Start.getFarbe()!=this.FarbeAmZug.getFarbe() || Ziel.getFarbe()!=SpielfeldZelle.Farbe.FREI){
      return false;
    }
    if(Start.istNachbar(Ziel) || this.darfSpringen()){
      Start.entferneSpielstein();
      Ziel.setzeSpielstein(this.FarbeAmZug.getFarbe());
      if(Ziel.inMuehle()){
        FarbeAmZug.erhoeheZuEntfernen();
      }
      this.bestimmeNaechstenZug();
      this.setAktivierteZelle(null);
      return true;
    }
    return false;
  }
  public Spielfeld getFeld(){
    return this.spielfeld;
  }
  public boolean SpielSteinAbsetzen(SpielfeldZelle Zelle){
    if(this.getAktivierteZelle()==Zelle){
      this.setAktivierteZelle(null);
      return true;
    }else{
      return false;
    }
  }
  public boolean aktiviereZelle(SpielfeldZelle Zelle){
    if(Zelle==null){
      return false;
    }
    if(Zelle.getFarbe()!=this.FarbeAmZug.getFarbe()){
      return false;
    }
    this.setAktivierteZelle(Zelle);
    return true;
  }
  public void spielZug(SpielfeldZelle Zelle){
    if(Zelle==null){
      return;
    }
    switch(this.naechsterZug){
      case SETZEN:
        this.SetzZug(Zelle);
        break;
      case ENTFERNEN:
        this.entferneZug(Zelle);
        break;
      case VERSCHIEBEN:
        if(this.getAktivierteZelle()==null){
          this.aktiviereZelle(Zelle);
        }else{
          if(this.getAktivierteZelle()==Zelle){
            this.SpielSteinAbsetzen(Zelle);
          }else{
            this.Bewegen(this.getAktivierteZelle(), Zelle);
          }
        }
        break;
      case ENDE:
        //todo
        break;
    }
  
  }
  public void spielen(int zeile, int spalte){
    SpielfeldZelle Zelle=this.spielfeld.getSpielfeldZelle(zeile, spalte);
    this.spielZug(Zelle);
  }
  private void bestimmeNaechstenZug(){
    if(this.KannEntfernen()){
      this.naechsterZug=Zug.ENTFERNEN;
      this.spielfeld.setZugText(this.FarbeAmZug.getFarbe().toString() + " darf einen Stein entfernen");
      return;
    }
    this.SpielerWechsel();
    if(this.KannSetzen()){
      this.naechsterZug=Zug.SETZEN;
      this.spielfeld.setZugText(this.FarbeAmZug.getFarbe().toString() + " darf einen Stein setzen");
      return;
    }
    if(this.KannVerschieben()){
      this.naechsterZug=Zug.VERSCHIEBEN;
      this.spielfeld.setZugText(this.FarbeAmZug.getFarbe().toString() + " darf einen Stein verschieben");
      return;
    }
    this.naechsterZug=Zug.ENDE;
    this.spielfeld.setZugText(this.FarbeAmZug.getFarbe().getGegner().toString() + " hat gewonnen");
  }
  
}