package sixmax06.javafx.enigmamachine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EnigmaMachine {
    private Rotor rotor1, rotor2, rotor3;
    private Reflector reflector;
    private final String rotorsFile = "rotors.csv", reflectorFile = "reflector.csv";

    public EnigmaMachine(int typeRotor1, int typeRotor2, int typeRotor3) {
        FileReader fr;

        try {
            fr = new FileReader(this.rotorsFile);

            BufferedReader br = new BufferedReader(fr);
            String line;

            while (br.readLine() != null) {
                line = br.readLine();
                String[] info = line.split(";");

                if (info[0].equals("typeRotor1"))
                    this.rotor1 = new Rotor(0, info[1]);

                if (info[0].equals("typeRotor2"))
                    this.rotor2 = new Rotor(0, info[1]);

                if (info[0].equals("typeRotor3"))
                    this.rotor3 = new Rotor(0, info[1]);


                fr = new FileReader(rotorsFile);

                br = new BufferedReader(fr);
                line = br.readLine();
                this.reflector = new Reflector(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR. File not found : " + e.getMessage());
            System.exit(1);

        } catch (IOException e) {
            System.out.println("ERROR. I/O Error : " + e.getMessage());
            System.exit(2);
        }
    }
}
