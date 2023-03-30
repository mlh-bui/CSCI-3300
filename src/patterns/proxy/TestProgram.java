package patterns.proxy;

public class TestProgram {

    public static void main(String[] args) {
        IWebBrowser browser;

        String [] pages = {"www.slu.edu", "https://www.slu.edu/madrid/index.php", "www.bet.com", "www.bets4you.com", "www.cs.slu.edu"};

        System.out.println("Browsing pages using the web browser\n");

        browser = new WebBrowser();

        for (String page : pages)
            browser.browse(page);

        System.out.println("\nBrowsing pages using the security proxy\n");

        browser = new WebBrowserProxy();

        for (String page : pages)
            browser.browse(page);

    }

}