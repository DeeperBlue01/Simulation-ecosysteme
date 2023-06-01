import java.awt.*;

public class Perroquet extends Oiseau {
    public Perroquet(Color c,int nombre) {
        super(c,nombre);
    }
    public void positionGrille(int cellX,int cellY,Graphics g, Espece espece){
        g.setColor(espece.getCouleur());
        g.fillOval(cellX +20, cellY+70 , espece.getNombre()*5, espece.getNombre()*5);
    }
}