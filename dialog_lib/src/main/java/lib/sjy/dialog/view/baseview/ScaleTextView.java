package lib.sjy.dialog.view.baseview;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;

import lib.sjy.dialog.scale.ScaleUtils;


/**
 * textView修改 padding 高度和字体大小
 */

public class ScaleTextView extends android.support.v7.widget.AppCompatTextView {
    public ScaleTextView(Context context) {
        super(context);
        config();
    }

    private void config() {
        setGravity(Gravity.CENTER);
    }

    @Override
    public void setHeight(int pixels) {
        int dimenHeight = ScaleUtils.scaleValue(pixels);
        super.setMinHeight(dimenHeight);
    }

    @Override
    public void setWidth(int pixels) {
        int dimenHeight = ScaleUtils.scaleValue(pixels);
        super.setMinWidth(dimenHeight);
    }

    @Override
    public void setTextSize(float size) {
        int dimenTextSize = ScaleUtils.scaleValue((int) size);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenTextSize);
    }


    public void setAutoPadding(int left, int top, int right, int bottom) {
        int dimenLeft = ScaleUtils.scaleValue(left);
        int dimenTop = ScaleUtils.scaleValue(top);
        int dimenRight = ScaleUtils.scaleValue(right);
        int dimenBottom = ScaleUtils.scaleValue(bottom);
        super.setPadding(dimenLeft, dimenTop, dimenRight, dimenBottom);
    }
}
