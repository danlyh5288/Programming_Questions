import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class natjecanje {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int numDamaged;

        while(!(line = br.readLine()).equals(" ")){
            String[] info = line.split(" ");
            int length = Integer.parseInt(info[0]);

            numDamaged = Integer.parseInt(info[1]);
            String[] damagedIndex = br.readLine().split(" ");
            String[] reserveIndex = br.readLine().split(" ");

            HashMap<String, Integer> map = new HashMap<>();
            for(int i = 0; i < damagedIndex.length; i++){
                map.put(damagedIndex[i], -1);
            }
            for(int j = 0; j < reserveIndex.length; j++){
                if(map.containsKey(reserveIndex[j]) && map.get(reserveIndex[j])==-1){
                    numDamaged--;
                    map.remove(reserveIndex[j]);
                }
                else{
                    map.put(reserveIndex[j], 1);
                }
            }

            for(int i = 1; i <= length; i++){
                String index = Integer.toString(i);
                if(map.containsKey(index) && map.get(index)==-1){
                    if(i == 1){
                        String rightPos = Integer.toString(i+1);
                        if(map.containsKey(rightPos)&&map.get(rightPos)==1){
                            numDamaged--;
                            map.remove(index);
                            map.remove(rightPos);
                        }
                    }
                    else if(i == length){
                        String leftPos = Integer.toString(i-1);
                        if(map.containsKey(leftPos)&&map.get(leftPos)==1){
                            numDamaged--;
                            map.remove(index);
                            map.remove(leftPos);
                        }
                    }
                    else{
                        String leftPos = Integer.toString(i-1);
                        String rightPos = Integer.toString(i+1);
                        if (map.containsKey(leftPos) && map.get(leftPos)==1){
                            numDamaged--;
                            map.remove(index);
                            map.remove(leftPos);
                        }
                        else if(map.containsKey(rightPos)&&map.get(rightPos)==1){
                            numDamaged--;
                            map.remove(index);
                            map.remove(rightPos);
                        }
                    }
                }
            }
            System.out.println(numDamaged);
            System.exit(0);
        }
    }
}
