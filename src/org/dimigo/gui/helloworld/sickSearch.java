package org.dimigo.gui.helloworld;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class sickSearch {
    @SuppressWarnings("unchecked")

    public static List<sickCd> getSickCdList(String code) throws Exception{
        String response = search("SICK_CD",code);

        //list 에 추가
        List<sickCd> result= new ArrayList<>();
        Map<String, Map<String, Map<String, Map>>> map = new ObjectMapper().readValue(response, Map.class);

        List<Map<String, String>> list = (List<Map<String, String>>) map.get("response").get("body").get("items").get("item");


        for (Map<String, String> sick : list) {
            String sickCd = sick.get("sickCd");
            String sickNm = sick.get("sickNm");
            result.add(new sickCd(sickCd,sickNm));
        }
        return result;
    }

    @SuppressWarnings("unchecked")

    public static List<sickNm> getSickNmList(String keyword) throws Exception{
        String response = search("SICK_NM",keyword);

        //list 에 추가
        List<sickNm> result= new ArrayList<>();
        Map<String, Map<String, Map<String, Map>>> map = new ObjectMapper().readValue(response, Map.class);

        List<Map<String, String>> list = (List<Map<String, String>>) map.get("response").get("body").get("items").get("item");


        for (Map<String, String> sick : list) {
            String sickNm = sick.get("sickNm");
            String sickCd = sick.get("sickCd");
            result.add(new sickNm(sickNm,sickCd));
        }
        return result;
    }

    private static String search(String type, String search) throws Exception {
        BufferedReader br = null;
        try {
            if(search.equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText("검색어 없음");
                alert.setContentText("검색어를 입력해주세요");
                alert.showAndWait();
                return "";
            }
            String text = URLEncoder.encode(search, "UTF-8");
//            String sickType = ("SICK_NM"); //코드로 검색 : SICK_CD
            String apiURL = "http://apis.data.go.kr/B551182/diseaseInfoService/getDissNameCodeList?" +
//                    "sickType=1" + //1 =
//                    "&medTp=2" +
                    "&diseaseType=" + type +
                    "&searchText=" + text +
                    "&_type=json" +
                    "&ServiceKey=a6kmDvaaocSYu4iBHzM7koZWJbdmnJJOD61dtlwPfYEhuqw9WGY2bEO%2F0San8NhMVYTIY2XPXBofbl49QlBV%2FQ%3D%3D";


            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();

            if (responseCode == 200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            return response.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            br.close();
        }
    }

}
