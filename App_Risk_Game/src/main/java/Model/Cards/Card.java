package App_Risk_Game.src.main.java.Model.Cards;

public class Card {

    private int value;
    private String location;
    private String cardType;

    public void setValue(int val){
        this.value = val;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setCardType(String cardType){
        this.cardType = cardType;
    }

    public int getValue() {
        return value;
    }

    public String getLocation() {
        return location;
    }

    public String getCardType(){ return cardType; }

}

