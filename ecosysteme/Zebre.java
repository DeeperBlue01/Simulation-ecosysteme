import java.awt.*;

public class Zebre extends Herbivore {
    public Zebre(Color c,int nombre) {
        super(c,nombre);
    }
    public void positionGrille(int cellX,int cellY,Graphics g, Espece espece){
        g.setColor(espece.getCouleur());
        g.fillOval(cellX +100, cellY+30 , espece.getNombre()*5, espece.getNombre()*5);
    }
}