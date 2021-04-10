====================================
#                                  #
#   Lucky Numbers project !        #
#   Write by Duran Rehan for DEV2  #
#                                  #
====================================

#The variation of the game :
    1. Version en ligne sur Board Game Arena 

#How I implemented the start of the game:
    1. In the Deck class, I added a public method that allows me to get 4 tiles with a value between 1 and 20 as a list
        - this method randomly adds 4 tiles with different values ​​without possible equal values then sort the list using a collections.sort
    
    2. In the Game Model, I added a public method (void) which allowed me to add to the board of each player 4 tiles chosen by harsard 
        thanks to the method previously described

    3. the method created in the Game class has therefore found its use in the controller at the time of game.start ()