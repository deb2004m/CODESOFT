import java.util.Scanner;
class BankAcc{
    private double balance;
    public BankAcc(double initialBalance){
        this.balance=initialBalance;
    }
    public double getbalance(){
        return balance;
    }
    public void deposit(double amount){

        balance+=amount;
    }
    public void withdraw(double amount){
       
            balance-=amount;     
    }
}
 class ATM{
    private BankAcc account;
    public ATM(BankAcc account){
        this.account = account;
    }

    public void displaymenu(){
        System.out.println("..Welcome to ATM machin menu..");
        System.out.println("1.Cheack Balance");
        System.out.println("2.Depposit Money:");
        System.out.println("3.Withdraw Money");
        System.out.println("4.Exit");
        System.out.println("Please enter your option sir/mam:");
    }

        public void cheackBalance(){
            System.out.println("your balance is:" + account. getbalance());

        }
        public void deposit(double amount){
        
            if(amount > 0 ){
                account.deposit(amount);
                System.out.println("Deposite Successfull.");
                cheackBalance();
            }else{
                System.out.println("Invalid amount.");
            }
        }
        public void withdraw(double amount){
            if(amount > 0 && amount <= account.getbalance() ){
                account.withdraw(amount);
                System.out.println("Withdraw Successfull.");
                cheackBalance();
            }
            else{
                System.out.println("Insufficient Balance.");
            }

        }

    }
public class ATMapp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double initialBalance;
        System.out.println("Please enter your initial balance:");
        initialBalance = sc.nextDouble();

        BankAcc account = new BankAcc(initialBalance);
        ATM atm = new ATM(account);

        
            
            while(true){
                atm.displaymenu();
            int choice = sc.nextInt();

            switch(choice){
                case 1:
                atm.cheackBalance();
                break;
                case 2:
                System.out.println("enter your deposit amount:");
                double depositeamount = sc.nextDouble();
                atm.deposit(depositeamount);
                break;
                case 3:
                System.out.println("enter your withdraw amount:");
                double withdrawamount = sc.nextDouble();
                atm.withdraw(withdrawamount);
                break;
                case 4:
                System.out.println("goodbye..");
                System.exit(0);
                break;
                default :
                System.out.println("invalid choice.");
                break;

            }
        }             
 }
} 