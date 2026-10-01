package br.edu.aesa.app;

import br.edu.aesa.model.Produto;
import br.edu.aesa.service.EstoqueService;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final EstoqueService service = new EstoqueService();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            mostraMenu();
            String opc = scanner.nextLine().trim();
            switch (opc) {
                case "1": cadastrar(); break;
                case "2": listar(); break;
                case "3": buscar(); break;
                case "4": atualizar(); break;
                case "5": remover(); break;
                case "0": System.out.println("Saindo..."); scanner.close(); return;
                default: System.out.println("Opção inválida.");
            }
            System.out.println();
        }
    }

    private static void mostraMenu() {
        System.out.println("===== Controle de Estoque =====");
        System.out.println("[1] Cadastrar novo produto");
        System.out.println("[2] Listar todos os produtos");
        System.out.println("[3] Buscar produto por ID");
        System.out.println("[4] Atualizar produto");
        System.out.println("[5] Remover produto");
        System.out.println("[0] Sair do programa");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrar() {
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();
            System.out.print("Categoria: ");
            String categoria = scanner.nextLine().trim();
            System.out.print("Preço: ");
            String precoStr = scanner.nextLine().trim();
            precoStr = precoStr.replace(',', '.');
            double preco = Double.parseDouble(precoStr);
            System.out.print("Quantidade: ");
            int qtd = Integer.parseInt(scanner.nextLine().trim());

            Produto p = new Produto(0, nome, categoria, preco, qtd);
            service.cadastrar(p);
            System.out.println("Registro cadastrado com sucesso! ID = " + p.getId());
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar: " + e.getMessage());
        }
    }

    private static void listar() {
        List<Produto> lista = service.listarTodos();
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("--- Produtos ---");
        for (Produto p : lista) System.out.println(p);
    }

    private static void buscar() {
        try {
            System.out.print("ID: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Produto p = service.buscarPorId(id);
            if (p == null) System.out.println("Nenhum item encontrado com o ID informado.");
            else System.out.println(p);
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }

    private static void atualizar() {
        try {
            System.out.print("ID do produto a atualizar: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            Produto existente = service.buscarPorId(id);
            if (existente == null) {
                System.out.println("Nenhum item encontrado com o ID informado.");
                return;
            }
            System.out.println("Deixe em branco para manter o valor atual.");
            System.out.print("Nome (atual: " + existente.getNome() + "): ");
            String nome = scanner.nextLine().trim();
            System.out.print("Categoria (atual: " + existente.getCategoria() + "): ");
            String categoria = scanner.nextLine().trim();
            System.out.print("Preço (atual: " + existente.getPreco() + "): ");
            String precoStr = scanner.nextLine().trim();
            System.out.print("Quantidade (atual: " + existente.getQuantidade() + "): ");
            String qtdStr = scanner.nextLine().trim();

            Produto novos = new Produto();
            if (!nome.isEmpty()) novos.setNome(nome);
            if (!categoria.isEmpty()) novos.setCategoria(categoria);
            if (!precoStr.isEmpty()) {
                precoStr = precoStr.replace(',', '.');
                novos.setPreco(Double.parseDouble(precoStr));
            } else novos.setPreco(-1);
            if (!qtdStr.isEmpty()) novos.setQuantidade(Integer.parseInt(qtdStr)); else novos.setQuantidade(-1);

            boolean ok = service.atualizar(id, novos);
            System.out.println(ok ? "Atualização realizada com sucesso." : "Falha ao atualizar (ID não encontrado).");
        } catch (NumberFormatException e) {
            System.out.println("Entrada numérica inválida.");
        }
    }

    private static void remover() {
        try {
            System.out.print("ID do produto a remover: ");
            int id = Integer.parseInt(scanner.nextLine().trim());
            boolean ok = service.remover(id);
            System.out.println(ok ? "Remoção efetuada." : "Nenhum item encontrado com o ID informado.");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        }
    }
}
