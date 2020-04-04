package connection;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerCommand {

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

}
