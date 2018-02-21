Feature: Player cannot make move

  Scenario: Player cannot make move. Turn is passed to next player
    Given this is the state of the game and is BLACK's turn
    """
    1 --------
    2 ---O----
    3 ---X----
    4 --XXX---
    5 -OXXX---
    6 --XXX---
    7 --XXX---
    8 --XXX---
      abcdefgh
    """
    When move a5 command entered
    Then expect game exception with message:
    """
    Player 'X' move: a5
    1 --------
    2 ---O----
    3 ---X----
    4 --XXX---
    5 XXXXX---
    6 --XXX---
    7 --XXX---
    8 --XXX---
      abcdefgh

    player O cannot make a move, back to X turn
    """
    And it is BLACK's turn


  Scenario: Neither player can make move. Game ends
    Given this is the state of the game and is BLACK's turn
    """
    1 --------
    2 ---O----
    3 ---X----
    4 --XXX---
    5 XXXXX---
    6 --XXX---
    7 --XXX---
    8 --XXX---
      abcdefgh
    """
    When move d1 command entered
    And expect game exception with message:
    """
    Player 'X' move: d1
    1 ---X----
    2 ---X----
    3 ---X----
    4 --XXX---
    5 XXXXX---
    6 --XXX---
    7 --XXX---
    8 --XXX---
      abcdefgh

    No further moves available
    Player 'X' wins ( 20 vs 0 )
    """


  Scenario: Game ends. Last move loses
    Given this is the state of the game and is WHITE's turn
    """
    1 XXXXXXXX
    2 XXXXXXOX
    3 XXXXXXXX
    4 XXXXXXXX
    5 OXXXXXXX
    6 OXXXXXXX
    7 -XXOXXXX
    8 OXXOXXXX
      abcdefgh
    """
    When move a7 command entered
    And expect game exception with message:
    """
    Player 'O' move: a7
    1 XXXXXXXX
    2 XXXXXXOX
    3 XXXXXXXX
    4 XXXXXXXX
    5 OXXXXXXX
    6 OXXXXXXX
    7 OOOOXXXX
    8 OXXOXXXX
      abcdefgh

    No further moves available
    Player 'X' wins ( 55 vs 9 )
    """


  Scenario: Game ends. Last move wins
    Given this is the state of the game and is BLACK's turn
    """
    1 XXXXXXXX
    2 XXXXXXOX
    3 XXXXXXXX
    4 XXXXXXXX
    5 OXXXXXXX
    6 OXXXXXXX
    7 -XXOXXXX
    8 OXXOXXXX
      abcdefgh
    """
    When move a7 command entered
    And expect game exception with message:
    """
    Player 'X' move: a7
    1 XXXXXXXX
    2 XXXXXXOX
    3 XXXXXXXX
    4 XXXXXXXX
    5 XXXXXXXX
    6 XXXXXXXX
    7 XXXOXXXX
    8 OXXOXXXX
      abcdefgh

    No further moves available
    Player 'X' wins ( 60 vs 4 )
    """


  Scenario: Game ends. Tie Game
    Given this is the state of the game and is BLACK's turn
    """
    1 XOOOOOOO
    2 XOOOOOOO
    3 XOOOOOOO
    4 XOOOOOOO
    5 OXXXXXXX
    6 OXXXXXXO
    7 -XXOXXXX
    8 OXXOXXXX
      abcdefgh
    """
    When move a7 command entered
    And expect game exception with message:
    """
    Player 'X' move: a7
    1 XOOOOOOO
    2 XOOOOOOO
    3 XOOOOOOO
    4 XOOOOOOO
    5 XXXXXXXX
    6 XXXXXXXO
    7 XXXOXXXX
    8 OXXOXXXX
      abcdefgh

    No further moves available
    Game tied ( 32 vs 32 )
    """