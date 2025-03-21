package sixmax06.javafx.enigmamachine;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class EnigmaMachine {
    private Rotor rotor1, rotor2, rotor3;
    private Reflector reflector;
    private String rotorType1, rotorType2, rotorType3;

    public EnigmaMachine() {
        FileReader fr;
        final String rotorsFile = "rotors.csv", reflectorFile = "reflector.csv";

        try {
            fr = new FileReader(rotorsFile);

            BufferedReader br = new BufferedReader(fr);
            String line;
            String[] info;

            line = br.readLine();
            info = line.split(";");
            this.rotorType1 = info[1];

            line = br.readLine();
            info = line.split(";");
            this.rotorType2 = info[1];

            line = br.readLine();
            info = line.split(";");
            this.rotorType3 = info[1];

            this.rotor3 = new Rotor(0, rotorType1);
            this.rotor2 = new Rotor(0, rotorType1);
            this.rotor1 = new Rotor(0, rotorType1);

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

        if (this.rotor3.getRotation() == 0) {
            this.rotor2.rotate(1);

            if (this.rotor2.getRotation() == 0)
                this.rotor1.rotate(1);
        }
    }

    public char getCodifiedCharacter(char character) {
        this.rotateRotors();

        int result = character - 'A';

        System.out.println((char) (result + 'A'));

        result = rotor3.getAlphabet().get(result);
        System.out.println((char) (result + 'A'));
        result = rotor2.getAlphabet().get(result);
        System.out.println((char) (result + 'A'));
        result = rotor1.getAlphabet().get(result);
        System.out.println((char) (result + 'A'));

        result = reflector.getReflectedCharacter(result);
        System.out.println((char) (result + 'A'));

        result = rotor1.getAlphabet().get(result);
        System.out.println((char) (result + 'A'));
        result = rotor2.getAlphabet().get(result);
        System.out.println((char) (result + 'A'));
        result = rotor3.getAlphabet().get(result);
        System.out.println((char) (result + 'A'));

        return (char) (result + 'A');
    }

    public static void main(String[] args) {
        EnigmaMachine enigmaMachine = new EnigmaMachine();
        System.out.println(enigmaMachine.getCodifiedCharacter('T'));
        System.out.println(enigmaMachine);
    }

    public String toString() {
        return rotor3 + "\n" + rotor2 + "\n" + rotor1 + "\n" + reflector;
    }
}
