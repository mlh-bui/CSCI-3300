package patterns.proxy;

public class WebBrowserProxy implements IWebBrowser {
    WebBrowser browser = new WebBrowser();

    @Override
    public void browse(String web) {
        if (BlackList.contains(web))
            System.out.println("Page " + web + " is forbidden!");
        else
            this.browser.browse(web);
    }

}