package transferencia;

public class Conta {
    private int agencia;
    private int numeroConta;
    private double saldo;
    private Cliente cliente;

    public Conta(int agencia, int numeroConta, double saldo, Cliente cliente) {
        this.agencia = agencia;
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldo() {
        return saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void deposita(double valor){
        this.saldo += valor;
    }

    public boolean saca(double valor){
        if (valor > saldo){
            return false;
        }
        this.saldo -= valor;
        return true;
    }

    public boolean transfere(double valor, Conta conta){
        if (saca(valor)){
            conta.deposita(valor);
            return true;
        }
        return false;
    }
}



















