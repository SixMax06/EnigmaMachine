package sixmax06.javafx.enigmamachine;

import java.util.TreeMap;

/**
 * Classe che implementa il funzionamento di un riflettore
 */
public class Reflector {
    private final TreeMap <Character, Character> reflectedAlphabet;

    /**
     * Costruttore con alfabeto di codifica
     * @param alphabet Alfabeto di codifica
     */
    public Reflector(String alphabet) {
        this.reflectedAlphabet = new TreeMap<>();

        for (int i = 0; i < 26; i++)
            this.reflectedAlphabet.put((char) (i + 'A'), alphabet.charAt(i));
    }

    /**
     * Codifica o decodifica una lettera secondo l'alfabeto del riflessore
     * @param key Lettera da codificare
     * @return Codifica della lettera
     */
    public char getReflectedCharacter(char key) {
        return this.reflectedAlphabet.get(key);
    }

    /**
     * Alfabeto riflesso
     * @return toString dell'alfabeto riflesso
     */
    public TreeMap<Character, Character> getReflectedAlphabet() {
        return this.reflectedAlphabet;
    }

    /**
     * toString dell'alfabeto del riflessore
     * @return toString della mappa del riflessore
     */
    @Override
    public String toString() {
        return this.reflectedAlphabet.toString();
    }
}
