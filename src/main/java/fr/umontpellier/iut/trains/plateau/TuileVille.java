package fr.umontpellier.iut.trains.plateau;

import fr.umontpellier.iut.trains.Joueur;

/**
 * Classe représentant une tuile ville (où l'on peut poser des gares)
 */
public class TuileVille extends Tuile {
    /**
     * Nombre maximum de gares que l'on peut poser sur la tuile
     */
    private final int nbGaresMax;
    /**
     * Nombre de gares posées sur la tuile
     */
    private int nbGaresPosees;

    public TuileVille(int taille) {
        super();
        this.nbGaresMax = taille;
        this.nbGaresPosees = 0;
    }

    @Override
    public int getNbGaresMax() {
        return nbGaresMax;
    }

    @Override
    public int getNbGares() {
        return nbGaresPosees;
    }

    public void ajouterGare(Joueur joueur){
        if(nbGaresPosees<nbGaresMax) nbGaresPosees++;
    }
}
