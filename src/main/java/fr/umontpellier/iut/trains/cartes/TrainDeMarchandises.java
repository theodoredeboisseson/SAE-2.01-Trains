package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class TrainDeMarchandises extends Carte {
    public TrainDeMarchandises() {
        super("Train de marchandises", TypeCarte.Train,1,4);
    }

    @Override
    public void jouer(Joueur joueur) {
        genererValeurPour(joueur);
        ListeDeCartes main = joueur.getMain();

        if(main.count("Ferraille") == 0){
            joueur.log("Vous ne possédez aucune carte Ferraille dans votre main, cette carte n'auras aucun effet.");
            return;
        }
        List<String> choix = List.of("Ferraille");
        String input = "null";

        while(!input.isEmpty()){
            input = joueur.choisir(
                    "Cliquez sur des cartes ferrailles de votre main pour les remettre dans la réserve",
                    choix,
                    null,
                    true
            );

            if(input.equals("Ferraille")){
                joueur.remettreSurLaPile("Ferraille");
                joueur.incrementerArgent(1);
            }
        }
    }
}
