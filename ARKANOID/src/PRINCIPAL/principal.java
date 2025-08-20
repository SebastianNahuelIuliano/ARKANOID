package PRINCIPAL;

import javax.swing.JFrame;
import CLASES.Juego;

public class principal {

    public static void main(String[] args) {
        JFrame ventana = new JFrame("Arkanoid");
        Juego juego = new Juego();

        ventana.add(juego);
        ventana.setSize(800, 600);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true); // La ventana se hace visible aqui

        juego.iniciarPartida(); // Ahora la partida se inicializa cuando la ventana tiene un tamaño
        juego.requestFocusInWindow(); // Le da el foco a la ventana del juego para que el teclado funcione
    }
}
