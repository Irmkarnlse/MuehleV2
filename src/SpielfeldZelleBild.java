import java.awt.Color;

class SpielfeldZelleBild {
  boolean verbindungOben;
  boolean verbindungUnten;
  boolean verbindungLinks;
  boolean verbindungRechts;
  boolean Feld;
  Color Spielstein;
  boolean aktiv;
  static boolean horizontaleVerbindung=true;

  public SpielfeldZelleBild(SpielfeldZelle Zelle, boolean aktiv){
    if(Zelle != null){
      this.verbindungOben = Zelle.getOben() != null;
      this.verbindungUnten = Zelle.getUnten() != null;
      this.verbindungLinks = Zelle.getLinks() != null;
      this.verbindungRechts = Zelle.getRechts() != null;
      horizontaleVerbindung = Zelle.getRechts() != null;
      this.Feld = true;
      this.aktiv = aktiv;
    }else{
      if(horizontaleVerbindung){
        this.setHorizontaeleVerbindung();
      }else{
        this.setVertikaleVerbindung();
      }
    }
  }
  public void setVertikaleVerbindung(){
    this.verbindungOben = true;
    this.verbindungUnten = true;
    this.verbindungLinks = false;
    this.verbindungRechts = false;
    this.Feld = false;
  }
  public void setHorizontaeleVerbindung(){
    this.verbindungOben = false;
    this.verbindungUnten = false;
    this.verbindungLinks = true;
    this.verbindungRechts = true;
    this.Feld = false;
  }
  public boolean getVerbindungOben(){
    return this.verbindungOben;
  }
  public boolean getVerbindungUnten(){
    return this.verbindungUnten;
  }
  public boolean getVerbindungLinks(){
    return this.verbindungLinks;
  }
  public boolean getVerbindungRechts(){
    return this.verbindungRechts;
  }
  public boolean getFeld(){
    return this.Feld;
  }
  public Color getSpielstein(){
    return this.Spielstein;
  }
  public void setSpielstein(Color Spielstein){
    this.Spielstein = Spielstein;
  }
  public boolean getAktiv(){
    return this.aktiv;
  }
  public void Updaten(SpielfeldZelle Zelle, boolean aktiv){
    if(Zelle!=null){
      if(Zelle.getFarbe()==SpielfeldZelle.Farbe.SCHWARZ){
        this.setSpielstein(Color.DARK_GRAY);
      }
      if(Zelle.getFarbe()==SpielfeldZelle.Farbe.WEISS){
        this.setSpielstein(Color.white);
      }
      if(Zelle.getFarbe()==SpielfeldZelle.Farbe.FREI){
        this.setSpielstein(null);
      }
      this.aktiv=aktiv;
    }
  }
  
}