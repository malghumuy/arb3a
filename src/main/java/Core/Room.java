package Core;

import Core.Player.CharMap;

// This room will allow players to join, Imagine(GUI App) have an internet connection.
public class Room {

    // Now i am kinda keeping track of where i am right?

    public final Player[] Players;
    public final RoomManager MyManager = new RoomManager(); // FIXME
    private int Size;
    public static final int MAX_SIZE = 4;
    String RoomName;
    long RoomId;
    String RoomCode; // TODO. for Player-created rooms
    boolean Expired;
    boolean Finished;
    public Player CurrentDealer;
    private static long Gen;



    public Room(String RN) {
        Players = new Player[MAX_SIZE];
        RoomName = RN;
        RoomId = Gen++;
    }

    // The idea is click on play not friend room;
    // Will lookup RoomPool is there any not fiull?
    // if yes please add(LookUp().Join(*this)); player to the room
    // then update the list or what?
    // then notify? if no found getOrCreate there
    public boolean Join(Player A_Player) {
        if (IsFull()) { // TODO: can this be true when ::Lookup called.
            // If full go and create a new one; and find other requesting as well ! it's like a request problem that needs thinking?
            // Call the dealer pick up mechanism as well as start keep track of them counter-clockwise as well as keep track of other things
            // Game start when room has 4 players.
            return false; // Failed to join the room for non connectivity issue
        }

        Players[Size++] = A_Player;
        MyManager.Assign(A_Player);
        return true;
    }

    public boolean Exit(Player A_Player) { return false; } // TODO: Unimplemented

    // Should be called when RoomPool is looked up.
    // If true, create a room. Else, Get
    public boolean IsFull() { return Size == MAX_SIZE; }

    public boolean IsEmpty() { return Size == 0; }

    public void Dump() {
        for (var P: Players) {
            System.out.println("[Dump] room's players: " + P);
        }
    }

    // Getters
    public int GetSize() {
        return Size;
    }

    public Player GetPlayerByMap(CharMap M) {

        for (Player P : Players) {
            if (P.Mapper == M) {
                return P;
            }
        }
        return null; // Error shouldnot happen;
    }

    // ToString
    @Override
    public String toString() {
        return "[" + this.GetSize() + "] " + "Players";
    }

    public boolean FillPlayerMapp(int BasedPlayer) {

        var Lookup = BasedPlayer + 1;
        if (BasedPlayer == 3)
            Lookup = 0;
        
        var CPlayer = Players[Lookup];
        while (CPlayer.Mapper == null) {
            CPlayer.GetPlayerMap(Lookup); // Set the player Map next to dealer.
            Lookup ++;
            if (Lookup == 4) 
                Lookup = 0;  // Circling.

            CPlayer = Players[Lookup];
        }
        return false;


    }
}

