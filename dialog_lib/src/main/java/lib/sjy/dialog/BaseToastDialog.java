package lib.sjy.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import lib.sjy.dialog.drawable.ToastDialogDrawable;
import lib.sjy.dialog.scale.ScaleUtils;
import lib.sjy.dialog.value.DialogDimens;


/**
 * 自定义的 抽象基类
 * Created by sjy on 2017/11/23.
 */

public abstract class BaseToastDialog extends DialogFragment {

    public abstract View createDialogView(Context context, LayoutInflater inflater, ViewGroup container);

    private int dialog_gravity = Gravity.CENTER;//对话框的位置 居中
    private boolean isOutsideTouchEnabled = true;//是否触摸外部关闭
    private boolean isBackDownEnabled = true;//是否返回键关闭
    private boolean isDimEnabled = true;//背景是否变暗

    private float dialog_width = 0.85f;//对话框宽度，范围：0-1；1整屏宽
    //    private float dialog_height = 0.35f;//对话框高度 项目UI需求 px
    private int[] dialog_padding;//对话框的padding距离
    private int[] dialog_margin;//对话框的margin距离
    private int dialog_anim;//动画样式

    private int dialog_bg = Color.TRANSPARENT;//对话框背景色 默认context的界面颜色
    private int dialog_radius = DialogDimens.RADIUS;
    private float dialog_alpha = 1f;//弹窗透明度 范围：0-1；1不透明
    private int dialog_X, dialog_Y;//偏移坐标


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置 无标题 无边框
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        //获取保存
        if (savedInstanceState != null) {
            dialog_gravity = savedInstanceState.getInt(ToastDialogConstant.SAVED_GRAVITY);
            isOutsideTouchEnabled = savedInstanceState.getBoolean(ToastDialogConstant.SAVED_TOUCH_OUT);
            isBackDownEnabled = savedInstanceState.getBoolean(ToastDialogConstant.SAVED_CANCELED_BACK);
            isDimEnabled = savedInstanceState.getBoolean(ToastDialogConstant.SAVED_DIM_ENABLED);

            dialog_width = savedInstanceState.getFloat(ToastDialogConstant.SAVED_WIDTH);
            //            dialog_height = savedInstanceState.getInt(ToastDialogConstant.SAVED_HEIGH);
            dialog_padding = savedInstanceState.getIntArray(ToastDialogConstant.SAVED_PADDING);
            dialog_margin = savedInstanceState.getIntArray(ToastDialogConstant.SAVED_MARGIN);

            dialog_anim = savedInstanceState.getInt(ToastDialogConstant.SAVED_ANIM_STYLE);
            dialog_bg = savedInstanceState.getInt(ToastDialogConstant.SAVED_BACKGROUND_COLOR);
            dialog_radius = savedInstanceState.getInt(ToastDialogConstant.SAVED_RADIUS);
            dialog_alpha = savedInstanceState.getFloat(ToastDialogConstant.SAVED_ALPHA);

            dialog_X = savedInstanceState.getInt(ToastDialogConstant.SAVED_X);
            dialog_Y = savedInstanceState.getInt(ToastDialogConstant.SAVED_Y);
        }

    }

    /**
     * 保存
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(ToastDialogConstant.SAVED_GRAVITY, dialog_gravity);
        outState.putBoolean(ToastDialogConstant.SAVED_TOUCH_OUT, isOutsideTouchEnabled);
        outState.putBoolean(ToastDialogConstant.SAVED_CANCELED_BACK, isBackDownEnabled);
        outState.putBoolean(ToastDialogConstant.SAVED_DIM_ENABLED, isDimEnabled);

        outState.putFloat(ToastDialogConstant.SAVED_WIDTH, dialog_width);
        //        outState.putFloat(ToastDialogConstant.SAVED_HEIGH, dialog_height);

        if (dialog_padding != null && dialog_padding.length > 0) {
            outState.putIntArray(ToastDialogConstant.SAVED_PADDING, dialog_padding);
        }

        if (dialog_margin != null && dialog_margin.length > 0) {
            outState.putIntArray(ToastDialogConstant.SAVED_MARGIN, dialog_margin);
        }
        outState.putInt(ToastDialogConstant.SAVED_ANIM_STYLE, dialog_anim);

        outState.putInt(ToastDialogConstant.SAVED_BACKGROUND_COLOR, dialog_bg);
        outState.putInt(ToastDialogConstant.SAVED_RADIUS, dialog_radius);
        outState.putFloat(ToastDialogConstant.SAVED_ALPHA, dialog_alpha);

        outState.putInt(ToastDialogConstant.SAVED_X, dialog_X);
        outState.putInt(ToastDialogConstant.SAVED_Y, dialog_Y);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = createDialogView(getContext(), inflater, container);
        //设置 背景色 和圆角
        view.setBackground(new ToastDialogDrawable(dialog_bg, dialog_radius));
        //设置透明度
        view.setAlpha(dialog_alpha);
        return view;
    }

    @Override
    public void onStart() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.setCanceledOnTouchOutside(isOutsideTouchEnabled);
            dialog.setCancelable(isBackDownEnabled);
            initDialog(dialog);//设置弹窗布局
        }
        super.onStart();
    }

    /**
     * 绘制/创建弹窗
     * 并实现各个参数
     *
     * @param dialog
     */
    private void initDialog(Dialog dialog) {

        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowManager.LayoutParams wlp = window.getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        //获取屏幕宽
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);

        wlp.width = (int) (dm.widthPixels * dialog_width);//弹窗宽度
        //        wlp.height = (int) (dm.widthPixels * dialog_height);//高度不设置，wrap样式，避免子控件高度超出
        wlp.gravity = dialog_gravity;
        wlp.x = dialog_X;
        wlp.y = dialog_Y;

        //边距
        if (dialog_padding != null) {
            int[] mPadding = dialog_padding;
            wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
            wlp.height = WindowManager.LayoutParams.MATCH_PARENT;

            window.getDecorView().setPadding(ScaleUtils.scaleValue(mPadding[0]),
                    ScaleUtils.scaleValue(mPadding[1]),
                    ScaleUtils.scaleValue(mPadding[2]),
                    ScaleUtils.scaleValue(mPadding[3]));
        }

        //动画
        if (dialog_anim != 0) {
            window.setWindowAnimations(dialog_anim);
        }

        //弹窗背景变暗
        if (isDimEnabled) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }
        window.setAttributes(wlp);

    }

    @Override
    public void show(FragmentManager manager, String tag) {
        if (!isAdded()) {
            android.support.v4.app.FragmentTransaction transaction = manager.beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.add(this, tag);
            transaction.commitAllowingStateLoss();
        }
    }

    public void remove() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.remove(this);
        ft.addToBackStack(null);
    }


    /**
     * 设置对话框位置
     * {@link Gravity#CENTER 默认}
     *
     * @param dialog_gravity
     */
    public void setDialogGravity(int dialog_gravity) {
        this.dialog_gravity = dialog_gravity;
    }

    /**
     * 设置对话框点击外部关闭
     *
     * @param outsideTouchEnabled true允许
     */
    public void setOutsideTouchEnabled(boolean outsideTouchEnabled) {
        isOutsideTouchEnabled = outsideTouchEnabled;
    }

    /**
     * 设置对话框返回键关闭关闭
     *
     * @param backDownEnabled true允许
     */
    public void setBackDownEnabled(boolean backDownEnabled) {
        isBackDownEnabled = backDownEnabled;
    }

    /**
     * 设置背景是否昏暗，默认true
     *
     * @param dimEnabled true昏暗
     */
    public void setDimEnabled(boolean dimEnabled) {
        isDimEnabled = dimEnabled;
    }

    /**
     * 设置对话框宽度
     *
     * @param dialog_width 0.0 - 1.0
     */
    public void setDialogWidth(float dialog_width) {
        this.dialog_width = dialog_width;
    }

