import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuizApp {
    private List<Question> questions;
    private int score;
    private int questionIndex;

    public QuizApp() {
        questions = new ArrayList<>();
        score = 0;
        questionIndex = 0;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void startQuiz() {
        displayQuestion(questions.get(questionIndex));
    }

    private void displayQuestion(Question question) {
        System.out.println(question.getQuestion());
        for (int i = 0; i < question.getOptions().size(); i++) {
            System.out.println((i + 1) + ". " + question.getOptions().get(i));
        }
        System.out.print("Enter your answer (1-" + question.getOptions().size() + "): ");
    }

    public void submitAnswer(int answer) {
        Question currentQuestion = questions.get(questionIndex);
        if (answer == currentQuestion.getCorrectAnswer()) {
            score++;
        }
        questionIndex++;
        if (questionIndex < questions.size()) {
            displayQuestion(questions.get(questionIndex));
        } else {
            System.out.println("Quiz complete! Your score is " + score + "/" + questions.size());
        }
    }

    public static void main(String[] args) {
        QuizApp quiz = new QuizApp();

        // Add questions to the quiz
        quiz.addQuestion(new Question("What is the capital of France?", new String[] {"Paris", "London", "Berlin", "Rome"}, 0));
        quiz.addQuestion(new Question("What is the largest planet in our solar system?", new String[] {"Earth", "Saturn", "Jupiter", "Uranus"}, 2));
        quiz.addQuestion(new Question("What is the smallest country in the world?", new String[] {"Vatican City", "Monaco", "Nauru", "Tuvalu"}, 0));

        // Start the quiz
        quiz.startQuiz();

        // Get user input
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int answer = scanner.nextInt();
            quiz.submitAnswer(answer);
        }
    }
}

class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return Arrays.asList(options);
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }
}