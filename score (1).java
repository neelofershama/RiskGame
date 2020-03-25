package yash;

public class Score {
	private int tScore =0;
	
		public int getScore(){
	        return tScore;
	    }

	    public void setScore(int score){
	        tScore += score;
	    }
Public static void main(String args[]){
int min,i;

player p1 = new Player();
player p2 = new Player();
Dice dice = new Dice();

if (p1.dice.length > p2.dice.length)
	min = p2.dice.length;
else
	min = p1.dice.length;

for (i=0;i<min;i++) {
  if (p1.dice[i] > p2.dice[i]) {
	 p1.setScore(score)
	 
  else
	 p2.setScore(score)
	 
  }
  
if (p1.getScore > p2.getScore)
   System.out.println("player 1 won");
else
   System.out.println("player 2 won");

}
/* if  a player wins during attacking or defending we call set score with corespponding to the player like if we take player 1 then we call p1.setscore/**
 * after the win or loose is finsished we call get methods
 /**
 
