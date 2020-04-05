package connection;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerCommand {
    public  String analyse(String messagek){
        String message="";

        return message;
    }

    public ArrayList<String> GetPlayersList(String Command){
        ArrayList<String> array = new ArrayList<String>();
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(Command);
        while (m.find()) {
            array.add(m.group(1));
        }
        return array;
    }
    public ArrayList<String> GetGameList(String Command){
        ArrayList<String> array = new ArrayList<String>();
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(Command);
        while (m.find()) {
            array.add(m.group(1));
        }
        return array;
    }
    public int getTheMovementIndex(String command){
        int index=0;
        Pattern p = Pattern.compile("\"([^\"]*)\"");
        Matcher m = p.matcher(command);
        while (m.find()) {
            System.out.println(m.matches());

            index=Integer.parseInt(m.group(0));
            System.out.println(m.matches());

        }

        return index;
    }

}
