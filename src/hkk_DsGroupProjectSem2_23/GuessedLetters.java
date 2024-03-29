package hkk_DsGroupProjectSem2_23;

import java.util.HashSet;
import java.util.Set;

public class GuessedLetters {
    private Set<Character> guessedLetters;

    public GuessedLetters() {
        guessedLetters = new HashSet<>();
    }

    public boolean guessLetter(char letter) {
        if (guessedLetters.contains(letter)) {
            System.out.println("You already guessed this letter.");
            return false;
        } else {
            guessedLetters.add(letter);
            return true;
        }
    }

    public boolean isLetterGuessed(char letter) {
        return guessedLetters.contains(letter);
    }
}