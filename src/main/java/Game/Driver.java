package Game;

import Core.Arb3aCalculator;
import Core.Player;
import Core.RoomPool;
import Core.Arb3aCalculator.GameType;
import Core.DealingSys.SessionTracker;
import Core.Hukom.HukomColor;
import CardEngine.Card;
import CardEngine.CardDeck;
import CardEngine.Card.CardColor;
import CardEngine.Card.CardName;

public class Driver {
    public static void main(String[] args) {


        // 1- Receve a user request, create a room basically

        // so now weh 

        var Player1 = new Player("Mohammed");
        var Player2 = new Player("Ahmed");
        var Player3 = new Player("Waleed");
        var Player4 = new Player("Anas");
        //  Heavy refactor should go here as well !.
        System.out.println("... Hello to Arb3a ...");

        var TheRoom = RoomPool.Shared().LookupRoom();
        assert TheRoom != null;
        

        TheRoom.Join(Player1);
        TheRoom.Join(Player2);
        TheRoom.Join(Player3);
        TheRoom.Join(Player4);
        // We create a room please join the players in .



        var ST = new SessionTracker(TheRoom);

        var bool = ST.LaunchSession();
    
        RoomPool.Shared().Dump();// Memory-leaks?


        System.out.println("------------------------------------------");




        // CardDeck.Shared().Dump();

        for (Player players : TheRoom.Players) {
            System.out.println(players);
        }


        var calculator = new Arb3aCalculator(GameType.Hukom);

        // The cards, by the CardDeckSuffle, and the Hukom/Sun by the session/halla tracker.
        calculator.HotCalculation(new Card[] {

            new Card(CardName.Nine, CardColor.Club),
            new Card(CardName.Jack, CardColor.Club),
            new Card(CardName.Ace, CardColor.Heart),
            new Card(CardName.King, CardColor.Diamond)

        }, HukomColor.Club);
    }
}

