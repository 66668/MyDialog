package lib.sjy.dialog.view;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import lib.sjy.dialog.params.AllParams;
import lib.sjy.dialog.view.baseview.DividerView;
import lib.sjy.dialog.view.baseview.IBuildView;
import lib.sjy.dialog.view.baseview.ScaleLinearLayout;


/**
 * 创建弹窗各个布局的具体实现
 */

public class BuildViewImpl implements IBuildView {
    private Context mContext;
    private AllParams mParams;
    private LinearLayout mRootView;//根视图
    private TitleView titleView;//标题视图

    private BodyContentTextView mBodyTextView;//纯文本视图
    private BodyImgTextView mBodyImgTextView;//文本+图片视图
    private MultipleButton mMultipleButton;//两个按钮视图
    private SingleButton mSingleButton;//一个按钮视图

    private BodyItemsView mBodyItemsView;
    private BodyProgressView mBodyProgressView;
    private BodyInputView mBodyInputView;
    private ItemsButton mItemsButton;

    public BuildViewImpl(Context context, AllParams params) {
        this.mContext = context;
        this.mParams = params;
    }

    /**
     * 创建 根布局
     */
    @Override
    public void buildRootView() {
        if (mRootView == null) {
            mRootView = new ScaleLinearLayout(mContext);
            mRootView.setOrientation(LinearLayout.VERTICAL);
        }

    }

    /**
     * 创建 标题布局
     * 要求有横线
     */
    @Override
    public void buildTitleView() {
        if (titleView == null) {
            titleView = new TitleView(mContext, mParams);
            DividerView dividerView = new DividerView(mContext);
            dividerView.setVertical();
            mRootView.addView(titleView);
            mRootView.addView(dividerView);
        }
    }

    /**
     * 创建 文本布局
     */
    @Override
    public void buildTextView() {
        if (mBodyTextView == null) {
            mBodyTextView = new BodyContentTextView(mContext, mParams);
            mRootView.addView(mBodyTextView);
        }
    }

    @Override
    public void refreshText() {
        if (mBodyTextView != null) {
            mBodyTextView.refreshText();
        }
    }

    @Override
    public void buildItems() {
        if (mBodyItemsView == null) {
            mBodyItemsView = new BodyItemsView(mContext, mParams);
            mRootView.addView(mBodyItemsView);
        }

    }

    @Override
    public void buildItemsButton() {
        if (mItemsButton == null) {
            mItemsButton = new ItemsButton(mContext, mParams);
            mRootView.addView(mItemsButton);
        }
    }

    @Override
    public void refreshItems() {
        if (mBodyItemsView != null)
            mBodyItemsView.refreshItems();
    }

    /**
     * 创建加载布局
     */
    @Override
    public void buildProgress() {
        if (mBodyProgressView == null) {
            mBodyProgressView = new BodyProgressView(mContext, mParams);
            mRootView.addView(mBodyProgressView);
        }
    }

    @Override
    public void refreshProgress() {
        if (mBodyProgressView != null)
            mBodyProgressView.refreshProgress();
    }

    /**
     * 创建输入框布局
     */
    @Override
    public void buildInputView() {
        if (mBodyInputView == null) {
            mBodyInputView = new BodyInputView(mContext, mParams);
            mRootView.addView(mBodyInputView);
        }
    }

    @Override
    public void buildMultipleButtonView() {
        if (mMultipleButton == null) {
            mMultipleButton = new MultipleButton(mContext, mParams);
            mRootView.addView(mMultipleButton);
        }
    }

    @Override
    public void buildSingleButtonView() {
        if (mSingleButton == null) {
            mSingleButton = new SingleButton(mContext, mParams);
            mRootView.addView(mSingleButton);
        }
    }

    @Override
    public void buildImageView() {
        if (mBodyImgTextView == null) {
            mBodyImgTextView = new BodyImgTextView(mContext, mParams);
            mRootView.addView(mBodyImgTextView);
        }
    }

    @Override
    public void regInputListener() {
        if (mSingleButton != null && mBodyInputView != null)
            mSingleButton.regOnInputClickListener(mBodyInputView.getInput());

        if (mMultipleButton != null && mBodyInputView != null)
            mMultipleButton.regOnInputClickListener(mBodyInputView.getInput());
    }

    @Override
    public View getView() {
        return mRootView;
    }
}
