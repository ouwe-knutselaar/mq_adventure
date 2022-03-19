package mqadventure;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class TestLoop {

    public static void main(String [] argv) throws FileNotFoundException, NoSuchAlgorithmException {
        MqAdventureLoop mqAdventure = new MqAdventureLoop();
        mqAdventure.init();
        boolean loop = true;

        File script = new File("D:\\onze_projecten\\MqAdventure\\src\\main\\resources\\script.txt");
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
