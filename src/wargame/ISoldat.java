package wargame;

import java.awt.*;

public interface ISoldat {
    public static enum TypesH {
        HUMAIN(40, 3, 10, 10, "images/homme.png"), NAIN(80, 1, 20, 0, "images/nain.png"), ELF(70, 5, 2, 6, "images/elf.png"), HOBBIT(20, 4, 6, 15, "images/hobbit.png"), MAGE(30, 4, 7, 20, "images/mage.png");
        private int POINTS_DE_VIE;
        private final int PORTEE_VISUELLE;
        private final int PUISSANCE;
        private final int TIR;
        private final String ICONE;

        TypesH(int points, int portee, int puissance, int tir, String icone) {
            POINTS_DE_VIE = points;
            PORTEE_VISUELLE = portee;
            PUISSANCE = puissance;
            TIR = tir;
            ICONE = icone;

        }

        public int getPoints() {
            return POINTS_DE_VIE;
        }

        public void setPoints(int Points) {
            POINTS_DE_VIE = Points;
        }

        public String getICONE() {
            return ICONE;
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
        ORC(100, 1, 10, 0, "images/orc.png"), TROLL(40, 2, 20, 10, "images/troll.png"), GOBELIN(20, 4, 5, 20, "images/goblin_e.png"), MORTVIVANT(80, 1, 10, 0, "images/mortv.png");
        private int POINTS_DE_VIE;
        private final int PORTEE_VISUELLE;
        private final int PUISSANCE;
        private final int TIR;
        private final String ICONE;

        TypesM(int points, int portee, int puissance, int tir, String icone) {
            POINTS_DE_VIE = points;
            PORTEE_VISUELLE = portee;
            PUISSANCE = puissance;
            TIR = tir;
            ICONE = icone;
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

        public String getICONE() {
            return ICONE;
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