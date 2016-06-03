package servlet;

/**
 * Created by root on 02/06/16.
 */
public class Test {
    static enum A{A}
    public static void main(String[] args)
    {
        try{
            String url =
                    "https%3A%2F%2Fmywebsite%2Fdocs%2Fenglish%2Fsite%2Fmybook.do" +
                            "%3Frequest_type%3D%26type%3Dprivate";

            System.out.println(url+"Hello World!------->" +
                    new String(url.getBytes("UTF-8"),"ASCII"));
            System.out.println(java.net.URLDecoder.decode(url, "UTF-8"));
            System.out.println(A.A.name().toLowerCase());
        }
        catch(Exception E){
        }
    }
}
