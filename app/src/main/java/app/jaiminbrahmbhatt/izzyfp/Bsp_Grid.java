package app.jaiminbrahmbhatt.izzyfp;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


//defining ArrayList in GridItem calling it in GridData
public class Bsp_Grid extends ArrayAdapter<GridItem> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<GridItem> mGridData = new ArrayList<GridItem>();

    public Bsp_Grid(Context mContext, int layoutResourceId, ArrayList<GridItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }


    // getting ArrayList in GridItem
    public void setGridData(ArrayList<GridItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    //Making a ViewGroup and ViewHolder for product image and product title
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.titleTextView = row.findViewById(R.id.grid_item_title);
            holder.imageView = row.findViewById(R.id.grid_item_image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        GridItem item = mGridData.get(position);
        holder.titleTextView.setText(Html.fromHtml(item.getTitle()));

        Picasso.with(mContext).load(item.getImage()).placeholder(R.drawable.watermark_icon).into(holder.imageView);
        return row;
    }

    // Calling textview for title and  and ImageView for product images
    static class ViewHolder {
        TextView titleTextView;
        ImageView imageView;
    }
}
