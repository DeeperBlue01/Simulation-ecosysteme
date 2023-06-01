import java.awt.*;

public abstract class Oiseau extends Animal {
    public Oiseau(Color c,int nombre) {
        super(c,nombre);
    }
    public abstract void positionGrille(int cellX,int cellY,Graphics g, Espece espece);
}