package yash;

public class Score {
	private int tScore;
	
		public int getScore(){
	        return tScore;
	    }

	    public void setScore(int score){
	        tScore += score;
	    }
		
int min,i;

player p1 = new Player();
player p2 = new Player();
Dice dice = new Dice();

if (p1.dice.dices.length > p2.dice.dices.length)
	min = p2.dice.dices.length;
else
	min = p1.dice.dices.length;

for (i=0;i<min;i++) {
  if (p1.dice[i] > p2.dice[i]) {
	 p1.setScore(score)
	 
  else
	 p2.setScore(score)
	 
  }
  
  

/* if  a player wins during attacking or defending we call set score with corespponding to the player like if we take player 1 then we call p1.setscore/**
 * after the win or loose is finsished we call get methods
 /**
 
