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
}
