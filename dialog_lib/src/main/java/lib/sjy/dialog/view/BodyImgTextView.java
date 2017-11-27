package lib.sjy.dialog.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import lib.sjy.dialog.R;
import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.params.ImageParams;
import lib.sjy.dialog.value.DialogColor;
import lib.sjy.dialog.view.baseview.ScaleTextView;


/**
 * 对话框图片+文本视图
 */
class BodyImgTextView extends ScaleTextView {
    private AllParams mParams;

    public BodyImgTextView(Context context, AllParams params) {
        super(context);
        this.mParams = params;
        init(params);
    }

    private void init(AllParams params) {

        ImageParams imageParams = params.imageParams;
        Drawable drawable;
        setGravity(imageParams.gravity);
        if (imageParams.isSuccess) {
            drawable = ContextCompat.getDrawable(getContext(), R.mipmap.id_success);
        } else {
            drawable = ContextCompat.getDrawable(getContext(), R.mipmap.id_failed);
        }
        setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
        setCompoundDrawablePadding(10);//px
        setPadding(0, 50, 0, 0);

        //如果标题没有背景色，则使用默认色
        int backgroundColor = imageParams.text_bg_cl != 0 ? imageParams.text_bg_cl : DialogColor.content_bg_cl;
        setBackgroundColor(backgroundColor);

        setMinHeight(imageParams.height);
        setTextColor(imageParams.textColor);
        setTextSize(imageParams.textSize);
        setText(imageParams.text);

        refreshDrawableState();

        int[] padding = imageParams.padding;
        if (padding != null)
            setAutoPadding(padding[0], padding[1], padding[2], padding[3]);
    }

    public void refreshText() {
        if (mParams.textParams == null)
            return;
        post(new Runnable() {
            @Override
            public void run() {
                setText(mParams.textParams.text);
            }
        });
    }
}
