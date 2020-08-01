import random

CARDS_MIN_VALUE = 0
CARDS_MAX_VALUE = 12
SUITS = ['Diamond', 'Club', 'Heart', 'Spade']
cardsList = []
players = {}


def setupDeck():
    global cardsList
    cardsList = []
    for suit in SUITS:
        i = CARDS_MIN_VALUE
        while i <= CARDS_MAX_VALUE:
            cardsList.append((suit, i))
            i += 1


def setupPlayers():
    global players
    players["P1"] = []
    players["P2"] = []
    players["P3"] = []


def setupGame():
    setupDeck()
    setupPlayers()


def shuffleDeck():
    random.shuffle(cardsList)


def dealSingleCardToPlayers():
    global players
    shuffleDeck()
    for playerName in players.keys():
        players[playerName].append(cardsList.pop())


def dealCardsToPlayers(numCardsToDeal):
    i = 0
    while i < numCardsToDeal:
        dealSingleCardToPlayers()
        i += 1


def printPlayers():
    for playerName in players:
        handStr = ""
        for cards in players[playerName]:
            handStr += str(cards[0]) + " " + str(cards[1]) + " "
        print(playerName)
        print(handStr)


def passCards():
    global players
    cardsToPass = []
    for index in range(0, len(players.keys())):
        playerName = players.keys()[index]
        currentPlayerHand = players[playerName]
        curMinIndex = 0
        for handIndex in range(0, len(currentPlayerHand)):
            currentCard = currentPlayerHand[handIndex]
            currentValue = currentCard[1]
            if currentValue < currentPlayerHand[curMinIndex][1]:
                curMinIndex = handIndex
        cardsToPass.append(currentPlayerHand.pop(curMinIndex))
    cardsToPass.append(cardsToPass.pop(0))
    for index in range(0, len(players.keys())):
        playerName = players.keys()[index]
        players[playerName].append(cardsToPass[index])
    print("---------------------")
    print("Passing cards")
    print("---------------------")


def calculateWinner():
    handValues = []
    for index in range(0, len(players.keys())):
        playerName = players.keys()[index]
        currentPlayerHand = players[playerName]
        curTotal = 0
        for handIndex in range(0, len(currentPlayerHand)):
            currentCard = currentPlayerHand[handIndex]
            currentValue = currentCard[1]
            curTotal += currentValue
        handValues.append(curTotal)
    curMinIndex = 0
    for index in range(0, len(handValues)):
        if handValues[index] < handValues[curMinIndex]:
            curMinIndex = index
    print("Winner: " + players.keys()[curMinIndex])


# Pretty much same except in here you'd have the large switch/many if statements to select which function to run
def runGame():
    setupGame()
    dealCardsToPlayers(3)
    printPlayers()
    passCards()
    printPlayers()
    calculateWinner()


if __name__ == '__main__':
    runGame()
