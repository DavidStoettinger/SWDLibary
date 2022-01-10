package at.fh.winb.swd.libary;

import at.fh.winb.swd.libary.entity.Ausleihe;
import at.fh.winb.swd.libary.entity.Exemplar;
import at.fh.winb.swd.libary.entity.Kunde;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileReader;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class LibaryApplication {

    public static void main(String[] args) {
        readDatafromJSONInitially();
        SpringApplication.run(LibaryApplication.class, args);
    }

    public static void readDatafromJSONInitially() {
        List<Kunde> kundeData;

        JSONParser parser = new JSONParser(); //create Parser
        String myPath = "resources/static/exampleData"; //path to test data
        File dir = new File(myPath);    //create File for directory where test data is stored
        File[] dirListing = dir.listFiles(); //array for found files
        if (dirListing != null) {
            for (File child : dirListing) {//loop through files
                switch (child.toString()) {
                    case "Daten_Kunden.json":
                        try (FileReader reader = new FileReader(child.toString());) {
                            Object jsonArr = parser.parse(reader);
                            JSONArray testDataArr = (JSONArray) jsonArr;

                            for (Object objectJs : testDataArr) { //loop through JSON objects in JSON Array
                                Kunde temp1 = new Kunde();
                                Object obj = parser.parse(String.valueOf(child));
                                JSONObject jsonObject = (JSONObject) obj;
                                long id = (long) jsonObject.get("id");
                                String name = (String) jsonObject.get("Name");
                                temp1.setId(id);
                                temp1.setName(name);
                                System.out.println(temp1.getId() + temp1.getName());
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;

                    case "Daten_Ausleihe.json":
                        try (FileReader reader = new FileReader(child.toString());) {
                            Object jsonArr = parser.parse(reader);
                            JSONArray testDataArr = (JSONArray) jsonArr;

                            for (Object objectJs : testDataArr) { //loop through JSON objects in JSON Array
                                Ausleihe temp1 = new Ausleihe();
                                Object obj = parser.parse(String.valueOf(child));
                                JSONObject jsonObject = (JSONObject) obj;
                                long id = (long) jsonObject.get("id");
                                Date zeitp = (Date) jsonObject.get("Zeitpunkt");
                                Date istzeit = (Date) jsonObject.get("IstZeit");
                                Date sollzeit = (Date) jsonObject.get("SollZeit");
                                Kunde kunde = (Kunde) jsonObject.get("Kunde");
                                Exemplar exemp = (Exemplar) jsonObject.get("Exemplar");
                                temp1.setId(id);
                                temp1.setZeitpunkt(zeitp);
                                temp1.setIstZeit(istzeit);
                                temp1.setSollZeit(sollzeit);
                                temp1.setKunde(kunde);
                                temp1.setExemplar(exemp);
                                System.out.println(temp1.getExemplar());
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        }
    }

}
