package patterns.proxy;

public class WebBrowser implements IWebBrowser {

    @Override
    public void browse(String web) {
        System.out.println("Browsing " + web);
    }

}