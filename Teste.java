import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Teste extends JFrame {

    public Teste() {
        super("Reservador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(700, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(64, 150, 255));

        setVisible(true);
    }

    public static void main(String[] args) {
        new Teste();
    }
}
