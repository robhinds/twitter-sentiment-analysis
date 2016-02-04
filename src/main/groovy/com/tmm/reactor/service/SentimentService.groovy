package com.tmm.reactor.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import edu.stanford.nlp.pipeline.Annotation
import edu.stanford.nlp.pipeline.StanfordCoreNLP
import edu.stanford.nlp.trees.Tree
import edu.stanford.nlp.util.CoreMap
import edu.stanford.nlp.ling.CoreAnnotations
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations

@Service
class SentimentService {

	@Autowired StanfordCoreNLP stanfordCoreNLP

	public int calculateSentimentScore( String tweet ) {
		int mainSentiment = 0
		if ( tweet ) {
			int longest = 0
			Annotation annotation = stanfordCoreNLP.process( tweet )
			annotation.get( CoreAnnotations.SentencesAnnotation ).each{ CoreMap sentence ->
				Tree tree = sentence.get( SentimentCoreAnnotations.SentimentAnnotatedTree )
				int sentiment = RNNCoreAnnotations.getPredictedClass( tree )
				String partText = sentence.toString()
				if ( partText.length() > longest ) {
					mainSentiment = sentiment
					longest = partText.length()
				}
			}
		}
		mainSentiment
	}
}
