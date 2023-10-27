package pwo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public class LucasGenerator {

    private int lastIndex = 0;
    private BigDecimal current = new BigDecimal(2); // Pierwszy element ciągu Lucas
    private BigDecimal l_1 = new BigDecimal(1); // Drugi element ciągu Lucas
    private BigDecimal l_2 = new BigDecimal(2); // Pierwszy element ciągu Lucas

    public void reset() {
        lastIndex = 0;
        current = new BigDecimal(2);
        l_1 = new BigDecimal(1);
        l_2 = new BigDecimal(2);
    }

    public BigDecimal nextTerm() {
        if (lastIndex > 1) {
            current = l_1.add(l_2);
            l_2 = l_1;
            l_1 = current;
        } else if (lastIndex == 1) current = new BigDecimal(1);
        else current = new BigDecimal(2);
        lastIndex++;
        return current;
    }

    public BigDecimal getTerm(int i) {
        if (i < 0) throw new IllegalArgumentException();
        if (i < lastIndex) reset();
        while (lastIndex <= i) nextTerm();
        return current;
    }

    public static void main(String[] args) {
        LucasGenerator lucasGenerator = new LucasGenerator();
        int numberOfTerms = 100;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("lucseq100.txt"))) {
            for (int i = 0; i < numberOfTerms; i++) {
                BigDecimal term = lucasGenerator.getTerm(i);
                writer.write(term.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
