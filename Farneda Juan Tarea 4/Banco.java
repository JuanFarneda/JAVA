import java.util.ArrayList;
public class Banco {
    public static void main(String[] args) {
        //Creacion de listas para almacenar las personas y las cuentas.
        ArrayList<Persona> listaPersonas = new ArrayList<>();
        ArrayList<Cuenta> listaCuentas = new ArrayList<>();

        //Creación de instancias de la clase Persona.
        Persona p1= new Persona("Manuel", "Lanzini", 3456, "Buenos Aires", "Martinez", "Calle Falsa 123");
        Persona p2= new Persona("Fabricio", "Bustos", 9999, "Buenos Aires", "San Isidro", "Calle real 67");
        Persona p3= new Persona("Franco", "Armani", 5563, "Buenos Aires", "CABA", "Rosas 43");
        Persona p4= new Persona("Paulo", "Diaz", 1652, "Entre Rios", "Parana", "Direccion 66");
        Persona p5= new Persona("Franco", "Mastantuono", 8754, "Buenos Aires", "CABA", "Random 33") ;

        //Creación de instancias de la clase Cuenta. Las cuentas tipo 1 son en pesos y las cuentas tipo 2 son en dólares.
        Cuenta c1= new Cuenta(1, p1.getDni());//cuenta en pesos
        Cuenta c2= new Cuenta(1, p2.getDni());//cuenta en pesos
        Cuenta c3= new Cuenta(1, p3.getDni());//cuenta en pesos
        Cuenta c4= new Cuenta(2, p4.getDni());//cuenta en dólares
        Cuenta c5= new Cuenta(2, p5.getDni());//cuenta en dólares
        
        //Se agregan las personas a la lista 'listaPersonas'
        listaPersonas.add(p1);
        listaPersonas.add(p2);
        listaPersonas.add(p3);
        listaPersonas.add(p4);
        listaPersonas.add(p5);
        
        //Se agregan las cuentas a la lista 'listaCuentas'
        listaCuentas.add(c1);
        listaCuentas.add(c2);
        listaCuentas.add(c3);
        listaCuentas.add(c4);
        listaCuentas.add(c5);

        //Depósito de pesos en las cuentas pesos.
        c1.depositoExtraccion(1000,false,listaCuentas);
        c2.depositoExtraccion(1000,false,listaCuentas);
        c3.depositoExtraccion(1000,false,listaCuentas);

        //Depósito de dólares en las cuentas dólares.
        c4.depositoExtraccion(100,false,listaCuentas);
        c5.depositoExtraccion(100,false,listaCuentas);

        //Extracción de 400 pesos en la cuenta c1.
        c1.depositoExtraccion(-400, false,listaCuentas);

        //Extracción de 50 dólares en la cuenta c4.
        c4.depositoExtraccion(-50, false,listaCuentas);
        
        //Intento de extracción en la cuenta c2. 
        c2.depositoExtraccion(-5000, false,listaCuentas);

        //Transferencia de 10 dóalres de la cuenta c4 a la cuenta c1.
        c4.transferencia(10,c1,listaCuentas);

        //Transferencia de 1000 pesos de la cuenta c4 a la cuenta c1.
        c2.transferencia(1000, c5, listaCuentas);

        //Muestra las cuentas cuyos titulares viven en "Buenos Aires".
        Persona.mostrarCuentasProvincia(listaPersonas, "Buenos Aires", listaCuentas);
        
        //Muestra las cuentas cuyos titulares viven en "CABA".
        Persona.mostrarCuentasCiudad(listaPersonas, "CABA", listaCuentas);
    }   
}