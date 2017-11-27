package lib.sjy.dialog.callback;


import lib.sjy.dialog.params.InputParams;

/**
 * 具体设置的抽象
 * 需要在act/frg构建的时候具体实现
 */

public abstract class ConfigInput {
    public abstract void onConfig(InputParams params);
}
