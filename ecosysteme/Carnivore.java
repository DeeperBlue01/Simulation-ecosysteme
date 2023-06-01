import java.awt.*;

public abstract class Carnivore extends Mamifere {
    public Carnivore(Color c,int nombre) {
        super(c,nombre);
    }
    public abstract void positionGrille(int cellX,int cellY,Graphics g, Espece espece);

}