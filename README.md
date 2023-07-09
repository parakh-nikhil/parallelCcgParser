# Parallel CCG Parser - Capstone Research

This research was focused on developing a parser that parses Natural Language (English) using Combinatory Categorial Grammar (CCG). The parsed English Language would be used to generate executable software tests.

The existing machine learning implementations for parsing natural language are non-modular and difficult to repair. The other implementations use some type of probabilities and work with Context-Free Grammar. We did not have enough statistics to be able to work with probabilities with the current CCG lexicon as it does not include enough software and testing related terms.

We have applied classic ideas from linguistics to parse natural language. While there are existing parsers that do the work, they aren’t very efficient. We used shared concurrency model in the parsing algorithm for more efficient results.
