package company;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<MobileItems> listMobiles = new ArrayList<>();

        Document doc = Jsoup.connect("https://tiki.vn/dien-thoai-may-tinh-bang/c1789").get();

        Elements elements = doc.getElementsByClass("product-item");

        for (Element e : elements){
            MobileItems item = new MobileItems();
            item.setTitle(e.attr("data-title"));
            item.setPrice(e.attr("data-price"));
            item.setImageUrl(e.childNode(1).childNode(1).childNode(1).childNode(1).attr("src"));
            item.setDetailUrl(e.child(0).attr("href"));

            listMobiles.add(item);
        }

        //Chuyển listMobiles thành JSON text
        Gson gson = new Gson();
        String jsonData = gson.toJson(listMobiles);

        System.out.println(jsonData);
        for (int i = 0; i < listMobiles.size(); i++){
            System.out.println(i + " Title: " + listMobiles.get(i).getTitle());
        }
    }
}
