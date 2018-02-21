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


  Scenario: Board is full. Game ends