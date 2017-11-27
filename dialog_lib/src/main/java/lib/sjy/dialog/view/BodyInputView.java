package lib.sjy.dialog.view;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

import lib.sjy.dialog.drawable.InputDrawable;
import lib.sjy.dialog.drawable.ToastDialogDrawable;
import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.params.ButtonParams;
import lib.sjy.dialog.params.DialogParams;
import lib.sjy.dialog.params.InputParams;
import lib.sjy.dialog.params.TitleParams;
import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.view.baseview.ScaleEditText;
import lib.sjy.dialog.view.baseview.ScaleLinearLayout;


/**
 * Created by hupei on 2017/3/31.
 */

class BodyInputView extends ScaleLinearLayout {
    private ScaleEditText mEditText;

    public BodyInputView(Context context, AllParams params) {
        super(context);
        init(context, params);
    }

    private void init(Context context, AllParams params) {
        DialogParams dialogParams = params.dialogParams;
        TitleParams titleParams = params.titleParams;
        InputParams inputParams = params.inputParams;
        ButtonParams negativeParams = params.negativeParams;
        ButtonParams positiveParams = params.positiveParams;

        //如果标题没有背景色，则使用默认色
        int backgroundColor = inputParams.backgroundColor != 0 ? inputParams.backgroundColor :
                DialogColor.common_bg;

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
        else {
            setBackgroundColor(backgroundColor);
        }

        mEditText = new ScaleEditText(context);
        mEditText.setHint(inputParams.hintText);
        mEditText.setHintTextColor(inputParams.hintTextColor);
        mEditText.setTextSize(inputParams.textSize);
        mEditText.setTextColor(inputParams.textColor);
        mEditText.setHeight(inputParams.inputHeight);

        int backgroundResourceId = inputParams.inputBackgroundResourceId;
        if (backgroundResourceId == 0) {
            mEditText.setBackground(new InputDrawable(inputParams.strokeWidth, inputParams.strokeColor, inputParams.inputBackgroundColor));

        } else
            mEditText.setBackgroundResource(backgroundResourceId);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        int[] margins = inputParams.margins;
        if (margins != null) {
            layoutParams.setMargins(margins[0], margins[1], margins[2], margins[3]);
        }
        addView(mEditText, layoutParams);
    }

    public EditText getInput() {
        return mEditText;
    }
}
