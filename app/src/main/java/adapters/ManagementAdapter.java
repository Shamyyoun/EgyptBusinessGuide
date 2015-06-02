package adapters;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mahmoudelshamy.ebg.R;

import java.util.ArrayList;
import java.util.List;

import datamodels.ManagementPerson;
import views.SlideExpandableListView;

public class ManagementAdapter extends SlideExpandableListView.AnimatedExpandableListAdapter {

    private Context context;
    private int groupLayoutResourceId;
    private int childLayoutResourceId;
    private ManagementPerson[] data;

    private LayoutInflater inflater;
    private List<GroupHolder> groupHolders;

    public ManagementAdapter(Context context, int groupLayoutResourceId, int childLayoutResourceId, ManagementPerson[] data) {
        this.context = context;
        this.groupLayoutResourceId = groupLayoutResourceId;
        this.childLayoutResourceId = childLayoutResourceId;
        this.data = data;

        inflater = LayoutInflater.from(context);
        groupHolders = new ArrayList<GroupHolder>(data.length);
    }

    @Override
    public ManagementPerson getChild(int groupPosition, int childPosition) {
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
        final ManagementPerson person = getChild(groupPosition, childPosition);

        if (convertView == null) {
            holder = new ChildHolder();

            convertView = inflater.inflate(childLayoutResourceId, parent, false);
            holder.textDescription = (TextView) convertView.findViewById(R.id.text_description);
            holder.layoutMail = convertView.findViewById(R.id.layout_mail);
            holder.textMail = (TextView) convertView.findViewById(R.id.text_mail);

            convertView.setTag(holder);
        } else {
            holder = (ChildHolder) convertView.getTag();
        }

        holder.textDescription.setText(Html.fromHtml(person.getDesc()));
        holder.textMail.setText(person.getEmail());

        // check if has email
        if (person.getEmail() == null) {
            // hide it
            holder.layoutMail.setVisibility(View.GONE);
        } else {
            // show it
            holder.layoutMail.setVisibility(View.VISIBLE);

            // add listener
            holder.layoutMail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{person.getEmail()});
                    intent.setType("message/rfc822");
                    context.startActivity(intent);
                }
            });
        }

        return convertView;
    }

    @Override
    public int getRealChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public ManagementPerson getGroup(int groupPosition) {
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
        final ManagementPerson person = getGroup(groupPosition);

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
        holder.imageImage.setImageResource(person.getImageResId());
        holder.textName.setText(person.getName());

        // set expand button image
        if (person.isExpanded()) {
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
        View layoutMail;
        TextView textMail;
    }
}
