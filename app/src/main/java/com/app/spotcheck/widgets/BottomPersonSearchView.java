package com.app.spotcheck.widgets;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.PersonSearchBean;
import com.app.spotcheck.moudle.bean.ReportSearchBean;
import com.app.spotcheck.moudle.report.MExpandableListAdapter;
import com.app.spotcheck.moudle.report.MPersonExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;


public class BottomPersonSearchView extends BottomView {

    private ExpandableListView expandableListView;
    private List<List<PersonSearchBean.PersonItemBean.WorkListDTO>> childList;
    private List<String> groupList;
    private EditText serarchEt;
    private TextView titleTv;
    private MPersonExpandableListAdapter mAdapter;
    private Context context;

    public BottomPersonSearchView(@NonNull Context context, int tag) {
        super(context, tag);
        this.context = context;
        childList = new ArrayList<>();
        groupList = new ArrayList<>();

    }

    @Override
    protected int getLayout(int tag) {
        return R.layout.report_repair_search;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        expandableListView = findViewById(R.id.mSearchList);
        serarchEt = findViewById(R.id.serarchEt);
        titleTv = findViewById(R.id.titleTv);
        setSearchHint("查找维修人员");
        setTitleTv("维修人员选择");
        setListener();
    }

    private void setListener() {
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                PersonSearchBean.PersonItemBean.WorkListDTO childBean = childList.get(groupPosition).get(childPosition);
                if (childBean != null) {
                    mDeviceListener.onClick(childBean.getWORKER_NAME(), childBean.getWORKER_NO());
                }

                return false;
            }
        });
    }

    public void setData(PersonSearchBean bean) {
        groupList.clear();
        childList.clear();
        for (int i = 0; i < bean.getSearchList().size(); i++) {
            groupList.add(bean.getSearchList().get(i).getDEPARTMENT_NAME());
            for (int i1 = 0; i1 < bean.getSearchList().get(i).getWORKERLIST().size(); i1++) {
                childList.add(bean.getSearchList().get(i).getWORKERLIST());
            }
        }
        mAdapter = new MPersonExpandableListAdapter(context, groupList, childList);
        expandableListView.setAdapter(mAdapter);
    }

    private BottomPersonSearchView.PersonOnClickListener mDeviceListener;

    public interface PersonOnClickListener {
        public void onClick(String workName, String workNo);
    }

    public void setDeviceOnClickListener(BottomPersonSearchView.PersonOnClickListener mDeviceListener) {
        this.mDeviceListener = mDeviceListener;
    }

    public void setSearchHint(String hint) {
        serarchEt.setHint(hint);
    }

    public void setTitleTv(String title) {
        titleTv.setText(title);
    }
}
