package com.tmm.reactor.service

import org.springframework.stereotype.Service;

@Service
class TweetAnalyticsService {

    private final String RWC = "RWC"
    private final Map COUNTRY_MAP = [
        "#ARG": "Argentina",
        "#VamosPumas": "Argentina",
        "Argentina": "Argentina",
        "#AUS": "Australia",
        "#StrongerAsOne": "Australia",
        "Aussies": "Australia",
        "Australia": "Australia",
        "#CAN": "Canada",
        "#RedNation": "Canada",
        "Canada": "Canada",
        "#ENG": "England",
        "#CarryThemHome": "England",
        "England": "England",
        "#FJI": "Fiji",
        "#FlyingFijians": "Fiji",
        "Fiji": "Fiji",
        "#FRA": "France",
        "#SoutiensLeXV": "France",
        "France": "France",
        "#GEO": "Georgia",
        "#RugbyIsOurGame": "Georgia",
        "Georgia": "Georgia",
        "#IRE": "Ireland",
        "#ShoulderToShoulder": "Ireland",
        "Ireland": "Ireland",
        "#ITA": "Italy",
        "#ItalRugby": "Italy",
        "Italy": "Italy",
        "#JPN": "Japan",
        "#JapanWay": "Japan",
        "Japan": "Japan",
        "#NAM": "Namibia",
        "#OneNationOneTeam": "Namibia",
        "Namibia": "Namibia",
        "#NZL": "New Zealand",
        "#TeamAllBlacks": "New Zealand",
        "#New Zealand": "New Zealand",
        "AllBlacks": "New Zealand",
        "#ROM": "Romania",
        "#RugbyRomania": "Romania",
        "Romania": "Romania",
        "#SAM": "Samoa",
        "#LeManu": "Samoa",
        "Samoa": "Samoa",
        "#SCO": "Scotland",
        "#AsOne": "Scotland",
        "Scotland": "Scotland",
        "#RSA": "South Africa",
        "#HomeGroundAdvantage": "South Africa",
        "South Africa": "South Africa",
        "#TGA": "Tonga",
        "#IkaleTahi": "Tonga",
        "Tonga": "Tonga",
        "#URU": "Uruguay",
        "#VamosTeros": "Uruguay",
        "Uruguay": "Uruguay",
        "#USA": "United States",
        "#BlueNation2015": "United States",
        "United States": "United States",
        "USE": "United States",
        "#WAL": "Wales",
        "#IAmWales": "Wales",
        "Wales": "Wales"
    ]
		
    public List<String> getCountriesFromTweet( String tweet ){
        COUNTRY_MAP.findAll{ hashtag, country -> tweet.toLowerCase().contains( hashtag.toLowerCase() ) }
            .collect{ hashtag, country -> country }
            .unique() 
            : []
    }
	
    public boolean isRwcTweet( String tweet ){
        tweet.toLowerCase().contains( RWC.toLowerCase() )
    }
	
    public List getCountries(){
        COUNTRY_MAP.collect{ k,v -> v }.unique()
    }
}
