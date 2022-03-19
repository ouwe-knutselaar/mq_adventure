package mqadventure.data;

public class Spell extends DefaultObject {

    String response="pffft..... does nothing";

    public String getResponse() {
        return response;
    }
    public void setResponse(String response) {
        this.response = response;
    }


    @Override
    public String toString() {
        return "Spell{" +
                "response='" + response + '\'' +
                '}';
    }
}
