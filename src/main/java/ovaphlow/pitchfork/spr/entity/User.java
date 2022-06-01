package ovaphlow.pitchfork.spr.entity;

/*
CREATE TABLE pitchfork."user" (
	id bigserial NOT NULL,
	dept_id int8 NOT NULL,
	"name" varchar NOT NULL,
	phone varchar NOT NULL,
	"password" varchar NOT NULL,
	salt varchar NOT NULL,
	tag jsonb NOT NULL,
	dept varchar NOT NULL,
	CONSTRAINT user_pk PRIMARY KEY (id)
);
 */

import lombok.Data;

@Data
public class User {
    long id;
    String name;
    String password;
    String salt;
    String phone;
    String dept;
    String tag;
}
