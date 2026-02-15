import chess

# Cria um tabuleiro de xadrez
board = chess.Board()

# Movimento em notação UCI
uci_move = "g1f3"

# Converte o movimento UCI para um objeto Move
move = chess.Move.from_uci(uci_move)

# Converte o movimento UCI para notação algébrica (SAN)
algebraic_move = board.san(move)

print(f"Movimento UCI: {uci_move} -> Notação Algébrica: {algebraic_move}")

