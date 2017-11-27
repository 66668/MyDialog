package lib.sjy.dialog.callback;


import lib.sjy.dialog.params.ContentTextParams;

/**
 * 具体设置的抽象
 * 需要在act/frg构建的时候具体实现
 */

public abstract class ConfigText {
    public abstract void onConfig(ContentTextParams params);
}
