package ovaphlow.pitchfork.spr.entity;
/*
-- pitchfork.schedule definition

-- Drop table

-- DROP TABLE pitchfork.schedule;

CREATE TABLE pitchfork.schedule (
	id int8 NOT NULL,
	train varchar NOT NULL,
	time_begin timestamptz NOT NULL,
	time_end timestamptz NOT NULL,
	dept varchar NOT NULL,
	detail jsonb NOT NULL,
	CONSTRAINT newtable_pk PRIMARY KEY (id)
);
CREATE INDEX newtable_train_idx ON pitchfork.schedule USING btree (train); */

public class Schedule {
    long id;
    String train;
    String timeBegin;
    String timeEnd;
    String dept;
    String detail;
    String tag;

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", train='" + train + '\'' +
                ", timeBegin='" + timeBegin + '\'' +
                ", timeEnd='" + timeEnd + '\'' +
                ", dept='" + dept + '\'' +
                ", detail='" + detail + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Schedule(long id, String train, String timeBegin, String timeEnd, String dept, String detail, String tag) {
        this.id = id;
        this.train = train;
        this.timeBegin = timeBegin;
        this.timeEnd = timeEnd;
        this.dept = dept;
        this.detail = detail;
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Schedule() {

    }


}
