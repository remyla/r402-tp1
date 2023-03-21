import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TP1Tests {

    // dans JUnit 5, un test est simplement une méthode de type void, marquée avec le tag @Test
    // elle peut avoir n'importe quel nom
    @Test
    void simpleTest() {
        // pour être utile, un test doit comporter au moins une assertion qui vérifie une propriété
        String s = "st" + "tring";
        // cette assertion vérifie que le deuxième argument est égal au premier (le résultat attendu)
        assertEquals("string", s);
    }

    @Test
    public void testOverArrays() {
        int[] a = {3, 2, 1};
        int[] expected = {1, 2, 3};

        Arrays.sort(a);
        // cette assertion permet de tester que deux tableaux ont le même contenu
        assertArrayEquals(expected, a);
    }

}
