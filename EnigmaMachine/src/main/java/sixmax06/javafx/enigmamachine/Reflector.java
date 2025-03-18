package sixmax06.javafx.enigmamachine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeMap;

public class Reflector {
    private TreeMap <Character, Character> reflectedAlphabet;

    public Reflector(String alphabet) {
        this.reflectedAlphabet = new TreeMap<>();

        for (int i = 0; i < 26; i++)
            this.reflectedAlphabet.put((char) (i + 'A'), alphabet.charAt(i));
    }

    public TreeMap<Character, Character> getReflectedAlphabet() {
        return this.reflectedAlphabet;
    }
}
