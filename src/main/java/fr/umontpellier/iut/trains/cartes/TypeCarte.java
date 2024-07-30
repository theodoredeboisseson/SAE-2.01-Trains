package fr.umontpellier.iut.trains.cartes;

public enum TypeCarte {
    Train,
    Rail,
    Gare,
    Action,
    Victoire,
    Ferraille;

    @Override
    public String toString() {
        String str = "";
        switch (this) {
            case Train -> str = "Train";
            case Rail -> str = "Rail";
            case Gare -> str = "Gare";
            case Action -> str = "Action";
            case Victoire -> str = "Victoire";
            case Ferraille -> str = "Ferraille";
        };
        return str;
    }
}
