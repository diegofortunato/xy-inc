DROP TABLE IF EXISTS poitable CASCADE;
CREATE TABLE poitable (
  point_id         SERIAL,
  point_name       TEXT,
  point_coordinate_x      BIGINT,
  point_coordinate_Y      BIGINT,
  PRIMARY KEY (point_id)
);

INSERT INTO public.poitable (
  point_id, point_name, point_coordinate_x, point_coordinate_Y)
VALUES (1, 'lanchonete', 27, 12),
  (2, 'posto', 31, 18),
  (3, 'joalheria',15, 12),
  (4, 'floricultura', 19, 21),
  (5, 'pub', 12, 8),
  (6, 'supermecado', 23, 6),
  (7, 'churrascaria', 28, 2);

CREATE SEQUENCE hibernate_sequence START 1;
SELECT setval('hibernate_sequence', (SELECT max(point_id) FROM public.poitable));