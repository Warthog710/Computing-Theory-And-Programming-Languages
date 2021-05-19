import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * Static utility methods for parsing a list of String tokens representing a
 * simple mathematical prefix expression and for evaluating the resulting value.
 * The tokens must adhere to the grammar E -> +EE|*EE|0|...|9. The result
 * of parsing is a parse tree. The resulting parse tree can be passed to an
 * evaluation method which returns an int.
 *
 * For example calling parsePrefixExpr on the List ["*","+","2","3","4"]
 * results in a parse tree, and calling eval on that parse tree results in 20.
 * 
 * @author J Doe, 1234 (parseE and eval)
 * @author T Krovetz (starter code)
 * @version April 30, 2020
 */
public class Hw5 {

    private static LinkedList<String> toks;

    private static boolean isEmpty() { return toks.isEmpty(); }
    private static String  next()    { return toks.element(); }

    private static void match(String tok) {
        if (isEmpty() || !next().equals(tok))
            throw new IllegalStateException("Error on match(" + tok + ")");
        toks.remove();
    }

    public static void printLeaves(TreeNode noderef) {
        if (noderef.getChildCount()==0) {           // noderef is a leaf node
            String label = noderef.toString();
            System.out.print(label);
        } else {
            for (int i=0; i<noderef.getChildCount(); i++) {
                printLeaves(noderef.getChildAt(i));
            }
        }
    }

    // Another example showing that you can return things during your walk and
    // how to access the node as a DefaultMutableTreeNode if you want
    public static String toString(TreeNode noderef) {
        DefaultMutableTreeNode dmtn = (DefaultMutableTreeNode)noderef;
        if (dmtn.getChildCount()==0) {                // leaf node
            return (String)(dmtn.getUserObject());    // the Object is a String
        } else {
            StringBuilder result = new StringBuilder();
            for (int i=0; i<dmtn.getChildCount(); i++) {
                result.append(toString(dmtn.getChildAt(i)));
            }
            return result.toString();
        }
    }

    /**
     * Receives a reference to the root of a tree created by parseE and
     * computes the integer result that the tree represents.
     * 
     * @param noderef tree created by parseE
     * @return result of evaluating tree rooted at noderef
     */
    public static int eval(TreeNode noderef) {
        // Note: Because DefaultMutableTreeNode implements the TreeNode
        // interface, DefaultMutableTreeNode objects can be passed to eval.
        // If DefaultMutableTreeNode methods are needed, cast noderef to
        // DefaultMutableTreeNode (ie, DefaultMutableTreeNode dmtn =
        // (DefaultMutableTreeNode)noderef;). This is safe because all objects
        // created by parseE are DefaultMutableTreeNode objects.
        
        //If I have 1 child
        if (noderef.getChildCount() == 1)
        {
            return Integer.parseInt(noderef.getChildAt(0).toString());
        }
        
        //If I have 3 children
        else
        {
            if (noderef.getChildAt(0).toString().equals("+")) 
            {
                return eval(noderef.getChildAt(1)) + eval(noderef.getChildAt(2));
            }
            else 
            {
                return eval(noderef.getChildAt(1)) * eval(noderef.getChildAt(2));
            }
        }
    }

    /**
     * Uses toks as a source of String tokens to parse E -> +EE|*EE|0|...|9.
     * Upon success returns a reference to the root of the resulting parse tree.
     * Each node in the tree is a DefaultMutableTreeNode object with a String
     * UserObject representing the node's data. All nodes with data "+", "*",
     * "0", ..., "9" are leaf nodes (ie, have 0 children) and all leaf nodes
     * have one of these data. All interior nodes have data "E" and either 1 or
     * 3 children. If it has 1 child, the child is a leaf node with data "0",
     * ..., "9". If it has 3 children, the first child is a leaf with data "+" or
     * "*" and the other two children are nodes with data "E".
     *
     * Note that calling printLeaves on the resulting tree should print the
     * parsed tokens in the same order they appeared in the token stream. So,
     * if toks contains ["*","+","2","3","4"] then printLeaves(parseE())
     * should print "*+234".
     * 
     * @return reference to root of parse tree representing result of this parse
     * @throws IllegalStateException if an error occurs parsing toks
     */
    public static DefaultMutableTreeNode parseE() {
        //Create new node to be returned
        DefaultMutableTreeNode rval = new DefaultMutableTreeNode("E");

        if (isEmpty())
        {
            throw new IllegalStateException("Unexpected Input");
        }
        
        if(next().equals("+") || next().equals("*"))
        {
            String tok = next();
            match(tok);
            rval.add(new DefaultMutableTreeNode(tok));
            rval.add(parseE());
            rval.add(parseE());
        }
        else if(next().equals("0") || next().equals("1") || next().equals("2") || next().equals("3") || next().equals("4") || next().equals("5") || next().equals("6") || next().equals("7") || next().equals("8") || next().equals("9"))
        {
            String tok = next();
            match(tok);
            rval.add(new DefaultMutableTreeNode(tok));
        }
        else
        {
            throw new IllegalStateException("Unexpected: " + next());
        }
        
        return rval;
    }
    
    /**
     * Uses exp as a source of String tokens to parse E -> +EE|*EE|0|...|9.
     * Upon success returns a reference to the root of the resulting parse tree
     * whose structure is described in the parseE documentation. If all of exp
     * is consumed the tree is returned, otherwise an exception is thrown.
     *
     * @param exp list of tokens to be parsed (only "+","*","0",..."9" allowed)
     * @return reference to root of parse tree representing result of this parse
     * @throws IllegalStateException if an error occurs parsing exp
     */
    public static DefaultMutableTreeNode parsePrefixExpr(List<String> exp) {
        toks = new LinkedList<String>(exp);
        DefaultMutableTreeNode root = parseE();
        if (isEmpty())
            return root;
        else
            throw new IllegalStateException("Excessive input");
    }
    
    /** For your use during testing */
    public static void main(String[] args) {
        DefaultMutableTreeNode root = parsePrefixExpr(Arrays.asList("*", "+", "2", "3", "4"));
        System.out.println(isEmpty() ? "accept" : "reject");
        System.out.println(toString(root));
        printLeaves(root);
        System.out.println();
        System.out.println(eval(root));
    }

}