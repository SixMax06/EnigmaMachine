package sixmax06.javafx.enigmamachine;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Classe che ricrea il funzionamento della macchina enigma
 */
public class EnigmaMachine {
    private Rotor rotor1, rotor2, rotor3;
    private Reflector reflector;
    private String encryptedRotor1, encryptedRotor2, encryptedRotor3;
    private char turnoverChar1, turnoverChar2, turnoverChar3;

    /**
     * Costruttore
     * @param rotor1Type Tipo del 1° rotore
     * @param rotor2Type Tipo del 2° rotore
     * @param rotor3Type Tipo del 3° rotore
     */
    public EnigmaMachine(int rotor1Type, int rotor2Type, int rotor3Type) {
        FileReader fr;
        final String rotorsFile = "rotors.csv", reflectorFile = "reflector.csv";

        try {
            fr = new FileReader(rotorsFile);

            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] info;

            while ((line = br.readLine()) != null) {
                info = line.split(";");

                int type = Integer.parseInt(info[0]);

                if (type == 1) {
                    this.encryptedRotor1 = info[1] + info[2];
                    this.turnoverChar1 = info[2].charAt(0);

                } else if (type == 2) {
                    this.encryptedRotor2 = info[1] + info[2];
                    this.turnoverChar2 = info[2].charAt(0);

                } else if (type == 3) {
                    this.encryptedRotor3 = info[1] + info[2];
                    this.turnoverChar3 = info[2].charAt(0);
                }

                if (type == rotor1Type)
                    this.rotor1 = new Rotor(0, info[1], info[2].charAt(0));

                if (type == rotor2Type)
                    this.rotor2 = new Rotor(0, info[1], info[2].charAt(0));

                if (type == rotor3Type)
                    this.rotor3 = new Rotor(0, info[1], info[2].charAt(0));
            }

            fr = new FileReader(reflectorFile);

            br = new BufferedReader(fr);
            line = br.readLine();
            this.reflector = new Reflector(line);

        } catch (FileNotFoundException e) {
            System.out.println("ERROR. File not found : " + e.getMessage());
            System.exit(1);

        } catch (IOException e) {
            System.out.println("ERROR. I/O Error : " + e.getMessage());
            System.exit(2);
        }
    }

    /**
     * Funzione che ruota i rotori
     */
    public void rotateRotors() {
        this.rotor3.rotate(1);

        if (this.rotor3.getRotation() == rotor3.getTurnoverInt()) {
            this.rotor2.rotate(1);

            if (this.rotor2.getRotation() == rotor2.getTurnoverInt()) {
                this.rotor1.rotate(1);

                if (this.rotor1.getRotation() == rotor1.getTurnoverInt()) {
                    this.rotor2.rotate(1);
                    this.rotor3.rotate(1);
                }
            }
        }
    }

    /**
     * Cambia la tipologia del 1° rotore
     * @param type Tipo
     */
    private void changeRotor1Type(int type) {
        switch (type) {
            case 1:
                this.rotor1.changeEncryptedAlphabet(encryptedRotor1, turnoverChar1);
                break;
            case 2:
                this.rotor1.changeEncryptedAlphabet(encryptedRotor2, turnoverChar2);
                break;
            case 3:
                this.rotor1.changeEncryptedAlphabet(encryptedRotor3, turnoverChar3);
                break;
            default:
                break;
        }
    }

    /**
     * Cambia la tipologia del 2° rotore
     * @param type Tipo
     */
    private void changeRotor2Type(int type) {
        switch (type) {
            case 1:
                this.rotor2.changeEncryptedAlphabet(encryptedRotor1, turnoverChar1);
                break;
            case 2:
                this.rotor2.changeEncryptedAlphabet(encryptedRotor2, turnoverChar2);
                break;
            case 3:
                this.rotor2.changeEncryptedAlphabet(encryptedRotor3, turnoverChar3);
                break;
            default:
                break;
        }
    }
    /**
     * Cambia la tipologia del 3° rotore
     * @param type Tipo
     */
    private void changeRotor3Type(int type) {
        switch (type) {
            case 1:
                this.rotor3.changeEncryptedAlphabet(encryptedRotor1, turnoverChar1);
                break;
            case 2:
                this.rotor3.changeEncryptedAlphabet(encryptedRotor2, turnoverChar2);
                break;
            case 3:
                this.rotor3.changeEncryptedAlphabet(encryptedRotor3, turnoverChar3);
                break;
            default:
                break;
        }
    }

    /**
     * Cambia la tipologia di un singolo rotore
     * @param rotor Posizione del rotore
     * @param type Tipologia della posizione
     */
    public void changeRotorType(int rotor, int type) {
        switch (rotor) {
            case 1:
                this.changeRotor1Type(type);
                break;
            case 2:
                this.changeRotor2Type(type);
                break;
            case 3:
                this.changeRotor3Type(type);
                break;
            default:
                break;
        }
    }

    /**
     * Codifica una lettera attraverso i rotori e il riflessore
     * @param character Lettera da codificare
     * @return Lettera codificata
     */
    public char getEncryptedCharacter(char character) {
        this.rotateRotors();

        char result = character;

        result = rotor3.getForwardEncryptedCharacter(result);
        result = rotor2.getForwardEncryptedCharacter(result);
        result = rotor1.getForwardEncryptedCharacter(result);

        result = reflector.getReflectedCharacter(result);

        result = rotor1.getBackwardEncryptedCharacter(result);
        result = rotor2.getBackwardEncryptedCharacter(result);
        result = rotor3.getBackwardEncryptedCharacter(result);

        return result;
    }

    /**
     * Codifica una frase lettera per lettera, eliminando ogni lettera maiuscola
     * @param phrase Frase da codificare
     * @return Frase codificata
     */
    public String getCodifiedPhrase(String phrase) {
        phrase = phrase.toUpperCase();
        phrase = phrase.replaceAll("[^A-Z]", "");

        StringBuilder result = new StringBuilder();
        for (char c : phrase.toCharArray()) {
            char encryptedChar = getEncryptedCharacter(c);
            result.append(encryptedChar);
        }

        return result.toString();
    }

    /**
     * Main per debugging
     */
    public static void main(String[] args) {
        EnigmaMachine enigmaMachine = new EnigmaMachine(1, 1, 1);
        enigmaMachine.changeRotorType(1, 2);
        System.out.println(enigmaMachine.getCodifiedPhrase("ciao"));
    }

    /**
     * ToString
     * @return Lista di ogni rotore
     */
    @Override
    public String toString() {
        return rotor3 + "\n" + rotor2 + "\n" + rotor1 + "\n" + reflector;
    }
}
