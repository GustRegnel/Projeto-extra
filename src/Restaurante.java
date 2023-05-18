import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ItemCardapio {
    private String nome;
    private double preco;

    public ItemCardapio(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }
}

public class Restaurante {
    private List<ItemCardapio> cardapio = new ArrayList<>();
    private List<Integer> pedido = new ArrayList<>();
    private Scanner scanner;

    public Restaurante() {
        this.scanner = new Scanner(System.in);
    }

    public void executar() {
        this.cardapio.add(new ItemCardapio("Frango Disfarçado de Avestruz", 25.0));
        this.cardapio.add(new ItemCardapio("Batatas Fritas Estalantes de Diversão", 15.0));
        this.cardapio.add(new ItemCardapio("Pudim de Brócolis Invisível", 12.0));
        this.cardapio.add(new ItemCardapio("Sundae de Sorvete Zumbi", 8.0));
        this.cardapio.add(new ItemCardapio("Suco Refrescante do Abacaxi Maluco", 5.0));

        boolean continuar = true;
        while (continuar) {
            this.exibirMenu();
            int opcao = this.lerOpcao();
            switch (opcao) {
                case 1:
                    this.fazerPedido();
                    break;
                case 2:
                    continuar = false;
                    System.out.println("Programa encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
            }
        }
    }

    private void exibirMenu() {
        System.out.println("===== MENU =====");
        System.out.println("1. Fazer pedido");
        System.out.println("2. Sair");
        System.out.println("================");
    }

    private int lerOpcao() {
        System.out.print("Digite a opção desejada: ");
        return this.scanner.nextInt();
    }

    private void fazerPedido() {
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = this.scanner.next();
        this.exibirCardapio();

        int opcao;
        do {
            opcao = this.lerOpcao();
            if (opcao == 0 || opcao >= 1 && opcao <= this.cardapio.size()) {
                if (opcao != 0) {
                    this.pedido.add(opcao);
                }
            } else {
                System.out.println("Opção inválida. Digite novamente.");
            }
        } while (opcao != 0);

        this.exibirNotaFiscal(nomeCliente);
    }

    private void exibirCardapio() {
        System.out.println("===== CARDÁPIO =====");
        for (int i = 0; i < this.cardapio.size(); ++i) {
            ItemCardapio item = this.cardapio.get(i);
            System.out.println(i + 1 + ". " + item.getNome() + " - R$" + item.getPreco());
        }
        System.out.println("0. Finalizar pedido");
        System.out.println("====================");
    }

    private void exibirNotaFiscal(String nomeCliente) {
        System.out.println("===== NOTA FISCAL =====");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Itens do pedido:");

        double subtotal = 0.0;
        for (int i = 0; i < this.pedido.size(); ++i) {
            int itemIndex = this.pedido.get(i) - 1;
            ItemCardapio item = this.cardapio.get(itemIndex);
            System.out.println("- " + item.getNome() + " - R$" + item.getPreco());
            subtotal += item.getPreco();
        }

        double taxaServico = calcularTaxaServico(subtotal);
        double valorTotal = subtotal + taxaServico;

        System.out.println("Subtotal: R$" + subtotal);
        System.out.println("Taxa de serviço (10%): R$" + taxaServico);
        System.out.println("Valor total: R$" + valorTotal);

        double valorPago = 0.0;
        do {
            System.out.print("Digite o valor pago: ");
            valorPago = this.scanner.nextDouble();

            if (valorPago < valorTotal){
                System.out.println("Valor Insuficiente. Digite um valor igual ou maior.");
            }
        } while (valorPago < valorTotal);

        double troco = valorPago - valorTotal;
        System.out.println("Troco: R$" + troco);

        System.out.println("=======================");
    }

    private double calcularTaxaServico(double subtotal) {
        return subtotal * 0.1; // 10% de taxa de serviço
    }
}