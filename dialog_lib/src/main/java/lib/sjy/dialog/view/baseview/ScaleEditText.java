package lib.sjy.dialog.view.baseview;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;

import lib.sjy.dialog.scale.ScaleUtils;


/**
 * editText修改
 * Created by hupei on 2017/3/31.
 */
public class ScaleEditText extends android.support.v7.widget.AppCompatEditText {
    public ScaleEditText(Context context) {
        super(context);
        config();
    }

    private void config() {
        requestFocus();
        setFocusable(true);
        setFocusableInTouchMode(true);
        setGravity(Gravity.TOP | Gravity.LEFT);
    }

    @Override
    public void setHeight(int pixels) {
        int dimenHeight = ScaleUtils.scaleValue(pixels);
        super.setHeight(dimenHeight);
    }

    @Override
    public void setTextSize(float size) {
        int dimenTextSize = ScaleUtils.scaleValue((int) size);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, dimenTextSize);
    }
}
