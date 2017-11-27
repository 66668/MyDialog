package lib.sjy.dialog.view.baseview;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import lib.sjy.dialog.value.DialogColor;


/**
 * 分隔线，默认垂直
 * Created by hupei on 2017/3/30.
 */
public class DividerView extends View {
    public DividerView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setLayoutParams(new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT));
        setBackgroundColor(DialogColor.divider);
    }

    /**
     * 将分隔方式为水平分隔
     */
    public void setVertical() {
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 2));//2px-->1dp
    }
}
