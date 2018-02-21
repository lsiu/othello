Feature: Player cannot make move

  Scenario: Player cannot make move. Turn is passed to next player
    Given this is the state of the game and is DARK's turn
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
    And it is DARK's turn