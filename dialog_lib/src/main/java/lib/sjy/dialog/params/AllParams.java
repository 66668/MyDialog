package lib.sjy.dialog.params;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;

/**
 * 弹窗 按钮 标题 内容的参数设置
 * Created by hupei on 2017/3/30.
 */

public final class AllParams implements Parcelable {

    public DialogFragment dialogFragment;
    public DialogParams dialogParams;
    public TitleParams titleParams;
    public ContentTextParams textParams;
    public ButtonParams negativeParams;
    public ButtonParams positiveParams;
    public ItemsParams itemsParams;
    public ProgressParams progressParams;
    public InputParams inputParams;
    public ImageParams imageParams;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.dialogParams, flags);
        dest.writeParcelable(this.titleParams, flags);
        dest.writeParcelable(this.textParams, flags);
        dest.writeParcelable(this.negativeParams, flags);
        dest.writeParcelable(this.positiveParams, flags);
        dest.writeParcelable(this.itemsParams, flags);
        dest.writeParcelable(this.progressParams, flags);
        dest.writeParcelable(this.inputParams, flags);
        dest.writeParcelable(this.imageParams, flags);
    }

    public AllParams() {
    }

    protected AllParams(Parcel in) {
        this.dialogParams = in.readParcelable(DialogParams.class.getClassLoader());
        this.titleParams = in.readParcelable(TitleParams.class.getClassLoader());
        this.textParams = in.readParcelable(ContentTextParams.class.getClassLoader());
        this.negativeParams = in.readParcelable(ButtonParams.class.getClassLoader());
        this.positiveParams = in.readParcelable(ButtonParams.class.getClassLoader());
        this.itemsParams = in.readParcelable(ItemsParams.class.getClassLoader());
        this.progressParams = in.readParcelable(ProgressParams.class.getClassLoader());
        this.inputParams = in.readParcelable(InputParams.class.getClassLoader());
        this.imageParams = in.readParcelable(ImageParams.class.getClassLoader());
    }

    public static final Creator<AllParams> CREATOR = new Creator<AllParams>() {
        @Override
        public AllParams createFromParcel(Parcel source) {
            return new AllParams(source);
        }

        @Override
        public AllParams[] newArray(int size) {
            return new AllParams[size];
        }
    };
}
