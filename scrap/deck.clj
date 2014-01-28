(def suits  ["Clubs" "Hearts" "Spades" "Diamonds"] )
(def values ["Ace" "Deuce" "Three" "Four" "Five" "Six" "Seven" "Eight" "Nine" "Ten" "Jack" "Queen" "King"])

(dorun
	(for [suit suits
	      value values]
       	     (println  value "of" suit)))