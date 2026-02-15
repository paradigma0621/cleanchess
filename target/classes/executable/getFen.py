import chess

# Cria um tabuleiro de xadrez
board = chess.Board()

# Movimento em notação UCI
uci_move = "g1f4"

# Converte o movimento UCI para um objeto Move
move = chess.Move.from_uci(uci_move)

# Verifica se o movimento é legal
if move in board.legal_moves:
    # Faz o movimento no tabuleiro
    board.push(move)
    
    # Obtém a FEN da posição atual
    fen = board.fen()

    # Converte o movimento UCI para notação algébrica (SAN)
    algebraic_move = board.san(move)
    
    print(f"Movimento UCI: {uci_move} -> Notação Algébrica: {algebraic_move}")
    print(f"FEN da nova posição: {fen}")
else:
    print("Movimento ilegal.")


