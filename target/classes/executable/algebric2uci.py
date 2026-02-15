import chess
import chess.pgn

# Cria um tabuleiro de xadrez
board = chess.Board()

# Movimento em notação algébrica
algebraic_move = "Nf3"

# Converte o movimento de notação algébrica para UCI (coordenadas de tabuleiro)
uci_move = board.parse_san(algebraic_move)
print(f"Movimento UCI para {algebraic_move}: {uci_move}")

# Faz o movimento no tabuleiro
#board.push(uci_move)

# Exemplo de conversão de UCI para notação algébrica sem fazer o movimento no tabuleiro
uci_example = chess.Move.from_uci("g1f3")
print(f"Movimento algébrico para {uci_example}: {board.san(uci_example)}")

