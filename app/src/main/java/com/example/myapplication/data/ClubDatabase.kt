package com.example.myapplication.data

import com.example.myapplication.model.Club

class ClubDatabase() {
    fun getClub(): Club {
        val clubs = listOf(
            Club(
                "Eisbären Berlin",
                "Berlin",
                "1954",
                325,
                135,
                "Die Eisbären Berlin sind eine professionelle Eishockeymannschaft mit Sitz in Berlin, Deutschland. Gegründet im Jahr 1954, hat das Team eine reiche Geschichte im deutschen Eishockey.",
                "Eishockey"
            ),
            Club(
                "Füchse Berlin",
                "Berlin",
                "2002",
                232,
                128,
                "Die Füchse Berlin sind eine Handballmannschaft aus der lebendigen Stadt Berlin. Gegründet im Jahr 2002 hat das Team bemerkenswerten Erfolg in der Welt des Handballs erzielt.",
                "Handball"
            ),
            Club(
                "SG Dynamo Dresden",
                "Dresden",
                "1953",
                327,
                140,
                "Die SG Dynamo Dresden ist eine Fußballmannschaft, die in der 3. Liga spielt. Der Verein, 1953 gegründet, hat eine starke Tradition und leidenschaftliche Fanbasis.",
                "Fußball"
            ),
            Club(
                "Rhein-Neckar Löwen",
                "Mannheim",
                "2002",
                230,
                132,
                "Die Rhein-Neckar Löwen sind eine Handballmannschaft mit Sitz in Mannheim. Gegründet im Jahr 2002, hat das Team eine beeindruckende Geschichte im deutschen Handball.",
                "Handball"
            ),
            Club(
                "THW Kiel",
                "Kiel",
                "1902",
                128,
                36,
                "Der THW Kiel ist eine Handballmannschaft mit Sitz in Kiel, Deutschland. Gegründet im Jahr 1902, ist der Verein für seine Erfolge im deutschen und internationalen Handball bekannt.",
                "Handball"
            ),
            Club(
                "SC Magdeburg",
                "Magdeburg",
                "1955",
                226,
                68,
                "Der SC Magdeburg ist eine Handballmannschaft mit Sitz in Magdeburg, Deutschland. Gegründet im Jahr 1955, hat das Team eine lange Geschichte im deutschen Handball.",
                "Handball"
            ),
            Club(
                "EWE Baskets Oldenburg",
                "Oldenburg",
                "2004",
                129,
                44,
                "Die EWE Baskets Oldenburg sind eine Basketballmannschaft mit Sitz in Oldenburg, Deutschland. Gegründet im Jahr 2004, hat das Team Erfolge im deutschen Basketball verzeichnet.",
                "Basketball"
            ),
            Club(
                "Iserlohn Roosters",
                "Iserlohn",
                "1971",
                122,
                39,
                "Die Iserlohn Roosters sind eine Eishockeymannschaft mit Sitz in Iserlohn, Deutschland. Gegründet im Jahr 1971, hat das Team eine lange Geschichte im deutschen Eishockey.",
                "Eishockey"
            ),
            Club(
                "FC Bayern München",
                "München",
                "1900",
                134,
                130,
                "Der FC Bayern München ist eine Fußballmannschaft mit Sitz in München, Deutschland. Gegründet im Jahr 1900, ist der Verein einer der erfolgreichsten im deutschen Fußball.",
                "Fußball"
            ),
            Club(
                "Brose Bamberg",
                "Bamberg",
                "1955",
                128,
                66,
                "Brose Bamberg ist eine Basketballmannschaft mit Sitz in Bamberg, Deutschland. Gegründet im Jahr 1955, hat das Team mehrere nationale und internationale Erfolge erzielt.",
                "Basketball"
            ),
            Club(
                "Borussia Dortmund",
                "Dortmund",
                "1909",
                232,
                71,
                "Borussia Dortmund ist eine Fußballmannschaft mit Sitz in Dortmund, Deutschland. Gegründet im Jahr 1909, ist der Verein bekannt für seine leidenschaftlichen Fans und Erfolge im deutschen Fußball.",
                "Fußball"
            ),
            Club(
                "Bayer 04 Leverkusen",
                "Leverkusen",
                "1904",
                330,
                45,
                "Bayer 04 Leverkusen ist eine Fußballmannschaft mit Sitz in Leverkusen, Deutschland. Gegründet im Jahr 1904, hat der Verein eine lange Geschichte im deutschen Fußball.",
                "Fußball"
            ),
            Club(
                "Adler Mannheim",
                "Mannheim",
                "1938",
                524,
                57,
                "Die Adler Mannheim sind eine Eishockeymannschaft mit Sitz in Mannheim, Deutschland. Gegründet im Jahr 1938, hat das Team eine erfolgreiche Geschichte im deutschen Eishockey.",
                "Eishockey"
            ),
            Club(
                "HSG Wetzlar",
                "Wetzlar",
                "1992",
                429,
                133,
                "Die HSG Wetzlar ist eine Handballmannschaft mit Sitz in Wetzlar, Deutschland. Gegründet im Jahr 1992, hat das Team Erfolge im deutschen Handball erzielt.",
                "Handball"
            ),
            Club(
                "HC Empor Rostock",
                "Rostock",
                "1954",
                426,
                238,
                "Der HC Empor Rostock ist eine Handballmannschaft mit Sitz in Rostock, Deutschland. Gegründet im Jahr 1954, hat das Team eine lange Geschichte im deutschen Handball.",
                "Handball"
            ),
            Club(
                "1. FC Köln",
                "Köln",
                "1948",
                231,
                110,
                "Der 1. FC Köln ist eine Fußballmannschaft mit Sitz in Köln, Deutschland. Gegründet im Jahr 1948, hat der Verein eine treue Fangemeinde und Erfolge im deutschen Fußball.",
                "Fußball"
            ),
            Club(
                "TSV 1860 München",
                "München",
                "1860",
                125,
                140,
                "Der TSV 1860 München ist eine Fußballmannschaft mit Sitz in München, Deutschland. Gegründet im Jahr 1860, hat der Verein eine lange Geschichte und Tradition im deutschen Fußball.",
                "Fußball"
            ),
            Club(
                "ERC Ingolstadt",
                "Ingolstadt",
                "1964",
                523,
                79,
                "Der ERC Ingolstadt ist eine Eishockeymannschaft mit Sitz in Ingolstadt, Deutschland. Gegründet im Jahr 1964, hat das Team eine erfolgreiche Geschichte im deutschen Eishockey.",
                "Eishockey"
            ),
            Club(
                "DJK Rimpar Wölfe",
                "Rimpar",
                "1921",
                520,
                75,
                "Die DJK Rimpar Wölfe sind eine Handballmannschaft mit Sitz in Rimpar, Deutschland. Gegründet im Jahr 1921, hat das Team eine lange Tradition im deutschen Handball.",
                "Handball"
            )
        )
        return clubs.random()
    }
}
