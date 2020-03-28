import java.io.*;
import java.util.*;

public class Main {



    public static void main(String[] args) throws IOException {

        PrintWriter printWriter = new PrintWriter(new FileWriter(new File("deviation.csv")));

        for (int i = 1; i < 50; i++) {
            printWriter.println(""+i+";"+cipher(i));
            printWriter.flush();
        }

    }

    public static long cipher(int K) throws IOException {
        Character[] chars = {'й','ц','у','к','е','н','г','ш','щ','з','х','ъ','ф','ы','в',
                'а','п','р','о','л','д','ж','э','я','ч','с','м','и','т','ь','б','ю','ё'};
        List<Character> symbols = new ArrayList<Character>(Arrays.asList(chars));

        Reader reader = new BufferedReader(new FileReader(new File("input.txt")));
        PrintWriter printer = new PrintWriter(new File("output.txt"));

        int[] values = new int[symbols.size()];
        for(int i = 0; i < symbols.size(); i++) {
            values[i] = 0;
        }

        char[] reserve = new char[K];
        char[] currents = new char[K+1];
        int r;
        char ch;
        for(int i = 0; i < K; i++) {
            while(true) {
                r = reader.read();
                ch = (char)r;
                if(symbols.contains(ch)) {
                    break;
                }
            }
            reserve[i] = ch;
            currents[i] = ch;
        }

        while((r = reader.read()) != -1) {
            ch = Character.toLowerCase((char) r);
            if(!symbols.contains(ch)) {
                continue;
            }
            currents[K] = ch;

            int newIndex = 0;
            for(int i = 1; i < K + 1; i++) {
                newIndex += symbols.indexOf(currents[i]);
            }
            newIndex %= symbols.size();
            printer.print(symbols.get(newIndex));
            values[newIndex]++;

            for(int i = 0; i < K; i++) {
                currents[i] = currents[i + 1];
            }
        }

        int count = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        double deviation = 0.;

        for(int i = 0; i < symbols.size(); i++) {
            count += values[i];
            if(values[i] < min) {
                min = values[i];
            }
            if(values[i] > max) {
                max = values[i];
            }
        }
        double average = ((double) count)/symbols.size();

        for(int i = 0; i < symbols.size(); i++) {
            //System.out.printf("%c: %d %3f\n", symbols.get(i), values[i], values[i]*100./count);
            deviation += (values[i] - average) * (values[i] - average);
        }

        return Math.round(Math.sqrt(deviation));
    }


}
