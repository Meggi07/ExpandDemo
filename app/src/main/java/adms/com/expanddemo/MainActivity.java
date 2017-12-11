package adms.com.expanddemo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnMenu, btnFilterHomework, btnBackHomework;
    private static TextView fromDate, toDate;
    private TextView txtNoRecordsHomework;
    private static String dateFinal;
    private Context mContext;
    private GetStudHomeworkAsyncTask getStudHomeworkAsyncTask = null;
    private ArrayList<HomeWorkModel> homeWorkModels = new ArrayList<>();
    private ProgressDialog progressDialog = null;
    private static boolean isFromDate = false;
    private int lastExpandedPosition = -1;

    ExpandableListAdapterHomework listAdapter;
    ExpandableListView lvExpHomework;
    List<String> listDataHeader;
    HashMap<String, ArrayList<HomeWorkModel.HomeWorkData>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        initViews();
        setListners();
    }

    public void initViews() {
        txtNoRecordsHomework = (TextView) findViewById(R.id.txtNoRecordsHomework);
        lvExpHomework = (ExpandableListView) findViewById(R.id.lvExpHomework);

        getHomeworkData();
    }

    public void setListners() {
        lvExpHomework.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    lvExpHomework.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });
    }

    public void getHomeworkData() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("StudentID", "294");//
                    params.put("HomeWorkFromDate", "11/12/2017");
                    params.put("HomeWorkToDate", "11/12/2017");
                    homeWorkModels.clear();
                    getStudHomeworkAsyncTask = new GetStudHomeworkAsyncTask(params);
                    homeWorkModels = getStudHomeworkAsyncTask.execute().get();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (homeWorkModels.size() > 0) {
                                txtNoRecordsHomework.setVisibility(View.GONE);
                                prepaareList();
                                listAdapter = new ExpandableListAdapterHomework(mContext, listDataHeader, listDataChild);
                                lvExpHomework.setAdapter(listAdapter);
                            } else {
                                txtNoRecordsHomework.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void prepaareList() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, ArrayList<HomeWorkModel.HomeWorkData>>();

        for (int i = 0; i < homeWorkModels.size(); i++) {
            listDataHeader.add(homeWorkModels.get(i).getHomeWorkDate());

            ArrayList<HomeWorkModel.HomeWorkData> rows = new ArrayList<HomeWorkModel.HomeWorkData>();
            for (int j = 0; j < homeWorkModels.get(i).getHomeWorkDatas().size(); j++) {
                rows.add(homeWorkModels.get(i).getHomeWorkDatas().get(j));

            }
            listDataChild.put(listDataHeader.get(i), rows);
        }
    }

}
