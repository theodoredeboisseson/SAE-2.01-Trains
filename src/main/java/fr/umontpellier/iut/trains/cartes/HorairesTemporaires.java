package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class HorairesTemporaires extends Carte {
    public HorairesTemporaires() {
        super("Horaires temporaires", TypeCarte.Action,0,5);
    }

    @Override
    public void jouer(Joueur joueur) {
        ListeDeCartes devoilees = new ListeDeCartes();

        boolean piocheNonVide = true;
        while ( devoilees.selection(TypeCarte.Train,-1,-1).size() < 2 && piocheNonVide){
            Carte cartePiochee = joueur.piocher();
            if(cartePiochee == null){
                piocheNonVide = false;
                joueur.log("La pioche est vide, l'effet de la carte est partiellement realisé");
            }


            else{
                devoilees.add(cartePiochee);
                joueur.log("Dévoile "+cartePiochee.getNom());
                //Jeu.attendre(1);
            }
        }

        ListeDeCartes trains = devoilees.selection(TypeCarte.Train,-1,-1);

        devoilees.removeAll(trains);
        joueur.getMain().addAll(trains);

        joueur.getDefausse().addAll(devoilees);
    }
}
