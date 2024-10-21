class SpielfeldBild {
  private SpielfeldZelleBild spielfeld[][];
  private String zugText;

  public SpielfeldBild(Spielfeld feld){
    this.zugText = feld.getZugText();
    spielfeld = new SpielfeldZelleBild[7][7];
    for(int i = 0; i < 7; i++){
      for(int j = 0; j < 7; j++){
        spielfeld[i][j] =new SpielfeldZelleBild(feld.getSpielfeldZelle(i, j), false);
      }
    }
  }
  public String getZugText(){
    return this.zugText;
  }
  public void setZugText(String text){
    this.zugText = text;
  }
  public SpielfeldZelleBild getSpielfeldZelle(int y, int x){
    return this.spielfeld[y][x];
  }
  public void Updaten(Spielfeld feld){
    this.zugText = feld.getZugText();
    for(int i = 0; i < 7; i++){
      for(int j = 0; j < 7; j++){
        if(feld.getSpielfeldZelle(i, j)!=null){
          spielfeld[i][j].Updaten(feld.getSpielfeldZelle(i, j), feld.getAktiveZelle()==feld.getSpielfeldZelle(i, j));
        }
      }
    }
  }
}