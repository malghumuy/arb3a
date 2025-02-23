package Core.DealingSys;


import Core.Player;
import Core.Room;
import Core.Player.CharMap;


// Keep track of players table and their turn && dealers.
public class DealingController {
    Room CurRoom;
    Player Dealer; // FIXME

    public DealingController(Room CR) {
        CurRoom = CR;
        DoIt();
    }

    // This shall be called by the DC, in two ways:
    // 1- When no purchase in a session
    // 2- When a complete session and after calculation is made to get the player. this ways.

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
        CurRoom.CurrentDealer = Dealer;
        return NxtDealer;

    }

    public CharMap MapNxtDealer(CharMap Mapping) {

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

        // and this is related for sure by the way the First dealer is assigned to for determinsim.

        CurRoom.CurrentDealer = Dealer;
        var Ignored = Dealer.GetPlayerMap(RandIndex); // Set the clock to wind.
        CurRoom.FillPlayerMapp(RandIndex); // this is basically telling you which player was picked as the dealer and the assigned letter to it. basically.

        System.err.println("The dealer is: " + Dealer.Name);
        return Dealer;
    }

    public Player DoIt() {
        return GenerateDealer();
    }
}
