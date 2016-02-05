# Twitter Sentiment Analysis  

After plaing around with the event-based stuff in spring streaming events from twitter, I decided to see how hard it would be to hook in some sentiment analysis.  Turns out not hard at all, with the caveat that the analysis is obviously wayyyyy inaccurate. I plugged in the Standford Core NLP library to determine the sentiment of tweets, but the model I loaded was their example that was trained using movie reviews, and tweets have very different forms to normal written text (more disjointed, more web language, abrevieations etc).

Anyway, I hooked it all up with a twitter stream filtering for a handful of tech stocks and started processing them to measure sentiment - I got some meaningless numbers out, but fun none the less.  If someone was inclined to train a model using tweets then it could be more useful.


Mostly I just wanted to make a web app that could have lame references to Eddie Murphy's character in Trading Places.


