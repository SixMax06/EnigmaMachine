package sixmax06.javafx.enigmamachine;

import java.util.TreeMap;

public class Reflector {
    private final TreeMap <Character, Character> reflectedAlphabet;

    public Reflector(String alphabet) {
        this.reflectedAlphabet = new TreeMap<>();

        for (int i = 0; i < 26; i++)
            this.reflectedAlphabet.put((char) (i + 'A'), alphabet.charAt(i));
    }

    public char getReflectedCharacter(char key) {
        return this.reflectedAlphabet.get(key);
    }

    public TreeMap<Character, Character> getReflectedAlphabet() {
        return this.reflectedAlphabet;
    }

    public String toString() {
        return this.reflectedAlphabet.toString();
    }
}
