package Core.DealingSys;


import Core.Player;
import Core.Room;
import Core.Arb3aCalculator.GameType;
import Core.Player.CharMap;

class SessionTracker {


    
    Player CurrentDealer;
    GameType GT = null;


    public boolean LaunchSession (Room R) {



        var Idx = 0;
        var FirstTrial = true;
        var Priority = 0;
        var ValidPriority = false;
        // Its valid if Idx - Priority != 0 hits true. If becoms True the conditiuo its valid to request/claim AheadPurchase.

        while (Idx < 8) { // 3 and 7 is always last purchaser. this way we can know who can have before vaildaity.
            // If purchase made, 
            // are index not 0: yes - > pop up basically
            if (Idx != 0)
                ValidPriority = true; // basically like saying.  its valid to popup.

            // if purchase made 1, higher priority. 
            if (Idx == 4) { FirstTrial = false;} // We start second trial no purchase made in the first trial.
            

            // always keep track of this validation.

        }


        
        for (Player P : R.Players) {

            System.err.println("You have 5 seconds, please make a choice based on the cards you got in your hands");
            
            // 1- We should not start looping each player, but on the right of the current dealer of the session right which if say C, so for loop won't work, cuz of currentDealer C, will go 1
            // second will be full (out of scope.) // so better to start while loop(i < 8); 0, 1, 2, 3, 4, 5, 6, 7;

        }
        // Each player will have a timer if they make a purchase hell yes, 
        while (true) {
            // Take input from currentPlayerDealer.
            // if the user make a purshase, we have a sucess story.
            // We wanna keep a loop in the 





        }


        // Failed to see a purchase, else WE have a valid session, ignite the type and wake the calculate up and the qeed system as well as the Mashroo3 calculator.
        return false;





    }


    



} 
// Keep track of players table and their turn && dealers.
public class DealingController {
    Room CurRoom;
    Player Dealer; // FIXME

    public DealingController(Room CR) {
        CurRoom = CR;
    }

    // TODO: All return type should be smth different, and must have params instead.
    private Player /*&*/ NextDealer() {
        // FIXME: Should be for sure, O(1), Until we have 'team makeup/pair mechanism'
        assert Dealer != null;


        // A dealer must be assinged to whatever we got from the room.Lookingup;
        var LookupMapper = MapNxtDealer(Dealer.Mapper);
        
        // We got the turn
        var NxtDealer = CurRoom.GetPlayerByMap(LookupMapper);  // this is o(n) loop/
        // If Dealer == NxtDealer : means we fail to continue 'الصكة'
        
        NxtDealer.IsDealer = true;
        
        Dealer.IsDealer = false;
        Dealer = NxtDealer;
    
        return NxtDealer;

    }

    private CharMap MapNxtDealer(CharMap Mapping) {

        switch (Mapping) {
            case A:
                return CharMap.B;
                case B:
                return CharMap.C;
                case C:
                return CharMap.D;
                case D:
                return CharMap.A; // Circuling, Pay attention;
            default:
                // we will see;
                return null;
        }

    }

    // this is a first for every room right ? and we need a map between the number picked and store the corresponding character, okay? this is not of any help wiothout encryption okay ?
    private Player GenerateDealer() {
        // TODO: AlreadyGeneratedRooms, To mark rooms has asked. Force By API.

        assert CurRoom.IsFull();  // FIXME: This is not deterministic, Assume full means 4 players have joined the room

        var Min = 0;
        var Max = 3;
        var RandIndex = (int) Math.floor(Math.random() * (Max - Min + 1) + Min);  // Generate random int value from 0 to 3
        Dealer = CurRoom.Players[RandIndex];  // FIXME
        Dealer.IsDealer = true;
        var Ignored = Dealer.GetPlayerMap(RandIndex); // Set the clock to wind.
        return Dealer;
    }

    public Player DoIt() {
        return GenerateDealer();
    }
}
