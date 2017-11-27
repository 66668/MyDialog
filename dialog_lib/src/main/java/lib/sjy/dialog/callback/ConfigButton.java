package lib.sjy.dialog.callback;


import lib.sjy.dialog.params.ButtonParams;

/**
 * 具体设置的抽象
 * 需要在act/frg构建的时候具体实现
 */

public abstract class ConfigButton {
    public abstract void onConfig(ButtonParams params);
}
