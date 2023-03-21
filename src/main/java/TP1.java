import ecommerce.Address;
import ecommerce.Client;
import ecommerce.Order;
import ecommerce.Product;

import java.math.BigDecimal;
import java.util.Arrays;

public class TP1 {

    /* Exercice 1: défauts, erreurs et défaillances
     *
     * Pour chacune des méthodes indexOfLastOccurrence, average, et countOddElements:
     * 1) déterminez le défaut logiciel dans la méthode (si vous ne trouvez pas le défaut simplement en inspectant le
     *    code, écrivez des tests pour vous aider)
     * 2) écrivez un test qui n'exécute pas le défaut
     * 3) écrivez un test qui exécute le défaut, mais ne provoque pas de défaillance
     * 4) écrivez un test qui provoque une défaillance
     *
     */

    /**
     * Retourne la position de la dernière occurrence de la valeur x dans le tableau a, ou -1 si la valeur n'est pas
     * présente dans le tableau.
     *
     * @param a le tableau où chercher la valeur
     * @param x la valeur recherchée
     * @return la plus grande position <code>i</code> telle que <code>a[i] == x</code>, ou -1 si aucune position ne
     * contient la valeur recherchée
     * @throws NullPointerException si <code>a == null</code>
     */
    public static int indexOfLastOccurrence(int[] a, int x) {
        for (int i = a.length - 1; i > 0; i--) {
            if (a[i] == x) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retourne la moyenne des valeurs stockées dans le tableau.
     *
     * @param a le tableau contenant les valeurs
     * @return la moyenne des valeurs stockées, ou 0.0 si le tableau est vide
     * @throws NullPointerException si <code>a == null</code>
     */
    public static double average(int[] a) {
        if (a.length == 0) {
            return 0.0;
        }
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum / a.length;
    }

    /**
     * Retourne le nombre d'entiers impairs contenus dans le tableau.
     *
     * @param a le tableau contenant les valeurs
     * @return le nombre d'entier impairs contenus dans le tableau
     * @throws NullPointerException si <code>a == null</code>
     */
    public static int countOddElements(int[] a) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] % 2 == 1) {
                res++;
            }
        }
        return res;
    }

    /* Exercice 2: utiliser le debugger pour analyser les états d'exécution
     *
     * En utilisant le debugger (sans modifier le code source de la méthode f), répondez aux questions suivantes:
     *
     * 1) Si on exécute la méthode f avec la valeur n = 18, quelles sont les valeurs successives prise par la
     *    variable i?
     * 2) Si on exécute la méthode f avec la valeur n = 31, le programme atteindra un état où, au début de la
     *    boucle, la variable i vaut 7. Dans cet état, quelle est la valeur de la variable j?
     * 3) Si la méthode f passe par un état où i = 20 et j = 3 (en début de boucle), quelle valeur sera retournée à la
     *    fin de l'exécution?
     */

    public static int f(int n) {
        int i = n;
        int j = 0;
        while (i > 1) {
            if (i % 3 == 0) {
                i /= 3;
                j *= 2;
            } else if (i % 2 == 0) {
                i /= 2;
                j += 1;
            } else {
                i = 5 * i + 1;
                j += 2;
            }
        }
        return j;
    }

    /* Exercice 3: utiliser le debugger pour analyser une application complexe
     *
     * Exécutez la méthode ecommerceUsecase et répondez aux questions suivantes en utilisant le debugger:
     *
     * 1) Donnez les valeurs des différents champs de l'objet (Client) c immédiatement après sa création. Si certains
     *    de ces champs sont des objets, listez aussi les valeurs de leurs champs.
     * 2) Donnez sous forme d'arbre (arbre d'appel) toutes les méthodes qui sont exécutées par l'appel à add(p).
     */

    public static void ecommerceUsecase() {
        Client c = new Client("Jean", "Dupont");
        Product p = new Product("Ordinateur", BigDecimal.valueOf(1000));
        c.getShoppingCart().add(p);
        Address a = new Address("Place du marché", "Nantes", 44000);
        Order o = c.generateOrder(a);
    }

    /* Exercice 4: utiliser le debugger pour réparer des défauts logiciels
     *
     * 1) Écrivez des tests pour la méthode sort, en essayant de choisir des entrées adaptées pour découvrir des
     *    défauts logiciels.
     * 2) Utilisez le debugger pour trouver les défauts logiciels dans les méthode sort, sortSegment et
     *    mergeSortedArrays, puis réparez-les. Pour chaque défaut trouvé, décrivez le problème, indiquez la
     *    modification effectuée pour le résoudre, et listez les tests dont le statut a changé suite à la modification.
     * 3) Pouvez-vous déterminer quand tous les défauts ont été trouvés? Expliquez pourquoi, et imaginez des moyens
     *    pour mesurer la confiance qu'on peut accorder aux tests.
     */

    /**
     * Fusionne deux segments du tableau.
     *
     * Cette méthode requiert que les segments a[start .. mid] et a[mid .. end] soient initialement triés en ordre
     * croissant. Après application, le segment a[start .. end] contient les éléments des deux segments, triés en ordre
     * croissant. Si les segments ne sont pas triés initialement, le comportement n'est pas spécifié.
     * @param a le tableau avec les segments à fusionner
     * @param start l'indice du début du premier segment (inclus)
     * @param mid l'indice de la fin du second segment (non-inclus) et du début du second segment (inclus)
     * @param end l'indice de la fin du second segment (non-inclus)
     * @throws NullPointerException si a est null
     * @throws IllegalArgumentException si mid < start ou mid > end
     * @throws ArrayIndexOutOfBoundsException si start < 0 ou end > a.length
     */
    private static void mergeSortedSubarrays(int[] a, int start, int mid, int end) {
        int [] a1 = Arrays.copyOfRange(a, start, mid);
        int [] a2 = Arrays.copyOfRange(a, mid, end);
        int i = 0;
        int j = 0;
        for (int k = start; k < end; k++) {
            if (i >= a1.length) {
                a[k] = a2[j++];
            } else if (j >= a2.length) {
                a[k] = a1[i++];
            } else if (a1[i] < a2[j]) {
                a[k] = a1[i++];
            } else if (a2[j] < a1[i]) {
                a[k] = a2[j++];
            }
        }
    }

    /**
     * Trie un segment du tableau par ordre croissant.
     * @param a le tableau à trier
     * @param start l'indice du début du segment à trier, inclus
     * @param end l'indice de la fin du segment à trier, non-inclus
     * @throws NullPointerException si a est null
     * @throws ArrayIndexOutOfBoundsException si start < 0 ou end > a.length
     */
    private static void sortSegment(int[] a, int start, int end) {
        if (end >= start) {
            int mid = start + (end - start) / 2;
            sortSegment(a, start, mid);
            sortSegment(a, mid + 1, end);
            if (a[mid - 1] > a[mid]) {
                mergeSortedSubarrays(a, start, mid, end);
            }
        }
    }

    /**
     * Trie le tableau d'entier par ordre croissant.
     * @param a le tableau à trier
     * @throws NullPointerException si a est null
     */
    public static void sort(int[] a) {
        sortSegment(a, 0, a.length);
    }

    public static void main(String[] args) {
        // le debugger peut être utilisé aussi bien sur un programme lancé de manière standard (main) que sur des tests
        ecommerceUsecase();
    }
}
