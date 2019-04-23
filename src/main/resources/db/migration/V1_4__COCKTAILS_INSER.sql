INSERT INTO cocktail(ID, TITLE, ANNOUNCE, CREATED_DATE, COOKING_TIME, PREP_TIME, USER_ID ,PHOTO_ID)
VALUES (1, 'Sex on the Beach Cocktail Recipe',
        'So I have to say one of the best drink''s I''ve drunk so far has become my favorite drink as well',
        CURRENT_TIMESTAMP(), 112, 15, 1 ,1);
INSERT INTO cocktail_ingredients(COCKTAIL_ID, INGREDIENT_ID)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5);
INSERT INTO cocktail(ID, TITLE, ANNOUNCE, CREATED_DATE, COOKING_TIME, PREP_TIME, USER_ID,PHOTO_ID)
VALUES (2, 'Eight Cocktail',
        'Shake the ingredients vigorously with a few ice cubes for about 10 seconds and then strain into a martini glass or a shooter glass. Put an After Eight in the glass.',CURRENT_TIMESTAMP() ,
        12, 5, 2,2);
INSERT INTO cocktail_ingredients(Cocktail_id, INGREDIENT_ID)
VALUES (2, 6),
       (2, 7),
       (2, 8),
       (2, 5);
INSERT INTO cocktail(ID, TITLE, ANNOUNCE, CREATED_DATE, COOKING_TIME, PREP_TIME, USER_ID,PHOTO_ID)
VALUES (3, 'Alfie cocktail',
        'Add the vodka, triple sec and pineapple juice together with a few ice cubes to the shaker and shake vigorously. Strain the mixture through the strainer into a cocktail glass.',
        CURRENT_TIMESTAMP(), 16, 15, 3,3);
INSERT INTO cocktail_ingredients(COCKTAIL_ID, INGREDIENT_ID)
VALUES (3, 9),
       (3, 10),
       (3, 11),
       (3, 5);