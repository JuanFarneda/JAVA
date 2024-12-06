import java.util.ArrayList;
public class Cuenta {
    //Declaración de variables de clase.
    public static int ulimtoCbu=1;//Variable de clase inicializada en 1

    //Declaración de variables de instancia.
    private int cbu,tipo,titular,posicion;
    private double saldo;

    //Constructor de la clase Cuenta.
    public Cuenta(int tipo, int titular){
        this.tipo=tipo;
        this.titular=titular;
        saldo=0;
        cbu=ulimtoCbu;
        ulimtoCbu++;//Incrementa el número de ultimoCbu cada vez que se crea una instancia de tipo Cuenta con el fin de generar un CBU único para cada instancia de Cuenta.
    }

    @Override 
    //Override de método toString de la clase object para que, al intentar imprimir una instancia de Cuenta, imprima los valores que se requieren en vez de la posición en memoria de la intancia.
    public String toString() {
        return "DNI del titular:" + titular + " CBU:" + cbu + " Tipo:" + tipo + " Saldo:" + saldo;
    }

    //Método de clase que permite mostrar toda la lista listCuentas.
    public static void mostrarListaCuentas(ArrayList<Cuenta>listaCuentas){
        for (Cuenta cuenta : listaCuentas) {//for each que crea una variable temporal cuenta de tipo Cuenta y recorre la lista listaCuentas.
                System.out.println(cuenta); //En cada iteración, cuenta representa uno de los objetos o elementos de listaCuentas y se imprime por pantalla mediante un println()
                System.out.println();
        }
    }

    //Método de instancia que se encarga de los depósitos y las extracciones. El boolean transferencia se encarga de determinar que mensaje se mostrará por pantalla al realizar una operación.
    public boolean depositoExtraccion(double monto,boolean transferencia,ArrayList<Cuenta>listaCuentas){
        double saldoAnterior=saldo;
        boolean exito=true;//Se utiliza para las transferencias. Comprueba que la cuenta que transfiere quede con saldo positivo al extraer el dinero. Si eso ocurre, el método transferencia procederá a depositar ese dinero en la cuenta receptora.

        if (monto>=0 && transferencia==false) {//Comprueba si el monto es positivo, además de comprobar si es una transferencia.
            saldo+=monto;//Ya que el monto ingresado es mayor a 0, se trata de un depósito, por lo que se suma el monto ingresado al saldo de la cuenta.
            System.out.printf("Depósito realizado correctamente. %nSaldo anterior:%.2f %nSaldo actual:%.2f %n %n",saldoAnterior,saldo);//Print que informa al usuario el éxito de la operación.
            
            /*Al realizar un depósito o una extracción, los saldos de las instancias de Cuenta se modifican 'automáticamente' (simplemente se suma o se resta el monto a la variable saldo). 
            *Esto no ocurre con los elementos dentro de la lista listaCuenta. Para actualizar el saldo de cada cuenta en esta lista, se utilizan ciclos for each cómo el de abajo.
            *Este for recorre la lista listaCuentas, hasta que encuentra el CBU de la instancia que está realizando la operación en la lista. Luego, se encarga de modificar los valores que se encuentran en
            *la lista, cambiándolos por los valores actuales de la instancia (la única modificación es el saldo de la cuenta, por lo que lo demás queda igual).
            *Una vez que se actualiza la lista, se hace un break para detener la iteración del for, ya que cumplió su función. Finalmente, se vuelve a asignar el valor 0 a la posicion.
            */
            for (Cuenta cuenta : listaCuentas) {   
                if (this.getCbu()==cuenta.getCbu()) {
                    listaCuentas.set(posicion, this);
                    break;
                }
               posicion++;
            }
            posicion=0;
            
        } else if (monto<0 && transferencia==false) {//Mimsa lógica que antes, pero en este caso, se realiza una extracción ya que el monto es negativo.
            saldo+=monto;
            if (saldo>=0) {//Valida que el saldo luego de la extracción sea mayor o igual a 0.
                System.out.printf("Extracción realizada correctamente. %nSaldo anterior:%.2f %nSaldo actual:%.2f %n %n",saldoAnterior,saldo);
                for (Cuenta cuenta : listaCuentas) {
              
                    if (this.getCbu()==cuenta.getCbu()) {
                        listaCuentas.set(posicion, this);
                        break;
                    }
                   posicion++;
                }
                posicion=0;

            } else {
                saldo-=monto;//Como el saldo luego de la extracción es negativo, se 'cancela' la operación (ya que el saldo es negativo, al restarse con el monto, que también es negativo, se termina sumando. Por lo que se ddevuelve el monto al saldo.)
                System.out.printf("No se pudo realizar la extracción.%nMotivo: saldo insuficiente%n %n");  
                exito=false; 
            } 
        } else if (monto>=0 && transferencia==true) {//Misma lógica que antes. Pero ahora, al ser una transerencia, sólo se va a mostrar por pantalla el saldo restante de la cuenta que transiere/debita y no se mostrará el saldo de la cuuenta a la que se transiere/deposita.
            saldo+=monto;
            for (Cuenta cuenta : listaCuentas) {
              
                if (this.getCbu()==cuenta.getCbu()) {
                    listaCuentas.set(posicion, this);
                    break;
                }
               posicion++;
            }
            posicion=0;
        } else if (monto<0 && transferencia==true) {
            saldo+=monto;
            if (saldo>=0) {
                System.out.printf("Transferencia realizada correctamente. %nSaldo anterior:%.2f %nSaldo actual:%.2f %n %n",saldoAnterior,saldo);
                for (Cuenta cuenta : listaCuentas) {
              
                    if (this.getCbu()==cuenta.getCbu()) {
                        listaCuentas.set(posicion, this);
                        break;
                    }
                   posicion++;
                }
                posicion=0;
            } else {
                saldo-=monto;
                System.out.printf("No se pudo realizar la transferencia.%nMotivo: saldo insuficiente%n %n");
                exito=false;   
            } 
        }

        return exito;
    }

