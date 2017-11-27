package lib.sjy.dialog.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;

import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.value.DialogDimens;


/**
 * 文本内容参数
 */

public class ContentTextParams implements Parcelable {
    /**
     * body文本内间距
     */
    public int[] padding;

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

    public ContentTextParams() {
    }

    public ContentTextParams(Parcel in) {
        padding = in.createIntArray();
        text = in.readString();
        height = in.readInt();
        text_bg_cl = in.readInt();
        textColor = in.readInt();
        textSize = in.readInt();
        gravity = in.readInt();
    }

    public static final Creator<ContentTextParams> CREATOR = new Creator<ContentTextParams>() {
        @Override
        public ContentTextParams createFromParcel(Parcel in) {
            return new ContentTextParams(in);
        }

        @Override
        public ContentTextParams[] newArray(int size) {
            return new ContentTextParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(padding);
        dest.writeString(text);
        dest.writeInt(height);
        dest.writeInt(text_bg_cl);
        dest.writeInt(textColor);
        dest.writeInt(textSize);
        dest.writeInt(gravity);
    }
}
