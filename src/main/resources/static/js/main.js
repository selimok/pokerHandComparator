var pokerHands;
var pokerDeck;
var suits = ["HEARTS", "CLUBS", "DIAMONDS", "SPADES"];
var values = ["ACE","TWO", "THREE", "FOUR", "FIVE", "SIX",
              "SEVEN","EIGHT","NINE","TEN","JACK","QUEEN","KING"];

function initPokerDeck(){
	pokerDeck = [];
	for(i = 0; i < suits.length ; i++){
		for(j = 0; j < values.length ; j++){
			pokerDeck.push(new PokerCard(suits[i], values[j]))
		}
	}
}

function PokerHand(handId) {
	this.handId = handId;
	this.cards = [];
}

function PokerCard(suit,value) {
	this.suit = suit;
	this.value = value;
}

function dealOutCardsRandomly() {
	initPokerDeck();
	pokerHands = [];
	console.log( "Poker deck is initialized with " + pokerDeck.length + " cards.");
	
	pokerHandOne = new PokerHand(1);
	pokerHandTwo = new PokerHand(2);
	
	for(i = 0; i < 5 ; i++){
		pokerHandOne.cards.push(pickACardRandomly());
		pokerHandTwo.cards.push(pickACardRandomly());
	}
	
	pokerHands.push(pokerHandOne);
	pokerHands.push(pokerHandTwo);
	
	console.log( "Poker cards dealed out randomly.");
	console.log( "Ready to compare!" );
	
	updateGUI();
}

function pickACardRandomly() {
	randomCardIndex = Math.floor(Math.random()*pokerDeck.length);
	randomCard = pokerDeck[randomCardIndex];
	pokerDeck.splice(randomCardIndex, 1);
	return randomCard;
}

function updateGUI(){
	
	for(i = 0; i < pokerHands.length ; i++){
		pokerHand = pokerHands[i];
		handId = pokerHand.handId;
		pokerHandDiv = $("#poker-hand-" + handId);
		pokerHandInfoDiv = $("#poker-hand-" + handId + "-info");
		pokerHandDiv.empty();
		pokerHandInfoDiv.empty();
		for(j = 0; j < 5 ; j++){
			pokerCard = pokerHand.cards[j];
			pokerHandDiv.append('<div class="poker-card"><img src="img/' + pokerCard.value + '-' + pokerCard.suit +'.png" title="' + pokerCard.value + '-' + pokerCard.suit +'"></div>');
		}
	}
}

function comparePokerHands(){
	$.ajax({
		  type: "POST",
		  url: "http://localhost:8080/poker/rankPokerHands",
		  dataType: "json",
		  contentType: 'application/json',
		  data: JSON.stringify(pokerHands),
		  success: function ( response ){ handleCompareResults(response); },
		  error: function( jqXhr, textStatus, errorThrown ){ alert( jqXhr.responseText ); }
		});
}

function handleCompareResults(response){
	
	for (i = 0; i < response.length; i++){
		var handId = response[i].handId;
		var rank = response[i].rank.replace(/_/g,' ');
		var overallRank = response[i].overallRank;
		
		pokerHandInfoDiv = $("#poker-hand-" + handId + "-info");
		
		pokerHandInfoDiv.empty();
		pokerHandInfoDiv.append('<span class="poker-hand-rank">'+rank+'</span><br/>');
		
		if(overallRank == 1){
			pokerHandInfoDiv.append('<span class="poker-hand-winner">Winner</span>');
		}
		
	}
	
}

$("#dealButton" ).click(function() {
	dealOutCardsRandomly();
});

$("#compareButton" ).click(function() {
	comparePokerHands();
});



$(document).ready(function() {
	
});