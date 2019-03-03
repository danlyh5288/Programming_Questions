import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class sortofsorting_260788720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while(!(line=br.readLine()).equals("0")){
            int numNames = Integer.parseInt(line);

            String[] names = new String[numNames];
            for (int i = 0; i < names.length; i++){
                String name = br.readLine();
                names[i] = name;
            }

            Arrays.sort(names, (name1, name2) -> name1.charAt(0) == name2.charAt(0) && name1.charAt(1) == name2.charAt(1) ? 0 : name1.compareTo(name2));
            
            for(String name:names){
                System.out.println(name);
            }
            System.out.println("");
        }
        System.exit(0);
    }
}
