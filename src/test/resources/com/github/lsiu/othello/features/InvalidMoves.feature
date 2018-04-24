Feature: Invalid moves

  Scenario: Place piece in an occupied location is invalid
    Given this is the state of the game and is LIGHT's turn
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
    Invalid move. Please try again.
    """


  Scenario: Slightly out of bound
    Given this is the state of the game and is DARK's turn
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
    When move d9 command entered
    Then expect game exception with message:
    """
    Invalid move. Please try again.
    """