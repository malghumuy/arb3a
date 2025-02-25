package Core;

import CardEngine.Card;

// the team make up is not a concern of this class, and it should be composed in every room, or single and async for every request service,
// This is a good microservice example, where every room/session should query when needded
public class Arb3aCalculator {
    public enum GameType {
        Sun, Hukom;
    }
    GameType Type;

    Card.CardColor HukomColor; // to make 

    

    
    // Hot calculation should happen each Halla, where 4 cards are calculated based on the GameType.
    public boolean HotCalculation(Card[] Halla) {

        // first we have to have the table of whats known. how to get info about Halla.
        
        // We have garbage, in sun 789, are all garbge. so if the game is sun, and the 4 cards of type 7777, 7778, 7788, etc.. its garbage return 0; to the sum of the
        // Halla.



        
        // in Hukom it still garbage, execpt for the nine of the hukon type;
        return true;
    }

    public Arb3aCalculator(GameType T) {
        Type = T;
    }
}


