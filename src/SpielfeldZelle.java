import java.util.ArrayList;

class SpielfeldZelle{
  
  public enum Farbe{
    WEISS,SCHWARZ,FREI;
    
    public String toString(){
      switch(this){
        case WEISS:
          return "Weiß";
        case SCHWARZ:
          return "Schwarz";
        default:
          return "Frei";
      }
    }
    public Farbe getGegner(){
      switch(this){
        case WEISS:
          return Farbe.SCHWARZ;
        case SCHWARZ:
          return Farbe.WEISS;
        default:
          return Farbe.FREI;
      }
    }
  }
  private Farbe belegt;
  private SpielfeldZelle rechts;
  private SpielfeldZelle unten;
  private SpielfeldZelle oben;
  private SpielfeldZelle links;
  private int Zeile;
  private int Spalte;

  public int getZeile(){
    return this.Zeile;
  }
  public int getSpalte(){
    return this.Spalte;
  }
  public void setZeile(int Zeile){
    this.Zeile = Zeile;
  }
  public void setSpalte(int Spalte){
    this.Spalte = Spalte;
  }  
  public Farbe getFarbe(){
    return this.belegt;
  }
  public void setFarbe(Farbe f){
    this.belegt=f;
  }
  public SpielfeldZelle(int z, int s){
    this.belegt=Farbe.FREI;
    this.Zeile=z;
    this.Spalte=s;
  }
  public SpielfeldZelle getRechts(){
    return this.rechts;
  }
  public void setRechts(SpielfeldZelle rechts){
    this.rechts=rechts;
  }
  public void setRechtsPlus(SpielfeldZelle rechts){
    if(rechts!=null){
      this.rechts=rechts;
      rechts.setLinks(this);
    }
  }
  public SpielfeldZelle getUnten(){
    return this.unten;
  }
  public void setUnten(SpielfeldZelle unten){
    this.unten=unten;
  }
  public void setUntenPlus(SpielfeldZelle unten){
    if(unten!=null){
      this.unten=unten;
      unten.setOben(this);
    }
  }
  public SpielfeldZelle getOben(){
    return this.oben;
  }
  public void setOben(SpielfeldZelle oben){
    this.oben=oben;
  }
  public void setObenPlus(SpielfeldZelle oben){
    if(oben!=null){
      this.oben=oben;
      oben.setUnten(this);
    }
  }
  public SpielfeldZelle getLinks(){
    return this.links;
  }
  public void setLinks(SpielfeldZelle links){
    this.links=links;
  }
  public void setLinksPlus(SpielfeldZelle links){
    if(links!=null){
      this.links=links;
      links.setRechts(this);
    }
  }
  public SpielfeldZelle(SpielfeldZelle links, SpielfeldZelle oben, int z, int s){
    this.setLinksPlus(links);
    this.setObenPlus(oben);
    this.belegt=Farbe.FREI;
    this.Zeile=z;
    this.Spalte=s;
  }
  public boolean hatFarbe(Farbe f){
    return this.belegt==f;
  }
  public boolean istFrei(){
    return this.belegt==Farbe.FREI;
  }
  public boolean horizontalMuehle(){
    if(this.getLinks()==null){
      return this.getRechts().horizontalMuehle();
    }
    if(this.getRechts()==null){
      return this.getLinks().horizontalMuehle();
    }
    if(this.istFrei()){
      return false;
    }
    return this.getLinks().hatFarbe(this.getFarbe())&&this.getRechts().hatFarbe(this.getFarbe());   
  }
  public boolean vertikalMuehle(){
    if(this.getOben()==null){
      return this.getUnten().vertikalMuehle();
    }
    if(this.getUnten()==null){
      return this.getOben().vertikalMuehle();
    }
    if(this.istFrei()){
      return false;
    }
    return this.getOben().hatFarbe(this.getFarbe())&&this.getUnten().hatFarbe(this.getFarbe());
  }
  public boolean inMuehle(){
    return this.vertikalMuehle()||this.horizontalMuehle();
  }
  public boolean setzeSpielstein(Farbe f){
    if(this.istFrei()){
      this.setFarbe(f);
      return true;
    }else{
      return false;
    }
  }
  public void entferneSpielstein(){
    this.setFarbe(Farbe.FREI);
  }
  public ArrayList<SpielfeldZelle> getAllNachbarn(){
    ArrayList<SpielfeldZelle> nachbarn=new ArrayList<SpielfeldZelle>();
    if(this.getRechts()!=null){
      nachbarn.add(this.getRechts());
    }
    if(this.getLinks()!=null){
      nachbarn.add(this.getLinks());
    }
    if(this.getOben()!=null){
      nachbarn.add(this.getOben());
    }
    if(this.getUnten()!=null){
      nachbarn.add(this.getUnten());
    }
    return nachbarn;
  }
  public boolean istNachbar(SpielfeldZelle z){
    ArrayList<SpielfeldZelle> nachbarn=this.getAllNachbarn();
    return nachbarn.contains(z);
  }
  public boolean kannSichBewegen(Farbe f){
    if(this.getFarbe()!=f){
      return false;
    }
    ArrayList<SpielfeldZelle> nachbarn=this.getAllNachbarn();
    for(int i=0;i<nachbarn.size();i++){
      if(nachbarn.get(i).getFarbe()==Farbe.FREI){
        return true;
      }
    }  
    return false;
  }
  
}