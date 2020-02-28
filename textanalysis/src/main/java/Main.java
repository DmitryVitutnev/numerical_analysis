import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        List<Integer> values = new ArrayList<Integer>();
        Character[] chars = {'й','ц','у','к','е','н','г','ш','щ','з','х','ъ','ф','ы','в',
                'а','п','р','о','л','д','ж','э','я','ч','с','м','и','т','ь','б','ю'};
        List<Character> symbols = new ArrayList<Character>(Arrays.asList(chars));
        List<Character> newSymbols = new ArrayList<Character>(symbols);
        Collections.shuffle(newSymbols);

        Scanner scanner = new Scanner(new BufferedInputStream(new FileInputStream(new File("input.txt"))));
        PrintWriter printer = new PrintWriter(new File("output.txt"));


        for(int i = 0; i < 32; i++) {
            values.add(0);
        }
        char[] currents;
        char cur;
        int index;
        while (scanner.hasNext()) {
            currents = scanner.next().toCharArray();
            for(int i = 0; i < currents.length; i++) {
                cur = currents[i];
                index = symbols.indexOf(cur);
                if(index != -1) {
                    values.set(index, values.get(index) + 1);
                    printer.print(newSymbols.get(index));
                } else {
                    printer.print(cur);
                }

            }
        }



        for(int i = 0; i < 32; i++) {
            System.out.println("" + symbols.get(i) + " : " + values.get(i) + " : " + newSymbols.get(i));
        }







    }


}
