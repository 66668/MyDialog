package lib.sjy.dialog.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import lib.sjy.dialog.drawable.ToastDialogButton;
import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.params.ButtonParams;
import lib.sjy.dialog.scale.ScaleUtils;
import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.value.DialogDimens;
import lib.sjy.dialog.view.baseview.ScaleTextView;


/**
 * 列表对话框的取消按钮视图
 * Created by hupei on 2017/3/30.
 */
class ItemsButton extends ScaleTextView {

    public ItemsButton(Context context, AllParams params) {
        super(context);
        init(params);
    }

    private void init(AllParams params) {
        ButtonParams negativeParams = params.negativeParams;
        final ButtonParams buttonParams = negativeParams != null ? negativeParams : params
                .positiveParams;
        //为列表显示时，设置列表与按钮之间的距离
        if (params.itemsParams != null)
            buttonParams.topMargin = DialogDimens.BUTTON_ITEMS_MARGIN;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup
                .LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1);
        layoutParams.topMargin = ScaleUtils.scaleValue(buttonParams.topMargin);
        setLayoutParams(layoutParams);

        setClickable(true);
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonParams.dismiss();
                if (buttonParams.listener != null)
                    buttonParams.listener.onClick(v);
            }
        });
        setText(buttonParams.btn_text);
        setTextSize(buttonParams.btnTxtSize);
        setTextColor(buttonParams.btnTxtColor);
        setHeight(buttonParams.btnHeight);

        //如果取消按钮没有背景色，则使用默认色
        int backgroundColor = buttonParams.btn_bg_cl != 0 ? buttonParams.btn_bg_cl :
                DialogColor.common_bg;
        int radius = params.dialogParams.radius;
        setBackground(new ToastDialogButton(backgroundColor, radius, radius, radius, radius));

    }
}
