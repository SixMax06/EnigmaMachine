package sixmax06.javafx.enigmamachine;

import java.util.TreeMap;

public class Reflector {
    private TreeMap<Character, Character> reflectedAlphabet;

    public Reflector(TreeMap<Character, Character> alphabet) {
        this.reflectedAlphabet = alphabet;
    }

    public Reflector(String alphabet) {
        this.reflectedAlphabet = new TreeMap<>();

        for (int i = 0; i < 26; i++)
            this.reflectedAlphabet.put((char) (i + 'A'), alphabet.charAt(i));
    }

    public TreeMap<Character, Character> getReflectedAlphabet() {
        return reflectedAlphabet;
    }
}
