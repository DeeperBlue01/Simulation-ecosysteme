import java.awt.*;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;


public class Zone {
    private float temperature;
    private float fourchette;
    public LinkedList<Espece> lEspeces;
    private TypeCase typeCase;
    private Color c;
    private EspeceFactory especeFactory;


    Zone(float temperature, float fourchette) {
        this.temperature = temperature;
        this.fourchette = fourchette;
        lEspeces = new LinkedList<Espece>();
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public Color getCouleur() {
        return c;
    }

    public void setCouleur(Color c) {
        this.c = c;
    }

    public void setTypeCase(TypeCase typeCase) {
        this.typeCase = typeCase;
    }

    public void addEspece(EspeceDisponible especeDisponible,int nombre) {
        Espece espece = EspeceFactory.getInstance(especeDisponible,1);

        for (Espece e :lEspeces){
            if (e.getClass().equals(espece.getClass())){
                throw new RuntimeException("Création d'une nouvelle instance de "+ especeDisponible+" dans la même zone");
            }
        }
        lEspeces.add(EspeceFactory.getInstance(especeDisponible,nombre));
    }
    public void ajouterDeplacementEspece(Espece espece){
        for (Espece e:lEspeces) {
            if (e.getClass().equals(espece.getClass())){
                e.setNombre(e.getNombre()+1);
                return;
            }
        }
        addEspece(Espece.getEspeceDisponible(espece),1);
    }

    public float getFourchette() {
        return fourchette;
    }

    //Cette fonction nous permet de libérer la mémoire si le nombre d'élèments d'espèces est 0
    public void cleanEspece(){
        for (int i=0;i<this.lEspeces.size();i++){
            if (this.lEspeces.get(i).getNombre()==0){
                this.lEspeces.remove(i);
                i--;
            }
        }
    }

    public void basculeZone(){
        if (typeCase.equals(TypeCase.desert)){
            // Aucun changement quand la zone est un désert
            return;
        }
        if (this.temperature>=45 || this.fourchette==0){
            this.setCouleur(Color.ORANGE);
            this.setTypeCase(TypeCase.desert);
            this.lEspeces.forEach(e->{
                e.setNombre(0);
            });
            this.cleanEspece();
            return;
        }
        if (typeCase.equals(TypeCase.foret)){
            AtomicInteger count= new AtomicInteger();
            lEspeces.forEach(e->
            {
                if (e.getClass().equals(Vegetal.class)) {
                    count.set(e.getNombre());
                }
            });
            if (count.get()<=0){
                this.setCouleur(Color.CYAN);
                this.setTypeCase(TypeCase.plaine);
                return;
            }
        }
        if (this.fourchette>=500){
            this.setCouleur(Color.CYAN);
            this.setTypeCase(TypeCase.plaine);
            return;
        }
        if (this.fourchette<=500){
            this.setCouleur(Color.GREEN);
            this.setTypeCase(TypeCase.foret);
        }

    }

    public void cleanDesert(){
        if (this.typeCase.equals(TypeCase.desert)){
            lEspeces.forEach(espece -> {
                espece.setNombre(0);
            });
        }
        cleanEspece();
    }
    public void setFourchette(float fourchette) {
        if (fourchette<0)
            throw new RuntimeException("Valeur de la fourchette négative");
        this.fourchette = fourchette;
    }

    public void invasionGuepe(){
        this.lEspeces.forEach(e->{
            if (e instanceof Guepe){
                if (e.getNombre()>=20){
                    this.typeCase=TypeCase.desert;
                    this.c=Color.ORANGE;
                    cleanDesert();
                }
            }
        });
    }
}