import chess

# FEN de exemplo
fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

# Cria um tabuleiro de xadrez a partir da FEN
board = chess.Board(fen)

# Movimento em notação UCI
uci_move = "g1f3"

# Converte o movimento UCI para um objeto Move
move = chess.Move.from_uci(uci_move)

# Verifica se o movimento é legal
if move in board.legal_moves:
    print(f"Movimento {uci_move} é legal.")
    # Faz o movimento no tabuleiro
    board.push(move)
    # Obtém a FEN da nova posição
    fen_after_move = board.fen()
    print(f"FEN da nova posição: {fen_after_move}")
else:
    print(f"Movimento {uci_move} é ilegal.")

