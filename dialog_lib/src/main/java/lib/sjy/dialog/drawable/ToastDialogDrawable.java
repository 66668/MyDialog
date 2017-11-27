package lib.sjy.dialog.drawable;

import android.graphics.drawable.ShapeDrawable;

/**
 * 绘制需要的图形样式
 * 该处设置 有圆角的白色Rect
 * Created by sjy on 2017/11/23.
 */

public class ToastDialogDrawable extends ShapeDrawable {

    public ToastDialogDrawable(int bgColor, int Radius) {
        this(bgColor, Radius, Radius, Radius, Radius);
    }

    public ToastDialogDrawable(int bgcolor, int leftTopRadius, int rightTopRadius, int leftBottomRadius, int rightBottomRadius) {
        getPaint().setColor(bgcolor);
        //绘制圆角
        setShape(ToastDialogDrawableUtil.getRoundRectShape(leftTopRadius, rightTopRadius, leftBottomRadius, rightBottomRadius));
    }
}
