package lib.sjy.dialog.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;

import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.value.DialogDimens;


/**
 * 标题参数
 * 没赋值 走默认值
 * Created by sjy on 2017/11/23.
 */

public class TitleParams implements Parcelable {
    /**
     * 标题
     */
    public String text;
    /**
     * 标题高度
     */
    public int height = DialogDimens.TITLE_HEIGHT;

    /**
     * 标题字体大小
     */
    public int textSize = DialogDimens.TITLE_TEXT_SIZE;
    /**
     * 标题字体颜色
     */
    public int textColor = DialogColor.title_cl;
    /**
     * 标题背景颜色
     */
    public int backgroundColor = DialogColor.title_bg_cl;

    public int gravity = Gravity.CENTER;

    public TitleParams() {
    }


    protected TitleParams(Parcel in) {
        text = in.readString();
        height = in.readInt();
        textSize = in.readInt();
        textColor = in.readInt();
        backgroundColor = in.readInt();
        gravity = in.readInt();
    }

    public static final Creator<TitleParams> CREATOR = new Creator<TitleParams>() {
        @Override
        public TitleParams createFromParcel(Parcel in) {
            return new TitleParams(in);
        }

        @Override
        public TitleParams[] newArray(int size) {
            return new TitleParams[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeInt(height);
        dest.writeInt(textSize);
        dest.writeInt(textColor);
        dest.writeInt(backgroundColor);
        dest.writeInt(gravity);
    }
}
