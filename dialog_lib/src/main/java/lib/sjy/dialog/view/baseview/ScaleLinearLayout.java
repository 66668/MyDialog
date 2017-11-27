package lib.sjy.dialog.view.baseview;

import android.content.Context;
import android.widget.LinearLayout;

import lib.sjy.dialog.scale.ScaleLayoutConfig;


/**
 * Created by hupei on 2017/3/29.
 */
public class ScaleLinearLayout extends LinearLayout {

    public ScaleLinearLayout(Context context) {
        super(context);
        //初始化操作
        ScaleLayoutConfig.init(context.getApplicationContext());
    }
}
