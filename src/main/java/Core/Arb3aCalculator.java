package Core;

import CardEngine.Card;
import CardEngine.Card.CardName;

// the team make up is not a concern of this class, and it should be composed in every room, or single and async for every request service,
// This is a good microservice example, where every room/session should query when needded
public class Arb3aCalculator {
    public enum GameType {
        Sun, Hukom;
    }
    GameType Type;

    Card.CardColor HukomColor; // to make 

    int SakahSum = 0; // Adds up each halla result
    

    
    // Hot calculation should happen each Halla, where 4 cards are calculated based on the GameType.
    public boolean HotCalculation(Card[] Halla, Hukom.HukomColor HC) {

        // Well, we don't concern the eater. we just calculate, who eats. is determine, by the team purcahses, and who eats who. 
        // who eats who is got by as follows: if the game is sun and halla start with a new color or redefined color, if its redefined, then we need
        // bank system, that keep tracks of things. 
        var HallaResult = 0;
        var IsHukom = false;

        if (Type == GameType.Hukom) {
            IsHukom = true;
        }
                
        // this should work for sun, and hukom.
        for (Card C : Halla) {
            if (C.Name == CardName.Nine || C.Name == CardName.Jack) {
                if (IsHukom) {
                    // first make sure, same hukom color;
                    if (C.Color.toString() == HC.toString()) {
                        if (C.Name == CardName.Nine) { HallaResult += 14; C.SetForceException(14);}
                        else { HallaResult += 20; C.SetForceException(20);}
                        continue;
                    }
                }
            }
            HallaResult += C.GetDefultValue();
        }

        // the only thing left, to determine the value wins, and the value looses.
        // each team should store their final score. and accessing the teams scores to determine, easy.
        System.out.println("The 4 cards halla result: " + HallaResult);
        return true;
    }

    public Arb3aCalculator(GameType T) {
        Type = T;
    }
}


