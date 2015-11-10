package com.shaung.ramclear.adapter;

import java.util.List;

import com.shaung.ramclear.R;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridViewHomeAdapter extends BaseAdapter {
    private List<String> mList;
    private Context mContext;
    public GridViewHomeAdapter(List<String> list,Context context){
    	mList=list;
    	
    	mContext=context;
    }
	@Override
	public int getCount() {
		return mList.size();
	}
	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
	   View view=null;
	   HolderView holderView=null;
	   if(convertView==null){
		   view=View.inflate(mContext, R.layout.item_home, null);
		   holderView=new HolderView();
		   holderView.tv_homeitem_name=(TextView) view.findViewById(R.id.tv_homeitem_name);
		   view.setTag(holderView);
	   }else{
		   view=convertView;
		   holderView=(HolderView) view.getTag();
	   }
	    holderView.tv_homeitem_name.setText(mList.get(position));
		return view;
	}
	
	static class HolderView{
		
		TextView tv_homeitem_name;
	}

}
