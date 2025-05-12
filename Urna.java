import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Urna {
    private final Utilities useful = new Utilities();
    private String mode;
    private ArrayList<Integer> votos = new ArrayList<Integer>();
    private static final Candidato[] candidatos = {
            new Candidato(51, "Candidato 1", true),
            new Candidato(52, "Candidato 2", true),
            new Candidato(-1, "Voto Nulo", false),
            new Candidato(0, "Voto Branco", false)
    };

    public Urna(String mode) {
        this.setMode(mode);
        this.showOptions();
    }

    public void showOptions() {
        this.useful.clearConsole();
        this.showBasePrompt();

        switch (this.getMode()) {
            case "em test":
                System.out.println("1. Votar em modo teste");
                System.out.println("2. Ver resultado de teste");
                System.out.println("3. Abrir votação oficial");
                String option = this.useful.CatchInput();

                if (option.equals("1")) {
                    this.newVote();
                } else if (option.equals("2")) {
                    this.resultado();
                } else if (option.equals("3")) {
                    this.setMode("aberto");
                    this.votos.clear();
                    this.useful.clearConsole();
                    System.out.println("A votação oficial vai começar");
                    this.useful.awaitTime(1);
                    this.showOptions();
                } else {
                    System.out.println("Entrada inválida");
                    this.showOptions();
                }
                break;

            case "aberto":
                System.out.println("1. Votar");
                System.out.println("2. fechar votação");
                String option2 = this.useful.CatchInput();

                if (option2.equals("1")) {
                    this.newVote();
                } else if (option2.equals("2")) {
                    this.setMode("fechado");
                    this.useful.clearConsole();
                    System.out.println("A votação oficial foi encerrada");
                    this.useful.awaitTime(1);
                    this.showOptions();
                } else {
                    this.useful.clearConsole();
                    System.out.println("Entrada inválida");
                    this.useful.awaitTime(1);
                    this.showOptions();
                }
                break;

            case "fechado":
                System.out.println("1. Ver resultado");
                System.out.println("2. Desligar sistema");
                String option3 = this.useful.CatchInput();

                if (option3.equals("1")) {
                    this.resultado();
                } else if (option3.equals("2")) {
                    this.setMode("fechado");
                    this.useful.clearConsole();
                    System.out.println("O sistema será desligado, até a proxima");
                    this.useful.awaitTime(1);
                    return;
                } else {
                    this.useful.clearConsole();
                    System.out.println("Entrada inválida");
                    this.useful.awaitTime(1);
                    this.showOptions();
                }
                break;

            default:
                this.useful.clearConsole();
                System.out.println("Modo não reconhecido.");
                this.useful.awaitTime(1);
                this.showOptions();
                break;
        }
    }

    private void newVote() {
        this.useful.clearConsole();
        this.showBasePrompt();

        List<Integer> opts = new ArrayList<>();

        for (Candidato option : candidatos) {
            System.out.println(option.getNumber() + ". " + option.getName());
            opts.add(option.getNumber());
        }

        String choiceStr = this.useful.CatchInput();

        try {
            Integer choice = Integer.parseInt(choiceStr);

            if (!opts.contains(choice)) {
                System.out.println("Número não correspondente.");
                this.useful.awaitTime(1);
                this.useful.clearConsole();
                this.showOptions();
                return;
            }

            this.votos.add(choice);
            System.out.println("Voto registrado com sucesso!");
            this.useful.awaitTime(1);
            this.useful.clearConsole();
            this.showOptions(); // retorna ao menu

        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número.");
            this.useful.awaitTime(1);
            this.useful.clearConsole();
            this.showOptions();
        }
    }

    private void resultado() {
        this.useful.clearConsole();
        Integer[] opts = new Integer[candidatos.length];

        Integer count = 0;
        for (Candidato candidato : candidatos) {
            opts[count] = 0;

            for (Integer voto : votos) {
                if (voto == candidato.getNumber()) {
                    opts[count]++;
                }
            }

            count++;
        }

        count = 0;
        Integer lastCandidatoValue = opts[0];
        Candidato ganhador = candidatos[0];

        System.out.println(
                "----------------------------------------------------------------------------------------------");

        for (Candidato candidato : candidatos) {
            Float percentualDeVotos = (float) ((opts[count] * 100) / candidatos.length);
            if (opts[count] > lastCandidatoValue && candidato.getPessoa()) {
                ganhador = candidato;
            }

            System.out.println("O " + candidato.getName() + "(" + candidato.getNumber() + ") obteve "
                    + percentualDeVotos + "% dos votos");

            lastCandidatoValue = opts[count];
            count++;
        }

        System.out.println(
            "----------------------------------------------------------------------------------------------");

        System.out.println(
                "O ganhador é o " + ganhador.getName() + "(" + ganhador.getNumber() + ")");

        System.out.println(
                "----------------------------------------------------------------------------------------------");

        System.out.println("voltar as opções, clique enter");
        String choiceStr = this.useful.CatchInput();
        this.showOptions();

    }

    private void showBasePrompt() {
        System.out.println("Status de votação: " + this.getMode());
        if (this.getMode().equals("em test")) {
            System.out.println("votos: " + this.getVotos());
        }
        System.out.println("Escolha uma opção: (Digite o número):");
    }

    private void setMode(String value) {
        String[] modes = { "em test", "aberto", "fechado" };
        if (Arrays.asList(modes).contains(value)) {
            this.mode = value;
        } else {
            throw new IllegalArgumentException("Modo inválido: " + value);
        }
    }

    private String getMode() {
        return this.mode;
    }

    private ArrayList<Integer> getVotos() {
        return this.votos;
    }
}