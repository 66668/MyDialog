package lib.sjy.dialog.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import lib.sjy.dialog.drawable.BottonParentView;
import lib.sjy.dialog.drawable.ToastDialogButton;
import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.params.ButtonParams;
import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.value.DialogDimens;
import lib.sjy.dialog.view.baseview.ScaleLinearLayout;
import lib.sjy.dialog.view.baseview.ScaleTextView;


/**
 * 对话框确定按钮与取消的视图
 * 可以修改
 */
class MultipleButton extends ScaleLinearLayout {
    private ButtonParams mNegativeParams;
    private ButtonParams mPositiveParams;

    //
    private ScaleTextView mNegativeButton;
    private ScaleTextView mPositiveButton;

    public MultipleButton(Context context, AllParams params) {
        super(context);
        init(params);
    }

    private void init(AllParams params) {
        //水平布局
        setOrientation(HORIZONTAL);

        mNegativeParams = params.negativeParams;
        mPositiveParams = params.positiveParams;

        int radius = params.dialogParams.radius;

        //取消按钮
        //        createNegative(radius);
        createNegative2(radius);

        //添加二人按钮之间的分隔线
        //        DividerView dividerView = new DividerView(getContext());
        //        addView(dividerView);

        //确定按钮
        //        createPositive(radius);
        createPositive2(radius);
    }

    /**
     * ==================================================== 创建按钮 方式1====================================================================
     */
    /**
     * 创建取消按钮 方式1：按钮充满布局
     *
     * @param radius
     */
    private void createNegative(int radius) {
        mNegativeButton = new ScaleTextView(getContext());
        mNegativeButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        mNegativeButton.setText(mNegativeParams.btn_text);
        mNegativeButton.setTextSize(mNegativeParams.btnTxtSize);
        mNegativeButton.setTextColor(mNegativeParams.btnTxtColor);
        mNegativeButton.setHeight(mNegativeParams.btnHeight);

        //如果取消按钮没有背景色，则使用默认色
        int backgroundNegative = mNegativeParams.btn_bg_cl != 0 ? mNegativeParams.btn_bg_cl : DialogColor.btn_bg_cancel_cl;

        //按钮切圆角
        mNegativeButton.setBackground(new ToastDialogButton(backgroundNegative, radius, radius, radius, radius));

        regNegativeListener();

        addView(mNegativeButton);
    }

