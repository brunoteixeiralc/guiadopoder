package br.com.vector.guiadopoder.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.vector.guiadopoder.model.DrawerItem;

import com.example.guiadopoder.R;

public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {

	Context context;
	List<DrawerItem> drawerItemList;
	int layoutResID;
	private View view;
	
	public DrawerListAdapter(Context context, int layoutResourceID,
			List<DrawerItem> listItems) {
		super(context, layoutResourceID, listItems);
		this.context = context;
		this.drawerItemList = listItems;
		this.layoutResID = layoutResourceID;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

	
		DrawerItemHolder drawerHolder;
		view = convertView;

		if (view == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			drawerHolder = new DrawerItemHolder();

			view = inflater.inflate(layoutResID, parent, false);
			drawerHolder.ItemName = (TextView) view
					.findViewById(R.id.drawer_itemName);
			drawerHolder.icon = (ImageView) view.findViewById(R.id.drawer_icon);
			
			drawerHolder.searchIcon = (ImageView) view.findViewById(R.id.drawer_icon_search);

			drawerHolder.title = (TextView) view.findViewById(R.id.drawerTitle);
			
			drawerHolder.searchTitle = (TextView) view.findViewById(R.id.drawer_txt_search);
			
			drawerHolder.lineColor = (View) view.findViewById(R.id.line_color);

			drawerHolder.headerLayout = (LinearLayout) view
					.findViewById(R.id.headerLayout);
			drawerHolder.itemLayout = (LinearLayout) view
					.findViewById(R.id.itemLayout);
			drawerHolder.searchLayout = (LinearLayout) view
					.findViewById(R.id.llSearch);

			view.setTag(drawerHolder);

		} else {
			
			drawerHolder = (DrawerItemHolder) view.getTag();

		}
		
		DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);
		
		if (dItem.getTitle() != null) {
			
			drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.searchLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.title.setText(dItem.getTitle());

		}else if(dItem.getSearchText() != null){
			
			drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.searchLayout.setVisibility(LinearLayout.VISIBLE);
			
			drawerHolder.searchIcon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
			drawerHolder.searchTitle.setText(dItem.getSearchText());
			
			
		}else {
			
			drawerHolder.headerLayout.setVisibility(LinearLayout.INVISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.searchLayout.setVisibility(LinearLayout.INVISIBLE);

			drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
					dItem.getImgResID()));
			drawerHolder.ItemName.setText(dItem.getItemName());
			drawerHolder.lineColor.setBackgroundColor(lineColor(dItem.getItemName()));
			

		}
		return view;
	}
	
	private static class DrawerItemHolder {
		TextView ItemName, title, searchTitle;
		ImageView icon,searchIcon;
		LinearLayout headerLayout, itemLayout,searchLayout;
		View lineColor;
	}
	
	private int lineColor(String poder){
		
		if(poder.equalsIgnoreCase("Executivo")){
			return view.getResources().getColor(R.color.yellow);
		}else if(poder.equalsIgnoreCase("Legislativo")){
			return view.getResources().getColor(R.color.green);
		}else if(poder.equalsIgnoreCase("Judici‡rio")){
			return view.getResources().getColor(R.color.red);
		}else{
			return view.getResources().getColor(R.color.blue_light);
		}
		
	}
    
   
}