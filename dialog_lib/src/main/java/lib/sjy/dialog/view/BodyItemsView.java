package lib.sjy.dialog.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import lib.sjy.dialog.drawable.ToastDialogButton;
import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.params.ItemsParams;
import lib.sjy.dialog.params.TitleParams;
import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.view.baseview.ScaleTextView;


/**
 * Created by hupei on 2017/3/30.
 */

class BodyItemsView extends ListView {
    private BaseAdapter mAdapter;

    public BodyItemsView(Context context, AllParams params) {
        super(context);
        init(context, params);
    }

    private void init(Context context, AllParams params) {
        final ItemsParams itemsParams = params.itemsParams;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LayoutParams
                .MATCH_PARENT, LayoutParams
                .MATCH_PARENT);
        setLayoutParams(layoutParams);

        setSelector(new ColorDrawable(Color.TRANSPARENT));
        setDivider(new ColorDrawable(DialogColor.divider));
        setDividerHeight(1);

        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemsParams.dismiss();
                if (itemsParams.listener != null)
                    itemsParams.listener.onItemClick(parent, view, position, id);
            }
        });

        mAdapter = new ItemsAdapter(context, params);
        setAdapter(mAdapter);
    }

    public void refreshItems() {
        post(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    static class ItemsAdapter<T> extends BaseAdapter {
        class ViewHolder {
            TextView item;
        }

        private Context mContext;
        private List<T> mItems;
        private int mRadius;
        private int mBackgroundColor;
        private ItemsParams mItemsParams;
        private TitleParams mTitleParams;

        public ItemsAdapter(Context context, AllParams params) {
            this.mContext = context;
            this.mTitleParams = params.titleParams;
            this.mItemsParams = params.itemsParams;
            this.mRadius = params.dialogParams.radius;
            //如果没有背景色，则使用默认色
            this.mBackgroundColor = mItemsParams.backgroundColor != 0 ? mItemsParams.backgroundColor : DialogColor.common_bg;
            Object entity = this.mItemsParams.items;
            if (entity != null && entity instanceof Iterable) {
                this.mItems = (List<T>) entity;
            } else if (entity != null && entity.getClass().isArray()) {
                this.mItems = Arrays.asList((T[]) entity);
            } else {
                throw new IllegalArgumentException("entity must be an Array or an Iterable.");
            }
        }

        @Override
        public int getCount() {
            if (mItems != null)
                return mItems.size();
            return 0;
        }

        @Override
        public T getItem(int position) {
            if (mItems != null)
                return mItems.get(position);
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                ScaleTextView textView = new ScaleTextView(mContext);
                textView.setTextSize(mItemsParams.textSize);
                textView.setTextColor(mItemsParams.textColor);
                textView.setHeight(mItemsParams.itemHeight);
                viewHolder.item = textView;
                convertView = textView;
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //top
            if (position == 0 && mTitleParams == null) {
                if (getCount() == 1) {
                    viewHolder.item.setBackground(new ToastDialogButton(mBackgroundColor, mRadius, mRadius, mRadius, mRadius));
                } else {
                    viewHolder.item.setBackground(new ToastDialogButton(mBackgroundColor, mRadius, mRadius, 0, 0));
                }
            }
            //bottom
            else if (position == getCount() - 1) {
                viewHolder.item.setBackground(new ToastDialogButton(mBackgroundColor, 0, 0, mRadius, mRadius));
            }
            //middle
            else {
                viewHolder.item.setBackground(new ToastDialogButton(mBackgroundColor, 0, 0, 0, 0));

            }
            viewHolder.item.setText(String.valueOf(getItem(position).toString()));
            return convertView;
        }
    }
}
