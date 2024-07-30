package fr.umontpellier.iut.trains;

import java.util.Objects;

/**
 * La classe {@code Bouton} permet d'afficher un bouton dans l'interface
 * graphique pour que l'utilisateur puisse envoyer des inputs au programme.
 * <p>
 * Les boutons sont utilisés par la méthode {@code Joueur.choisir}
 */
public record Bouton(

        /** Le texte affiché sur le bouton */
        String label,

        /** La valeur envoyée au jeu lorsque le bouton est cliqué */
        String valeur) {

    public Bouton(String valeur) {
        this(valeur, valeur);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bouton bouton = (Bouton) o;
        return Objects.equals(valeur, bouton.valeur);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(valeur);
    }

    @Override
    public String toString() {
        if (label.equals(valeur)) {
            return valeur;
        } else {
            return label + " (" + valeur + ")";
        }
    }
}