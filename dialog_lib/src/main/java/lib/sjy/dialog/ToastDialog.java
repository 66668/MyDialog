package lib.sjy.dialog;


import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;

import lib.sjy.dialog.callback.ConfigButton;
import lib.sjy.dialog.callback.ConfigDialog;
import lib.sjy.dialog.callback.ConfigImage;
import lib.sjy.dialog.callback.ConfigInput;
import lib.sjy.dialog.callback.ConfigItems;
import lib.sjy.dialog.callback.ConfigProgress;
import lib.sjy.dialog.callback.ConfigText;
import lib.sjy.dialog.callback.ConfigTitle;
import lib.sjy.dialog.listener.OnInputClickListener;
import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.params.ButtonParams;
import lib.sjy.dialog.params.ContentTextParams;
import lib.sjy.dialog.params.DialogParams;
import lib.sjy.dialog.params.ImageParams;
import lib.sjy.dialog.params.InputParams;
import lib.sjy.dialog.params.ItemsParams;
import lib.sjy.dialog.params.ProgressParams;
import lib.sjy.dialog.params.TitleParams;


/**
 * 弹窗提示 主控件
 * 各个样式的弹窗 这里直接控制
 * Created by sjy on 2017/11/23.
 */

public class ToastDialog {
    private ToastDialogImpl mDialog;

    private ToastDialog() {

    }

    /**
     * Buidler中调用
     *
     * @param params
     */
    public DialogFragment dialogCreate(AllParams params) {
        if (mDialog == null) {
            mDialog = ToastDialogImpl.newInstance(params);
        } else {
            if (mDialog.getDialog() != null && mDialog.getDialog().isShowing()) {
                mDialog.refreshView();
            }
        }
        return mDialog;
    }

    /**
     * Buidler中调用
     *
     * @param activity
     */
    public void dialogShow(FragmentActivity activity) {
        mDialog.show(activity.getSupportFragmentManager(), "dialog");
    }


    /**
     * 设置静态内部类 的调用
     * 距离都是px
     * 颜色都是 0xFFFFFFF样式
     */
    public static class Builder {
        private FragmentActivity fragmentActivity;
        private ToastDialog dialog;
        private AllParams params;

        public Builder(@NonNull FragmentActivity activity) {
            this.fragmentActivity = activity;
            params = new AllParams();
            params.dialogParams = new DialogParams();
        }

        public DialogFragment create() {
            if (dialog == null)
                dialog = new ToastDialog();
            return dialog.dialogCreate(params);
        }

        public DialogFragment show() {
            DialogFragment dialogFragment = create();
            dialog.dialogShow(fragmentActivity);
            return dialogFragment;
        }

        private void onDismiss() {
            if (params.dialogFragment != null) {
                params.dialogFragment.dismiss();
                fragmentActivity = null;
                params.dialogFragment = null;
            }
        }

        /**
         * ========================================================================
         * ====================================弹窗相关====================================
         * ========================================================================
         */

        /**
         * 设置对话框位置
         *
         * @param gravity 位置
         * @return builder
         */
        public Builder setGravity(int gravity) {
            params.dialogParams.gravity = gravity;
            return this;
        }

        /**
         * 设置对话框点击外部关闭
         *
         * @param cancel true允许
         * @return Builder
         */
        public Builder setCanceledOnTouchOutside(boolean cancel) {
            params.dialogParams.isOutsideTouchEnabled = cancel;
            return this;
        }

        /**
         * 设置对话框返回键关闭
         *
         * @param cancel true允许
         * @return Builder
         */
        public Builder setCancelable(boolean cancel) {
            params.dialogParams.cancelable = cancel;
            return this;
        }

        /**
         * 设置对话框宽度
         *
         * @param width 0.0 - 1.0
         * @return Builder
         */
        public Builder setWidth(@FloatRange(from = 0.0, to = 1.0) float width) {
            params.dialogParams.width = width;
            return this;
        }

        /**
         * 设置对话框圆角
         *
         * @param radius px 默认20px
         * @return Builder
         */
        public Builder setRadiusPx(int radius) {
            params.dialogParams.radius = radius;
            return this;
        }

        /**
         * 设置padding（该设置较依赖具体的屏幕宽高）
         * <p>
         * 暴力的边距弹窗，设置参数后，弹窗宽会去掉 paddingLeft和 paddingRight的宽，高会去掉paddingTop的高
         */
        public Builder setPadding(int paddingLeft, int paddingTop, int paddingRight, int paddingBottom) {
            params.dialogParams.mPadding = new int[]{paddingLeft, paddingTop, paddingRight, paddingBottom};
            return this;
        }

