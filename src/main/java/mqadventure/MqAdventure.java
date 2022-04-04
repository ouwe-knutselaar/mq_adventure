package mqadventure;

import java.util.Scanner;

public class MqAdventure {

    private Boolean loop = true;

    public static void main(String [] argv) {
        new MqAdventure();
    }

    public MqAdventure() {
        MqAdventureLoop mqAdventure = new MqAdventureLoop();
        mqAdventure.init();
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println(mqAdventure.getResponse());
            mqAdventure.sendInput(scanner.nextLine());
            if(mqAdventure.hasPlayerWon() || mqAdventure.hasPlayerStopped()){
                loop=false;
            }

        }
        System.out.println(mqAdventure.getResponse());
    }

}
