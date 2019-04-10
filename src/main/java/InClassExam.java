import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;

public class InClassExam {

    private static String url = "https://xkcd.com/";
    private static File homeDir = new File(System.getProperty("user.home"));
    private static File dir = new File(homeDir, "/Exams");
    private static String fullPathToFile = (System.getProperty("user.home") + File.separator + "/Exams");

    public static void main(String[] args) throws Exception{

        if(!dir.exists()) {
            if (dir.mkdir())
                System.out.println("Directory creatred.\n");
            else
                System.out.println("Directory not created.\n");
        }
        Document doc = Jsoup.connect(url).get();
        Elements img = doc.getElementsByTag("img");
        for (Element el : img) {
            String src = el.absUrl("src");
            System.out.println("Image found");
            System.out.println("img src = " + src);

            getImages(src);
        }
    }

    private static void getImages(String src) throws IOException {
        String folder = null;

        //Exctract the name of the image from the src attribute
        int indexname = src.lastIndexOf("/");

        if (indexname == src.length()) {
            src = src.substring(1, indexname);
        }

        indexname = src.lastIndexOf("/");
        String name = src.substring(indexname);

        System.out.println(name);
        URL url = new URL(src);

        InputStream in = url.openStream();

        OutputStream out = new BufferedOutputStream(new FileOutputStream( fullPathToFile+ name));

        for (int b; (b = in.read()) != -1;) {
            out.write(b);
        }
        out.close();
        in.close();
    }
    //saves to home directory
}
