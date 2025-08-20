package CLASES;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Paleta {
    public Rectangle forma;

    public Paleta(int x, int y, int ancho, int alto) {
        forma = new Rectangle(x, y, ancho, alto);
    }

    public void moverIzquierda(int limite) {
        if (forma.x > 0) {
            forma.x -= 15;
            if (forma.x < 0) forma.x = 0;
        }
    }

    public void moverDerecha(int limite) {
        if (forma.x < limite - forma.width) {
            forma.x += 15;
            if (forma.x > limite - forma.width) forma.x = limite - forma.width;
        }
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(forma.x, forma.y, forma.width, forma.height);
    }
}

