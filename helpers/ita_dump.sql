--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE categories (
    id integer NOT NULL,
    name character varying(50)
);


ALTER TABLE categories OWNER TO postgres;

--
-- Name: content; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE content (
    id integer NOT NULL,
    name character varying(50)
);


ALTER TABLE content OWNER TO postgres;

--
-- Name: content_categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE content_categories (
    note_id integer NOT NULL,
    categories_id integer NOT NULL
);


ALTER TABLE content_categories OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE hibernate_sequence OWNER TO postgres;

--
-- Name: hibernate_sequences; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE hibernate_sequences (
    sequence_name character varying(255) NOT NULL,
    next_val bigint
);


ALTER TABLE hibernate_sequences OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users (
    id integer NOT NULL,
    comment character varying(20),
    msisdn bigint NOT NULL
);


ALTER TABLE users OWNER TO postgres;

--
-- Name: users_content; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE users_content (
    user_id integer NOT NULL,
    content_id integer NOT NULL
);


ALTER TABLE users_content OWNER TO postgres;

--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY categories (id, name) FROM stdin;
3	category 1
5	category 3
7	category 2
\.


--
-- Data for Name: content; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY content (id, name) FROM stdin;
2	note 1
4	note 10
6	note 7
8	note 4
10	note 2
11	note 8
12	note 11
13	note 5
15	note 3
16	note 9
17	note 12
18	note 6
\.


--
-- Data for Name: content_categories; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY content_categories (note_id, categories_id) FROM stdin;
2	3
4	5
6	7
8	5
10	7
11	5
12	7
13	7
15	5
16	3
17	3
18	3
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 12, true);


--
-- Data for Name: hibernate_sequences; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY hibernate_sequences (sequence_name, next_val) FROM stdin;
default	19
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users (id, comment, msisdn) FROM stdin;
1	user 1	88888889
9	user 2	88888810
14	user 3	88888811
\.


--
-- Data for Name: users_content; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY users_content (user_id, content_id) FROM stdin;
1	2
1	4
1	6
1	8
9	10
9	11
9	12
9	13
14	15
14	16
14	17
14	18
\.


--
-- Name: categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);


--
-- Name: content_categories_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY content_categories
    ADD CONSTRAINT content_categories_pkey PRIMARY KEY (note_id, categories_id);


--
-- Name: content_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY content
    ADD CONSTRAINT content_pkey PRIMARY KEY (id);


--
-- Name: hibernate_sequences_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY hibernate_sequences
    ADD CONSTRAINT hibernate_sequences_pkey PRIMARY KEY (sequence_name);


--
-- Name: uk_pw16d0gojul3b5hnf9naoaux7; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT uk_pw16d0gojul3b5hnf9naoaux7 UNIQUE (msisdn);


--
-- Name: users_content_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users_content
    ADD CONSTRAINT users_content_pkey PRIMARY KEY (user_id, content_id);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: fk1gk3e4092ymeixsymdn01jgj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY content_categories
    ADD CONSTRAINT fk1gk3e4092ymeixsymdn01jgj FOREIGN KEY (categories_id) REFERENCES categories(id);


--
-- Name: fkiiyhn4e091t51u1qkh5cwbhqb; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users_content
    ADD CONSTRAINT fkiiyhn4e091t51u1qkh5cwbhqb FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: fkqqb5a0crtvgaefg36jyph4wqh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY content_categories
    ADD CONSTRAINT fkqqb5a0crtvgaefg36jyph4wqh FOREIGN KEY (note_id) REFERENCES content(id);


--
-- Name: fkqsmi2ixneklyveo1u2dy37dje; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY users_content
    ADD CONSTRAINT fkqsmi2ixneklyveo1u2dy37dje FOREIGN KEY (content_id) REFERENCES content(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

