/* DFA simulator for CSC 135 by Ted Krovetz <tdk@csus.edu>, 2019.
   
   This simulates a DFA that has been specified in the format given
   in the homework assignment. It should work correctly if the DFA
   file is formatted correctly and the DFA is legal, but has undefined
   behavior if it is not. The program tries to open two files that should
   be in the same directory as the program: "dfa.txt" and "strings.txt". It
   runs the simulated DFA for each line in "strings.txt" and outputs
   "Accept" or "Reject" for each.
*/
import java.util.*;
import java.io.*;

public class Dfa {
    public static void main(String[] args) throws FileNotFoundException {
        // Open dfa file
        Scanner dfa = new Scanner(new File("dfa.txt"));
        
        // Read number of states in dfa
        int states = getNextLineAsScanner(dfa).nextInt();
        // Read alphabet symbols into a set
        Set<String> alpha = new HashSet<>();
        Scanner line = getNextLineAsScanner(dfa);
        while (line.hasNext())
            alpha.add(line.next());
        // Read accept state ints into a set
        Set<Integer> accept = new HashSet<>();
        line = getNextLineAsScanner(dfa);
        while (line.hasNextInt())
            accept.add(line.nextInt());
        // Build transition table. Should be (|states| x |alpha|) lines
        List<Map<String,Integer>> transition = new ArrayList<>();
        for (int i=0; i < states; i++)
            transition.add(new HashMap<String,Integer>());
        for (int i=0; i < states * alpha.size(); i++) {
            line = getNextLineAsScanner(dfa);
            int src = line.nextInt();
            String sym = line.next();
            int dst = line.nextInt();
            transition.get(src).put(sym, dst);
        }
        
        Scanner strings = new Scanner(new File("strings.txt"));
        while (strings.hasNextLine()) {
            String input = strings.nextLine();
            System.out.println("Input: " + input);
            int curState = 0;
            for (int i=0; i<input.length(); i++) {
                curState = transition.get(curState).get(input.substring(i,i+1));
            }
            if (accept.contains(curState))
                System.out.println("Accept");
            else
                System.out.println("Reject");
        }
    }
    
    public static Scanner getNextLineAsScanner(Scanner in) {
        while (in.hasNextLine()) {
            String s = in.nextLine();
            if (s.length() == 0 || s.charAt(0) != '#')
                return new Scanner(s);
        }
        return null;
    }

}
