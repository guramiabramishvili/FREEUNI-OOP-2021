// TabooTest.java
// Taboo class tests -- nothing provided.

import java.util.*;

import junit.framework.TestCase;

public class TabooTest extends TestCase {


    public void testFollow1(){
        List<String> tab = Arrays.asList("a", "b", "a", "c", "a", "k", "d","L","d","&","d"," ","m",null,"l");
        Taboo<String> taboo = new Taboo<>(tab);

        Set<String> test1 = new HashSet<>(Arrays.asList("b", "c", "k"));

        assertEquals(test1,taboo.noFollow("a"));

        Set<String> test2 = new HashSet<>(Arrays.asList("L", "&"," "));

        assertEquals(test2,taboo.noFollow("d"));

        Set<String> test3 = new HashSet<>();
        test3.add(null);
        /*
        Set<String> temp;
        temp = taboo.noFollow("m");
        for(Iterator<String> it=temp.iterator(); it.hasNext();){
            System.out.print(it.next());
        }*/
        assertEquals(test3,taboo.noFollow("m"));

    }

    public void testReduce1(){
        List<String> tab = Arrays.asList("a", "b", "a", "c", "a", "k", "d","L","d","&","d"," ","m",null,"l","l");
        Taboo<String> taboo = new Taboo<>(tab);

        List<String> test1 = new ArrayList<>(Arrays.asList("a", "b", "a", "c", "k", "d"));
        taboo.reduce(test1);
        assertEquals(test1,Arrays.asList("a", "a", "d"));

        List<String> test2 = new ArrayList<>(Arrays.asList("l", "l", "m", null,"d"," ", "&"));
        taboo.reduce(test2);
        assertEquals(test2,Arrays.asList("l", "m",null,"d"));

        List<String> test3 = new ArrayList<>(Arrays.asList("a", "b", "c", "k"));
        taboo.reduce(test3);
        assertEquals(test3,Arrays.asList("a"));

    }

    public void testReduce2(){
        List<String> tab = Arrays.asList("");
        Taboo<String> taboo = new Taboo<>(tab);

        List<String> test1 = new ArrayList<>(Arrays.asList("a", "b", "a", "c", "k", "d"));
        taboo.reduce(test1);

        assertEquals(test1,(Arrays.asList("a", "b", "a", "c", "k", "d")));

        List<String> test2 = new ArrayList<>(Arrays.asList(""));
        taboo.reduce(test2);
        assertEquals(test2,(Arrays.asList("")));
    }

    public void testReduce3(){
        List<String> tab = Arrays.asList("x");
        Taboo<String> taboo = new Taboo<>(tab);

        List<String> test1 = new ArrayList<>(Arrays.asList("x"));
        taboo.reduce(test1);
        assertEquals(test1,(Arrays.asList("x")));
    }

}