    /**
     * 创建确定按钮 方式1：按钮充满布局
     *
     * @param radius
     */
    private void createPositive(int radius) {

        mPositiveButton = new ScaleTextView(getContext());
        mPositiveButton.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));

        mPositiveButton.setText(mPositiveParams.btn_text);
        mPositiveButton.setTextSize(mPositiveParams.btnTxtSize);
        mPositiveButton.setTextColor(mPositiveParams.btnTxtColor);
        mPositiveButton.setHeight(mPositiveParams.btnHeight);

        //如果取消按钮没有背景色，则使用默认色
        int backgroundPositive = mPositiveParams.btn_bg_cl != 0 ? mPositiveParams.btn_bg_cl : DialogColor.btn_bg_sure_cl;

        //按钮切圆角
        mPositiveButton.setBackground(new ToastDialogButton(backgroundPositive, radius, radius, radius, radius));

        regPositiveListener();

        addView(mPositiveButton);
    }
    /**
     * ==================================================== 创建按钮 方式2====================================================================
     */
    /**
     * 创建取消按钮 方式2：有个父布局，按钮居中显示
     *
     * @param radius
     */
    private void createNegative2(int radius) {
        //父布局
        LinearLayout layout = new LinearLayout(getContext());
        //这是
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mNegativeParams.btnHeightF, 1);
        //子控件居中

        //父布局背景色
        int relativeLayoutbg = mNegativeParams.btn_parent_bg != 0 ? mNegativeParams.btn_parent_bg : DialogColor.common_bg;
        //左下角圆角
        layout.setBackground(new BottonParentView(relativeLayoutbg, 0, 0, radius, 0));

        //子控件 按钮
        mNegativeButton = new ScaleTextView(getContext());
        LinearLayout.LayoutParams childLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mNegativeParams.btnHeight);
        childLp.gravity = Gravity.CENTER;
        childLp.setMarginStart(DialogDimens.BUTTON_Padding);
        childLp.setMarginEnd(DialogDimens.BUTTON_Padding);


        mNegativeButton.setText(mNegativeParams.btn_text);
        mNegativeButton.setTextSize(mNegativeParams.btnTxtSize);
        mNegativeButton.setTextColor(mNegativeParams.btnTxtColor);
        //        mNegativeButton.setWidth(mNegativeParams.btnWidth);
        mNegativeButton.setHeight(mNegativeParams.btnHeight);

        //如果取消按钮没有背景色，则使用默认色
        int backgroundNegative = mNegativeParams.btn_bg_cl != 0 ? mNegativeParams.btn_bg_cl : DialogColor.btn_bg_cancel_cl;

        //按钮切圆角
        mNegativeButton.setBackground(new ToastDialogButton(backgroundNegative, radius, radius, radius, radius));

        regNegativeListener();

        layout.addView(mNegativeButton, childLp);
        addView(layout, rlp);
    }

    /**
     * 创建确定按钮  方式2：有个父布局，按钮居中显示
     *
     * @param radius
     */
    private void createPositive2(int radius) {
        //父布局
        LinearLayout layout = new LinearLayout(getContext());
        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mNegativeParams.btnHeightF, 1);

        //父布局背景色
        int relativeLayoutbg = mNegativeParams.btn_parent_bg != 0 ? mNegativeParams.btn_parent_bg : DialogColor.common_bg;
        //右下角 圆角
        layout.setBackground(new BottonParentView(relativeLayoutbg, 0, 0, 0, radius));

        //子控件

        mPositiveButton = new ScaleTextView(getContext());
        LinearLayout.LayoutParams childLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mNegativeParams.btnHeight);
        childLp.gravity = Gravity.CENTER;
        childLp.setMarginStart(DialogDimens.BUTTON_Padding);
        childLp.setMarginEnd(DialogDimens.BUTTON_Padding);

        mPositiveButton.setText(mPositiveParams.btn_text);
        mPositiveButton.setTextSize(mPositiveParams.btnTxtSize);
        mPositiveButton.setTextColor(mPositiveParams.btnTxtColor);
        //        mPositiveButton.setWidth(mNegativeParams.btnWidth);
        mPositiveButton.setHeight(mPositiveParams.btnHeight);

        //如果取消按钮没有背景色，则使用默认色
        int backgroundPositive = mPositiveParams.btn_bg_cl != 0 ? mPositiveParams.btn_bg_cl : DialogColor.btn_bg_sure_cl;

        //按钮切圆角
        mPositiveButton.setBackground(new ToastDialogButton(backgroundPositive, radius, radius, radius, radius));

        regPositiveListener();

        layout.addView(mPositiveButton, childLp);
        addView(layout, rlp);
    }

    /**
     * 取消按钮监听
     */
    private void regNegativeListener() {
        mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNegativeParams.dismiss();
                if (mNegativeParams.listener != null)
                    mNegativeParams.listener.onClick(v);
            }
        });
    }

    /**
     * 确定按钮监听
     */
    private void regPositiveListener() {
        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPositiveParams.dismiss();
                if (mPositiveParams.listener != null)
                    mPositiveParams.listener.onClick(v);
            }
        });
    }

    public void regOnInputClickListener(final EditText input) {
        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = input.getText().toString();
                if (!TextUtils.isEmpty(text))
                    mPositiveParams.dismiss();
                if (mPositiveParams.inputListener != null)
                    mPositiveParams.inputListener.onClick(text, v);
            }
        });
    }
}
