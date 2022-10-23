import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

interface GameFunctions
{
    boolean isEnd();
    int CalculateScore();
    void SavetoFile(String data);
    void LeaderBoard(String data);
}

class Game
{
    String UsedLetters;
    String language;
    public Game()
    {
        this.UsedLetters="";
        this.language="PL";
    }

    public Game(int language)
{
    this.UsedLetters="";

        switch (language) {
            case 1 -> this.language="PL";
            case 2 -> this.language="ENG";
            default -> throw new IllegalArgumentException("Wrong Language!");
        }

}
    public String getUsedLetters()
    {
        return UsedLetters;
    }
    public String getLanguage()
    {
        return language;
    }
}

class Hangman extends Game implements GameFunctions
{
    int characters,lives,category;
    String name;
    boolean[] isGuessed;

    public Hangman(int language, int category)
    {
        super(language);
        this.category=category;
        this.lives=6;
        SetWordAutomatically();
    }

    public Hangman(String word)
    {
        SetWordManually(word);
        this.lives=6;
    }

    public void SetWordManually(String name)
    {
        this.name=name;
        this.characters=name.length();
        this.isGuessed=new boolean[name.length()];
        RemoveUnguessable();
    }

    public void SetWordAutomatically()
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader("words"+super.getLanguage()+".txt"));
            String data; int current=1;                                                  //Opens a notepad with 100 example words
            data="none";

            int line_number=(int)Math.floor(Math.random()*21+(category-1)*20);

            while(data!=null&&line_number>current)                                                       //Generates a random line number and then gets data from this line
            {
                data=br.readLine();
                current++;
            }
            br.close();
            this.name=data;                                                                     //Closes the input stream and sets the word's name and length from data
            this.characters=data.length();
            this.isGuessed=new boolean[data.length()];
            RemoveUnguessable();
        }catch(IOException e){System.out.println("Either file words"+language+".txt not Found or an unexpected error happened!");}
    }

    public void RemoveUnguessable()
    {
        for(int i=0;i<this.isGuessed.length;i++)
        {
            if(this.name.charAt(i)==' '||this.name.charAt(i)=='-'||this.name.charAt(i)=='\n')
            {
                this.isGuessed[i]=true;
            }
        }
    }

    public boolean Guess(String guess)           //Function responsible for guessing
    {
        if(guess.equals("0"))
        {
            Forfeit();lives=0;return true;
        }
        if(guess.equals("7"))
        {
            isGuessed[0]=true;isGuessed[1]=true;isGuessed[2]=true; return true;
        }               //Specific cases while guessing. Helps and surrender
        if(guess.equals("8"))
        {
            lives++;
                return true;
        }
        if(guess.equals("9"))
        {
            lives=6; return true;
        }

        boolean Guessed=false;
        this.UsedLetters+=guess.toUpperCase()+",";

        if(guess.length()>1)
        {
            if(name.equalsIgnoreCase(guess))                           //Checks if you gussed a word and it is correct
            {
                Forfeit();Guessed=true;
            }
        }
        if(guess.length()==1)
        {
            for(int i=0;i<name.length();i++)
            {
                if(name.toUpperCase().charAt(i)==guess.toUpperCase().charAt(0))          //Checks if you guessed a letter nd it is correct
                {
                    Guessed=true;
                    isGuessed[i]=true;
                }
            }

        }
        return Guessed;
    }
    public boolean isEnd()
    {
        if(lives==0)return true;                                        //Checks if player is out of lives
        for(boolean b : isGuessed) if(!b) return false;                 //Checks if every character has been guessed
        return true;
    }
    public int CalculateScore()
    {
        return (lives)*name.length();
    }
    public void SavetoFile(String data)                     //Function responsible for Saving data to file
    {
        try {
            File inputFile = new File("PlayerInfo.txt");
            File tempFile = new File("tmp.txt");
                                                                                                    //Preparing I/O to file
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String[] ProperData = data.split(",");      //Splits data that needs to be saved into better format

            boolean ProperPlayer=false;             //boolean is true when player is found in txt file

            String currentLine;

            while ((currentLine = reader.readLine()) != null)
            {
                String[] Line = currentLine.split(",");     //Gets data from file and changes it into better format

                if (Line[0].equals(ProperData[0]))
                {
                    writer.write(ProperData[0]+","+(Integer.parseInt(ProperData[1])+Integer.parseInt(Line[1]))+","+(Integer.parseInt(ProperData[2])+Integer.parseInt(Line[2]))+","+(Integer.parseInt(ProperData[3])+Integer.parseInt(Line[3]))+"\n");
                    ProperPlayer=true;              //Player has been found, data has been changed
                    continue;
                }
                writer.write(currentLine+"\n");
            }
            if(!ProperPlayer)       //If there was no player found, create a new one
            {
                writer.write(ProperData[0]+","+ProperData[1]+","+ProperData[2]+","+ProperData[3]);
            }

            writer.close();         //Close the I/O
            reader.close();
            boolean a = inputFile.delete();
            boolean b = tempFile.renameTo(inputFile);       //All data has been saved into new file that has been renamed in order to imitate the previous one
        } catch(Exception e){System.out.println("File not Found");}
    }

    public void LeaderBoard(String text)        //Function responsible for printing Leaderboard
    {
        try {
            File inputFile = new File("PlayerInfo.txt");
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));      //Open output

            boolean isScoreHigher=false;
            String currentLine;
            String[] Top3 = {"None,0,0,0","None,0,0,0","None,0,0,0"};               //Default top 3 players
            String[] Help;


            while ((currentLine = reader.readLine()) != null)       //Generally same logic applies just like in SavetoFile function
            {
                String[] Line = currentLine.split(",");
                if (Line[0].equals(text))
                {
                    System.out.println("Your Score "+Line[0]+" is "+Line[3]+" with "+Line[1]+" wins and "+Line[2]+" losses.");
                }
                for(int i=0;i<3;i++) {                         //Loop responsible for comparing best 3 sccores to current line's score
                    Help = Top3[i].split(",");
                    if(!isScoreHigher)                      //If this player had already been in  higer position on top 3, dont check again
                    {
                    if (Integer.parseInt(Help[3]) <= Integer.parseInt(Line[3]))
                    {
                        if(i<2)
                        {
                            Top3[2]=Top3[1];
                        }
                        if(i<1)                                         //Those are checks and changes depending on the place where this score should be in the top3
                        {
                            Top3[1]=Top3[0];
                        }
                        Top3[i] = Line[0] + "," + Line[1] + "," + Line[2] + "," + Line[3];
                        isScoreHigher=true;
                    }
                    }
                }
                isScoreHigher=false;
            }
            reader.close();
            System.out.println("|LEADERBOARD| Name,Wins,Loses,Accumulated Points\n1."+Top3[0]+"\n2."+Top3[1]+"\n3."+Top3[2]);       //Prints down the leaderboard

        }catch(Exception e){System.out.println("File not found!");}
    }

    public String Forfeit()                             //Function used in case of surrendering or guessing whole name properly
    {
        Arrays.fill(isGuessed, true);
        return name;
    }

    public int getLives()
    {
        return lives;
    }

    public boolean[] getIsGuessed()
    {
        return isGuessed;
    }

    public String getName()
    {
        return name;
    }
    public int getCategory()
    {
        return category;
    }
    public void LoseLife()
    {
        this.lives--;
    }
}

