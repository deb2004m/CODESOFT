import java.util.Random;
import java.util.Scanner;
public class NumberGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        boolean playAgain =true;
        
        while (playAgain){ 
        int randomNumber = rand.nextInt(100) + 1 ;
        // System.out.println("random Number is:"+ randomNumber); 
            
        int attempts = 0;
        boolean GuessNum = false;
        System.out.println("Welcome to the Number Guessing Game!");

        while(!GuessNum){

            System.out.println("enter your guess (between 1-100) number:");
            int guessNum = sc.nextInt();
            attempts++;
    
        if(guessNum == randomNumber){
            System.out.println("<--congratulations--> you win the game");
            System.out.println("you guessed the number : " + guessNum + " & you took it " + attempts + " attempts.");
            GuessNum = true;
        }
        else if(guessNum > randomNumber){
            System.out.println("oops..!!the number is  too high, try again..");
          
        }
        else {
            System.out.println("oops!!the number is  too low, try again..");
            
        }
    }
    System.out.print("Do you want to play again? (yes/no): ");
    String playChoice = sc.next().toLowerCase();
    if (!playChoice.equals("yes")) {
        playAgain = false;
        System.out.println("Thank you for playing!");
       }
    }
  }   
}
