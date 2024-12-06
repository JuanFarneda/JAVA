import java.util.ArrayList;

public class Persona {
    //declaración de variables de instancia.
    private String nombre,apellido,provincia,ciudad,direccion;
    private int dni;

    //Constructor de la clase Persona.
    public Persona(String nombre,String apellido,int dni,String provincia,String ciudad,String direccion){
        this.nombre=nombre;
        this.apellido=apellido;
        this.dni=dni;
        this.provincia=provincia;
        this.ciudad=ciudad;
        this.direccion=direccion;
    }

    @Override
    //Override del método toString en la clase Object para que, al intentar imprimir una instancia de Persona, 
    //imprima los valores que se requieren en vez de la posición en memoria de la intancia
    public String toString() {
        return "Nombre:" + nombre +" Apellido:"+ apellido + " DNI:" + dni + " Provincia:" + provincia + " Ciudad:" + ciudad + " Direccion:" + direccion;
    }

    //Método de clase que permite mostrar toda la lista listPersonas.
    public static void mostrarListaPersonas(ArrayList<Persona>listaPersonas){
        for (Persona persona : listaPersonas) {//for each que crea una variable temporal persona de tipo Persona y recorre la lista listaPersonas.
            System.out.println(persona);       //En cada iteración, persona representa uno de los objetos o elementos de listaPersona y se imprime por pantalla mediante un println()
            System.out.println();
        }
    }

    //Método de clase que se encarga de mostrar las cuentas que hay en una determinada provincia.
    public static void mostrarCuentasProvincia(ArrayList<Persona>listaPersonas,String prov,ArrayList<Cuenta>listaCuentas){
        ArrayList<Integer> dnis= new ArrayList<>();//Se crea una nueva lista llamada 'dnis' para almacenar los DNIs de las personas de la provincia deseada.
        for (Persona persona : listaPersonas) {//For each que recorre la lista listaPersonas.
            if (persona.getProvincia().equalsIgnoreCase(prov)) {//En caso de que la provincia deseada coincida con la provincia de la persona en la lista, se añadirá el DNI de la persona a la lista dnis.
                dnis.add(persona.getDni());                     
            }
        }

        for (Integer dni : dnis) {//For each que recorre la lista dnis.
            for (Cuenta cuenta : listaCuentas) {//For each que recorre la lista listaCuentas.
                if (dni==cuenta.getTitular()) {//Si el dni de la lista dnis coincide con el titular (o sea, dni del titular) de la lista listaCuentas, se imprime por pantalla ese elemento de la lista.
                    System.out.println(cuenta);
                    System.out.println();
                }     
            }
        }
    }

    //Método de clase que se encarga de mostrar las cuentas que hay en una determinada provincia. Mista lógica que el método mostrarCuentasProvincias, pero adaptado para las ciudades.
    public static void mostrarCuentasCiudad(ArrayList<Persona>listaPersonas,String ciud,ArrayList<Cuenta>listaCuentas){
        ArrayList<Integer> dnis= new ArrayList<>();
        for (Persona persona : listaPersonas) {
            if (persona.getCiudad().equalsIgnoreCase(ciud)) {
                dnis.add(persona.getDni());
            }
        }

        for (Integer dni : dnis) {  
            for (Cuenta cuenta : listaCuentas) {
                if (dni==cuenta.getTitular()) {
                    System.out.println(cuenta);
                    System.out.println();
                }     
            }
        }
    }
    //Método de instancia para devolver el nombre.
    public String getNombre() {
        return nombre;
    }

    //Método de instancia para modificar el nombre.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Método de instancia para devolver el apellido.
    public String getApellido() {
        return apellido;
    }
    
    //Método de instancia para modificar el apellido.
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    //Método de instancia para devolver la provincia.
    public String getProvincia() {
        return provincia;
    }

    //Método de instancia para modificar la provincia.
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    //Método de instancia para devolver la ciudad.
    public String getCiudad() {
        return ciudad;
    }

    //Método de instancia para modiicar la ciudad.
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    //Método de instancia para devolver la direccion.
    public String getDireccion() {
        return direccion;
    }

    //Método de instancia para modificar la direccion.
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    //Método de instancia para devolver el dni.
    public int getDni() {
        return dni;
    }

    //Método de instancia para modificar el dni.
    public void setDni(int dni) {
        this.dni = dni;
    }

}
