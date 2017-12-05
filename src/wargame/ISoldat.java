package wargame;

public interface ISoldat {
    public static enum TypesH {
        HUMAIN(40, 3, 10, 10), NAIN(80, 1, 20, 0), ELF(70, 5, 2, 6), HOBBIT(20, 4, 6, 15),MAGE(30,4,7,20);
        private int POINTS_DE_VIE;
        private final int PORTEE_VISUELLE;
        private final int PUISSANCE;
        private final int TIR;

        TypesH(int points, int portee, int puissance, int tir) {
            POINTS_DE_VIE = points;
            PORTEE_VISUELLE = portee;
            PUISSANCE = puissance;
            TIR = tir;
        }

        public int getPoints() {
            return POINTS_DE_VIE;
        }

        public void setPoints(int Points) {
            POINTS_DE_VIE = Points;
        }

        public int getPortee() {
            return PORTEE_VISUELLE;
        }

        public int getPuissance() {
            return PUISSANCE;
        }

        public int getTir() {
            return TIR;
        }

        public static TypesH getTypeHAlea() {
            return values()[(int) (Math.random() * values().length)];
        }
    }

    public static enum TypesM {
        ORC(100, 1, 10, 0), TROLL(40, 2, 20, 10), GOBELIN(20, 4, 5, 20),MORTVIVANT(80,1,10,0);
        private int POINTS_DE_VIE;
        private final int PORTEE_VISUELLE;
        private final int PUISSANCE;
        private final int TIR;

        TypesM(int points, int portee, int puissance, int tir) {
            POINTS_DE_VIE = points;
            PORTEE_VISUELLE = portee;
            PUISSANCE = puissance;
            TIR = tir;
        }

        public int getPoints() {
            return POINTS_DE_VIE;
        }

        public void setPoints(int Points) {
            POINTS_DE_VIE = Points;
        }

        public int getPortee() {
            return PORTEE_VISUELLE;
        }

        public int getPuissance() {
            return PUISSANCE;
        }

        public int getTir() {
            return TIR;
        }

        public static TypesM getTypeMAlea() {
            return values()[(int) (Math.random() * values().length)];
        }
    }



    int getPortee();

    int getPuissance();

    int getTir();

    void combat(Soldat soldat);

    void seDeplace(Position newPos);
}