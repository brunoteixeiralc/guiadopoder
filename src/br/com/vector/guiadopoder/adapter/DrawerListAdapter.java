package br.com.vector.guiadopoder.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
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
			
			drawerHolder.logoVectorAbout = (ImageView) view.findViewById(R.id.logo_about);

			drawerHolder.headerLayout = (LinearLayout) view
					.findViewById(R.id.headerLayout);
			drawerHolder.itemLayout = (LinearLayout) view
					.findViewById(R.id.itemLayout);
			drawerHolder.searchLayout = (LinearLayout) view
					.findViewById(R.id.llSearch);
			drawerHolder.aboutLayout = (LinearLayout) view
					.findViewById(R.id.llAbout);

			view.setTag(drawerHolder);

		} else {
			
			drawerHolder = (DrawerItemHolder) view.getTag();

		}
		
		DrawerItem dItem = (DrawerItem) this.drawerItemList.get(position);
		
		if (dItem.getTitle() != null) {
			
			drawerHolder.headerLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.searchLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.aboutLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.title.setText(dItem.getTitle());

		}else if(dItem.getSearchText() != null){
			
			drawerHolder.headerLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.searchLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.aboutLayout.setVisibility(LinearLayout.GONE);
			
			drawerHolder.searchIcon.setImageDrawable(view.getResources().getDrawable(dItem.getImgResID()));
			drawerHolder.searchTitle.setText(dItem.getSearchText());
				
		}else if(dItem.getImgResLogoAbout() != 0){
			
			drawerHolder.headerLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.searchLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.aboutLayout.setVisibility(LinearLayout.VISIBLE);
			
			drawerHolder.logoVectorAbout.setImageDrawable(view.getResources().getDrawable(
					dItem.getImgResLogoAbout()));
			
		}else {
			
			drawerHolder.headerLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.itemLayout.setVisibility(LinearLayout.VISIBLE);
			drawerHolder.searchLayout.setVisibility(LinearLayout.GONE);
			drawerHolder.aboutLayout.setVisibility(LinearLayout.GONE);

			drawerHolder.icon.setImageDrawable(view.getResources().getDrawable(
					dItem.getImgResID()));
			drawerHolder.ItemName.setText(dItem.getItemName());
			drawerHolder.lineColor.setBackgroundColor(Color.parseColor("#" + dItem.getColor()));
			

		}
		return view;
	}
	
	private static class DrawerItemHolder {
		TextView ItemName, title, searchTitle;
		ImageView icon,searchIcon,logoVectorAbout;
		LinearLayout headerLayout, itemLayout,searchLayout,aboutLayout;
		View lineColor;
	}
	
//	private int lineColor(String poder){
//		
//		if(poder.equalsIgnoreCase("Executivo")){
//			return view.setBackgroundColor(Color.parseColor("#" + poder))
//		}else if(poder.equalsIgnoreCase("Legislativo")){
//			return view.getResources().getColor(R.color.green);
//		}else if(poder.equalsIgnoreCase("Estadual")){
//			return view.getResources().getColor(R.color.blue_light);
//		}else{
//			return view.getResources().getColor(R.color.red);
//		}
//		
//	}
//    
   
}