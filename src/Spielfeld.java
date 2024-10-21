class Spielfeld {
  private SpielfeldZelle[][] spielfeld;
  private SpielfeldZelle aktiveZelle;
  private String zugText;
  private static int ANZAHLZEILEN = 7;
  private static int ANZAHLSPALTE = 7;

  public Spielfeld() {
    this.zugText = "Weiß darf einen Stein setzen";
    this.spielfeld = new SpielfeldZelle[ANZAHLZEILEN][ANZAHLSPALTE];
    spielfeld[0][0] = new SpielfeldZelle(0,0);
    spielfeld[0][3] = new SpielfeldZelle(spielfeld[0][0], null, 0, 3);
    spielfeld[0][6] = new SpielfeldZelle(spielfeld[0][3], null, 0, 6);
    spielfeld[1][1] = new SpielfeldZelle(1, 1);
    spielfeld[1][3] = new SpielfeldZelle(spielfeld[1][1], spielfeld[0][3], 1, 3);
    spielfeld[1][5] = new SpielfeldZelle(spielfeld[1][3], null, 1, 5);
    spielfeld[2][2] = new SpielfeldZelle(2, 2);
    spielfeld[2][3] = new SpielfeldZelle(spielfeld[2][2], spielfeld[1][3], 2, 3);
    spielfeld[2][4] = new SpielfeldZelle(spielfeld[2][3], null, 2, 4);
    spielfeld[3][0] = new SpielfeldZelle(null, spielfeld[0][0], 3, 0);
    spielfeld[3][1] = new SpielfeldZelle(spielfeld[3][0], spielfeld[1][1], 3, 1);
    spielfeld[3][2] = new SpielfeldZelle(spielfeld[3][1], spielfeld[2][2], 3, 2);
    spielfeld[3][4] = new SpielfeldZelle(null, spielfeld[2][4], 3, 4);;
    spielfeld[3][5] = new SpielfeldZelle(spielfeld[3][4], spielfeld[1][5], 3, 5);
    spielfeld[3][6] = new SpielfeldZelle(spielfeld[3][5], spielfeld[0][6], 3, 6);
    spielfeld[4][2] = new SpielfeldZelle(null, spielfeld[3][2], 4, 2);
    spielfeld[4][3] = new SpielfeldZelle(spielfeld[4][2], null, 4, 3);
    spielfeld[4][4] = new SpielfeldZelle(spielfeld[4][3], spielfeld[3][4], 4, 4);
    spielfeld[5][1] = new SpielfeldZelle(null, spielfeld[3][1], 5, 1);
    spielfeld[5][3] = new SpielfeldZelle(spielfeld[5][1], spielfeld[4][3], 5, 3);
    spielfeld[5][5] = new SpielfeldZelle(spielfeld[5][3], spielfeld[3][5], 5, 5);;
    spielfeld[6][0] = new SpielfeldZelle(null, spielfeld[3][0], 6, 0);
    spielfeld[6][3] = new SpielfeldZelle(spielfeld[6][0], spielfeld[5][3], 6, 3);
    spielfeld[6][6] = new SpielfeldZelle(spielfeld[6][3], spielfeld[3][6], 6, 6);
  }
  public int getAnzahlZeilen(){
    return ANZAHLZEILEN;
  }
  public int getAnzahlSpalten(){
    return ANZAHLSPALTE;
  }
  public SpielfeldZelle getSpielfeldZelle(int y, int x) {
    return spielfeld[y][x];
  }
  public SpielfeldZelle getAktiveZelle() {
    return aktiveZelle;
  }
  public void setAktiveZelle(SpielfeldZelle z) {
    aktiveZelle = z;
  }
  public String getZugText() {
    return zugText;
  }
  public void setZugText(String zugText) {
    this.zugText = zugText;
  }
}