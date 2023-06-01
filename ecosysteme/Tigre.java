import java.awt.*;

public class Tigre extends Carnivore {
    public Tigre(Color c,int nombre) {
        super(c,nombre);
    }
    public void positionGrille(int cellX,int cellY,Graphics g, Espece espece){
        g.setColor(espece.getCouleur());
        g.fillOval(cellX +100, cellY+60 , espece.getNombre()*5, espece.getNombre()*5);
    }
}