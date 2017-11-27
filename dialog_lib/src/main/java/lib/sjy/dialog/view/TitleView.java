package lib.sjy.dialog.view;

import android.content.Context;

import lib.sjy.dialog.drawable.ToastDialogDrawable;
import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.params.DialogParams;
import lib.sjy.dialog.params.TitleParams;
import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.view.baseview.ScaleTextView;


/**
 * 对话框标题
 */
class TitleView extends ScaleTextView {

    public TitleView(Context context, AllParams params) {
        super(context);
        init(params);
    }

    private void init(AllParams params) {
        DialogParams dialogParams = params.dialogParams;
        TitleParams titleParams = params.titleParams;

        setGravity(titleParams.gravity);

        //如果标题没有背景色，则使用默认色
        int backgroundColor = titleParams.backgroundColor != 0 ? titleParams.backgroundColor : DialogColor.title_bg_cl;

        setBackground(new ToastDialogDrawable(backgroundColor, dialogParams.radius, dialogParams.radius, 0, 0));


        setHeight(titleParams.height);
        setTextColor(titleParams.textColor);//该处颜色 bug
        setTextSize(titleParams.textSize);
        setText(titleParams.text);
    }
}
