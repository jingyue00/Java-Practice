package edu.nyu.cs9053.homework8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static edu.nyu.cs9053.homework8.ValidTextKeyPress.Eight;
import static edu.nyu.cs9053.homework8.ValidTextKeyPress.Four;
import static edu.nyu.cs9053.homework8.ValidTextKeyPress.Seven;
import static edu.nyu.cs9053.homework8.ValidTextKeyPress.Two;

/**
 *
 * Created by yuejing on 4/10/18.
 */
public class TestCase {
    public static void main(String[] args) {
        TextingDictionary dictionary = new TextingDictionary();
        try {
            dictionary.insert(null);
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("Input:%s, %s", null, e.getMessage()));
        }

        try {
            dictionary.insert("");
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("Input:%s, %s", "", e.getMessage()));
        }

        try {
            dictionary.insert("jf4");
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("Input:%s, %s", "jf4", e.getMessage()));
        }

        dictionary.insert("Brian");
        dictionary.insert("brian");
        dictionary.insert("bribn");
        dictionary.insert("aricn");
        dictionary.insert("brain");
        dictionary.insert("braid");
        List<ValidTextKeyPress> test1 = new ArrayList<>();
        List<String> res0 = dictionary.search(test1);
        System.out.println(res0.isEmpty());

        List<String> res1 = dictionary.search(Arrays.asList(Two, Seven));
        res1.forEach(s -> System.out.print(s + ","));
        System.out.println("");

        List<String> res2 = dictionary.search(Arrays.asList(Two, Seven, Two));
        res2.forEach(s -> System.out.print(s + ","));
        System.out.println("");

        List<String> res3 = dictionary.search(Arrays.asList(Two, Seven, Four));
        res3.forEach(s -> System.out.print(s + ","));
        System.out.println("");

        List<String> res4 = dictionary.search(Arrays.asList(Two, Eight));
        res4.forEach(s -> System.out.print(s + ","));
        System.out.println("");

        dictionary.insert("butte");

        List<String> res5 = dictionary.search(Arrays.asList(Two, Eight));
        res5.forEach(s -> System.out.print(s + ","));
        System.out.println("");
    }
}
