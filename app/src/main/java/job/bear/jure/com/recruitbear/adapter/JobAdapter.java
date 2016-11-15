package job.bear.jure.com.recruitbear.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import job.bear.jure.com.recruitbear.R;
import job.bear.jure.com.recruitbear.beans.Job;

/**
 * Created by jrue on 2015/9/21.
 */
public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {

    private List<Job> mData;
    private OnItemClickListener listener;
    private LayoutInflater mInflater;

    public JobAdapter(Context mContext,List<Job> mData) {
        mInflater = LayoutInflater.from(mContext);
        this.mData = mData;
    }


    /**
     * 在onCreateViewHolder里面实现对item布局的加载
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @Override
    public JobAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.recyclerview_item, null);
        ViewHolder mViewHolder = new ViewHolder(view);
        mViewHolder.item_icon = (ImageView) view.findViewById(R.id.icon);
        mViewHolder.tv_company = (TextView) view.findViewById(R.id.company);
        mViewHolder.tv_holdTiome = (TextView) view.findViewById(R.id.time);
        mViewHolder.tv_address = (TextView) view.findViewById(R.id.address);
        mViewHolder.item_view = (RelativeLayout) view.findViewById(R.id.rootView);

        return mViewHolder;
    }

    /**
     * 在onBindViewHolder实现对数据的绑定工作
     * 这里实现了点击事件和长按事件
     *
     * @param viewHolder
     * @param i
     */
    public void onBindViewHolder(final JobAdapter.ViewHolder viewHolder, int i) {
        Job mJob = mData.get(i);
        viewHolder.item_icon.setImageResource(R.drawable.ic_menu);
        viewHolder.tv_company.setText(mJob.getCompany());
        viewHolder.tv_holdTiome.setText(mJob.getHoldTime());
        viewHolder.tv_address.setText(mJob.getAddress());

        //设置回调，并进行设置相应的点击事件处理
        if (listener != null) {

            //点击事件处理
            viewHolder.item_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = viewHolder.getLayoutPosition();
                    listener.onItemClick(viewHolder.item_view, pos);
                }
            });

            //长按事件处理
            viewHolder.item_view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = viewHolder.getLayoutPosition();
                    listener.onItemLongClick(viewHolder.item_view, pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {

        return mData.isEmpty() ? 0 : mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout item_view;
        private ImageView item_icon;
        private TextView tv_company;
        private TextView tv_holdTiome;
        private TextView tv_address;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     * 实现内部接口的回调方法
     */
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);

        public void onItemLongClick(View view, int position);
    }

    /**
     * 设置监听事件
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

}
