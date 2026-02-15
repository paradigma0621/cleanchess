import chess.pgn

def get_uci_from_pgn(pgn_file_path, move_number):
    # Abre o arquivo PGN
    with open(pgn_file_path) as pgn_file:
        # Lê o primeiro jogo do arquivo PGN
        game = chess.pgn.read_game(pgn_file)
    
    # Inicializa o tabuleiro
    board = game.board()
    
    # Itera pelos movimentos do jogo
    for i, move in enumerate(game.mainline_moves(), start=1):
        # Se o número do movimento corresponder ao especificado, retorna o movimento em UCI
        if i == move_number:
            return move.uci()
    
    return None

# Exemplo de uso
pgn_file_path = "example.pgn"  # Substitua pelo caminho do seu arquivo PGN
move_number = 10  # Substitua pelo número do movimento desejado

uci_move = get_uci_from_pgn(pgn_file_path, move_number)
if uci_move:
    print(f"Movimento {move_number} em UCI: {uci_move}")
else:
    print(f"Movimento {move_number} não encontrado.")
