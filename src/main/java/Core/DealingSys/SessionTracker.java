package Core.DealingSys;

import Core.Player;
import Core.Room;
import Core.Arb3aCalculator.GameType;

// We should injcet DC here;
public class SessionTracker {


    
    Player CurrentDealer;
    GameType GT = null;
    DealingController DC;
    boolean IsSessionClosed = false;

    Room R;

    public SessionTracker(Room R) {

        this.DC = new DealingController(R);
    }


    // This shall be call for every session,
    public boolean LaunchSession () {
        if (IsSessionClosed) {
            return false; // The room is already in a session, basically
        }



        var Idx = 0;
        var IsAwwal = true;
        var Priority = 0;
        var ValidPriority = false;


        System.out.println(DC.Dealer.Mapper);
        var LookupMapper = DC.MapNxtDealer(DC.Dealer.Mapper);
        System.out.println(LookupMapper);
        
        var StarterPlayer = DC.CurRoom.GetPlayerByMap(LookupMapper);  // this is o(n) loop








        
        String Msg = "Sun/Hukom/Pass";
        // Its valid if Idx - Priority != 0 hits true. If becoms True the conditiuo its valid to request/claim AheadPurchase.

        while (Idx < 8) { // 3 and 7 is always last purchaser. this way we can know who can have before vaildaity.

            // if purchase made 1, higher priority. 
            if (Idx == 4) {
                 IsAwwal = false;
                 Msg = "Sun/Hukom Thani/Walla";
                 
            } // We start second trial no purchase made in the first trial.
            
            // we shall break once a purchase is made.




            
            System.err.println("Please make a purchase: " + StarterPlayer.Name + Msg);
            
            

          //  System.err.println("Starting at player: ");
            // If purchase made, 
            // are index not 0: yes - > pop up basically
            //if (Idx != 0)
              //  ValidPriority = true; // basically like saying.  its valid to popup.
            // always keep track of this validation.

            // Moving means the next player turn whos next player ? the one whos next to this okay ? 
            LookupMapper = DC.MapNxtDealer(StarterPlayer.Mapper);  // Do a linked list.
        
            StarterPlayer = R.GetPlayerByMap(LookupMapper);  // this is o(n) loop

            Idx ++;
        }



        // , false means keep doing 
        // Failed to see a purchase, else WE have a valid session, ignite the type and wake the calculate up and the qeed system as well as the Mashroo3 calculator.
        return false;





    }
}