package com.app.spotcheck.moudle.event;

/**
 * @ClassName: SpotCheckEvent
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/24 19:03
 */
public class SpotCheckEvent {
    public int tab;
    public SpotCheckEvent(int tab) {
        this.tab = tab;
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }
}