        public Builder configDialog(@NonNull ConfigDialog configDialog) {
            configDialog.onConfig(params.dialogParams);
            return this;
        }

        /**
         *
         * ========================================================================
         * ====================================title相关====================================
         * ========================================================================
         */
        /**
         * 设置标题
         *
         * @param text
         * @return
         */
        public Builder setTitle(@NonNull String text) {
            newTitleParams();
            params.titleParams.text = text;
            return this;
        }

        public Builder setTitleColor(@ColorInt int color) {
            newTitleParams();
            params.titleParams.textColor = color;
            return this;
        }

        public Builder configTitle(@NonNull ConfigTitle configTitle) {
            newTitleParams();
            configTitle.onConfig(params.titleParams);
            return this;
        }

        private void newTitleParams() {
            if (params.titleParams == null)
                params.titleParams = new TitleParams();
        }

        /**
         * ========================================================================
         * ====================================按钮相关====================================
         * ========================================================================
         */

        /**
         * 确定按钮
         *
         * @param text
         * @param listener
         * @return
         */
        public Builder setPositive(@NonNull String text, View.OnClickListener listener) {
            newPositiveParams();
            ButtonParams paramss = params.positiveParams;
            paramss.btn_text = text;
            paramss.listener = listener;
            return this;
        }

        /**
         * 确定按钮的详细设置
         *
         * @param configButton
         * @return
         */
        public Builder configPositive(@NonNull ConfigButton configButton) {
            newPositiveParams();
            configButton.onConfig(params.positiveParams);
            return this;
        }

        /**
         * 创建
         */
        private void newPositiveParams() {
            if (params.positiveParams == null)
                params.positiveParams = new ButtonParams() {
                    @Override
                    public void dismiss() {
                        onDismiss();
                    }
                };
        }

        /**
         * 取消按钮
         *
         * @param text
         * @param listener
         * @return
         */
        public Builder setNegative(@NonNull String text, View.OnClickListener listener) {
            newNegativeParams();
            ButtonParams paramss = params.negativeParams;
            paramss.btn_text = text;
            paramss.listener = listener;
            return this;
        }

        /**
         * 取消按钮的详细设置
         *
         * @param configButton
         * @return
         */
        public Builder configNegative(@NonNull ConfigButton configButton) {
            newNegativeParams();
            configButton.onConfig(params.negativeParams);
            return this;
        }

        /**
         * 创建
         */
        private void newNegativeParams() {
            if (params.negativeParams == null)
                params.negativeParams = new ButtonParams() {
                    @Override
                    public void dismiss() {
                        onDismiss();
                    }
                };
        }

        /**
         * 输入确定
         *
         * @param text
         * @param listener
         * @return
         */
        public Builder setPositiveInput(@NonNull String text, OnInputClickListener listener) {
            newPositiveParams();
            ButtonParams paramss = params.positiveParams;
            paramss.btn_text = text;
            paramss.inputListener = listener;
            return this;
        }


        /**
         * ========================================================================
         * ====================================文本内容相关====================================
         * ========================================================================
         */

        /**
         * 设置文本内容
         *
         * @param text
         * @return
         */
        public Builder setContentText(@NonNull String text) {
            newTextParams();
            params.textParams.text = text;
            return this;
        }

        /**
         * 设置文本颜色
         *
         * @param color
         * @return
         */
        public Builder setContentColor(@ColorInt int color) {
            newTextParams();
            params.textParams.textColor = color;
            return this;
        }

        public Builder configText(@NonNull ConfigText configText) {
            newTextParams();
            configText.onConfig(params.textParams);
            return this;
        }

        private void newTextParams() {
            //判断是否已经设置过
            if (params.dialogParams.gravity == Gravity.NO_GRAVITY) {
                params.dialogParams.gravity = Gravity.CENTER;
            }
            if (params.textParams == null)
                params.textParams = new ContentTextParams();
        }

        /**
         * ========================================================================
         * ====================================图片+文本内容相关====================================
         * ========================================================================
         */

        public Builder setSuccessText(@NonNull String text) {
            newImgTextParams();
            params.imageParams.isSuccess = true;
            params.imageParams.text = text;
            return this;

        }

        public Builder setSuccessTextColor(@ColorInt int color) {
            newImgTextParams();
            params.imageParams.isSuccess = true;
            params.imageParams.textColor = color;
            return this;
        }

