package sixmax06.javafx.enigmamachine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Rotor {
    private int rotation;
    private TreeMap<Character, Character> alphabet;

    public Rotor(int rotation, String alphabet) {
        this.rotation = rotation % 26;
        this.alphabet = new TreeMap<>();

        for (int i = 0; i < 26; i++)
            this.alphabet.put((char) (i + 'A'), alphabet.charAt(i));
    }

    public void rotate(int index) {
        ArrayList<Character> values = new ArrayList<>();
        index %= 26;
        for(int i = 0; i < this.alphabet.size(); ++i){
            values.add(this.alphabet.get((char)(i+1)));
        }
        System.out.println(values);

        for (int i = 0; i < index; ++i){
            values.addFirst(values.getLast());
            values.removeLast();
        }
        for(int i = 0; i < values.size(); ++i){
            this.alphabet.put((char) (i+1), values.get(i));
        }
        values.clear();
    }

    public Character getCorrespondingValue(char key) {
        return this.alphabet.get(key);
    }

    public Character getCorrespondingKey(char value) {
        Collection<Character> chiavi = alphabet.keySet();
        for (var i : chiavi){
            if (this.alphabet.get(i).equals(value)){
                return i;
            }
        }
        return null;
    }

    public int getRotation() {
        return rotation;
    }

    public TreeMap<Character, Character> getAlphabet() {
        return alphabet;
    }

    public static TreeMap<Character, Character> getAlphabet(int index) throws IOException {
        String file = "rotors.csv";
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            return null;
        }
        BufferedReader br = new BufferedReader(fr);
        TreeMap<Character, Character> alphabet = new TreeMap<>();
        String riga = br.readLine();
        while(riga != null){
            //System.out.println(riga);
            if (riga.split(";")[0].equals(String.valueOf(index))){
                String riga_alfabeto = riga.split(";")[1];
                //System.out.println(riga_alfabeto);
                for (int i = 0; i < riga_alfabeto.length(); ++i){
                    alphabet.put((char)('A'+i), riga_alfabeto.charAt(i));
                }
                return alphabet;
            }
            riga = br.readLine();
        }
        return null;
    }
}
