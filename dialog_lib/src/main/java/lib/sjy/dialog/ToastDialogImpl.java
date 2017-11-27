package lib.sjy.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.params.DialogParams;


/**
 * 弹窗提示 实现类
 * Created by sjy on 2017/11/23.
 */

public final class ToastDialogImpl extends BaseToastDialog {
    private AllParams mParams;
    private Controller controller;

    /**
     * 创建
     *
     * @param params
     * @return
     */
    public static ToastDialogImpl newInstance(AllParams params) {
        ToastDialogImpl dialog = new ToastDialogImpl();
        dialog.mParams = params;
        Bundle bundle = new Bundle();
        bundle.putParcelable(ToastDialogConstant.SAVED_PARAMS, params);
        dialog.setArguments(bundle);
        return dialog;
    }

    /**
     * 弹窗消失
     *
     * @param dialog
     */
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        mParams = null;
    }

    /**
     * 设置弹窗样式参数
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 获取参数
         */
        if (savedInstanceState != null) {
            mParams = savedInstanceState.getParcelable(ToastDialogConstant.SAVED_PARAMS);
        } else {
            mParams = getArguments().getParcelable(ToastDialogConstant.SAVED_PARAMS);
        }

        //弹窗参数设置
        DialogParams dialogParams = mParams.dialogParams;
        setDialogGravity(dialogParams.gravity);//弹窗位置
        setOutsideTouchEnabled(dialogParams.isOutsideTouchEnabled);//是否外部关闭
        setBackDownEnabled(dialogParams.cancelable);//后退键关闭
        setDialogWidth(dialogParams.width);//弹窗宽度
//        setDialogHeigh(dialogParams.height);//弹窗高度
        //padding
        int[] mPadding = dialogParams.mPadding;
        if (mPadding != null) {
            setDialogPadding(mPadding[0], mPadding[1], mPadding[2], mPadding[3]);
        }
        setDialogAnim(dialogParams.animStyle);//动画
        setDimEnabled(dialogParams.isDimEnabled);//背景变暗
        setDialogBackgroundColor(dialogParams.backgroundColor);//弹窗背景色
        setDialogRadius(dialogParams.radius);//圆角
        setDialogAlpha(dialogParams.alpha);//透明度
        setDialogX(dialogParams.xOff);//偏移
        setDialogY(dialogParams.yOff);//偏移

    }

    /**
     * 保存参数
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(ToastDialogConstant.SAVED_PARAMS, mParams);
    }

    /**
     * 重写父类抽象方法
     *
     * @param context
     * @param inflater
     * @param container
     * @return
     */
    @Override
    public View createDialogView(Context context, LayoutInflater inflater, ViewGroup container) {
        controller = new Controller(getContext(), mParams);
        mParams.dialogFragment = this;
        return controller.createView();
    }

    public void refreshView() {
        controller.refreshView();
    }
}
