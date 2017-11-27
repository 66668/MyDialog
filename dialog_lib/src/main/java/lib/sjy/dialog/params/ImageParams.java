package lib.sjy.dialog.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;

import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.value.DialogDimens;


/**
 * 图片内容参数
 * Created by sjy on 2017/11/23.
 */

public class ImageParams implements Parcelable {
    /**
     * body文本内间距
     */
    public int[] padding;

    public boolean isSuccess = true;//默认是true

    /**
     * 文本
     */
    public String text;

    /**
     * 文本高度
     */
    public int height = DialogDimens.TEXT_HEIGHT;

    /**
     * 文本背景颜色
     */
    public int text_bg_cl;

    /**
     * 文本字体颜色
     */
    public int textColor = DialogColor.content_txt_cl;

    /**
     * 文本字体大小
     */
    public int textSize = DialogDimens.CONTENT_TEXT_SIZE;

    /**
     * 位置
     */
    public int gravity = Gravity.CENTER;


    public ImageParams() {

    }


    protected ImageParams(Parcel in) {
        padding = in.createIntArray();
        isSuccess = in.readByte() != 0;
        text = in.readString();
        height = in.readInt();
        text_bg_cl = in.readInt();
        textColor = in.readInt();
        textSize = in.readInt();
        gravity = in.readInt();
    }

    public static final Creator<ImageParams> CREATOR = new Creator<ImageParams>() {
        @Override
        public ImageParams createFromParcel(Parcel in) {
            return new ImageParams(in);
        }

        @Override
        public ImageParams[] newArray(int size) {
            return new ImageParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(padding);
        dest.writeByte((byte) (isSuccess ? 1 : 0));
        dest.writeString(text);
        dest.writeInt(height);
        dest.writeInt(text_bg_cl);
        dest.writeInt(textColor);
        dest.writeInt(textSize);
        dest.writeInt(gravity);
    }
}
