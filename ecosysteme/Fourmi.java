import java.awt.*;

public class Fourmi extends Insecte {
    public Fourmi(Color c,int nombre) {
        super(c,nombre);
    }

    public void positionGrille(int cellX,int cellY,Graphics g, Espece espece){
        g.setColor(espece.getCouleur());
        g.fillOval(cellX +20, cellY+5 , espece.getNombre(), espece.getNombre());
    }


}