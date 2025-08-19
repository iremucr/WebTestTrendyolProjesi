package BaseFiles;

public class Browser {

    public static BaseBrowser prepareBrowser(){
        String browser=TestBase.BROWSER.toLowerCase();
        int value=0;

        if (browser.equals("chrome")){
            value=0;
        }else if (browser.equals("firefox")){
            value=1;
        }else if (browser.equals("edge")){
            value=2;
        }else if(browser.equals("safari")){

        }else{
            value=0;
        }


        switch (value){
            case 0:
                return new Chrome();

            default:
                return new Chrome();

        }


    }

}
