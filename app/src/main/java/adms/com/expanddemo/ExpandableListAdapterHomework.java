package adms.com.expanddemo;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admsandroid on 9/5/2017.
 */

public class ExpandableListAdapterHomework extends BaseExpandableListAdapter {

    private Context _context;
    boolean visible = true;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, ArrayList<HomeWorkModel.HomeWorkData>> _listDataChild;
    String FontStyle, splitFont1, splitFont2, splitFont3, splitFont4;
    TextView subject_title_txt, homework_title_txt, chapter_title_txt, lblchaptername, objective_title_txt, lblobjective, que_title_txt, lblque;
    LinearLayout chapter_linear, objective_linear, que_linear;
    Typeface typeface;

    public ExpandableListAdapterHomework(Context context, List<String> listDataHeader,
                                         HashMap<String, ArrayList<HomeWorkModel.HomeWorkData>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
    }

    @Override
    public ArrayList<HomeWorkModel.HomeWorkData> getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition));
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             final boolean isLastChild, View convertView, ViewGroup parent) {

        final ArrayList<HomeWorkModel.HomeWorkData> childData = getChild(groupPosition, 0);


        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_item_home_work, null);
        }
        subject_title_txt = (TextView) convertView.findViewById(R.id.subject_title_txt);
        homework_title_txt = (TextView) convertView.findViewById(R.id.homework_title_txt);
        chapter_linear = (LinearLayout) convertView.findViewById(R.id.chapter_linear);
        chapter_title_txt = (TextView) convertView.findViewById(R.id.chapter_title_txt);
        lblchaptername = (TextView) convertView.findViewById(R.id.lblchaptername);

        subject_title_txt.setText(childData.get(childPosition).getSubject());
        homework_title_txt.setText(childData.get(childPosition).getHomework());
//        chapter_title_txt.setText("Chapter Name :");
//        lblchaptername.setText(childData.get(childPosition).getChapterName());
        chapter_title_txt.setText(String.valueOf(childPosition));

        homework_title_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (visible == true) {
                    chapter_linear.setVisibility(View.VISIBLE);
                    chapter_title_txt.setVisibility(View.VISIBLE);
                    lblchaptername.setVisibility(View.VISIBLE);
                    visible = false;
                } else {
                    chapter_linear.setVisibility(View.GONE);
                    chapter_title_txt.setVisibility(View.GONE);
                    lblchaptername.setVisibility(View.GONE);
                    visible = true;
                }
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this._context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }

        if (isExpanded) {
            convertView.setBackgroundResource(R.drawable.homework_selected_bg);
        } else {
            convertView.setBackgroundResource(R.drawable.homework_subject_bg);
        }


        TextView lblListHeader = (TextView) convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

//    public void SetLanguageHomework(String type) {
//        switch (type) {
//            case "ArivNdr POMt":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Hindi Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Hindi Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Hindi Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Hindi Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Shivaji05":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            case "Shruti":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
//                homework_title_txt.setTypeface(typeface);
//                break;
//            default:
//        }
//    }
//
//    public void SetLanguageChapterName(String type) {
//        switch (type) {
//            case "ArivNdr POMt":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Hindi Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Hindi Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Hindi Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Hindi Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Shivaji05":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
//                lblchaptername.setTypeface(typeface);
//                break;
//            case "Shruti":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
//                lblchaptername.setTypeface(typeface);
//                break;
//            default:
//        }
//    }
//
//    public void SetLanguageObjective(String type) {
//        switch (type) {
//            case "ArivNdr POMt":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Hindi Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Hindi Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Hindi Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Hindi Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Shivaji05":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
//                lblobjective.setTypeface(typeface);
//                break;
//            case "Shruti":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
//                lblobjective.setTypeface(typeface);
//                break;
//            default:
//        }
//    }
//
//    public void SetLanguageAssessmentQue(String type) {
//        switch (type) {
//            case "ArivNdr POMt":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fotns/Arvinder.ttf");
//                lblque.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Gujrati-Saral-1.ttf");
//                lblque.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL2.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL3.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Gujrati Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/G-SARAL4.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Hindi Saral-4":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/H-SARAL0.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Hindi Saral-1":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral1.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Hindi Saral-2":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral2.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Hindi Saral-3":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/h-saral3.TTF");
//                lblque.setTypeface(typeface);
//                break;
//            case "Shivaji05":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shivaji05.ttf");
//                lblque.setTypeface(typeface);
//                break;
//            case "Shruti":
//                typeface = Typeface.createFromAsset(_context.getAssets(), "Fonts/Shruti.ttf");
//                lblque.setTypeface(typeface);
//                break;
//            default:
//        }
//    }
}

