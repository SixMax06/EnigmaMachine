package sixmax06.javafx.enigmamachine;

import java.util.ArrayList;

public class Rotor {
    private int rotation;
    private char turnoverChar;
    private ArrayList<Integer> alphabet;

    public Rotor(int rotation, String alphabet, char turnoverChar) {
        this.rotation = rotation % 26;
        this.turnoverChar = turnoverChar;
        this.alphabet = new ArrayList<>();

        for (int i = 0; i < 26; i++)
            this.alphabet.add(alphabet.charAt(i) - 'A');
    }

    public void rotate(int index) {
        rotation = (rotation + index) % 26;
    }

    public char getForwardEncryptedCharacter(char character) {
        int i = (character + rotation - 'A') % 26;
        int result = (alphabet.get(i) - rotation + 26) % 26;
        return (char) ('A' + result);
    }

    public char getBackwardEncryptedCharacter(char character) {
        int i = (character + rotation - 'A') % 26;
        int result = (alphabet.indexOf(i) - rotation + 26) % 26;
        return (char) ('A' + result);
    }

    public void changeEncryptedAlphabet(String alphabet, char turnoverChar) {
        this.turnoverChar = turnoverChar;
        this.alphabet = new ArrayList<>();

        for (int i = 0; i < 26; i++)
            this.alphabet.add(alphabet.charAt(i) - 'A');
    }

    public int getRotation() {
        return this.rotation;
    }

    public int getTurnoverInt() {
        return this.turnoverChar - 'A';
    }

    public ArrayList<Integer> getAlphabet() {
        return this.alphabet;
    }

    public String toString() {
        return this.alphabet.toString();
    }
}
