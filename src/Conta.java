import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Conta implements ITaxas{

    private int numero;

    private Cliente dono;

    protected double saldo;

    protected double limite;

    private List<Operacao> operacoes;

    private int proximaOperacao;

    private static int totalContas = 0;

    public Conta(int numero, Cliente dono, double saldo) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;

        this.operacoes = new ArrayList<>();
        this.proximaOperacao = 0;

        totalContas++;
    }

    public void depositar(double valor) {
        this.saldo += valor;

        this.operacoes.add(new OperacaoDeposito(valor));
    }

    public boolean sacar(double valor) {
        if (valor > 0 && valor <= this.saldo) {
            this.saldo = this.saldo - valor;

            //Adiciona uma nova operacao de Saque
            this.operacoes.add(new OperacaoSaque(valor));
            return true;
        }else{
            return false;
        }
    }

    public boolean transferir(Conta destino, double valor) {
        boolean valorSacado = this.sacar(valor);
        if (valorSacado) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        String ContaString = "\n" + "Conta " + this.getNumero() + "\n" + this.getDono().toString() + "\n" + "Saldo: " + this.getSaldo()
                + "\n" + "Limite: " + this.getLimite() + "\n";

        return ContaString;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.getClass() != obj.getClass()) {
            return false;
        } else {
            Conta conta = (Conta) obj;
            if (this.numero == (conta.numero)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void imprimirExtrato(int ordem) {
        if(ordem == 1) {
            System.out.println("\n======= Extrato Conta " + this.numero + "na ordem b: ======================");
            Collections.sort(this.operacoes);
            for (Operacao atual : this.operacoes) {
                atual.imprimir();
            }

        }else {
            System.out.println("\n======= Extrato Conta " + this.numero + "na ordem a: ======================");
            for (Operacao atual : this.operacoes) {
                atual.imprimir();
            }
        }

    }

    public void imprimirExtratoTaxas() {
        double totalTaxas = this.calculaTaxas();
        System.out.println("*=*=*=*=* Extrato de taxas " + dono.getNome()+ " *=*=*=*=*");
        System.out.println("Manutenção da conta: " + this.calculaTaxas());

        System.out.println("Operações");
            for (Operacao operacao : this.operacoes) {
                if (operacao.getTipo() == 's') {
                    //ou: if(operacao instanceof OperacaoSaque){}
                    totalTaxas += operacao.calculaTaxas();
                    System.out.println("Saque: " + operacao.calculaTaxas());

                }
        }
        System.out.printf("Total: %.2f\n", totalTaxas);
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getLimite() {
        return limite;
    }

    public static int getTotalContas() {
        return Conta.totalContas;
    }

    public abstract void setLimite(double limite);
}
