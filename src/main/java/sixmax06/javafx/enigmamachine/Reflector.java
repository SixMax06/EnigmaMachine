package sixmax06.javafx.enigmamachine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeMap;

public class Reflector {
    private ArrayList<Rotor> rotors;
    private TreeMap <Character, Character> alphabet;

    public Reflector(ArrayList<Rotor> rotors) {
        this.rotors = rotors;
        this.alphabet = new TreeMap<>();
        String percorso = "reflector.csv";
        try {
            FileReader fr = new FileReader(percorso);
            BufferedReader br = new BufferedReader(fr);
            String riga = br.readLine();
            if (riga != null) {
                for (int i = 0; i < 26; ++i){
                    this.alphabet.put((char)('A'+i), riga.charAt(i));
                }
                System.out.println(alphabet.keySet() + ", " + alphabet.values());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public char getCodifica(char c){
        System.out.println("INIZIO: " + c);
        for (var i : rotors) {
            c = i.getCorrespondingValue(c);
            System.out.println(c);
        }
        System.out.println("ORA LE CHIAVI: ");
        c = alphabet.get(c);
        System.out.println(c + ";");
        for(int i = rotors.size()-1; 0 <= i; --i) {
            c = rotors.get(i).getCorrespondingValue(c);
            System.out.println(c + ", alfabeto = " + rotors.get(i).getAlphabet().values());
        }
        return c;
    }

    public TreeMap<Character, Character> getReflectedAlphabet() {
        return null;
    }
}
