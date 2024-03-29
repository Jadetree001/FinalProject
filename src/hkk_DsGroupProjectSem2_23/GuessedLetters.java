package hkk_DsGroupProjectSem2_23;

import java.util.HashSet;
import java.util.Set;

public class GuessedLetters {
    private Set<Character> guessedLetters;

    public GuessedLetters() {
        guessedLetters = new HashSet<>();
    }

    public boolean isLetterGuessedAlready(char letter) {
        if (guessedLetters.contains(letter)) {
            System.out.println("You already guessed this letter.");
            return true;
        } else {
            guessedLetters.add(letter);
            return false;
        }
    }

    public boolean isLetterGuessed(char letter) {
        return guessedLetters.contains(letter);
    }
}