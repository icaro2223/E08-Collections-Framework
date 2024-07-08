import java.util.Date;
public class Main {
    public static void main(String[] args) {

        Cliente joao = new PessoaFisica("João", "Av. Antonio Carlos, 6627",
                new Date(), "111.111.111-11", 36, 'm');

        Cliente lojinha = new PessoaJuridica("Loja R$1,99", "Av. Afonso Pena, 3000",
                new Date(), "000.00000.0000/0001", 25, "Comércio");

        /*
        Cliente icaro = new PessoaFisica("Icaro", "Rua Carlos torrezani, 367",
                new Date(), "147.477.926-35", 16, 'm');*/


        Conta conta1 = new ContaCorrente(1234, joao, 20000);
        Conta conta2 = new ContaCorrente(1235, lojinha, 10000);

        conta1.depositar(300);
        conta1.sacar(15);
        conta1.depositar(1500);
        conta1.sacar(23);
        conta1.sacar(45);
        conta1.sacar(150);
        conta1.imprimirExtrato(1);
        conta1.imprimirExtratoTaxas();

        conta2.depositar(200);
        conta2.sacar(125);
        conta2.depositar(1500);
        conta2.sacar(200);
        conta2.sacar(190);
        conta1.transferir(conta2, 9800);
        conta2.imprimirExtrato(0);
        conta2.imprimirExtratoTaxas();

        System.out.println();
        System.out.println("Saldo de joão: " + conta1.getSaldo());
        System.out.println("Saldo de R$Loja 1,99: " + conta2.getSaldo());

        int media = Operacao.getTotalOperacoes() / Conta.getTotalContas();
        System.out.println("Média de operações por conta aberta: " + media + "\n");
    }

        //teste para ver se sao as mesmas contas
        /*
        System.out.println("Sobre as contas:");

        if (conta1.equals(conta2)) {
            System.out.println("É a mesma conta, pois é o mesmo número identificador!\n");
        } else {
            System.out.println("É uma conta diferente, o número identificador é diferente!\n");
        }

        System.out.println("Sobre as pessoas físicas:");
        if (joao.equals(icaro)) {
            System.out.println("É o mesmo cliente, pois o CPF é igual para ambos clientes!\n");
        } else {
            System.out.println("São clientes diferentes, os CPFs são diferentes!\n");
        }

        System.out.println("Sobre as pessoas jurídicas:");
        if (lojinha.equals(mercado)) {
            System.out.println("É o mesmo cliente, pois o CNPJ é igual para ambos clientes!\n");
        } else {
            System.out.println("São clientes diferentes, os CNPJs são diferentes!\n");
        }
    }*/
    }