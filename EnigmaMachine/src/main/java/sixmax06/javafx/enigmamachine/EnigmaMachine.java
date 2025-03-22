package sixmax06.javafx.enigmamachine;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EnigmaMachine {
    private Rotor rotor1, rotor2, rotor3;
    private Reflector reflector;
    private String encryptedRotor1, encryptedRotor2, encryptedRotor3;

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

                if (type == 1) this.encryptedRotor1 = info[1];
                else if (type == 2) this.encryptedRotor2 = info[1];
                else if (type == 3) this.encryptedRotor3 = info[1];

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

    public void rotateRotors() {
        this.rotor3.rotate(1);

        if (this.rotor3.getRotation() == rotor3.getTurnoverInt()) {
            this.rotor2.rotate(1);

            if (this.rotor2.getRotation() == rotor2.getTurnoverInt()) {
                this.rotor1.rotate(1);

                if (this.rotor1.getRotation() == rotor1.getTurnoverInt()) {
                    this.rotor2.rotate(1); this.rotor3.rotate(1);
                }
            }
        }
    }

    public char getCodifiedCharacter(char character) {
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

    public String getCodifiedPhrase(String phrase) {
        phrase = phrase.toUpperCase();
        phrase = phrase.replaceAll("[^A-Z]", "");

        StringBuilder result = new StringBuilder();
        for (char c : phrase.toCharArray()) {
            char encryptedChar = getCodifiedCharacter(c);
            result.append(encryptedChar);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        EnigmaMachine enigmaMachine = new EnigmaMachine(1, 1, 1);
        System.out.println(enigmaMachine.getCodifiedPhrase("XQQMR"));
    }

    public String toString() {
        return rotor3 + "\n" + rotor2 + "\n" + rotor1 + "\n" + reflector;
    }
}
