import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.*;
import java.applet.*;


public class Panel extends JPanel implements MouseListener{
  private int SizeX;
  private int SizeY;
  private int SizeYText;
  private int SizeArray;
  private SpielfeldBild Bild;
  private SpielLogik Logik;
  private static Color aktiverStein = new Color(255,255,102);
  private static Color hintergrund = new Color(255,255,203);
  
  private void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
    g.setColor(Color.black);
    Graphics2D g2d = (Graphics2D)g;
    int width = 5;
    g2d.setStroke(new BasicStroke(width));
    g2d.drawLine(x1, y1, x2, y2);
  }
  public void PaintObenVerbindungslinie(int x, int y, Graphics g){
    int x1 = (int) Math.round(SizeX/SizeArray*(x+0.5));
    int y1 = (int) Math.round(SizeY/SizeArray*(y+0.5))+this.SizeYText;
    int x2 = (int) Math.round(SizeX/SizeArray*(x+0.5));
    int y2 = (int) Math.round(SizeY/SizeArray*(y))+this.SizeYText;
    this.drawLine(g, x1, y1, x2, y2);
  }
  public void PaintRechtsVerbindungslinie(int x, int y, Graphics g){
    int x1 = (int) Math.round(SizeX/SizeArray*(x+0.5));
    int y1 = (int) Math.round(SizeY/SizeArray*(y+0.5))+this.SizeYText;
    int x2 = (int) Math.round(SizeX/SizeArray*(x+1));
    int y2 = (int) Math.round(SizeY/SizeArray*(y+0.5))+this.SizeYText;
    this.drawLine(g, x1, y1, x2, y2);
  }
  public void PaintUntenVerbindungslinie(int x, int y, Graphics g){
    int x1 = (int) Math.round(SizeX/SizeArray*(x+0.5));
    int y1 = (int) Math.round(SizeY/SizeArray*(y+0.5))+this.SizeYText;
    int x2 = (int) Math.round(SizeX/SizeArray*(x+0.5));
    int y2 = (int) Math.round(SizeY/SizeArray*(y+1))+this.SizeYText;
    this.drawLine(g, x1, y1, x2, y2);
  }
  public void PaintLinksVerbindungslinie(int x, int y, Graphics g){
    int x1 = (int) Math.round(SizeX/SizeArray*(x+0.5));
    int y1 = (int) Math.round(SizeY/SizeArray*(y+0.5))+this.SizeYText;
    int x2 = (int) Math.round(SizeX/SizeArray*(x));
    int y2 = (int) Math.round(SizeY/SizeArray*(y+0.5))+this.SizeYText;
    this.drawLine(g, x1, y1, x2, y2);
  }
  public void PaintKreis(int x, int y, Graphics g){
    int r = 20;
    int x1 = (int) Math.round(SizeX/SizeArray*(x+0.5)-r/2);
    int y1 = (int) Math.round(SizeY/SizeArray*(y+0.5)-r/2)+this.SizeYText;
    g.setColor(Color.black);
    g.fillOval(x1, y1, r, r);
  }
  public void PaintSpielstein(int x, int y, Graphics g, Color c){
    int r = 50;
    int offset = 5;
    int x1 = (int) Math.round(SizeX/SizeArray*(x+0.5)-r/2);
    int y1 = (int) Math.round(SizeY/SizeArray*(y+0.5)-r/2)+this.SizeYText;
    g.setColor(Color.black);
    g.fillOval(x1-offset, y1-offset, r+offset*2, r+offset*2);
    g.setColor(c);
    g.fillOval(x1, y1, r, r);
  }
  public void PaintAktivenStein(int x, int y, Graphics g){
    int r = 30;
    int x1 = (int) Math.round(SizeX/SizeArray*(x+0.5)-r/2);
    int y1 = (int) Math.round(SizeY/SizeArray*(y+0.5)-r/2)+this.SizeYText;
    g.setColor(aktiverStein);
    g.fillOval(x1, y1, r, r);
  }
  public void paintSpielfeldBG(SpielfeldBild bild, Graphics g){
    for(int i = 0; i < SizeArray; i++){
      for(int j = 0; j < SizeArray; j++){
        if(bild.getSpielfeldZelle(i, j).getVerbindungOben()){
          PaintObenVerbindungslinie(j, i, g);
        }
        if(bild.getSpielfeldZelle(i, j).getVerbindungRechts()){
          PaintRechtsVerbindungslinie(j, i, g);
        }
        if(bild.getSpielfeldZelle(i, j).getVerbindungUnten()){
          PaintUntenVerbindungslinie(j, i, g);
        }
        if(bild.getSpielfeldZelle(i, j).getVerbindungLinks()){
          PaintLinksVerbindungslinie(j, i, g);
        }
        if(bild.getSpielfeldZelle(i, j).getFeld()){
          PaintKreis(j, i, g);
        }
        if(bild.getSpielfeldZelle(i, j).getSpielstein()!=null){
          PaintSpielstein(j, i, g, bild.getSpielfeldZelle(i, j).getSpielstein());
          if(bild.getSpielfeldZelle(i, j).getAktiv()){
            PaintAktivenStein(j, i, g);
          }
        }
      }
    }
  }
  public void schreibeText(Graphics g, String text){
    int fontSize = 15;
    int x=50;
    int y=40;
    g.setColor(Color.black);
    g.setFont(new Font("TimesRoman", Font.PLAIN, fontSize));
    g.drawString(text, x, y);
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.paintSpielfeldBG(this.Bild, g);
    this.schreibeText(g, this.Bild.getZugText());
  }
  
  public Panel(SpielfeldBild bild, SpielLogik logik){  
    super();
    this.Bild=bild;
    this.Logik=logik;
    this.SizeArray=7;
    this.SizeX = 500;
    this.SizeY = 500;
    this.SizeYText = 50;
    this.setSize(this.SizeX, this.SizeY+this.SizeYText);
    setVisible(true);
    setBackground(hintergrund);
    this.addMouseListener(this);
  }


  public void mouseClicked(MouseEvent e) {
    //System.out.println("MouseP " + e.getClickCount() + " times clicked at " + e.getPoint());
    int zeile=(int) (e.getPoint().y-this.SizeYText)/(SizeY/SizeArray);
    int spalte=(int) e.getPoint().x/(SizeX/SizeArray);
    //System.out.println(zeile +" " + spalte);
    this.Logik.spielen(zeile, spalte);
    Bild.Updaten(this.Logik.getFeld());
    this.updateUI();
  }

  @Override
  public void mousePressed(MouseEvent e) {}
  @Override
  public void mouseReleased(MouseEvent e) {}
  @Override
  public void mouseEntered(MouseEvent e) {}
  @Override
  public void mouseExited(MouseEvent e) {}

}