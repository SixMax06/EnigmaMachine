package sixmax06.javafx.enigmamachine;

import java.util.ArrayList;
import java.util.TreeMap;

public class Rotor {
    private int rotation;
    private ArrayList<Integer> alphabet;

    public Rotor(int rotation, String alphabet) {
        this.rotation = rotation % 26;
        this.alphabet = new ArrayList<>();

        for (int i = 0; i < 26; i++)
            this.alphabet.add(alphabet.charAt(i) - 'A');
    }

    public void rotate(int index) {
        index %= 26;

        for (int i = 0; i < index; i++) {
            this.alphabet.addFirst(this.alphabet.getLast());
            this.alphabet.removeLast();
        }

        rotation = (rotation + index) % 26;
    }

    public int getRotation() {
        return this.rotation;
    }

    public ArrayList<Integer> getAlphabet() {
        return this.alphabet;
    }

    public String toString() {
        return this.alphabet.toString();
    }
}
