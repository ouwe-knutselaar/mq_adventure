package mqadventure;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MqAdventure {

    private Boolean loop = true;

    public static void main(String [] argv) throws NoSuchAlgorithmException {
      new MqAdventure();
    }

    public MqAdventure() throws NoSuchAlgorithmException {
        MqAdventureLoop mqAdventure = new MqAdventureLoop();
        mqAdventure.init();
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println(mqAdventure.getResponse());
            mqAdventure.sendInput(scanner.nextLine());
            if(mqAdventure.hasPlayerWon()){
                loop=false;
            }

        }
        System.out.println(mqAdventure.getResponse());
    }
}
