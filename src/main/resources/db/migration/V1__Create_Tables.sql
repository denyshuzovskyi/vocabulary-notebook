CREATE SEQUENCE notebooks_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE notebooks (
    id BIGINT DEFAULT nextval('notebooks_id_seq') NOT NULL,
    name VARCHAR(255),
    description VARCHAR(255)
);

ALTER TABLE notebooks
    ADD CONSTRAINT pk_notebooks PRIMARY KEY (id);
-----------------------------------------------------


CREATE SEQUENCE words_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE words (
    id BIGINT DEFAULT nextval('words_id_seq') NOT NULL,
    word VARCHAR(255),
    pos VARCHAR(20),
    pronunciation VARCHAR(255),
    definition VARCHAR(255),
    notebook_id BIGINT
);

ALTER TABLE words
    ADD CONSTRAINT pk_words PRIMARY KEY (id);

ALTER TABLE words
    ADD CONSTRAINT fk_words_notebook_id FOREIGN KEY (notebook_id) REFERENCES notebooks (id) ON DELETE CASCADE;
-----------------------------------------------------


CREATE SEQUENCE examples_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE examples (
    id BIGINT DEFAULT nextval('examples_id_seq') NOT NULL,
    example_text VARCHAR(255),
    word_id BIGINT
);

ALTER TABLE examples
    ADD CONSTRAINT pk_examples PRIMARY KEY (id);

ALTER TABLE examples
    ADD CONSTRAINT fk_examples_word_id FOREIGN KEY (word_id) REFERENCES words (id) ON DELETE CASCADE;
-----------------------------------------------------


CREATE SEQUENCE test_collections_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE test_collections (
    id BIGINT DEFAULT nextval('test_collections_id_seq') NOT NULL,
    notebook_id BIGINT,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE
);

ALTER TABLE test_collections
    ADD CONSTRAINT pk_test_collections PRIMARY KEY (id);

ALTER TABLE test_collections
    ADD CONSTRAINT fk_test_collections_notebook_id FOREIGN KEY (notebook_id) REFERENCES notebooks (id) ON DELETE CASCADE;
-----------------------------------------------------


CREATE SEQUENCE test_collection_generation_jobs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE test_collection_generation_jobs (
    id BIGINT DEFAULT nextval('test_collection_generation_jobs_id_seq') NOT NULL,
    status VARCHAR(255),
    test_collection_id BIGINT
);


ALTER TABLE test_collection_generation_jobs
    ADD CONSTRAINT pk_test_collection_generation_jobs PRIMARY KEY (id);

ALTER TABLE test_collection_generation_jobs
    ADD CONSTRAINT fk_test_collection_generation_jobs_test_collection_id FOREIGN KEY (test_collection_id) REFERENCES test_collections (id) ON DELETE CASCADE;
-----------------------------------------------------


CREATE SEQUENCE tests_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE tests (
    id BIGINT DEFAULT nextval('tests_id_seq') NOT NULL,
    task VARCHAR(255),
    word_id BIGINT,
    test_collection_id BIGINT,
    created_at TIMESTAMP(6) WITHOUT TIME ZONE
);

ALTER TABLE tests
    ADD CONSTRAINT pk_tests PRIMARY KEY (id);

ALTER TABLE tests
    ADD CONSTRAINT fk_tests_word_id FOREIGN KEY (word_id) REFERENCES words (id) ON DELETE CASCADE;
ALTER TABLE tests
    ADD CONSTRAINT fk_tests_test_collection_id FOREIGN KEY (test_collection_id) REFERENCES test_collections (id) ON DELETE CASCADE;
-----------------------------------------------------


CREATE SEQUENCE test_options_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE test_options (
    id BIGINT DEFAULT nextval('test_options_id_seq') NOT NULL,
    option VARCHAR(255),
    is_correct BOOLEAN,
    test_id BIGINT
);

ALTER TABLE test_options
    ADD CONSTRAINT pk_test_options PRIMARY KEY (id);

ALTER TABLE test_options
    ADD CONSTRAINT fk_test_options_test_id FOREIGN KEY (test_id) REFERENCES tests (id) ON DELETE CASCADE;
-----------------------------------------------------


CREATE SEQUENCE test_sessions_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE test_sessions (
    id BIGINT DEFAULT nextval('test_sessions_id_seq') NOT NULL,
    test_collection_id BIGINT,
    started_at TIMESTAMP(6) WITHOUT TIME ZONE,
    finished_at TIMESTAMP(6) WITHOUT TIME ZONE
);

ALTER TABLE test_sessions
    ADD CONSTRAINT pk_test_sessions PRIMARY KEY (id);

ALTER TABLE test_sessions
    ADD CONSTRAINT fk_test_sessions_test_collection_id FOREIGN KEY (test_collection_id) REFERENCES test_collections (id) ON DELETE CASCADE;
-----------------------------------------------------


CREATE SEQUENCE test_results_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE test_results (
    id BIGINT DEFAULT nextval('test_results_id_seq') NOT NULL,
    test_session_id BIGINT,
    test_id BIGINT
);

ALTER TABLE test_results
    ADD CONSTRAINT pk_test_results PRIMARY KEY (id);

ALTER TABLE test_results
    ADD CONSTRAINT fk_test_results_test_session_id FOREIGN KEY (test_session_id) REFERENCES test_sessions (id) ON DELETE CASCADE;
ALTER TABLE test_results
    ADD CONSTRAINT fk_test_results_test_id FOREIGN KEY (test_id) REFERENCES tests (id) ON DELETE CASCADE;
-----------------------------------------------------


CREATE SEQUENCE selected_test_options_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE selected_test_options (
    id BIGINT DEFAULT nextval('selected_test_options_id_seq') NOT NULL,
    test_option_id BIGINT,
    test_result_id BIGINT
);

ALTER TABLE selected_test_options
    ADD CONSTRAINT pk_selected_test_options PRIMARY KEY (id);

ALTER TABLE selected_test_options
    ADD CONSTRAINT fk_selected_test_options_test_option_id FOREIGN KEY (test_option_id) REFERENCES test_options (id) ON DELETE CASCADE;
ALTER TABLE selected_test_options
    ADD CONSTRAINT fk_selected_test_options_test_result_id FOREIGN KEY (test_result_id) REFERENCES test_results (id) ON DELETE CASCADE;
-----------------------------------------------------


