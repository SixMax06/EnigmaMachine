package sixmax06.javafx.enigmamachine;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Rotor {
    private int position, rotation;
    private TreeMap<Character, Character> alphabet;

    public Rotor(int position, int rotation, TreeMap<Character, Character> alphabet) {
        this.position = position;
        this.rotation = rotation % 26;
        this.alphabet = alphabet;

        this.nRotate(rotation);
    }

    public Rotor(int position, int rotation, String alphabet) {
        this.position = position;
        this.rotation = rotation % 26;
        this.alphabet = new TreeMap<>();

        for (int i = 0; i < 26; i++)
            this.alphabet.put((char) (i + 'A'), alphabet.charAt(i));

        //this.nRotate(rotation);
    }

    public void rotate(int index) {
        ArrayList<Character> valori = new ArrayList<>();
        index %= 26;
        for(int i = 0; i < this.alphabet.size(); ++i){
            valori.add(this.alphabet.get((char)(i+1)));
        }
        System.out.println(valori);

        for (int i = 0; i < index; ++i){
            valori.addFirst(valori.getLast());
            valori.removeLast();
        }
        for(int i = 0; i < valori.size(); ++i){
            this.alphabet.put((char) (i+1), valori.get(i));
        }
        valori.clear();
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
        TreeMap<Character, Character> alphabet = new TreeMap();
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
        }
        return null;
    }

    public void nRotate(int rotations) {
        TreeMap<Character, Character> newAlphabet = new TreeMap<>();
        int index = rotations % 26;

        for (int i = 0; i < alphabet.size(); i++) {
            index = (index + 1) % alphabet.size();
            newAlphabet.put((char) (index + 'A'), alphabet.get((char) (i + 'A')));
        }

        this.alphabet = newAlphabet;
    }

    public int getPosition() {
        return position;
    }

    public int getRotation() {
        return rotation;
    }

    public TreeMap<Character, Character> getAlphabet() {
        return alphabet;
    }

    public static void main(String[] args) throws IOException {
        TreeMap<Character, Character> ts = Rotor.getAlphabet(1);
        System.out.println(ts);
    }
}