        public Builder setFailedText(@NonNull String text) {
            newImgTextParams();
            params.imageParams.isSuccess = false;
            params.imageParams.text = text;
            return this;

        }

        public Builder setFailedTextColor(@ColorInt int color) {
            newImgTextParams();
            params.imageParams.isSuccess = false;
            params.imageParams.textColor = color;
            return this;
        }

        /**
         * 具体细节
         *
         * @param configImage
         * @return
         */
        public Builder configImage(@NonNull ConfigImage configImage) {
            newImgTextParams();
            configImage.onConfig(params.imageParams);
            return this;
        }

        private void newImgTextParams() {
            if (params.imageParams == null) {
                params.imageParams = new ImageParams();
            }
        }
        /**
         * ========================================================================
         * ====================================输入框内容相关====================================
         * ========================================================================
         */

        /**
         * 提示语
         *
         * @param text
         * @return
         */
        public Builder setInputHint(@NonNull String text) {
            newInputParams();
            params.inputParams.hintText = text;
            return this;
        }

        /**
         * 输入框高度
         *
         * @param height
         * @return
         */
        public Builder setInputHeight(int height) {
            newInputParams();
            params.inputParams.inputHeight = height;
            return this;
        }

        public Builder configInput(@NonNull ConfigInput configInput) {
            newInputParams();
            configInput.onConfig(params.inputParams);
            return this;
        }

        private void newInputParams() {
            //判断是否已经设置过
            if (params.dialogParams.gravity == Gravity.NO_GRAVITY)
                params.dialogParams.gravity = Gravity.CENTER;
            if (params.inputParams == null)
                params.inputParams = new InputParams();
        }

        /**
         * ========================================================================
         * ====================================进度条内容相关====================================
         * ========================================================================
         */
        /**
         * 设置进度条文本
         *
         * @param text 进度条文本，style = 水平样式时，支持String.format() 例如：已经下载%s
         * @return
         */
        public Builder setProgressText(@NonNull String text) {
            newProgressParams();
            params.progressParams.text = text;
            return this;
        }

        /**
         * 设置进度条样式
         *
         * @param style {@link ProgressParams#STYLE_HORIZONTAL 水平样式} or
         *              {@link ProgressParams#STYLE_SPINNER}
         * @return
         */
        public Builder setProgressStyle(int style) {
            newProgressParams();
            params.progressParams.style = style;
            return this;
        }

        public Builder setProgress(int max, int progress) {
            newProgressParams();
            ProgressParams progressParams = params.progressParams;
            progressParams.max = max;
            progressParams.progress = progress;
            return this;
        }

        public Builder setProgressDrawable(@DrawableRes int progressDrawableId) {
            newProgressParams();
            params.progressParams.progressDrawableId = progressDrawableId;
            return this;
        }

        public Builder setProgressHeight(int height) {
            newProgressParams();
            params.progressParams.progressHeight = height;
            return this;
        }

        public Builder configProgress(@NonNull ConfigProgress configProgress) {
            newProgressParams();
            configProgress.onConfig(params.progressParams);
            return this;
        }

        private void newProgressParams() {
            //判断是否已经设置过
            if (params.dialogParams.gravity == Gravity.NO_GRAVITY)
                params.dialogParams.gravity = Gravity.CENTER;
            if (params.progressParams == null)
                params.progressParams = new ProgressParams();
        }

        /**
         * ========================================================================
         * ====================================item相关====================================
         * ========================================================================
         */
        public Builder setItems(@NonNull Object items, AdapterView.OnItemClickListener listener) {
            newItemsParams();
            ItemsParams paramss = params.itemsParams;
            paramss.items = items;
            paramss.listener = listener;
            return this;
        }


        public Builder configItems(@NonNull ConfigItems configItems) {
            newItemsParams();
            configItems.onConfig(params.itemsParams);
            return this;
        }

        private void newItemsParams() {
            //设置列表特殊的参数
            DialogParams dialogParams = params.dialogParams;
            //判断是否已经设置过
            if (dialogParams.gravity == Gravity.NO_GRAVITY)
                dialogParams.gravity = Gravity.BOTTOM;//默认底部显示
            //判断是否已经设置过
            if (dialogParams.yOff == 0)
                dialogParams.yOff = 20;//底部与屏幕的距离

            if (params.itemsParams == null)
                params.itemsParams = new ItemsParams() {
                    @Override
                    public void dismiss() {
                        onDismiss();
                    }
                };
        }

    }

}
