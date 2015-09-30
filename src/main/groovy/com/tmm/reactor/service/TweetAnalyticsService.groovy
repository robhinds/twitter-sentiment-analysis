package com.tmm.reactor.service

import org.springframework.stereotype.Service;

@Service
class TweetAnalyticsService {

	private final String RWC = "#RWC2015"
	private final Map COUNTRY_MAP = [
		"#ARG": "Argentina",
		"#VamosPumas": "Argentina",
		"#AUS": "Australia",
		"#StrongerAsOne": "Australia",
		"#CAN": "Canada",
		"#RedNation": "Canada",
		"#ENG": "England",
		"#CarryThemHome": "England",
		"#FJI": "Fiji",
		"#FlyingFijians": "Fiji",
		"#FRA": "France",
		"#SoutiensLeXV": "France",
		"#GEO": "Georgia",
		"#RugbyIsOurGame": "Georgia",
		"#IRE": "Ireland",
		"#ShoulderToShoulder": "Ireland",
		"#ITA": "Italy",
		"#ItalRugby": "Italy",
		"#JPN": "Japan",
		"#JapanWay": "Japan",
		"#NAM": "Namibia",
		"#OneNationOneTeam": "Namibia",
		"#NZL": "New Zealand",
		"#TeamAllBlacks": "New Zealand",
		"#ROM": "Romania",
		"#RugbyRomania": "Romania",
		"#SAM": "Samoa",
		"#LeManu": "Samoa",
		"#SCO": "Scotland",
		"#AsOne": "Scotland",
		"#RSA": "South Africa",
		"#HomeGroundAdvantage": "South Africa",
		"#TGA": "Tonga",
		"#IkaleTahi": "Tonga",
		"#URU": "Uruguay",
		"#VamosTeros": "Uruguay",
		"#USA": "United States",
		"#BlueNation2015": "United States",
		"#WAL": "Wales",
		"#IAmWales": "Wales"
	]
		
	public List<String> getCountriesFromTweet( String tweet ){
		isRwcTweet ? COUNTRY_MAP.findAll{ hashtag, country -> tweet.contains( hashtag ) }
			.collect{ hashtag, country -> country }
			.unique() 
			: []
	}
	
	public boolean isRwcTweet( String tweet ){
		tweet.contains( RWC )
	}
	
}
