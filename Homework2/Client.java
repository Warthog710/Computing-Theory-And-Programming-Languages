import java.util.Map;

public class Client
{
    public static void main (String[] Args)
    {
        System.out.println("Hello world");
        Map135<Character, Integer> m1 = new Map135();
        Map135 m2 = m1.put('a', 1);
        Map135 m3 = m2.put('b', 2);
        Map135 m4 = m3.put('c', 3);
        Map135 m5 = m4.put('d', 4);
        Map135 m6 = m5.put('e', 5);
        Map135 m7 = m6.put('f', 20);

        //System.out.println(m3.get('d'));

        System.out.println(m5);
        System.out.println(m7);
        System.out.println(m6);

        System.out.println(m7.get('c'));
        System.out.println(m6.get('c'));

        System.out.println(m7.put('a', 50));

        System.out.println(m7);




    }
}
