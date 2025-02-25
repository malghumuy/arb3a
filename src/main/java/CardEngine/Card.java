package CardEngine;


// A signle card from the CardSet with its type; 
public class Card implements Comparable<Card> { 
    // TODO: it should be private, and generated not on demand.
    // for now, Will be generated on demand
    
    public enum CardColor { Club /*ةيريش*/, Diamond, Heart, Spade }

    public enum CardName { Seven, Eight, Nine, Ten, Jack, Queen, King, Ace }
    public CardColor Color;

    public CardName Name;
    private int Value = -1;  // set card priority.
    // Ten = 10 in sun and hukom,
    // King, 4 in both.
    // Queen, 3 both
    // Ace, 11 both
    // Jack, 2 sun, 20 hukom,
    // 9 14 only in hukom

    public Card(CardName N) {
        Name = N;

    }

    public Card(CardName N, CardColor C) {
        Name = N;
        Color = C;

        DeterminePriority(N);
    }


    private void DeterminePriority(CardName N) {
        switch (N) {
            case Seven, Eight, Nine:
                this.Value = 0; // Default
                break;
            case Ten:
                this.Value = 10;
                break;
            case Jack:
                this.Value = 2;  // Default, Hukom should change this.
                break;
            case Queen:
                this.Value = 3;
                break;
            case King:
                this.Value = 4;
                break;
            case Ace:
                this.Value = 11;
                break;
            default:
            // this.Value = -1;
                break; // unrechable
        }
    }

    public boolean SetForceException(int ChangeValue) {


        assert (this.Name == CardName.Nine || this.Name == CardName.Jack);

        if (ChangeValue != 14 && ChangeValue != 20)
            return false;


        this.Value = ChangeValue;
        return true;
        

        
    }

    // This is the default value, regardless of the GameType. Another phase is required.
    public int GetDefultValue() { return this.Value; }

    @Override 
    public int compareTo(Card Other) {

        var result = this.Value > Other.Value ? 1 : -1;
        if (Other.Value == this.Value) { result = 0; }
        return result;
    }

    @Override
    public String toString() {
        return "Card{" +
                "Type=" + Color +
                ", Name=" + Name +
                ", Value=" + Value +
                '}';
    }
}

