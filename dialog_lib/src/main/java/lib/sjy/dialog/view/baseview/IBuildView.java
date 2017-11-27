package lib.sjy.dialog.view.baseview;

import android.view.View;

/**
 * 创建弹窗各个布局的接口
 */

public interface IBuildView {
    /**
     * 生成根布局
     */
    void buildRootView();

    /**
     * 生成标题布局
     */
    void buildTitleView();

    /**
     * 生成文本布局
     */
    void buildTextView();

    /**
     * 刷新文本内容
     */
    void refreshText();

    /**
     * 生成列表布局
     */
    void buildItems();

    /**
     * 生成列表按钮
     */
    void buildItemsButton();

    /**
     * 刷新列表内容
     */
    void refreshItems();



    /**
     * 生成输入布局
     */
    void buildInputView();

    /**
     * 生成多按钮布局
     */
    void buildMultipleButtonView();

    /**
     * 生成单个按钮布局
     */
    void buildSingleButtonView();

    /**
     * 生成图片+内容布局
     */
    void buildImageView();

    /**
     * 注册输入框确定事件
     */
    void regInputListener();
    /**
     * 生成进度条布局
     */
    void buildProgress();

    /**
     * 刷新进度条
     */
    void refreshProgress();

    /**
     * 取出根布局
     *
     * @return 对话框视图
     */
    View getView();
}
