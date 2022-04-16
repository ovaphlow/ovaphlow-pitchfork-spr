package ovaphlow.pitchfork.spr.entity;

public class Document {
    Long id;
    String train;
    String timeBegin;
    String timeEnd;
    String title;
    String tag;
    String detail;
    String approve;
    String review;
    String sub01;
    String sub02;
    String sub03;
    String sub04;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrain() {
        return train;
    }

    public void setTrain(String train) {
        this.train = train;
    }

    public String getTimeBegin() {
        return timeBegin;
    }

    public void setTimeBegin(String timeBegin) {
        this.timeBegin = timeBegin;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getSub01() {
        return sub01;
    }

    public void setSub01(String sub01) {
        this.sub01 = sub01;
    }

    public String getSub02() {
        return sub02;
    }

    public void setSub02(String sub02) {
        this.sub02 = sub02;
    }

    public String getSub03() {
        return sub03;
    }

    public void setSub03(String sub03) {
        this.sub03 = sub03;
    }

    public String getSub04() {
        return sub04;
    }

    public void setSub04(String sub04) {
        this.sub04 = sub04;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", train='" + train + '\'' +
                ", timeBegin='" + timeBegin + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", detail='" + detail + '\'' +
                ", approve='" + approve + '\'' +
                ", review='" + review + '\'' +
                ", sub01='" + sub01 + '\'' +
                ", sub02='" + sub02 + '\'' +
                ", sub03='" + sub03 + '\'' +
                ", sub04='" + sub04 + '\'' +
                '}';
    }
}
