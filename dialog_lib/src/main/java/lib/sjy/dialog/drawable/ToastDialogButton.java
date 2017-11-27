package lib.sjy.dialog.drawable;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;

import lib.sjy.dialog.value.DialogColor;


/**
 * 按钮的背景，有点击效果
 * Created by hupei on 2017/3/30.
 */

public class ToastDialogButton extends StateListDrawable {

    public ToastDialogButton(int backgroundColor, int leftTopRadius, int rightTopRadius, int rightBottomRadius, int
            leftBottomRadius) {
        //按下
        ShapeDrawable drawablePress = new ShapeDrawable(ToastDialogDrawableUtil.getRoundRectShape(leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius));
        drawablePress.getPaint().setColor(DialogColor.buttonPress);
        //默认
        ShapeDrawable defaultDrawable = new ShapeDrawable(ToastDialogDrawableUtil.getRoundRectShape(leftTopRadius, rightTopRadius, rightBottomRadius, leftBottomRadius));
        defaultDrawable.getPaint().setColor(backgroundColor);

        addState(new int[]{android.R.attr.state_pressed}, drawablePress);
        addState(new int[]{-android.R.attr.state_pressed}, defaultDrawable);
    }
}
