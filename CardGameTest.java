import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Tests the CardGame class through smaller unit tests which can be performed quickly.
 *
 * @author 014485
 * @author 054530
 * @version 1.1
 */
public class CardGameTest {
    private ArrayList<Integer> createdPack;
    private Player[] plArray;
    private CardDeck[] dkArray;
    private Player plObject;
    private CardDeck dkObject;

    // Creates mock object for a player, and then assigns three duplicate players to the player array.
    @Before
    public void objectArrSetUp() {
        plArray = new Player[2];
        dkArray = new CardDeck[2];
        plObject = new Player(1);
        dkObject = new CardDeck(1);
        // Adds the player to the player array.
        for (int i = 0; i < 2; i++) {
            plArray[i] = plObject;
            dkArray[i] = dkObject;
        }
    }

    @Before
    public void cardPackSetUp() {
        createdPack = new ArrayList<Integer>();
        for (int i = 0; i < 16; i++) {
            createdPack.add(2);
        }
        System.out.println(createdPack.size());
    }


    // Removes mock objects after tests to save memory by triggering automatic garbage collection.
    @After
    public void tearDown() {
        createdPack = null;
        plArray = null;
        dkArray = null;
    }

    @Test
    public void main() {
    }

    /**
     * Tests validateNumPlayersInput method with a valid input of a positive integer greater than 1.
     *
     * @throws NumberFormatException When an input which cannot be parsed as an integer is entered.
     */
    @Test
    public void testValidateNumPlayersInput() throws NumberFormatException, IllegalNumPlayersSizeException {
        // Creates an input which can be parsed as an integer greater than 1.
        Scanner inputPlayersPosInt = new Scanner("4");
        int expected = 4;
        int result = CardGame.validateNumPlayersInput(inputPlayersPosInt);
        assertEquals(expected, result);
    }

    // Tests validateNumPlayersInput with an invalid input of an integer which is too small.
    @Test
    public void testValidateNumPlayersInput_IllegalSmallValue() {
        // Creates an input which can be parsed as an integer, but is too small.
        try {
            Scanner inputPlayersString = new Scanner("1");
            CardGame.validateNumPlayersInput(inputPlayersString);
            fail("Should have thrown IllegalNumPlayersSizeException.");
        } catch (IllegalNumPlayersSizeException e) {
            assertTrue(true);
        }
    }

    // Tests validateNumPlayersInput with an invalid input of a negative integer value.
    @Test
    public void testValidateNumPlayersInput_IllegalNegValue() {
        // Creates an input which can be parsed as an integer, but is negative.
        try {
            Scanner inputPlayersString = new Scanner("-1");
            CardGame.validateNumPlayersInput(inputPlayersString);
            fail("Should have thrown IllegalNumPlayersSizeException.");
        } catch (IllegalNumPlayersSizeException e) {
            assertTrue(true);
        }
    }

    /**
     * Tests validateNumPlayersInput method with an invalid input of a string.
     *
     * @throws NumberFormatException          When an input which cannot be parsed as an integer is entered.
     * @throws IllegalNumPlayersSizeException When an input which can be parsed as an integer, but is too small, is
     *                                        entered.
     */
    @Test(expected = NumberFormatException.class)
    public void testValidateNumPlayersInput_IllegalStringValue() throws NumberFormatException, IllegalNumPlayersSizeException {
        // Creates an input which cannot be passed as an integer (only a string).
        Scanner inputPlayersString = new Scanner("string");
        CardGame.validateNumPlayersInput(inputPlayersString);
        fail("NumberFormatException should have been thrown.");
    }

    // Tests validatePackInput method with an invalid non-existing file name.
    @Test(expected = FileNotFoundException.class)
    public void testValidatePackInput_IllegalFileName() {
        Scanner inputPack = new Scanner("invalid");
        CardGame.validatePackInput(inputPack);
        fail("FileNotFoundException should have been thrown.");
    }

    /**
     * Tests importPack method by comparing number of lines and contents after file has been parsed by game.
     *
     * @throws IOException When the card pack file does not exist.
     */
    @Test
    public void testImportPack() throws IOException {
        String input = "testCardPack.txt";
        Scanner inputPack = new Scanner(input);
        BufferedReader in = CardGame.validatePackInput(inputPack);
        ArrayList<Integer> importedPack = CardGame.importPack(in, 3);
        boolean notIdentical = false;
        if (importedPack.size() != 24) {
            notIdentical = true;
        }
        for (Integer i : importedPack) {
            if (i != 4) {
                notIdentical = true;
                break;
            }
        }
        assertFalse(notIdentical);
    }

    // Tests dealCards by inputting a test card pack for a two-player game, and checking that every hand and every
    // deck has four cards.
    @Test
    public void testDealCards() {
        boolean evenlyDistributed = true;
        CardGame.dealCards(createdPack, 2, plArray, dkArray);

        for (Player p : plArray) {
            if (p.getHandSize() != 4) {
                evenlyDistributed = false;
                break;
            }
        }
        assertTrue(evenlyDistributed);
    }

    // Tests genHashMap by inputting a test card pack consisting only of values 2, and then checking the generated
    // dictionary for the frequency of value 2, which should be 2 * 8 = 16.
    @Test
    public void testGenHashMap() {
        Map<Integer, Integer> dict = CardGame.genHashMap(createdPack);
        int frequency = dict.get(2);
        assertEquals(16, frequency);
    }

    /**
     * Tests countFrequencies method by inputting a test card pack consisting only of values 2, then counting the
     * frequencies of the pack for a two-player game, and testing whether the game continues, stating that there is
     * guaranteed to be a winner.
     */
    @Test
    public void testCountFrequencies() {
        boolean playGame = CardGame.countFrequencies(createdPack, 2);
        assertTrue(playGame);
    }
}
