package com.app.spotcheck.moudle.bean;

/**
 * @ClassName: HomeBean
 * @Description: java类作用描述
 * @Author: 作者名
 * @CreateDate: 2019/12/18 15:56
 */
public class HomeBean {

    /**
     * CHKINFO : {"CHK_EXECENDTIME":"2019-11-27","CHK_RECID":"17848907a2d14d348bca2ca93e22259c","CHK_MAINID":"E006","CHK_MAINNAME":"测试卸船机","CHK_PARTID":"M04P003","CHK_PARTNAME":"俯仰机构","CHK_UNNUM":1,"CHK_EXECSTARTTIME":"2019-11-24"}
     * LUBINFO : {"LUB_PARTNAME":"润滑部位A","LUB_RECID":"65f0e53698d045089132a047e371ebdf","LUB_MAINID":"E001","LUB_PARTID":"94acaa661576483eaf1bec2dce1a2ec2","LUB_UNNUM":1,"LUB_MAINNAME":"1#取料机","LUB_EXECENDTIME":null,"LUB_EXECSTARTTIME":null}
     */

    private CHKINFOBean CHKINFO;
    private LUBINFOBean LUBINFO;

    public CHKINFOBean getCHKINFO() {
        return CHKINFO;
    }

    public void setCHKINFO(CHKINFOBean CHKINFO) {
        this.CHKINFO = CHKINFO;
    }

    public LUBINFOBean getLUBINFO() {
        return LUBINFO;
    }

    public void setLUBINFO(LUBINFOBean LUBINFO) {
        this.LUBINFO = LUBINFO;
    }

    public static class CHKINFOBean {
        /**
         * CHK_EXECENDTIME : 2019-11-27
         * CHK_RECID : 17848907a2d14d348bca2ca93e22259c
         * CHK_MAINID : E006
         * CHK_MAINNAME : 测试卸船机
         * CHK_PARTID : M04P003
         * CHK_PARTNAME : 俯仰机构
         * CHK_UNNUM : 1
         * CHK_EXECSTARTTIME : 2019-11-24
         */

        private String CHK_EXECENDTIME;
        private String CHK_RECID;
        private String CHK_MAINID;
        private String CHK_MAINNAME;
        private String CHK_PARTID;
        private String CHK_PARTNAME;
        private int CHK_UNNUM;
        private String CHK_EXECSTARTTIME;


        public String getCHK_EXECENDTIME() {
            return CHK_EXECENDTIME;
        }

        public void setCHK_EXECENDTIME(String CHK_EXECENDTIME) {
            this.CHK_EXECENDTIME = CHK_EXECENDTIME;
        }

        public String getCHK_RECID() {
            return CHK_RECID;
        }

        public void setCHK_RECID(String CHK_RECID) {
            this.CHK_RECID = CHK_RECID;
        }

        public String getCHK_MAINID() {
            return CHK_MAINID;
        }

        public void setCHK_MAINID(String CHK_MAINID) {
            this.CHK_MAINID = CHK_MAINID;
        }

        public String getCHK_MAINNAME() {
            return CHK_MAINNAME;
        }

        public void setCHK_MAINNAME(String CHK_MAINNAME) {
            this.CHK_MAINNAME = CHK_MAINNAME;
        }

        public String getCHK_PARTID() {
            return CHK_PARTID;
        }

        public void setCHK_PARTID(String CHK_PARTID) {
            this.CHK_PARTID = CHK_PARTID;
        }

        public String getCHK_PARTNAME() {
            return CHK_PARTNAME;
        }

        public void setCHK_PARTNAME(String CHK_PARTNAME) {
            this.CHK_PARTNAME = CHK_PARTNAME;
        }

        public int getCHK_UNNUM() {
            return CHK_UNNUM;
        }

        public void setCHK_UNNUM(int CHK_UNNUM) {
            this.CHK_UNNUM = CHK_UNNUM;
        }

        public String getCHK_EXECSTARTTIME() {
            return CHK_EXECSTARTTIME;
        }

        public void setCHK_EXECSTARTTIME(String CHK_EXECSTARTTIME) {
            this.CHK_EXECSTARTTIME = CHK_EXECSTARTTIME;
        }
    }

    public static class LUBINFOBean {
        /**
         * LUB_PARTNAME : 润滑部位A
         * LUB_RECID : 65f0e53698d045089132a047e371ebdf
         * LUB_MAINID : E001
         * LUB_PARTID : 94acaa661576483eaf1bec2dce1a2ec2
         * LUB_UNNUM : 1
         * LUB_MAINNAME : 1#取料机
         * LUB_EXECENDTIME : null
         * LUB_EXECSTARTTIME : null
         */

        private String LUB_PARTNAME;
        private String LUB_RECID;
        private String LUB_MAINID;
        private String LUB_PARTID;
        private int LUB_UNNUM;
        private String LUB_MAINNAME;
        private Object LUB_EXECENDTIME;
        private Object LUB_EXECSTARTTIME;

        public String getLUB_PARTNAME() {
            return LUB_PARTNAME;
        }

        public void setLUB_PARTNAME(String LUB_PARTNAME) {
            this.LUB_PARTNAME = LUB_PARTNAME;
        }

        public String getLUB_RECID() {
            return LUB_RECID;
        }

        public void setLUB_RECID(String LUB_RECID) {
            this.LUB_RECID = LUB_RECID;
        }

        public String getLUB_MAINID() {
            return LUB_MAINID;
        }

        public void setLUB_MAINID(String LUB_MAINID) {
            this.LUB_MAINID = LUB_MAINID;
        }

        public String getLUB_PARTID() {
            return LUB_PARTID;
        }

        public void setLUB_PARTID(String LUB_PARTID) {
            this.LUB_PARTID = LUB_PARTID;
        }

        public int getLUB_UNNUM() {
            return LUB_UNNUM;
        }

        public void setLUB_UNNUM(int LUB_UNNUM) {
            this.LUB_UNNUM = LUB_UNNUM;
        }

        public String getLUB_MAINNAME() {
            return LUB_MAINNAME;
        }

        public void setLUB_MAINNAME(String LUB_MAINNAME) {
            this.LUB_MAINNAME = LUB_MAINNAME;
        }

        public Object getLUB_EXECENDTIME() {
            return LUB_EXECENDTIME;
        }

        public void setLUB_EXECENDTIME(Object LUB_EXECENDTIME) {
            this.LUB_EXECENDTIME = LUB_EXECENDTIME;
        }

        public Object getLUB_EXECSTARTTIME() {
            return LUB_EXECSTARTTIME;
        }

        public void setLUB_EXECSTARTTIME(Object LUB_EXECSTARTTIME) {
            this.LUB_EXECSTARTTIME = LUB_EXECSTARTTIME;
        }
    }
}