    //Método de instancia que se encarga de las transferencias.
    public void transferencia(double monto,Cuenta recibe,ArrayList<Cuenta>listaCuentas){
        double cotizacion1a2=0.0010;//EL valor por el que se convertirán los pesos a dólares (1 dólar 0,0010 pesos).
        double cotizacion2a1=967.75;//EL valor por el que se convertirán los dólares a pesos (1 dólar 967,75 pesos).
        
        if (this.getTipo()==1 && recibe.getTipo()==2) {//Comprobación del tipo de transferencia. En este caso, de peso a dólar (cuenta de tipo 1 a cuenta de tipo 2).

            monto*=-1;//Multiplica el monto por -1 para hacerlo negatvio y realizar una extracción en la cuenta que transfiere.
            
            if (this.depositoExtraccion(monto,true,listaCuentas)==true) {//Extrae el monto de la cuenta que transfiere, si es que el saldo es positivo luego de extraer ese dinero. Se realiza una extracción porque el monto es negativo. 
           
            monto*=-1;//Se vuelve a multiplicar el monto por -1, esta vez para hacerlo positivo y así realizar un depósito en la cuenta que recibe la transferencia.
            monto*=cotizacion1a2;//Convierte el monto ingresado en pesos a dólares.
            
            recibe.depositoExtraccion(monto,true,listaCuentas);//Deposita el monto en la cuenta a la que se transiere. Se realiza un depósito y no una extracción porque el monto es positivo.
            }
        } else if (this.getTipo()==2 && recibe.getTipo()==1) {//Comprobación del tipo de transferencia. En este caso, de dólar a peso(cuenta de tipo 2 a cuenta de tipo 1).
                
            monto*=-1;//Multiplica el monto por -1 para hacerlo negatvio y realizar una extracción en la cuenta que transfiere.

                if (this.depositoExtraccion(monto,true,listaCuentas)==true) {//Extrae el monto de la cuenta que transfiere, si es que el saldo es positivo luego de extraer ese dinero. Se realiza una extracción porque el monto es negativo. 
                
                    monto*=-1;//Se vuelve a multiplicar el monto por -1, esta vez para hacerlo positivo y así realizar un depósito en la cuenta que recibe la transferencia.
                    monto*=cotizacion2a1;//Convierte el monto ingresado en dólares a pesos.    
               
                    recibe.depositoExtraccion(monto,true,listaCuentas);//Deposita el monto en la cuenta a la que se transiere. Se realiza un depósito y no una extracción porque el monto es positivo.
                }
            } else if (this.getCbu()==recibe.getCbu()) {//validación para que una cuenta no pueda transferirse dinero a sí misma.
                
                System.out.println();
                System.out.println("Error, una cuenta no puede transferirse a sí misma. Ingrese otro CBU");
                System.out.println();

            } else {//Transferencia entre cuentas del mismo tipo.
                
                monto*=-1;//Multiplica el monto por -1 para hacerlo negatvio y realizar una extracción en la cuenta que transfiere.
                if (this.depositoExtraccion(monto,true,listaCuentas)==true) {//Extrae el monto de la cuenta que transfiere, si es que el saldo es positivo luego de extraer ese dinero. Se realiza una extracción porque el monto es negativo. 
                monto*=-1;//Se vuelve a multiplicar el monto por -1, esta vez para hacerlo positivo y así realizar un depósito en la cuenta que recibe la transferencia.
               
                recibe.depositoExtraccion(monto,true,listaCuentas);//Deposita el monto en la cuenta a la que se transiere. Se realiza un depósito y no una extracción porque el monto es positivo.
                }
            }

        }
    
    //Método de instancia para devolver el cbu.
    public int getCbu() {
        return cbu;
    }

    //Método de instancia para modificar el cbu.
    public void setCbu(int cbu) {
        this.cbu = cbu;
    }

    //Método de instancia para devolver el tipo.
    public int getTipo() {
        return tipo;
    }

    //Método de instancia para modificar el tipo.
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    //Método de instancia para devolver el titular.
    public int getTitular() {
        return titular;
    }

    //Método de instancia para modificar el titular.
    public void setTitular(int titular) {
        this.titular = titular;
    }

    //Método de instancia para devolver el saldo.
    public double getSaldo() {
        return saldo;
    }

    //Método de instancia para modificar el saldo.
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
