import java.awt.*;

public class Lion extends Carnivore {
    public Lion(Color c,int nombre) {
        super(c,nombre);
    }
    public void positionGrille(int cellX,int cellY,Graphics g, Espece espece){
        g.setColor(espece.getCouleur());
        g.fillOval(cellX +20, cellY+60 , espece.getNombre()*5, espece.getNombre()*5);
    }
}