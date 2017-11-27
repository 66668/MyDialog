package lib.sjy.dialog.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import lib.sjy.dialog.drawable.BottonParentView;
import lib.sjy.dialog.drawable.ToastDialogButton;
import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.params.ButtonParams;
import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.view.baseview.ScaleTextView;


/**
 * 对话框单个按钮的视图
 */
class SingleButton extends RelativeLayout {
    private ButtonParams mButtonParams;
    private ScaleTextView singleButton;

    public SingleButton(Context context, AllParams params) {
        super(context);
        init(params);
    }

    private void init(AllParams params) {

        mButtonParams = params.negativeParams != null ? params.negativeParams : params.positiveParams;
        int radius = params.dialogParams.radius;

        //父布局
        RelativeLayout layout = new RelativeLayout(getContext());
        //设置限制
        LayoutParams rlp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mButtonParams.btnHeightF);
        //子控件居中
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);

        //父布局背景色
        int relativeLayoutbg = mButtonParams.btn_parent_bg != 0 ? mButtonParams.btn_parent_bg : DialogColor.common_bg;
        //左-右下角做圆角处理
        layout.setBackground(new BottonParentView(relativeLayoutbg, 0, 0, radius, radius));

        //子控件 按钮
        singleButton = new ScaleTextView(getContext());
        //子控件限制
        LayoutParams childLp = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, mButtonParams.btnHeight);
        //字体居中
        childLp.addRule(RelativeLayout.CENTER_IN_PARENT);

        singleButton.setText(mButtonParams.btn_text);
        singleButton.setTextSize(mButtonParams.btnTxtSize);
        singleButton.setTextColor(mButtonParams.btnTxtColor);
        singleButton.setWidth(mButtonParams.btnWidth);
        singleButton.setHeight(mButtonParams.btnHeight);

        //如果取消按钮没有背景色，则使用默认色
        int backgroundPositive = mButtonParams.btn_bg_cl != 0 ? mButtonParams.btn_bg_cl : DialogColor.btn_bg_sure_cl;

        //按钮切圆角
        singleButton.setBackground(new ToastDialogButton(backgroundPositive, radius, radius, radius, radius));

        regOnClickListener();

        layout.addView(singleButton, childLp);
        addView(layout, rlp);
    }

    /**
     * 按钮监听
     */
    private void regOnClickListener() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mButtonParams.dismiss();
                if (mButtonParams.listener != null)
                    mButtonParams.listener.onClick(v);
            }
        });
    }

    public void regOnInputClickListener(final EditText input) {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                if (!TextUtils.isEmpty(text))
                    mButtonParams.dismiss();
                if (mButtonParams.inputListener != null)
                    mButtonParams.inputListener.onClick(text, v);
            }
        });
    }
}
