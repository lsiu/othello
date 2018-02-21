Feature: Invalid moves

  Scenario: Place piece in an occupied location is invalid

    Given this is the state of the game and is WHITE's turn
    """
    1 --------
    2 ---O----
    3 --XXX---
    4 --XXX---
    5 -OXXX---
    6 --XXX---
    7 --XXXO--
    8 --OOO---
      abcdefgh
    """
    When move d5 command entered
    Then expect game exception with message:
    """
    Player 'O' move: d5
    Invalid move. Please try again.
    """