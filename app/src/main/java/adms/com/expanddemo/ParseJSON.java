package adms.com.expanddemo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by admsandroid on 12/11/2017.
 */

public class ParseJSON {
    public static ArrayList<HomeWorkModel> parseStudHomeworkJson(String responseString) {
        ArrayList<HomeWorkModel> result = new ArrayList<>();

        try {
            JSONObject reader = new JSONObject(responseString);
            String data_load_basket = reader.getString("Success");
            if (data_load_basket.toString().equals("True")) {
                JSONArray jsonMainNode = reader.optJSONArray("FinalArray");
                HomeWorkModel homeWorkModel = null;

                for (int i = 0; i < jsonMainNode.length(); i++) {
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    homeWorkModel = new HomeWorkModel();
                    homeWorkModel.setHomeWorkDate(jsonChildNode.getString("HomeWorkDate"));

                    HomeWorkModel.HomeWorkData homeWorkData = null;
                    ArrayList<HomeWorkModel.HomeWorkData> homeWorkDatas = new ArrayList<>();
                    JSONArray jsonMainNode1 = jsonChildNode.optJSONArray("Data");
                    for (int j = 0; j < jsonMainNode1.length(); j++) {
                        JSONObject jsonChildNode1 = jsonMainNode1.getJSONObject(j);
                        homeWorkData = homeWorkModel.new HomeWorkData();
                        homeWorkData.setSubject(jsonChildNode1.getString("Subject"));
                        homeWorkData.setHomework(jsonChildNode1.getString("HomeWork"));
                        homeWorkData.setChapterName(jsonChildNode1.getString("ChapterName"));
                        homeWorkData.setObjective(jsonChildNode1.getString("Objective"));
                        homeWorkData.setAssessmentQue(jsonChildNode1.getString("AssessmentQue"));
                        homeWorkData.setFont(jsonChildNode1.getString("Font"));
                        homeWorkDatas.add(homeWorkData);
                    }
                    homeWorkModel.setHomeWorkDatas(homeWorkDatas);
                    result.add(homeWorkModel);
                }

            } else {
                //invalid login
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
