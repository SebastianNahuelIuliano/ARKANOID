package CLASES;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

public class Juego extends JPanel implements KeyListener, ActionListener {

    private Timer timer;
    private Paleta paleta;
    private Pelota pelota;
    private ArrayList<Ladrillo> cccccccccccccccccccccccccccccccccccc;
    private int filas = 5;
    private int columnas = 10;
    private int score;
    private boolean gameOver;
    private boolean ganado;

    public Juego() {
        setFocusable(true);
        addKeyListener(this);

        ladrillos = new ArrayList<>();
        timer = new Timer(10, this);
        timer.start(); // El timer se inicia para comenzar el bucle del juego
    }

    public void iniciarPartida() {
        // Reset estado
        score = 0;
        gameOver = false;
        ganado = false;

        // Paleta
        paleta = new Paleta(350, 500, 100, 20);

        // Pelota centrada sobre la paleta y hacia arriba
        int pelotaX = paleta.forma.x + paleta.forma.width / 2 - 10;
        int pelotaY = paleta.forma.y - 20;
        pelota = new Pelota(pelotaX, pelotaY, 20, 20, 1, -1);

        // Ladrillos
        inicializarLadrillos();
    }

    private void inicializarLadrillos() {
        ladrillos.clear();
        int margenSuperior = 50;
        int margenLateral = 50;
        int espacioEntreLadrillos = 10;
        int ladrilloAncho = (getWidth() - 2 * margenLateral - (columnas - 1) * espacioEntreLadrillos) / columnas;
        int ladrilloAlto = 20;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int x = margenLateral + j * (ladrilloAncho + espacioEntreLadrillos);
                int y = margenSuperior + i * (ladrilloAlto + espacioEntreLadrillos);
                ladrillos.add(new Ladrillo(x, y, ladrilloAncho, ladrilloAlto));
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // La paleta y la pelota solo se dibujan si existen
        if (paleta != null) {
            paleta.dibujar(g);
        }
        if (pelota != null) {
            pelota.dibujar(g);
        }

        g.setColor(Color.GRAY);
        for (Ladrillo ladrillo : ladrillos) {
            ladrillo.dibujar(g);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 25));
        g.drawString("Score: " + score, 10, 25);

        if (gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Game Over", getWidth() / 2 - 120, getHeight() / 2);
        } else if (ganado) {
            g.setColor(Color.GREEN);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            g.drawString("Ganaste!", getWidth() / 2 - 100, getHeight() / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !ganado) {
            if (pelota != null) {
                pelota.mover(getWidth(), getHeight());

                if (pelota.forma.intersects(paleta.forma)) {
                    pelota.rebotarVertical();
                }

                for (Ladrillo ladrillo : ladrillos) {
                    if (!ladrillo.destruido && pelota.forma.intersects(ladrillo.forma)) {
                        ladrillo.destruido = true;
                        pelota.rebotarVertical();
                        score += 100;
                        break;
                    }
                }

                if (pelota.forma.y > getHeight()) {
                    gameOver = true;
                    timer.stop();
                }

                boolean todosDestruidos = true;
                for (Ladrillo ladrillo : ladrillos) {
                    if (!ladrillo.destruido) {
                        todosDestruidos = false;
                        break;
                    }
                }
                if (todosDestruidos) {
                    ganado = true;
                    timer.stop();
                }
            }
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            paleta.moverIzquierda(getWidth());
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            paleta.moverDerecha(getWidth());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
