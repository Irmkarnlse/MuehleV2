/*
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Panel extends JPanel implements MouseListener{
  
  private static final long serialVersionUID = 1L;
  BufferedImage pic;  //BufferedImage zum Speichern des Bildes

  public Panel(){  
    super();
    
    URL pic_url = this.getClass().getClassLoader().getResource("muehleBild.jpg"); //kein Slash vor dem Unterordner!

    //Bild laden mit ImageIO
    try {
      pic = ImageIO.read(pic_url);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.setSize(440, 440);
    setVisible(true);
    setBackground(Color.black);
    this.addMouseListener(this);
  }
  
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(pic,20,20,this);

  }
  
  public void mouseClicked(MouseEvent e) {
    System.out.println("MouseP " + e.getClickCount() + " times clicked at " + e.getPoint());
    int zeile=(int) e.getPoint().y/40;
    int spalte=(int) e.getPoint().x/40;
    System.out.println(zeile +" " + spalte);
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
*/