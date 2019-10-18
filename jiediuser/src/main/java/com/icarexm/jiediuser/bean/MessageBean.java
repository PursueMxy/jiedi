package com.icarexm.jiediuser.bean;

import java.util.List;

public class MessageBean {

    /**
     * code : 200
     * msg :
     * time : 1571383564
     * data : {"list":[{"id":52,"title":"标题","user_id":12,"user_type":"0","typedata":"3","content":"内容","createtime":"2020年05月28日 18:57","status":"1","deletetime":null,"link":null,"memo":"222","updatetime":1571383514}]}
     */

    private int code;
    private String msg;
    private String time;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 52
             * title : 标题
             * user_id : 12
             * user_type : 0
             * typedata : 3
             * content : 内容
             * createtime : 2020年05月28日 18:57
             * status : 1
             * deletetime : null
             * link : null
             * memo : 222
             * updatetime : 1571383514
             */

            private int id;
            private String title;
            private int user_id;
            private String user_type;
            private String typedata;
            private String content;
            private String createtime;
            private String status;
            private Object deletetime;
            private Object link;
            private String memo;
            private int updatetime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getUser_type() {
                return user_type;
            }

            public void setUser_type(String user_type) {
                this.user_type = user_type;
            }

            public String getTypedata() {
                return typedata;
            }

            public void setTypedata(String typedata) {
                this.typedata = typedata;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getDeletetime() {
                return deletetime;
            }

            public void setDeletetime(Object deletetime) {
                this.deletetime = deletetime;
            }

            public Object getLink() {
                return link;
            }

            public void setLink(Object link) {
                this.link = link;
            }

            public String getMemo() {
                return memo;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public int getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(int updatetime) {
                this.updatetime = updatetime;
            }
        }
    }
}
