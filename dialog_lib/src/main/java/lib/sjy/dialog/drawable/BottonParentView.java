package lib.sjy.dialog.drawable;

import android.graphics.drawable.ShapeDrawable;

/**
 * 绘制需要的图形样式 用于按钮的父布局
 * 该处设置 有圆角的白色Rect
 * Created by sjy on 2017/11/23.
 */

public class BottonParentView extends ShapeDrawable {

    public BottonParentView(int bgColor, int Radius) {
        this(bgColor, Radius, Radius, Radius, Radius);
    }

    public BottonParentView(int bgcolor, int leftTopRadius, int rightTopRadius, int leftBottomRadius, int rightBottomRadius) {
        getPaint().setColor(bgcolor);
        //绘制圆角
        setShape(ToastDialogDrawableUtil.getRoundRectShape(leftTopRadius, rightTopRadius, leftBottomRadius, rightBottomRadius));
    }
}
