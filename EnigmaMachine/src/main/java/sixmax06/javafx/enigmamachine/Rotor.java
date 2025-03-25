package sixmax06.javafx.enigmamachine;

import java.util.ArrayList;

/**
 * Classe che implementa il funzionamento di un singolo rotore
 */
public class Rotor {
    private int rotation;
    private char turnoverChar;
    private ArrayList<Integer> alphabet;

    /**
     * Costruttore
     * @param rotation Rotazione iniziale
     * @param alphabet Alfabeto di codifica
     * @param turnoverChar Carattere a cui scatta la rotazione forzata
     */
    public Rotor(int rotation, String alphabet, char turnoverChar) {
        this.rotation = rotation % 26;
        this.turnoverChar = turnoverChar;
        this.alphabet = new ArrayList<>();

        for (int i = 0; i < 26; i++)
            this.alphabet.add(alphabet.charAt(i) - 'A');
    }

    /**
     * Aggiorna la rotazione del rotore
     * @param index Indice di rotazione
     */
    public void rotate(int index) {
        rotation = (rotation + index) % 26;
    }

    /**
     * Codifica una lettera "in avanti" (verso il rotore successivo)
     * @param character Lettera da codificare
     * @return Lettera codificata
     */
    public char getForwardEncryptedCharacter(char character) {
        int i = (character + rotation - 'A') % 26;
        int result = (alphabet.get(i) - rotation + 26) % 26;
        return (char) ('A' + result);
    }

    /**
     * Codifica una lettera "al contrario" (verso il rotore precedente)
     * @param character Lettera da codificare
     * @return Lettera codificata
     */
    public char getBackwardEncryptedCharacter(char character) {
        int i = (character + rotation - 'A') % 26;
        int result = (alphabet.indexOf(i) - rotation + 26) % 26;
        return (char) ('A' + result);
    }

    /**
     * Cambia l'alfabeto di criptazione
     * @param alphabet Alfabeto da sostituire
     * @param turnoverChar Lettera di rotazione forzata
     */
    public void changeEncryptedAlphabet(String alphabet, char turnoverChar) {
        this.turnoverChar = turnoverChar;
        this.alphabet = new ArrayList<>();

        for (int i = 0; i < 26; i++)
            this.alphabet.add(alphabet.charAt(i) - 'A');
    }

    /**
     * Metodo che restituisce la rotazione
     * @return Rotazione
     */
    public int getRotation() {
        return this.rotation;
    }

    /**
     * Metodo che restituisce la lettera di rotazione forzata, castata come intero
     * @return Lettera di rotazione forzata (int)
     */
    public int getTurnoverInt() {
        return this.turnoverChar - 'A';
    }

    /**
     * Metodo che ritorna l'alfabeto di codifica
     * @return Alfabeto di codifica
     */
    public ArrayList<Integer> getAlphabet() {
        return this.alphabet;
    }

    /**
     * toString del rotore
     * @return toString dell'ArrayList di codifica
     */
    @Override
    public String toString() {
        return this.alphabet.toString();
    }
}
