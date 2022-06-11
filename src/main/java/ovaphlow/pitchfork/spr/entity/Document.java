package ovaphlow.pitchfork.spr.entity;

import java.io.Serializable;
import java.util.Objects;
/*
-- pitchfork."document" definition

-- Drop table

-- DROP TABLE pitchfork."document";

CREATE TABLE pitchfork."document" (
	id bigserial NOT NULL,
	title varchar NOT NULL DEFAULT ''::character varying,
	time_begin varchar NOT NULL DEFAULT '2000-01-01 12:34:56+08'::timestamp with time zone,
	time_end varchar NOT NULL DEFAULT '2000-01-01 12:34:56+08'::timestamp with time zone,
	train varchar NOT NULL DEFAULT ''::character varying,
	tag jsonb NOT NULL DEFAULT '[]'::jsonb,
	detail jsonb NOT NULL DEFAULT '{}'::jsonb,
	approve jsonb NOT NULL DEFAULT '{}'::jsonb,
	review jsonb NOT NULL DEFAULT '{}'::jsonb,
	sub01 jsonb NOT NULL DEFAULT '{}'::jsonb,
	sub02 jsonb NOT NULL DEFAULT '[]'::jsonb,
	sub03 jsonb NOT NULL DEFAULT '[]'::jsonb,
	sub04 jsonb NOT NULL DEFAULT '{}'::jsonb,
	CONSTRAINT document_pk PRIMARY KEY (id)
);
CREATE INDEX document_time_begin_idx ON pitchfork.document USING btree (time_begin);
CREATE INDEX document_train_idx ON pitchfork.document USING btree (train);
 */

// POJO 类 简单Java对象
public class Document implements Serializable {
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

    public Document(Long id, String train, String timeBegin, String timeEnd, String title, String tag, String detail, String approve, String review, String sub01, String sub02, String sub03, String sub04) {
        this.id = id;
        this.train = train;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.title = title;
        this.tag = tag;
        this.detail = detail;
        this.approve = approve;
        this.review = review;
        this.sub01 = sub01;
        this.sub02 = sub02;
        this.sub03 = sub03;
        this.sub04 = sub04;
    }
}
