-- ----------------------------
-- crawler_news
-- ----------------------------

-- ----------------------------
-- Table structure for crawler_news
-- ----------------------------
DROP TABLE IF EXISTS "public"."crawler_news";
CREATE TABLE "public"."crawler_news" (
  "id" int8 NOT NULL,
  "title" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "content" text COLLATE "pg_catalog"."default",
  "url" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "created_at" timestamp(6),
  "modified_at" timestamp(6)
)
;

DROP SEQUENCE IF EXISTS "public"."table_crawler_news_id_seq";
CREATE SEQUENCE "public"."table_crawler_news_id_seq"
INCREMENT 1
START 1
MINVALUE 1
MAXVALUE 99999999
CACHE 1;

ALTER TABLE "public"."crawler_news" OWNER TO "postgres";

-- ----------------------------
-- Primary Key structure for table crawler_news
-- ----------------------------
ALTER TABLE "public"."crawler_news" ADD CONSTRAINT "crawler_news_pkey" PRIMARY KEY ("id");
ALTER TABLE "public"."crawler_news" ALTER COLUMN id SET DEFAULT nextval('table_crawler_news_id_seq');


-- ----------------------------
-- links_to_be_processed
-- ----------------------------

-- ----------------------------
-- Table structure for links_to_be_processed
-- ----------------------------
DROP TABLE IF EXISTS "public"."links_to_be_processed";
CREATE TABLE "public"."links_to_be_processed" (
  "url" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "processed" bool
)
;
ALTER TABLE "public"."links_to_be_processed" OWNER TO "postgres";

-- ----------------------------
-- Primary Key structure for table links_to_be_processed
-- ----------------------------
ALTER TABLE "public"."links_to_be_processed" ADD CONSTRAINT "links_to_be_processed_pkey" PRIMARY KEY ("url");