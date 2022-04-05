package ovaphlow.pitchfork.spr.entity;

public class Setting {
    Long id;
    Long refId;
    Long ref1Id;
    String tag;
    String detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public Long getRef1Id() {
        return ref1Id;
    }

    public void setRef1Id(Long ref1Id) {
        this.ref1Id = ref1Id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Setting{" +
                "id=" + id +
                ", refId=" + refId +
                ", ref1Id=" + ref1Id +
                ", tag='" + tag + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
