package sd.java.lab3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Lab3Application {
    Lab3Application() {}

    public static void run() {
        Scanner scanner = new Scanner(System.in);

        StringBuffer text = getInputText(scanner);
        int lengthToReplace = getInputLengthToReplace(scanner);
        StringBuffer replacement = getInputReplacement(scanner);

        StringBuffer modifiedText = replaceWordsOfLength(text, lengthToReplace, replacement);

        System.out.println("Modified Text:");
        System.out.println(modifiedText);
    }

    private static StringBuffer getInputText(Scanner scanner) {
        StringBuffer text;
        while (true) {
            try {
                System.out.print("Enter the text: ");
                text = new StringBuffer(scanner.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.err.println("Invalid input. Please enter a valid text.");
                scanner.next();
            }
        }
        return text;
    }

    private static int getInputLengthToReplace(Scanner scanner) {
        int lengthToReplace;
        while (true) {
            try {
                System.out.print("Enter the word length to replace: ");
                lengthToReplace = scanner.nextInt();

                if (lengthToReplace < 1) {
                    System.out.println("Please enter positive number.");
                }

                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid int.");
                scanner.next();
            }
        }
        return lengthToReplace;
    }

    private static StringBuffer getInputReplacement(Scanner scanner) {
        StringBuffer replacement = new StringBuffer();
        while (true) {
            try {
                System.out.print("Enter the replacement string: ");
                replacement.setLength(0);
                replacement.append(scanner.nextLine());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid string.");
                scanner.next();
            }
        }
        return replacement;
    }

    public static StringBuffer replaceWordsOfLength(StringBuffer text, int length, StringBuffer replacement) {
        if (text == null || text.isEmpty() || length <= 0 || replacement == null) {
            return text;
        }

        StringBuffer result = new StringBuffer();
        StringBuffer currentWord = new StringBuffer();
        boolean inWord = false;

        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (Character.isLetterOrDigit(currentChar)) {
                currentWord.append(currentChar);
                inWord = true;
            } else {
                if (inWord) {
                    if (currentWord.length() == length) {
                        result.append(replacement);
                    } else {
                        result.append(currentWord);
                    }
                    currentWord.setLength(0);
                    inWord = false;
                }

                result.append(currentChar);
            }
        }

        if (inWord && currentWord.length() == length) {
            result.append(replacement);
        } else if (inWord) {
            result.append(currentWord);
        }

        return result;
    }
}
