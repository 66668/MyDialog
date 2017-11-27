package lib.sjy.dialog.params;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;

import lib.sjy.dialog.value.DialogDimens;


/**
 * 弹窗参数 默认值
 * Created by sjy on 2017/11/23.
 */

public class DialogParams implements Parcelable {
    /**
     * 对话框的位置
     */
    public int gravity = Gravity.NO_GRAVITY;

    /**
     * 是否触摸外部关闭
     */
    public boolean isOutsideTouchEnabled = true;
    /**
     * 返回键是否关闭
     */
    public boolean cancelable = true;
    /**
     * 对话框透明度，范围：0-1；1不透明
     */
    public float alpha = 1f;
    /**
     * 对话框宽度，范围：0-1；1整屏宽
     */
    public float width = 0.85f;

    /**
     * 对话框高度
     */

    public float height = 0.35f;

    /**
     * 对话框与屏幕边距
     */
    public int[] mPadding;

    /**
     * 对话框弹出动画,StyleRes
     */
    public int animStyle;

    /**
     * 对话框刷新动画，AnimRes
     */
    public int refreshAnimation;

    /**
     * 对话框背景是否昏暗，默认true
     */
    public boolean isDimEnabled = true;

    /**
     * 对话框的背景色透明，因为列表模式情况，内容与按钮中间有距离
     */
    public int backgroundColor = Color.TRANSPARENT;

    /**
     * 对话框的圆角半径
     */
    public int radius = DialogDimens.RADIUS;

    /**
     * 对话框 x 坐标偏移
     */
    public int xOff;
    /**
     * 对话框 y 坐标偏移
     */
    public int yOff;


    public DialogParams() {
    }


    protected DialogParams(Parcel in) {
        gravity = in.readInt();
        isOutsideTouchEnabled = in.readByte() != 0;
        cancelable = in.readByte() != 0;
        alpha = in.readFloat();
        width = in.readFloat();
        height = in.readInt();
        mPadding = in.createIntArray();
        animStyle = in.readInt();
        refreshAnimation = in.readInt();
        isDimEnabled = in.readByte() != 0;
        backgroundColor = in.readInt();
        radius = in.readInt();
        xOff = in.readInt();
        yOff = in.readInt();
    }

    public static final Creator<DialogParams> CREATOR = new Creator<DialogParams>() {
        @Override
        public DialogParams createFromParcel(Parcel in) {
            return new DialogParams(in);
        }

        @Override
        public DialogParams[] newArray(int size) {
            return new DialogParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(gravity);
        dest.writeByte((byte) (isOutsideTouchEnabled ? 1 : 0));
        dest.writeByte((byte) (cancelable ? 1 : 0));
        dest.writeFloat(alpha);
        dest.writeFloat(width);
        dest.writeFloat(height);
        dest.writeIntArray(mPadding);
        dest.writeInt(animStyle);
        dest.writeInt(refreshAnimation);
        dest.writeByte((byte) (isDimEnabled ? 1 : 0));
        dest.writeInt(backgroundColor);
        dest.writeInt(radius);
        dest.writeInt(xOff);
        dest.writeInt(yOff);
    }

}
