package com.app.spotcheck.widgets;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.ReportSearchBean;
import com.app.spotcheck.moudle.report.MExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;


public class BottomSearchView extends BottomView {

    private ExpandableListView expandableListView;
    private List<List<ReportSearchBean.SearchListDTO.PartslistDTO>> childList;
    private List<String> groupList;
    private EditText serarchEt;
    private TextView titleTv;
    private MExpandableListAdapter mAdapter;
    private Context context;

    public BottomSearchView(@NonNull Context context, int tag) {
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
                ReportSearchBean.SearchListDTO.PartslistDTO childBean = childList.get(groupPosition).get(childPosition);
                if (childBean != null) {
                    mDeviceListener.onClick(childBean.getMAINNAME(), childBean.getPARTNAME(), childBean.getMAINID(), childBean.getPARTID());
                }

                return false;
            }
        });
    }

    public void setData(ReportSearchBean bean) {
        groupList.clear();
        childList.clear();
        for (int i = 0; i < bean.getSearchList().size(); i++) {
            groupList.add(bean.getSearchList().get(i).getMAINNAME());
            for (int i1 = 0; i1 < bean.getSearchList().get(i).getPARTSLIST().size(); i1++) {
                childList.add(bean.getSearchList().get(i).getPARTSLIST());
            }
        }
        mAdapter = new MExpandableListAdapter(context, groupList, childList);
        expandableListView.setAdapter(mAdapter);
    }

    private DeviceOnClickListener mDeviceListener;

    public interface DeviceOnClickListener {
        public void onClick(String mainName, String partName, String mainId, String partId);
    }

    public void setDeviceOnClickListener(DeviceOnClickListener mDeviceListener) {
        this.mDeviceListener = mDeviceListener;
    }

    public void setSearchHint(String hint){
        serarchEt.setHint(hint);
    }

    public void setTitleTv(String title){
        titleTv.setText(title);
    }
}
