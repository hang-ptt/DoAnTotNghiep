package company;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        String dir = "D:\\images\\MI1130";

        ArrayList<MobileItems> listMobiles = new ArrayList<>();
        ArrayList<String> listUrlImage = new ArrayList<>();

        Document doc = Jsoup.connect("https://tiki.vn/cham-soc-co-the/c1592?page=4").get();

        Elements elements = doc.getElementsByClass("product-item");

        for (Element e : elements) {
            MobileItems item = new MobileItems();
            String temp = "";
            item.setTitle(e.attr("data-title"));
            item.setPrice(e.attr("data-price"));
            item.setImageUrl(e.childNode(1).childNode(1).childNode(1).childNode(1).attr("src"));
            item.setDetailUrl(e.child(0).attr("href"));

            listMobiles.add(item);
            listUrlImage.add(item.imageUrl);
        }

        //Chuyển listMobiles thành JSON text
        Gson gson = new Gson();
        String jsonData = gson.toJson(listMobiles);

        System.out.println(jsonData);

//        for (int i = 0; i < listUrlImage.size(); i++) {
//            System.out.println(i + " Title: " + listUrlImage.get(i));
//        }
        int x = 0;
        try {
            for (int i = 0; i < listUrlImage.size(); i++) {
                x=i+147;
                String name = "MI1130-"+ x + ".jpg";
                saveImg(listUrlImage.get(i), name, dir);

            }
            } catch(Exception e){
                JOptionPane.showMessageDialog(null, "Error", "Error to save file !", JOptionPane.ERROR_MESSAGE);
            }


        }


    private static void saveImg(String src_image, String name, String dir) {
        try {
            URL url = new URL(src_image);
            InputStream in = url.openStream();
            OutputStream out = new BufferedOutputStream(new FileOutputStream(dir + "\\" + name));
            for (int b; (b = in.read()) != -1;) {
                out.write(b);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Can not Dowload File !");
        }
    }

}
