package CardEngine;

import java.util.Random;
import java.util.logging.LogRecord;

public class CardDeck  {
    private  static Card[] Cards;
    // it should be allocated by the user or what ?
    // once the instance is generated for us we gotta basically do the computing once rikght ? but does killing. the should be stored at a very high level in the app initi.

    private static CardDeck __deck__;
    // and basically in a server or what ? got no idea but its a shared object between all users online and offline so either we populate or createig 

    // the thing is to be afradid who calls once right ?
    private CardDeck() {


        Cards = new Card[32];
        GenerateDeck();
    }


    public static CardDeck Shared() {
        if (__deck__ == null) {
            __deck__ = new CardDeck();
        }
        return __deck__;
    }

    // This should statically lookedup onc
    private static boolean GenerateDeck() {
        // this shall geneate 4 sevens
        var Idx = 0;
        var Colors = new Card.CardColor[] {
            Card.CardColor.Club,
            Card.CardColor.Heart,
            Card.CardColor.Spade,
            Card.CardColor.Diamond,
        };

       var Names = new Card.CardName[] {
            Card.CardName.Seven,
            Card.CardName.Eight,
            Card.CardName.Nine,
            Card.CardName.Ten,
            Card.CardName.Jack,
            Card.CardName.Queen,
            Card.CardName.King,
            Card.CardName.Ace
       };

       var Divider = 0;
       var ColorIdx = 0; 

       while (Idx < 32) {

            if ((Idx) % 4== 0 && Idx > 0) {
                Divider += 1;
                ColorIdx = 0;
            }
            Cards[Idx] = new Card(Names[Divider], Colors[ColorIdx]); // We will see into the priorioryt
            ColorIdx ++;
            Idx ++;
        }
        Shuffle();
        return true;
    }

    public void Dump() {
        for (Card C : Cards) {
            System.out.println("Deck is: " + C.Name);
            System.out.println("Deck is: " + C.Color);
            System.out.println("Deck is: " + C.GetDefultValue());
        }
    }


        // Fisher-Yates Shuffle adapted for Card[]
    private static void Shuffle() {
        var R = new Random();
        var Length = Cards.length;

        // Fisher-Yates shuffle with while loop
        var I = Length - 1;
        while (I > 0) {
            var J = R.nextInt(I + 1);

            // Swap cards[i] and cards[j]
            var temp = Cards[I];
            Cards[I] = Cards[J];
            Cards[J] = temp;
            I--;
        }
    }
    
}