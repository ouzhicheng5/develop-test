CREATE TABLE "ndt"."t_user" (
"id" numeric NOT NULL,
"login_name" varchar(40) COLLATE "default",
"salt" varchar(40) COLLATE "default",
"password" varchar(100) COLLATE "default",
"name" varchar(60) COLLATE "default",
"gender" int4,
"mobile" varchar(20) COLLATE "default",
"image_url" varchar(50) COLLATE "default",
"user_type" int4,
"admin_division_id" numeric,
"org_id" numeric,
"user_detail_id" numeric,
"account_state" int4,
"source_id" int4,
"status" int2,
"create_by" numeric,
"create_at" timestamp(6),
"update_by" numeric,
"update_at" timestamp(6),
"last_login_time" timestamp(6),
CONSTRAINT "t_user_pkey" PRIMARY KEY ("id")
)
WITH (OIDS=FALSE)
;

ALTER TABLE "ndt"."t_user" OWNER TO "postgres";

COMMENT ON TABLE "ndt"."t_user" IS '用户表';

COMMENT ON COLUMN "ndt"."t_user"."login_name" IS '用户表';

COMMENT ON COLUMN "ndt"."t_user"."salt" IS 'SALT';

COMMENT ON COLUMN "ndt"."t_user"."password" IS '密码';

COMMENT ON COLUMN "ndt"."t_user"."name" IS '姓名';

COMMENT ON COLUMN "ndt"."t_user"."gender" IS '性别';

COMMENT ON COLUMN "ndt"."t_user"."mobile" IS '电话';

COMMENT ON COLUMN "ndt"."t_user"."image_url" IS '用户头像';

COMMENT ON COLUMN "ndt"."t_user"."user_type" IS '用户类型';

COMMENT ON COLUMN "ndt"."t_user"."admin_division_id" IS '行政区划ID';

COMMENT ON COLUMN "ndt"."t_user"."org_id" IS '机构ID';

COMMENT ON COLUMN "ndt"."t_user"."user_detail_id" IS '详情信息ID';

COMMENT ON COLUMN "ndt"."t_user"."account_state" IS '账号状态';

COMMENT ON COLUMN "ndt"."t_user"."source_id" IS '来源,PC app';

COMMENT ON COLUMN "ndt"."t_user"."status" IS '记录状态';

COMMENT ON COLUMN "ndt"."t_user"."create_by" IS '创建人';

COMMENT ON COLUMN "ndt"."t_user"."create_at" IS '创建时间';

COMMENT ON COLUMN "ndt"."t_user"."update_by" IS '更新人';

COMMENT ON COLUMN "ndt"."t_user"."update_at" IS '更新时间';

COMMENT ON COLUMN "ndt"."t_user"."last_login_time" IS '用户最后登录时间';

