package net.shopnc.b2b2c.android.bean;

/**
 * Created by xws on 2017/2/15.
 */

public class PromotionAgreement {


    /**
     * code : 200
     * datas : {"joinAgreement":{"articleId":100,"title":"推广会员申请认证协议","categoryId":20,"url":"","content":"","createTime":"2016-02-26 17:18:21","allowDelete":0,"sort":0,"categoryTitle":""}}
     */

    private int code;
    /**
     * joinAgreement : {"articleId":100,"title":"推广会员申请认证协议","categoryId":20,"url":"","content":"","createTime":"2016-02-26 17:18:21","allowDelete":0,"sort":0,"categoryTitle":""}
     */

    private DatasBean datas;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * articleId : 100
         * title : 推广会员申请认证协议
         * categoryId : 20
         * url :
         * content :
         * createTime : 2016-02-26 17:18:21
         * allowDelete : 0
         * sort : 0
         * categoryTitle :
         */

        private JoinAgreementBean joinAgreement;

        public JoinAgreementBean getJoinAgreement() {
            return joinAgreement;
        }

        public void setJoinAgreement(JoinAgreementBean joinAgreement) {
            this.joinAgreement = joinAgreement;
        }

        public static class JoinAgreementBean {
            private int articleId;
            private String title;
            private int categoryId;
            private String url;
            private String content;
            private String createTime;
            private int allowDelete;
            private int sort;
            private String categoryTitle;

            public int getArticleId() {
                return articleId;
            }

            public void setArticleId(int articleId) {
                this.articleId = articleId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(int categoryId) {
                this.categoryId = categoryId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public int getAllowDelete() {
                return allowDelete;
            }

            public void setAllowDelete(int allowDelete) {
                this.allowDelete = allowDelete;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getCategoryTitle() {
                return categoryTitle;
            }

            public void setCategoryTitle(String categoryTitle) {
                this.categoryTitle = categoryTitle;
            }
        }
    }
}
