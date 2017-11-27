package lib.sjy.dialog.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import lib.sjy.dialog.listener.OnInputClickListener;
import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.value.DialogDimens;


/**
 * botton参数
 * Created by sjy on 2017/11/23.
 */

public class ButtonParams implements Parcelable {


    public void dismiss() {

    }

    /**
     * 按钮点击事件
     */
    public View.OnClickListener listener;
    /**
     * 输入框确定事件
     */
    public OnInputClickListener inputListener;
    /**
     * 按钮框与顶部距离
     */
    public int topMargin;

    /**
     * 按钮文本颜色
     */
    public int btnTxtColor = DialogColor.btn_text_srue_cl;

    /**
     * 按钮文本大小
     */
    public int btnTxtSize = DialogDimens.BUTTON_TEXT_SIZE;

    /**
     * 按钮父布局高度
     */
    public int btnHeightF = DialogDimens.BUTTON_PARENT_HEIGHT;

    /**
     * 按钮高度
     */
    public int btnHeight = DialogDimens.BUTTON_HEIGHT;
    /**
     * 按钮宽度
     */
    public int btnWidth = DialogDimens.BUTTON_WIDTH;

    /**
     * 按钮背景颜色
     */
    public int btn_bg_cl;

    /**
     * 按钮父布局背景色
     */
    public int btn_parent_bg = DialogColor.common_bg;

    /**
     * 按钮文本
     */
    public String btn_text;

    public ButtonParams() {

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(topMargin);
        dest.writeInt(btnTxtColor);
        dest.writeInt(btnTxtSize);
        dest.writeInt(btnHeightF);
        dest.writeInt(btnHeight);
        dest.writeInt(btnWidth);
        dest.writeInt(btn_bg_cl);
        dest.writeInt(btn_parent_bg);
        dest.writeString(btn_text);
    }

    protected ButtonParams(Parcel in) {
        topMargin = in.readInt();
        btnTxtColor = in.readInt();
        btnTxtSize = in.readInt();
        btnHeightF = in.readInt();
        btnHeight = in.readInt();
        btnWidth = in.readInt();
        btn_bg_cl = in.readInt();
        btn_parent_bg = in.readInt();
        btn_text = in.readString();
    }

    public static final Creator<ButtonParams> CREATOR = new Creator<ButtonParams>() {
        @Override
        public ButtonParams createFromParcel(Parcel in) {
            return new ButtonParams(in);
        }

        @Override
        public ButtonParams[] newArray(int size) {
            return new ButtonParams[size];
        }
    };


}
