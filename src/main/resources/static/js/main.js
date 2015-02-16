var pokerHands = [];
var suits = ["HEARTS", "CLUBS", "DIAMONDS", "SPADES"];
var values = ["ACE","TWO", "THREE", "FOUR", "FIVE", "SIX",
              "SEVEN","EIGHT","NINE","TEN","JACK","QUEEN","KING"];

function PokerHand(handId) {
	this.handId = handId;
	this.cards = [{
						"value": "ACE",
						"suit": "HEARTS"
					},
					{
						"value": "ACE",
						"suit": "HEARTS"
					},
					{
						"value": "ACE",
						"suit": "HEARTS"
					},
					{
						"value": "ACE",
						"suit": "HEARTS"
					},
					{
						"value": "ACE",
						"suit": "HEARTS"
					}];
	
}

function createPokerCard(suit,value) {
	this.suit = suit;
	this.value = value;
}

function addPokerHandPanel() {
	var numberOfPokerHands = pokerHands.length;
	if(numberOfPokerHands < 10){
		divId = 'poker-hand-' + numberOfPokerHands;
		$("#poker-hand-panel" ).append('<div id="' + divId + '" class="poker-hand"></div>');
		fillPokerHandPanel(divId, numberOfPokerHands);		
	} else {
		alert("Maximum number of poker hands reached. Refresh page if you want add new poker hand.");

	}
	
}

function fillPokerHandPanel(divId, handId) {
		$("#"+divId )
		.append('<div class="handId">Poker Hand: '+ handId + '</div>')
		.append('<div class="rank" id="rank-' + handId + '"></div>');
		
		pokerHands.push(new PokerHand(handId));
		
		for(i = 0; i < 5; i++){
			
			cardDiv = $("<div/>")
			.attr("class","card")
			.append(createSuitSelectBox(handId, i))
			.append(createValueSelectBox(handId, i));
			
			$("#"+divId ).append(cardDiv);
		}
}

function createSuitSelectBox(handId, cardIndex) {
	var selectBoxId = "suitSelect-"+handId+"-"+cardIndex;
	var selectBox = $("<select/>")
	.attr("class", "suit-select")
	.attr("id", selectBoxId)
    .attr("handId", handId)
    .attr("cardIndex", cardIndex)
	.on('change', function() { updateCardSuit(selectBoxId) });
	
	$(suits).each(function() {
		selectBox.append($("<option>").attr('value',this).text(this));
	});
	return selectBox;
}

function createValueSelectBox(handId, cardIndex) {
	var selectBoxId = "valueSelect-"+handId+"-"+cardIndex;
	var selectBox = $("<select/>")
	.attr("class", "value-select")
	.attr("id", selectBoxId)
    .attr("handId", handId)
    .attr("cardIndex", cardIndex)
    .on('change', function() { updateCardValue(selectBoxId) });
	
	$(values).each(function() {
		selectBox.append($("<option>").attr('value',this).text(this));
	});
	return selectBox;
}

function updateCardValue(selectBoxId){
	var selectBox = $("#"+selectBoxId);
	var pokerHand = getPokerHandById(selectBox.attr("handId"));
	pokerHand.cards[selectBox.attr("cardIndex")].value = selectBox.val();
}

function updateCardSuit(selectBoxId){
	var selectBox = $("#"+selectBoxId);
	var pokerHand = getPokerHandById(selectBox.attr("handId"));
	pokerHand.cards[selectBox.attr("cardIndex")].suit = selectBox.val();
}

function getPokerHandById(handId){
	var pokerHand = null;
	for(i = 0; i < pokerHands.length; i++){
		if (pokerHands[i].handId == handId) {
			return pokerHands[i];
		} 
	}
	
	return pokerHand;
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
		var rank = response[i].rank;
		var overallRank = response[i].overallRank;
		$('#rank-'+handId).empty().append("Overall Rank: " + overallRank + " / Rank: " + rank);
		if(overallRank == 1){
			$('#rank-'+response[i].handId).append(" / WINNER! ");
		}
	}
	
}

$("#addPokerHandButton" ).click(function() {
	addPokerHandPanel();
});

$("#compareButton" ).click(function() {
	comparePokerHands();
});



$(document).ready(function() {
	initPokerDeck();
	console.log( "Poker deck is initialized with " + pokerDeck.length + " cards.");
    console.log( "Ready to compare!" );
});