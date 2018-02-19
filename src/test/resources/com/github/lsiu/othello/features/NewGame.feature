Feature: New game

  Scenario: Create new game
    When new command entered
    Then shell output contains:
    """
    1 --------
    2 --------
    3 --------
    4 ---OX---
    5 ---XO---
    6 --------
    7 --------
    8 --------
      abcdefgh
    """