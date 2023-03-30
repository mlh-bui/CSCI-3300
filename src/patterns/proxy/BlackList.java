package patterns.proxy;

import java.util.Hashtable;
import java.util.Map;

// implement like a singleton aka instantiate hashmap only once
// doesn't make sense to make a new hashmap for every time we check a website
public class BlackList {
    private static BlackList instance = null;
    private static Map<String, Integer> blackList;

    private BlackList() {
        // constructor method
        initializeBlackList();
    }

    public static synchronized boolean contains(String web) {
        // returns true if the web page is in the blacklist
        if(instance == null) {
            instance = new BlackList();
        }
        return (blackList.containsKey(web)); // if we don't get a null than the website is on the list, aka avoid it
    }

    // Adding all the forbidden websites to the list (can make this a file and read from it, etc.)
    private static void initializeBlackList() {
        blackList = new Hashtable<String, Integer>();

        blackList.put("www.bet.com", 1);
        blackList.put("www.bet2win.com", 1);
        blackList.put("www.bets4you.com", 1);
        blackList.put("www.bets4all.com", 1);
        blackList.put("www.allbets.com", 1);
        blackList.put("www.sports.com", 1);
        blackList.put("www.bestsports.com", 1);
        blackList.put("www.games.com", 1);
        blackList.put("www.games4you.com", 1);
        blackList.put("www.games4fun.com", 1);
    }
}