public class Project {

    public static void main(String[] args)
    {
    Hangman word;                                                                  //Setup stuff
    Scanner sc = new Scanner(System.in);

    System.out.print("Type 1 for polish word\nType 2 for english word\nType 0 midgame to surrender the game\nYou can also type down a Custom word:");               //Make a choice
    String input = sc.nextLine();

    if(isNumeric(input))
    {

        System.out.println("\nAvailable Categories:\n[1]Animals\n[2]Occupations\n[3]Adjective\n[4]Cities\n[5]Vegetables and Fruits\nYour choice:");
        int choice=sc.nextInt();
        if((Integer.parseInt(input)>0&&Integer.parseInt(input)<3)&&(choice>0&&choice<6))
        {
            word = new Hangman(Integer.parseInt(input),choice);
        }
        else
        {
            System.out.println("\nWrong input! Initializing defaults\n");
            word = new Hangman(1,1);
        }

    }                                         //Calls a constructor with a random word from language
    else
    {
        word = new Hangman(input);
    }                                                           //Calls a constructor with a selected word
        sc.nextLine();
    while(!word.isEnd())
    {
        for (int j = 0; j < 50; ++j) System.out.println();              //Empty space generator
        Draw(word);
        if (!word.Guess(sc.nextLine()))                                 //If a guess is not correct, life is lost
        {
            word.LoseLife();
        }
    }
        Draw(word);
    int score=word.CalculateScore();
    System.out.println("=======================\nWord:"+word.Forfeit());
    System.out.println("Score:"+score);
    System.out.print("May I ask for your name?:");
    String name=sc.next();

    if(score==0)                                                //Score is 0 when a word has not been guessed
    {
        word.SavetoFile(name+",0"+",1,"+score);            //If there is no score, save it as a lose
    }
    else
    {
        word.SavetoFile(name+",1"+",0,"+score);             //If there is a score, save it as a win
    }
    word.LeaderBoard(name);
    }

    public static boolean isNumeric(String data)                //Function that checks if a String is parsable to Int
    {
        if (data == null) {
            return false;
        }
        try {
            Integer.parseInt(data);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static void Draw(Hangman w)                         //Function that is responsible for drawing hangman
    {
        System.out.println("Available Helps:\n[7]Show me first three chars\n[8]-Dont look at Last move\n[9]Reassemble me!");
        System.out.print("   ___\n");
        System.out.print("  |   |\n");
    System.out.print("  |");
    if(w.getLives()<6){System.out.println("   O");}else         //Line 1
    {System.out.println();}

    System.out.print("  |");
    if(w.getLives()<3){System.out.println("  /|\\");}else
    if(w.getLives()<4){System.out.println("  /|");}else         //Line 2
    if(w.getLives()<5){System.out.println("   |");}else
    {System.out.println();}

    System.out.print("  |");
    if(w.getLives()<1){System.out.println("  / \\");}else       //Line 3
    if(w.getLives()<2){System.out.println("  /");}else
    {System.out.println();}

        System.out.println(" _|_");
        System.out.println("|   |______");
        System.out.println("|          |");
        System.out.print("|__________|         Category:");
        if(w.getCategory()==1){System.out.print("Animals\n");}
        if(w.getCategory()==2){System.out.print("Occupations\n");}
        if(w.getCategory()==3){System.out.print("Adjectives\n");}
        if(w.getCategory()==4){System.out.print("Cities\n");}
        if(w.getCategory()==5){System.out.print("Fruits and Vegetables\n");}

    for(int i=0;i<w.getIsGuessed().length;i++)
    {
        if(!w.getIsGuessed()[i]){System.out.print('_');}                     //Prints empty space if a letter is not guessed
        if(w.getIsGuessed()[i]){System.out.print(w.getName().charAt(i));}         //Prints a letter if it is guessed
    }
    System.out.println("\nYour guesses:"+w.getUsedLetters());                   //Prints already used letters
    }


}
