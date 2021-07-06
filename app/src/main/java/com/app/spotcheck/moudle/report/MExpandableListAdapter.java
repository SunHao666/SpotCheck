package com.app.spotcheck.moudle.report;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.app.spotcheck.R;
import com.app.spotcheck.moudle.bean.ReportSearchBean;

import java.util.List;
import java.util.Map;

public class MExpandableListAdapter extends BaseExpandableListAdapter {

	List<List<ReportSearchBean.SearchListDTO.PartslistDTO>> childList;
	private Context context;

	private List<String>  groupList;

	public MExpandableListAdapter(Context context,
								  List<String> groupList, List<List<ReportSearchBean.SearchListDTO.PartslistDTO>> childList) {
		this.context = context;
		this.childList = childList;
		this.groupList = groupList;
	}

	/**
	 * 获取一级标签总数
	 */
	@Override
	public int getGroupCount() {
		return groupList.size();
	}

	/**
	 * 获取一级标签下二级标签的总数
	 */
	@Override
	public int getChildrenCount(int groupPosition) {
		return childList.get(groupPosition).size();
	}

	/**
	 * 获取一级标签内容
	 */
	@Override
	public String getGroup(int groupPosition) {
		return groupList.get(groupPosition);
	}

	/**
	 * 获取一级标签下二级标签的内容
	 */
	@Override
	public ReportSearchBean.SearchListDTO.PartslistDTO getChild(int groupPosition, int childPosition) {
		return childList.get(groupPosition).get(childPosition);
	}

	/**
	 * 获取一级标签的ID
	 */
	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	/**
	 * 获取二级标签的ID
	 */
	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	/**
	 * 指定位置相应的组视图
	 */
	@Override
	public boolean hasStableIds() {
		return true;
	}

	/**
	 * 对一级标签进行设置
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		convertView = (LinearLayout) LinearLayout.inflate(context,
				R.layout.item_group_layout, null);

		TextView group_title = (TextView) convertView
				.findViewById(R.id.group_title);
		if (isExpanded) {
			group_title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					R.drawable.group_down, 0);
		} else {
			group_title.setCompoundDrawablesWithIntrinsicBounds(0, 0,
					R.drawable.group_up, 0);
		}
		group_title.setText(groupList.get(groupPosition));

		return convertView;
	}

	/**
	 * 对一级标签下的二级标签进行设置
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		convertView = (RelativeLayout) RelativeLayout.inflate(context,
				R.layout.item_child_layout, null);
		TextView child_text = (TextView) convertView
				.findViewById(R.id.child_text);

		child_text.setText(childList.get(groupPosition).get(childPosition).getPARTNAME());

		return convertView;
	}

	/**
	 * 当选择子节点的时候，调用该方法
	 */
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
