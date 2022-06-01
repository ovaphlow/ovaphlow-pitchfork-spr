package ovaphlow.pitchfork.spr.entity;

import lombok.Data;

/*
-- pitchfork.setting definition

-- Drop table

-- DROP TABLE pitchfork.setting;

CREATE TABLE pitchfork.setting (
	id bigserial NOT NULL,
	ref_id int8 NOT NULL,
	tag jsonb NOT NULL,
	detail jsonb NOT NULL,
	ref1_id int8 NOT NULL,
	CONSTRAINT setting_pk PRIMARY KEY (id)
);
 */

@Data
public class Setting {
    Long id;
    Long refId;
    Long ref1Id;
    String tag;
    String detail;
}
