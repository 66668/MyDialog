package lib.sjy.dialog.callback;


import lib.sjy.dialog.params.DialogParams;

/**
 * 具体设置的抽象
 * 需要在act/frg构建的时候具体实现
 */
public abstract class ConfigDialog {
    public abstract void onConfig(DialogParams params);
}
