package Game;

import Core.Player;
import Core.RoomPool;
import Core.DealingSys.SessionTracker;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {


        // 1- Receve a user request, create a room basically



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

    }
}

