package fr.umontpellier.iut.trains;

public enum Effet {
    pasDeFerraille,
    gagne2argentCarteRailJouee,
    aucunSurcoutSurAdversaire,
    aucunSurcoutSurRiviere,
    aucunSurcoutSurMontagne,
    aucunSurcoutSurVille,
    aucunSurcout,
    cartesAchetees_auDeck;

    @Override
    public String toString() {
        String str = "";
        switch (this) {
            case pasDeFerraille -> str = "Pas de Ferraille"; //implémenté
            case gagne2argentCarteRailJouee -> str = "Gagne 2 argent quand carte Rail jouée"; //implémenté
            case aucunSurcoutSurAdversaire -> str = "Aucun surcout chez les Adversaires"; //implémenté
            case aucunSurcoutSurRiviere -> str = "Aucun surcout sur les Rivières"; //implémenté
            case aucunSurcoutSurMontagne -> str = "Aucun surcout dans les Montagnes"; //implémenté
            case aucunSurcoutSurVille -> str = "Aucun surcout dans les Villes"; //implémenté
            case aucunSurcout -> str = "Aucun surcout pour pauser des Rails"; //i m p lé men -pas trop en fait-
            case cartesAchetees_auDeck -> str = "Cartes achetées peuvent aller sur le deck";
        };
        return str;
    }
}
