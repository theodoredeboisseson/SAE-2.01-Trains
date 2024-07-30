package fr.umontpellier.iut.trains.cartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Représentation d'une liste de cartes
 */
public class ListeDeCartes extends ArrayList<Carte> {
    public ListeDeCartes() {
        super();
    }

    public ListeDeCartes(List<Carte> l) {
        super(l);
    }

    /**
     * Mélange la liste
     */
    public void melanger() {
        Collections.shuffle(this);
    }

    /**
     * Retire une carte de la liste dont le nom est égal à l'argument passé
     *
     * @param nomCarte le nom de la carte à retirer
     * @return la carte retirée si elle a été trouvée, {@code null} sinon
     */
    public Carte retirer(String nomCarte) {
        for (Carte c : this)
            if (c.getNom().equals(nomCarte)) {
                remove(c);
                return c;
            }
        return null;
    }

    /**
     * Renvoie liste de cartes respectant les critères donnés en paramètre
     *
     * @param type "null" pour ignorer
     * @param valeur mettre "-1" pour ignorer
     * @param cout mettre "-1" pour ignorer
     * @return la sous-liste de cartes triées, null si la liste est vide.
     */
    public ListeDeCartes selection(TypeCarte type, int valeur, int cout){
        ListeDeCartes sousListe = new ListeDeCartes();
        for(Carte c: this) {
            boolean typeCheck = (type == null || c.getType() == type);
            boolean valeurCheck = (valeur == -1 || c.getValeur() <= valeur);
            boolean coutCheck = (cout == -1 || c.getCout() <= cout);

            if (typeCheck && valeurCheck && coutCheck)
                sousListe.add(c);
        }

        return sousListe;
    }

    /**
     * Renvoie this sans doublons
     */
    public ListeDeCartes listeDistincte(){
        ListeDeCartes listeDistince = new ListeDeCartes();
        for(Carte c: this)
            if(!listeDistince.contains(c))
                listeDistince.add(c);

        return listeDistince;
    }

    /**
     * Renvoie une carte de la liste dont le nom est égal à l'argument (la carte
     * n'est pas retirée de la liste)
     *
     * @param nomCarte le nom de la carte à chercher
     * @return une carte de la liste ayant le nom cherché si elle existe,
     *         {@code null} sinon
     */
    public Carte getCarte(String nomCarte) {
        for (Carte c : this)
            if (c.getNom().equals(nomCarte))
                return c;
        return null;
    }

    /**
     * @return Les noms des cartes dans la liste
     */
    public List<String> getNoms(){
        return this.stream()
                .map(Carte::getNom)
                .collect(Collectors.toList());
    }

    public int getPointsVictoire(){
        ListeDeCartes cartesVictoires = this.selection(TypeCarte.Victoire,-1,-1);
        int points = 0;

        for (Carte c : cartesVictoires)
            points += c.getValeur();

        return points;
    }

    /**
     * Renvoie le nombre de cartes dans la liste ayant le nom passé en argument
     *
     * @param nomCarte le nom des cartes à compter
     * @return un entier indiquant le nombre de cartes ayant le nom recherché
     */
    public int count(String nomCarte) {
        int total = 0;
        for (Carte c : this)
            if (c.getNom().equals(nomCarte))
                total += 1;
        return total;
    }


    public boolean contains(String nomCarte) {
        for (Carte c : this)
            if(c.getNom().equals(nomCarte))
                return true;
        return false;
    }

    /**
     * Représentation de la liste sous forme d'une chaîne de caractères
     * <p>
     * Cette fonction renvoie une chaîne de caractères constituée des noms des
     * cartes de la liste séparées par ", ".
     */
    @Override
    public String toString() {
        return this.stream()
                .map(Carte::toString)
                .collect(Collectors.joining(", "));
    }

    /**
     * @return une représentation de la liste de cartes sous la forme d'une liste de
     *         noms (qui sera converti en JSON pour l'envoyer à
     *         l'interface graphique)
     */
    public Object dataMap() {
        return this.stream().map(Carte::getNom).toList();
    }
}
