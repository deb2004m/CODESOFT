import java.util.Scanner;
public class GradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        
        System.out.print("enter Number of subjects:");
        int numsubjects = sc.nextInt();
       
        int [] marks = new int [numsubjects];

        for(int i = 0; i< marks.length; i++){
            System.out.print("enter the marks of " + (i+1) + " : "  );
            marks[i] = sc.nextInt();
        }
        double totalMarks = 0;
        for(int Mark:marks){ 
         totalMarks += Mark;         
        }
        
        double avrage =  totalMarks / numsubjects ;

       char Grade;
       if(avrage >=90 && avrage <=100){
        Grade = 'O';     
       }
       else if( avrage>=80 && avrage< 90){
        Grade = 'A';
       }
       else if( avrage >=70 && avrage < 80){
        Grade = 'B';
       }
       else if( avrage >=60 && avrage < 70){
        Grade = 'C';
       }
       else if(avrage >=50 && avrage < 60){
        Grade = 'D';
       }
       else{
        Grade = 'F';
       }
        System.out.println("Total Marks is: " + totalMarks);
        System.out.println("your percentage is : " + avrage);
        System.out.println("your Grade is " + Grade);

    }
   
    
}
