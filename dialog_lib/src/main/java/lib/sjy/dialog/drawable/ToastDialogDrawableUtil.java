package lib.sjy.dialog.drawable;

import android.graphics.drawable.shapes.RoundRectShape;

/**
 * 绘制 图像圆角的工具类
 * Created by sjy on 2017/11/23.
 */

public class ToastDialogDrawableUtil {
    /**
     * 设置图形圆角
     *
     * @param leftTop
     * @param rightTop
     * @param leftBottom
     * @param rightBottom
     * @return
     */
    public static RoundRectShape getRoundRectShape(int leftTop, int rightTop, int leftBottom, int rightBottom) {
        float outerRadii[] = new float[8];
        if (leftTop > 0) {
            outerRadii[0] = leftTop;
            outerRadii[1] = leftTop;
        }
        if (rightTop > 0) {
            outerRadii[2] = rightTop;
            outerRadii[3] = rightTop;
        }
        if (rightBottom > 0) {
            outerRadii[4] = rightBottom;
            outerRadii[5] = rightBottom;
        }
        if (leftBottom > 0) {
            outerRadii[6] = leftBottom;
            outerRadii[7] = leftBottom;
        }
        return new RoundRectShape(outerRadii, null, null);

    }
}
