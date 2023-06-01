import java.awt.*;

public class Guepe extends Insecte {
    public Guepe(Color c, int nombre) {
        super(c, nombre);
    }

    public void positionGrille(int cellX, int cellY, Graphics g, Espece espece) {
        g.setColor(espece.getCouleur());
        g.fillOval(cellX + 100, cellY + 5, espece.getNombre() * 5, espece.getNombre() * 5);
    }
}
