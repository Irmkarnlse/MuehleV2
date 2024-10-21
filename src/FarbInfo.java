class FarbInfo {
  private SpielfeldZelle.Farbe farbe;
  private int anzahlZuSetzen;
  private int anzahlImSpiel;
  private FarbInfo gegner;
  private int zuEntfernen;

  public FarbInfo(SpielfeldZelle.Farbe f, int zuSetzen, int imSpiel, FarbInfo g){
    this.farbe=f;
    this.anzahlZuSetzen=zuSetzen;
    this.anzahlImSpiel=imSpiel;
    this.gegner=g;
    this.zuEntfernen=0;
  }
  public int getZuEntfernen(){
    return this.zuEntfernen;
  }
  public SpielfeldZelle.Farbe getFarbe(){
    return this.farbe;
  }
  public int getZuSetzen(){
    return this.anzahlZuSetzen;
  }
  public int getImSpiel(){
    return this.anzahlImSpiel;
  }
  public FarbInfo getGegner(){
    return this.gegner;
  }
  public void setZuEntfernen(int z){
    this.zuEntfernen=z;
  }
  public void setAnzahlZuSetzen(int anzahl){
    this.anzahlZuSetzen=anzahl;
  }
  public void setAnzahlImSpiel(int anzahl){
    this.anzahlImSpiel=anzahl;
  }
  public void setGegner(FarbInfo g){
    this.gegner=g;
  }
  public void verringereZuSetzen(){
    this.anzahlZuSetzen--;
  }
  public void verringereImSpiel(){
    this.anzahlImSpiel--;
  }
  public void erhoeheImSpiel(){
    this.anzahlImSpiel++;
  }
  public void erhoeheZuEntfernen(){
    this.zuEntfernen++;
  }
  public void verringereZuEntfernen(){
    this.zuEntfernen--;
  }
}