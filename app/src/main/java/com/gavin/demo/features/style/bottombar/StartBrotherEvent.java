package com.gavin.demo.features.style.bottombar;

import me.yokeyword.fragmentation.SupportFragment;

/**
 * tab子页打开父级fragment同级页面事件
 *
 * @author gavin.xiong 2016/12/2
 */
public class StartBrotherEvent {
    public SupportFragment targetFragment;

    public StartBrotherEvent(SupportFragment targetFragment) {
        this.targetFragment = targetFragment;
    }
}
