import java.util.*;

public class ISBN {
    // Atributos
    private int isbn13;

    // Constructor
    public ISBN(int isbn13) {
        this.isbn13 = isbn13;
    }

    // Aqui hay que poner un algoritmo que verifique si el ISBN es correcto :
    // https://en.wikipedia.org/wiki/ISBN#ISBN-13_check_digit_calculation
    // s = 9×1 + 7×3 + 8×1 + 0×3 + 3×1 + 0×3 + 6×1 + 4×3 + 0×1 + 6×3 + 1×1 + 5×3
    // = 9 + 21 + 8 + 0 + 3 + 0 + 6 + 12 + 0 + 18 + 1 + 15
    // = 93
    // 93 / 10 = 9 remainder 3
    // 10 – 3 = 7
}