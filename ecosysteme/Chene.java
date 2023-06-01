import java.awt.*;

public class Chene extends Arbre {
    public Chene(Color c,int nombre) {
        super(c,nombre);
    }
    public void positionGrille(int cellX,int cellY,Graphics g, Espece espece){
        g.setColor(espece.getCouleur());
        g.fillOval(cellX +20, cellY+130 , espece.getNombre()*5, espece.getNombre()*5);
    }
}