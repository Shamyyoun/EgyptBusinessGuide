package adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahmoudelshamy.ebg.R;

import java.util.ArrayList;
import java.util.List;

import datamodels.InvestmentSystem;
import views.SlideExpandableListView;

public class SystemsAdapter extends SlideExpandableListView.AnimatedExpandableListAdapter {

    private Context context;
    private int groupLayoutResourceId;
    private int childLayoutResourceId;
    private InvestmentSystem[] data;

    private LayoutInflater inflater;
    private List<GroupHolder> groupHolders;

    public SystemsAdapter(Context context, int groupLayoutResourceId, int childLayoutResourceId, InvestmentSystem[] data) {
        this.context = context;
        this.groupLayoutResourceId = groupLayoutResourceId;
        this.childLayoutResourceId = childLayoutResourceId;
        this.data = data;

        inflater = LayoutInflater.from(context);
        groupHolders = new ArrayList<GroupHolder>(data.length);
    }

    @Override
    public InvestmentSystem getChild(int groupPosition, int childPosition) {
        return data[groupPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getRealChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {

        ChildHolder holder;
        final InvestmentSystem system = getChild(groupPosition, childPosition);

        if (convertView == null) {
            holder = new ChildHolder();

            convertView = inflater.inflate(childLayoutResourceId, parent, false);
            holder.textDescription = (TextView) convertView.findViewById(R.id.text_description);

            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        holder.textDescription.setText(Html.fromHtml(system.getDesc()));

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public InvestmentSystem getGroup(int groupPosition) {
        return data[groupPosition];
    }

    @Override
    public int getGroupCount() {
        return data.length;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final GroupHolder holder;
        final InvestmentSystem system = getGroup(groupPosition);

        if (convertView == null) {
            holder = new GroupHolder();
            convertView = inflater.inflate(groupLayoutResourceId, parent, false);

            holder.imageImage = (ImageView) convertView.findViewById(R.id.image_image);
            holder.textName = (TextView) convertView.findViewById(R.id.text_name);
            holder.imageExpand = (ImageView) convertView.findViewById(R.id.image_expand);

            convertView.setTag(holder);
        } else {
            holder = (GroupHolder) convertView.getTag();
        }

        // set data
        holder.imageImage.setImageResource(system.getImageResId());
        holder.textName.setText(system.getName());

        // set expand button image
        if (system.isExpanded()) {
            holder.imageExpand.setImageResource(R.drawable.ex_list_arrow_expanded);
        } else {
            holder.imageExpand.setImageResource(R.drawable.ex_list_arrow_collapsed);
        }

        // save reference to this group row
        groupHolders.add(groupPosition, holder);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

    /**
     * method, used to change icon and state when expand/collapse group
     */
    public void expandItem(int position, boolean expand) {
        GroupHolder holder = groupHolders.get(position);

        // change expand icon
        holder.imageExpand.setImageResource(expand ? R.drawable.ex_list_arrow_expanded : R.drawable.ex_list_arrow_collapsed);

        // change project object state
        data[position].setExpanded(expand);
    }

    private static class GroupHolder {
        ImageView imageImage;
        TextView textName;
        ImageView imageExpand;
    }

    private static class ChildHolder {
        TextView textDescription;
    }
}
