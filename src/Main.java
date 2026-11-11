//VERSIONE 1.0

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static java.lang.Character.toLowerCase;

void main() {

    Random random = new Random();

    String[] Words = {
            "BULLET", "BUTTER", "BUCKET", "BASKET",

            "BURGER", "BURNER", "BUTLER", "BUFFET",

            "HOUSE", "MOUSE", "HORSE", "HOOSE",

            "TRAIN", "BRAIN", "GRAIN", "DRAIN",

            "PLANT", "PLAIN", "PLATE", "BLADE"
    };

    String[] Symbols = {"!", "<", ">", "?", "&", "$", "@", "#", "[", "]"};

    ArrayList<Character> Excluded = new ArrayList<>();

    ArrayList<String> ToShow = new ArrayList<>();


    String input = "empty";
    String wordToWin = Words[random.nextInt(Words.length)];
    int similarity = 0;
    boolean win = false;
    int trys = 4;


    IO.println();

    IO.println(" Welcome to STAR Star Industries And Robotic");

    IO.println("   PASSWORDS REQUIRED");

    PrintWords(Words, Symbols);

    while(trys > 0) {

        input = AskInput(input);

        win = CheckWin(win, input, wordToWin);

        if(win){
            break;
        }

        trys = SubtractTrys(win, trys);

        PrintWords(Words, Symbols);

        similarity = CalcSimilarity(similarity, input, wordToWin, Excluded);

        ShowAlreadyWords(input, ToShow);

        PrintTrys(trys);

    }
}

public static void PrintWords(String[] Words, String[] Symbols) {

    Random random = new Random();

    IO.println();

    for(int i = 0; i < Words.length; i++){

        String randomSymbolsBefore = "";
        String randomSymbolsAfter = "";

        int numChar = random.nextInt(15-Words[i].length());

        for(int j = 0; j < numChar; j++){

            randomSymbolsBefore += Symbols[random.nextInt(Symbols.length)];

        }

        for(int j = 0; j < 15-Words[i].length()-numChar; j++){

            randomSymbolsAfter += Symbols[random.nextInt(Symbols.length)];

        }

        IO.print(" 00x02");

        if(i/10 < 1){
            IO.print("0");
        }

        if(i % 2 == 1){
            IO.println(i + randomSymbolsBefore + "[" + Words[i] + "]" + randomSymbolsAfter);
        }
        else{
            IO.print(i + randomSymbolsBefore + "[" + Words[i] + "]" + randomSymbolsAfter);
        }

    }

    IO.println();
}

public static void PrintTrys(int trys) {

    IO.print("   ATTEMPTS: ");

    for(int j = 0; j < trys; j++){

        IO.print("█ ");
    }

    IO.println();

    IO.println();

    if(trys < 1){

        IO.println("   ACCESSO PERMANENTEMENTE BLOCCATO");
    }
}

public static String AskInput(String input) {

    Scanner scanner = new Scanner(System.in);

    IO.println(" --- - ----INPUT---- - ---");

    IO.println(" INPUT: ");

    input = scanner.next();

    IO.println();

    return input;
}

public static int CalcSimilarity(int similarity, String input, String wordToWin, ArrayList<Character> Excluded) {

    ArrayList<Character> InputList = new ArrayList<>();
    ArrayList<Character> WinList = new ArrayList<>();

    InputList.removeAll(InputList);
    WinList.removeAll(WinList);

    similarity = 0;

    for(int i = 0; i < input.length(); i++){

        InputList.add(input.charAt(i));
    }

    for(int i = 0; i < wordToWin.length(); i++){

        WinList.add(wordToWin.charAt(i));
    }

    for(int c = 0; c < InputList.size(); c++){

        for(int d = 0; d < WinList.size(); d++){

            if(toLowerCase(InputList.get(c)) == toLowerCase(WinList.get(d))){

                similarity++;

                WinList.remove(WinList.get(d));

                break;
            }
        }
    }

    IO.println(" La somiglianza di " + input.toUpperCase() + " è " + similarity);

    IO.println("   ACCESS DENIED");

    IO.println();

    return similarity;
}

public static void ShowAlreadyWords(String input, ArrayList<String> ToShow){

    ToShow.add(input);

    IO.print(" Parole gia Inserite:");

    for(String w : ToShow){

        IO.print(" " + w.toUpperCase());
    }

    IO.println();
}

public static boolean CheckWin(boolean win, String input, String wordToWin){

    if(input.equalsIgnoreCase(wordToWin)){

        win = true;

        IO.println(" -- --GIOCO CONCLUSO-- --");
        IO.println("   Access Granted");
    }

    return win;
}

public static int SubtractTrys(boolean win, int trys){

    if(!win){

        trys -= 1;
    }

    return trys;
}
