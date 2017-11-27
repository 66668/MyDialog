package lib.sjy.dialog;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.view.BuildViewImpl;
import lib.sjy.dialog.view.baseview.IBuildView;


/**
 * 样式控制器
 * <p>
 * Created by sjy on 2017/11/23.
 */

public class Controller {
    private Context context;
    private AllParams mParams;
    private IBuildView buildView;

    public Controller(Context context, AllParams params) {
        this.context = context;
        this.mParams = params;
        buildView = new BuildViewImpl(context, params);
    }

    public View createView() {
        applyRoot();
        applyTitle();
        applyBody();
        return getView();
    }

    /**
     * 创建根布局
     */
    private void applyRoot() {
        buildView.buildRootView();
    }

    /**
     * 创建标题布局
     */
    private void applyTitle() {
        if (mParams.titleParams != null)
            buildView.buildTitleView();
    }

    /**
     * 创建按钮布局
     */
    private void applyButton() {

        //有确定并且有取消按钮
        if (mParams.positiveParams != null && mParams.negativeParams != null)
            buildView.buildMultipleButtonView();
            //有确定或者有取消按钮
        else if (mParams.positiveParams != null || mParams.negativeParams != null)
            buildView.buildSingleButtonView();
    }

    /**
     * 各种内容布局
     */
    private void applyBody() {


        if (mParams.textParams != null) { //文本
            buildView.buildTextView();
            applyButton();

        } else if (mParams.imageParams != null) { // 成功 失败图片
            buildView.buildImageView();
            applyButton();

        } else if (mParams.itemsParams != null) { //列表
            buildView.buildItems();
            //有确定或者有取消按钮
            if (mParams.positiveParams != null || mParams.negativeParams != null)
                buildView.buildItemsButton();

        } else if (mParams.progressParams != null) {  //进度条
            buildView.buildProgress();
            applyButton();

        } else if (mParams.inputParams != null) {    //输入框
            buildView.buildInputView();
            applyButton();
            buildView.regInputListener();

        }

    }

    private View getView() {
        return buildView.getView();
    }

    public void refreshView() {
        buildView.refreshText();
        buildView.refreshItems();
        buildView.refreshProgress();
        //刷新时带动画
        if (mParams.dialogParams.refreshAnimation != 0 && getView() != null)
            getView().post(new Runnable() {
                @Override
                public void run() {
                    Animation animation = AnimationUtils.loadAnimation(context, mParams.dialogParams.refreshAnimation);
                    if (animation != null)
                        getView().startAnimation(animation);
                }
            });
    }
}
