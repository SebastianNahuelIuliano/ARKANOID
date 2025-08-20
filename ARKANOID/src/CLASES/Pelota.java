package CLASES;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Pelota {
    public Rectangle forma;
    public int dirX;
    public int dirY;

    public Pelota(int x, int y, int ancho, int alto, int dirX, int dirY) {
        forma = new Rectangle(x, y, ancho, alto);
        this.dirX = dirX;
        this.dirY = dirY;
    }

    public void mover(int anchoPanel, int altoPanel) {
        forma.x += dirX;
        forma.y += dirY;

        if (forma.x <= 0 || forma.x >= anchoPanel - forma.width) {
            dirX *= -1;
        }
        if (forma.y <= 0) {
            dirY *= -1;
        }
    }

    public void rebotarVertical() {
        dirY *= -1;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(forma.x, forma.y, forma.width, forma.height);
    }
}

