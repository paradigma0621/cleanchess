package paradigma0621.cleanchess.controller;

import java.util.*;

public class RavTree {

    // Um "nó" é um SAN (ex: "Kxh5") com:
    // - next: continuação da LINHA PRINCIPAL a partir desse nó
    // - variations: outras linhas alternativas (cada uma é uma lista de nós)
    public static class Node {
        public final String san;
        public Node next;                       // mainline continuation
        public final List<List<Node>> variations = new ArrayList<>(); // sibling lines

        public Node(String san) { this.san = san; }

        @Override public String toString() { return san; }
    }

    public static class Parsed {
        public final Node head;  // primeiro lance real
        public Parsed(Node head) { this.head = head; }
    }

    /**
     * Parser simples de RAV:
     * - entende "(" e ")"
     * - ignora números de lance "1." / "1..." e resultado "1-0" etc.
     * - ignora comentários {...} (se você quiser, dá pra expandir)
     *
     * Observação: é um parser "bom o suficiente" pra PGNs de linhas/análises.
     */
    public static Parsed parse(String text) {
        List<String> tokens = tokenize(text);

        Deque<Frame> stack = new ArrayDeque<>();
        // frame = onde eu vou anexar nós (em mainline ou em uma variação)
        Frame cur = new Frame(/*parent*/null);
        stack.push(cur);

        Node head = null;
        Node last = null;
        Node lastMainlinePreMoveAnchor = null; // âncora pra iniciar variante: posição antes do último lance mainline

        for (int i = 0; i < tokens.size(); i++) {
            String t = tokens.get(i);

            if (t.equals("(")) {
                // A variação é alternativa ao PRÓXIMO lance daquele ponto.
                // Em PGN, ( ... ) após um lance normalmente representa alternativas
                // ao lance seguinte do lado a jogar naquele ponto.
                // Aqui usamos a âncora "lastMainlinePreMoveAnchor": o nó após o qual o ramo diverge.
                Frame parentFrame = stack.peek();
                Frame varFrame = parentFrame.startVariation(lastMainlinePreMoveAnchor);
                stack.push(varFrame);
                last = null; // dentro da variação, recomeça encadeamento
                continue;
            }

            if (t.equals(")")) {
                stack.pop();
                last = null;
                continue;
            }

            // token SAN (ex: Bh5+, Kxh5, Kh6, Qg5#...)
            Node node = new Node(t);

            Frame top = stack.peek();
            if (top.parent == null && head == null) head = node;

            // anexar no encadeamento corrente (mainline ou variação)
            if (last == null) {
                top.attachFirst(node);
            } else {
                last.next = node;
            }

            // Se estamos no frame principal (stack size == 1), atualiza âncora para variações
            // âncora = "nó anterior", onde a linha pode ramificar para o próximo lance.
            if (stack.size() == 1) {
                lastMainlinePreMoveAnchor = last; // antes de avançar
            }

            last = node;
        }

        return new Parsed(head);
    }

    private static class Frame {
        final Node parent;                // nó onde a variação se ancora (pode ser null pro início)
        final List<Node> roots;           // onde colocar o "primeiro" nó desta linha (mainline/var)
        Node mainlineTail;                // último nó anexado nessa linha

        Frame(Node parent) {
            this.parent = parent;
            this.roots = new ArrayList<>();
        }

        void attachFirst(Node node) {
            roots.add(node);
            mainlineTail = node;
            // se é mainline e tem parent, conecta parent.next se vazio
            if (parent != null && parent.next == null) parent.next = node;
        }

        Frame startVariation(Node anchorNode) {
            // A variação é “irmã” da continuação do anchorNode
            // Se anchorNode == null, significa variação no começo (raro, mas ok)
            Frame f = new Frame(anchorNode);
            if (anchorNode != null) {
                // registra lista de nós da variação no anchor
                anchorNode.variations.add(f.roots);
            }
            return f;
        }
    }

    private static List<String> tokenize(String s) {
        // remove comentários {...} de forma simples
        s = s.replaceAll("\\{[^}]*\\}", " ");

        // separa parênteses
        s = s.replace("(", " ( ").replace(")", " ) ");

        String[] raw = s.trim().split("\\s+");
        List<String> out = new ArrayList<>();
        for (String tok : raw) {
            // ignora numeração "1." ou "1..."
            if (tok.matches("\\d+\\.+")) continue;

            // ignora resultados
            if (tok.equals("1-0") || tok.equals("0-1") || tok.equals("1/2-1/2") || tok.equals("*")) continue;

            out.add(tok);
        }
        return out;
    }
}
