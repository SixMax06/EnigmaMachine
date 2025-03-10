package sixmax06.javafx.enigmamachine;

import java.util.TreeMap;

public class Rotor {
    private int position, rotation;
    private TreeMap<Character, Character> alphabet;

    public Rotor(int position, int rotation, TreeMap<Character, Character> alphabet) {
        this.position = position;
        this.rotation = rotation % 26;
        this.alphabet = alphabet;

        this.nRotate(rotation);
    }

    public Rotor(int position, int rotation, String alphabet) {
        this.position = position;
        this.rotation = rotation % 26;
        this.alphabet = new TreeMap<>();

        for (int i = 0; i < 26; i++)
            this.alphabet.put((char) (i + 'A'), alphabet.charAt(i));

        this.nRotate(rotation);
    }

    public void rotate() {
        TreeMap<Character, Character> newAlphabet = new TreeMap<>();
        int index = 1;

        for (int i = 0; i < alphabet.size(); i++) {
            index = (index + 1) % alphabet.size();
            newAlphabet.put((char) (index + 'A'), alphabet.get((char) (i + 'A')));
        }

        this.alphabet = newAlphabet;
    }

    public void nRotate(int rotations) {
        TreeMap<Character, Character> newAlphabet = new TreeMap<>();
        int index = rotations % 26;

        for (int i = 0; i < alphabet.size(); i++) {
            index = (index + 1) % alphabet.size();
            newAlphabet.put((char) (index + 'A'), alphabet.get((char) (i + 'A')));
        }

        this.alphabet = newAlphabet;
    }

    public int getPosition() {
        return position;
    }

    public int getRotation() {
        return rotation;
    }

    public TreeMap<Character, Character> getAlphabet() {
        return alphabet;
    }

    public static void main(String[] args) {
        TreeMap<Character, Character> alphabet = new TreeMap<>();
        alphabet.put('A', '1');
        alphabet.put('B', '2');
        alphabet.put('C', '3');
        System.out.println(alphabet + "\n");

        TreeMap<Character, Character> alphabet1 = new TreeMap<>();

        int rotazione = 1;
        int index = rotazione % alphabet.size();

        for (int i = 0; i < alphabet.size(); i++) {
            index = (index + 1) % alphabet.size();
            alphabet1.put((char) (index + 'A'), alphabet.get((char) (i + 'A')));
        }
        System.out.println(alphabet1);
    }
}
