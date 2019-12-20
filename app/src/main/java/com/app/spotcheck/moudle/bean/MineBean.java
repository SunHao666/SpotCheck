package com.app.spotcheck.moudle.bean;

/**
 * @ClassName: MineBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 20:32
 */
public class MineBean {


    /**
     * LUB_LIST : {"LUB_NUM":4,"LUB_FINISHDATE":"2019-12-03"}
     * CHK_LIST : {"CHK_NUM":4,"CHK_FINISHDATE":"2019-12-03"}
     */

    private LUBLISTBean LUB_LIST;
    private CHKLISTBean CHK_LIST;

    public LUBLISTBean getLUB_LIST() {
        return LUB_LIST;
    }

    public void setLUB_LIST(LUBLISTBean LUB_LIST) {
        this.LUB_LIST = LUB_LIST;
    }

    public CHKLISTBean getCHK_LIST() {
        return CHK_LIST;
    }

    public void setCHK_LIST(CHKLISTBean CHK_LIST) {
        this.CHK_LIST = CHK_LIST;
    }

    public static class LUBLISTBean {
        /**
         * LUB_NUM : 4
         * LUB_FINISHDATE : 2019-12-03
         */

        private int LUB_NUM;
        private String LUB_FINISHDATE;

        public int getLUB_NUM() {
            return LUB_NUM;
        }

        public void setLUB_NUM(int LUB_NUM) {
            this.LUB_NUM = LUB_NUM;
        }

        public String getLUB_FINISHDATE() {
            return LUB_FINISHDATE;
        }

        public void setLUB_FINISHDATE(String LUB_FINISHDATE) {
            this.LUB_FINISHDATE = LUB_FINISHDATE;
        }
    }

    public static class CHKLISTBean {
        /**
         * CHK_NUM : 4
         * CHK_FINISHDATE : 2019-12-03
         */

        private int CHK_NUM;
        private String CHK_FINISHDATE;

        public int getCHK_NUM() {
            return CHK_NUM;
        }

        public void setCHK_NUM(int CHK_NUM) {
            this.CHK_NUM = CHK_NUM;
        }

        public String getCHK_FINISHDATE() {
            return CHK_FINISHDATE;
        }

        public void setCHK_FINISHDATE(String CHK_FINISHDATE) {
            this.CHK_FINISHDATE = CHK_FINISHDATE;
        }
    }
}
