class Main {
  public static void main(String[] args) {
    
    Frame fenster=new Frame();
    SpielLogik logik=new SpielLogik();
    SpielfeldBild spielfeld=new SpielfeldBild(logik.getFeld());
    Panel panel=new Panel(spielfeld, logik);
    fenster.add(panel);
    fenster.setVisible(true);
    
    //new PicPanel();
  }
}