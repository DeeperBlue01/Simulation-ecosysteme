import java.awt.*;

public class Arnica extends Vivace {
    public Arnica(Color c,int nombre) {
        super(c,nombre);
    }
    public void positionGrille(int cellX,int cellY,Graphics g, Espece espece){
        g.setColor(espece.getCouleur());
        g.fillOval(cellX +20, cellY+90 , espece.getNombre()*5, espece.getNombre()*5);
    }
}