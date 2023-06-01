import java.awt.*;
import java.util.Random;

public abstract class Animal implements Espece {
    protected int nombre = 0;
    protected Color c;

    Animal(Color c, int nombre) {
        setNombre(nombre);
        this.c = c;
    }

    @Override
    public int getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(int nombre) {
        if (nombre < 0) {
            throw new RuntimeException("Vous avez mis comme nombre " + nombre + " or il faudra mettre un nombre strictement positif");
        }
        this.nombre = nombre;
    }

    @Override
    public Color getCouleur() {
        return c;
    }

    public void setCouleur(Color c) {
        this.c = c;
    }

    @Override
    //Je force tous les fils à implémenter cette fonction car c'est une fonction critique dans le programme
    public abstract void positionGrille(int cellX, int cellY, Graphics g, Espece espece);

    @Override
    public void manger(Zone zone) {
        if (this.getClass().equals(Carnivore.class)) {
            for (Espece espece : zone.lEspeces) {
                if (espece.getClass().equals(Herbivore.class) && espece.getNombre() != 0) {
                    espece.setNombre(espece.getNombre() - 1);
                    return;
                }
            }
            //Abscence de nouriture pour le carnivore
            setNombre(this.nombre - 1);
        } else if (this.getClass().equals(Herbivore.class)) {
            for (Espece espece : zone.lEspeces) {
                if (espece.getClass().equals(Arbre.class) && espece.getNombre() != 0) {
                    espece.setNombre(espece.getNombre() - 1);
                    return;
                }
            }
            //Très peu de nouriture => extinction
            setNombre(0);
        } else if (this.getClass().equals(Oiseau.class)) {
            for (Espece espece : zone.lEspeces) {
                if (espece.getClass().equals(Insecte.class) && espece.getNombre() != 0) {
                    espece.setNombre(espece.getNombre() - 1);
                    return;
                }
            }
            //Extinction
            setNombre(0);
        } else if (this.getClass().equals(Insecte.class) && this.nombre >= 20) {//Le cas quand il y a beaucoup d'insecte toutes les especes vont mourrir
            for (Espece espece : zone.lEspeces) {
                if (!espece.getClass().equals(Insecte.class))
                    espece.setNombre(0);
            }
            zone.setTypeCase(TypeCase.desert);
        }
    }

    @Override
    public void boire(Zone zone) {
        for (Espece espece : zone.lEspeces) {
            //Pas assez d'eau
            if (zone.getFourchette() < 1) {
                espece.setNombre(0);
            } else {
                zone.setFourchette(zone.getFourchette() - 1);
            }
        }
    }

    @Override
    public void reproduction() {
        if (this.nombre!=0 && this.nombre>=2){
            this.nombre++;
        }
    }

    //Cette fonction gère tous les problèmes d'erreur de ségmentation
    @Override
    public void deplacer(Terrain terrain, Zone zone, int i, int j) {
        Random random = new Random();
        try {
            if (i == 0) {
                if (j == 0) {
                    int[] indexAleatoire = {i + 1, j + 1};
                    int index = random.nextInt(indexAleatoire.length);
                    if (getNombre()-1>=0)
                        this.setNombre(getNombre() - 1);
                    else
                        return;
                    if (indexAleatoire[index] == i + 1) {
                        terrain.getZones(i + 1, j).ajouterDeplacementEspece(this);
                    } else {
                        terrain.getZones(i, j + 1).ajouterDeplacementEspece(this);
                    }
                } else if (j == terrain.getNbCasesH() - 1) {
                    int[] indexAleatoire ={j-1, i + 1};
                    int index = random.nextInt(indexAleatoire.length);
                    if (getNombre()-1>=0)
                        this.setNombre(getNombre() - 1);
                    else
                        return;
                    if (indexAleatoire[index] == i + 1) {
                        terrain.getZones(i + 1, j).ajouterDeplacementEspece(this);
                    } else {
                        terrain.getZones(i, j-1).ajouterDeplacementEspece(this);
                    }
                }
            }
            else if (i== terrain.getNbCasesL()-1){
                if (j == 0) {
                    int[] indexAleatoire = {i -1, j + 1};
                    int index = random.nextInt(indexAleatoire.length);
                    if (getNombre()-1>=0)
                        this.setNombre(getNombre() - 1);
                    else
                        return;
                    if (indexAleatoire[index] == i - 1) {
                        terrain.getZones(i - 1, j).ajouterDeplacementEspece(this);
                    } else {
                        terrain.getZones(i, j + 1).ajouterDeplacementEspece(this);
                    }
                } else if (j == terrain.getNbCasesH() - 1) {
                    int[] indexAleatoire = {j - 1, i - 1};
                    int index = random.nextInt(indexAleatoire.length);
                    if (getNombre()-1>=0)
                        this.setNombre(getNombre() - 1);
                    else
                        return;
                    if (indexAleatoire[index] == i - 1) {
                        terrain.getZones(i - 1, j).ajouterDeplacementEspece(this);
                    } else {
                        terrain.getZones(i, j - 1).ajouterDeplacementEspece(this);
                    }
                }
            }
            else if (j==0){
                int[] indexAleatoire = {i - 1, j + 1 , i + 1};
                int index = random.nextInt(indexAleatoire.length);
                if (getNombre()-1>=0)
                    this.setNombre(getNombre() - 1);
                else
                    return;
                if (indexAleatoire[index] == i - 1) {
                    terrain.getZones(i - 1, j).ajouterDeplacementEspece(this);
                }else if(indexAleatoire[index] == j + 1){
                    terrain.getZones(i , j + 1).ajouterDeplacementEspece(this);
                }
                else {
                    terrain.getZones(i + 1, j).ajouterDeplacementEspece(this);
                }
            }
            else if(j==terrain.getNbCasesH() - 1){
                int[] indexAleatoire = {i - 1, j - 1, i + 1};
                int index = random.nextInt(indexAleatoire.length);
                if (getNombre()-1>=0)
                    this.setNombre(getNombre() - 1);
                else
                    return;
                if (indexAleatoire[index] == i - 1) {
                    terrain.getZones(i - 1, j).ajouterDeplacementEspece(this);
                }else if(indexAleatoire[index] == j - 1){
                    terrain.getZones(i , j - 1).ajouterDeplacementEspece(this);
                }
                else {
                    terrain.getZones(i + 1, j).ajouterDeplacementEspece(this);
                }
            }
            else {
                int[] indexAleatoire = {i - 1, i + 1, j + 1,j - 1};
                int index = random.nextInt(indexAleatoire.length);
                if (getNombre()-1>=0)
                    this.setNombre(getNombre() - 1);
                else
                    return;
                if (indexAleatoire[index] == i - 1) {
                    terrain.getZones(i - 1, j).ajouterDeplacementEspece(this);
                }else if(indexAleatoire[index] == i + 1){
                    terrain.getZones(i =1 , j).ajouterDeplacementEspece(this);
                }
                else if (indexAleatoire[index] == j + 1){
                    terrain.getZones(i, j + 1).ajouterDeplacementEspece(this);
                }
                else{
                    terrain.getZones(i, j - 1).ajouterDeplacementEspece(this);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("Les dimensions de votre terrain cause un problème merci de choisir un terrain au minimum 2x2");
        }
    }
}
