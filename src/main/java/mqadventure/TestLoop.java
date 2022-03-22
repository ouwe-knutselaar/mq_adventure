package mqadventure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestLoop {

    public static void main(String [] argv) throws FileNotFoundException {
        MqAdventureLoop mqAdventure = new MqAdventureLoop();
        mqAdventure.init();
        boolean loop = true;

        File script = new File(argv[0]);
        Scanner scanner = new Scanner(script);
        while(loop){
            System.out.println(mqAdventure.getResponse());
            String inputLine = scanner.nextLine();
            System.out.println(">"+inputLine);
            mqAdventure.sendInput(inputLine);
            if(mqAdventure.hasPlayerWon())loop=false;
        }
        System.out.println(mqAdventure.getResponse());
    }
}
