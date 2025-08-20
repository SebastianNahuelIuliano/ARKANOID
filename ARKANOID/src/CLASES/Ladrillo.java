package CLASES;

import java.awt.Rectangle;

public class Ladrillo {
    public Rectangle forma;
    public boolean destruido;

    public Ladrillo(int x, int y, int ancho, int alto) {
        this.forma = new Rectangle(x, y, ancho, alto);
        this.destruido = false;
    }

    public void dibujar(java.awt.Graphics g) {
        if (!destruido) {
            g.fillRect(forma.x, forma.y, forma.width, forma.height);
        }
    }
}

