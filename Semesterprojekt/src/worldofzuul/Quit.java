/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worldofzuul;

/**
 *
 * @author magnusmortensen
 */
public class Quit {
      /**
     * Quit handler method. If the user command isn't just quit, when print
     * error message and return the quit boolean as false.
     *
     * @param command String argument of user command input.
     * @return Boolean return true if correct quit command, otherwise false.
     */
    private boolean quit(Command command) {
        // If user command contain a second word, the boolean return false.
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } 
        // Otherwise return true, intending to quit the game.
        else {
            return true;
        }
    }
}