//    /**
//     * 设置对话框高度 默认175dp
//     *
//     * @param dialog_height int
//     */
//    public void setDialogHeigh(float dialog_height) {
//        this.dialog_height = dialog_height;
//    }

    /**
     * 设置边距
     *
     * @param left   px
     * @param top    px
     * @param right  px
     * @param bottom px
     */
    public void setDialogPadding(int left, int top, int right, int bottom) {
        this.dialog_padding = new int[]{left, top, right, bottom};
    }

    public void setDialog_margin(int[] dialog_margin) {
        this.dialog_margin = dialog_margin;
    }

    /**
     * 弹出对话框的动画
     *
     * @param dialog_anim StyleRes
     */
    public void setDialogAnim(int dialog_anim) {
        this.dialog_anim = dialog_anim;
    }

    /**
     * 设置对话框背景色
     *
     * @param dialog_bg 颜色值
     */
    public void setDialogBackgroundColor(int dialog_bg) {
        this.dialog_bg = dialog_bg;
    }

    /**
     * 设置对话框圆角
     *
     * @param dialog_radius 半径
     */
    public void setDialogRadius(int dialog_radius) {
        this.dialog_radius = dialog_radius;
    }

    /**
     * 设置对话框透明度
     *
     * @param dialog_alpha 0.0 - 1.0
     */
    public void setDialogAlpha(float dialog_alpha) {
        this.dialog_alpha = dialog_alpha;
    }

    public void setDialogX(int dialog_X) {
        this.dialog_X = dialog_X;
    }

    public void setDialogY(int dialog_Y) {
        this.dialog_Y = dialog_Y;
    }
}
