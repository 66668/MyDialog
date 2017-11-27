package lib.sjy.dialog.view;

import android.content.Context;

import lib.sjy.dialog.drawable.ToastDialogDrawable;
import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.params.ButtonParams;
import lib.sjy.dialog.params.ContentTextParams;
import lib.sjy.dialog.params.DialogParams;
import lib.sjy.dialog.params.TitleParams;
import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.view.baseview.ScaleTextView;


/**
 * 对话框纯文本视图
 */
class BodyContentTextView extends ScaleTextView {
    private AllParams mParams;

    public BodyContentTextView(Context context, AllParams params) {
        super(context);
        this.mParams = params;
        init(params);
    }

    private void init(AllParams params) {
        DialogParams dialogParams = params.dialogParams;
        TitleParams titleParams = params.titleParams;
        ContentTextParams textParams = params.textParams;
        ButtonParams negativeParams = params.negativeParams;
        ButtonParams positiveParams = params.positiveParams;

        setGravity(textParams.gravity);

        //如果标题没有背景色，则使用默认色
        int backgroundColor = textParams.text_bg_cl != 0 ? textParams.text_bg_cl :
                DialogColor.content_bg_cl;

        //有标题没按钮则底部圆角
        if (titleParams != null && negativeParams == null && positiveParams == null) {
            setBackground(new ToastDialogDrawable(backgroundColor, 0, 0, dialogParams.radius, dialogParams.radius));
        }
        //没标题有按钮则顶部圆角
        else if (titleParams == null && (negativeParams != null || positiveParams != null)) {
            setBackground(new ToastDialogDrawable(backgroundColor, dialogParams.radius, dialogParams.radius, 0, 0));
        }
        //没标题没按钮则全部圆角
        else if (titleParams == null && negativeParams == null && positiveParams == null) {
            setBackground(new ToastDialogDrawable(backgroundColor, dialogParams.radius));
        }
        //有标题有按钮则不用考虑圆角
        else
            setBackgroundColor(backgroundColor);

        setMinHeight(textParams.height);
        setTextColor(textParams.textColor);
        setTextSize(textParams.textSize);
        setText(textParams.text);

        int[] padding = textParams.padding;
        if (padding != null)
            setAutoPadding(padding[0], padding[1], padding[2], padding[3]);
    }

    public void refreshText() {
        if (mParams.textParams == null)
            return;
        post(new Runnable() {
            @Override
            public void run() {
                setText(mParams.textParams.text);
            }
        });
    }
}
