Feature: Valid Moves

  Scenario: New game, 'X' player make first move
    Given new command entered
    When move 3d command entered
    Then shell output contains:
    """
    Player 'X' move: 3d
    1 --------
    2 --------
    3 ---X----
    4 ---XX---
    5 ---XO---
    6 --------
    7 --------
    8 --------
      abcdefgh
    """

    When move c5 command entered
    Then shell output contains:
    """
    Player 'O' move: c5
    1 --------
    2 --------
    3 ---X----
    4 ---XX---
    5 --OOO---
    6 --------
    7 --------
    8 --------
      abcdefgh
    """

    When move e7 command entered
    Then expect game exception with message:
    """
    Player 'X' move: e7
    Invalid move. Please try again.
    """

    When move e6 command entered
    Then shell output contains:
    """
    Player 'X' move: e6
    1 --------
    2 --------
    3 ---X----
    4 ---XX---
    5 --OOX---
    6 ----X---
    7 --------
    8 --------
      abcdefgh
    """

    When move 5f command entered
    Then shell output contains:
    """
    Player 'O' move: 5f
    1 --------
    2 --------
    3 ---X----
    4 ---XX---
    5 --OOOO--
    6 ----X---
    7 --------
    8 --------
      abcdefgh
    """