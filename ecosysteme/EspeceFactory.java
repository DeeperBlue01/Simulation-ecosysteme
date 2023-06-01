import java.awt.*;

public abstract class EspeceFactory {

    public static Espece getInstance(EspeceDisponible especeDisponible, int nombre) {
        return switch (especeDisponible) {
            case capucine -> new Capucine(new Color(89, 255, 97), nombre);
            case arnica -> new Arnica(new Color(66, 203, 21), nombre);
            case sapin -> new Sapin(new Color(74, 105, 41), nombre);
            case chene -> new Chene(new Color(44, 114, 27), nombre);
            case pigeon -> new Pigeon(new Color(112, 0, 204), nombre);
            case perroquet -> new Perroquet(new Color(69, 24, 159), nombre);
            case lion -> new Lion(new Color(105, 28, 37), nombre);
            case tigre -> new Tigre(new Color(187, 30, 36), nombre);
            case zebre -> new Zebre(new Color(204, 102, 0), nombre);
            case girafe -> new Girafe(new Color(250, 138, 46), nombre);
            case fourmi -> new Fourmi(new Color(70, 70, 70), nombre);
            case mouche -> new Mouche(new Color(0, 0, 0), nombre);
            case guepe -> new Guepe(new Color(0, 0, 0), nombre);
            default -> throw new IllegalArgumentException("Espece inconnue : " + especeDisponible);
        };
    }


}