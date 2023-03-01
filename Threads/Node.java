package Threads;

public class Node {
    private Integer cota;
    private String CNP;

    public Node(Integer cota, String CNP)
    {
        this.cota = cota;
        this.CNP = CNP;
    }

    public String toString_1() {
        return "Cartea cu cota " + cota + " a fost IMPRUMUTATA de persoana cu CNP-ul " + CNP;
    }

    public String toString_2()
    {
        return "Cartea cu cota " + cota + " a fost RETURNATA de persoana cu CNP_ul " + CNP;
    }
}
