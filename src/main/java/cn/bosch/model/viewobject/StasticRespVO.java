package cn.bosch.model.viewobject;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StasticRespVO {

    private String stateCode;
    private String message;
    private DataBean data;

    @Getter
    @Setter
    public static class DataBean {
        private String type;
        private String xunit;
        private String yunit;
        private List<ContentBean> content;

        @Getter
        @Setter
        public static class ContentBean {
            private String xvalue;
            private String yvalue;
        }
    }
}
